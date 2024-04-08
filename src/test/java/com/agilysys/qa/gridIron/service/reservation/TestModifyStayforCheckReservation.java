package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.adjustments.reservations.ManipulateReservation;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.reservation.CreateReservation;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.insertanator.services.create.reservationServices.ServiceReservationFeed;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.reservation.model.ReservationSummary;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.gridIron.builders.home.SearchReservationAndOpen;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.reservation.ModifyStayReservation;
import com.agilysys.qa.gridIron.builders.reservation.NightAudit;
import com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsModifyStay;
import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickTabReservations;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestModifyStayforCheckReservation extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	ModifyStayReservation PO = new ModifyStayReservation();

	private static LocalDate arrivalDate;
	private static LocalDate departureDate;

	static ReservationSummary reservation;

	String tenantId = null;
	String propertyId = null;
	Property property;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode).createRatePlan()
				.createGuests(1).createRooms(8).createPaymentMethodAllCC()
				.createCancelationPolicy("CXL", "Cancellation policy").createRoomTypes(1).create();

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();

		property = masterObject.getProperty();
		new LoginApplication().Login(masterObject);
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {

		arrivalDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());
		departureDate = arrivalDate.plusDays(2);

		reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(), new ServiceReservationFeed()
						.paymentMethodCheck().arrive(arrivalDate.toString()).depart(departureDate.toString()));
	}

	public void loginAndOpenReservation() throws Exception {

		open(Endpoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);

		Selenide.sleep(2000);

		clickTabReservations();

		new SearchReservationAndOpen(reservation.getPrimaryGuestInfo().getLastName(), reservation.getPrimaryGuestInfo().getFirstName(),
				reservation.getConfirmationCode()).searchByName();

	}

	@Test
	public void testUndoCheckinReservation() throws Exception {

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, property,
				masterObject.getRooms().get(0));
		loginAndOpenReservation();
		PO.UndoCheckinReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("RES");

	}

	@Test
	public void testEarlyCheckoutReservation() throws Exception {

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, property,
				masterObject.getRooms().get(1));
		loginAndOpenReservation();
		PO.EarlyCheckoutReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("DPT");

	}

	@Test
	public void testCheckinReservation() throws Exception {

		ManipulateReservation.getAdjustReservation().confirmRoom(reservation, property,
				masterObject.getRooms().get(2));
		loginAndOpenReservation();
		PO.CheckinCheckReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("INH");

	}

	@Test
	@TestCase(id = "RST-4159")
	public void testCancelReservation() throws Exception {

		loginAndOpenReservation();
		PO.CancelReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("CXL");

	}

	@Test
	public void testUndoCancelReservation() throws Exception {

		ManipulateReservation.getAdjustReservation().cancel(reservation, masterObject.getCancelationPolicy());
		loginAndOpenReservation();
		PO.UndoCancelReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("RES");

	}

	@Test
	public void testUndoCheckoutReservation() throws Exception {

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, property,
				masterObject.getRooms().get(3));
		ManipulateReservation.getAdjustReservation().earlyCheckOut(reservation, property);
		loginAndOpenReservation();
		PO.UndoCheckoutReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("INH");

	}

	@Test
	public void testZNoShowCancelReservation() throws Exception {

		click(HeaderDropDowns.NAV_NIGHT_AUDIT);
		new NightAudit(reservation.getConfirmationCode()).SelectAndClick();
		$(LocatorsFoliosDetails.TAB_SUMMARY).should(Condition.exist,  Duration.ofMillis(Configuration.timeout));
		PO.NoShowCancelReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("NOS");

	}

}
