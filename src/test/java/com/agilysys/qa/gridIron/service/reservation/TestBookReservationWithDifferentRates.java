package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.common.model.FrequencyType;
import com.agilysys.common.model.rate.CompInfo;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.item.CreateInventoryItem;
import com.agilysys.insertanator.creates.item.CreateItem;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.account.model.InventoryItem;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.common.model.integration.OtaInterface;
import com.agilysys.pms.common.model.integration.OtaNativeSetting;
import com.agilysys.pms.property.client.PropertyManagementClient;
import com.agilysys.pms.rates.model.*;
import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.qa.client.account.comp.info.ClientCompInfoAdd;
import com.agilysys.qa.client.account.item.ClientAccountingItemGet;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.client.rate.bundle.ClientComponentBundleCreate;
import com.agilysys.qa.client.rate.calendar.ClientRateCalendarGet;
import com.agilysys.qa.client.rate.category.ClientRateCategoryGet;
import com.agilysys.qa.client.rate.plan.ClientRatePlanCreate;
import com.agilysys.qa.client.rate.plan.ClientRatePlanUpdate;
import com.agilysys.qa.client.rate.strategy.ClientRateStrategyGet;
import com.agilysys.qa.data.builder.rate.*;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.locators.reservation.estimatedcharges.LocatorsEstimatedChargesSection;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Configuration;
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

public class TestBookReservationWithDifferentRates extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	String tenantId = null;
	String propertyId = null;
	BuilderBookReservation builderBookReservation = null;
	ReservationCreateRequest reservationCreateRequest = null;
	LocalDate propertyDate = null;

	String packageName = null;
	int packageComponentQty = 0;
	TransactionItem packageComponentTransactionItem;
	InventoryItem packageComponentInventoryItem;
	BigDecimal packageComponentPrice = null;

	RatePlanDetail packageRatePlan = null;
	CompInfo compReasonCertificationRequired = null;

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

		// Enable Comp Certification
		PropertyManagementClient propertyManagementClient = new PropertyManagementClient(
				EndPoints.getPropertyServiceURI());
		
		
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

		try {
			compReasonCertificationRequired = new ClientCompInfoAdd().addCompReason(masterObject.getProperty(),
					compReasonCertificationRequired);
		} catch (Exception e) {

		}

		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());

		List<RateCategoryDetail> rateCategoryDetails = new ClientRateCategoryGet()
				.getRatePlanCategories(masterObject.getProperty());
		List<RateCalendarDetail> rateCalendarSummaries = new ClientRateCalendarGet()
				.getRateCalendars(masterObject.getProperty());

		packageComponentQty = RandomHelper.getRandomInt(1, 5);
		packageComponentPrice = new BigDecimal(RandomHelper.getRandomInt(1, 100));

		packageName = "PKG_" + RandomHelper.getRandomAlphaNumericString(6);
		packageRatePlan = new BuilderRatePlanDetail(packageName, "PKGC" + RandomHelper.getRandomAlphaNumericString(4),
				propertyDate, 2, 0, masterObject.getCancelationPolicy().getId(), rateCalendarSummaries.get(0).getId(),
				rateCategoryDetails.get(0).getId(),
				new ClientAccountingItemGet().getNightlyRoomChargeItem(masterObject.getProperty()).getId()).build();
		packageRatePlan.setRateSelectorType(RateSelectorType.DAY_OF_WEEK);

		// Create Components

		List<Component> components = new ArrayList<>();
		TransactionItem item = new CreateItem().create(masterObject.getCategories().get(0),
				masterObject.getSubCategories().get(0), masterObject.getProperty());
		List<TransactionItem> transactionItems = new ClientAccountingItemGet()
				.getTransactionItems(masterObject.getProperty());
		InventoryItem inventoryItem = new CreateInventoryItem().create(masterObject.getCategories().get(0),
				masterObject.getSubCategories().get(0), 5000, masterObject.getProperty());

		packageComponentTransactionItem = transactionItems.stream()
				.filter(x -> x.getName().equalsIgnoreCase(item.getName())).findAny().get();

		packageComponentInventoryItem = inventoryItem;
		components.add(new BuilderComponent(packageComponentTransactionItem, packageComponentPrice, packageComponentQty,
				FrequencyType.FIRST).build());
		components.add(new BuilderComponent(packageComponentInventoryItem, packageComponentPrice, packageComponentQty,
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

		new LoginApplication().Login(masterObject);

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
	@TestCase(ids = {"RST-7163", "RST-1570"})
	public void testCreateReservationWithAdjustedRate() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();
		RateSnapshot rateSnapshot = rateSnapshots.iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal("10"));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));

		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(),
				new BigDecimal(10));
	}

	@Test
	@TestCase(id="RST-1708")
	public void testCreateReservationWithCompRate() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();
		RateSnapshot rateSnapshot = rateSnapshots.iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal("0"));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason("Free room with promotion");
		rateSnapshot.setCompInfo(compInfo);

		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(),
				new BigDecimal(0));
	}

	@Test
	@TestCase(id = "RST-2229")
	public void testCreateReservationWithPackageRatePlanCompRate() {
		builderBookReservation
				.setRandomRateSnapshots(2, packageName, masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();
		RateSnapshot rateSnapshot = rateSnapshots.iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal("0"));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason("Free room with promotion");
		rateSnapshot.setCompInfo(compInfo);

		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());

		VerifyReservation.verifyPackageRoomChargeAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(),
				rateSnapshot.getPreOccupancyRate());

	}

	@Test
	@TestCase(id = "RST-13303")
	public void testCreateReservationWithPackageRatePlan() {
		System.out.println(packageName);
		Configuration.holdBrowserOpen=true;
		builderBookReservation.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
				.setRandomRateSnapshots(2, packageName, masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.build();
		RateSnapshot rateSnapshot = builderBookReservation.getReservationCreateRequest().getRateSnapshots().iterator()
				.next();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());

		VerifyReservation.verifyPackageRoomChargeAmount(propertyDate, rateSnapshot.getRoomTypeCode(),
				packageRatePlan.getRatePlanStrategies().get(0).getBasePrice());
		VerifyReservation.verifyPackageComponentAmount(propertyDate, packageComponentTransactionItem.getName(),
				packageComponentPrice.multiply(new BigDecimal(packageComponentQty)));
		VerifyReservation.verifyPackageComponentAmount(propertyDate, packageComponentInventoryItem.getName(),
				packageComponentPrice.multiply(new BigDecimal(packageComponentQty)));
		VerifyReservation.verifyPackageComponentQuantity(propertyDate, packageComponentTransactionItem.getName(),
				packageComponentQty);
		VerifyReservation.verifyPackageComponentQuantity(propertyDate, packageComponentInventoryItem.getName(),
				packageComponentQty);

		VerifyReservation.verifyPackageRoomChargeAmount(propertyDate.plusDays(1), rateSnapshot.getRoomTypeCode(),
				packageRatePlan.getRatePlanStrategies().get(0).getBasePrice());
		VerifyReservation.verifyElementNotPresent(LocatorsEstimatedChargesSection
				.getPackageComponentAmount(propertyDate.plusDays(1), packageComponentTransactionItem.getName()));
		VerifyReservation.verifyElementNotPresent(LocatorsEstimatedChargesSection
				.getPackageComponentAmount(propertyDate.plusDays(1), packageComponentInventoryItem.getName()));
	}

	@Test
	@TestCase(id = "RST-2252")
	public void testCreateReservationWithPackageRatePlanAdjustedRate() {
		builderBookReservation
				.setRandomRateSnapshots(2, packageName, masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();
		RateSnapshot rateSnapshot = rateSnapshots.iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal("10"));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));

		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPackageRoomChargeAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(),
				rateSnapshot.getPreOccupancyRate());
	}

	@Test
	public void testCreateReservationWithPackageRatePlanAndRegularRatePlan() {
		builderBookReservation
				.setRandomRateSnapshots(2, packageName, masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();
		RateSnapshot rateSnapshot = rateSnapshots.iterator().next();
		rateSnapshot.setRatePlanName(masterObject.getRatePlans().get(0).getName());
		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());

		rateSnapshots.forEach((snapShot) -> {
			if (snapShot.getRatePlanName().equalsIgnoreCase(packageName)) {
				VerifyReservation.verifyPackageRoomChargeAmount(snapShot.getDate(), snapShot.getRoomTypeCode(),
						packageRatePlan.getRatePlanStrategies().get(0).getBasePrice());
			}
		});

	}

	@Test
	@TestCase(id = "RST-8902")
	public void testCreateReservationWithRegularRatePlanIssueComp() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();
		RateSnapshot rateSnapshot = rateSnapshots.iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal("0"));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);
		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(),
				rateSnapshot.getPreOccupancyRate());
	}

	@Test
	@TestCase(id = "RST-8902")
	public void testCreateReservationWithRegularRatePlanRedeemComp() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();
		RateSnapshot rateSnapshot = rateSnapshots.iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal("0"));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());
		compInfo.setCompCertificateNumber(RandomHelper.getRandomAlphaNumericString(10));
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);

		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyEstimatedChargesLineItemAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(),
				rateSnapshot.getPreOccupancyRate());
	}

	@Test
	@TestCase(id = "RST-13383")
	public void testCreateReservationWithPackageRatePlanIssueComp() {
		builderBookReservation
				.setRandomRateSnapshots(2, packageName, masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();
		RateSnapshot rateSnapshot = rateSnapshots.iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal("0"));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);

		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPackageRoomChargeAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(),
				rateSnapshot.getPreOccupancyRate());
	}

	@Test
	@TestCase(id = "RST-13383")
	public void testCreateReservationWithPackageRatePlanRedeemComp() {
		builderBookReservation
				.setRandomRateSnapshots(2, packageName, masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null);

		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();
		RateSnapshot rateSnapshot = rateSnapshots.iterator().next();
		rateSnapshot.setPreOccupancyRate(new BigDecimal("0"));
		rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));
		CompInfo compInfo = new CompInfo();
		compInfo.setReason(compReasonCertificationRequired.getReason());
		compInfo.setCompCertificateNumber(RandomHelper.getRandomAlphaNumericString(10));
		compInfo.setCertificateRequired(true);
		rateSnapshot.setCompInfo(compInfo);

		builderBookReservation.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPackageRoomChargeAmount(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode(),
				rateSnapshot.getPreOccupancyRate());
	}

}