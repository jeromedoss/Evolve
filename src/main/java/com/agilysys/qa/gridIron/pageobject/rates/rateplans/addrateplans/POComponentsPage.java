package com.agilysys.qa.gridIron.pageobject.rates.rateplans.addrateplans;

import static com.agilysys.qa.gridIron.builders.shared.folios.AddAChargeModal.stepSelectItem;
import static com.agilysys.qa.gridIron.constants.locators.rates.packages.LocatorsComponentsModal.BUTTON_ADD_COMPONENTS;
import static com.agilysys.qa.gridIron.constants.locators.rates.packages.LocatorsComponentsModal.BUTTON_NEXT_1;
import static com.agilysys.qa.gridIron.constants.locators.rates.packages.LocatorsComponentsModal.BUTTON_NEXT_2;
import static com.agilysys.qa.gridIron.constants.locators.rates.packages.LocatorsComponentsModal.LINK_COMPONENTS;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POComponentsPage {

	public void FifthModal(String ChargeName) {

	//	$(BUTTON_NEXT);

		click(LINK_COMPONENTS);

		click(BUTTON_ADD_COMPONENTS);

		// String Path = PO.getPackagechargerow() + "1" +
		// PO.getPackagechargeaddbutton();
		//
		// $(By.xpath(Path));

		stepSelectItem(ChargeName);

		click(BUTTON_NEXT_1);

		click(BUTTON_NEXT_2);

	}
}
