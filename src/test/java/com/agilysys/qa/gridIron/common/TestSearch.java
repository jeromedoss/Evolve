package com.agilysys.qa.gridIron.common;

import com.agilysys.insertanator.adjustments.reservations.AdjustReservation;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.profile.model.*;
import com.agilysys.pms.profile.model.v1.GuestProfile;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.locators.home.ProfileTile;
import com.agilysys.qa.gridIron.constants.locators.home.RoomTile;
import com.agilysys.qa.gridIron.constants.locators.profiles.company.LocatorsCompanyProfilePage;
import com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage;
import com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsEmailDetails;
import com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFProfileTile;
import com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFRecommededUpgradesAndOtherMatchingRoom;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationSummary;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.profiles.company.POCompanyProfilePage;
import com.agilysys.qa.gridIron.pageobject.profiles.guest.POGuestProfilePage;
import com.agilysys.qa.gridIron.utils.ClientPropertyDate;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.ValidationBase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import junit.framework.Assert;
import org.joda.time.LocalDate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_LASTNAME;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.roomtile.PFRoomTile.clickCheckbuttonOCC;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.roomtile.PFRoomTile.clickTabRooms;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.*;

/*
 * *Author - Harish Baskaran - Aug/2018
 */


public class TestSearch extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	static String reservationProfileLastName = null;
	static String reservationProfileFirstName = null;
	static String expected = null;
	static String RoomNo = null;
	static String confirmationNumber = null;

	GuestProfile searchGuestProfile = null;
	CompanySummary searchCompanySummary = null;
	
	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createProperty("StayAutomation " + tenant.getTenantCode(),
				tenant.getTenantCode(), propertycode, propertycode).createRoomTypes(1).createRooms(3)
				.createReservation(1).create();

		new LoginApplication().Login(masterObject);
		
		//Add Guest profile to search
		GuestProfile guestProfile = new GuestProfile();

		PersonalDetails personalDetails = new PersonalDetails();
		personalDetails.setLastName(RandomHelper.getRandomAlphaNumericString(10));
		personalDetails.setFirstName(RandomHelper.getRandomAlphaNumericString(10));
		
		Phone phone = new Phone();
		phone.setPhoneType("Business");
		phone.setNumber(RandomHelper.getRandomAlphaNumericString(10));
		phone.setExtension(RandomHelper.getRandomAlphaNumericString(5));
		phone.setIsPrivate(true);
		PhoneDetails phoneDetails = new PhoneDetails();
		List<Phone> phones = new ArrayList<Phone>();
		phones.add(phone);
		phoneDetails.setPhones(phones);

		Email email = new Email();
		email.setEmailAddress(RandomHelper.getRandomAlphaNumericString(10));
		email.setType("Home");
		email.setIsPrivate(true);
		EmailDetails emailDetails = new EmailDetails();
		List<Email> emails = new ArrayList<Email>();
		emails.add(email);
		emailDetails.setEmailAddresses(emails);

		guestProfile.setPersonalDetails(personalDetails);		
		guestProfile.setPhoneDetails(phoneDetails);
		guestProfile.setEmailDetails(emailDetails);
		

		searchGuestProfile = guestProfile;
		POGuestProfilePage.addGuest(guestProfile, null, null, null);
		PFGuestProfilePage.ClickSavebutton();
		LocalDate propertyDate =new ClientPropertyDate().getPropertyDate(masterObject.getProperty().getTenantId(),
				masterObject.getProperty().getPropertyId());
		ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		reservationCreateRequest.setWalkIn(true);
		BuilderBookReservation builderBookReservation = new BuilderBookReservation(reservationCreateRequest)
				.setGuestSearchString(searchGuestProfile.getPersonalDetails().getLastName())
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0), masterObject.getRoomTypes().get(0), propertyDate)
				.addPaymentMethod("Cash", null, null)
				.build();

		confirmationNumber = PFReservationSummary.getReservationConfirmationNumber();

		RoomNo = PFRecommededUpgradesAndOtherMatchingRoom.getOtherMatchingRoomNumber(1);
		PFRecommededUpgradesAndOtherMatchingRoom.clickOtherMatchingRow(1);
		
		reservationProfileLastName = searchGuestProfile.getPersonalDetails().getLastName();
		reservationProfileFirstName = searchGuestProfile.getPersonalDetails().getFirstName();

		expected = reservationProfileLastName + ", " + reservationProfileFirstName;
		
		//Create Company Profile		
		searchCompanySummary = POCompanyProfilePage.createCompanyProfile();
	}

	@AfterClass
	public void AfterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {
		open(Endpoints.getBasePMSUrl() + "#/search/profiles?tenantId=" + masterObject.getProperty().getTenantId() + "&propertyId=" + masterObject.getProperty().getPropertyId());
		PFSearchTile.waitForSearchPageToLoad();
	}



	@TestCase(id = "RST-5113")
	@Test
	public void testSearchRoomByFullName() {
		Selenide.refresh();
		PFSearchTile.waitForSearchPageToLoad();
		clickTabRooms();
		clickCheckbuttonOCC();
		new POSearchInMainPage().search(reservationProfileLastName + ", " + reservationProfileFirstName);

		retryRoomSearchIfApplicable(RoomNo);
		$$(RoomTile.LIST_ROOM_NUMBERS).findBy(Condition.text(RoomNo)).shouldBe(Condition.exist);

	}

	@TestCase(id = "RST-12881")
	@Test
	public void testSearchRoomByLastName() {
		Selenide.refresh();
		PFSearchTile.waitForSearchPageToLoad();
		clickTabRooms();
		clickCheckbuttonOCC();
		new POSearchInMainPage().search(reservationProfileLastName);

		retryRoomSearchIfApplicable(RoomNo);
		$$(RoomTile.LIST_ROOM_NUMBERS).findBy(Condition.text(RoomNo)).shouldBe(Condition.exist);

	}

	@TestCase(id = "RST-12880")
	@Test
	public void testSearchRoomByFirstName() {
		Selenide.refresh();
		PFSearchTile.waitForSearchPageToLoad();
		clickTabRooms();
		clickCheckbuttonOCC();
		new POSearchInMainPage().search(reservationProfileFirstName);
		retryRoomSearchIfApplicable(RoomNo);
		$$(RoomTile.LIST_ROOM_NUMBERS).findBy(Condition.text(RoomNo)).shouldBe(Condition.exist);
	}

	@TestCase(id = "RST-5115")
	@Test
	public void testSearchRoomByRoomNumber() {
		Selenide.refresh();
		PFSearchTile.waitForSearchPageToLoad();
		clickTabRooms();
		clickCheckbuttonOCC();
		new POSearchInMainPage().search(RoomNo);
		retryRoomSearchIfApplicable(RoomNo);
		$$(RoomTile.LIST_ROOM_NUMBERS).findBy(Condition.text(RoomNo)).shouldBe(Condition.exist);

	}

	@TestCase(id="RST-1971")
	@Test
	public void testSearchGuestProfileByPartialLastName() {
		PFProfileTile.clickTabProfiles();
		String lastName = searchGuestProfile.getPersonalDetails().getLastName();
		POSearchInMainPage.goToGuestProfile(lastName.substring(0, lastName.length() - 2));
		Assert.assertEquals(lastName, $(LocatorsGuestProfilePage.INPUT_LASTNAME).getValue());
	}

	@TestCase(id="RST-1969")
	@Test
	public void testSearchGuestProfileByFullLastName() {
		PFProfileTile.clickTabProfiles();
		String lastName = searchGuestProfile.getPersonalDetails().getLastName();
		POSearchInMainPage.goToGuestProfile(lastName);
		Assert.assertEquals(lastName, $(LocatorsGuestProfilePage.INPUT_LASTNAME).getValue());
	}
	

	@TestCase(id="RST-1970")
	@Test
	public void testSearchGuestProfileByPartialFirstName() {
		PFProfileTile.clickTabProfiles();
		String firstName = searchGuestProfile.getPersonalDetails().getFirstName();
		POSearchInMainPage.goToGuestProfile(firstName.substring(0, firstName.length() - 2));
		Assert.assertEquals(firstName, $(LocatorsGuestProfilePage.INPUT_FIRSTNAME).getValue());
	}

	@TestCase(id="RST-120")
	@Test
	public void testSearchGuestProfileWByFullFirstName() {
		PFProfileTile.clickTabProfiles();
		String firstName = searchGuestProfile.getPersonalDetails().getFirstName();
		POSearchInMainPage.goToGuestProfile(firstName);
		Assert.assertEquals(firstName, $(LocatorsGuestProfilePage.INPUT_FIRSTNAME).getValue());
	}

	@TestCase(id="RST-1972")
	@Test
	public void testSearchGuestProfileByFullName() {
		PFProfileTile.clickTabProfiles();
		String lastname = searchGuestProfile.getPersonalDetails().getLastName();
		String firstName = searchGuestProfile.getPersonalDetails().getFirstName();
		POSearchInMainPage.searchForGuestProfile(firstName + " " + lastname);
		click($$(ProfileTile.LIST_GUESTS).findBy(Condition.text(searchGuestProfile.getPersonalDetails().getLastName())));

		$(INPUT_LASTNAME).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		Assert.assertEquals(firstName, $(LocatorsGuestProfilePage.INPUT_FIRSTNAME).getValue());
		Assert.assertEquals(lastname, $(LocatorsGuestProfilePage.INPUT_LASTNAME).getValue());
	}

	@TestCase(id="RST-1921")
	@Test
	public void testSearchGuestProfileByPhoneNumber() {
		PFProfileTile.clickTabProfiles();
		String phoneNumber = searchGuestProfile.getPhoneDetails().getPhones().get(0).getNumber();
		POSearchInMainPage.searchForGuestProfile(phoneNumber);
		click($$(ProfileTile.LIST_GUESTS).findBy(Condition.text(searchGuestProfile.getPersonalDetails().getLastName())));
		$(INPUT_LASTNAME).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		Assert.assertEquals(phoneNumber, $(LocatorsPhoneNumber.INPUT_PHONE_NUMBER).getValue());
	}

	@TestCase(id="RST-1922")
	@Test
	public void testSearchGuestProfileByEmail() {
		PFProfileTile.clickTabProfiles();
		String emailAddress = searchGuestProfile.getEmailDetails().getEmailAddresses().get(0).getEmailAddress();
		POSearchInMainPage.searchForGuestProfile(emailAddress);
		click($$(ProfileTile.LIST_GUESTS).findBy(Condition.text(searchGuestProfile.getPersonalDetails().getLastName())));
		$(INPUT_LASTNAME).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		Assert.assertEquals(emailAddress, $(LocatorsEmailDetails.INPUT_EMAIL_ADDRESS).getValue());
	}
	
	@Test
	@TestCase(id = "RST-1978")	
	public void testSearchCompanyProfileByCompanyName()
	{
		POSearchInMainPage.goToCompanyProfile(searchCompanySummary.getCompanyName());
		ValidationBase.verifyElementValue(searchCompanySummary.getCompanyName(), LocatorsCompanyProfilePage.INPUT_NAME);		
	}
	@Test
	@TestCase(id = "RST-1980")	
	public void testSearchCompanyProfileByCompanyCode()
	{
		POSearchInMainPage.searchForCompanyProfile(searchCompanySummary.getCompanyCode());
		click($$(ProfileTile.LIST_COMPANIES).findBy(Condition.text(searchCompanySummary.getCompanyName())));
		ValidationBase.verifyElementValue(searchCompanySummary.getCompanyCode(), LocatorsCompanyProfilePage.INPUT_CODE);
	}

	
	private void retryRoomSearchIfApplicable(String roomNumber){
		int time = 0;
		Selenide.sleep(Configuration.timeout);
		while(time < Configuration.timeout){
			if($$(RoomTile.LIST_ROOM_NUMBERS).filter(Condition.text(roomNumber)).size()>0){
				return;
			}
			PFSearchTile.clickButtonGo();
			Selenide.sleep(60000);			
			time = time + 60000;
		}
		
	}
}
