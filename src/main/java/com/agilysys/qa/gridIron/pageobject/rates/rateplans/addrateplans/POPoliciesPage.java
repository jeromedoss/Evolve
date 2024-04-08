package com.agilysys.qa.gridIron.pageobject.rates.rateplans.addrateplans;

import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsPoliciesModal.DROPDOWN_CANCELLATION_POLICY;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsPoliciesModal.DROPDOWN_LIST_FIRST_CANCELLATION_POLICY;
import static com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsPoliciesModal.LINK_POLICIES;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POPoliciesPage {

	public void SecondModal() {

		click(LINK_POLICIES);

		click(DROPDOWN_CANCELLATION_POLICY);

		click(DROPDOWN_LIST_FIRST_CANCELLATION_POLICY);

	//	$(BUTTON_NEXT_2);

	}

}
