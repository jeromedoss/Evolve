package com.agilysys.qa.gridIron.service.reservation;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.agilysys.pms.rates.model.*;
import com.agilysys.qa.constants.EndPoints;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.agilysys.common.model.BooleanSetting;
import com.agilysys.common.model.FrequencyType;
import com.agilysys.common.model.Setting;
import com.agilysys.common.model.rate.CompInfo;
import com.agilysys.qa.gridIron.pageobject.shared.widgets.POCalendars;
import com.agilysys.insertanator.creates.item.CreateInventoryItem;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.account.model.InventoryItem;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.common.model.integration.OtaInterface;
import com.agilysys.pms.common.model.integration.OtaNativeSetting;
import com.agilysys.pms.profile.model.GuestType;
import com.agilysys.pms.profile.model.MarketSegment;
import com.agilysys.pms.property.client.PropertyManagementClient;
import com.agilysys.pms.property.model.Property;
import com.agilysys.pms.property.model.RoomType;
import com.agilysys.pms.reservation.model.FullDayRateSnapshot;
import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.common.model.TrackingInfo;
import com.agilysys.qa.client.account.comp.info.ClientCompInfoAdd;
import com.agilysys.qa.client.account.item.ClientAccountingItemGet;
import com.agilysys.qa.client.area.room.type.ClientRoomTypeGet;
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
import com.agilysys.qa.data.builder.rate.BuilderComponent;
import com.agilysys.qa.data.builder.rate.BuilderRatePlanDetail;
import com.agilysys.qa.data.builder.rate.BuilderRatePlanStrategyDetail;
import com.agilysys.qa.data.builder.rate.BuilderRoomTypeRate;
import com.agilysys.qa.data.builder.rate.BuilderRoomTypeStrategyDetail;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.constants.locators.reservation.estimatedcharges.LocatorsEstimatedChargesSection;
import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsCurrentlySelectedRoom;
import com.agilysys.qa.gridIron.constants.locators.reservation.summary.LocatorsSummaryModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationEstimatedCharges;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationSummary;
import com.agilysys.qa.gridIron.data.models.RecurringCharge;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.reservation.POModifyStayPanel;
import com.agilysys.qa.gridIron.pageobject.reservation.PORooms;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import com.codeborne.selenide.SelenideElement;


import com.agilysys.qa.gridIron.utils.RandomHelper;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestReservationModifyStayRegularRatePlan extends StayUIWrappers {

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
	List<RatePlanSummary> ratePlanSummaries = null;
	List<RoomType> roomTypes = null;
	CompInfo compReasonCertificationRequired = null;
	RatePlanDetail ratePlanDetail = null;
	BigDecimal regularRatePlanBasePrice = null;
	BigDecimal packageRatePlanBasePrice = null;
	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createBuilding()
			.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
				propertycode)
			.createPaymentMethodAllCC().createRoomTypes(2).createRooms(5).createCategories(1).createSubCategories(1)
			.createItems(3).createReservation(1).createTravelAgents(1).create();

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());

		// Enable Comp Certification
		PropertyManagementClient propertyManagementClient = new PropertyManagementClient(EndPoints.getPropertyServiceURI());
		OtaInterface openapiInterface = new OtaInterface("openapi", true);
		openapiInterface.setTenantId(tenantId);
		openapiInterface.setPropertyId(propertyId);


		openapiInterface.getSettings().put(OtaNativeSetting.COMP_CERTIFICATES_PARTNER_URL.toString(),
			"http://stay-wiremock-dev.rguest.com:8090/comp");
		openapiInterface.getSettings().put(OtaNativeSetting.COMP_CERTIFICATES_USERNAME.toString(), "dummy");
		openapiInterface.getSettings().put(OtaNativeSetting.COMP_CERTIFICATES_PASSWORD.toString(), "dummy");
		openapiInterface.getSettings().put(OtaNativeSetting.COMP_CERTIFICATES_ENABLED.toString(), "true");
		openapiInterface = propertyManagementClient.getProxy().createOtaInterface(tenantId, propertyId,
			openapiInterface);

		compReasonCertificationRequired = new CompInfo();
		compReasonCertificationRequired.setCode("100SG");
		compReasonCertificationRequired.setReason("Guest Satification");
		compReasonCertificationRequired.setActive(true);
		compReasonCertificationRequired.setCertificateRequired(true);
		compReasonCertificationRequired.setDiscountType("ON_THE_HOUSE");

		try{
			compReasonCertificationRequired = new ClientCompInfoAdd().addCompReason(masterObject.getProperty(),
				compReasonCertificationRequired);
		}catch(Exception e){

		}
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
			"PKGC" + RandomHelper.getRandomAlphaNumericString(4), propertyDate, 2, 1,
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
			roomTypeRates01.add(new BuilderRoomTypeRate(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
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

		List<RatePlanDetail> ratePlans = new ClientRatePlanGet().getDetailedRatePlans(masterObject.getProperty());
		roomTypes = new ClientRoomTypeGet().findRoomTypes(masterObject.getProperty());
		ratePlanDetail = ratePlans.stream().filter(x -> x.getCode().equalsIgnoreCase(masterObject.getRatePlans().get(0).getCode())).findAny().get();
		regularRatePlanBasePrice = ratePlanDetail.getRatePlanStrategies().stream().filter(x -> x.isEnabled()).findFirst().get().getBasePrice();
		packageRatePlanBasePrice = packageRatePlan.getRatePlanStrategies().stream().filter(x -> x.isEnabled()).findFirst().get().getBasePrice();
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
		reservationCreateRequest.setWalkIn(true);
	}

	@Test
	public void testModifyStayRegularRatePlanByUpdatingCompanyDetails() {
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), propertyDate)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		PFReservationSummary.typeCompany(directBillAccountName);
		sleep(500);
		PFReservationSummary.clickSaveReservation();
		POModifyStayPanel.modifyStay(null, true, propertyDate, propertyDate.plusDays(2));
		POModifyStayPanel.waitForModifyToStayToComplete();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());

		VerifyReservation.verifyElementValue(directBillAccountName, LocatorsSummaryModal.TYPEHEAD_COMPANY);

	}

	@Test
	@TestCase(id="RST-4067")
	public void testModifyStayRegularRatePlanByChangingRoomType() {
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
				masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		PORooms.changeRoomType(masterObject.getRoomTypes().get(1).getTypeCode(), masterObject.getRoomTypes().get(1).getName());
		POModifyStayPanel.modifyStay(null, true, propertyDate);
		POModifyStayPanel.confirmModifyStay();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyElementText(masterObject.getRoomTypes().get(1).getTypeCode(), LocatorsCurrentlySelectedRoom.TEXT_CURRENTLY_SELECTED_ROOMTYPE_CODE);
		VerifyReservation.verifyElementText(masterObject.getRoomTypes().get(1).getName(), LocatorsCurrentlySelectedRoom.TEXT_CURRENTLY_SELECTED_ROOMTYPE_NAME);
		PORooms.releaseUnconfirmedRoom();
	}

	@Test
	@TestCase(ids = {"RST-2022","RST-2021","RST-1172"})
	public void testModifyStayRegularRatePlanWithRecurringChargesByExtendingDates() {

		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(masterObject.getItems().get(0).getName(),price,quantity);
		recurringCharge.setEveryNFrequency("1");
		recurringCharges.add(recurringCharge);

		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, ratePlanDetail, masterObject.getRoomTypes().get(0), propertyDate)
			.addRecurringCharge(recurringCharges, null, null)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		extendDepartureDate(propertyDate.plusDays(3));
		PFReservationSummary.clickSaveReservation();

		RateSnapshot rateSnapshot = new FullDayRateSnapshot();
		rateSnapshot.setRoomTypeName(masterObject.getRoomTypes().get(0).getName());
		rateSnapshot.setRoomTypeCode(masterObject.getRoomTypes().get(0).getTypeCode());
		rateSnapshot.setRatePlanName(masterObject.getRatePlans().get(0).getName());
		rateSnapshot.setRatePlanCode(masterObject.getRatePlans().get(0).getCode());
		rateSnapshot.setDate(propertyDate.plusDays(2));
		Set<RateSnapshot> rateSnapshots = new HashSet<>();
		rateSnapshots.add(rateSnapshot);
		builderBookReservation.getReservationCreateRequest().getRateSnapshots().add(rateSnapshot);
		POModifyStayPanel.modifyStay(rateSnapshots, true, propertyDate);
		POModifyStayPanel.confirmModifyStay();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());

		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), regularRatePlanBasePrice);
		VerifyReservation.verifyEstimatedChargesLineItemQuantity(propertyDate.plusDays(2), masterObject.getItems().get(0).getName(), quantity);
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate.plusDays(2), masterObject.getItems().get(0).getName(), price.multiply(new BigDecimal(quantity)));
	}

	@Test
	public void testModifyStayRegularRatePlanByShorteningDates() {
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0), masterObject.getRoomTypes().get(0), propertyDate)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		PFReservationSummary.setArrivalDate(propertyDate.plusDays(1));
		extendDepartureDate(propertyDate.plusDays(2));
		PFReservationSummary.clickSaveReservation();
		POModifyStayPanel.modifyStay(null, true, null);
		POModifyStayPanel.confirmModifyStay();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName(), propertyDate.plusDays(1));
		PFReservationEstimatedCharges.expandEstimatedCharges();
		VerifyReservation.verifyElementNotPresent(LocatorsEstimatedChargesSection.getLineItemChargeAmount(propertyDate, masterObject.getRoomTypes().get(0).getTypeCode()));
	}

	@Test
	public void testModifyStayRegularRatePlanByChangeToAnotherDates() {
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0), masterObject.getRoomTypes().get(0), propertyDate)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		PFReservationSummary.setArrivalDate(propertyDate.plusDays(5));
		PFReservationSummary.setDepartureDate(propertyDate.plusDays(7));
	//	extendDepartureDate(propertyDate.plusDays(7));

		builderBookReservation.getReservationCreateRequest().getRateSnapshots().forEach((rateSnapshot)->{
			rateSnapshot.setDate(rateSnapshot.getDate().plusDays(5));
		});

		PFReservationSummary.clickSaveReservation();
		POModifyStayPanel.modifyStay(builderBookReservation.getReservationCreateRequest().getRateSnapshots(), true, propertyDate.plusDays(5));
		POModifyStayPanel.confirmModifyStay();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName(), propertyDate.plusDays(5));

		builderBookReservation.getReservationCreateRequest().getRateSnapshots().forEach((rateSnapshot)->{
			VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), regularRatePlanBasePrice);
		});
	}


	@Test
	public void  testModifyStayRegularRatePlanByAdjustRate() {
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0), masterObject.getRoomTypes().get(0), propertyDate)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		extendDepartureDate(propertyDate.plusDays(3));
		PFReservationSummary.clickSaveReservation();

		RateSnapshot rateSnapshot = new FullDayRateSnapshot();
		rateSnapshot.setRoomTypeName(masterObject.getRoomTypes().get(0).getName());
		rateSnapshot.setRoomTypeCode(masterObject.getRoomTypes().get(0).getTypeCode());
		rateSnapshot.setRatePlanName(masterObject.getRatePlans().get(0).getName());
		rateSnapshot.setRatePlanCode(masterObject.getRatePlans().get(0).getCode());
		rateSnapshot.setDate(propertyDate.plusDays(2));
		rateSnapshot.setPreOccupancyRate(new BigDecimal(10));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));
		Set<RateSnapshot> rateSnapshots = new HashSet<>();
		rateSnapshots.add(rateSnapshot);
		builderBookReservation.getReservationCreateRequest().getRateSnapshots().add(rateSnapshot);
		POModifyStayPanel.modifyStay(rateSnapshots, false, propertyDate);
		POModifyStayPanel.confirmModifyStay();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}


	@Test
	public void testModifyStayRegularRatePlanWithCompRate() {
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0), masterObject.getRoomTypes().get(0), propertyDate)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		extendDepartureDate(propertyDate.plusDays(3));
		PFReservationSummary.clickSaveReservation();

		RateSnapshot rateSnapshot = new FullDayRateSnapshot();
		rateSnapshot.setRoomTypeName(masterObject.getRoomTypes().get(0).getName());
		rateSnapshot.setRoomTypeCode(masterObject.getRoomTypes().get(0).getTypeCode());
		rateSnapshot.setRatePlanName(masterObject.getRatePlans().get(0).getName());
		rateSnapshot.setRatePlanCode(masterObject.getRatePlans().get(0).getCode());
		rateSnapshot.setDate(propertyDate.plusDays(2));
		rateSnapshot.setPreOccupancyRate(new BigDecimal(0));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason("Free room with promotion");
		rateSnapshot.setCompInfo(compInfo);
		Set<RateSnapshot> rateSnapshots = new HashSet<>();
		rateSnapshots.add(rateSnapshot);
		builderBookReservation.getReservationCreateRequest().getRateSnapshots().add(rateSnapshot);
		POModifyStayPanel.modifyStay(rateSnapshots, true, propertyDate);
		POModifyStayPanel.confirmModifyStay();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}

	@Test
	public void testModifyStayRegularRatePlanWithIssueComp() {
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0), masterObject.getRoomTypes().get(0), propertyDate)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		extendDepartureDate(propertyDate.plusDays(3));
		PFReservationSummary.clickSaveReservation();

		RateSnapshot rateSnapshot = new FullDayRateSnapshot();
		rateSnapshot.setRoomTypeName(masterObject.getRoomTypes().get(0).getName());
		rateSnapshot.setRoomTypeCode(masterObject.getRoomTypes().get(0).getTypeCode());
		rateSnapshot.setRatePlanName(masterObject.getRatePlans().get(0).getName());
		rateSnapshot.setRatePlanCode(masterObject.getRatePlans().get(0).getCode());
		rateSnapshot.setDate(propertyDate.plusDays(2));
		rateSnapshot.setPreOccupancyRate(new BigDecimal(0));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);
		Set<RateSnapshot> rateSnapshots = new HashSet<>();
		rateSnapshots.add(rateSnapshot);
		builderBookReservation.getReservationCreateRequest().getRateSnapshots().add(rateSnapshot);
		POModifyStayPanel.modifyStay(rateSnapshots, true, propertyDate);
		POModifyStayPanel.confirmModifyStay();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}

	@Test
	public void testModifyStayRegularRatePlanWithRedeemComp() {
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0), masterObject.getRoomTypes().get(0), propertyDate)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		extendDepartureDate(propertyDate.plusDays(3));
		PFReservationSummary.clickSaveReservation();

		RateSnapshot rateSnapshot = new FullDayRateSnapshot();
		rateSnapshot.setRoomTypeName(masterObject.getRoomTypes().get(0).getName());
		rateSnapshot.setRoomTypeCode(masterObject.getRoomTypes().get(0).getTypeCode());
		rateSnapshot.setRatePlanName(masterObject.getRatePlans().get(0).getName());
		rateSnapshot.setRatePlanCode(masterObject.getRatePlans().get(0).getCode());
		rateSnapshot.setDate(propertyDate.plusDays(2));
		rateSnapshot.setPreOccupancyRate(new BigDecimal(0));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());
		compInfo.setCompCertificateNumber(RandomHelper.getRandomAlphaNumericString(10));
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);
		Set<RateSnapshot> rateSnapshots = new HashSet<>();
		rateSnapshots.add(rateSnapshot);
		builderBookReservation.getReservationCreateRequest().getRateSnapshots().add(rateSnapshot);
		POModifyStayPanel.modifyStay(rateSnapshots, true, propertyDate);
		POModifyStayPanel.confirmModifyStay();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}

	@Test
	public void testModifyStayRegularRatePlanChangeToPackageRatePlan() {
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0), masterObject.getRoomTypes().get(0), propertyDate)
			.addPaymentMethod("Cash", null, null)
			.build();

		PFReservationEstimatedCharges.loadEstimatedCharges();
		PFReservationEstimatedCharges.expandEstimatedCharges();
		PFReservationEstimatedCharges.clickRatePlan(propertyDate);

		builderBookReservation.getReservationCreateRequest().getRateSnapshots().forEach((rateSnapshot)->{
			rateSnapshot.setRatePlanCode(packageRatePlan.getCode());
			rateSnapshot.setRatePlanName(packageRatePlan.getName());
		});

		POModifyStayPanel.modifyStay(builderBookReservation.getReservationCreateRequest().getRateSnapshots(), false, propertyDate);
		POModifyStayPanel.confirmModifyStay();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		builderBookReservation.getReservationCreateRequest().getRateSnapshots().forEach((rateSnapshot)->{
			VerifyReservation.verifyPackageRoomChargeAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(),
				packageRatePlanBasePrice);
		});
	}

	private void extendDepartureDate(LocalDate date)
	{
//		String s = date.toString().substring(8);
//		new POCalendars(LocatorsSummaryModal.LABEL_DEPARTURE_DATE);
//
//		ElementsCollection $$ = $$(By.xpath(("//button[@class='btn btn-default btn-sm']/span")));
//		for (SelenideElement selenideElement : $$) {
//			if (selenideElement.getText().contains(s) &&
//				!selenideElement.getCssValue("background-color").contains("rgba(204, 204, 204, 1)")) {
//				selenideElement.vicks
//				return;
//			}
//
//		}
//
		PFReservationSummary.setDepartureDate(date);
	}
}