package com.agilysys.qa.gridIron.builders.profiles.company;

import static com.agilysys.qa.gridIron.builders.shared.folios.AddAChargeModal.stepAddChargeForAnItem;
import static com.agilysys.qa.gridIron.builders.shared.folios.TransferModal.stepTransferFromAR;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.PFARFolios.*;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.ar.noninvoice.PFNonInvoicesSection.clickButtonAddACharge;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.ar.noninvoice.PFNonInvoicesSection.clickButtonMore;
import static com.agilysys.qa.gridIron.pageobject.shared.POAddCreditModal.addCredit;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.PFARFolios;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class ARFolios {

	String ChargeName = null;

	public ARFolios(String ChargeName) {
		this.ChargeName = ChargeName;
	}

	public ARFolios(TransactionItem item) {
		this.ChargeName = item.getName();

	}

	private void navigate() {

		Selenide.executeJavaScript("window.scrollTo(0, 0);");

		$(byText("Preferred")).scrollIntoView(true);
	}

	public void AddCharge() {

		navigate();
		clickButtonMore();
		clickButtonAddACharge();

		stepAddChargeForAnItem(ChargeName);

	}

	public void MakePayment() {

		navigate();

		clickNonInvoiceCheckBoxSelectAll();
		clickButtonCreateInvoice();
		clickModalButtonSave();
		clickInvoiceCheckBoxSelectAll();
		clickButtonMakePayment();
		selectPaymentMethod("Cash");
		setCredentials();
		clickButtonARModalMakePayment();

	}

	public void AddCredit() {

		navigate();
		Selenide.sleep(2000);
		clickButtonAddACredit();
		addCredit(ChargeName);

	}

	public void Transfer(String Input) {

		navigate();
		clickNonInvoiceCheckBoxSelectAll();
		clickButtonTransferCharges();
		stepTransferFromAR(Input);

	}

}
