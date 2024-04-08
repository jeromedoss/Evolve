package com.agilysys.qa.gridIron.constants.locators.reservation;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsCheckinModal {
	public static final By BUTTON_CHECK_IN = By.xpath("//*[@data-qa-id='fGUyQH9']");

	public static final By BUTTON_CHECK_IN_WITHOUT_AUTH = By.xpath("//*[@data-qa-id='fGUyQ54']");
	public static final By BUTTON_CHECK_IN_WITH_AUTH = By.xpath("//*[@data-qa-id='fGUyRAu']");
	public static final By MODAL_CHECK_CHECK_IN = By.xpath("//*[@data-qa-id='fGUyPZo']");
	public static final By LABEL_WAIT_FOR_INPUT = By.xpath("//*[@data-qa-id='fGUyQ6g-text']");
	public static final By HEADER_MODAL_CHECK = By.xpath("//*[@data-qa-id='fGUyPZo']");
	public static final By MODAL_CHECK_BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyPZo']//a");
	public static final By MODAL_CHECK_BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyRJr']");
	
	//Undo CheckIn
	public static final By BUTTON_CONFIRM_AND_RETAIN_ROOM = By.xpath("//button[@data-qa-id='fGUyQAH']");
			
}
