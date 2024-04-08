package com.agilysys.qa.gridIron.pageobject.housekeeping;

import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFHousekeepingHome;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Selenide;

public class POHousekeepingHome {
	
	public static void openEditServiceRequestModal(String roomNumber, String serviceRequestCode){
		PFHeaderDropDowns.navigateToHouseKeeping();
		PFHousekeepingHome.clickStaffTab();
		ThreadHelper.sleep(1000);
		Selenide.refresh();
		PFHousekeepingHome.clickAssignedServiceRequestID(roomNumber, serviceRequestCode);
	}

}
