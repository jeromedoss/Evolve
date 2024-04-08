package com.agilysys.qa.gridIron.constants.locators.reservation.payments;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsPaymentsSection {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyQvJ']");
	public static final By BUTTON_NEW_PAYMENT_METHOD = By.xpath("//*[@data-qa-id='fGUyRfP']");
	public static final By SYMBOL_DEFAULT = By.xpath("//*[@data-qa-id='fGUyPF2']");
	public static final By DEFAULT_PAYMENT_METHOD_NAME = By.xpath("//i[@data-qa-id='fGUyPF2']/../span/span");
	public static final By TAB_PAYMENT = By.xpath("//*[@data-qa-id='fGUyPSy']");
	
	public static final By LINK_DIRECTBILL_ACCOUNT = By.xpath("//a[@data-qa-id='fGUyRT6']//ancestor::div[contains(@class,'active')]//*[@data-qa-id='fGUyPEV']");
	public static final By LINK_FOLIOS = By.xpath("//a[@data-qa-id='fGUyRT6']//ancestor::div[contains(@class,'active')]//*[@data-qa-id='fGUyRT6']");
	public static final By CHECKBOX_THIRD_PARTY_PAYMENT = By.xpath("//a[@data-qa-id='fGUyRT6']//ancestor::div[contains(@class,'active')]//*[@data-qa-id='fGUyRGu-click']");
	public static final By CHECKBOX_DO_NOT_DISCLOSE_RATES = By.xpath("//a[@data-qa-id='fGUyRT6']//ancestor::div[contains(@class,'active')]//*[@data-qa-id='fGUyPHM-click']");
	public static final By LINK_AUTHORIZE_AMOUNT = By.xpath("//a[@data-qa-id='fGUyRT6']//ancestor::div[contains(@class,'active')]//*[@data-qa-id='mQXVJ7C']");
	public static final By LINK_AUTHORIZE_RELEASE = By.xpath("//a[@data-qa-id='fGUyRT6']//ancestor::div[contains(@class,'active')]//*[@data-qa-id='fGUyNjQ']");
	public static final By RADIO_MAKE_THIS_DEFAULT = By.xpath("//a[@data-qa-id='fGUyRT6']//ancestor::div[contains(@class,'active')]//*[@data-qa-id='fGUyR2t-click']");
	public static final By LINK_DELETE_THIS_PAYMENT = By.xpath("//a[@data-qa-id='fGUyRT6']//ancestor::div[contains(@class,'active')]//*[@data-qa-id='fGUyPRa']");
	
	public static By getPaymentMethodByName(String paymentMethodName){
		return By.xpath("//span[normalize-space()='"+paymentMethodName+"']");
	}

}
