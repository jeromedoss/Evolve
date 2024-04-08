package com.agilysys.qa.gridIron.builders.houseaccounts;

import static com.agilysys.qa.gridIron.builders.shared.folios.AddAChargeModal.stepAddChargeForAnItem;
import static com.agilysys.qa.gridIron.builders.shared.folios.TransferModal.stepTransfer;
import static com.agilysys.qa.gridIron.constants.pagefactory.houseaccounts.PFHouseaccountFolios.clickButtonAddCharge;
import static com.agilysys.qa.gridIron.constants.pagefactory.houseaccounts.PFHouseaccountFolios.clickButtonAddCredit;
import static com.agilysys.qa.gridIron.constants.pagefactory.houseaccounts.PFHouseaccountFolios.clickButtonMakePayment;
import static com.agilysys.qa.gridIron.constants.pagefactory.houseaccounts.PFHouseaccountFolios.clickButtonSelectAllFolio;
import static com.agilysys.qa.gridIron.constants.pagefactory.houseaccounts.PFHouseaccountFolios.clickButtonTransfer;
import static com.agilysys.qa.gridIron.constants.pagefactory.houseaccounts.PFHouseaccountFolios.clickTabFolio1;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddAChargeModal.clickButtonDone;
import static com.agilysys.qa.gridIron.pageobject.shared.POPayments.makeCashPayment;

import com.agilysys.pms.account.model.TransactionItem;

import static com.agilysys.qa.gridIron.pageobject.shared.POAddCreditModal.addCredit;


/*
 * *Author - Harish Baskaran - 2018
 */
public class HAFolios {

	String ChargeName = null;

	public HAFolios(String ChargeName) {
		this.ChargeName = ChargeName;

	}

	public HAFolios(TransactionItem item) {
		this.ChargeName = item.getName();

	}

	public void flowAddChargeForAnItem() {

		clickButtonAddCharge();
		stepAddChargeForAnItem(ChargeName);
		clickButtonDone();
	}

	public void flowTransferfromMoreButton(String Input) {

		clickTabFolio1();
		clickButtonSelectAllFolio();
		clickButtonTransfer();
		stepTransfer(Input);

	}

	public void flowAddCreditForAnItem() {

		clickButtonAddCredit();
		addCredit(ChargeName);

	}

	public void flowMakePayment(String Payment) {

		clickButtonMakePayment();
		makeCashPayment(Payment);

	}

}
