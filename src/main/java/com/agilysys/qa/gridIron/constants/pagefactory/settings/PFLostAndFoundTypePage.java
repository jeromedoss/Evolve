package com.agilysys.qa.gridIron.constants.pagefactory.settings;

import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsTypePage.BUTTON_ADD;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsTypePage.BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsTypePage.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsTypePage.INPUT_CODE;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsTypePage.INPUT_TYPE;
import static com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsTypePage.LIST_LINK_NAME;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;

public class PFLostAndFoundTypePage {

	public static void clickButtonAdd() {
		click(BUTTON_ADD);
	}

	public static void setInputType(String type) {
		$(INPUT_TYPE).setValue(type);
	}

	public static void setInputCode(String code) {
		$(INPUT_CODE).setValue(code);
	}

	public static void clickButtonSave() {
		click(BUTTON_SAVE);
	}

	public static void clickExistingType(String existingType) {
		$$(LIST_LINK_NAME).findBy(Condition.exactText(existingType)).shouldBe(Condition.visible);
	}

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

}
