package com.agilysys.qa.gridIron.constants.pagefactory.groups;

import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsFoliosAndRoutingRulesSection.BUTTON_ADD_A_CHARGE;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsFoliosAndRoutingRulesSection.BUTTON_ADD_A_CREDIT;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsFoliosAndRoutingRulesSection.BUTTON_MAKE_A_PAYMENT;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsFoliosAndRoutingRulesSection.BUTTON_MORE;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsFoliosAndRoutingRulesSection.BUTTON_TRANSFER;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupsCommon.BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.BUTTON_SELECTALL_FOLIO1;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.LABEL_QUANTITY_FOLIO1;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.TAB_FOLIO_1;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Configuration;
import org.apache.tomcat.jni.Thread;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFGroupFolios extends PageFactoryBase {

	public static void clickButtonAddACharge() {
		clickAndWaitForNextElement(BUTTON_MORE,BUTTON_ADD_A_CHARGE);
		jsClick(BUTTON_ADD_A_CHARGE);
	}

	public static void clickButtonMakeAPayment() {
		clickAndWaitForNextElement(BUTTON_MORE,BUTTON_MAKE_A_PAYMENT);
		jsClick(BUTTON_MAKE_A_PAYMENT);
	}

	public static void clickButtonAddACredit() {
        clickAndWaitForNextElement(BUTTON_MORE,BUTTON_ADD_A_CREDIT);
	    jsClick(BUTTON_ADD_A_CREDIT);
	}

	public static void clickTabFolio1() {
		click(TAB_FOLIO_1);
		Selenide.sleep(4000);
		click(LABEL_QUANTITY_FOLIO1);
	}

	public static void clickButtonSelectAllFolio1() {
		click(BUTTON_SELECTALL_FOLIO1);
	}

	public static void clickButtonTransfer() {
		click(BUTTON_TRANSFER);
	}
}