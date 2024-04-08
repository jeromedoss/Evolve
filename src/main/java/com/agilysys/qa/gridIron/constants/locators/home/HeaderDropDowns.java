package com.agilysys.qa.gridIron.constants.locators.home;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class HeaderDropDowns {

	public static final By BUTTON_LOGO = By.xpath("//a[@ng-if='logoLink']");

	public static final By NAV_RESERVATION = By.xpath("//a[@id='reservationsNavItem' and contains(.,'Reservations')]");

	public static final By NAV_SEARCH = By.xpath("//a[@id='reservationsHomeLink' and contains(text(),'Search')]");

	public static final By NAV_BOOK_RESERVATION = By
			.xpath("//a[@id='reservationsBookLink' and contains(.,'Book a Reservation')]");

	public static final By NAV_PROFILES = By.xpath("//a[@id='profilesLink' and text() = 'Profiles']");

	public static final By NAV_DASHBOARD = By.xpath("//a[@id='dashboardNavItem' and text() = 'Dashboard']");

	public static final By NAV_GENERAL_AVAILABILITY = By
			.xpath("//a[@id='generalAvailabilityLink' and text() = 'General Availability']");

	public static final By NAV_GROUP_ROOMS_CONTROL = By.xpath("//a[@id='grcLink' and text() = 'Group Rooms Control']");

	public static final By NAV_TAPECHART = By.xpath("//a[@id='tapeChartLink' and text() = 'Tape Chart']");

	public static final By NAV_FRONTDESK = By.xpath("//a[@id='frontDeskNavItem' and text() = 'Front Desk']");

	public static final By NAV_HOME = By.xpath("//a[@id='frontDeskHomeLink' and text() = 'Home']");

	public static final By NAV_BATCH_OPERATIONS = By
			.xpath("//a[@id='batchOperationsLink' and text() = 'Batch Operations']");

	public static final By NAV_ROOM_MGMT = By.xpath("//a[@id='roomsMgmtNavItem' and contains(text(), 'Rooms Mgmt')]");

//	public static final By NAV_HOUSE_KEEPING = By.xpath("//a[@id='housekeepingHomeLink' and text() = 'Housekeeping']");
	public static final By NAV_HOUSE_KEEPING = By.xpath("//a[@id='housekeepingHomeLink']");
	public static final By NAV_MAINTENANCE = By.xpath("//a[@id='maintenanceHomeLink' and contains(text(), 'Maintenance')]");

	public static final By NAV_ROOM_CONDITIONS = By
			.xpath("//a[@id='housekeepingConditionsLink' and text() = 'Room Conditions']");

	public static final By NAV_INVENTORY_BLOCKS = By
			.xpath("//a[@id='housekeepingBlocksLink' and contains(text(), 'Inventory Blocks')]");

	public static final By NAV_REQUEST_SERVICE = By
			.xpath("//a[@id='requestServiceLink' and contains(text(), 'Request Service')]");

	public static final By NAV_LOST_FOUND = By
			.xpath("//a[@id='lostandfoundHomeLink' and text() = 'Lost & Found']");

	public static final By NAV_NIGHT_AUDIT = By.xpath("//a[@id='nightAuditLink' and text() = 'Night Audit']");

	public static final By NAV_REPORTS = By.xpath("//a[@id='reportsNavItem' and text() = 'Reports']");

	public static final By NAV_ALL_REPORTS = By.xpath("//a[@id='allReportsLink' and text() = 'All Reports']");

	public static final By NAV_TA_COMMISSION_REPORT = By
			.xpath("//a[@id='taCommissionReportLink' and text() = 'TA Commission Report']");

	public static final By NAV_INTERFACE_LOGS = By.xpath("//a[@id='interfaceLogsLink' and text() = 'Interface Logs']");

	public static final By NAV_SETTINGS = By.xpath("//a[@id='settingsNavItem' and text() = 'Settings']");

	public static final By NAV_ALLSETTINGS = By.xpath("//a[@id='settingsLink' and text() = 'All Settings']");

	public static final By NAV_RATES = By.xpath("//a[@id='ratesLink' and text() = 'Rates']");
	
	public static final By NAV_USER_ACCOUNTS = By.xpath("//a[@id='userAccountsLink' and text() = 'User Accounts']");

	public static final By NAV_GUEST_ACCOUNTING = By.xpath("//a[@id='guestAccountingLink' and text() = 'Guest Accounting']");

	public static final By NAV_INTERFACES = By.xpath("//a[@id='interfacesLink' and text() = 'Interfaces']");

	public static final By NAV_ROOM_AVAILABILITY = By.xpath("//a[@id='roomAvailabilityLink' and text() = 'Room Availability']");

	public static final By TOTAL_ROOM_AVAILABILITY_COUNT = By.xpath("//tr[@data-qa-id='iZRXPvG']");

	public static final By ROOMTYPE_SAVE = By.xpath("//button[@data-qa-id='fGUyRcj']");

	public static final By ROH_LABEL = By.xpath("//label[@data-qa-id='fGUyRWW-label-text' and contains(text(),'Run Of House')]");

	public static By openRoomType(String roomTypeName){
		return By.xpath("//a[@data-qa-id='fiRpmsi' and contains(text(),'"+roomTypeName+"')]");
	}
}