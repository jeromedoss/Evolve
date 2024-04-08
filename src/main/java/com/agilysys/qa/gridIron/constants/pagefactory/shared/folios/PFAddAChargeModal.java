package com.agilysys.qa.gridIron.constants.pagefactory.shared.folios;

import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddAChargeModal.BUTTON_NEXT;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddAChargeModal.BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddAChargeModal.HA_BUTTON_DONE;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddAChargeModal.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddAChargeModal.INPUT_SEARCH;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddAChargeModal.LIST_BUTTON_ADD;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PFAddAChargeModal {

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void setInputSearch(String chargeName) {
		$(INPUT_SEARCH).setValue(chargeName);
	}

	public static void clickListButtonAdd() {
		click(LIST_BUTTON_ADD);
	}

	public static void clickButtonNext() {
		click(BUTTON_NEXT);
	}

	public static void clickButtonSave() {
		click(BUTTON_SAVE);
	}

	public static void clickButtonDone() {
		click(HA_BUTTON_DONE);
	}
}
