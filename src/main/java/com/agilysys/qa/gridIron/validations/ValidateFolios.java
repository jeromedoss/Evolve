package com.agilysys.qa.gridIron.validations;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.pms.account.model.Charge;
import com.agilysys.pms.account.model.Credit;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.property.model.LostItem;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsNonInvoicedChargesSection;

public class ValidateFolios {

	protected boolean totalAmount = false;
	protected boolean quantity = false;
	protected boolean taxAmount = false;
	protected boolean displayDate = false;
	protected boolean grandTotalAmount = false;
	protected boolean Payment = false;
	protected boolean chargeAmount = false;
	protected boolean transactionDestinationAccount = false;
	protected boolean transactionSourceAccount = false;

	public ValidateFolios VerifyAll() {
		totalAmount = true;
		quantity = true;
		taxAmount = true;
		displayDate = true;
		grandTotalAmount = true;
		Payment = true;
		chargeAmount = true;
		transactionDestinationAccount = true;
		transactionSourceAccount = true;

		return this;
	}

	public ValidateFolios VerifyRequired() {
		totalAmount = true;
		quantity = true;
		taxAmount = true;
		displayDate = true;
		grandTotalAmount = true;
		chargeAmount = true;
		Payment = true;

		return this;
	}

	public void ValidateARNoninvoice(Charge charge, TransactionItem item) {

		$(LocatorsNonInvoicedChargesSection.LABEL_NON_GROUP_RELATED).shouldBe(Condition.visible);

		Selenide.sleep(2000);

		$(LocatorsNonInvoicedChargesSection.INPUT_SEARCH).setValue(item.getName());
		click(LocatorsNonInvoicedChargesSection.LABEL_GRAND_TOTAL_VALUE);

		if (totalAmount) {

			$(LocatorsNonInvoicedChargesSection.LABEL_TOTAL_VALUE).getText().contains(charge.getAmount().toString());
		}

		if (quantity) {

		}

		if (taxAmount) {

		}

		if (displayDate) {
			$(LocatorsNonInvoicedChargesSection.LABEL_DATE_VALUE).getText()
					.contains(charge.getPostingDate().toString());
		}

		if (grandTotalAmount) {
			$(LocatorsNonInvoicedChargesSection.LABEL_GRAND_TOTAL_VALUE).getText()
					.contains(charge.getAmount().toString());
		}

		if (chargeAmount) {
			$(LocatorsNonInvoicedChargesSection.LABEL_GRAND_TOTAL_VALUE).getText()
					.contains(charge.getAmount().toString());
		}

		if (Payment) {

		}

	}

	public void ValidateARNoninvoice(Credit credit, TransactionItem item) {

		$(LocatorsNonInvoicedChargesSection.LABEL_NON_GROUP_RELATED).shouldBe(Condition.visible);

		Selenide.sleep(2000);

		$(LocatorsNonInvoicedChargesSection.INPUT_SEARCH).setValue(item.getName());
		click(LocatorsNonInvoicedChargesSection.LABEL_GRAND_TOTAL_VALUE);

		if (totalAmount) {

			$(LocatorsNonInvoicedChargesSection.LABEL_TOTAL_VALUE).getText().contains(credit.getAmount().toString());
		}

		if (quantity) {

		}

		if (taxAmount) {

		}

		if (displayDate) {
			$(LocatorsNonInvoicedChargesSection.LABEL_DATE_VALUE).getText()
					.contains(credit.getPostingDate().toString());
		}

		if (grandTotalAmount) {
			$(LocatorsNonInvoicedChargesSection.LABEL_GRAND_TOTAL_VALUE).getText()
					.contains(credit.getAmount().toString());
		}

		if (chargeAmount) {
			$(LocatorsNonInvoicedChargesSection.LABEL_GRAND_TOTAL_VALUE).getText()
					.contains(credit.getAmount().toString());
		}

		if (Payment) {

		}

	}

	public void ValidateHA(Charge charge, TransactionItem item) {

		if (totalAmount) {
		}

		if (quantity) {

		}

		if (taxAmount) {

		}

		if (displayDate) {
		}

		if (grandTotalAmount) {
		}

		if (chargeAmount) {
		}

		if (Payment) {

		}

	}

}
