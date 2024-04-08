package com.agilysys.qa.gridIron.constants.pagefactory.login;

import static com.agilysys.qa.gridIron.constants.locators.login.LocatorsPropertySelectDialog.BUTTON_OK;
import static com.agilysys.qa.gridIron.constants.locators.login.LocatorsPropertySelectDialog.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.login.LocatorsPropertySelectDialog.TYPEHEAD_PROPERTY_NAME;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFPropertySelectDialog {

	public static void setPropertyName(String propertyName) {
		click(TYPEHEAD_PROPERTY_NAME);
		Selenide.actions().click().sendKeys(propertyName).build().perform();
	}

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void clickButtonOk() {
		click(BUTTON_OK);

	}
}
