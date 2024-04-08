package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.common.model.BooleanSetting;
import com.agilysys.common.model.FrequencyType;
import com.agilysys.common.model.Setting;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.item.CreateInventoryItem;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.account.model.InventoryItem;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.profile.model.GuestType;
import com.agilysys.pms.profile.model.MarketSegment;
import com.agilysys.pms.property.model.Property;
import com.agilysys.pms.rates.model.*;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.pms.reservation.model.ThirdPartyConfirmation;
import com.agilysys.common.model.TrackingInfo;
import com.agilysys.qa.constants.EndPoints;
import com.agilysys.qa.helpers.ThreadHelper;
import com.agilysys.qa.client.account.item.ClientAccountingItemGet;
import com.agilysys.qa.client.profile.guest.type.ClientGuestTypeCreate;
import com.agilysys.qa.client.profile.guest.type.ClientGuestTypeUpdate;
import com.agilysys.qa.client.profile.market.segment.ClientMarketSegmentCreate;
import com.agilysys.qa.client.property.ClientPropertyGet;
import com.agilysys.qa.client.property.ClientPropertyUpdate;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.client.rate.bundle.ClientComponentBundleCreate;
import com.agilysys.qa.client.rate.calendar.ClientRateCalendarGet;
import com.agilysys.qa.client.rate.category.ClientRateCategoryGet;
import com.agilysys.qa.client.rate.plan.ClientRatePlanCreate;
import com.agilysys.qa.client.rate.plan.ClientRatePlanUpdate;
import com.agilysys.qa.client.rate.strategy.ClientRateStrategyGet;
import com.agilysys.qa.data.builder.profile.guest.type.BuilderGuestType;
import com.agilysys.qa.data.builder.profile.market.segment.BuilderMarketSegment;
import com.agilysys.qa.data.builder.rate.*;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.constants.locators.reservation.summary.LocatorsSummaryModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationSummary;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationSummary;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.ValidationBase;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestReservationSummary extends StayUIWrappers {

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

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createBuilding()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createPaymentMethodAllCC().createRoomTypes(1).createRooms(5).createCategories(1).createSubCategories(1)
				.createItems(3).createReservation(1).createTravelAgents(1).createVIPStatuses(1).create();

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());

		// Update Reservation alias settings
		Property propertyToBeUpdated = new ClientPropertyGet().getPropertyById(masterObject.getProperty());
		Map<String, Setting<?>> featureSettings = propertyToBeUpdated.getFeatureSettings();
		BooleanSetting reservationAliasSetting = BooleanSetting.class.cast(featureSettings.get("RESERVATION_ALIAS"));
		reservationAliasSetting.setValue(true);
		new ClientPropertyUpdate().updateProperty(masterObject.getProperty(), propertyToBeUpdated);

		String code = RandomHelper.getRandomAlphaNumericString(6);
		String name = "GuestType_" + code;

		marketSegment = new ClientMarketSegmentCreate().createMarketSegment(masterObject.getProperty(),
				new BuilderMarketSegment().build());
		List<String> marketSegmentIds = new LinkedList<>();
		marketSegmentIds.add(marketSegment.getId());

		guestType = new BuilderGuestType(name, code).build();
		guestType = new ClientGuestTypeCreate().createGuestType(masterObject.getProperty(),
				new BuilderGuestType(name, code).build());
		guestType.setMarketSegmentIds(marketSegmentIds);
		guestType = new ClientGuestTypeUpdate().updateGuestType(masterObject.getProperty(), guestType);

		trackingInfo.setSourceCode("3rd Party");
		trackingInfo.setGuestType(guestType.getName());
		trackingInfo.setSegmentCode(marketSegment.getName());

		List<RateCategoryDetail> rateCategoryDetails = new ClientRateCategoryGet()
				.getRatePlanCategories(masterObject.getProperty());
		List<RateCalendarDetail> rateCalendarSummaries = new ClientRateCalendarGet()
				.getRateCalendars(masterObject.getProperty());

		packageName = "PKG_" + RandomHelper.getRandomAlphaNumericString(6);
		RatePlanDetail packageRatePlan = new BuilderRatePlanDetail(packageName,
				"PKGC" + RandomHelper.getRandomAlphaNumericString(4), propertyDate, 2, 0,
				masterObject.getCancelationPolicy().getId(), rateCalendarSummaries.get(0).getId(),
				rateCategoryDetails.get(0).getId(),
				new ClientAccountingItemGet().getNightlyRoomChargeItem(masterObject.getProperty()).getId()).build();
		packageRatePlan.setRateSelectorType(RateSelectorType.DAY_OF_WEEK);

		// Create Components
		List<Component> components = new ArrayList<>();
		List<TransactionItem> transactionItems = new ClientAccountingItemGet()
				.getTransactionItems(masterObject.getProperty());
		InventoryItem inventoryItem = new CreateInventoryItem().create(masterObject.getCategories().get(0),
				masterObject.getSubCategories().get(0), RandomHelper.getRandomInt(1, 99), masterObject.getProperty());

		components.add(new BuilderComponent(transactionItems.get(0), new BigDecimal(RandomHelper.getRandomInt(1, 10)),
				1, FrequencyType.FIRST).build());
		components.add(new BuilderComponent(inventoryItem, new BigDecimal(RandomHelper.getRandomInt(1, 10)), 1,
				FrequencyType.FIRST).build());
		ComponentBundle componentBundle = new ComponentBundle();
		componentBundle.setComponents(components);
		componentBundle = new ClientComponentBundleCreate().createComponentBundle(masterObject.getProperty(),
				componentBundle);
		Set<String> bundles = new HashSet<>();
		bundles.add(componentBundle.getId());
		packageRatePlan.setComponentBundleIds(bundles);
		packageRatePlan.setCommissionable(true);

		packageRatePlan = new ClientRatePlanCreate().createRatePlan(masterObject.getProperty(), packageRatePlan);

		// Create Strategies
		List<RoomTypeRate> roomTypeRates01 = new ArrayList<>();
		for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
			roomTypeRates01.add(new BuilderRoomTypeRate(new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP),
					RateType.ADJUST_UNIT, dayOfWeek).build());
		}

		List<RoomTypeStrategyDetail> roomTypeStrategyDetails = new ArrayList<>();
		roomTypeStrategyDetails.add(new BuilderRoomTypeStrategyDetail(true)
				.setRoomTypeId(masterObject.getRoomTypes().get(0).getId()).setRoomTypeRates(roomTypeRates01)
				.build());

		// set up rate strategy - rate plan strategy detail
		BigDecimal basePrice = new BigDecimal(RandomHelper.getRandomDouble(100, 500)).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal extraAdults = new BigDecimal(RandomHelper.getRandomDouble(10, 20)).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal extraChildren = new BigDecimal(RandomHelper.getRandomDouble(10, 20)).setScale(2,
				BigDecimal.ROUND_HALF_UP);

		RateStrategyDetail rateStrategyDetail = new ClientRateStrategyGet()
				.getRateStrategies(masterObject.getProperty()).stream().filter(x -> x.getName().equals("Base"))
				.findAny().get();
		RatePlanStrategyDetail ratePlanStrategyDetail = new BuilderRatePlanStrategyDetail(rateStrategyDetail, basePrice,
				extraAdults, extraChildren).setRoomTypeStrategies(roomTypeStrategyDetails).build();
		packageRatePlan.setRatePlanStrategies(Arrays.asList(ratePlanStrategyDetail));
		packageRatePlan = new ClientRatePlanUpdate().updateRatePlan(masterObject.getProperty(), packageRatePlan);

		// Enable Open API and comp settings

		new LoginApplication().Login(masterObject);

		new CreateCompanyProfile(directBillAccountName, RandomHelper.getRandomAlphaNumericString(6))
				.flowCreateWithContactsARUsingSaveExit();
		PFSearchTile.waitForSearchPageToLoad();
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {
		new CreateRoom().create(masterObject.getRoomTypes().get(0), masterObject.getBuilding(), masterObject.getProperty());
		open(EndPoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);
		PFSearchTile.waitForSearchPageToLoad();
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		reservationCreateRequest.setWalkIn(true);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
	}

	@Test
	@TestCase(ids ={"RST-3951","RST-135"})
	public void testUpdateReservationAddRemoveVIP() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();

		POReservationSummary.addVIPStatus(masterObject.getVipStatuses().get(0).getName());
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyBadgeHighlighted(LocatorsSummaryModal.BADGE_VIP);
		POReservationSummary.removeVIPStatus();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyBadgeNotHighlighted(LocatorsSummaryModal.BADGE_VIP);
	}

	@Test
	@TestCase(id="RST-3948")
	public void testUpdateReservationAddRemoveLateCheckOut() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();

		POReservationSummary.addLateCheckout("03", "30");
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyBadgeHighlighted(LocatorsSummaryModal.BADGE_LATE_CHECKOUT);
		POReservationSummary.removeLateCheckout();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyBadgeNotHighlighted(LocatorsSummaryModal.BADGE_LATE_CHECKOUT);
	}

	@Test
	public void testUpdateReservationAddRemoveNRG() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();


		POReservationSummary.toggleNRG();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyBadgeHighlighted(LocatorsSummaryModal.BADGE_NRG);
		POReservationSummary.toggleNRG();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyBadgeNotHighlighted(LocatorsSummaryModal.BADGE_NRG);
	}

	@Test
	@TestCase(ids = {"RST-13030"})
	public void testUpdateReservationAddRemovePet() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();

		POReservationSummary.addPet("Service animal", RandomHelper.getRandomAlphaNumericString(10));
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyBadgeHighlighted(LocatorsSummaryModal.BADGE_PET);
		sleep(500);
		POReservationSummary.removePet();
		sleep(500);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyBadgeNotHighlighted(LocatorsSummaryModal.BADGE_PET);
	}

	@Test
	@TestCase(id="RST-10103")
	public void testUpdateReservationWithAliasName(){
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();

		sleep(500);
		String reservationAlias = RandomHelper.getRandomAlphaString(10);
		POReservationSummary.updateReservationAlias(reservationAlias);
		POReservationSummary.saveReservation();
		POSearchInMainPage.searchForReservation(reservationAlias);
		PFReservationTile.clickReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		ValidationBase.verifyElementValue(reservationAlias, LocatorsSummaryModal.INPUT_RESERVATION_ALIAS);
	}

	@Test
	@TestCase(id="RST-4013")
	public void testUpdateReservationWithTrackingInfoThirdPartyConfirmation(){
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();

		PFReservationSummary.expandReservationSummary();
		TrackingInfo trackingInfo = new TrackingInfo();
		trackingInfo.setSourceCode("3rd Party");
		trackingInfo.setGuestType(guestType.getName());
		trackingInfo.setSegmentCode(marketSegment.getName());
		ThreadHelper.sleep(500);

		POReservationSummary.updateTrackingInfo(trackingInfo);

		Set<ThirdPartyConfirmation> thirdPartyConfirmations = new HashSet<>();
		ThirdPartyConfirmation thirdPartyConfirmation = new ThirdPartyConfirmation();
		thirdPartyConfirmation.setConfirmationName("Bookings.com");
		thirdPartyConfirmation.setConfirmationNumber(RandomHelper.getRandomAlphaNumericString(10));
		thirdPartyConfirmations.add(thirdPartyConfirmation);
		POReservationSummary.addThirdPartyConfirmation(thirdPartyConfirmations);

		POReservationSummary.saveReservation();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyTrackingInfo(trackingInfo);
		VerifyReservation.verifyThirdPartyConfirmations(thirdPartyConfirmations);
	}

	@Test
	public void testUpdateReservationBookedBy(){
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();

		PFReservationSummary.expandReservationSummary();
		String bookedBy = RandomHelper.getRandomAlphaNumericString(10);
		PFReservationSummary.typeBookedBy(bookedBy);
		POReservationSummary.saveReservation();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationSummary.expandReservationSummary();
		VerifyReservation.waitForElementValueToLoad(bookedBy, LocatorsSummaryModal.INPUT_BOOKED_BY);
		VerifyReservation.verifyElementValue(bookedBy, LocatorsSummaryModal.INPUT_BOOKED_BY);
	}

	@Test
	public void testUpdateReservationWithTravelAgent(){
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();

		PFReservationSummary.expandReservationSummary();
		List<String> travelAgents = new ArrayList<String>();
		travelAgents.add(masterObject.getTravelAgents().get(0).getAgentSummary().getCompanyName());
		POReservationSummary.updateTravelAgent(travelAgents);
		POReservationSummary.saveReservation();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyTravelAgents(travelAgents);
	}
}
