package com.agilysys.qa.gridIron.pageobject.shared;

import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFPaymentModal.clickButtonApply;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFPaymentModal.clickButtonDone;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFPaymentModal.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFPaymentModal.clickLabelBalance;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFPaymentModal.typePaymentAmount;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFPaymentModal.*;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFRefundModal.*;

import java.math.BigDecimal;

import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFAddPaymentModal;
import com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFPaymentModal;
import com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFRefundModal;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POPayments {

	public static void makeCashPayment(String payment) {
		clickHeaderModal();
		clickLabelBalance();
		typePaymentAmount("Folio 1", new BigDecimal(payment));
		PFPaymentModal.typePassword();
		clickButtonApply();
		clickButtonDone();
	}

	public static void makeCashPayment(String folioName, String paymentMethod, BigDecimal paymentAmount) {
		selectPaymentMethod(folioName, paymentMethod);
		typePaymentAmount(folioName, paymentAmount);
		PFPaymentModal.typeUsername();
		PFPaymentModal.typePassword();
		clickButtonApply();
		clickButtonDone();
	}

	public static void makeCardPayment(String folioName, String paymentMethod, BigDecimal paymentAmount) {
		selectPaymentMethod(folioName, paymentMethod);
		typePaymentAmount(folioName, paymentAmount);
		clickButtonApply();
		clickButtonDone();
	}

	public static void makeCardPayment(String folioName, String paymentMethod, String swipeData,
			BigDecimal paymentAmount) {
		selectPaymentMethod(folioName, "New Credit Card");
		typePaymentAmount(folioName, paymentAmount);
		clickButtonNext();
		PFAddPaymentModal.swipeData(swipeData);
		clickButtonApply();
		clickButtonDone();
	}

	public static void refundPayment(BigDecimal refundAmount, BigDecimal paymentAmount, String reason) {
		PFRefundModal.typeReason(reason);

		PFRefundModal.typeRefundAmount(refundAmount);
//		PFRefundModal.typePaymentAmount(paymentAmount);
		PFRefundModal.typeUsername();
		PFRefundModal.typePassword();
		PFRefundModal.saveRefund();
	}
}