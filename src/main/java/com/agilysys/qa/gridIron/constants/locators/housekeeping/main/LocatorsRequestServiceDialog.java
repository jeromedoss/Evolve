package com.agilysys.qa.gridIron.constants.locators.housekeeping.main;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsRequestServiceDialog {

	public static final By HEADER_MODAL = By.xpath("//div[@data-qa-id='fGUyQk4']");


	public static final By SPAN_AREA = By.xpath("//span[@data-qa-id='fs8VyNe']");
	public static final By DROPDOWN_AREA_SELECT = By.xpath("//input[@data-qa-id='fjiP5Qf-click']");
	public static final By DROPDOWN_AREA_OPTION_ROOM = By.xpath("//li[@data-qa-id='fjiP5Qf-item'][1]");
	public static final By DROPDOWN_AREA_OPTION_GENERAL_AREA = By.xpath("//li[@data-qa-id='fjiP5Qf-item'][2]");

	public static final By DROPDOWN_BUILDING = By.xpath("//input[@data-qa-id='fjiP5Qh-click']");
	public static final By DROPDOWN_LIST_BUILDING = By.xpath("//a[@data-qa-id='fjiP5Qh-text']");
	
	public static final By INPUT_ROOM_NO = By.xpath("//input[@data-qa-id='fjiP5P5-click'][2]");
	public static final By INPUT_GENERALAREA_NAME = By.xpath("//input[@data-qa-id='fjiP5Pb-click'][2]");

	public static final By DROPDOWN_CATEGORY_SELECT = By.xpath("//input[@data-qa-id='fGUyPPb-click']");
	public static final By DROPDOWN_CATEGORY_OPTION_HK_SELECT = By.xpath("//a[@data-qa-id='fGUyPPb-text'][text()='HK (Housekeeping)']");
	public static final By DROPDOWN_CATEGORY_OPTION_MT_SELECT = By.xpath("//a[@data-qa-id='fGUyPPb-text'][text()='MT (Maintenance)']");
	public static final By DROPDOWN_CATEGORY_OPTION_HKG_SELECT = By.xpath("//a[@data-qa-id='fGUyPPb-text'][text()='HKG (Guest Service Request)']");

	public static final By DROPDOWN_SERVICE_SELECT = By.xpath("//input[@data-qa-id='fjiP5QN-click']");
	public static final By DROPDOWN_LIST_SERVICE = By.xpath("//a[@data-qa-id='fjiP5QN-text']");
	
	public static final By DROPDOWN_STATUS_SELECT = By.xpath("//input[@data-qa-id='fjiP5RP-click']");
	public static final By DROPDOWN_LIST_STATUS = By.xpath("//a[@data-qa-id='fjiP5RP-text']");
	
	public static final By TEXTAREA_NOTES = By.xpath("//textarea[@data-qa-id='fjiP5Qq-val']");

	public static final By DRODDOWN_SEVERITY_SELECT = By.xpath("//input[@data-qa-id='fjiP5RH-click']");
	public static final By DROPDOWN_OPTION_SEVERITY_MINOR = By.xpath("//li[@data-qa-id='fjiP5RH-item'][1]");
	public static final By DROPDOWN_OPTION_SEVERITY_NORMAL = By.xpath("//li[@data-qa-id='fjiP5RH-item'][2]");
	public static final By DROPDOWN_OPTION_SEVERITY_URGENT = By.xpath("//li[@data-qa-id='fjiP5RH-item'][3]");

	public static final By UNASSIGNED_RADIO = By.xpath("//label[text()='Unassigned']");
	public static final By ASSIGN_RADIO = By.xpath("//label[text()='Assign']");
	public static final By ASSIGN_RADIO_BUTTON = By.xpath("//*[@data-qa-id='fjiP5Pg-click']");
	public static final By STAFF_NAME_SELECT = By.xpath("//input[@data-qa-id='fjiP5P8-click'][2]");

	public static final By SET_SCHEDULE_RADIO = By.xpath("//label[text()='Set Schedule']");
	public static final By INPUT_SCHEDULE_DATE = By.xpath("//input[@data-qa-id='g6nJuXz-val']");

	public static final By SET_BLOCK_RADIO = By.xpath("//label[contains(text(),'Block Room')]");
	public static final By DROPDOWN_BLOCKSTATUS = By.xpath("//input[@data-qa-id='g6nJuXk-click']");
	public static final By DROPDOWN_OPTION_BLOCKSTATUS_OOO = By.xpath("//a[text()='OOO - Out of Order']");
	public static final By DROPDOWN_OPTION_BLOCKSTATUS_OTM = By.xpath("//a[text()='OTM - Off the Market']");
	public static final By DROPDOWN_OPTION_BLOCKSTATUS_HOLD = By.xpath("//a[text()='HOLD - On Hold']");
	public static final By INPUT_BLOCK_START_DATE = By.xpath("//input[@data-qa-id='g6nJuXz-val']");
	public static final By INPUT_BLOCK_END_DATE = By.xpath("//input[contains(@name,'EndDate') and @data-qa-id='g6nJuXz-val']");

	public static final By BUTTON_DONE = By.xpath("//button[@data-qa-id='fGUyP5g']");
	public static final By BUTTON_CANCEL = By.xpath("//a[@data-qa-id='fGUyR5m'][contains(text(),'Cancel')]");

	public static final By HK_SERVICE_LIST = By.xpath("//*[@data-qa-id='fb4dUTF']/td");
	public static final By STATUS_DD = By.xpath("//*[@data-qa-id='fjiP5RP-click']");
	public static final By STATUS_DD_LIST = By.xpath("//*[@data-qa-id='fjiP5RP-text']");
	public static final By STATUS_COMPLETED = By.xpath("//*[@data-qa-id='fjiP5RP-text' and text()='Completed']");

}