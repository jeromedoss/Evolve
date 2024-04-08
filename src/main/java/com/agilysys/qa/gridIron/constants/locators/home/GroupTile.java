package com.agilysys.qa.gridIron.constants.locators.home;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class GroupTile {

	public static final By TAB_GROUP = By.xpath("//li[@data-qa-id='fGUyRXt']/a");

	public static final By LIST_GROUPS = By.xpath("//li[@data-qa-id='fGUyNWs']/a/div/h3");

	public static final By TAB_BOOKING = By.xpath("//*[@data-qa-id='fGUyNrs']/a");

	public static final By TAB_ROOM_BLOCKS = By.xpath("//*[@data-qa-id='fGUyPvQ']/a");

	public static final By TAB_FOLIOSGroupFoliosTab = By.xpath("//*[@data-qa-id='fGUyPnF']/a");

	public static final By BUTTON_FOLIO_MORE = By.xpath("//*[@data-qa-id='fGUyQ3Y']//button[text()='More']");

	public static final By DROPDOWN_VALUE_ADD_CHARGE = By.xpath("//*[@data-qa-id='fGUyQ3Y']//a[text()='Add a Charge']");
	public static final By DROPDOWN_VALUE_ADD_CREDIT = By.xpath("//*[@data-qa-id='fGUyQ3Y']//a[text()='Add a Credit']");
	public static final By BUTTON_MAKE_PAYMENT = By.xpath("//*[@data-qa-id='fGUyQ3Y']//a[text()='Make a Payment']");
	public static final By BUTTON_COLLAPSE_FOLIO_MODAL = By.xpath("//*[@data-qa-id='fGUyQji']/div/button");

	public static final By TAB_RESERVATION = By.xpath("//*[@data-qa-id='fGUyQoZ']/a");

	public static final By TAB_PRESET = By.xpath("//*[@data-qa-id='gaE9zko']/a");

	public static final By GROUP_DATE = By.xpath("//input[@data-qa-id='fGUyPTE-val']");

}
