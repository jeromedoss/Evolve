package com.agilysys.qa.gridIron.builders.batchoperation;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns.navigateToBatchOperations;
import static com.agilysys.qa.gridIron.pageobject.batchoperation.POCheckOutSection.stepPerformBatchForceCheckoutForAll;

import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class BatchCheckoutOperation {

	public static void flowBatchCheckoutAllReservations() {

		Selenide.sleep(6000);

		navigateToBatchOperations();

		stepPerformBatchForceCheckoutForAll();

	}

}
