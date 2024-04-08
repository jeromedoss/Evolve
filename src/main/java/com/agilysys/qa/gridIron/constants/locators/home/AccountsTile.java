package com.agilysys.qa.gridIron.constants.locators.home;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class AccountsTile {

	public static final By TAB_ACCOUNTS = By.xpath("//div[@data-qa-id='fGUyRUS']//li[@ui-sref='.accounts']");

	public static final By CHECKBOX_TYPE_MEETING_ROOM = By
			.xpath("//*[@title='Meeting Room' and @data-qa-id='fbRy3Ae-item']");// *[@data-qa-id='fbRy3Ae-item']");

	public static final By LINK_CREATE_ACCOUNT = By.xpath("//*[@data-qa-id='fbRy3BR']");
	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fbRy2dq']//h3");
	public static final By INPUT_NAME = By.xpath("//input[@data-qa-id='fbRy3EK-val']");
	public static final By DROPDOWN_CATEGORY = By.xpath("//input[@data-qa-id='fbRy2dr-click'][2]");
	public static final By BUTTON_CREATE = By.xpath("//button[@data-qa-id='fbRy39w']");
	public static final By BUTTON_CANCEL = By.xpath("//a[@data-qa-id='fbRy3Fy']");
	public static final By BUTTON_CLOSE = By.xpath("//a[@data-ng-click='closeModal()']");

	public static final By LINK_ADD_FOLIO = By.xpath("//*[@data-qa-id='fbRy3HS']");
	public static final By BUTTON_DONE = By.xpath("//*[@data-qa-id='fbRy3FR']");

	public static final By LIST_HOUSEACCOUNTS = By
			.xpath("//div[@data-qa-id='fGUyPL3']//div[@data-qa-id='fbRy3Dg']/h3/a");

	public static final By ICON_SPINNER = By
			.xpath("//*[@data-qa-id='g6nHJon']//div[@class='spinner-container ng-hide']");

}
