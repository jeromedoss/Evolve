package com.agilysys.qa.gridIron.constants.locators.booking.wizard.deposits;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsDepositTab {

	public static final By DROPDOWN_DEPOSIT = By.xpath("//input[@data-qa-id='fGUyQUG-click']");
	public static final By DROPDOWN_VALUE_DEPOSIT = By.xpath("//a[@data-qa-id='fGUyQUG-text']");
	
	public static final By LINK_NEW_PAYMENT = By.xpath("//*[@data-qa-id='fzNCfDk']");
	public static final By INPUT_DEPOSIT_AMOUNT = By.xpath("//input[@data-qa-id='fGUyQg7-val']");

	public static final By LABEL_DEPOSIT_AMOUNT = By.xpath("//label[@data-qa-id='fGUyQdi-text']");
	
	public static final By INPUT_USERNAME = By.xpath("//*[@data-qa-id='fGUyQRM-val']");
	public static final By INPUT_PASSWORD = By.xpath("//*[@data-qa-id='fGUyP2C-val']");
}
