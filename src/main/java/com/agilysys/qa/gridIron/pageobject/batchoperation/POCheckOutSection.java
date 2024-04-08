package com.agilysys.qa.gridIron.pageobject.batchoperation;

import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckOutSection.ClickDropdownValueForceCheckout;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckOutSection.clickButtonRun;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckOutSection.clickCheckboxSelectAllReservations;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckOutSection.clickCheckboxShowCheckoutsWithAvailablePayment;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckOutSection.clickCheckboxShowRemainingCheckouts;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckOutSection.clickTabCheckout;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POCheckOutSection {

	public static void stepPerformBatchForceCheckoutForAll() {

		clickTabCheckout();
		clickCheckboxShowRemainingCheckouts();
		clickCheckboxShowCheckoutsWithAvailablePayment();
		clickCheckboxSelectAllReservations();
		clickButtonRun();
		ClickDropdownValueForceCheckout();

	}

}
