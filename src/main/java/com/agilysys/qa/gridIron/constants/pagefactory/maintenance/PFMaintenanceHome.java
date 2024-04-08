package com.agilysys.qa.gridIron.constants.pagefactory.maintenance;

import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsRequestServiceDialog;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTMainSection;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class PFMaintenanceHome extends PageFactoryBase{
	
	public static void clickRequestService(){
		click(LocatorsMTMainSection.BUTTON_REQUEST_SERVICE);
	}
	public static void clickRoomMTRequests(){
		click(LocatorsMTMainSection.LABEL_ROOM_MT_REQUESTS);
	}
	
	public static void clickGeneralAreaMTRequests(){
		click(LocatorsMTMainSection.LABEL_GENERAL_MT_REQUESTS);
	}
	
	public static void clickEditServiceRequest(String areaOrRoomName, String serviceRequestCode){
		$(LocatorsMTMainSection.getMaintenanceServiceRequestEdit(areaOrRoomName, serviceRequestCode)).shouldBe(Condition.enabled);
		click(LocatorsMTMainSection.getMaintenanceServiceRequestEdit(areaOrRoomName, serviceRequestCode));
		click(LocatorsRequestServiceDialog.TEXTAREA_NOTES);
	}
	
	public static void clickDeleteServiceRequest(String areaOrRoomName, String serviceRequestCode){
		click(LocatorsMTMainSection.getMaintenanceServiceRequestDelete(areaOrRoomName, serviceRequestCode));
		click(LocatorsMTMainSection.BUTTON_CONFIRM_DELETE);
		$(LocatorsMTMainSection.BUTTON_CONFIRM_DELETE).shouldNot(Condition.visible);
	}

}
