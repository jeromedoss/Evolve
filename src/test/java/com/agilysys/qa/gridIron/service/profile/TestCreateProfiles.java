package com.agilysys.qa.gridIron.service.profile;

import com.agilysys.qa.gridIron.constants.locators.housekeeping.setup.LocatorsStaffSetupSection;
import com.agilysys.qa.gridIron.constants.locators.profiles.agent.LocatorsAgentProfilePage;
import com.agilysys.qa.gridIron.constants.locators.reservation.loyalty.LocatorsLoyaltySection;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.profile.model.*;
import com.agilysys.pms.profile.model.v1.GuestProfile;
import com.agilysys.pms.profile.model.v1.GuestProfileCreateRequest;
import com.agilysys.qa.client.profile.guest.type.ClientGuestTypeCreate;
import com.agilysys.qa.client.profile.guest.type.ClientGuestTypeUpdate;
import com.agilysys.qa.client.profile.market.segment.ClientMarketSegmentCreate;
import com.agilysys.qa.data.builder.profile.guest.type.BuilderGuestType;
import com.agilysys.qa.data.builder.profile.market.segment.BuilderMarketSegment;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.profiles.agent.CreateAgentProfile;
import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.constants.locators.home.ProfileTile;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.profiles.guest.POGuestProfilePage;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.PMSHelper;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import com.codeborne.selenide.commands.Click;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFProfileTile.*;
import static com.codeborne.selenide.Selenide.*;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestCreateProfiles extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	static String agentName = null;
	static String IATA = null;

	static String companyName = null;
	static String companyCode = null;

	String tenantId = null;
	String propertyId = null;

	MarketSegment marketSegment = null;
	GuestType guestType = null;

	GuestProfileCreateRequest guestProfileCreateRequest = null;
	
	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createProperty("StayAutomation " + tenant.getTenantCode(),
				tenant.getTenantCode(), propertycode, propertycode).createGuests(1).createPaymentMethodAllCC().
				createLoyaltyPrograms(1).create();

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

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		new PMSHelper().enableIDTech(masterObject.getProperty());
		new LoginApplication().Login(masterObject);
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() {
		Selenide.refresh();
		open(Endpoints.getBasePMSUrl() + "#/search/profiles?tenantId=" + tenantId + "&propertyId=" + propertyId);
		PFSearchTile.waitForSearchPageToLoad();
		click(ProfileTile.TAB_PROFILES);

	}

	@TestCase(ids = {"RST-3726","RST-3723"})
	@Test
	public void testCreateGuestProfileWithPhoneNo() {
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

		guestProfile.setPersonalDetails(personalDetails);
		guestProfile.setPhoneDetails(phoneDetails);

		POGuestProfilePage.addGuest(guestProfile, null, null, null);
		PFGuestProfilePage.ClickSavebutton();

		POSearchInMainPage.goToGuestProfile(guestProfile.getPersonalDetails().getLastName());

		// new
		// ValidateGuestProfile().activateFirstname().activateLastName().activatePhoneNo()
		// .Validate(guestProfileCreateRequest);
	}

	@TestCase(ids = {"RST-108","RST-132","RST-110"})
	@Test
	public void testCreateGuestProfileWithAddress() {
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

		Address address = new Address();
		address.setAddressType("Office");
		address.setAddressLine1(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine2(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine3(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine4(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine5(RandomHelper.getRandomAlphaNumericString(20));
		address.setCityName(RandomHelper.getRandomAlphaNumericString(20));
		address.setStateProvinceCode(RandomHelper.getRandomAlphaNumericString(20));
		address.setPostalCode("adfafd");
		address.setCountyCode("Albania");
		address.setCountryCode("Albania");
		address.setIsDefault(false);
		address.setIsPrivateAddress(true);

		AddressDetails addressDetails = new AddressDetails();
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		addressDetails.setAddresses(addresses);

		guestProfile.setPersonalDetails(personalDetails);
		guestProfile.setPhoneDetails(phoneDetails);
		guestProfile.setAddressDetails(addressDetails);

		POGuestProfilePage.addGuest(guestProfile, null, null, null);
		PFGuestProfilePage.ClickSavebutton();

		POSearchInMainPage.goToGuestProfile(guestProfile.getPersonalDetails().getLastName());
		// Validation Need to be implemented
	}

	@TestCase(ids = {"RST-109", "RST-3728","RST-7265","RST-7269"})
	@Test
	public void testCreateGuestProfileWithEmailAndLoyalty() {
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

		POGuestProfilePage.addGuest(guestProfile, null, null, null);
		PFGuestProfilePage.ClickSavebutton();

		POSearchInMainPage.goToGuestProfile(guestProfile.getPersonalDetails().getLastName());
		// Validation Need to be implemented

		String loyaltyName = masterObject.getLoyaltyPrograms().get(0).getName();

		click(LocatorsLoyaltySection.LOYALTY_HEADER);
		click(LocatorsLoyaltySection.LINK_ADD_LOYALTY);

		click(LocatorsLoyaltySection.DROPDOWN_LOYALTY_PROGRAMS);
		click($$(LocatorsLoyaltySection.DROPDOWN_LIST_LOYALTY_PROGRAMS).findBy(Condition.text(loyaltyName)));

		$(LocatorsLoyaltySection.INPUT_MEMBER_ID).setValue("123");
		click(LocatorsAgentProfilePage.BUTTON_SAVE_EXIT);

	}

	@TestCase(id = "RST-234")
	@Test
	public void testCreateGuestProfileWithMarketingDetails() {
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

		guestProfile.setPersonalDetails(personalDetails);
		guestProfile.setPhoneDetails(phoneDetails);

		POGuestProfilePage.addGuest(guestProfile, "3rd Party", guestType.getName(), marketSegment.getName());
		PFGuestProfilePage.ClickSavebutton();

		POSearchInMainPage.goToGuestProfile(guestProfile.getPersonalDetails().getLastName());
		// Validation Need to be implemented
	}

	@TestCase(ids = {"RST-236","RST-3702"})
	@Test
	public void testCreateGuestProfileWithAllFields() {
		GuestProfile guestProfile = new GuestProfile();

		PersonalDetails personalDetails = new PersonalDetails();
		personalDetails.setLastName(RandomHelper.getRandomAlphaNumericString(10));
		personalDetails.setFirstName(RandomHelper.getRandomAlphaNumericString(10));
		personalDetails.setGender("Male");
		personalDetails.setMiddleInitial(RandomHelper.getRandomAlphaNumericString(1));
		personalDetails.setBirthDate(new LocalDate("1991-04-28"));
		personalDetails.setSuffix("Sr.");
		personalDetails.setAnniversary(new LocalDate("2010-04-28"));
		personalDetails.setTitle("Mr.");
		personalDetails.setCompanyName(RandomHelper.getRandomAlphaString(10));
		personalDetails.setAlias(RandomHelper.getRandomAlphaNumericString(10));
		personalDetails.setCompanyTitle(RandomHelper.getRandomAlphaString(10));
		personalDetails.setNamePronunciation(RandomHelper.getRandomAlphaString(10));
		personalDetails.setLanguage("English (en)");

		Address address = new Address();
		address.setAddressType("Office");
		address.setAddressLine1(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine2(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine3(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine4(RandomHelper.getRandomAlphaNumericString(20));
		address.setAddressLine5(RandomHelper.getRandomAlphaNumericString(20));
		address.setCityName(RandomHelper.getRandomAlphaNumericString(20));
		address.setStateProvinceCode(RandomHelper.getRandomAlphaNumericString(20));
		address.setPostalCode("adfafd");
		address.setCountyCode("Albania");
		address.setCountryCode("Albania");
		address.setIsDefault(false);
		address.setIsPrivateAddress(true);
		AddressDetails addressDetails = new AddressDetails();
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		addressDetails.setAddresses(addresses);

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

		Identity identity = new Identity();
		identity.setIdNumber(RandomHelper.getRandomAlphaNumericString(10));
		identity.setType("Driver License");
		identity.setIssuingAgency(RandomHelper.getRandomAlphaNumericString(10));
		identity.setExpirationDate(new LocalDate("2020-04-28"));
		IdentityDetails identityDetails = new IdentityDetails();
		List<Identity> identities = new ArrayList<Identity>();
		identities.add(identity);
		identityDetails.setIdentities(identities);

		guestProfile.setPersonalDetails(personalDetails);
		guestProfile.setAddressDetails(addressDetails);
		guestProfile.setPhoneDetails(phoneDetails);
		guestProfile.setEmailDetails(emailDetails);
		guestProfile.setIdentityDetails(identityDetails);

		POGuestProfilePage.addGuest(guestProfile, "3rd Party", guestType.getName(), marketSegment.getName());
		PFGuestProfilePage.ClickSavebutton();

		POSearchInMainPage.goToGuestProfile(guestProfile.getPersonalDetails().getLastName());
		// Validation Need to be implemented
	}

	@TestCase(id = "RST-3775")
	@Test
	public void testCreateGuestWithComment() {
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

		guestProfile.setPersonalDetails(personalDetails);
		guestProfile.setPhoneDetails(phoneDetails);

		POGuestProfilePage.addGuest(guestProfile, null, null, null);
		PFGuestProfilePage.ClickSavebutton();
		POGuestProfilePage.addComment("Test Comment", "Booking", "Low");
		POSearchInMainPage.goToGuestProfile(guestProfile.getPersonalDetails().getLastName());
	}

	@TestCase(ids = {"RST-242", "RST-264"})
	@Test
	public void testCreateGuestProfileWithAllPaymentMethods() {

		String arName = RandomHelper.getRandomAlphaNumericString(6);
		String arCode = RandomHelper.getRandomAlphaNumericString(6);
		new CreateCompanyProfile(arName, arCode).flowCreateWithContactsARUsingSaveExit();
		PFSearchTile.waitForSearchPageToLoad();
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

		guestProfile.setPersonalDetails(personalDetails);
		guestProfile.setPhoneDetails(phoneDetails);

		POGuestProfilePage.addGuest(guestProfile, null, null, null);
		PFGuestProfilePage.ClickSavebutton();

		POGuestProfilePage.addPaymentMethod("Cash", true, null, null);
		String swipeData = "2C100C037001C078692;4761********0010=2212:***?*098745=ACF828DC27C1BB4E1DAA43F06A25BDD1B661394C0BFA22248608AE55765ECA2D000000000000000000000000000000000000000037343254303930303934FFFF987654004300025A453D03";
		POGuestProfilePage.addPaymentMethod("New Credit Card", true, null, swipeData);
		click(By.xpath("//button[@data-qa-id=\"im9VDJY\"]"));
		POGuestProfilePage.addPaymentMethod("Direct Bill", false, arName, null);
		click(By.xpath("//button[@data-qa-id=\"im9VDJY\"]"));
		POSearchInMainPage.goToGuestProfile(guestProfile.getPersonalDetails().getLastName());
		// Validation Need to be implemented
	}

	@TestCase(id = "RST-3773")
	@Test
	public void testCreateGuestProfileWithIdentity() {

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

		Identity identity = new Identity();
		identity.setIdNumber(RandomHelper.getRandomAlphaNumericString(10));
		identity.setType("Driver License");
		identity.setIssuingAgency(RandomHelper.getRandomAlphaNumericString(10));
		identity.setExpirationDate(new LocalDate("2020-04-28"));
		IdentityDetails identityDetails = new IdentityDetails();
		List<Identity> identities = new ArrayList<Identity>();
		identities.add(identity);
		identityDetails.setIdentities(identities);

		guestProfile.setPersonalDetails(personalDetails);
		guestProfile.setPhoneDetails(phoneDetails);
		guestProfile.setIdentityDetails(identityDetails);

		POGuestProfilePage.addGuest(guestProfile, null, null, null);
		PFGuestProfilePage.ClickSavebutton();

		POSearchInMainPage.goToGuestProfile(guestProfile.getPersonalDetails().getLastName());
		// Validation need to be implemented
	}

	@TestCase(id = "RST-5107")
	@Test
	public void testCreateTAProfileWithoutIATA() {

		agentName = "Agent " + RandomHelper.getRandomAlphaString(5);

		new CreateAgentProfile(agentName).flowCreateUsingSaveExit();

		clickTabProfiles();
		clickRadioAgent();
		new POSearchInMainPage().search(agentName);

		$$(ProfileTile.LIST_GUESTS).findBy(Condition.text(agentName)).shouldBe(Condition.exist);

	}

	@TestCase(id = "RST-5105")
	@Test
	public void testCreateProfileSearchIata() {
		IATA = RandomHelper.getRandomNumericString(8);
		agentName = "Agent " + RandomHelper.getRandomAlphaString(5);

		new CreateAgentProfile(agentName).flowCreateUsingSaveExitWithIATA(IATA);

		clickTabProfiles();
		clickRadioAgent();
		new POSearchInMainPage().search(IATA);

		$$(ProfileTile.LIST_GUESTS).findBy(Condition.text(agentName)).shouldBe(Condition.exist);

	}

	@TestCase(id = "RST-98")
	@Test
	public void testCreateCompanyProfileWithoutAR() {
		companyName = "Company " + RandomHelper.getRandomAlphaString(5);
		companyCode = RandomHelper.getRandomAlphaString(6);

		new CreateCompanyProfile(companyName, companyCode).flowCreateWithoutARUsingSaveExit();

		clickTabProfiles();
		clickRadioCompany();
		new POSearchInMainPage().search(companyName);
		$$(ProfileTile.LIST_GUESTS).findBy(Condition.text(companyName)).shouldBe(Condition.exist);
	}


}