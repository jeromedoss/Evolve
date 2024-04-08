package com.agilysys.qa.gridIron.constants.locators.booking.wizard.payments;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsPaymentMethodTab {

	public static final By BUTTON_ADD_PAYMENT = By.xpath("//button[@data-qa-id='fGUyQkV']");
	
	public static final By BUTTON_PAYMENT_SET_THIRDPARTY = By.xpath("//button[@data-qa-id='fGUyQdw-click']");
	public static final By BUTTON_PAYMENT_DO_NOT_DISCLOSE = By.xpath("//button[@data-qa-id='fGUyNpc-click']");
	public static final By BUTTON_PAYMENT_SAVETO_PROFILE = By.xpath("//button[@data-qa-id='fGUyR9L-click']");
	public static final By BUTTON_PAYMENT_SETAS_DEFAULT = By.xpath("//button[@data-qa-id='fGUyRUD-click']");
	
	public static final By DROPDOWN_PAYMENT = By.xpath("//input[@data-qa-id='fGUyRoy-click']");
	public static final By LIST_DROPDOWN_PAYMENT = By.xpath("//a[@data-qa-id='fGUyRoy-text']");
	
	public static final By INPUT_DIRECTBILL = By.xpath("//input[@data-qa-id='fGUyQBu']");
	public static final By SELECT_DIRECTBILL = By.xpath("//input[@data-qa-id='fGUyQBu']//following::li[1]");
	
	public static final By BUTTON_CREDIT_CARD_FOCUS = By.xpath("//button[@data-qa-id='fGUyQWv']");
	public static final By MESSAGE_CARD_TYPED = By.xpath("//*[@data-qa-id='fGUyR9F-text']");

}
