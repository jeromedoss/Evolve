package com.agilysys.qa.gridIron.constants.locators.maintenance;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsMTMainSection {

	public static final By NAV_HOME = By.xpath("//*[@data-qa-id='fb4dUGe']//li[2]");
	public static final By NAV_STAFF = By.xpath("//*[@data-qa-id='fb4dUGe']//li[3]");
	public static final By NAV_ASSIGN = By.xpath("//*[@data-qa-id='fb4dUGe']//li[4]");
	public static final By NAV_SETUP = By.xpath("//*[@data-qa-id='fb4dUGe']//li[5]");
	public static final By BUTTON_REQUEST_SERVICE = By.xpath("//*[@data-qa-id='fiRpmoF']");

	public static final By LABEL_ROOM_MT_REQUESTS = By.xpath("//*[@data-qa-id='fiRpmtP' and contains(text(), 'Room MT')]");

	public static final By LABEL_GENERAL_MT_REQUESTS = By.xpath("//*[@data-qa-id='fiRpmtP' and text() = 'General MT']");
	public static final By BUTTON_CONFIRM_DELETE = By.xpath("//button[@ng-click='yes()']");
	public static By getMaintenanceServiceRequestEdit(String areaName, String serviceRequestCode){
		return By.xpath("//table[@data-qa-id='fpsjz6h']//tr[contains(.,'"+areaName+"') and contains(., '"+serviceRequestCode+"')]//a[@data-qa-id='frZjMYd']");
	}
	
	public static By getMaintenanceServiceRequestDelete(String areaName, String serviceRequestCode){
		return By.xpath("//table[@data-qa-id='fpsjz6h']//tr[contains(.,'"+areaName+"') and contains(., '"+serviceRequestCode+"')]//span[@data-qa-id='fr4hPkh']");
		}

}