package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import static com.codeborne.selenide.Selenide.$;

import java.math.BigDecimal;

import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.constants.locators.reservation.payments.LocatorsPaymentsSection;
import com.agilysys.qa.gridIron.utils.HelperMethods;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Configuration;

public class PFPaymentMethodsSection extends PageFactoryBase{

	public static String getDefaultPaymentMethodName(){
		return $(LocatorsPaymentsSection.DEFAULT_PAYMENT_METHOD_NAME).getText();
	}
	
	public static void clickAddNewPaymentMethod(){
		click(LocatorsPaymentsSection.BUTTON_NEW_PAYMENT_METHOD);
	}
	
	public static void clickPaymentMethod(String paymentMethodName){
		click(LocatorsPaymentsSection.getPaymentMethodByName(paymentMethodName));
	}
	
	public static void clickDeletePaymentMethod(){
		click(LocatorsPaymentsSection.LINK_DELETE_THIS_PAYMENT);		
		click(By.xpath("//button[@ng-click='yes()']"));
		waitForElementToDisappear(LocatorsPaymentsSection.LINK_DELETE_THIS_PAYMENT, Configuration.timeout);
	}
	
	public static void clickMakeDefault(){
		click(LocatorsPaymentsSection.RADIO_MAKE_THIS_DEFAULT);
		waitForElementToDisappear(LocatorsPaymentsSection.RADIO_MAKE_THIS_DEFAULT, Configuration.timeout);
	}
	
	public static BigDecimal getAuthAmount(){
		return HelperMethods.convertToBigDecimal($(LocatorsPaymentsSection.LINK_AUTHORIZE_AMOUNT).getText());
	}
}
