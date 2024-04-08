package com.agilysys.qa.gridIron.constants.pagefactory.housekeeping;

import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsAssignedServicesSection;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsHkMain;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsRequestServiceDialog;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.setup.LocatorsNavigationSubMenu;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFHousekeepingHome extends PageFactoryBase{

	public static void clickAssignedServiceRequestID(String roomNumber, String serviceRequestCode){		
		click(LocatorsAssignedServicesSection.getLinkServiceRequestIDLocatorByRoomNumberAndServiceRequestType(roomNumber, serviceRequestCode));
		click(LocatorsRequestServiceDialog.TEXTAREA_NOTES);
	}	
	
	public static void clickStaffTab(){
		click(LocatorsAssignedServicesSection.TAB_STAFF);
	}
	
	public static void clickRequestService(){
		click(LocatorsHkMain.BUTTON_REQUEST_SERVICE);
	}

	public static void clickRoomsTab(){
		click(LocatorsAssignedServicesSection.TAB_ROOMS);
	}

	public static void clickSetup(){
		click(LocatorsHkMain.NAV_SETUP);
	}

	public static void clickGuestService(){
		click(LocatorsNavigationSubMenu.GUEST_SERVICES_MENU);
	}

	public static void addNewServiceType(String name , String code){
		click(By.xpath("//*[@id=\"guest-services-panel\"]/div/div[1]/div[2]/ul/li/button"));
		type(By.xpath("//div[@class=\"m3-panel flexitem\"]//input[@data-qa-id=\"fb4dUUU-val\"]"),name);
		type(By.xpath("//div[@class=\"m3-panel flexitem\"]//input[@data-qa-id=\"fb4dUYj-val\"]"),code);
		click(By.xpath("//div[@class=\"m3-panel flexitem\"]//button[@data-qa-id=\"fb4dUWG\"]"));

	}

}
