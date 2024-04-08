package com.agilysys.qa.gridIron.constants.pagefactory.shared.folios;

import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsRoutingRuleModal.*;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsRoutingRuleModal.APPROPRIATE_FOLIO;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFRoutingRulesModal {

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void clickButtonAnyCategory() {
		$(EXPAND_CHARGES).scrollIntoView(false);
		click(EXPAND_CHARGES);
		Selenide.sleep(2000);
		click(BUTTON_ANY_CATEGORY);
		Selenide.sleep(2000);
	}

	public static void selectSearchForAnAccount() {
		$(EXPAND_DESTINATION).scrollIntoView(false);
		click(EXPAND_DESTINATION);
		$(TYPEHEAD_DESTINATION).scrollIntoView(false);
		click(TYPEHEAD_DESTINATION);
		Selenide.actions().click().sendKeys("Search accounts").build().perform();
		//$(HEADER_MODAL).hover();
	}

	public static void setInputDestinationAccountName(String Input) {
		$(INPUT_DESTINATION_ACCOUNT_NAME).setValue(Input);
	}

	public static void clickSelectDestination() {
		click(SELECT_DESTINATION);
		click(FOLIO_DROPDOWN);
		click(APPROPRIATE_FOLIO);
	}

	public static void clickButtonSave() {
		click(BUTTON_SAVE);
	}

}
