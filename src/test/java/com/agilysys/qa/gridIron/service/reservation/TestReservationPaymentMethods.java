package com.agilysys.qa.gridIron.service.reservation;

import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.profile.model.GuestType;
import com.agilysys.pms.profile.model.MarketSegment;
import com.agilysys.pms.rates.model.RatePlanDetail;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.common.model.TrackingInfo;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.constants.locators.reservation.payments.LocatorsPaymentsSection;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFPaymentMethodsSection;
import com.agilysys.qa.gridIron.data.models.ReservationPaymentMethod;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationPaymentMethods;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.PMSHelper;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.ValidationBase;
import com.agilysys.qa.gridIron.validations.VerifyGuestProfile;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.agilysys.qa.constants.EndPoints;

import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestReservationPaymentMethods extends StayUIWrappers {

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
		reservationCreateRequest.setWalkIn(true);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
	}

	@Test
	@TestCase(id="RST-4204")
	public void testReservationAddCreditCardPaymentMethod() {		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		
		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();		
		paymentMethod.setPaymentMethod("New Credit Card");
		paymentMethod.setSwipeData(swipeData);
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPaymentMethod("Visa 0011", false);
	}

	@Test
	@TestCase(ids= {"RST-4212","RST-4165"})
	public void testReservationAddDirectBillPaymentMethod() {		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		
		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();		
		paymentMethod.setPaymentMethod("Direct Bill");
		paymentMethod.setDirectBillAccountName(directBillAccountName);
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFPaymentMethodsSection.clickPaymentMethod(directBillAccountName);
		VerifyReservation.verifyPaymentMethod(directBillAccountName, false);
		ValidationBase.verifyElementText(directBillAccountName, LocatorsPaymentsSection.LINK_DIRECTBILL_ACCOUNT);
		ValidationBase.verifyElementText("Third Party Folio", LocatorsPaymentsSection.LINK_FOLIOS);
		ValidationBase.verifyCheckBox(true, LocatorsPaymentsSection.CHECKBOX_DO_NOT_DISCLOSE_RATES);
		ValidationBase.verifyCheckBox(true, LocatorsPaymentsSection.CHECKBOX_THIRD_PARTY_PAYMENT);
		
		
	}
	
	@Test
	@TestCase(id="RST-4200")
	public void testReservationAddCheckPaymentMethod() {		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		
		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();		
		paymentMethod.setPaymentMethod("Check");		
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFPaymentMethodsSection.clickPaymentMethod("Check");
		VerifyReservation.verifyPaymentMethod("Check", false);		
		ValidationBase.verifyElementText("None", LocatorsPaymentsSection.LINK_FOLIOS);
		
	}

	@Test
	public void testReservationSavePaymentMethodToGuestAsDefault() {		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		
		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();
		paymentMethod.setSaveToGuestProfile(true);
		paymentMethod.setGuestDefault(true);		
		paymentMethod.setPaymentMethod("Check");		
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFPaymentMethodsSection.clickPaymentMethod("Check");
		VerifyReservation.verifyPaymentMethod("Check", false);
		POSearchInMainPage.goToGuestProfile(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyGuestProfile.verifyPaymentMethod("Check", true);
	}

	@Test
	public void testReservationSavePaymentMethodToReservationAsDefault() {		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		
		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();
		paymentMethod.setPrimaryMethod(true);
		paymentMethod.setAssociateToDefaultFolio(true);				
		paymentMethod.setPaymentMethod("Check");		
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFPaymentMethodsSection.clickPaymentMethod("Check");
		VerifyReservation.verifyPaymentMethod("Check", true);	
		ValidationBase.verifyElementText("Folio 1 - Default", LocatorsPaymentsSection.LINK_FOLIOS);
	}

	@Test
	public void testReservationSaveAsThirdPaymentMethod() {		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		
		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();
		paymentMethod.setThirdParty(true);
		paymentMethod.setDoNotDiscloseRates(true);						
		paymentMethod.setPaymentMethod("Check");		
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFPaymentMethodsSection.clickPaymentMethod("Check");
		VerifyReservation.verifyPaymentMethod("Check", false);	
		ValidationBase.verifyCheckBox(true, LocatorsPaymentsSection.CHECKBOX_THIRD_PARTY_PAYMENT);
		ValidationBase.verifyCheckBox(true, LocatorsPaymentsSection.CHECKBOX_DO_NOT_DISCLOSE_RATES);
	}
	
	@Test
	public void testReservationSaveDoNotDisClosePaymentMethod() {		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		
		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();		
		paymentMethod.setDoNotDiscloseRates(true);
		paymentMethod.setPaymentMethod("Check");		
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFPaymentMethodsSection.clickPaymentMethod("Check");
		VerifyReservation.verifyPaymentMethod("Check", false);	
		ValidationBase.verifyCheckBox(false, LocatorsPaymentsSection.CHECKBOX_THIRD_PARTY_PAYMENT);
		ValidationBase.verifyCheckBox(true, LocatorsPaymentsSection.CHECKBOX_DO_NOT_DISCLOSE_RATES);
	}
	
	@Test
	@TestCase(id="RST-4225")
	public void testReservationDeletePaymentMethod() {		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		
		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();
		paymentMethod.setPaymentMethod("Check");		
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFPaymentMethodsSection.clickPaymentMethod("Check");
		PFPaymentMethodsSection.clickDeletePaymentMethod();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPaymentMethod("Cash", true);
		ValidationBase.verifyElementNotPresent(LocatorsPaymentsSection.getPaymentMethodByName("Check"));		
	}
	
	@Test
	@TestCase(id="RST-4227")
	public void testReservationUpdatePaymentMethodSetAsDefault() {		
		builderBookReservation
				.setRandomPersonalDetails()
				.setRandomPhoneNumber()
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)				
				.addPaymentMethod("Cash", null, null)
				.build();
		
		ReservationPaymentMethod paymentMethod = new ReservationPaymentMethod();
		paymentMethod.setPaymentMethod("Check");		
		POReservationPaymentMethods.addPaymentMethod(paymentMethod);
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PFPaymentMethodsSection.clickPaymentMethod("Check");
		PFPaymentMethodsSection.clickMakeDefault();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		VerifyReservation.verifyPaymentMethod("Check", true);
	}
}