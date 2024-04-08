package com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest;


import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.BUTTON_ADD_ADDRESS;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.CHECKBOX_ADDRESS_KEEP_PRIVATE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.DROPDOWN_ADDRESS_TYPE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_ADDRESS_LINE1;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_ADDRESS_LINE2;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_ADDRESS_LINE3;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_ADDRESS_LINE4;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_ADDRESS_LINE5;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_CITY;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_COUNTRY;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_COUNTY;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_POSTAL_CODE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.INPUT_STATE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.LINK_ADD_ADDRESS_LINE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.LIST_ADDRESS_TYPE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.RADIO_USE_THIS_ADDRESS;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress.SIDEPANE_CONTACT;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsEmailDetails.BUTTON_ADD_EMAIL;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsEmailDetails.CHECKBOX_EMAIL_KEEP_PRIVATE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsEmailDetails.DROPDOWN_EMAIL;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsEmailDetails.INPUT_EMAIL_ADDRESS;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsEmailDetails.LIST_DROPDOWN_EMAIL;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.BUTTON_ADD_PHONE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.CHECKBOX_KEEP_PRIVATE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.DROPDOWN_PHONE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.INPUT_PHONE_EXTENSION;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.INPUT_PHONE_NUMBER;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsPhoneNumber.LIST_DROPDOWN_PHONE;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact.LocatorsAddress;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFGuestProfileContact extends PageFactoryBase{

	public int addressIndex=0;
	public int phoneIndex=0;
	public int emailIndex=0;
	
	public PFGuestProfileContact(int addressIndex, int phoneIndex, int emailIndex){
		if(addressIndex>0){
			this.addressIndex = addressIndex;
		}
		
		if(phoneIndex>0){
			this.phoneIndex = phoneIndex;
		}
		
		if(emailIndex>0){
			this.emailIndex = emailIndex;
		}
	}
	
	public void clickContactSideMenu() {
		click(SIDEPANE_CONTACT);
	}
	
	public void clickAddNewAddress() {
		click(BUTTON_ADD_ADDRESS);
	}
	
	public void selectAddressType(String addressType) {		
		selectByText(DROPDOWN_ADDRESS_TYPE,addressIndex, LIST_ADDRESS_TYPE, addressType);				
	}
	
	public void typeAddressLine1(String addressLine1) {		
		type(INPUT_ADDRESS_LINE1, addressIndex,addressLine1);
		acceptGoogleAlertIfPresent();
	}
	
	public void typeAddressLine2(String addressLine2) {		
		click(LINK_ADD_ADDRESS_LINE);		
		type(INPUT_ADDRESS_LINE2,addressIndex,addressLine2);
	}
	
	public void typeAddressLine3(String addressLine3) {
		click(LINK_ADD_ADDRESS_LINE);
		type(INPUT_ADDRESS_LINE3, addressIndex,addressLine3);
	}
	
	public void typeAddressLine4(String addressLine4) {
		click(LINK_ADD_ADDRESS_LINE);
		type(INPUT_ADDRESS_LINE4, addressIndex,addressLine4);
	}
	
	public void typeAddressLine5(String addressLine5) {
		click(LINK_ADD_ADDRESS_LINE);
		type(INPUT_ADDRESS_LINE5, addressIndex,addressLine5);
	}
	
	public void typeCity(String city) {		
		type(INPUT_CITY, addressIndex,city);
	}
	
	public void typeState(String state) {		
		type(INPUT_STATE,addressIndex,state);
	}
	
	public void typePostalCode(String postalCode) {		
		type(INPUT_POSTAL_CODE, addressIndex, postalCode);
	}
	
	public void typeCountry(String country) {		
		type(INPUT_COUNTRY, addressIndex,country);
	}
	
	public void typeCounty(String county) {		
		type(INPUT_COUNTY, addressIndex,county);
	}
	
	public void setPrivateAddress(boolean isPrivate) {
		if(isPrivate){
			click(CHECKBOX_ADDRESS_KEEP_PRIVATE, addressIndex);
		}		
	}
	
	public void setDefaultAddress(boolean isDefault) {
		if(isDefault){
			click(RADIO_USE_THIS_ADDRESS, addressIndex);
		}		
	}
	
	public void clickAddNewPhone() {
		click(BUTTON_ADD_PHONE);
	}
	
	public void selectPhoneType(String phoneType) {		
		selectByText(DROPDOWN_PHONE, phoneIndex, LIST_DROPDOWN_PHONE, phoneType);
	}
	
	public void typePhonenumberinput(String phoneNumber) {
		type(INPUT_PHONE_NUMBER, phoneIndex,phoneNumber);
	}
	
	public void typePhoneExtension(String extn) {
		type(INPUT_PHONE_EXTENSION, phoneIndex,extn);
	}
	
	public void checkIsPhonePrivate(boolean isPrivate) {
		if(isPrivate){			
			click(CHECKBOX_KEEP_PRIVATE, phoneIndex);
		}		
	}
	
	public void clickAddNewEmail() {
		click(BUTTON_ADD_EMAIL);
	}
	
	public void selectEmailType(String emailType) {		
		selectByText(DROPDOWN_EMAIL,emailIndex, LIST_DROPDOWN_EMAIL, emailType);
	}
	
	public void typeEmail(String email) {
		type(INPUT_EMAIL_ADDRESS,emailIndex,email);
	}
	
	public void checkIsEmailPrivate(boolean isPrivate) {
		if(isPrivate){
			click(CHECKBOX_EMAIL_KEEP_PRIVATE, emailIndex);
		}		
	}
}