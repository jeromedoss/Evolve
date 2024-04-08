package com.agilysys.qa.gridIron.builders.groups;

import static com.agilysys.qa.gridIron.builders.shared.folios.AddAChargeModal.stepAddChargeForAnItem;
import static com.agilysys.qa.gridIron.builders.shared.folios.TransferModal.stepTransfer;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupFolios.clickButtonAddACharge;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupFolios.clickButtonAddACredit;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupFolios.clickButtonMakeAPayment;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupFolios.clickButtonSelectAllFolio1;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupFolios.clickButtonTransfer;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupFolios.clickTabFolio1;
import static com.agilysys.qa.gridIron.pageobject.shared.POPayments.makeCashPayment;

import com.agilysys.pms.account.model.TransactionItem;

import static com.agilysys.qa.gridIron.pageobject.shared.POAddCreditModal.addCredit;

/*
 * *Author - Harish Baskaran - 2018
 */
public class GroupFolios {

	String ChargeName = null;

	public GroupFolios(String ChargeName) {
		this.ChargeName = ChargeName;

	}

	public GroupFolios(TransactionItem item) {
		this.ChargeName = item.getName();

	}

	public void flowAddCharge() {

		clickButtonAddACharge();
		stepAddChargeForAnItem(ChargeName);

	}

	public void flowMakePayment(String Payment) {

		clickButtonMakeAPayment();
		makeCashPayment(Payment);

	}

	public void flowAddCredit() {

		clickButtonAddACredit();
		addCredit(ChargeName);

	}

	public void flowTransfer(String Input) {

		clickTabFolio1();
		clickButtonSelectAllFolio1();
		clickButtonTransfer();
		stepTransfer(Input);

	}

}
