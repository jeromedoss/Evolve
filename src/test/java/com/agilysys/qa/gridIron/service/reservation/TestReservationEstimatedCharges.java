package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.common.model.BooleanSetting;
import com.agilysys.common.model.FrequencyType;
import com.agilysys.common.model.Setting;
import com.agilysys.common.model.rate.CompInfo;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.account.client.InventoryItemServiceClient;
import com.agilysys.pms.account.model.InventoryItem;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.common.model.integration.OtaInterface;
import com.agilysys.pms.common.model.integration.OtaNativeSetting;
import com.agilysys.pms.profile.model.GuestType;
import com.agilysys.pms.profile.model.MarketSegment;
import com.agilysys.pms.property.client.PropertyManagementClient;
import com.agilysys.pms.property.model.Property;
import com.agilysys.pms.property.model.RoomType;
import com.agilysys.pms.rates.model.*;
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
import com.agilysys.qa.data.builder.rate.*;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.shared.folios.BuilderAddRecurringCharge;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationEstimatedCharges;
import com.agilysys.qa.gridIron.data.models.RecurringCharge;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.reservation.POEstimatedCharges;
import com.agilysys.qa.gridIron.utils.MainDriver;
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

public class TestReservationEstimatedCharges extends StayUIWrappers {

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
	
	InventoryItem inventoryItem = null;
	
	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createBuilding()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createPaymentMethodAllCC()
				.createRoomTypes(2)
				.createRooms(5)
				.createCategories(1)
				.createSubCategories(1)
				.createItems(3)
				.createReservation(1)
				.createTravelAgents(1)
				.create();

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		
		inventoryItem = new InventoryItem();
		inventoryItem.setName(RandomHelper.getRandomAlphaString(10));
		inventoryItem.setCode(RandomHelper.getRandomAlphaString(5));
		inventoryItem.setDefaultPrice(new BigDecimal(10));
		inventoryItem.setAvailableCount(50);
		inventoryItem.setCategoryId(masterObject.getCategories().get(0).getId());
		inventoryItem.setSubcategoryId(masterObject.getSubCategories().get(0).getId());
		Map<String, List<String>> sourceMealPeriods = new HashMap<>();
		sourceMealPeriods.put(masterObject.getBuilding().getId(), new ArrayList<String>());		
		inventoryItem.setSourceMealPeriods(sourceMealPeriods);		
		InventoryItemServiceClient inventoryItemClient = new InventoryItemServiceClient(EndPoints.getAccountServiceURI());
		inventoryItem = inventoryItemClient.getProxy().createInventoryItem(tenantId, propertyId, inventoryItem);
		
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
		
		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());

		new LoginApplication().Login(masterObject);

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
	@TestCase(ids= {"RST-2207","RST-1572"})
	public void testEstimatedChargesRegularRatePlanAdjustRate() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0),
						masterObject.getRoomTypes().get(0), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		RateSnapshot rateSnapshot = builderBookReservation.getReservationCreateRequest().getRateSnapshots().iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal(10));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));
		POEstimatedCharges.adjustNightlyRoomCharge(rateSnapshot, false);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}	
	
	@Test
	public void testEstimatedChargesRegularRatePlanAdjustCompRate() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0),
						masterObject.getRoomTypes().get(0), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		RateSnapshot rateSnapshot = builderBookReservation.getReservationCreateRequest().getRateSnapshots().iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal(0));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason("Free room with promotion");
		rateSnapshot.setCompInfo(compInfo);
		POEstimatedCharges.adjustNightlyRoomCharge(rateSnapshot, false);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}
	
	@Test
	public void testEstimatedChargesRegularRatePlanAdjustRateIssueNewComp() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0),
						masterObject.getRoomTypes().get(0), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		RateSnapshot rateSnapshot = builderBookReservation.getReservationCreateRequest().getRateSnapshots().iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal(0));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));		
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());
		compInfo.setCompCertificateNumber(RandomHelper.getRandomAlphaNumericString(10));
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);
		POEstimatedCharges.adjustNightlyRoomCharge(rateSnapshot, false);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}
	
	
	@Test
	public void testEstimatedChargesRegularRatePlanAdjustRateRedeemComp() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0),
						masterObject.getRoomTypes().get(0), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		RateSnapshot rateSnapshot = builderBookReservation.getReservationCreateRequest().getRateSnapshots().iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal(0));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));		
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());		
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);
		POEstimatedCharges.adjustNightlyRoomCharge(rateSnapshot, false);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}
	
	@Test
	public void testEstimatedChargesPackageRatePlanAdjustRate() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, packageRatePlan,
						masterObject.getRoomTypes().get(0), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		RateSnapshot rateSnapshot = builderBookReservation.getReservationCreateRequest().getRateSnapshots().iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal(10));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));
		POEstimatedCharges.adjustNightlyRoomCharge(rateSnapshot, true);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPackageRoomChargeAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}	
	
	@Test
	public void testEstimatedChargesPackageRatePlanAdjustCompRate() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, packageRatePlan,
						masterObject.getRoomTypes().get(0), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		RateSnapshot rateSnapshot = builderBookReservation.getReservationCreateRequest().getRateSnapshots().iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal(0));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason("Free room with promotion");
		rateSnapshot.setCompInfo(compInfo);
		POEstimatedCharges.adjustNightlyRoomCharge(rateSnapshot, true);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPackageRoomChargeAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}
	
	@Test
	public void testEstimatedChargesPackageRatePlanAdjustRateIssueNewComp() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, packageRatePlan,
						masterObject.getRoomTypes().get(0), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		RateSnapshot rateSnapshot = builderBookReservation.getReservationCreateRequest().getRateSnapshots().iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal(0));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));		
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());
		compInfo.setCompCertificateNumber(RandomHelper.getRandomAlphaNumericString(10));
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);
		POEstimatedCharges.adjustNightlyRoomCharge(rateSnapshot, true);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPackageRoomChargeAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}
	
	@Test
	public void testEstimatedChargesPackageRatePlanAdjustRateRedeemComp() {
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, packageRatePlan,
						masterObject.getRoomTypes().get(0), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		RateSnapshot rateSnapshot = builderBookReservation.getReservationCreateRequest().getRateSnapshots().iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal(0));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(10));		
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());		
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);
		POEstimatedCharges.adjustNightlyRoomCharge(rateSnapshot, true);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPackageRoomChargeAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(), rateSnapshot.getPreOccupancyRate());
	}
	
	@Test
	@TestCase(ids = {"RST-4415","RST-3958"})
	public void testEstimatedChargesAdjustCharge() {
		int quantity = 1;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(masterObject.getItems().get(0).getName(),price,quantity);
		recurringCharge.setEveryNFrequency("1");
		recurringCharges.add(recurringCharge);
		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0),
						masterObject.getRoomTypes().get(0), propertyDate)
				.addRecurringCharge(recurringCharges, null, null)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		POEstimatedCharges.adjustCharge(propertyDate, masterObject.getItems().get(0).getName(), new BigDecimal(10), "Comment");
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate, masterObject.getItems().get(0).getName(), new BigDecimal(10));
	}
	
	@Test
	@TestCase(ids={"RST-2354","RST-2353","RST-4379",""})
	public void testEstimatedChargesDeleteCharge() {
		int quantity = 1;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(masterObject.getItems().get(0).getName(),price,quantity);
		recurringCharge.setEveryNFrequency("1");
		recurringCharges.add(recurringCharge);
		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0),
						masterObject.getRoomTypes().get(0), propertyDate)
				.addRecurringCharge(recurringCharges, null, null)
				.addPaymentMethod("Cash", null, null)
				.build();
		
		POEstimatedCharges.deleteCharge(propertyDate, masterObject.getItems().get(0).getName());
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate, masterObject.getItems().get(0).getName());
	}
	
	@Test
	@TestCase(id="RST-2331")
	public void testEstimatedChargesAddRecurringChargeTransactionItemEveryN() {
		int quantity = 1;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(masterObject.getItems().get(0).getName(),price,quantity);
		recurringCharge.setEveryNFrequency("2");
		recurringCharges.add(recurringCharge);
		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0),
						masterObject.getRoomTypes().get(0), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		PFReservationEstimatedCharges.expandEstimatedCharges();
		PFReservationEstimatedCharges.clickAddRecurringCharge();
		
		new BuilderAddRecurringCharge(recurringCharges)
				.setReason(RandomHelper.getRandomAlphaNumericString(10))
				.setCheckAvailability(false)
				.setPostDate(null)
				.build();
		
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());		
		VerifyReservation.verifyEstimatedChargesLineItemQuantity(propertyDate.plusDays(1), masterObject.getItems().get(0).getName(), quantity);
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate.plusDays(1), masterObject.getItems().get(0).getName(), price.multiply(new BigDecimal(quantity)));
		VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate, masterObject.getItems().get(0).getName());
	}
	
	@Test
	@TestCase(ids = {"RST-3956"})
	public void testEstimatedChargesAddRecurringChargeTransactionItemFirstNightOnly() {
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(inventoryItem.getName(),price, quantity);
		recurringCharge.setFirstNightOnlyFrequency();
		recurringCharges.add(recurringCharge);
		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0),
						masterObject.getRoomTypes().get(0), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		PFReservationEstimatedCharges.expandEstimatedCharges();
		PFReservationEstimatedCharges.clickAddRecurringCharge();
		
		new BuilderAddRecurringCharge(recurringCharges)
				.setReason(RandomHelper.getRandomAlphaNumericString(10))
				.setCheckAvailability(true)
				.setPostDate(null)
				.build();
		
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());		
		VerifyReservation.verifyEstimatedChargesLineItemQuantity(propertyDate, inventoryItem.getName(), quantity);
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate, inventoryItem.getName(), price.multiply(new BigDecimal(quantity)));
		VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate.plusDays(1), inventoryItem.getName());
	}
}