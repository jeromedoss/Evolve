package com.agilysys.qa.gridIron.pageobject.profiles.company;

import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.MODAL_BUTTON_CANCEL;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.TAB_PROFILES;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.summary.LocatorsSummarySection.INPUT_CREDIT_LIMIT;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.PFCompanyProfilePage.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
//import static jdk.nashorn.internal.objects.NativeJava.type;

import java.math.BigDecimal;

import com.agilysys.qa.gridIron.constants.locators.reservation.guestinformation.LocatorsGuestInformationSection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import com.agilysys.pms.profile.model.Address;
import com.agilysys.pms.profile.model.CompanySummary;
import com.agilysys.pms.profile.model.Email;
import com.agilysys.pms.profile.model.Phone;
import com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.PFCompanyProfilePage;
import com.agilysys.qa.gridIron.pageobject.home.profiletile.CreateNewProfile;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.codeborne.selenide.Condition;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POCompanyProfilePage {
	
	public static void setCompanySummary(CompanySummary companySummary)
	{
		clickSummaryPane();
		PFCompanyProfilePage.typeCompanyName(companySummary.getCompanyName());
		PFCompanyProfilePage.typeCompanyCode(companySummary.getCompanyCode());
		PFCompanyProfilePage.typePronounced(companySummary.getPronounced());
		PFCompanyProfilePage.typeWebsite(companySummary.getCompanyWebsite());
		PFCompanyProfilePage.selectRatePlan(companySummary.getNegotiatedRatePlanId());
	}
	
	public static void setmarketingdetails(String guestType, String sourceOfBusiness,  String marketSegment)
	{		
		PFCompanyProfilePage.selectGuestType(guestType);
		PFCompanyProfilePage.selectSourceOfBusiness(sourceOfBusiness);		
		PFCompanyProfilePage.selectMarketSegment(marketSegment);
	}
	
	public static void addAddressDetails(Address address)
	{		
		PFCompanyProfilePage.selectAddressType(address.getAddressType());
		PFCompanyProfilePage.typeStreet(address.getAddressLine1());		
		PFCompanyProfilePage.typeAddressLine2(address.getAddressLine2());
		PFCompanyProfilePage.typeAddressLine3(address.getAddressLine3());
		PFCompanyProfilePage.typeAddressLine4(address.getAddressLine4());
		PFCompanyProfilePage.typeAddressLine5(address.getAddressLine5());
		PFCompanyProfilePage.typeCity(address.getCityName());
		PFCompanyProfilePage.typeState(address.getStateProvinceCode());
		PFCompanyProfilePage.typeZip(address.getPostalCode());
		PFCompanyProfilePage.typeCountry(address.getCountryCode());
		PFCompanyProfilePage.typeCounty(address.getCountyCode());
		PFCompanyProfilePage.setPrivateAddress(address.getIsPrivateAddress());
	}
	
	public static void addPhoneDetails(Phone phone)
	{
		//clickContactPane();
		PFCompanyProfilePage.selectPhoneType(phone.getPhoneType());
		PFCompanyProfilePage.typeNumber(phone.getNumber());
		PFCompanyProfilePage.typeExtension(phone.getExtension());
		PFCompanyProfilePage.setPrivatePhone(phone.getIsPrivate());
		//
		click(LocatorsGuestInformationSection.BUTTON_PHONE_ADD);

	}
	
	public static void addEmailDetails(Email email)
	{
		PFCompanyProfilePage.selectEmailType(email.getType());
		PFCompanyProfilePage.typeEmail(email.getEmailAddress());
		PFCompanyProfilePage.setPrivateEmail(email.getIsPrivate());
	}

	public static void saveCompanyProfile() {
		clickButtonSave();
	}
	
	public static void stepCreateCompanyProfileSave(String companyName, String companyCode) {

		createMandate(companyName, companyCode);
		clickButtonSave();
	}

	public static void saveExitCompanyProfile() {
		clickButtonSaveExit();
	}
	
	public static void stepCreateCompanyProfileSaveExit(String companyName, String companyCode) {

		createMandate(companyName, companyCode);
		clickButtonSaveExit();

	}


	public static void stepCreateARAccountSaveExit(BigDecimal credit, String term) {

//		$(byText("Profile Created")).shouldBe(Condition.visible);
//		$(By.xpath("//*[contains(text(),'History unavailable at this time.')] | //*[contains(text(),'Profile Created')]")).should(Condition.visible);
		
		createAR(credit, term);
		clickButtonSaveExit();		
	}

	public static void stepCreateARAccountSave(BigDecimal credit, String term) {

//		$(byText("Profile Created")).shouldBe(Condition.visible);
//		$(By.xpath("//*[contains(text(),'History unavailable at this time.')] | //*[contains(text(),'Profile Created')]")).should(Condition.visible);
		createAR(credit, term);
		clickButtonSave();

	}

	public static void createAR(BigDecimal credit, String term) {
		Selenide.refresh();
		clickTabAR();
		clickLinkARCreate();
		setInputCreditLimit(credit.toString());
		selectTerm(term);
	}

	public static void createAR(BigDecimal accountNumber, BigDecimal credit, String term) {
		clickTabAR();
		clickLinkARCreate();
		setAccountNumber(accountNumber.toString());
		setInputCreditLimit(credit.toString());
		selectTerm(term);
	}

	public static void createMandate(String companyName, String companyCode) {

		PFCompanyProfilePage.typeCompanyCode(companyCode);
		PFCompanyProfilePage.typeCompanyName(companyName);
	}
	
	public static CompanySummary createCompanyProfile(){		
		CreateNewProfile.stepClickCreateCompanyFromMain();
		CompanySummary companySummary = new CompanySummary();
		companySummary.setCompanyName(RandomHelper.getRandomAlphaNumericString(10));
		companySummary.setCompanyCode(RandomHelper.getRandomAlphaNumericString(4));
		POCompanyProfilePage.setCompanySummary(companySummary);
		POCompanyProfilePage.saveExitCompanyProfile();		
		return companySummary;		
	}
	
	public static CompanySummary createAccountReceivable(){
		BigDecimal credit = new BigDecimal(RandomHelper.getRandomInt(10000, 50000));
		String term = "30 days";
		CreateNewProfile.stepClickCreateCompanyFromMain();
		CompanySummary companySummary = new CompanySummary();
		companySummary.setCompanyName(RandomHelper.getRandomAlphaNumericString(10));
		companySummary.setCompanyCode(RandomHelper.getRandomAlphaNumericString(4));				
		POCompanyProfilePage.setCompanySummary(companySummary);
		POCompanyProfilePage.saveCompanyProfile();
		POCompanyProfilePage.createAR(credit, term);
		POCompanyProfilePage.saveExitCompanyProfile();
		return companySummary;		
	}
}