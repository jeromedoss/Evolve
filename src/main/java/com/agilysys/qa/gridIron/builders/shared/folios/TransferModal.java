package com.agilysys.qa.gridIron.builders.shared.folios;

import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFTransferModal.clickARButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFTransferModal.clickButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFTransferModal.selectARTransferMatch;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFTransferModal.selectSearchForAccount;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFTransferModal.selectTransferMatch;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFTransferModal.setARTextAreaReason;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFTransferModal.setTextAreaReason;

/*
 * *Author - Harish Baskaran - 2018
 */
public class TransferModal {

	public static void stepTransfer(String Input) {

		selectSearchForAccount();
		selectTransferMatch(Input);
		setTextAreaReason();
		clickButtonSave();
	}

	public static void stepTransferFromAR(String Input) {

		selectARTransferMatch(Input);
		setARTextAreaReason();
		clickARButtonSave();
	}
}