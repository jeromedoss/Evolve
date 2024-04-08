package com.agilysys.qa.gridIron.constants.pagefactory.groups;

import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsReservations.BUTTON_CREATE_RESERVATIONS;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsReservations.BUTTON_GROUP_ADD_STAGE;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsReservations.BUTTON_MAKE_RESERVATION;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsReservations.STAGING_CHECKBOX_SELECT_ONE;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;

public class PFGroupReservations extends PageFactoryBase {

	public static void clickButtonAdd() {
		click(BUTTON_GROUP_ADD_STAGE);
	}

	public static void clickStagingCheckbox() {
		click(STAGING_CHECKBOX_SELECT_ONE);
	}
	
	public static void clickButtonCreateReservation() {
		click(BUTTON_CREATE_RESERVATIONS);
	}

	public static void clickButtonMakeReservation() {
		click(BUTTON_MAKE_RESERVATION);
	}
	
}
