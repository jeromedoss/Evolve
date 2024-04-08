package com.agilysys.qa.gridIron.pageobject.rates.packages;

import static com.agilysys.qa.gridIron.constants.locators.rates.packages.LocatorsPackagePage.BUTTON_NEW_PACKAGE;
import static com.agilysys.qa.gridIron.constants.locators.rates.packages.LocatorsPackagePage.TAB_PACKAGE;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsRatePage.BUTTON_MORE;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POPackageMainPage {

	public void AddPackageRatePlan() {

		click(TAB_PACKAGE);

		click(BUTTON_MORE);

		click(BUTTON_NEW_PACKAGE);
	}
}