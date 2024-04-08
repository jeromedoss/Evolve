package com.agilysys.qa.gridIron.pageobject.login;

import static com.agilysys.qa.gridIron.constants.locators.login.LocatorsPropertySelectDialog.FORM_MODAL;
import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFPropertySelectDialog.clickButtonOk;
import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFPropertySelectDialog.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFPropertySelectDialog.setPropertyName;
import static com.codeborne.selenide.Selenide.$$;

/*
 * *Author - Harish Baskaran - Aug/2018
 */
public class POPropertySelectDialog {

	public void PropertySelect(String PropertyName) {

		if ($$(FORM_MODAL).size() != 0) {

			setPropertyName(PropertyName);
			clickHeaderModal();
			clickButtonOk();
		}
	}
}
