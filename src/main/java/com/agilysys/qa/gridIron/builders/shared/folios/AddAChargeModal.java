package com.agilysys.qa.gridIron.builders.shared.folios;

import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddAChargeModal.clickButtonNext;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddAChargeModal.clickButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddAChargeModal.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddAChargeModal.clickListButtonAdd;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddAChargeModal.setInputSearch;

/*
 * *Author - Harish Baskaran - 2018
 */
public class AddAChargeModal {

	public static void stepAddChargeForAnItem(String chargeName) {

		clickHeaderModal();
		setInputSearch(chargeName);
		clickListButtonAdd();
		clickButtonNext();
		clickButtonSave();

	}

	public static void stepSelectItem(String chargeName) {

		setInputSearch(chargeName);
		clickListButtonAdd();

	}
}
