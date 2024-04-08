package com.agilysys.qa.gridIron.constants.locators.shared.folios;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsMakePaymentModal {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyRCo']");
	public static final By BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyRCo']//div[@class='panel-heading']/a");

	public static final By LINK_PAY_ALL = By.xpath("//*[@data-qa-id='fGUyQQF']");
	
	public static final By BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyPuq']");
	public static final By BUTTON_APPLY = By.xpath("//*[@data-qa-id='fGUyPt1']");
	public static final By BUTTON_NEXT = By.xpath("//*[@data-qa-id='fGUyQ6C']");
	public static final By BUTTON_DONE = By.xpath("//*[@data-qa-id='fGUyPqC']");

	public static final By INPUT_USERNAME = By.xpath("//*[@data-qa-id='fGUyQb4-val']");
	public static final By INPUT_PASSWORD = By.xpath("//*[@data-qa-id='fGUyQb5-val']");

	public static final By MODAL_PAYMENT_PROCESSING = By.xpath("//*[@data-qa-id='gxhj4wv']");
	public static final By LABEL_BALANCE = By.xpath("//*[@data-qa-id='fGUyRbH']");
	public static final By BUTTON_AR_MODAL_MAKE_PAYMENT = By.xpath("//button[@data-qa-id='fGUyQYY']");	
	
	public static By getPaymentMethodDropdownByFolioName(String folioName){
		return By.xpath("//*[@data-qa-id='fGUyQJG']/span[contains(text(),'"+folioName+"')]//ancestor::tr//input[@data-qa-id='fGUyPUK-click']");
	}
	
	public static By getPaymentMethodDropdownOptionsByFolioName(String folioName){
		return By.xpath("//*[@data-qa-id='fGUyQJG']/span[contains(text(),'"+folioName+"')]//ancestor::tr//*[@data-qa-id='fGUyPUK-text']");
	}
	
	public static By getAmountByFolioName(String folioName){
		return By.xpath("//*[@data-qa-id='fGUyQJG']/span[contains(text(),'"+folioName+"')]//ancestor::tr//input[@data-qa-id='fGUyPRR-val']");
	}
}
