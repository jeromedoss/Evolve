package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.common.model.FrequencyType;
import com.agilysys.common.model.rate.CompInfo;
import com.agilysys.insertanator.creates.ratePlan.CreateRatePlan;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.item.CreateInventoryItem;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.account.model.InventoryItem;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.property.model.Room;
import com.agilysys.pms.rates.model.*;
import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.qa.client.account.item.ClientAccountingItemGet;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.client.rate.bundle.ClientComponentBundleCreate;
import com.agilysys.qa.client.rate.calendar.ClientRateCalendarGet;
import com.agilysys.qa.client.rate.category.ClientRateCategoryGet;
import com.agilysys.qa.client.rate.plan.ClientRatePlanCreate;
import com.agilysys.qa.client.rate.plan.ClientRatePlanGet;
import com.agilysys.qa.client.rate.plan.ClientRatePlanUpdate;
import com.agilysys.qa.client.rate.strategy.ClientRateStrategyGet;
import com.agilysys.qa.data.builder.rate.*;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.data.models.RecurringCharge;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.reservation.POEstimatedCharges;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationCheckInModal;
import com.agilysys.qa.gridIron.pageobject.reservation.PORooms;
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


public class TestReservationCheckIn extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	String tenantId = null;
	String propertyId = null;
	BuilderBookReservation builderBookReservation = null;
	ReservationCreateRequest reservationCreateRequest = null;
	LocalDate propertyDate = null;
	private String  cardDetails="Visa 0011";

    String swipeData = "2ED01801F4F2800839B%*4761********0011^UAT USA/TEST CARD 01      ^2412***************************?*;4761********0011=2412****************?*B54354BCB250AFBAE14CD33CD0C61EA9F624A3A9D2CB2E6413ED5B96A7E9602F237EC4CCAE89663299CD3D3D8101D4EC27E2D2F026C2C136B25B59AC4B4218D2F3DBF2377036929C58DFB74B164B3273FADAFFBF70254BE69697A00A6177B4DBCE7078FBA25DA70D58F9A7E0A07D937E128382652311B65E0000000000000000000000000000000000000000000000000000000000000000000000000000000038303654343439353534FFFF9876540042E0011C5DB303";
	String packageName = null;
	RatePlanDetail packageRatePlan = null;
	POReservationCheckInModal poReservationCheckInModal = new POReservationCheckInModal();
	Room room = null;
	String directBillAccountName = RandomHelper.getRandomAlphaString(5);

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
		packageRatePlan = clientRatePlanUpdate.updateRatePlan(masterObject.getProperty(), packageRatePlan);

		new PMSHelper().enableIDTech(masterObject.getProperty());
		new LoginApplication().Login(masterObject);

		new CreateCompanyProfile(directBillAccountName, RandomHelper.getRandomAlphaNumericString(6))
			.flowCreateWithARUsingSaveExit();
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
	}

	@Test	
	@TestCase(ids = {"`RST-166","RST-4121","RST-4134"})
	public void testReservationCheckInWithCash() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
				.setDepositDetails("Cash", "0")
				.build();
		
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		poReservationCheckInModal.checkInWithAuthorization();		
		VerifyReservation.verifyCheckIn();
		VerifyReservation.verifyAuthAmount("Cash", new BigDecimal(0.00));
		poReservationCheckInModal.performUndoCheckIn();
		VerifyReservation.verifyUndoCheckIn();
		VerifyReservation.verifyAuthAmount("Cash", new BigDecimal(0.00));
	}
	
	@Test	
	@TestCase(ids = {"RST-268","RST-4124"})
	public void testReservationCheckInWithDirectBill() {
		RatePlanSummary ratePlanSummary = new CreateRatePlan().create(masterObject.getCancelationPolicy(), masterObject.getProperty());
		builderBookReservation
				.setRandomRateSnapshots(2, ratePlanSummary.getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber()
				.addPaymentMethod("Direct Bill", null, directBillAccountName)
				.setDepositDetails(null, "0")
				.build();
		
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		poReservationCheckInModal.checkInWithAuthorization();		
		VerifyReservation.verifyCheckIn();
		VerifyReservation.verifyAuthAmount("Cash", new BigDecimal(0.00));		
	}

	@Test
	@TestCase(ids = {"RST-168", "RST-169","RST-4126","RST-4240"})
	public void testReservationCheckInWithExistingCreditCard() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber()
				.addPaymentMethod("New Credit Card", swipeData, null)				
				.setDepositDetails(cardDetails, "0")
				.build();
				
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		poReservationCheckInModal.checkInWithAuthorization();	
		VerifyReservation.verifyCheckIn();
		VerifyReservation.verifyAuthAmount(cardDetails, POEstimatedCharges.getEstimatedDueAtCheckOut());
		poReservationCheckInModal.performUndoCheckIn();
		VerifyReservation.verifyUndoCheckIn();
		Selenide.refresh();
		VerifyReservation.verifyAuthAmount(cardDetails, new BigDecimal(0.00));
	}

	@Test	
	@TestCase(ids={"RST-4241"})
	public void testReservationCheckInWithNewCard() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)				
				.setDepositDetails("Cash", "10")
				.build();
				
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		poReservationCheckInModal.checkInWithNewCreditCard(swipeData);
		VerifyReservation.verifyCheckIn();
		VerifyReservation.verifyAuthAmount(cardDetails, POEstimatedCharges.getEstimatedDueAtCheckOut());
	}
	
	@Test
	@TestCase(ids = {"RST-12532"})
	public void testReservationCheckInWithoutAuth() {
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber()
				.addPaymentMethod("New Credit Card", swipeData, null)
				.setDepositPaymentMethod(cardDetails)
				.build();
				
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		poReservationCheckInModal.checkInWithoutAuthorization();
		VerifyReservation.verifyCheckIn();
		VerifyReservation.verifyAuthAmount(cardDetails, new BigDecimal(0.00));
	}
	
	@Test	
	@TestCase(ids={"RST-7174"})
	public void testPackageReservationCheckInWithExistingCreditCard() {
		builderBookReservation
				.setRandomRateSnapshots(2, packageName,
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber()
				.addPaymentMethod("New Credit Card", swipeData, null)								
				.build();
				
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		poReservationCheckInModal.checkInWithAuthorization();	
		VerifyReservation.verifyCheckIn();
		VerifyReservation.verifyAuthAmount(cardDetails, POEstimatedCharges.getEstimatedDueAtCheckOut());
		poReservationCheckInModal.performUndoCheckIn();
		VerifyReservation.verifyUndoCheckIn();
		VerifyReservation.verifyAuthAmount(cardDetails, new BigDecimal(0.00));
	}
	
	@Test(priority = 60)
	@TestCase(ids = {"RST-4088"})
	public void testCompReservationCheckInWithExistingCreditCard() {		
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(masterObject.getItems().get(0).getName(),price,quantity);
		recurringCharge.setEveryNFrequency("1");
		recurringCharges.add(recurringCharge);
		
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber()
				.addRecurringCharge(recurringCharges, null, null)
				.addPaymentMethod("New Credit Card", swipeData, null)
				.setDepositPaymentMethod(cardDetails);
		
		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();		
		rateSnapshots.forEach(rateSnapshot ->{
			rateSnapshot.setPreOccupancyRate(new BigDecimal("0"));
			rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));
			CompInfo compInfo = new CompInfo();
			compInfo.setReason("Free room with promotion");
			rateSnapshot.setCompInfo(compInfo);
		});
		
		builderBookReservation.build();
				
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		poReservationCheckInModal.checkInWithAuthorization();	
		VerifyReservation.verifyCheckIn();
		VerifyReservation.verifyAuthAmount(cardDetails, POEstimatedCharges.getEstimatedDueAtCheckOut());
	}
	
	@Test	
	public void testCompPackageReservationCheckInWithExistingCreditCard() {
		builderBookReservation
				.setRandomRateSnapshots(2, packageName,
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber()
				.addPaymentMethod("New Credit Card", swipeData, null)
				.setDepositPaymentMethod(cardDetails);
		
		Set<RateSnapshot> rateSnapshots = builderBookReservation.getReservationCreateRequest().getRateSnapshots();		
		rateSnapshots.forEach(rateSnapshot ->{
			rateSnapshot.setPreOccupancyRate(new BigDecimal("0"));
			rateSnapshot.setOverriddenComment(RandomHelper.getRandomAlphaNumericString(20));
			CompInfo compInfo = new CompInfo();
			compInfo.setReason("Free room with promotion");
			rateSnapshot.setCompInfo(compInfo);
		});
		
		builderBookReservation.build();
				
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		poReservationCheckInModal.checkInWithAuthorization();	
		VerifyReservation.verifyCheckIn();
		VerifyReservation.verifyAuthAmount(cardDetails, POEstimatedCharges.getEstimatedDueAtCheckOut());
	}
}