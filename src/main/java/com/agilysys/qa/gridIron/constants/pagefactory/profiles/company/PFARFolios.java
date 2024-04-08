package com.agilysys.qa.gridIron.constants.pagefactory.profiles.company;

import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkout.LocatorsBatchCheckOutSection.DROPDOWN_VALUE_FORCE_CHECKOUT;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.invoice.LocatorsInvoicesSection.*;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsCreateInvoicesDialog.MODAL_BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsNonInvoicedChargesSection.BUTTON_ADD_A_CREDIT;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsNonInvoicedChargesSection.BUTTON_CREATE_INVOICE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsNonInvoicedChargesSection.BUTTON_MORE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsNonInvoicedChargesSection.BUTTON_TRANSFER_CHARGES;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsNonInvoicedChargesSection.CHECBOX_SELECTALL;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsMakePaymentModal.BUTTON_AR_MODAL_MAKE_PAYMENT;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.invoice.LocatorsInvoicesSection;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFARFolios extends PageFactoryBase {

	public static void clickNonInvoiceCheckBoxSelectAll() {
		click(CHECBOX_SELECTALL);

	}

	public static void clickInvoiceCheckBoxSelectAll() {
		click(LocatorsInvoicesSection.CHECKBOX_SELECTALL);

	}

	public static void clickButtonCreateInvoice() {
		Selenide.sleep(5000);
		click(BUTTON_CREATE_INVOICE);
		Selenide.sleep(2000);
	}

	public static void clickModalButtonSave() {
		click(MODAL_BUTTON_SAVE);
		Selenide.sleep(2000);
	}

	public static void clickButtonMakePayment() {
		click(BUTTON_MAKE_PAYMENT);
		Selenide.sleep(3000);
	}

	public static void clickButtonARModalMakePayment() {
		click(BUTTON_AR_MODAL_MAKE_PAYMENT);
		Selenide.sleep(4000);
		/*if ($(BUTTON_AR_MODAL_MAKE_PAYMENT).is(Condition.visible))
			click(BUTTON_AR_MODAL_MAKE_PAYMENT);*/
	}

	public static void clickButtonAddACredit() {
		click(BUTTON_MORE);
		click(BUTTON_ADD_A_CREDIT);
	}

	public static void clickButtonTransferCharges() {
		click(BUTTON_MORE);
		click(BUTTON_TRANSFER_CHARGES);
	}

	public static void selectPaymentMethod(String paymentMethod) {
		selectByText(DROPDOWN_PAYMENT, LIST_DROPDOWN_PAYMENT, paymentMethod);
	}

	public static void setCredentials() {
		clearAndType(INPUT_USERNAME, Endpoints.getUserName());
		clearAndType(INPUT_PASSWORD, Endpoints.getPassword());
	}
}