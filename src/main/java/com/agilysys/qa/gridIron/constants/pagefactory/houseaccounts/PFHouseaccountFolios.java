package com.agilysys.qa.gridIron.constants.pagefactory.houseaccounts;

import static com.agilysys.qa.gridIron.constants.locators.houseaccounts.LocatorsHouseaccountFolios.BUTTON_ADD_CHARGE;
import static com.agilysys.qa.gridIron.constants.locators.houseaccounts.LocatorsHouseaccountFolios.BUTTON_ADD_CREDIT;
import static com.agilysys.qa.gridIron.constants.locators.houseaccounts.LocatorsHouseaccountFolios.BUTTON_FOLIO_MORE;
import static com.agilysys.qa.gridIron.constants.locators.houseaccounts.LocatorsHouseaccountFolios.BUTTON_MAKE_PAYMENT;
import static com.agilysys.qa.gridIron.constants.locators.houseaccounts.LocatorsHouseaccountFolios.BUTTON_TRANSFER;
import static com.agilysys.qa.gridIron.constants.locators.houseaccounts.LocatorsHouseaccountFolios.FOLIO_SELECTALL_BUTTON;
import static com.agilysys.qa.gridIron.constants.locators.houseaccounts.LocatorsHouseaccountFolios.TAB_FOLIO1;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFHouseaccountFolios {

	public static void clickButtonAddCharge() {
		click(BUTTON_FOLIO_MORE);
		click(BUTTON_ADD_CHARGE);
	}

	public static void clickButtonAddCredit() {
		click(BUTTON_FOLIO_MORE);
		click(BUTTON_ADD_CREDIT);
	}

	public static void clickButtonMakePayment() {
		click(BUTTON_FOLIO_MORE);
		click(BUTTON_MAKE_PAYMENT);
	}

	public static void clickTabFolio1() {
		click(TAB_FOLIO1);
		Selenide.sleep(2000);
	}

	public static void clickButtonSelectAllFolio() {
		click(FOLIO_SELECTALL_BUTTON);
	}

	public static void clickButtonTransfer() {
		click(BUTTON_TRANSFER);
	}
}