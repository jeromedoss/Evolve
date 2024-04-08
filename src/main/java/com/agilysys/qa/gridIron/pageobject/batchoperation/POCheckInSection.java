package com.agilysys.qa.gridIron.pageobject.batchoperation;

import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckInSection.clickButtonAssignRooms;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckInSection.clickButtonAutoAssign;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckInSection.clickButtonCheckin;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckInSection.clickButtonSaveAndClose;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckInSection.clickCheckboxSelectAll;
import static com.agilysys.qa.gridIron.constants.pagefactory.batchoperation.PFCheckInSection.clickTabCheckin;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POCheckInSection {

	public static void stepPerformBatchCheckinForAll() {

		clickTabCheckin();
		clickCheckboxSelectAll();
		clickButtonAssignRooms();
		clickButtonAutoAssign();
		clickButtonSaveAndClose();
		clickCheckboxSelectAll();
		clickButtonCheckin();

		org.openqa.selenium.WebDriver driver ;

	}
}
