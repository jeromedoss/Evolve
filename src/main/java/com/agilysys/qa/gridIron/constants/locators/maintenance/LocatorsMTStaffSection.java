package com.agilysys.qa.gridIron.constants.locators.maintenance;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsMTStaffSection {

	public static final By CHECKBOX_SELECTALL_STAFF_OFF = By.xpath("//*[@data-qa-id='fcEEkGb-click']");
	public static final By CHECKBOX_SELECTALL_STAFF_ON = By.xpath("//*[@data-qa-id='fcEEkGb-click']");

	public static final By AREA_DRAG_AND_DROP = By.xpath("//p[@data-qa-id='fcEEkGj']");
	public static final By LIST_STAFF_OFF = By.xpath("//*[@data-qa-id='fcEEkGH']/tr[1]");
//	public static final By DRAG_STAFF_OFF_SOURCE = By.xpath("//*[@data-qa-id='fcEEkH6']");
	public static final By DRAG_STAFF_OFF_SOURCE = By.xpath("//*[@data-qa-id='fcEEkH7']");
	public static final By DRAG_STAFF_ON_DESTINATION= By.xpath("//*[@data-qa-id='fcEEkHG']");
	public static final By STAFF_ON_DUTY_NAME= By.xpath("//*[@data-qa-id='fcEEkGN']");


}
