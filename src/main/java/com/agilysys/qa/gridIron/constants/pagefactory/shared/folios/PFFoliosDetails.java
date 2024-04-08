package com.agilysys.qa.gridIron.constants.pagefactory.shared.folios;

import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.BUTTON_ADD_A_CHARGE;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.BUTTON_ADD_A_CREDIT;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.BUTTON_ADD_ROUTING_RULE;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.BUTTON_MAKE_PAYMENT;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.BUTTON_MORE;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.BUTTON_SELECTALL_FOLIO1;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.*;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.LABEL_QUANTITY_FOLIO1;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.NAV_PAYMENT;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.TAB_FOLIO_1;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class PFFoliosDetails extends PageFactoryBase{

	public  static void folioOperations(By option){
		clickAndWaitForNextElement(BUTTON_MORE,option);
		SelenideElement element = $(BUTTON_MORE);
		actions().moveToElement(element).click(element).perform();
		SelenideElement element1 = $(option);
		jsClick(element1);
	//	actions().moveToElement(element1).click().perform();
	}

	public static void clickAddFolio() {

		folioOperations(BUTTON_ADD_NEW_FOLIO);
	}
	
	public static void clickAddACharge() {

		folioOperations(BUTTON_ADD_A_CHARGE);
	}

	public static void clickAddACredit() {

		folioOperations(BUTTON_ADD_A_CREDIT);
	}

	public static void clickMakePayment() {

		folioOperations(BUTTON_MAKE_PAYMENT);
	}

	public static void clickButtonAddRoutingRule() {

		folioOperations(BUTTON_ADD_ROUTING_RULE);
	}

	public static void navigatePaymentSection() {
		Selenide.sleep(2000);
		click(NAV_PAYMENT);
		Selenide.sleep(2000);
		Selenide.executeJavaScript("window.scrollBy(0,250);");
	}

	public static void clickTabFolio1() {
		//Selenide.executeJavaScript("window.scrollTo(0, 3070)");
		$(TAB_FOLIO_1).shouldBe(Condition.visible);
		//$(TAB_FOLIO_1).scrollTo();
		click(TAB_FOLIO_1);
		click(LABEL_QUANTITY_FOLIO1);
	}

	public static void clickButtonSelectAllFolio1() {
		Selenide.executeJavaScript("window.scrollTo(0, 3075)");
		click(BUTTON_SELECTALL_FOLIO1);
	}

	public static void clickButtonTransfer() {
		click(BUTTON_TRANSFER);
	}
	
	public static void typeNewFolioName(String folioName){
		clearAndType(INPUT_ADD_NEW_FOLIO_NAME, folioName);
	}
	
	public static void saveNewFolio(){
		click(BUTTON_ADD_NEW_FOLIO_SAVE);
		waitForElementToDisappear(BUTTON_ADD_NEW_FOLIO_SAVE, Configuration.timeout);
	}
	
}
