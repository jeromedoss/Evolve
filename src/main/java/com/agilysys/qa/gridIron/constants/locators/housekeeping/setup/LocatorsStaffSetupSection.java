package com.agilysys.qa.gridIron.constants.locators.housekeeping.setup;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsStaffSetupSection {

	public static final By CONTAINER = By.xpath("//*[@data-qa-id='fQoURfA']/div");
	public static final By BUTTON_ADD_NEW = By.xpath("//*[@data-qa-id='fQoURkJ']");
	public static final By BUTTON_BULK_EDIT = By.xpath("//*[@data-qa-id='fQoURkW']");
	public static final By STAFF_TABLE = By.xpath("//*[@data-qa-id='fQoURkn']");
	public static final By STAFF_ROW = By.xpath("//*[@data-qa-id='fQoURkE']");
	public static final By CHECKBOX_SELECTALL = By.xpath("//button[@data-qa-id='fQoURkU-click']");

	public static final By STAFF_LASTNAME = By.xpath("//*[@data-qa-id='fb4dUSf-val']");
	public static final By STAFF_FIRSTNAME = By.xpath("//*[@data-qa-id='fb4dUXp-val']");
	public static final By DROPDOWN_ROLE = By.xpath("//*[@data-qa-id='fb4dUZJ-click']");

	public static final By DROPDOWN_LIST_ROLE = By.xpath("//*[@data-qa-id='fb4dUZJ-list']/li/a");

	public static final By BUTTON_SAVE = By.xpath("//*[@data-qa-id='fb4dUUh']");

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fb4dUSx']");

}
