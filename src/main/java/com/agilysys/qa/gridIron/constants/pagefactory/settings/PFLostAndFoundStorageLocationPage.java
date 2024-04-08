package com.agilysys.qa.gridIron.constants.pagefactory.settings;

import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsStorageLocationPage.BUTTON_ADD;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsStorageLocationPage.BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsStorageLocationPage.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsStorageLocationPage.INPUT_CODE;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsStorageLocationPage.INPUT_LOCATION;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsStorageLocationPage.LIST_LINK_LOCATION;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsStorageLocationPage.TAB_LOCATION;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;

public class PFLostAndFoundStorageLocationPage {

	public static void clickTabLocation() {
		click(TAB_LOCATION);
	}

	public static void clickExistingLocation(String location) {
	click($$(LIST_LINK_LOCATION).findBy(Condition.exactText(location)));
	}

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void clickButtonAdd() {
		click(BUTTON_ADD);
	}

	public static void setInputLocation(String locationName) {
		$(INPUT_LOCATION).setValue(locationName);
	}

	public static void setInputCode(String locationCode) {
		$(INPUT_CODE).setValue(locationCode);
	}

	public static void clickButtonSave() {
		click(BUTTON_SAVE);
	}
}
