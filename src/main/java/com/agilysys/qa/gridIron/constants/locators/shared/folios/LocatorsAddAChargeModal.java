package com.agilysys.qa.gridIron.constants.locators.shared.folios;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsAddAChargeModal {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fiRpmrc']");
	public static final By BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fiRpmrc']//div[@class='panel-heading']//a");

	// First screen
	public static final By INPUT_SEARCH = By.xpath("//*[@data-qa-id='fbRy3Gj-val']");

	public static final By LIST_ITEM_NAME = By.xpath("//p[@data-qa-id='gzssPkQ']");
	public static final By LIST_BUTTON_ADD = By.xpath("//a[@data-qa-id='fbRy3G6']");
	public static final By BUTTON_NEXT = By.xpath("//button[@data-qa-id='hKaNt4d' and text()='Next']");
	
	public static final By BUTTON_NEXT_2 = By.xpath("//button[@data-qa-id='hKaNtCB' and text()='Next']");

	// Second screen
	public static final By BUTTON_SAVE = By.xpath("//button[@data-qa-id='fiRpmsf']");

	public static final By DROPDOWN_DATE_POST = By.xpath("//*[@data-qa-id='fGUyPS4-click']");
	public static final By DROPDOWN_VALUE_DATE_POST = By.xpath("//*[@data-qa-id='fGUyPS4-text']");

	public static final By HA_BUTTON_DONE = By.xpath("//button[@data-qa-id='fiRpmuH']");

}
