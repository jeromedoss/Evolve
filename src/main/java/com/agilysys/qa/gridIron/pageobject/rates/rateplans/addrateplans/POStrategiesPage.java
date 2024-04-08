package com.agilysys.qa.gridIron.pageobject.rates.rateplans.addrateplans;

import static com.agilysys.qa.gridIron.constants.locators.rates.LocatorsRatesPage.LIST_CHECKBOXES_ROOM_TYPES;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsStrategiesModal.CHECKBOX_SELECTALL_MODAL_ROOM;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsStrategiesModal.INPUT_BASE_RATE;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsStrategiesModal.LINK_ROOMS;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsStrategiesModal.LINK_STRATEGIES;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsStrategiesModal.MODAL_ROOM_BUTTON_SAVE;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.math.BigDecimal;

import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import com.codeborne.selenide.SelenideElement;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POStrategiesPage {

	public void FourthModal(BigDecimal basePrice) {

		click(LINK_STRATEGIES);

		$(INPUT_BASE_RATE).setValue(basePrice.toString());

		Selenide.sleep(3000);

		click(LINK_ROOMS);

		Selenide.sleep(3000);

		if ($$(CHECKBOX_SELECTALL_MODAL_ROOM).size() != 0)
			click(CHECKBOX_SELECTALL_MODAL_ROOM);

		else if ($$(LIST_CHECKBOXES_ROOM_TYPES).size() != 0)
			for (SelenideElement element : $$(LIST_CHECKBOXES_ROOM_TYPES))
				click(element);

		click(MODAL_ROOM_BUTTON_SAVE);
	}
}
