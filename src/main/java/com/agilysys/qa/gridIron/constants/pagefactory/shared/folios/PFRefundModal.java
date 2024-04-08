package com.agilysys.qa.gridIron.constants.pagefactory.shared.folios;


import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsRefundModal.*;
import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsRefundModal.INPUT_REFUND_AMOUNT;

import java.math.BigDecimal;

import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.HelperMethods;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Configuration;

public class PFRefundModal extends PageFactoryBase{

	public static void typeRefundAmount(BigDecimal refundAmount){
		if(refundAmount!=null){
			clearAndType(INPUT_REFUND_AMOUNT, refundAmount.toString());
		}		
	}
	
	public static void typePaymentAmount(BigDecimal paymentAmount){
		if(paymentAmount != null){
			clearAndType(INPUT_PAYMENT_AMOUNT, paymentAmount.toString());
		}		
	}
	
	public static void typePassword() {
		clearAndType(INPUT_PASSWORD, Endpoints.getPassword());
	}
	
	public static void typeUsername() {
		clearAndType(INPUT_USERNAME, Endpoints.getUserName());
	}
	
	public static void typeReason(String reason){
		type(TEXTAREA_REASON, reason);
	}
	
	public static void saveRefund(){
		click(BUTTON_SAVE);
		waitForElementToDisappear(BUTTON_SAVE, Configuration.timeout);
	}
}
