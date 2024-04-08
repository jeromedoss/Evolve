package com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile;

import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.BUTTON_CREATE;
import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.BUTTON_DONE;
import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.CHECKBOX_TYPE_MEETING_ROOM;
import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.DROPDOWN_CATEGORY;
import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.INPUT_NAME;
import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.LINK_CREATE_ACCOUNT;
import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.LIST_HOUSEACCOUNTS;
import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.TAB_ACCOUNTS;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFHouseAccountPage extends PageFactoryBase {

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void setInputName(String accountName) {
		click(INPUT_NAME);
		$(INPUT_NAME).sendKeys(accountName);
		Selenide.sleep(4000);
	}

	public static void setDropdownCategory(String type) {
		click(DROPDOWN_CATEGORY);
		Selenide.actions().sendKeys(type).build().perform();
		Selenide.sleep(4000);
	}

	public static void clickButtonCreate() {
		click(BUTTON_CREATE);
	}

	public static void clickTabAccounts() {
		click(TAB_ACCOUNTS);
	}

	public static void clickLinkCreateAccount() {
		click(LINK_CREATE_ACCOUNT);
	}

	public static void selectListHouseAccounts(String accountName) {
		$$(LIST_HOUSEACCOUNTS).findBy(Condition.text(accountName)).click();
	}

	public static void clickCheckboxTypeMeetingRoom() {
		click(CHECKBOX_TYPE_MEETING_ROOM);
	}

	public static void clickButtonDone() {
		click(BUTTON_DONE);
	}
}