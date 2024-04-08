package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.account.client.InventoryItemServiceClient;
import com.agilysys.pms.account.model.InventoryItem;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.data.models.RecurringCharge;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestBookReservationEstimatedCharges extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	String tenantId = null;
	String propertyId = null;
	BuilderBookReservation builderBookReservation = null;
	ReservationCreateRequest reservationCreateRequest = null;
	LocalDate propertyDate = null;
	
	InventoryItem inventoryItem = null;
	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty()
				.createBuilding()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createRoomTypes(1)
				.createRooms(5)				
				.createCategories(1)
				.createSubCategories(1)
				.createItems(3)				
				.createReservation(1)	
				.createTax()
				.create();
		
		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());

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
	}
	
	@Test
	@TestCase(ids = {"RST-4238","RST-12228"})
	public void testAddRecurringChargeTransactionItemEveryN(){
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(masterObject.getItems().get(0).getName(),price,quantity);
		recurringCharge.setEveryNFrequency("2");
		recurringCharges.add(recurringCharge);
		
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(), masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)		
			.addRecurringCharge(recurringCharges, null, null)
			.addPaymentMethod("Cash", null, null)
			.build();
		
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		
		VerifyReservation.verifyEstimatedChargesLineItemQuantity(propertyDate.plusDays(1), masterObject.getItems().get(0).getName(), quantity);
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate.plusDays(1), masterObject.getItems().get(0).getName(), price.multiply(new BigDecimal(quantity)));
		VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate, masterObject.getItems().get(0).getName());
	}
	
	@Test
	public void testAddRecurringChargeTransactionItemFirstNightOnly(){
		int quantity = 3;
		BigDecimal price = new BigDecimal(15);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(masterObject.getItems().get(0).getName(),price,quantity);
		recurringCharge.setFirstNightOnlyFrequency();
		recurringCharges.add(recurringCharge);
		
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(), masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)		
			.addRecurringCharge(recurringCharges, null, null)			
			.addPaymentMethod("Cash", null, null)
			.build();
		
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		
		VerifyReservation.verifyEstimatedChargesLineItemQuantity(propertyDate, masterObject.getItems().get(0).getName(), quantity);
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate, masterObject.getItems().get(0).getName(), price.multiply(new BigDecimal(quantity)));
		VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate.plusDays(1), masterObject.getItems().get(0).getName());
	}
	
	@Test
	@TestCase(id = "RST-8559")
	public void testAddRecurringChargeInventoryItemFirstNightOnly(){
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(inventoryItem.getName(),price, quantity);
		recurringCharge.setFirstNightOnlyFrequency();
		recurringCharges.add(recurringCharge);
		
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(), masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)		
			.addRecurringCharge(recurringCharges, null, null)
			.performCheckAvailability()
			.addPaymentMethod("Cash", null, null)
			.build();
		
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		
		VerifyReservation.verifyEstimatedChargesLineItemQuantity(propertyDate, inventoryItem.getName(), quantity);
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate, inventoryItem.getName(), price.multiply(new BigDecimal(quantity)));
		VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate.plusDays(1), inventoryItem.getName());
	}
	
	@Test
	@TestCase(ids = {"RST-8577", "RST-10055"})
	public void testAddRecurringChargeInventoryItemEveryN(){
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge recurringCharge = new RecurringCharge(inventoryItem.getName(),price,quantity);
		recurringCharge.setEveryNFrequency("2");
		recurringCharges.add(recurringCharge);
		
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(), masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)		
			.addRecurringCharge(recurringCharges, null, null)
			.performCheckAvailability()
			.addPaymentMethod("Cash", null, null)
			.build();
		
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		
		VerifyReservation.verifyEstimatedChargesLineItemQuantity(propertyDate.plusDays(1), inventoryItem.getName(), quantity);
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate.plusDays(1), inventoryItem.getName(), price.multiply(new BigDecimal(quantity)));
		VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate, inventoryItem.getName());
		
	}
	
	@Test
	@TestCase(id = "RST-8559")
	public void testAddRecurringChargeWithTransactionAndInventoryItem(){
		int quantity = 2;
		BigDecimal price = new BigDecimal(30);
		
		List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
		RecurringCharge inventoryCharge = new RecurringCharge(inventoryItem.getName(),price,quantity);
		inventoryCharge.setEveryNFrequency("2");
		recurringCharges.add(inventoryCharge);
		
		RecurringCharge transactionCharge = new RecurringCharge(masterObject.getItems().get(0).getName(),price,quantity);
		transactionCharge.setEveryNFrequency("2");
		recurringCharges.add(transactionCharge);
		
		builderBookReservation
			.setRandomPersonalDetails()
			.setRandomPhoneNumber()
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(), masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)		
			.addRecurringCharge(recurringCharges, null, null)				
			.addPaymentMethod("Cash", null, null)
			.build();
		
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		
		VerifyReservation.verifyEstimatedChargesLineItemQuantity(propertyDate.plusDays(1), inventoryItem.getName(), quantity);
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate.plusDays(1), inventoryItem.getName(), price.multiply(new BigDecimal(quantity)));
		VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate, inventoryItem.getName());
		
		VerifyReservation.verifyEstimatedChargesLineItemQuantity(propertyDate.plusDays(1), masterObject.getItems().get(0).getName(), quantity);
		VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate.plusDays(1), masterObject.getItems().get(0).getName(), price.multiply(new BigDecimal(quantity)));
		VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate, inventoryItem.getName());
	}
}