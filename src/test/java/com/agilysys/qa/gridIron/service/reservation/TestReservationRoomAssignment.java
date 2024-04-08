package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.common.model.TrackingInfo;
import com.agilysys.pms.rates.api.RateServiceInterface;
import com.agilysys.pms.rates.model.RoomUpgradesConfig;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.profile.model.GuestType;
import com.agilysys.pms.profile.model.MarketSegment;
import com.agilysys.pms.property.model.Room;
import com.agilysys.pms.property.model.RoomClass;
import com.agilysys.pms.property.model.RoomType;
import com.agilysys.pms.rates.client.RateServiceClient;
import com.agilysys.pms.rates.model.RatePlanDetail;
import com.agilysys.pms.rates.model.RateType;
import com.agilysys.pms.rates.model.UpgradeRateDetail;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.qa.client.area.room.ClientRoomClassCreate;
import com.agilysys.qa.client.area.room.type.ClientRoomTypeGet;
import com.agilysys.qa.client.area.room.type.ClientRoomTypeUpdate;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsFooterButtonsSection;
import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsCurrentlySelectedRoom;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFCurrentlySelectedRoom;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFRecommededUpgradesAndOtherMatchingRoom;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationCheckInModal;
import com.agilysys.qa.gridIron.pageobject.reservation.PORooms;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import junit.framework.Assert;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.agilysys.qa.constants.EndPoints;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestReservationRoomAssignment extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	String directBillAccountName = RandomHelper.getRandomAlphaString(5);

	String tenantId = null;
	String propertyId = null;
	BuilderBookReservation builderBookReservation = null;
	ReservationCreateRequest reservationCreateRequest = null;
	LocalDate propertyDate = null;
	TrackingInfo trackingInfo = new TrackingInfo();
	MarketSegment marketSegment = null;
	GuestType guestType = null;
	String swipeData = "2B301801F392400839B%*401200******0026^BOYINGTON/CLINTON C ^1804***********?*;401200******0026=1804************?*39457A24612BA961782B3CD8E5DCCB9EAEECD03301A400636A4D9AFE7564E4000BD74B198FF3BB7DA1830246F75482F30A638F2296F5F67970EDAF0529EE2E5349B3A11DB8E1E290D0F9454769CF3F4814A7E36065F0E0F3D5AB149828E430510A2A54A5672DECAF0000000000000000000000000000000000000000000000000000000000000000000000000000000034313854303632353436FFFF9876540000C002B6446C03";
	String packageName = null;
	RatePlanDetail packageRatePlan = null;
	Room room = null;
	POReservationCheckInModal poReservationCheckInModal;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createBuilding()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createPaymentMethodAllCC().createRoomTypes(1).createRooms(5).createCategories(1).createSubCategories(1)
				.createRoomTypes(2)
				.createItems(3).createReservation(1).createTravelAgents(1).createVIPStatuses(1).create();

		List<RoomType> roomTypes = masterObject.getRoomTypes();
		List<RoomClass> roomClasses = new ArrayList<RoomClass>();

		ClientRoomClassCreate clientRoomClassCreate = new ClientRoomClassCreate();
		ClientRoomTypeGet clientRoomTypeGet = new ClientRoomTypeGet();
		ClientRoomTypeUpdate clientRoomTypeUpdate = new ClientRoomTypeUpdate();

		for(RoomType roomType : roomTypes){
			RoomClass roomClass = new RoomClass();
			roomClass.setName(roomType.getName() +"-"+roomType.getTypeCode());
			roomClass = clientRoomClassCreate.createRoomClass(masterObject.getProperty(), roomClass);
			roomClasses.add(roomClass);

			com.agilysys.pms.property.model.RoomType pmsRoomType = clientRoomTypeGet.getRoomTypeById(masterObject.getProperty(), roomType);
			roomType.setRoomClassId(roomClass.getId());
			pmsRoomType.setRoomClassId(roomClass.getId());
			pmsRoomType = clientRoomTypeUpdate.updateRoomType(masterObject.getProperty(), pmsRoomType);
		}


		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());

		RateServiceInterface ratesGet = new RateServiceClient(EndPoints.getRateServiceURI()).getProxy();
		UpgradeRateDetail[] gradesArray = new UpgradeRateDetail[3];
		List<UpgradeRateDetail> grades = new ArrayList<>();
		BigDecimal amount = BigDecimal.ZERO;
		for (int i = 0; i < roomClasses.size(); i++) {
			UpgradeRateDetail upgradeRate = new UpgradeRateDetail();
			upgradeRate.setAmount(amount);
			upgradeRate.setRoomClassId(roomClasses.get(i).getId());
			upgradeRate.setType(RateType.ADJUST_UNIT);
			grades.add(upgradeRate);
			gradesArray[i] = upgradeRate;
			amount = amount.add(BigDecimal.TEN);
		}
        // Room upgrades setUp in property
		RoomUpgradesConfig roomUpgradesConfig = new RoomUpgradesConfig(null, true, false, grades, "upgrade Fee", "");
		ratesGet.updateRoomUpgradeConfig(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId(), roomUpgradesConfig);
		poReservationCheckInModal = new POReservationCheckInModal();
		new LoginApplication().Login(masterObject);

		PFSearchTile.waitForSearchPageToLoad();
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		room = new CreateRoom().create(masterObject.getRoomTypes().get(0), masterObject.getBuilding(), masterObject.getProperty());
		open(EndPoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);
		PFSearchTile.waitForSearchPageToLoad();
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);

		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
		builderBookReservation.getReservationCreateRequest().setWalkIn(true);
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();
	}

	@Test (priority = 10)
	@TestCase(id="RST-4064")
	public void testReservationAssignAndReleaseRoom() {
		PORooms.assignRoom(room.getRoomNumber());
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyReservationRoomNumber(room.getRoomNumber());
		PFCurrentlySelectedRoom.clickReleaseRoom();
		$(LocatorsFooterButtonsSection.BUTTON_CHECKIN).shouldBe(Condition.disabled);
		$(LocatorsFooterButtonsSection.BUTTON_CANCEL_RESERVATION).shouldBe(Condition.enabled);
	}

	@Test
	public void testReservationAssignWithDoNotMoveFlag() {
		PORooms.assignRoom(room.getRoomNumber());
		Selenide.refresh();
		String reason = RandomHelper.getRandomAlphaNumericString(10);
		PORooms.addDoNotMoveGuest(reason);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		//PFCurrentlySelectedRoom.clickExclamationDoNotMoveGuest();
		VerifyReservation.verifyElementValue(reason, LocatorsCurrentlySelectedRoom.TEXTAREA_DO_NOT_MOVE_REASON);
		PORooms.removeDoNotMoveGuest();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		$(LocatorsCurrentlySelectedRoom.BUTTON_CHANGE).shouldBe(Condition.enabled);
	}

	@Test
	@TestCase(id="RST-4088")
	public void testReservationUpgradeRoom(){
		String recommendedUpgradeRoomNumber = PFRecommededUpgradesAndOtherMatchingRoom.getRecommendedUpgradeRoomNumber();
		PFRecommededUpgradesAndOtherMatchingRoom.upgradeRoom();
		Selenide.sleep(4000);
		click(By.xpath("//button[text()= 'Yes']"));
		PORooms.confirmRoom();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyReservationRoomNumber(recommendedUpgradeRoomNumber);
	}

	@Test
	public void testReservationReleaseUnconfirmedRoom(){
		String recommendedUpgradeRoomNumber = PFRecommededUpgradesAndOtherMatchingRoom.getRecommendedUpgradeRoomNumber();
		PFRecommededUpgradesAndOtherMatchingRoom.upgradeRoom();
		Selenide.sleep(4000);
		PORooms.releaseUnconfirmedRoom();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		Assert.assertEquals(recommendedUpgradeRoomNumber, PFRecommededUpgradesAndOtherMatchingRoom.getRecommendedUpgradeRoomNumber());
	}

//	@Test
//	public void testReservationAssignRoomFromOtherMatchingSection(){
//		String roomNumber = PFRecommededUpgradesAndOtherMatchingRoom.getOtherMatchingRoomNumber(1);
//		PFRecommededUpgradesAndOtherMatchingRoom.clickOtherMatchingRow(1);
//		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
//		VerifyReservation.verifyReservationRoomNumber(roomNumber);
//	}
}