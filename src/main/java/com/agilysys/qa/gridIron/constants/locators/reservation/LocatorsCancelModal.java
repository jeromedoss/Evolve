package com.agilysys.qa.gridIron.constants.locators.reservation;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsCancelModal {
	public static final By DROPDOWN_CANCELLATION_POLICY = By.xpath("//*[@data-qa-id='fGUyRFL-click']");
	public static final By DROPDOWN_OPTIONS_CANCELLATION_POLICY = By.xpath("//*[@data-qa-id='fGUyRFL-text']");
	
	public static final By CHECKBOX_CHANGE_CANCELLATION_CHARGE = By.xpath("//*[@data-qa-id='fGUyRWL-click']");
	public static final By RADIO_FLAT_FEE = By.xpath("//*[@data-qa-id='fGUyQt1-click']");
	public static final By RADIO_PERCENTAGE = By.xpath("//*[@data-qa-id='fGUyPCa-click']");
	
	public static final By INPUT_FLAT_FEE = By.xpath("//*[@data-qa-id='fGUyR3i-val']");
	public static final By INPUT_PERCENTAGE = By.xpath("//*[@data-qa-id='fGUyP27-val']");
	
	public static final By DROPDOWN_PAYMENT_METHOD = By.xpath("//*[@data-qa-id='fGUyRoy-click']");
	public static final By DROPDOWN_OPTIONS_PAYMENT_METHOD = By.xpath("//*[@data-qa-id='fGUyRoy-text']");
	public static final By INPUT_USERNAME = By.xpath("//*[@data-qa-id='fGUyPsS-val']");
	public static final By INPUT_PASSWORD = By.xpath("//*[@data-qa-id='fGUyRns-val']");
	public static final By TEXTAREA_REASON = By.xpath("//*[@data-qa-id='jWytvdk-val']");
	public static final By BUTTON_CONFIRM_CANCELLATION = By.xpath("//button[@data-qa-id='1WwKV1']");
	public static final By BUTTON_PAY_AND_CONFIRM_CANCELLATION = By.xpath("//button[@data-qa-id='1WwKUn']");
	
	public static final By CARD_FOCUS = By.xpath("//*[@ng-click='captureCardCtrl.focus()']");
	
	//UNDO CANCEL
	public static final By BUTTON_CONFIRM_UNDO_CANCEL = By.xpath("//*[@data-qa-id='fJm7mnM']"); 
}
