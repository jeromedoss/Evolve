package com.agilysys.qa.gridIron.pageobject.rates.rateplans;

import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsRatePage.BUTTON_MORE;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsRatePage.BUTTON_NEW_RATE_PLAN;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsRatePage.TAB_RATE_PLAN;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

/*
 * *Author - Harish Baskaran - 2018
 */
public class PORateMainPage {

	public void AddRatePlan() {

		click(TAB_RATE_PLAN);

		click(BUTTON_MORE);

		click(BUTTON_NEW_RATE_PLAN);
	}

}
