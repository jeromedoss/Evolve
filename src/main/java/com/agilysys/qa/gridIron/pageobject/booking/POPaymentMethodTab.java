package com.agilysys.qa.gridIron.pageobject.booking;

import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFPaymentMethodTab;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POPaymentMethodTab {

	public static void stepPaymentByCash() {
		POBookAReservationModal.addPaymentMethod(0, "Cash", null, null);
	}

	public static void stepPaymentByCheck() {
		POBookAReservationModal.addPaymentMethod(0, "Check", null, null);
	}

	public static void stepPaymentByVISACard() {
		PFPaymentMethodTab pfPaymentMethodTab =new PFPaymentMethodTab(0);
		String swipeData = "2B301801F392400839B%*401200******0026^BOYINGTON/CLINTON C ^1804***********?*;401200******0026=1804************?*39457A24612BA961782B3CD8E5DCCB9EAEECD03301A400636A4D9AFE7564E4000BD74B198FF3BB7DA1830246F75482F30A638F2296F5F67970EDAF0529EE2E5349B3A11DB8E1E290D0F9454769CF3F4814A7E36065F0E0F3D5AB149828E430510A2A54A5672DECAF0000000000000000000000000000000000000000000000000000000000000000000000000000000034313854303632353436FFFF9876540000C002B6446C03";
		POBookAReservationModal.addPaymentMethod(0, "New Credit Card", swipeData, null);
	}

	public static void stepPaymentByDirect(String directBillAccount) {
		POBookAReservationModal.addPaymentMethod(0, "Direct Bill", null, directBillAccount);
	}

}
