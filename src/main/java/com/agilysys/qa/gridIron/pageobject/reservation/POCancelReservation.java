package com.agilysys.qa.gridIron.pageobject.reservation;

import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments.LocatorsPaymentMethodTab.MESSAGE_CARD_TYPED;
import static com.codeborne.selenide.Selenide.$;

import java.math.BigDecimal;

import org.openqa.selenium.Keys;

import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFCancelReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationFooterOperations;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class POCancelReservation {

	PFCancelReservationModal pfCancelReservationModal;
	
	String cancellationPolicyName = null;
	boolean overrideCancellationCharge = false;
	BigDecimal flatFee = null;
	BigDecimal percentage = null;
	String paymentMethodName = null;
	boolean setCredentials = false;
	String reason = null;
	String swipeData = null;
	PFReservationFooterOperations pfReservationFooterOperations;
	
	public POCancelReservation(){
		pfCancelReservationModal = new PFCancelReservationModal();
		pfReservationFooterOperations = new PFReservationFooterOperations();
	}

	public String getCancellationPolicyName() {
		return cancellationPolicyName;
	}

	public POCancelReservation setCancellationPolicyName(String cancellationPolicyName) {
		this.cancellationPolicyName = cancellationPolicyName;
		return this;
	}

	public boolean isOverrideCancellationCharge() {
		return overrideCancellationCharge;
	}

	public POCancelReservation setOverrideCancellationCharge(boolean overrideCancellationCharge) {
		this.overrideCancellationCharge = overrideCancellationCharge;
		return this;
	}

	public BigDecimal getFlatFee() {
		return flatFee;
	}

	public POCancelReservation setFlatFee(BigDecimal flatFee) {
		this.flatFee = flatFee;
		return this;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public POCancelReservation setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
		return this;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	public POCancelReservation setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
		return this;
	}

	public boolean isSetCredentials() {
		return setCredentials;
	}

	public POCancelReservation setSetCredentials(boolean setCredentials) {
		this.setCredentials = setCredentials;
		return this;
	}

	public String getReason() {
		return reason;
	}

	public POCancelReservation setReason(String reason) {
		this.reason = reason;
		return this;
	}

	public String getSwipeData() {
		return swipeData;
	}

	public POCancelReservation setSwipeData(String swipeData) {
		this.swipeData = swipeData;
		return this;
	}
	
	public void cancelReservation(){
		pfReservationFooterOperations.clickCancelReservation();
		pfCancelReservationModal.selectCancellationPolicy(cancellationPolicyName);
		pfCancelReservationModal.clickChangeCancellationCharge(overrideCancellationCharge);
		pfCancelReservationModal.typeFlatFee(flatFee);
		pfCancelReservationModal.typePercentage(percentage);
		pfCancelReservationModal.selectCancellationPaymentMethod(paymentMethodName);
		if(swipeData!= null){			
			Selenide.sleep(3000);
			pfCancelReservationModal.clickCreditCardFocus();
			Selenide.sleep(3000);
			Selenide.actions().sendKeys(Keys.NUMPAD0, swipeData, Keys.ENTER).build().perform();
			Selenide.sleep(3000);
			pfCancelReservationModal.typeReason(reason);
			$(MESSAGE_CARD_TYPED).shouldBe(Condition.visible).hover();
		}
		if(setCredentials){
			pfCancelReservationModal.typeCredentials(Endpoints.getUserName(), Endpoints.getPassword());
		}
		pfCancelReservationModal.typeReason(reason);
		if(setCredentials || (paymentMethodName!=null && paymentMethodName.contains("Visa")))
			pfCancelReservationModal.payAndConfirmCancellation();
		else
			pfCancelReservationModal.confirmCancellation();
	}
	
	public void undoCancelReservation(){
		pfReservationFooterOperations.clickUndoCancelReservation();
		pfCancelReservationModal.confirmUndoCancellation();
	}
	
}
