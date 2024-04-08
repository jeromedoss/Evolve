package com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsBatchCheckinSection {

	public static final By TAB_CHECKIN = By.xpath("//*[@data-qa-id='fGUyRdp'][2]/a");

	public static final By INPUT_SEARCH = By.xpath("//*[@data-qa-id='gffZXfE-val']");
	public static final By CHECKBOX_SELECT_ALL = By.xpath("//*[@data-qa-id='gffZXfW-click']");
	public static final By CHECKBOX_SHOW_CHECKED_IN = By.xpath("//*[@data-qa-id='gffZXfg-label-text']");
	public static final By BUTTON_ASSIGN_ROOMS = By.xpath("//*[@data-qa-id='gffZXeq']");
	public static final By BUTTON_CHECKIN = By.xpath("//*[@data-qa-id='gffZXes']");

	public static final By LIST_COLUMN_RESERVATION = By.xpath("//*[@data-qa-id='gffZXfR']");
	public static final By LIST_COLUMN_CONFORMATION = By.xpath("//*[@data-qa-id='gffZXfR']/td[2]");
	public static final By LIST_COLUMN_GUESTNAME = By.xpath("//*[@data-qa-id='gffZXfR']/td[3]");
	public static final By LIST_COLUMN_STATUS = By.xpath("//*[@data-qa-id='gffZXfR']/td[12]");
	public static final By LIST_CHECKOUT_COLUMN_STATUS = By.xpath("//*[@data-qa-id='fGUyQfo']");

	public static final By BUTTON_AUTO_ASSIGN = By.xpath("//*[@data-qa-id='gffZXef']");
	public static final By BUTTON_SAVE_AND_CLOSE = By.xpath("//*[@data-qa-id='gffZXgY']");
	public static final By BUTTON_CLICK_YES = By.xpath("//button[text()='Yes']");

	public static final By MODAL_CHECKIN_PROCESSING = By.xpath("//*[@data-qa-id='fGUyNkk']");

}
