package com.agilysys.qa.gridIron.constants.locators.groups;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsGroupStagingSection {

	public static final By INPUT_LASTNAME = By.xpath("//*[@data-qa-id='h6dpFP73']//*[@data-qa-id='h6dpFP7-val']");
	public static final By INPUT_FIRSTNAME = By.xpath("//*[@data-qa-id='h6dpFP74']//*[@data-qa-id='h6dpFP7-val']");
	public static final By INPUT_PHONENUMBER = By.xpath("//*[@data-qa-id='h6dpFA3-val' and @placeholder[contains(.,'Enter a phone number')]]");
	public static final By DROPDOWN_ROOM_TYPE = By.xpath("//*[@data-qa-id='h6dpFFX-click' and @placeholder[contains(.,'Select a room type')]]");
	public static final By DROPDOWN_ROOM_TYPE_LIST = By.xpath("//*[@data-qa-id='h6dpFFX5']//a[@data-qa-id='h6dpFFX0-text']");
	public static final By BUTTON_SAVE_STAGING = By.xpath("//*[@data-qa-id='h6dpFGN']");
	public static final By CHECKBOX_AUTO_CREATE_RESERVATION = By.xpath("//*[@data-qa-id='h6dpFEz-click']");

	public static final By MODAL_BATCH_OPERATIONS = By.xpath("//*[@data-qa-id='h6dpF9v']");
}
