package com.agilysys.qa.gridIron.pageobject.maintenance;

import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.maintenance.PFMaintenanceHome;

public class POMaintenanceHome {
	
	
	public static void deleteRoomMTRequest(String areaOrRoomName, String serviceRequestCode){
		PFHeaderDropDowns.navigateToMaintenace();
		PFMaintenanceHome.clickRoomMTRequests();
		PFMaintenanceHome.clickDeleteServiceRequest(areaOrRoomName, serviceRequestCode);
	}
	
	public static void goToRoomMTRequest(String areaOrRoomName, String serviceRequestCode){
		PFHeaderDropDowns.navigateToMaintenace();
		PFMaintenanceHome.clickRoomMTRequests();
		PFMaintenanceHome.clickEditServiceRequest(areaOrRoomName, serviceRequestCode);
	}
	
	public static void goToGeneralAreaMTRequest(String areaOrRoomName, String serviceRequestCode){
		PFHeaderDropDowns.navigateToMaintenace();
		PFMaintenanceHome.clickGeneralAreaMTRequests();
		PFMaintenanceHome.clickEditServiceRequest(areaOrRoomName, serviceRequestCode);
	}
}
