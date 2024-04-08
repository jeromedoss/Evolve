package com.agilysys.qa.gridIron.constants.pagefactory.shared.folios;

import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddACreditModal.BUTTON_DONE;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddACreditModal.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddACreditModal.INPUT_SEARCH;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.math.BigDecimal;

import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddACreditModal;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFAddACreditModal extends PageFactoryBase{

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void typeSearch(String itemName) {
		type(INPUT_SEARCH, itemName);
	}

	public static void selectItem(String itemName) {
		click(LocatorsAddACreditModal.getItemByName(itemName));
		Selenide.sleep(2000);
	}
	
	public static void selectSource(String source){
		selectByText(LocatorsAddACreditModal.DROPDOWN_SOURCE, LocatorsAddACreditModal.DROPDOWN_OPTIONS_SOURCE, source);
	}
	
	public static void selectFolio(String folio){
		selectByText(LocatorsAddACreditModal.DROPDOWN_FOLIO, LocatorsAddACreditModal.DROPDOWN_OPTIONS_FOLIO, folio);
	}
	
	public static void typeAmount(BigDecimal amount){
		clearAndType(LocatorsAddACreditModal.INPUT_AMOUNT, amount.toString());
	}
	
	public static void typeReason(String reason){
		type(LocatorsAddACreditModal.TEXTAREA_REASON, reason);
	}

	public static void clickDone() {
//		$(BUTTON_DONE).shouldBe(visible,enabled).vicks
		click(BUTTON_DONE);
		waitForElementToDisappear(BUTTON_DONE, Configuration.timeout);
	}
}
