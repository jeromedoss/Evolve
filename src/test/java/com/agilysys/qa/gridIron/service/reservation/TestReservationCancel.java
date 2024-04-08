package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.common.model.FrequencyType;
import com.agilysys.common.model.rate.CompInfo;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.item.CreateInventoryItem;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.account.model.InventoryItem;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.property.model.CancellationPolicy;
import com.agilysys.pms.property.model.Room;
import com.agilysys.pms.rates.model.*;
import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.qa.client.account.item.ClientAccountingItemGet;
import com.agilysys.qa.client.policy.cancellation.ClientCancellationPolicyCreate;
import com.agilysys.qa.client.policy.cancellation.ClientCancellationPolicyGet;
import com.agilysys.qa.client.policy.cancellation.ClientCancellationPolicyUpdate;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.client.rate.bundle.ClientComponentBundleCreate;
import com.agilysys.qa.client.rate.calendar.ClientRateCalendarGet;
import com.agilysys.qa.client.rate.category.ClientRateCategoryGet;
import com.agilysys.qa.client.rate.plan.ClientRatePlanCreate;
import com.agilysys.qa.client.rate.plan.ClientRatePlanGet;
import com.agilysys.qa.client.rate.plan.ClientRatePlanUpdate;
import com.agilysys.qa.client.rate.strategy.ClientRateStrategyGet;
import com.agilysys.qa.data.builder.property.BuilderCancellationPolicy;
import com.agilysys.qa.data.builder.rate.*;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationFoliosSection;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.reservation.POCancelReservation;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationCheckInModal;
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


public class TestReservationCancel extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	String tenantId = null;
	String propertyId = null;
	BuilderBookReservation builderBookReservation = null;
	ReservationCreateRequest reservationCreateRequest = null;
	LocalDate propertyDate = null;

	String swipeData = "2ED01801F4F2800839B%*4761********0011^UAT USA/TEST CARD 01      ^2412***************************?*;4761********0011=2412****************?*B54354BCB250AFBAE14CD33CD0C61EA9F624A3A9D2CB2E6413ED5B96A7E9602F237EC4CCAE89663299CD3D3D8101D4EC27E2D2F026C2C136B25B59AC4B4218D2F3DBF2377036929C58DFB74B164B3273FADAFFBF70254BE69697A00A6177B4DBCE7078FBA25DA70D58F9A7E0A07D937E128382652311B65E0000000000000000000000000000000000000000000000000000000000000000000000000000000038303654343439353534FFFF9876540042E0011C5DB303";
	String packageName = null;
	RatePlanDetail packageRatePlan = null;
	POReservationCheckInModal poReservationCheckInModal = new POReservationCheckInModal();
	Room room = null;
	ClientCancellationPolicyUpdate clientCancellationPolicyUpdate;
	CancellationPolicy cancellationPolicy;
	POCancelReservation poCancelReservation;
	String cancellationChargeName;

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
		
		ClientRatePlanUpdate clientRatePlanUpdate = new ClientRatePlanUpdate();
		List<RatePlanDetail> ratePlanDetails = new ClientRatePlanGet().getDetailedRatePlans(masterObject.getProperty());
		RatePlanDetail ratePlanDetail = ratePlanDetails.stream().filter(x -> x.getCode().equalsIgnoreCase(masterObject.getRatePlans().get(0).getCode())).findAny().get();
		ratePlanDetail.setDepositPolicyId(masterObject.getDepositPolicy().getId());		
		clientRatePlanUpdate.updateRatePlan(masterObject.getProperty(), ratePlanDetail);
		
		clientCancellationPolicyUpdate = new ClientCancellationPolicyUpdate();
		List<CancellationPolicy> cancellationPolicies = new ClientCancellationPolicyGet().findCancellationPolicies(masterObject.getProperty());
		cancellationPolicy = cancellationPolicies.stream().filter(x -> x.getId().equals(ratePlanDetail.getCancellationPolicyId())).findAny().get();
		
		List<TransactionItem> items = new ClientAccountingItemGet().getTransactionItems(masterObject.getProperty());
		cancellationChargeName = items.stream().filter(x->x.getId().equals(cancellationPolicy.getItemId())).findAny().get().getName();
		
		List<RateCategoryDetail> rateCategoryDetails = new ClientRateCategoryGet()
				.getRatePlanCategories(masterObject.getProperty());
		List<RateCalendarDetail> rateCalendarSummaries = new ClientRateCalendarGet()
				.getRateCalendars(masterObject.getProperty());
		
		packageName = "PKG_" + RandomHelper.getRandomAlphaNumericString(6);
		packageRatePlan = new BuilderRatePlanDetail(packageName,
				"PKGC" + RandomHelper.getRandomAlphaNumericString(4), propertyDate, 2, 0,
				cancellationPolicy.getId(), rateCalendarSummaries.get(0).getId(),
				rateCategoryDetails.get(0).getId(),
				new ClientAccountingItemGet().getNightlyRoomChargeItem(masterObject.getProperty()).getId()).build();
		packageRatePlan.setRateSelectorType(RateSelectorType.DAY_OF_WEEK);

		// Create Components
		List<Component> components = new ArrayList<>();
		List<TransactionItem> transactionItems = new ClientAccountingItemGet()
				.getTransactionItems(masterObject.getProperty());
		InventoryItem inventoryItem = new CreateInventoryItem().create(masterObject.getCategories().get(0),
				masterObject.getSubCategories().get(0), RandomHelper.getRandomInt(1, 99), masterObject.getProperty());

		components.add(new BuilderComponent(transactionItems.stream().filter(x->x.getName().equalsIgnoreCase("Nightly Room Charge")).findFirst().get(), new BigDecimal(RandomHelper.getRandomInt(1, 10)),
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
		packageRatePlan.setCancellationPolicyId(cancellationPolicy.getId());
		packageRatePlan = clientRatePlanUpdate.updateRatePlan(masterObject.getProperty(), packageRatePlan);
		new PMSHelper().enableIDTech(masterObject.getProperty());
		new LoginApplication().Login(masterObject);

		PFSearchTile.waitForSearchPageToLoad();
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {
		room = new CreateRoom().create(masterObject.getRoomTypes().get(0), masterObject.getBuilding(), masterObject.getProperty());
		open(EndPoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);
		PFSearchTile.waitForSearchPageToLoad();
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);

		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
		
		cancellationPolicy.setFlatFee(null);
		cancellationPolicy.setNights(null);
		cancellationPolicy.setMatchDepositPolicy(false);
		
		cancellationPolicy.setDaysAfterBooking(null);
		cancellationPolicy.setDaysBeforeArrival(null);
		poCancelReservation= new POCancelReservation();
	}

	@Test	
	@TestCase(ids={"RST-3891","RST-4144"})
	public void testCancelReservationWithCashPaymentFlatFee() {
		cancellationPolicy.setFlatFee(new BigDecimal(50));
		cancellationPolicy.setDaysBeforeArrival(2);
		clientCancellationPolicyUpdate.updateCancellationPolicy(masterObject.getProperty(), cancellationPolicy);
		
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate.plusDays(1))
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.addPaymentMethod("Cash", null, null)
				.setDepositDetails("Cash", "0")
				.build();		
		
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();		
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName(), propertyDate.plusDays(1));
		poCancelReservation
				.setPaymentMethodName("Cash")
				.setSetCredentials(true)
				.setReason(RandomHelper.getRandomAlphaNumericString(10))
				.cancelReservation();
		
		VerifyReservation.verifyReservationCancel();		
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal("Cash", new BigDecimal(0).subtract(cancellationPolicy.getFlatFee()));
		VerifyReservation.verifyFolioLineItemTotal(cancellationChargeName, cancellationPolicy.getFlatFee());
	}

	@Test
	@TestCase(ids = {"RST-4162","RST-4145"})
	public void testCancelReservationWithCardPaymentTwoNightCancellationFee() {
		BigDecimal preOccupancyRate = new BigDecimal(50);
		cancellationPolicy.setNights(2);
		cancellationPolicy.setDaysBeforeArrival(2);		
		clientCancellationPolicyUpdate.updateCancellationPolicy(masterObject.getProperty(), cancellationPolicy);
		
		builderBookReservation
				.setRandomRateSnapshots(3, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate.plusDays(1))
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.addPaymentMethod("New Credit Card", swipeData, null)
				.setDepositDetails("Visa 0011", "0");
		builderBookReservation.getReservationCreateRequest().getRateSnapshots().forEach(x->{
			x.setPreOccupancyRate(preOccupancyRate);
			x.setOverriddenComment(RandomHelper.getRandomAlphaString(10));
		});
		builderBookReservation.build();
		
		
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();		
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName(), propertyDate.plusDays(1));
		poCancelReservation
				.setPaymentMethodName("Visa 0011")
				.setReason(RandomHelper.getRandomAlphaNumericString(10))
				.cancelReservation();
		
		VerifyReservation.verifyReservationCancel();		
		PFReservationFoliosSection.clickDefaultFolio();
		BigDecimal cancellationFee = preOccupancyRate.multiply(new BigDecimal(2));
		VerifyReservation.verifyFolioLineItemTotal("Visa 0011", new BigDecimal(0).subtract(cancellationFee));
		VerifyReservation.verifyFolioLineItemTotal(cancellationChargeName, cancellationFee);
		poCancelReservation.undoCancelReservation();	
		VerifyReservation.verifyReservationUndoCancel();
	}
	
	@Test
	public void testCancelReservationCancellationChargeMatchToDepositPolicy() {		
		cancellationPolicy.setMatchDepositPolicy(true);
		cancellationPolicy.setDaysBeforeArrival(2);
		clientCancellationPolicyUpdate.updateCancellationPolicy(masterObject.getProperty(), cancellationPolicy);
		
		builderBookReservation
				.setRandomRateSnapshots(3, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate.plusDays(1))
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.addPaymentMethod("Cash", null, null)
				.setDepositPaymentMethod("Cash")
				.build();		
		
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();		
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName(), propertyDate.plusDays(1));
		poCancelReservation				
				.setReason(RandomHelper.getRandomAlphaNumericString(10))
				.cancelReservation();
		
		VerifyReservation.verifyReservationCancel();		
		PFReservationFoliosSection.clickDefaultFolio();
		BigDecimal cancellationFee = new BigDecimal(50);		
		VerifyReservation.verifyFolioLineItemTotal(cancellationChargeName, cancellationFee);
	}
	
	@Test
	@TestCase(id="RST-7186")
	public void testCancelPackageReservationOverrideFlatCancellationCharge() {		
		cancellationPolicy.setMatchDepositPolicy(true);
		cancellationPolicy.setDaysBeforeArrival(2);
		clientCancellationPolicyUpdate.updateCancellationPolicy(masterObject.getProperty(), cancellationPolicy);
		BigDecimal cancellationFee = new BigDecimal(50);
		builderBookReservation
				.setRandomRateSnapshots(3, packageName,
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate.plusDays(1))
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.addPaymentMethod("Cash", null, null)
				.setDepositDetails("Cash", "0")
				.build();		
		
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();		
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName(), propertyDate.plusDays(1));
		poCancelReservation				
				.setReason(RandomHelper.getRandomAlphaNumericString(10))
				.setOverrideCancellationCharge(true)
				.setFlatFee(cancellationFee)
				.setPaymentMethodName("Cash")
				.setSetCredentials(true)
				.cancelReservation();
		
		VerifyReservation.verifyReservationCancel();		
		PFReservationFoliosSection.clickDefaultFolio();				
		VerifyReservation.verifyFolioLineItemTotal("Cash", new BigDecimal(0).subtract(cancellationFee));
		VerifyReservation.verifyFolioLineItemTotal(cancellationChargeName, cancellationFee);
	}
	
	@Test
	@TestCase(id = "RST-4159")
	public void testCancelReservationOverridePercentageCancellationCharge() {		
		BigDecimal cancellationFee = new BigDecimal(50);
		cancellationPolicy.setFlatFee(cancellationFee);
		cancellationPolicy.setDaysBeforeArrival(2);
		clientCancellationPolicyUpdate.updateCancellationPolicy(masterObject.getProperty(), cancellationPolicy);
		
		builderBookReservation
				.setRandomRateSnapshots(3, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate.plusDays(1))
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.addPaymentMethod("Cash", null, null)
				.setDepositDetails("Cash", "0")
				.build();		
		
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();		
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName(), propertyDate.plusDays(1));
		poCancelReservation				
				.setReason(RandomHelper.getRandomAlphaNumericString(10))
				.setOverrideCancellationCharge(true)
				.setPercentage(new BigDecimal(50))
				.setPaymentMethodName("Cash")
				.setSetCredentials(true)
				.cancelReservation();
		
		cancellationFee = cancellationFee.divide(new BigDecimal(2));
		VerifyReservation.verifyReservationCancel();		
		PFReservationFoliosSection.clickDefaultFolio();				
		VerifyReservation.verifyFolioLineItemTotal("Cash", new BigDecimal(0).subtract(cancellationFee));
		VerifyReservation.verifyFolioLineItemTotal(cancellationChargeName, cancellationFee);
	}
	
	@Test
	@TestCase(id = "RST-4161")
	public void testCancelCompReservation() {

		BigDecimal cancellationFee = new BigDecimal(50);
		cancellationPolicy.setFlatFee(cancellationFee);
		cancellationPolicy.setDaysBeforeArrival(2);
		clientCancellationPolicyUpdate.updateCancellationPolicy(masterObject.getProperty(), cancellationPolicy);
		
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate.plusDays(1))
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.addPaymentMethod("Cash", null, null)
				.setDepositDetails("Cash", "0");
		
		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();		
		rateSnapshots.forEach(rateSnapshot ->{
			rateSnapshot.setPreOccupancyRate(new BigDecimal("0"));
			rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));
			CompInfo compInfo = new CompInfo();
			compInfo.setReason("Free room with promotion");
			rateSnapshot.setCompInfo(compInfo);
		});
		
		builderBookReservation.build();		
		
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();		
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName(), propertyDate.plusDays(1));
		poCancelReservation				
				.setReason(RandomHelper.getRandomAlphaNumericString(10))
				.setOverrideCancellationCharge(true)
				.setPercentage(new BigDecimal(50))
				.setPaymentMethodName("Cash")
				.setSetCredentials(true)
				.cancelReservation();
		
		cancellationFee = cancellationFee.divide(new BigDecimal(2));
		VerifyReservation.verifyReservationCancel();		
		PFReservationFoliosSection.clickDefaultFolio();				
		VerifyReservation.verifyFolioLineItemTotal("Cash", new BigDecimal(0).subtract(cancellationFee));
		VerifyReservation.verifyFolioLineItemTotal(cancellationChargeName, cancellationFee);
		poCancelReservation.undoCancelReservation();	
		VerifyReservation.verifyReservationUndoCancel();
	}
	
	@Test
	public void testCancelReservationChangePolicy() {		
		BigDecimal cancellationFee = new BigDecimal(50);
		CancellationPolicy overridePolicy = new BuilderCancellationPolicy(RandomHelper.getRandomAlphaString(5), RandomHelper.getRandomAlphaString(10), masterObject.getItems().get(0)).build();
		overridePolicy.setFlatFee(cancellationFee);
		overridePolicy.setDaysBeforeArrival(2);		
		new ClientCancellationPolicyCreate().createCancellationPolicy(masterObject.getProperty(), overridePolicy);
		
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate.plusDays(1))
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.addPaymentMethod("Cash", null, null)
				.setDepositDetails("Cash", "0")
				.build();		
		
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();		
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName(), propertyDate.plusDays(1));
		poCancelReservation				
				.setReason(RandomHelper.getRandomAlphaNumericString(10))
				.setCancellationPolicyName(overridePolicy.getCode())				
				.setPaymentMethodName("Cash")
				.setSetCredentials(true)
				.cancelReservation();
		
		VerifyReservation.verifyReservationCancel();		
		PFReservationFoliosSection.clickDefaultFolio();				
		VerifyReservation.verifyFolioLineItemTotal("Cash", new BigDecimal(0).subtract(cancellationFee));
		VerifyReservation.verifyFolioLineItemTotal(masterObject.getItems().get(0).getName(), cancellationFee);
	}
}