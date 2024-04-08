package com.agilysys.qa.gridIron.common;

import com.agilysys.qa.clients.ClientFactory;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.adjustments.reservations.AdjustReservation;
import com.agilysys.insertanator.constants.GatewayConstants;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.platform.common.exception.ClientException;
import com.agilysys.platform.common.rguest.exception.RGuestException;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.account.model.PaymentMethod;
import com.agilysys.pms.profile.model.Group;
import com.agilysys.pms.property.model.DateRollStatus;
import com.agilysys.pms.reservation.model.ReservationCancellationRequest;
import com.agilysys.pms.reservation.model.ReservationSummary;
import com.agilysys.pms.reservation.model.SummaryReport;
import com.agilysys.qa.client.group.ClientGroupRelease;
import com.agilysys.qa.client.payment.gateway.ClientPaymentGatewayGet;
import com.agilysys.qa.client.payment.method.ClientPaymentMethodGet;
import com.agilysys.qa.client.reservation.ClientReservationCancel;
import com.agilysys.qa.client.reservation.ClientReservationGet;
import com.agilysys.qa.clients.PmsClientException;
import com.agilysys.qa.data.builder.reservation.BuilderReservationCancellationRequest;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.agilysys.qa.helpers.ClientHelper;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.agilysys.qa.gridIron.builders.batchoperation.BatchCheckoutOperation.flowBatchCheckoutAllReservations;
import static com.agilysys.qa.gridIron.builders.batchoperation.BuilderBatchCheckinOperation.flowBatchCheckinAllReservations;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckInSection.waitForModalCheckinProcessingToClose;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckOutSection.waitForModalCheckoutProcessingToClose;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestBatchOperations extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	static String lastName = null;

	static String CompanyName = "Company " + RandomHelper.getRandomNumericString(8);

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createRooms(10).createPaymentMethodAllCC().createRoomTypes(1).createReservation(5).create();

		new LoginApplication().Login(masterObject);
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@TestCase(id = "RST-12879")
	@Test(groups = { "sanity" })
	public void testBatchCheckinAllReservations() {

		PFSearchTile.waitForSearchPageToLoad();

		flowBatchCheckinAllReservations();

		waitForModalCheckinProcessingToClose();
		Selenide.sleep(2000);
		Assert.assertEquals(
				$$(LocatorsBatchCheckinSection.LIST_COLUMN_STATUS).filterBy(Condition.text("Checked in")).size(), 5);
	}

	@TestCase(ids = {"RST-5879","RST-20570","RST-5877"})
	@Test(groups = { "sanity" })
	public void testBatchCheckoutAllReservations() {

		//DateRoll.Roll(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId());
		//DateRoll.Roll(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId());
		PFSearchTile.waitForSearchPageToLoad();

		flowBatchCheckinAllReservations();

		waitForModalCheckinProcessingToClose();
		Selenide.sleep(2000);
		doValidDateRoll(masterObject.getProperty(), true);
		doValidDateRoll(masterObject.getProperty(), true);

		Selenide.refresh();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		flowBatchCheckoutAllReservations();

		waitForModalCheckoutProcessingToClose();

		Assert.assertEquals(
				$$(LocatorsBatchCheckinSection.LIST_CHECKOUT_COLUMN_STATUS).filterBy(Condition.text("Successfully Checked out")).size(), 5);
	
	}

	public void doValidDateRoll(Property property, boolean override) {
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
			clearReservation(reservationSummary, property);
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

	String ERROR_MESSAGE_PREFIX = "tenantId: %s -  propertyId: %s";

	public void clearReservation(ReservationSummary reservationSummary, Property property) {
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
