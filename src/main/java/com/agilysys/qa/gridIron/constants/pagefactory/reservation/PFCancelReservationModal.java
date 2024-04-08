package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import java.math.BigDecimal;

import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsCancelModal;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsFooterButtonsSection;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.$;

public class PFCancelReservationModal extends PageFactoryBase{
	
	public void selectCancellationPolicy(String cancellationPolicy){
		selectByText(LocatorsCancelModal.DROPDOWN_CANCELLATION_POLICY, LocatorsCancelModal.DROPDOWN_OPTIONS_CANCELLATION_POLICY, cancellationPolicy);
	}
	
	public void clickChangeCancellationCharge(boolean overrideCancellationCharge){
		if(overrideCancellationCharge){
			click(LocatorsCancelModal.CHECKBOX_CHANGE_CANCELLATION_CHARGE);
		}
	}
	
	public void typeFlatFee(BigDecimal flatFee){
		if(flatFee != null){
			click(LocatorsCancelModal.RADIO_FLAT_FEE);
			clearAndType(LocatorsCancelModal.INPUT_FLAT_FEE, flatFee.toString());
		}
	}
	
	public void typePercentage(BigDecimal percentage){
		if(percentage != null){
			click(LocatorsCancelModal.RADIO_PERCENTAGE);
			clearAndType(LocatorsCancelModal.INPUT_PERCENTAGE, percentage.toString());
		}		
	}
	
	public void selectCancellationPaymentMethod(String paymentMethod){
		selectByText(LocatorsCancelModal.DROPDOWN_PAYMENT_METHOD, LocatorsCancelModal.DROPDOWN_OPTIONS_PAYMENT_METHOD, paymentMethod);
	}
	
	public void clickCreditCardFocus(){
		click(LocatorsCancelModal.CARD_FOCUS);
	}
	public void typeReason(String reason){
		type(LocatorsCancelModal.TEXTAREA_REASON, reason);
	}
	
	public void typeCredentials(String username, String password){
		clearAndType(LocatorsCancelModal.INPUT_USERNAME, username);
		clearAndType(LocatorsCancelModal.INPUT_PASSWORD, password);
	}
	
	public void confirmCancellation(){
		click(LocatorsCancelModal.BUTTON_CONFIRM_CANCELLATION);
		waitForElementToDisappear(LocatorsCancelModal.BUTTON_CONFIRM_CANCELLATION, Configuration.timeout);
	}

	public void payAndConfirmCancellation(){
		click(LocatorsCancelModal.BUTTON_PAY_AND_CONFIRM_CANCELLATION);
		waitForElementToDisappear(LocatorsCancelModal.BUTTON_CONFIRM_CANCELLATION, Configuration.timeout);
	}
	
	public void confirmUndoCancellation(){
		click(LocatorsCancelModal.BUTTON_CONFIRM_UNDO_CANCEL);
		waitForElementToDisappear(LocatorsCancelModal.BUTTON_CONFIRM_UNDO_CANCEL, Configuration.timeout);
		$(LocatorsFooterButtonsSection.BUTTON_CANCEL_RESERVATION).shouldBe(Condition.visible);
	}
	
}
