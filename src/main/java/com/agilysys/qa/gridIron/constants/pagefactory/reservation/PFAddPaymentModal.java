package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.agilysys.qa.gridIron.constants.locators.reservation.payments.LocatorsAddPaymentModal;
import com.agilysys.qa.gridIron.constants.locators.reservation.payments.LocatorsPaymentsSection;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFAddPaymentModal extends PageFactoryBase{

	public static void setThirdPartyPaymentMethod(boolean isThirdParty){
		setCheckBox(LocatorsAddPaymentModal.CHECKBOX_THIRD_PARTY, isThirdParty);
	}
	
	public static void setDoNotDiscloseRates(boolean doNotDiscloseRates){
		setCheckBox(LocatorsAddPaymentModal.CHECKBOX_DO_NOT_DISCLOSE, doNotDiscloseRates);
	}
	
	public static void setPrimaryMethod(boolean isPrimaryMethod){
		setCheckBox(LocatorsAddPaymentModal.CHECKBOX_RESERVATION_DEFAULT, isPrimaryMethod);
	}
	
	public static void setAssociateDefaultFolio(boolean associateToDefaultFolio){
		setCheckBox(LocatorsAddPaymentModal.CHECKBOX_ADD_TO_DEFAULT_FOLIO, associateToDefaultFolio);
	}
	
	public static void setSaveToGuestProfile(boolean saveToGuestProfile){
		setCheckBox(LocatorsAddPaymentModal.CHECKBOX_PROFILE_DEFAULT, saveToGuestProfile);
	}
	
	public static void setGuestDefaultPaymentMethod(boolean isDefault){
		setCheckBox(LocatorsAddPaymentModal.CHECKBOX_DEFAULT_PAYMENT, isDefault);
	}
	
	public static void selectPaymentMethod(String paymentMethod){
		selectByText(LocatorsAddPaymentModal.DROPDOWN_PAYMENT, LocatorsAddPaymentModal.LIST_PAYMENT_METHODS, paymentMethod);
	}
	
	public static void setDirectBillAccount(String directBill){
		type(LocatorsAddPaymentModal.INPUT_SEARCH_ACCOUNTS, directBill);
		click(By.xpath("//*[contains(text(),'"+directBill+"')]"));
	}
	
	public static void swipeData(String swipeData){
		Selenide.sleep(3000);
		Selenide.actions().sendKeys(Keys.NUMPAD0, swipeData, Keys.ENTER).build().perform();
	}
	
	public static void savePaymentModal(){
		click(LocatorsAddPaymentModal.BUTTON_SAVE);
//		waitForElementToDisappear(LocatorsAddPaymentModal.BUTTON_SAVE, Configuration.timeout);
		Selenide.sleep(2000);
	}	
}