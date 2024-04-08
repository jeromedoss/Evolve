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
import com.agilysys.qa.gridIron.builders.home.SearchReservationAndOpen;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.reservation.ModifyStayReservation;
import com.agilysys.qa.gridIron.builders.reservation.NightAudit;
import com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsModifyStay;
import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails;
import com.agilysys.qa.gridIron.utils.ClientPropertyDate;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import java.time.Duration;

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

public class TestModifyStayforCCReservation extends StayUIWrappers {

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
				.createGuests(1).createRooms(8).createPaymentMethodAllCC().createCancelationPolicy().createRoomTypes(1)
				.create();

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

		arrivalDate = new ClientPropertyDate().getPropertyDate(masterObject.getProperty().getTenantId(),
				masterObject.getProperty().getPropertyId());
		departureDate = arrivalDate.plusDays(2);

		reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(),
				new ServiceReservationFeed().paymentMethodNewCreditCard().cardValidVisa().arrive(arrivalDate.toString())
						.depart(departureDate.toString()));
	}

	public void loginAndOpenReservation() {

		open(Endpoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);

		Selenide.sleep(2000);

		clickTabReservations();

		new SearchReservationAndOpen(reservation.getPrimaryGuestInfo().getLastName(), reservation.getPrimaryGuestInfo().getFirstName(),
				reservation.getConfirmationCode()).searchByName();

	}

	@Test
	@TestCase(ids = {"RST-1171"})
	public void testUndoCheckinReservation() {

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, property,
				masterObject.getRooms().get(0));
		loginAndOpenReservation();
		PO.UndoCheckinReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("RES");

	}

	@Test
	@TestCase(ids = {"RST-170"})
	public void testEarlyCheckoutReservation() {

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, property,
				masterObject.getRooms().get(1));
		loginAndOpenReservation();
		PO.EarlyCheckoutReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("DPT");

	}

	@Test
	@TestCase(ids = {"RST-3888"})
	public void testCheckinReservation() {

		ManipulateReservation.getAdjustReservation().confirmRoom(reservation, property,
				masterObject.getRooms().get(2));
		loginAndOpenReservation();
		PO.CheckinCheckReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("INH");

	}

	@Test
	@TestCase(id = "RST-4159")
	public void testCancelReservation() {

		loginAndOpenReservation();
		PO.CancelReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("CXL");

	}

	@Test
	@TestCase(id = "RST-4162")
	public void testUndoCancelReservation() {

		ManipulateReservation.getAdjustReservation().cancel(reservation, masterObject.getCancelationPolicy());
		loginAndOpenReservation();
		PO.UndoCancelReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("RES");

	}

	@Test
	@TestCase(ids = {"RST-11498","RST-171"})
	public void testUndoCheckoutReservation() {

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, property,
				masterObject.getRooms().get(3));
		ManipulateReservation.getAdjustReservation().earlyCheckOut(reservation, property);
		loginAndOpenReservation();
		PO.UndoCheckoutReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("INH");

	}

	@Test
	public void testZNoShowCancelReservation() {

		click(HeaderDropDowns.NAV_NIGHT_AUDIT);
		new NightAudit(reservation.getConfirmationCode()).SelectAndClick();
		//need to confirm
		$(LocatorsFoliosDetails.TAB_SUMMARY).should(Condition.exist,  Duration.ofMillis(Configuration.timeout));
		PO.NoShowCancelReservation();
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("NOS");

	}
}
