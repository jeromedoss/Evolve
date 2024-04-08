package com.agilysys.qa.gridIron.utils;

import com.agilysys.insertanator.adjustments.reservations.AdjustReservation;
import com.agilysys.insertanator.constants.GatewayConstants;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.platform.common.exception.ClientException;
import com.agilysys.platform.common.rguest.exception.RGuestException;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.account.model.PaymentMethod;
import com.agilysys.pms.common.exceptions.system.SystemErrorCode;
import com.agilysys.pms.payment.model.PaymentGatewaySettingsView;
import com.agilysys.pms.profile.model.Group;
import com.agilysys.pms.property.model.DateRollStatus;
import com.agilysys.pms.reservation.model.ReservationCancellationRequest;
import com.agilysys.pms.reservation.model.ReservationSummary;
import com.agilysys.pms.reservation.model.SummaryReport;
import com.agilysys.qa.client.group.ClientGroupRelease;
import com.agilysys.qa.client.payment.gateway.ClientPaymentGatewayGet;
import com.agilysys.qa.client.payment.gateway.ClientPaymentGatewayUpsert;
import com.agilysys.qa.client.payment.method.ClientPaymentMethodGet;
import com.agilysys.qa.client.reservation.ClientReservationCancel;
import com.agilysys.qa.client.reservation.ClientReservationGet;
import com.agilysys.qa.clients.ClientFactory;
import com.agilysys.qa.clients.PmsClientException;
import com.agilysys.qa.data.builder.reservation.BuilderReservationCancellationRequest;
import com.agilysys.qa.helpers.ClientHelper;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Configuration;

import org.joda.time.LocalDate;
import org.testng.Assert;

import javax.crypto.MacSpi;

import java.time.Duration;
import java.util.List;

public class PMSHelper {
	String ERROR_MESSAGE_PREFIX = "tenantId: %s -  propertyId: %s";
	
	
	public void enableIDTech(Property property){
		PaymentGatewaySettingsView  paymentGatewaySettingsView = new ClientPaymentGatewayGet().getPaymentGatewaySettings(property);
		paymentGatewaySettingsView.setIdTechDevicePresent(true);
		new ClientPaymentGatewayUpsert().updatePaymentGatewaySettings(property, paymentGatewaySettingsView);
	}

	public void doValidDateRoll(Property property, boolean override, MasterObject masterObject) {
		LocalDate propertyDate = ClientHelper.call(() ->
				ClientFactory.getPropertyManagement().getPropertyDate(property.getTenantId(), property.getPropertyId()));
		DateRollStatus dateRollStatus;
		int seconds = 0;
		String terminalId = new ClientPaymentGatewayGet()
				.getTerminalId(property.getTenantId(), property.getPropertyId(),
						true);
		SummaryReport summaryReport = ClientHelper.callUntil(() -> ClientFactory.getReservationReporting()
						.getSummaryReport(property.getTenantId(), property.getPropertyId(), propertyDate), null, 1000,
				20000,
				null);
		for (Group group : summaryReport.getGroupsWithRemaining()) {
			new ClientGroupRelease().releaseRoomBlocks(property, "", group, propertyDate.toString());
		}
		List<ReservationSummary> remainingArrival = summaryReport.getRemainingArrival();

		remainingArrival.addAll(summaryReport.getRemainingDeparture());

		for (ReservationSummary reservationSummary : remainingArrival) {
			clearReservation(reservationSummary, property, masterObject);
		}

		ClientHelper.callUntil(() -> ClientFactory.getPropertyManagement()
						.startDateRoll(property.getTenantId(), property.getPropertyId(), propertyDate, terminalId,
								override),
				null, 1000, 30000, null);



		do {
			dateRollStatus = ClientHelper.call(() -> ClientFactory.getPropertyManagement()
					.getDateRollStatus(property.getTenantId(), property.getPropertyId()));
			ThreadHelper.sleep(1000);
			seconds++;
		} while (!dateRollStatus.getStatus().equals(DateRollStatus.Status.COMPLETED) && seconds < 240);

		//Verify that the date roll succeeded
		Assert.assertTrue(dateRollStatus.getStatus().equals(DateRollStatus.Status.COMPLETED),
				String.format("Date roll did not complete successfully after 240 seconds\nExpected status: %s\nReceived status: %s",
						DateRollStatus.Status.COMPLETED.toString(), dateRollStatus.getStatus().toString()));
	}

	public void clearReservation(ReservationSummary reservationSummary, Property property, MasterObject masterObject) {
		LocalDate propertyDate = ClientHelper.call(() ->
				ClientFactory.getPropertyManagement().getPropertyDate(property.getTenantId(), property.getPropertyId()));
		reservationSummary = new ClientReservationGet().getReservationById(property,reservationSummary);

		try {
			if (reservationSummary.getStatus().equalsIgnoreCase("RES")) {
				//Get Payment Method
				PaymentMethod cashPaymentMethod = new ClientPaymentMethodGet().getCashPaymentMethod(property);

				//Create Reservation Cancellation Request
				ReservationCancellationRequest reservationCancellationRequest =
						new BuilderReservationCancellationRequest(masterObject.getCancelationPolicy(), cashPaymentMethod,
								GatewayConstants.getTerminalId()).build();

				// Cancel the reservation
				new ClientReservationCancel()
						.cancelReservation(property, reservationSummary, reservationCancellationRequest);
			} else if (reservationSummary.getStatus().equalsIgnoreCase("INH"))
				if (propertyDate.isBefore(reservationSummary.getDepartureDate())) {
					//Check-out
					new AdjustReservation().earlyCheckOut(reservationSummary, property);
				} else {
					new AdjustReservation().checkout(reservationSummary, property);
				}

		}
		catch (ClientException | RGuestException e)
		{
			throw new PmsClientException(String.format("Failed to clear the reservation. " + ERROR_MESSAGE_PREFIX + " - confirmation Number %s.",
					property.getTenantId(), property.getPropertyId(), reservationSummary.getConfirmationCode()), e);
		}
	}

}
