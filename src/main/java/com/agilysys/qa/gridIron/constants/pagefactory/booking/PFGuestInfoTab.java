package com.agilysys.qa.gridIron.constants.pagefactory.booking;

import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookReservationModal.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.guest.LocatorsGuestInfoTab.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFGuestInfoTab extends PageFactoryBase{

	public static void clickInputGuestProfile() {
		click(HEADER_MODAL);
		click(INPUT_GUEST_PROFILE_SEARCH);
	}

	public static void clickAddNewGuestProfile() {
		Selenide.sleep(500);
		int time = 0;
		while(time<=Configuration.timeout){
			click(LINK_ADD_NEW_GUEST_PROFILE);
			time = time +2000;
			if($$(INPUT_LASTNAME).size()>0){
				return;
			}
		}		
	}

	public static void typeLastName(String lastName) {
		type(INPUT_LASTNAME, lastName);
	}

	public static void typeFirstname(String firstName) {
		type(INPUT_FIRSTNAME,firstName);
	}

	public static void typeMiddleInitial(String middleInitial) {
		type(INPUT_MIDDLE_INITIAL,middleInitial);
	}
	
	public static void expandAddressSection(){
		if($(BUTTON_ADDRESS_TAB).getAttribute("class").contains("closed")){
			click(BUTTON_ADDRESS_TAB);
		}
	}
	
	public static void selectAddressType(String addressType){		
		selectByText(DROPDOWN_ADDERSS_TYPE, LIST_ADDERSS_TYPE, addressType);
	}
	
	public static void typeAddressLine1(String addressLine1){
		type(INPUT_ADDRESS_LINE1,addressLine1);		
		acceptGoogleAlertIfPresent();
	}
	
	public static void typeAddressLine2(String addressLine2){	
		addAddressLine();		
		type(INPUT_ADDRESS_LINE2,addressLine2);
	}
	
	public static void typeAddressLine3(String addressLine3){
		addAddressLine();
		type(INPUT_ADDRESS_LINE3,addressLine3);
	}
	
	public static void typeAddressLine4(String addressLine4){
		addAddressLine();
		type(INPUT_ADDRESS_LINE4,addressLine4);
	}
	
	public static void typeAddressLine5(String addressLine5){
		addAddressLine();
		type(INPUT_ADDRESS_LINE5,addressLine5);
	}
	
	private static void addAddressLine(){
		click(LINK_ADD_ADDRESS_LINE);
	}
	
	public static void typeCity(String city){
		type(INPUT_CITY,city);
	}
	
	public static void typeState(String state){
		type(INPUT_STATE,state);
	}
	
	public static void typePostalCode(String postalCode){
		type(INPUT_POSTAL_CODE,postalCode);
	}
	
	public static void typeCountry(String country){
		type(INPUT_COUNTRY,country);
	}
	
	public static void typeCounty(String county){
		type(INPUT_COUNTY,county);
	}
	
	
	public static void expandContactSection(){
		if($(BUTTON_CONTACT_TAB).getAttribute("class").contains("closed")){
			click(BUTTON_CONTACT_TAB);
		}
	}

	public static void typePhoneNumber(String phoneNo) {
		type(INPUT_PHONE_NUMBER,phoneNo);
	}
	
	public static void selectPhoneType(String phoneType){
		selectByText(DROPDOWN_PHONE_TYPE, LIST_PHONE_TYPE, phoneType);
	}
	
	public static void typePhoneExtn(String phoneExtn) {
		type(INPUT_PHONE_EXTN,phoneExtn);
	}
	
	public static void typeEmailAddress(String emailAddress){
		type(INPUT_EMAIL,emailAddress);
	}
	
	public static void selectEmailType(String emailType){
		selectByText(DROPDOWN_EMAIL_TYPE, LIST_EMAIL_TYPE, emailType);
	}
	
	public static void typeCompanyName(String companyName){
		type(INPUT_COMPANY_NAME,companyName);
	}

	public static void typeGuestProfileSearch(String searchString) {
		clearAndType(INPUT_GUEST_PROFILE_SEARCH,searchString);
	}

	public static void selectGuestProfile() {
		click(SELECT_GUEST_PROFILE);
		$(EDIT_GUEST_PROFILE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
}
