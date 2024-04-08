package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.item.CreateItem;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.account.client.InventoryItemServiceClient;
import com.agilysys.pms.account.model.InventoryItem;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.profile.model.GuestType;
import com.agilysys.pms.profile.model.MarketSegment;
import com.agilysys.pms.rates.model.RatePlanDetail;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.common.model.TrackingInfo;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.shared.folios.BuilderAddRecurringCharge;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationFoliosSection;
import com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAdjustChargeModal;
import com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails;
import com.agilysys.qa.gridIron.data.models.RecurringCharge;
import com.agilysys.qa.gridIron.data.models.ReservationPaymentMethod;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationPaymentMethods;
import com.agilysys.qa.gridIron.pageobject.shared.POAddCreditModal;
import com.agilysys.qa.gridIron.pageobject.shared.POPayments;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.agilysys.qa.constants.EndPoints;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestReservationFolios extends StayUIWrappers {

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

	InventoryItemServiceClient inventoryItemClient;

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
		inventoryItemClient = new InventoryItemServiceClient(EndPoints.getAccountServiceURI());

		new LoginApplication().Login(masterObject);
		PFSearchTile.waitForSearchPageToLoad();

		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
		builderBookReservation.setRandomPersonalDetails().setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.addPaymentMethod("Cash", null, null).build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();

	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {
		open(EndPoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);
		PFSearchTile.waitForSearchPageToLoad();
		Selenide.refresh();
		PFSearchTile.waitForSearchPageToLoad();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
	}

	@Test
	@TestCase(ids = { "RST-4362", "RST-274", "RST-338" })
	public void testReservationFolioAddTransactionItemChargeAndAdjustForGuestSatisficationByAmount() {
		PFFoliosDetails.navigatePaymentSection();
		PFFoliosDetails.clickAddACharge();
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(masterObject.getItems().get(0).getName(), price,
				quantity);
		recurringCharges.add(recurringCharge);

		BuilderAddRecurringCharge builderAddRecurringCharge = new BuilderAddRecurringCharge(recurringCharges)
				.setReason(RandomHelper.getRandomAlphaNumericString(10));
		builderAddRecurringCharge.build();
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(masterObject.getItems().get(0).getName(),
				price.multiply(new BigDecimal(quantity)));
		// Adjust Charge
		PFReservationFoliosSection.clickFolioLineItem(masterObject.getItems().get(0).getName());
		BigDecimal guestSatisfication = new BigDecimal(50);
		PFAdjustChargeModal.setDollarAmount(guestSatisfication);
		PFAdjustChargeModal.typeReason(RandomHelper.getRandomAlphaNumericString(10));
		PFAdjustChargeModal.saveAdjustCharge();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(masterObject.getItems().get(0).getName(),
				price.multiply(new BigDecimal(quantity)).subtract(guestSatisfication));
	}

	@Test
	@TestCase(ids = {"RST-9824"})
	public void testReservationFolioAddInventoryItemChargeAndAdjustForGuestSatisficationByPercentage() {
		InventoryItem inventoryItem = createInventoryItem();
		PFFoliosDetails.navigatePaymentSection();
		PFFoliosDetails.clickAddACharge();
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(inventoryItem.getName(), price, quantity);
		recurringCharges.add(recurringCharge);

		BuilderAddRecurringCharge builderAddRecurringCharge = new BuilderAddRecurringCharge(recurringCharges)
				.setReason(RandomHelper.getRandomAlphaNumericString(10));
		builderAddRecurringCharge.build();
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(inventoryItem.getName(), price.multiply(new BigDecimal(quantity)));
		// Adjust Charge
		PFReservationFoliosSection.clickFolioLineItem(inventoryItem.getName());
		BigDecimal percentage = new BigDecimal(50);
		PFAdjustChargeModal.setPercentage(new BigDecimal(50));
		PFAdjustChargeModal.typeReason(RandomHelper.getRandomAlphaNumericString(10));
		PFAdjustChargeModal.saveAdjustCharge();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(inventoryItem.getName(),
				price.multiply(new BigDecimal(quantity)).multiply(percentage).divide(new BigDecimal(100)));
	}

	@Test(priority = -10)
	public void testReservationFolioCorrectChargeIncreaseBy10Percentage() {
		InventoryItem inventoryItem = createInventoryItem();
		PFFoliosDetails.navigatePaymentSection();
		PFFoliosDetails.clickAddACharge();
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(inventoryItem.getName(), price, quantity);
		recurringCharges.add(recurringCharge);

		BuilderAddRecurringCharge builderAddRecurringCharge = new BuilderAddRecurringCharge(recurringCharges)
				.setReason(RandomHelper.getRandomAlphaNumericString(10));
		builderAddRecurringCharge.build();
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(inventoryItem.getName(), price.multiply(new BigDecimal(quantity)));
		// Adjust Charge
		String adjustReason = RandomHelper.getRandomAlphaNumericString(10);
		PFReservationFoliosSection.clickFolioLineItem(inventoryItem.getName());
		BigDecimal percentage = new BigDecimal(10);
		PFAdjustChargeModal.selectAdjustMethod("Add to the charge");
		PFAdjustChargeModal.setPercentage(percentage);
		PFAdjustChargeModal.typeReason(adjustReason);
		PFAdjustChargeModal.saveAdjustCharge();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickDefaultFolio();
		BigDecimal increaseAmount = price.multiply(new BigDecimal(quantity)).multiply(percentage)
				.divide(new BigDecimal(100));
		VerifyReservation.verifyFolioLineItemTotalByReason(adjustReason,
				price.multiply(new BigDecimal(quantity)).add(increaseAmount));
	}

	@Test
	@TestCase(ids = {"RST-2253"})
	public void testReservationFolioCorrectChargeDecreaseChargeBy10Dollar() {
		InventoryItem inventoryItem = createInventoryItem();
		PFFoliosDetails.navigatePaymentSection();
		PFFoliosDetails.clickAddACharge();
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(inventoryItem.getName(), price, quantity);
		recurringCharges.add(recurringCharge);

		BuilderAddRecurringCharge builderAddRecurringCharge = new BuilderAddRecurringCharge(recurringCharges)
				.setReason(RandomHelper.getRandomAlphaNumericString(10));
		builderAddRecurringCharge.build();
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(inventoryItem.getName(), price.multiply(new BigDecimal(quantity)));
		// Adjust Charge
		PFReservationFoliosSection.clickFolioLineItem(inventoryItem.getName());
		String adjustReason = RandomHelper.getRandomAlphaNumericString(10);
		PFAdjustChargeModal.selectAdjustMethod("Subtract from the charge");
		BigDecimal decreaseBy = new BigDecimal(10);
		PFAdjustChargeModal.setDollarAmount(new BigDecimal(10));
		PFAdjustChargeModal.typeReason(adjustReason);
		PFAdjustChargeModal.saveAdjustCharge();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotalByReason(adjustReason,
				price.multiply(new BigDecimal(quantity)).subtract(decreaseBy));
	}

	@Test(priority = 20)
	@TestCase(id = "RST-4364")
	public void testReservationAddInventoryItemCredit() {
		InventoryItem inventoryItem = createInventoryItem();
		PFFoliosDetails.navigatePaymentSection();
		PFFoliosDetails.clickAddACredit();
		POAddCreditModal.addCredit(inventoryItem.getName());
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(inventoryItem.getName(),
				new BigDecimal(0).subtract(inventoryItem.getDefaultPrice()));
	}

	@Test(priority = 30)
	@TestCase(id = "RST-4411")
	public void testReservationAddTransactionItemCredit() {
		TransactionItem item = new CreateItem().create(masterObject.getCategories().get(0),
				masterObject.getSubCategories().get(0), masterObject.getProperty());
		PFFoliosDetails.navigatePaymentSection();
		PFFoliosDetails.clickAddACredit();
		POAddCreditModal.addCredit(item.getName());
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(item.getName(),
				new BigDecimal(0).subtract(item.getDefaultPrice()));
	}

	@Test
	@TestCase(ids = { "RST-4365", "RST-331", "RST-4366","RST-332" })
	public void testReservationMakeCashPaymentToDefaultFolioAndRefund() {
		String folioName = "Folio 1";
		String paymentMethod = "Cash";
		BigDecimal paymentAmount = new BigDecimal(10);
		BigDecimal refundAmount = new BigDecimal(6);
		PFFoliosDetails.navigatePaymentSection();
		PFFoliosDetails.clickMakePayment();
		POPayments.makeCashPayment(folioName, paymentMethod, paymentAmount);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(paymentMethod, new BigDecimal(0).subtract(paymentAmount));

		PFReservationFoliosSection.clickFolioLineItem(paymentMethod);
		POPayments.refundPayment(refundAmount, null, RandomHelper.getRandomAlphaNumericString(20));
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickDefaultFolio();
		paymentAmount = paymentAmount.subtract(refundAmount);
		VerifyReservation.verifyFolioLineItemTotal(paymentMethod, new BigDecimal(0).subtract(paymentAmount));
	}

	@Test
	@TestCase(ids = { "RST-4367", "RST-4371" })
	public void testReservationMakePaymentWithExistingCardPaymentMethodAndRefund() {
		String folioName = "Folio 1";
		String paymentMethodName = "Visa 0011";
		String swipeData = "2ED01801F4F2800839B%*4761********0011^UAT USA/TEST CARD 01      ^2412***************************?*;4761********0011=2412****************?*B54354BCB250AFBAE14CD33CD0C61EA9F624A3A9D2CB2E6413ED5B96A7E9602F237EC4CCAE89663299CD3D3D8101D4EC27E2D2F026C2C136B25B59AC4B4218D2F3DBF2377036929C58DFB74B164B3273FADAFFBF70254BE69697A00A6177B4DBCE7078FBA25DA70D58F9A7E0A07D937E128382652311B65E0000000000000000000000000000000000000000000000000000000000000000000000000000000038303654343439353534FFFF9876540042E0011C5DB303";
		BigDecimal paymentAmount = new BigDecimal(20);
		BigDecimal refundAmount = new BigDecimal(20);

		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();
		paymentMethod.setPaymentMethod("New Credit Card");
		paymentMethod.setSwipeData(swipeData);
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		PFFoliosDetails.navigatePaymentSection();
		PFFoliosDetails.clickMakePayment();
		POPayments.makeCardPayment(folioName, paymentMethodName, paymentAmount);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(paymentMethodName, new BigDecimal(0).subtract(paymentAmount));


		paymentAmount = new BigDecimal(0).subtract(paymentAmount);
		PFReservationFoliosSection.clickFolioLineItem(paymentMethodName);

		sleep(1000);
		POPayments.refundPayment(refundAmount, paymentAmount, RandomHelper.getRandomAlphaNumericString(20));
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		paymentAmount = paymentAmount.add(refundAmount);
		PFReservationFoliosSection.clickDefaultFolio();
		VerifyReservation.verifyFolioLineItemTotal(paymentMethodName, paymentAmount);
	}

	@Test
	@TestCase(id = "RST-4370")
	public void testReservationMakePaymentWithNewCardToNonDefaultFolio() {
		String folioName = RandomHelper.getRandomAlphaString(6);
		String paymentMethodName = "Mastercard 4111";
		String swipeData = "2ED01801F4F2800839B%*5413********4111^UAT EUR/TEST CARD 07      ^2212***************************?*;5413********4111=2212****************?*2AEECABC007EB06826C9BAEAF8A81C6112EAA67990D20742EFDFC26CCA0A8A85C97CCC55CD232619EAC1C0B0D2E83CD5BA0E81DE95C2ED12D8D33912E9103B75693E8666D445AC1D0CBB1A4E7BFF3F8D228BCE3867AE2A90BD36D185E7AEBCC2355771121A5E5FD5C64DF83CB92BA7DBF98D0F14C40D0EE60000000000000000000000000000000000000000000000000000000000000000000000000000000037343254303930303934FFFF987654004300025C267003";
		BigDecimal paymentAmount = new BigDecimal(30);

		PFFoliosDetails.navigatePaymentSection();
		PFFoliosDetails.clickAddFolio();
		PFFoliosDetails.typeNewFolioName(folioName);
		PFFoliosDetails.saveNewFolio();
		PFReservationFoliosSection.clickFolioTab(folioName);
		Selenide.executeJavaScript("window.scrollBy(0,150);");
		PFFoliosDetails.clickMakePayment();
		POPayments.makeCardPayment(folioName, paymentMethodName, swipeData, paymentAmount);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFReservationFoliosSection.clickFolioTab(folioName);
		VerifyReservation.verifyFolioLineItemTotal(paymentMethodName, new BigDecimal(0).subtract(paymentAmount));
	}

	private InventoryItem createInventoryItem() {
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setName(RandomHelper.getRandomAlphaString(10));
		inventoryItem.setCode(RandomHelper.getRandomAlphaString(5));
		inventoryItem.setDefaultPrice(new BigDecimal(10));
		inventoryItem.setAvailableCount(50);
		inventoryItem.setCategoryId(masterObject.getCategories().get(0).getId());
		inventoryItem.setSubcategoryId(masterObject.getSubCategories().get(0).getId());
		Map<String, List<String>> sourceMealPeriods = new HashMap<>();
		sourceMealPeriods.put(masterObject.getBuilding().getId(), new ArrayList<String>());
		inventoryItem.setSourceMealPeriods(sourceMealPeriods);
		inventoryItem.setRecurringCharge(true);
		inventoryItem = inventoryItemClient.getProxy().createInventoryItem(tenantId, propertyId, inventoryItem);
		ThreadHelper.sleep(3000);
		return inventoryItem;
	}
}