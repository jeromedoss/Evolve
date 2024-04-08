package com.agilysys.qa.gridIron.pageobject.reservation;

import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFAddPaymentModal;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFPaymentMethodsSection;

import com.agilysys.qa.gridIron.data.models.ReservationPaymentMethod;
import com.agilysys.qa.helpers.ThreadHelper;

public class POReservationPaymentMethods {

	public static void addPaymentMethod(ReservationPaymentMethod paymentMethod){
		PFPaymentMethodsSection.clickAddNewPaymentMethod();
		PFAddPaymentModal.setThirdPartyPaymentMethod(paymentMethod.isThirdParty());
		PFAddPaymentModal.setDoNotDiscloseRates(paymentMethod.isDoNotDiscloseRates());
		PFAddPaymentModal.setPrimaryMethod(paymentMethod.isPrimaryMethod());
		PFAddPaymentModal.setAssociateDefaultFolio(paymentMethod.isAssociateToDefaultFolio());
		PFAddPaymentModal.setSaveToGuestProfile(paymentMethod.isSaveToGuestProfile());
		PFAddPaymentModal.setGuestDefaultPaymentMethod(paymentMethod.isGuestDefault());
		ThreadHelper.sleep(2000);
		PFAddPaymentModal.selectPaymentMethod(paymentMethod.getPaymentMethod());
		
		if(paymentMethod.getDirectBillAccountName() != null){
			PFAddPaymentModal.setDirectBillAccount(paymentMethod.getDirectBillAccountName());
		}
		
		if(paymentMethod.getSwipeData() != null){
			PFAddPaymentModal.swipeData(paymentMethod.getSwipeData());
		}		
		PFAddPaymentModal.savePaymentModal();
	}
}
