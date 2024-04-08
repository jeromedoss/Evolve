package com.agilysys.qa.gridIron.service.reservation;
import com.agilysys.common.model.TrackingInfo;
import com.agilysys.common.model.BooleanSetting;
import com.agilysys.common.model.FrequencyType;
import com.agilysys.common.model.PolicySchedule;
import com.agilysys.common.model.Setting;
import com.agilysys.insertanator.creates.ratePlan.CreateRatePlan;
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
import com.agilysys.pms.profile.model.v1.GuestProfile;
import com.agilysys.pms.property.model.DepositPolicy;
import com.agilysys.pms.property.model.Property;
import com.agilysys.pms.rates.model.*;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.qa.client.account.item.ClientAccountingItemGet;
import com.agilysys.qa.client.policy.deposit.ClientDepositPolicyGet;
import com.agilysys.qa.client.policy.deposit.ClientDepositPolicyUpdate;
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
import com.agilysys.qa.client.rate.plan.ClientRatePlanGet;
import com.agilysys.qa.client.rate.plan.ClientRatePlanUpdate;
import com.agilysys.qa.client.rate.strategy.ClientRateStrategyGet;
import com.agilysys.qa.data.builder.profile.guest.type.BuilderGuestType;
import com.agilysys.qa.data.builder.profile.market.segment.BuilderMarketSegment;
import com.agilysys.qa.data.builder.rate.*;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
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
import org.testng.Assert;
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

public class TestBookReservationPaymentMethods extends StayUIWrappers {

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
	String swipeData = "2ED01801F4F2800839B%*4761********0011^UAT USA/TEST CARD 01      ^2412***************************?*;4761********0011=2412****************?*B54354BCB250AFBAE14CD33CD0C61EA9F624A3A9D2CB2E6413ED5B96A7E9602F237EC4CCAE89663299CD3D3D8101D4EC27E2D2F026C2C136B25B59AC4B4218D2F3DBF2377036929C58DFB74B164B3273FADAFFBF70254BE69697A00A6177B4DBCE7078FBA25DA70D58F9A7E0A07D937E128382652311B65E0000000000000000000000000000000000000000000000000000000000000000000000000000000038303654343439353534FFFF9876540042E0011C5DB303";
	String packageName = null;
	RatePlanDetail packageRatePlan = null;
	ClientDepositPolicyUpdate clientDepositPolicyUpdate = null;
	DepositPolicy depositPolicy = null;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createBuilding()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createPaymentMethodAllCC().createRoomTypes(1).createRooms(5).createCategories(1).createSubCategories(1)
				.createItems(3).createReservation(1).createTravelAgents(1).createDepositPolicy().create();

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

		ClientRatePlanUpdate clientRatePlanUpdate = new ClientRatePlanUpdate();
		List<RatePlanDetail> ratePlanDetails = new ClientRatePlanGet().getDetailedRatePlans(masterObject.getProperty());
		RatePlanDetail ratePlanDetail = ratePlanDetails.stream().filter(x -> x.getCode().equalsIgnoreCase(masterObject.getRatePlans().get(0).getCode())).findAny().get();
		ratePlanDetail.setDepositPolicyId(masterObject.getDepositPolicy().getId());
		clientRatePlanUpdate.updateRatePlan(masterObject.getProperty(), ratePlanDetail);

		clientDepositPolicyUpdate = new ClientDepositPolicyUpdate();
		depositPolicy = new ClientDepositPolicyGet().getDepositPolicyById(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId(), ratePlanDetail.getDepositPolicyId());

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

	@Test
	@TestCase(ids = {"RST-4271","RST-3864","RST-3872","RST-3875"})
	public void testCreateReservationWithExistingGuestWithCash() {

		GuestProfile guestProfile = POGuestProfilePage.createGuestProfile();
		POGuestProfilePage.addPaymentMethod("Cash", true, null, null);
		builderBookReservation
				.setRandomRateSnapshots(1, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setGuestSearchString(guestProfile.getPersonalDetails().getLastName()).setDepositDetails("Cash", depositPolicy.getFixedFee().toString())
				.build();

		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(guestProfile.getPersonalDetails().getLastName());
		VerifyReservation.verifyPaymentMethod("Cash", true);
		VerifyReservation.verifyDeposit("Cash", depositPolicy.getFixedFee().setScale(2).toString());
	}

	@Test
	@TestCase(id = "RST-4276")
	public void testCreateReservationWithExistingGuestWithVisa() {
		GuestProfile guestProfile = POGuestProfilePage.createGuestProfile();
		POGuestProfilePage.addPaymentMethod("New Credit Card", true, null, swipeData);

		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setGuestSearchString(guestProfile.getPersonalDetails().getLastName())
				.setDepositDetails("Visa 0011", depositPolicy.getFixedFee().toString()).build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(guestProfile.getPersonalDetails().getLastName());
		VerifyReservation.verifyPaymentMethod("Visa 0011", true);
		VerifyReservation.verifyDeposit("Visa 0011", depositPolicy.getFixedFee().setScale(2).toString());
	}

	@Test
	@TestCase(id = "RST-4273")
	public void testCreateReservationWithExistingGuestWithDirectBill() {
		System.out.println("Direct bill name : " + directBillAccountName);
		GuestProfile guestProfile = POGuestProfilePage.createGuestProfile();
		POGuestProfilePage.addPaymentMethod("Direct Bill", false, directBillAccountName, null);
		PFHeaderDropDowns.navigateToBookReservation();

		RatePlanSummary ratePlanSummary = new CreateRatePlan().create(masterObject.getCancelationPolicy(), masterObject.getProperty());
		builderBookReservation
				.setRandomRateSnapshots(2, ratePlanSummary.getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setGuestSearchString(guestProfile.getPersonalDetails().getLastName()).
				addPaymentMethod("Direct Bill", null, directBillAccountName)
				.setDepositDetails(null, "0").build();

		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(guestProfile.getPersonalDetails().getLastName());
		VerifyReservation.verifyPaymentMethod(directBillAccountName, false);
		VerifyReservation.verifyPaymentMethod("Cash", true);
	}

	@Test
	@TestCase(ids = {"RST-4271","RST-3878"})
	public void testCreateReservationWithNewGuestWithCash() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null).setDepositDetails("Cash", "10").build();

		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPaymentMethod("Cash", true);
	}

	@Test
	@TestCase(ids = {"RST-4300","RST-4321"})
	public void testCreateReservationWithNewGuestWithVisaOverrideDeposit() {
		RatePlanSummary ratePlanSummary = new CreateRatePlan().create(masterObject.getCancelationPolicy(), masterObject.getProperty());
		builderBookReservation
				.setRandomRateSnapshots(2,ratePlanSummary.getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber()
				.addPaymentMethod("New Credit Card", swipeData, null)
				.setDepositDetails("Visa 0011", "8")
				.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPaymentMethod("Visa 0011", true);
		VerifyReservation.verifyDeposit("Visa 0011", new BigDecimal(8));
	}

	@Test
	@TestCase(ids = {"RST-4273","RST-4361"})
	public void testCreateReservationWithNewGuestWithDirectBill() {
		RatePlanSummary ratePlanSummary = new CreateRatePlan().create(masterObject.getCancelationPolicy(), masterObject.getProperty());
		builderBookReservation
				.setRandomRateSnapshots(2, ratePlanSummary.getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber()
				.addPaymentMethod("Direct Bill", null, directBillAccountName)
				.setDepositDetails(null, "0").build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPaymentMethod(directBillAccountName, false);
		VerifyReservation.verifyPaymentMethod("Cash", true);
	}

	@Test
	@TestCase(id = "RST-4271")
	public void testCreateReservationWithCash40PercentageDeposit() {
		depositPolicy.setPercentOfEntireStay(new BigDecimal(40));
		depositPolicy.setDays(null);
		depositPolicy.setChargeType(PolicySchedule.ChargeType.ENTIRE_STAY);
		depositPolicy.setFixedFee(null);
		depositPolicy.setTaxIncluded(false);
		depositPolicy = clientDepositPolicyUpdate.updateDepositPolicy(masterObject.getProperty(), depositPolicy);

		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
				.setDepositPaymentMethod("Cash");
		builderBookReservation.getGuestProfile().getPhoneDetails().getPhones().get(0).setPhoneType("Business");

		BigDecimal preOccupancyRate = new BigDecimal(40.00);
		builderBookReservation.getReservationCreateRequest().getRateSnapshots().forEach(x->{
			x.setPreOccupancyRate(preOccupancyRate);
			x.setOverriddenComment(RandomHelper.getRandomAlphaString(10));
		});

		builderBookReservation.build();

		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation
				.verifyGuestPhone(builderBookReservation.getGuestProfile().getPhoneDetails().getPhones().get(0));
		VerifyReservation.verifyDeposit("Cash", new BigDecimal(32));
	}

	@Test
	@TestCase(id = "RST-4276")
	public void testCreateReservationWithCreditCardTwoNightDeposit() {
		depositPolicy.setPercentOfEntireStay(null);
		depositPolicy.setDays(2);
		depositPolicy.setChargeType(PolicySchedule.ChargeType.NUMBER_OF_NIGHTS);
		depositPolicy.setFixedFee(null);
		depositPolicy.setTaxIncluded(false);
		depositPolicy = clientDepositPolicyUpdate.updateDepositPolicy(masterObject.getProperty(), depositPolicy);

		builderBookReservation
				.setRandomRateSnapshots(3, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("New Credit Card", swipeData, null)
				.setDepositPaymentMethod("Visa 0011");

		BigDecimal preOccupancyRate = new BigDecimal(50.00);
		builderBookReservation.getReservationCreateRequest().getRateSnapshots().forEach(x->{
			x.setPreOccupancyRate(preOccupancyRate);
			x.setOverriddenComment(RandomHelper.getRandomAlphaString(10));
		});

		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyDeposit("Visa 0011", new BigDecimal(depositPolicy.getDays()).multiply(preOccupancyRate));
	}

	@TestCase (ids = {"RST-4315","RST-1024"})
	@Test
	public void testCreateReservationWithDirectBillAndCreditCardFixedFeeDeposit() {

		depositPolicy.setPercentOfEntireStay(null);
		depositPolicy.setDays(null);
		depositPolicy.setChargeType(PolicySchedule.ChargeType.FIXED_FEE);
		depositPolicy.setFixedFee(new BigDecimal(5));
		depositPolicy.setTaxIncluded(false);
		depositPolicy = clientDepositPolicyUpdate.updateDepositPolicy(masterObject.getProperty(), depositPolicy);

		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber()
				.addPaymentMethod("Direct Bill", null, directBillAccountName)
				.addPaymentMethod("New Credit Card", swipeData, null)
				.setDepositPaymentMethod("Visa 0011")
				.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyDeposit("Visa 0011", depositPolicy.getFixedFee());
	}
}