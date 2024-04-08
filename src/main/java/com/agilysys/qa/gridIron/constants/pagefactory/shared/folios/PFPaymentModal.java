package com.agilysys.qa.gridIron.constants.pagefactory.shared.folios;

import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsMakePaymentModal.BUTTON_APPLY;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsMakePaymentModal.*;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsMakePaymentModal.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsMakePaymentModal.INPUT_PASSWORD;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsMakePaymentModal.INPUT_USERNAME;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsMakePaymentModal.LABEL_BALANCE;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsRefundModal.*;

import java.math.BigDecimal;
import java.time.Duration;

import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsMakePaymentModal;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;


public class PFPaymentModal extends PageFactoryBase{

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void clickLabelBalance() {
		click(LABEL_BALANCE);	
	}

	public static void selectPaymentMethod(String folioName, String paymentMethodName){
		selectByText(LocatorsMakePaymentModal.getPaymentMethodDropdownByFolioName(folioName), LocatorsMakePaymentModal.getPaymentMethodDropdownOptionsByFolioName(folioName), paymentMethodName);
	}

	public static void typePaymentAmount(String folioName, BigDecimal amount) {
		Selenide.sleep(3000);
		clearAndType(LocatorsMakePaymentModal.getAmountByFolioName(folioName), amount.toString());

	}

	public static void typePassword() {
		clearAndType(INPUT_PASSWORD, Endpoints.getPassword());
	}
	
	public static void typeUsername() {
		clearAndType(INPUT_USERNAME, Endpoints.getUserName());
	}

	public static void clickButtonNext() {
		click(BUTTON_NEXT);		
	}
	
	public static void clickButtonApply() {
		click(BUTTON_APPLY);
		$(BUTTON_DONE).should(Condition.enabled, Duration.ofMillis(Configuration.timeout));
	}

	public static void clickButtonDone() {		
		click(BUTTON_DONE);
		waitForElementToDisappear(BUTTON_DONE, Configuration.timeout);
	}
	
		
	
}
