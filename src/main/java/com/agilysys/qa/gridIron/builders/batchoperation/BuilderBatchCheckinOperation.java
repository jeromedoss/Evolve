package com.agilysys.qa.gridIron.builders.batchoperation;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns.navigateToBatchOperations;
import static com.agilysys.qa.gridIron.pageobject.batchoperation.POCheckInSection.stepPerformBatchCheckinForAll;

/*
 * *Author - Harish Baskaran - 2018
 */
public class BuilderBatchCheckinOperation {

	public static void flowBatchCheckinAllReservations() {

		navigateToBatchOperations();

		stepPerformBatchCheckinForAll();

	}

}