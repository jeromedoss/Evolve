package com.agilysys.qa.gridIron.constants.locators.groups;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsGroupsCommon {

	public static final By TAB_BOOKING = By.xpath("//*[@data-qa-id='fGUyNrs']");
	public static final By TAB_ROOM_BLOCKS = By.xpath("//*[@data-qa-id='fGUyPvQ']");
	public static final By TAB_FOLIOS_ROUTING_RULES = By.xpath("//*[@data-qa-id='fGUyPnF']");
	public static final By TAB_RESERVATIONS = By.xpath("//*[@data-qa-id='fGUyQoZ']");
	public static final By BUTTON_MAKE_RESERVATION = By.xpath("//*[@data-qa-id='fGUyQS1']");
	public static final By BUTTON_UNDO_CANCEL = By.xpath("//*[@data-qa-id='fPsRnT7']");
	public static final By BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyRaH']");
	public static final By BUTTON_SAVE = By.xpath("//button[contains(text(),'Save Changes')]");
	public static final By INPUT_GROUP_NAME = By.xpath("//*[@data-qa-id='fGUyQVZ']");
	public static final By BUTTON_MORE = By.xpath("//*[@data-qa-id='fiwAvZi']");
	public static final By BUTTON_ALERT_CLOSE= By.xpath("(//*[@role='alert']/button)[1]");

	public static final By BUTTON_SAVE_POPUP = By.xpath("//*[@data-qa-id='fGUyPcr']");

	public static final By ROOM_BLOCK_ALERT = By.xpath("//a[@ng-show=\"showUsermenu\"]/ancestor::nav/following" +
		"-sibling::div[1]//button");

}
