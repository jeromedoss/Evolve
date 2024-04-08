package com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest;


import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.MODAL_BUTTON_CANCEL;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.BUTTON_SAVE_EXIT;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.DROPDOWN_GENDER;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.DROPDOWN_SUFFIX;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.DROPDOWN_TITLE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.GROUP_BUTTON_ADD;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.*;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_ANNIVERSARY;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_COMPANY_NAME;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_COMPANY_TITLE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_DOB;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_FIRSTNAME;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_LANGUAGE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_LASTNAME;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_MIDDLENAME;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_PRONOUNCED;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.LIST_GENDER;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.LIST_SUFFIX;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.LIST_TITLE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.NAV_GROUP;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.CHECKBOX_KEEP_PRIVATE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.DROPDOWN_PHONE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.INPUT_PHONE_EXTENSION;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.INPUT_PHONE_NUMBER;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.LIST_DROPDOWN_PHONE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.marketing.LocatorsMarketing.DROPDOWN_GUEST_TYPE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.marketing.LocatorsMarketing.DROPDOWN_MARKET_SEGMENTS;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.marketing.LocatorsMarketing.DROPDOWN_SOURCE_OF_BUSINESS;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.marketing.LocatorsMarketing.LIST_DROPDOWN_GUEST_TYPE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.marketing.LocatorsMarketing.LIST_DROPDOWN_MARKET_SEGMENTS;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.marketing.LocatorsMarketing.LIST_DROPDOWN_SOURCE_OF_BUSINESS;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.marketing.LocatorsMarketing.SIDEPANE_MARKETING;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.constants.pagefactory.home.PFMainSearch;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFGuestProfilePage extends PageFactoryBase{

	public static void clickButtonGroupAdd() {
		//$(NAV_GROUP).shouldBe(Condition.visible);
		//$(NAV_GROUP).hover();
		//scrollElementToVisibleArea($(GROUP_BUTTON_ADD));
		$(GROUP_BUTTON_ADD).scrollIntoView(false);
		click(GROUP_BUTTON_ADD);
		//$(GROUP_BUTTON_ADD);
	}
	
	public static String getLastnameinput() {
		return $(INPUT_LASTNAME).getAttribute("value");
	}

	public static String getFirstnameinput() {
		return $(INPUT_FIRSTNAME).getAttribute("value");
	}

	public static String getPhonenumberinput() {
		return $(INPUT_PHONE_NUMBER).getAttribute("value");
	}

	public static void typeLastName(String lastName) {
		type(INPUT_LASTNAME, lastName);		
	}

	public static void typeFirstName(String firstName) {
		type(INPUT_FIRSTNAME, firstName);		
	}
	
	public static void typeMiddleInital(String middleInitial){
		type(INPUT_MIDDLENAME, middleInitial);		
	}
	
	public static void typeAlias(String alias){
		type(INPUT_ALIAS,alias);
	}
	
	public static void typeDOB(LocalDate dob){
		if(dob !=null){
			type(INPUT_DOB,dob.toString("MM/dd/yyyy"));
		}
	}
	
	public static void typeAnniversary(LocalDate anniversary){
		if(anniversary !=null){
			type(INPUT_ANNIVERSARY,anniversary.toString("MM/dd/yyyy"));
		}
	}
	
	public static void typeCompanyName(String companyName){
		type(INPUT_COMPANY_NAME,companyName);
	}
	
	public static void typeCompanyTitle(String companyTitle){
		type(INPUT_COMPANY_TITLE,companyTitle);
	}
	
	public static void typePronounced(String pronounced){
		type(INPUT_PRONOUNCED,pronounced);
	}
	
	public static void typeLanguage(String language){
		type(INPUT_LANGUAGE,language);
	}
	
	public static void selectGender(String gender){		
		selectByText(DROPDOWN_GENDER, LIST_GENDER, gender);
	}
	
	public static void selectSuffix(String suffix){		
		selectByText(DROPDOWN_SUFFIX, LIST_SUFFIX, suffix);
	}
	
	public static void selectTitle(String title){		
		selectByText(DROPDOWN_TITLE, LIST_TITLE, title);
	}
	
	public static void clickMarketingSideMenu(){
		$(SIDEPANE_MARKETING).shouldBe(Condition.visible);
	}
	
	public static void selectGuestType(String guestType){		
		selectByText(DROPDOWN_GUEST_TYPE, LIST_DROPDOWN_GUEST_TYPE, guestType);
	}
	
	public static void selectSourceOfBusiness(String sourceOfBusiness){		
		selectByText(DROPDOWN_SOURCE_OF_BUSINESS, LIST_DROPDOWN_SOURCE_OF_BUSINESS, sourceOfBusiness);
	}
	
	public static void selectMarketSegment(String marketSegment){		
		selectByText(DROPDOWN_MARKET_SEGMENTS, LIST_DROPDOWN_MARKET_SEGMENTS, marketSegment);
	}
	
	public static void selectPhoneType(String phoneType) {		
		selectByText(DROPDOWN_PHONE, LIST_DROPDOWN_PHONE, phoneType);
	}
	
	public static void typePhoneNumber(String phoneNumber) {
		type(INPUT_PHONE_NUMBER,phoneNumber);
	}
	
	public static void typePhoneExtension(String extn) {
		type(INPUT_PHONE_EXTENSION,extn);
	}
	
	public static void checkIsPrivate(boolean isPrivate) {
		if(isPrivate){
			click(CHECKBOX_KEEP_PRIVATE);
		}		
	}

	public static void clickSidepaneComments(){
		Selenide.sleep(1000);
		click(SIDEPANE_COMMENTS);
	}

	public static void clickSidepaneDocumentAttachment(){
		Selenide.sleep(1000);
		click(SIDEPANE_DOCUMENTATTACHMENT);
	}

	public static void clickAddCommentButton(){
		//$(CLICK_ADD_COMMENT);
		Selenide.sleep(1000);
		click(CLICK_ADD_COMMENT);
	}
	
	public static void typeComment(String comment) {
		type(TEXTAREA_COMMENT,comment);
	}
	
	public static void selectCommentType(String commentType) {
		selectByText(DROPDOWN_COMMENT_TYPE,LIST_COMMENT_TYPE, commentType);
	}
	
	public static void selectCommentSeverity(String commentSeverity) {
		selectByText(DROPDOWN_COMMENT_SEVERITY,LIST_COMMENT_SEVERITY, commentSeverity);
	}
	
	public static void clickCommentSave(){
		click(BUTTON_SAVE_COMMENT);
		$(TEXTAREA_COMMENT).should(Condition.value(""), Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clickSidepanePayments(){
		click(SIDEPANE_PAYMENT);
		Selenide.sleep(1000);
	}
	
	public static void clickAddPaymentMethod(){
		click(BUTTON_ADD_PAYMENT);
	}
	
	public static void clickPaymentIsDefault(boolean isDefault){
		if(isDefault){
			click(BUTTON_PAYMENT_SET_AS_DEFAULT);
		}		
	}
	
	public static void selectPaymentMethod(String paymentMethod){
		selectByText(DROPDOWN_PAYMENT_METHOD, LIST_PAYMENT_METHOD, paymentMethod);
	}
	
	public static void typeDirectBillAccount(String directBillAccount){
		if(directBillAccount == null || directBillAccount.isEmpty()){
			return;
		}
		type(INPUT_DIRECTBILL_ACCOUNT_NAME, directBillAccount);		
		click(LIST_DIRECTBILL_ACCOUNT_NAME);
	}
	
	public static void clickSavePaymentMethod(){
		click(BUTTON_SAVE_PAYMENT);
		$(BUTTON_SAVE_PAYMENT).shouldNot(Condition.visible);
		$(INPUT_LASTNAME).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void ClickSavebutton() {
		$(BUTTON_SAVE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		click(BUTTON_SAVE);
		waitForElementToDisappear(BUTTON_SAVE, Configuration.timeout);
		$(INPUT_LASTNAME).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}

	public static void ClickSaveexitbutton() {
		click(BUTTON_SAVE_EXIT);
		
		if ($(HEADER_MODAL).is(Condition.visible))
			click(MODAL_BUTTON_CANCEL);
		PFSearchTile.waitForSearchPageToLoad();
	}
}