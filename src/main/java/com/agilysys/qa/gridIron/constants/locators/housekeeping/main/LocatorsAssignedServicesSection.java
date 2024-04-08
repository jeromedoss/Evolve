package com.agilysys.qa.gridIron.constants.locators.housekeeping.main;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsAssignedServicesSection {

	public static final By CONTAINER = By.xpath("//*[@data-qa-id='fb4dUXW']");
	public static final By TAB_STAFF = By.xpath("//*[@data-qa-id='fb4dUZt']");
	public static final By TAB_ROOMS = By.xpath("//*[@data-qa-id='fb4dUV3']");
	public static final By TABLE_STAFF = By.xpath("//*[@data-qa-id='fb4dUTE']");
	public static final By ROW_STAFF = By.xpath("//*[@data-qa-id='fb4dUTF']");
	public static final By STAFF_NAME_COLUMN = By.xpath("//*[@data-qa-id='fb4dUZS']");
	public static final By STAFF_ROLE_COLUMN = By.xpath("//*[@data-qa-id='fb4dUW2']");
	public static final By STAFF_SERVICES_COLUMN = By.xpath("//*[@data-qa-id='fb4dUYg']");
	public static final By STAFF_PROGRESS_COLUMN = By.xpath("//*[@data-qa-id='g6fNDPY']");
	public static final By STAFF_SERVICE_TABLE = By.xpath("//*[@data-qa-id='fuLFBfA']");
	public static final By STAFF_ROOM_COLUMN = By.xpath("//*[@data-qa-id='fb4dUaE']");
	public static final By STAFF_INVENTORY_STATUS_COLUMN = By.xpath("//*[@data-qa-id='g5tHyGk']");
	public static final By STAFF_HOUSEKEEPING_STATUS_COLUMN = By.xpath("//*[@data-qa-id='fuLFBs3']");
	public static final By STAFF_SERVICE_CODE_COLUMN = By.xpath("//*[@data-qa-id='fuLFBtT']");
	public static final By STAFF_INPUT_STATUS_COLUMN = By.xpath("//*[@data-qa-id='fuLFBqs']");
	public static final By STAFF_SEVERITY_COLUMN = By.xpath("//*[@data-qa-id='g5tHyNL']");
	public static final By STAFF_POINTS_COLUMN = By.xpath("//*[@data-qa-id='fuLFBrM']");
	public static final By STAFF_ID_COLUMN = By.xpath("//*[@data-qa-id='g5mTRJm']");

	public static final By DROPDOWN_SERVICE = By.xpath("//*[@data-qa-id='fuLFBqs']/ag-dropdown[@data-qa-id='fb4dUcA']");
	public static final By DROPDOWN_SERVICE_COMPLETED = By
			.xpath("//*[@data-qa-id='fuLFBqs']/ag-dropdown[@data-qa-id='fb4dUcA']");

	public static final By LIST_SERVICE_ID = By.xpath("//*[@data-qa-id='fuLFBtS']//td[@data-qa-id='g5mTRJi']/a");// *[@data-qa-id='g5mTRJm']");
	
	public static By getLinkServiceRequestIDLocatorByRoomNumberAndServiceRequestType(String roomNumber, String serviceRequestCode){
		return By.xpath("//table[@id='hkStaffTable']//span[contains(text(),'"+roomNumber+"')]//ancestor::tr[1]//a[contains(text(),'"+serviceRequestCode+"')]");
	}
	
	public static By getDropdownServiceRequestStatusLocatorByRoomNumberAndServiceRequestType(String roomNumber, String serviceRequestCode){
		return By.xpath("//table[@id='hkStaffTable']//span[contains(text(),'"+roomNumber+"')]//ancestor::tr[1]//span[contains(text(),'"+serviceRequestCode+"')]//ancestor::tr[1]//input[@data-qa-id='fb4dUcA-click']");
	}
	
	public static By getOptionsServiceRequestStatusLocatorByRoomNumberAndServiceRequestType(String roomNumber, String serviceRequestCode){
		return By.xpath("//table[@id='hkStaffTable']//span[contains(text(),'"+roomNumber+"')]//ancestor::tr[1]//span[contains(text(),'"+serviceRequestCode+"')]//ancestor::tr[1]//a[@data-qa-id='fb4dUcA-text']");
	}
	
	public static By getServiceRequestSeverityLocatorByRoomNumberAndServiceRequestType(String roomNumber, String serviceRequestCode){
		return By.xpath("//table[@id='hkStaffTable']//span[contains(text(),'"+roomNumber+"')]//ancestor::tr[1]//span[contains(text(),'"+serviceRequestCode+"')]//ancestor::tr[1]//a[@data-qa-id='fb4dUcA-text']");
	}

	public static By getHKRoomRecordByRoomNo(String roomNumber){
		return By.xpath("//table[@id='hkRoomTable']//span[contains(text(),'"+roomNumber+"')]//ancestor::tr[1]");
	}

	public static By getHKRoomRecordByRoomType(String roomType){
		return By.xpath("//table[@id='hkRoomTable']//span[contains(text(),'"+roomType+"')]//ancestor::tr[1]");
	}
	public static By getHKStaffRecordByStaffName(String staffName){
		return By.xpath("//table[@id='hkStaffTable']//span[contains(text(),'"+staffName+"')]//ancestor::tr[1]");
	}

}
