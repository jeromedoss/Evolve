package com.agilysys.qa.gridIron.pageobject.rates.rateplans.addrateplans;

import static com.agilysys.qa.gridIron.constants.locators.rates.LocatorsRatesPage.HEADER_MODAL_ADD_RATE;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.DROPDOWN_CALENDAR;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.DROPDOWN_CATEGORY;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.DROPDOWN_POSTAS;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.DROPDOWN_VALUE_CALENDAR_BAR;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.DROPDOWN_VALUE_CATEGORY_TRANSIENT;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.DROPDOWN_VALUE_NIGHTLY_ROOM_CHARGE;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.INPUT_ADULT;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.INPUT_CHILD;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.INPUT_CODE;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal.INPUT_NAME;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/*
 * *Author - Harish Baskaran - 2018
 */
public class PODetailsPage {

	public void FirstModal(String RateName, String RateCode, String AdultCount, String ChildCount) {

		click(HEADER_MODAL_ADD_RATE);

		$(INPUT_NAME).setValue(RateName);

		$(INPUT_CODE).setValue(RateCode);

		$$(INPUT_ADULT).get(0).setValue(AdultCount);

		$$(INPUT_CHILD).get(1).setValue(ChildCount);

		click(DROPDOWN_CALENDAR);

		click(DROPDOWN_VALUE_CALENDAR_BAR);

		click(DROPDOWN_CATEGORY);

		click(DROPDOWN_VALUE_CATEGORY_TRANSIENT);

		click(DROPDOWN_POSTAS);

		click(DROPDOWN_VALUE_NIGHTLY_ROOM_CHARGE);

		//	$(BUTTON_NEXT);

	}
}
