package com.agilysys.qa.gridIron.constants.pagefactory.booking;

import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.BUTTON_ADD_PAYMENT;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.BUTTON_PAYMENT_DO_NOT_DISCLOSE;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.BUTTON_PAYMENT_SAVETO_PROFILE;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.BUTTON_PAYMENT_SETAS_DEFAULT;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.BUTTON_PAYMENT_SET_THIRDPARTY;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.DROPDOWN_PAYMENT;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.INPUT_DIRECTBILL;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.LIST_DROPDOWN_PAYMENT;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.MESSAGE_CARD_TYPED;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.SELECT_DIRECTBILL;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.Keys;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFPaymentMethodTab extends PageFactoryBase{

	int paymentIndex=0;
	
	public PFPaymentMethodTab(int paymentIndex){
		this.paymentIndex = paymentIndex;
	}

	public void clickAddNewPaymentMethod(){		
		click(BUTTON_ADD_PAYMENT);
	}
	
	public void selectPaymentMethod(String paymentMethod) {
		selectByText(DROPDOWN_PAYMENT,paymentIndex, LIST_DROPDOWN_PAYMENT, paymentMethod);		
	}
	
	public void savePaymentMethodToProfile(){
		click(BUTTON_PAYMENT_SAVETO_PROFILE, paymentIndex);
	}
	
	public void setAsDefaultPayment(){
		click(BUTTON_PAYMENT_SETAS_DEFAULT, paymentIndex);
	}
	
	public void setAsThirdPartyPayment(){
		click(BUTTON_PAYMENT_SET_THIRDPARTY, paymentIndex);
	}
	
	public void setDoNoDiscloseRates(){
		click(BUTTON_PAYMENT_DO_NOT_DISCLOSE, paymentIndex);
	}
	
	public void typeDirectBillAccount(String directBillAccount){
		$(INPUT_DIRECTBILL).setValue(directBillAccount);
		click(SELECT_DIRECTBILL);
	}
	
	public void swipeCard(String cardData){
		Selenide.sleep(2000);		
		Selenide.actions().sendKeys(Keys.NUMPAD0, cardData, Keys.ENTER).build().perform();
		$(MESSAGE_CARD_TYPED).shouldBe(Condition.visible).hover();
	}
}