package com.agilysys.qa.gridIron.service.profile;

import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.profile.model.*;
import com.agilysys.qa.client.profile.guest.type.ClientGuestTypeCreate;
import com.agilysys.qa.client.profile.guest.type.ClientGuestTypeUpdate;
import com.agilysys.qa.client.profile.market.segment.ClientMarketSegmentCreate;
import com.agilysys.qa.data.builder.profile.guest.type.BuilderGuestType;
import com.agilysys.qa.data.builder.profile.market.segment.BuilderMarketSegment;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.locators.home.ProfileTile;
import com.agilysys.qa.gridIron.constants.locators.profiles.company.LocatorsCompanyProfilePage;
import com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.summary.LocatorsSummarySection;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.pageobject.home.profiletile.CreateNewProfile;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.profiles.company.POCompanyProfilePage;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.HelperMethods;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.ValidationBase;
import com.agilysys.qa.gridIron.validations.VerifyCompanyProfile;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.PFCompanyProfilePage.clickTabAR;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.*;


/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestCreateCompanyProfile extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	String tenantId = null;
	String propertyId = null;

	MarketSegment marketSegment = null;
	GuestType guestType = null;	

	Email searchemail;
	

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createProperty("StayAutomation " + tenant.getTenantCode(),
				tenant.getTenantCode(), propertycode, propertycode).createGuests(1).createPaymentMethodAllCC().create();

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

		new LoginApplication().Login(masterObject);
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() {		
		open(Endpoints.getBasePMSUrl() + "#/search/profiles?tenantId=" + tenantId + "&propertyId=" + propertyId);
		PFSearchTile.waitForSearchPageToLoad();		
		CreateNewProfile.stepClickCreateCompanyFromMain();
		
	}
	
	@Test
	@TestCase(ids = {"RST-3796","RST-48"})
	public void testCreateCompanyProfilewithSummary(){		
		CompanySummary companySummary = new CompanySummary();
		companySummary.setCompanyName(RandomHelper.getRandomAlphaNumericString(10));
		companySummary.setCompanyCode(RandomHelper.getRandomAlphaNumericString(4));
		companySummary.setPronounced(RandomHelper.getRandomAlphaNumericString(5));
		companySummary.setCompanyWebsite("http://"+RandomHelper.getRandomAlphaNumericString(7)+".com");
		companySummary.setNegotiatedRatePlanId("BAR");
		
		POCompanyProfilePage.setCompanySummary(companySummary);
		POCompanyProfilePage.saveCompanyProfile();
		
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		VerifyCompanyProfile.verifyCompanySummary(companySummary);		
	}
	
	@TestCase(ids = {"RST-151","RST-3687"})
	@Test
	public void testCreateCompanyProfilewithMarketingDetails()
	{
		CompanySummary companySummary = new CompanySummary();
		companySummary.setCompanyName(RandomHelper.getRandomAlphaNumericString(10));
		companySummary.setCompanyCode(RandomHelper.getRandomAlphaNumericString(4));
		companySummary.setPronounced(RandomHelper.getRandomAlphaNumericString(5));
		companySummary.setCompanyWebsite("http://"+RandomHelper.getRandomAlphaNumericString(7)+".com");
		companySummary.setNegotiatedRatePlanId("BAR");
		
		POCompanyProfilePage.setCompanySummary(companySummary);		
		POCompanyProfilePage.setmarketingdetails(guestType.getName(), "Corporate Preferred", marketSegment.getName());
		POCompanyProfilePage.saveCompanyProfile();
		
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		VerifyCompanyProfile.verifyMarketing(guestType.getName(), "Corporate Preferred", marketSegment.getName());
		
	}
	
	@TestCase(id = "RST-144")
	@Test
	public void testCreateCompanyProfilewithAddress()
	{
		CompanySummary companySummary = new CompanySummary();
		companySummary.setCompanyName(RandomHelper.getRandomAlphaNumericString(10));
		companySummary.setCompanyCode(RandomHelper.getRandomAlphaNumericString(4));
		companySummary.setPronounced(RandomHelper.getRandomAlphaNumericString(5));
		companySummary.setCompanyWebsite("http://www."+RandomHelper.getRandomAlphaNumericString(7)+".com");
		companySummary.setNegotiatedRatePlanId("BAR");
		
		Address address = new Address();
		address.setAddressType("Office");
		address.setAddressLine1(RandomHelper.getRandomAlphaNumericString(10));
		address.setAddressLine2(RandomHelper.getRandomAlphaNumericString(10));
		address.setAddressLine3(RandomHelper.getRandomAlphaNumericString(10));
		address.setAddressLine4(RandomHelper.getRandomAlphaNumericString(10));
		address.setAddressLine5(RandomHelper.getRandomAlphaNumericString(10));
		address.setCityName(RandomHelper.getRandomAlphaString(5));
		address.setStateProvinceCode(RandomHelper.getRandomAlphaString(2));
		address.setPostalCode(RandomHelper.getRandomNumericString(6));
		address.setCountryCode("India");
		address.setCountyCode(RandomHelper.getRandomAlphaString(2));
		address.setIsPrivateAddress(true);
		
		POCompanyProfilePage.setCompanySummary(companySummary);
		POCompanyProfilePage.addAddressDetails(address);
		POCompanyProfilePage.saveCompanyProfile();
		
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		VerifyCompanyProfile.verifyAddress(address);
		
	}
	
	@TestCase(id = "RST-146")
	@Test
	public void testCreateCompanyProfilewithPhone()
	{
		CompanySummary companySummary = new CompanySummary();
		companySummary.setCompanyName(RandomHelper.getRandomAlphaNumericString(10));
		companySummary.setCompanyCode(RandomHelper.getRandomAlphaNumericString(4));
		companySummary.setPronounced(RandomHelper.getRandomAlphaNumericString(5));
		companySummary.setCompanyWebsite("http://"+RandomHelper.getRandomAlphaNumericString(7)+".com");
		companySummary.setNegotiatedRatePlanId("BAR");
		
		Phone phone = new Phone();
		phone.setPhoneType("Home");
		phone.setNumber(RandomHelper.getRandomPhoneNumber());
		phone.setExtension(RandomHelper.getRandomNumericString(2));
		phone.setIsPrivate(true);
		
		POCompanyProfilePage.setCompanySummary(companySummary);
		POCompanyProfilePage.addPhoneDetails(phone);
		POCompanyProfilePage.saveCompanyProfile();
		
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		VerifyCompanyProfile.verifyPhone(phone);
	}
	
	@TestCase(id = "RST-145")
	@Test
	public void testCreateCompanyProfilewithEmail()
	{
		CompanySummary companySummary = new CompanySummary();
		companySummary.setCompanyName(RandomHelper.getRandomAlphaNumericString(10));
		companySummary.setCompanyCode(RandomHelper.getRandomAlphaNumericString(4));
		companySummary.setPronounced(RandomHelper.getRandomAlphaNumericString(5));
		companySummary.setCompanyWebsite("http://"+RandomHelper.getRandomAlphaNumericString(7)+".com");
		companySummary.setNegotiatedRatePlanId("BAR");
		
		Email email = new Email();
		email.setType("Home");
		email.setEmailAddress(RandomHelper.getRandomEmailAddress());
		email.setIsPrivate(true);	
		
		POCompanyProfilePage.setCompanySummary(companySummary);
		POCompanyProfilePage.addEmailDetails(email);
		POCompanyProfilePage.saveCompanyProfile();
		
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		VerifyCompanyProfile.verifyEmail(email);	

	}
	
	@Test
	@TestCase(ids = {"RST-3830"}) //need to add contacts
	public void testCreateCompanyProfilewithAllfields()
	{
		CompanySummary companySummary = new CompanySummary();
		companySummary.setCompanyName(RandomHelper.getRandomAlphaNumericString(10));
		companySummary.setCompanyCode(RandomHelper.getRandomAlphaNumericString(4));
		companySummary.setPronounced(RandomHelper.getRandomAlphaNumericString(5));
		companySummary.setCompanyWebsite("http://"+RandomHelper.getRandomAlphaNumericString(7)+".com");
		companySummary.setNegotiatedRatePlanId("BAR");
		
		Address address = new Address();
		address.setAddressType("Office");
		address.setAddressLine1(RandomHelper.getRandomAlphaNumericString(10));
		address.setAddressLine2(RandomHelper.getRandomAlphaNumericString(10));
		address.setAddressLine3(RandomHelper.getRandomAlphaNumericString(10));
		address.setAddressLine4(RandomHelper.getRandomAlphaNumericString(10));
		address.setAddressLine5(RandomHelper.getRandomAlphaNumericString(10));
		address.setCityName(RandomHelper.getRandomAlphaString(5));
		address.setStateProvinceCode(RandomHelper.getRandomAlphaString(2));
		address.setPostalCode(RandomHelper.getRandomNumericString(6));
		address.setCountryCode("India");
		address.setCountyCode(RandomHelper.getRandomAlphaString(2));
		address.setIsPrivateAddress(true);
		
		Phone phone = new Phone();
		phone.setPhoneType("Home");
		phone.setNumber(RandomHelper.getRandomPhoneNumber());
		phone.setExtension(RandomHelper.getRandomNumericString(2));
		phone.setIsPrivate(true);

		
		Email email = new Email();
		email.setType("Home");
		email.setEmailAddress(RandomHelper.getRandomEmailAddress());
		email.setIsPrivate(true);
		
		POCompanyProfilePage.setCompanySummary(companySummary);		
		POCompanyProfilePage.setmarketingdetails(guestType.getName(), "Corporate Preferred", marketSegment.getName());
		POCompanyProfilePage.addAddressDetails(address);
		POCompanyProfilePage.addPhoneDetails(phone);
		POCompanyProfilePage.addEmailDetails(email);
		POCompanyProfilePage.saveCompanyProfile();
		
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		VerifyCompanyProfile.verifyCompanySummary(companySummary);		
		VerifyCompanyProfile.verifyMarketing(guestType.getName(), "Corporate Preferred", marketSegment.getName());
		VerifyCompanyProfile.verifyAddress(address);
		VerifyCompanyProfile.verifyPhone(phone);
		VerifyCompanyProfile.verifyEmail(email);
		
	}
	
	@TestCase(id = "RST-98")
	@Test
	public void testCreateCompanyProfilewithMandatoryfields()
	{
		CompanySummary companySummary = new CompanySummary();
		companySummary.setCompanyName(RandomHelper.getRandomAlphaNumericString(10));
		companySummary.setCompanyCode(RandomHelper.getRandomAlphaNumericString(4));
		
		POCompanyProfilePage.setCompanySummary(companySummary);
		POCompanyProfilePage.saveExitCompanyProfile();
		
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		VerifyCompanyProfile.verifyCompanySummary(companySummary);
	}
	
	@TestCase(id = "RST-119")
	@Test
	public void testUpdateCompanyProfileEmail()
	{
		CompanySummary companySummary = POCompanyProfilePage.createCompanyProfile();
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		Email email = new Email();
		email.setType("Home");
		$(LocatorsCompanyProfilePage.INPUT_EMAIL_ADDRESS).clear();
		email.setEmailAddress(RandomHelper.getRandomEmailAddress());
		email.setIsPrivate(true);
		POCompanyProfilePage.addEmailDetails(email);
		POCompanyProfilePage.saveCompanyProfile();
		
		searchemail = email; 
		String updatedemail = searchemail.getEmailAddress();
		POSearchInMainPage.searchForCompanyProfile(updatedemail);
		
		POSearchInMainPage.searchForCompanyProfile(companySummary.getCompanyName());
		click($$(ProfileTile.LIST_COMPANIES).findBy(Condition.text(companySummary.getCompanyName())));
		ValidationBase.verifyElementValue(updatedemail, LocatorsCompanyProfilePage.INPUT_EMAIL_ADDRESS);
	}
	
	@Test
	@TestCase(ids = {"RST-105","RST-106"})
	public void testCreateCompanyProfilewithAR()
	{
		CompanySummary companySummary = POCompanyProfilePage.createCompanyProfile();
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		BigDecimal credit = new BigDecimal(50);		
		String term = "30 days";
		POCompanyProfilePage.createAR(credit, term);
		POCompanyProfilePage.saveCompanyProfile();
		
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		clickTabAR();
		ValidationBase.verifyElementPriceValue(HelperMethods.formatAmount(credit), LocatorsSummarySection.INPUT_CREDIT_LIMIT);
		$(LocatorsSummarySection.DROPDOWN_TERMS).shouldBe(Condition.visible);
		ValidationBase.verifyElementValue(term, LocatorsSummarySection.DROPDOWN_TERMS);
	}

	@Test
	@TestCase(ids = {"RST-2459"})
	public void testCreateCompanyProfileWithAutoGenerateAccount()
	{
		CompanySummary companySummary = POCompanyProfilePage.createCompanyProfile();
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		BigDecimal credit = new BigDecimal(100);
		String term = "60 days";
		POCompanyProfilePage.createAR(credit, term);
		POCompanyProfilePage.saveCompanyProfile();

		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		clickTabAR();
		ValidationBase.verifyElementPriceValue(HelperMethods.formatAmount(credit), LocatorsSummarySection.INPUT_CREDIT_LIMIT);
		$(LocatorsSummarySection.DROPDOWN_TERMS).shouldBe(Condition.visible);
		ValidationBase.verifyElementValue(term, LocatorsSummarySection.DROPDOWN_TERMS);
	}

	@Test
	@TestCase(ids = {"RST-2459"})
	public void testCreateCompanyProfileWithCustomGenerateAccount()
	{
		CompanySummary companySummary = POCompanyProfilePage.createCompanyProfile();
		BigDecimal accountNumber = new BigDecimal(123456);
		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		BigDecimal credit = new BigDecimal(50);
		String term = "60 days";
		POCompanyProfilePage.createAR(accountNumber, credit, term);
		POCompanyProfilePage.saveCompanyProfile();

		POSearchInMainPage.goToCompanyProfile(companySummary.getCompanyName());
		clickTabAR();
		ValidationBase.verifyElementPriceValue(HelperMethods.formatAmount(credit), LocatorsSummarySection.INPUT_CREDIT_LIMIT);
		$(LocatorsSummarySection.DROPDOWN_TERMS).shouldBe(Condition.visible);
		ValidationBase.verifyElementValue(term, LocatorsSummarySection.DROPDOWN_TERMS);
	}
}