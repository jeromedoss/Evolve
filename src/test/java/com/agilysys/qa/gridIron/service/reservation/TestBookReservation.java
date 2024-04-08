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
import com.agilysys.pms.profile.model.Address;
import com.agilysys.pms.profile.model.Email;
import com.agilysys.pms.profile.model.GuestType;
import com.agilysys.pms.profile.model.MarketSegment;
import com.agilysys.pms.profile.model.v1.GuestProfile;
import com.agilysys.pms.property.model.Property;
import com.agilysys.pms.rates.model.*;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.pms.reservation.model.ThirdPartyConfirmation;
import com.agilysys.common.model.TrackingInfo;
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
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.profiles.guest.POGuestProfilePage;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.PMSHelper;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.agilysys.qa.constants.EndPoints;

import java.math.BigDecimal;
import java.util.*;

import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestBookReservation extends StayUIWrappers {

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
	String swipeData = "2C100C037001C078692;4761********0010=2212:***?*098745=ACF828DC27C1BB4E1DAA43F06A25BDD1B661394C0BFA22248608AE55765ECA2D000000000000000000000000000000000000000037343254303930303934FFFF987654004300025A453D03";
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
				.createItems(3).createReservation(1).createTravelAgents(1).create();

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
		packageRatePlan = new BuilderRatePlanDetail(packageName,
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
		new PMSHelper().enableIDTech(masterObject.getProperty());
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

		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
	}

	
	@Test(priority = 40)
	@TestCase(id = "RST-10084")
	public void testCreateReservationWithReservationAlias() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);
		String reservationAlias = RandomHelper.getRandomAlphaNumericString(10);
		builderBookReservation.getReservationCreateRequest().setReservationAlias(reservationAlias);
		builderBookReservation.build();

		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.searchForReservation(reservationAlias);
		PFReservationTile.clickReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
	}
	
	@TestCase(ids = {"RST-3902", "RST-2302"})
	@Test
	public void testCreateReservationWithTrackingInfoAndSearchWithLastName() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
				.setTrackingInfo(trackingInfo, false).build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyTrackingInfo(trackingInfo);
	}

	@TestCase(ids = {"RST-4568", "RST-2300","RST-3940"})
	@Test
	public void testCreateReservationWithTrackingInfoSaveToProfileAndSearchWithFirstName() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
				.setTrackingInfo(trackingInfo, true).build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getFirstName());
		VerifyReservation.verifyTrackingInfo(trackingInfo);
	}

	@TestCase(ids = {"RST-4626","RST-2306"})
	@Test(priority = -10)
	public void testCreateReservationWithThirdPartyDetailsAndSearchWithConfirmationNumber() {
		ThirdPartyConfirmation thirdPartyConfirmation = new ThirdPartyConfirmation();
		thirdPartyConfirmation.setConfirmationName("Bookings.com");
		thirdPartyConfirmation.setConfirmationNumber(RandomHelper.getRandomAlphaString(5));

		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
				.addThirdPartyConfirmations(thirdPartyConfirmation).build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		String confirmationNumber = PFBookReservationModal.getReservationConfirmationNumber();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(confirmationNumber, builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyThirdPartyConfirmations(
				builderBookReservation.getReservationCreateRequest().getThirdPartyConfirmation());
	}

	@TestCase(ids = {"RST-3903","RST-2307"})
	@Test
	public void testCreateReservationWithTravelAgentAndSearchWithPartialConfirmationNumber() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
				.addTravelAgent(masterObject.getTravelAgents().get(0).getAgentSummary().getCompanyName()).build();

		POBookAReservationModal.verifyBookConfirmationMessage();
		String confirmationNumber = PFBookReservationModal.getReservationConfirmationNumber();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(confirmationNumber.substring(0,3), builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyTravelAgents(Arrays.asList(masterObject.getTravelAgents().get(0).getAgentSummary().getCompanyName()));
	}

	@TestCase(id="RST-2303")
	@Test(priority = 30)
	public void testCreateReservationWithExistingProfileTrackingInfoAndSearchWithFullName() {
		GuestProfile guestProfile = POGuestProfilePage.createGuestProfile();
		POGuestProfilePage.addPaymentMethod("Cash", true, null, null);
		POGuestProfilePage.addTrackingInfo(trackingInfo.getSourceCode(), trackingInfo.getGuestType(),
				trackingInfo.getSegmentCode());
		POGuestProfilePage.saveProfilePage();

		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setGuestSearchString(guestProfile.getPersonalDetails().getLastName()).build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(guestProfile.getPersonalDetails().getLastName()+", "+guestProfile.getPersonalDetails().getFirstName());
		VerifyReservation.verifyTrackingInfo(trackingInfo);
	}

	@Test(priority = 20)
	@TestCase(id = "RST-3876")
	public void testCreateReservationWithEmail() {
		Email email = new Email();
		email.setEmailAddress(RandomHelper.getRandomAlphaNumericString(10));
		email.setType("Home");

		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().addEmailDetails(email).addPaymentMethod("Cash", null, null).build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyGuestEmail(email);
	}

	@Test(priority = 10)
	@TestCase(ids = {"RST-3877","RST-1876"})
	public void testCreateReservationWithAddress() {
		Address address = new Address();
		address.setAddressType("Office");
		address.setAddressLine1(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine2(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine3(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine4(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine5(RandomHelper.getRandomAlphaNumericString(20));
		address.setCityName(RandomHelper.getRandomAlphaNumericString(20));
		address.setStateProvinceCode(RandomHelper.getRandomAlphaNumericString(20));
		address.setPostalCode("adfafd");
		address.setCountyCode("Albania");
		address.setCountryCode("Albania");

		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().addAddressDetails(address).addPaymentMethod("Cash", null, null).build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyGuestAddress(address);
	}

	@TestCase(id = "RST-3914")
	@Test
	public void testCreateWalkInReservation() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		builderBookReservation.getReservationCreateRequest().setWalkIn(true);
		builderBookReservation.build();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
	}
}