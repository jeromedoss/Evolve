package com.agilysys.qa.gridIron.constants.pagefactory.shared.widgets;

import static com.agilysys.qa.gridIron.constants.locators.shared.widgets.Calendars.BUTTON_CENTER;
import static com.agilysys.qa.gridIron.constants.locators.shared.widgets.Calendars.BUTTON_CLEAR;
import static com.agilysys.qa.gridIron.constants.locators.shared.widgets.Calendars.BUTTON_NEXT_MONTH;
import static com.agilysys.qa.gridIron.constants.locators.shared.widgets.Calendars.BUTTON_PREVIOUS_MONTH;
import static com.agilysys.qa.gridIron.constants.locators.shared.widgets.Calendars.BUTTON_TODAY;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

public class PFCalendars {

	public static void clickButtonClear() {
		click(BUTTON_CLEAR);
	}

	public static void clickButtonToday() {
		click(BUTTON_TODAY);
	}

	public static void clickButtonPreviousMonth() {
		click(BUTTON_PREVIOUS_MONTH);
	}

	public static void clickButtonNextMonth() {
		click(BUTTON_NEXT_MONTH);
	}

	public static void clickButtonCenter() {
		click(BUTTON_CENTER);
	}

}
