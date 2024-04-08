package com.agilysys.qa.gridIron.constants.locators.profiles.guest;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsGuestProfilePage {

	// PERSONAL DETAILS FIELD
	public static final By INPUT_LASTNAME = By.xpath("//input[@data-qa-id='fGUyRBE-val']");
	public static final By INPUT_FIRSTNAME = By.xpath("//input[@data-qa-id='fGUyPcQ-val']");
	public static final By INPUT_MIDDLENAME = By.xpath("//input[@data-qa-id='fGUyNrH-val']");
	public static final By INPUT_DOB = By.xpath("//input[@data-qa-id='fGUyPZ1-val']");
	public static final By INPUT_ANNIVERSARY = By.xpath("//input[@data-qa-id='fGUyPEh-val']");
	public static final By INPUT_COMPANY_NAME = By.xpath("//input[@data-qa-id='fGUyQeu-val']");
	public static final By INPUT_ALIAS = By.xpath("//input[@data-qa-id='fGUyPYs-val']");
	public static final By INPUT_COMPANY_TITLE = By.xpath("//input[@data-qa-id='fGUyPKd-val']");
	public static final By INPUT_PRONOUNCED = By.xpath("//input[@data-qa-id='fGUyQKM-val']");
	public static final By INPUT_LANGUAGE = By.xpath("//input[@data-qa-id='jUdbHhb-click']/following-sibling::input[1]");
	public static final By DROPDOWN_GENDER = By.xpath("//input[@data-qa-id='fGUyQxu-click']");
	public static final By LIST_GENDER = By.xpath("//a[@data-qa-id='fGUyQxu-text']");
	public static final By DROPDOWN_SUFFIX = By.xpath("//input[@data-qa-id='fGUyQeU-click']");
	public static final By LIST_SUFFIX = By.xpath("//a[@data-qa-id='fGUyQeU-text']");
	public static final By DROPDOWN_TITLE = By.xpath("//input[@data-qa-id='fGUyPEU-click']");
	public static final By LIST_TITLE = By.xpath("//a[@data-qa-id='fGUyPEU-text']");
	
	//ID Modal
	public static final By SIDEPANE_ID = By.xpath("//div[@data-qa-id='fGUyNak']//span[text()='I.D.']");
	public static final By BUTTON_ADD_ID = By.xpath("//div[@data-qa-id='fGUyPtA']//button");//"//button[@data-qa-id='fGUyRWG']");
	public static final By DROPDOWN_ID_TYPE = By.xpath("//input[@data-qa-id='fGUyREg-click']");
	public static final By LIST_ID_TYPE = By.xpath("//a[@data-qa-id='fGUyREg-text']");
	public static final By INPUT_ID_NUMBER = By.xpath("//input[@data-qa-id='fGUyQJc-val']");
	public static final By BUTTON_EXPIRATION_DATE = By.xpath("//span[@data-qa-id='fGUyQ16-click']");
	public static final By INPUT_ISSUING_AGENCY = By.xpath("//input[@data-qa-id='fGUyQp3-val']");
	public static final By BUTTON_DELETE_ID = By.xpath("//button[@data-qa-id='fGUyPVf']");
	
	//Payment Modal
	public static final By SIDEPANE_PAYMENT = By.xpath("//div[@data-qa-id='fGUyNak']//span[text()='Payment' and @class='m5-nav content ng-binding ng-scope']");
	public static final By BUTTON_ADD_PAYMENT = By.xpath("//button[@data-qa-id='fGUyRiA']");
	public static final By BUTTON_PAYMENT_SET_AS_DEFAULT = By.xpath("//button[@data-qa-id='fGszoDG-click']");
	public static final By DROPDOWN_PAYMENT_METHOD = By.xpath("//input[@data-qa-id='fGUyRoy-click']");
	public static final By LIST_PAYMENT_METHOD = By.xpath("//a[@data-qa-id='fGUyRoy-text']");
	public static final By INPUT_DIRECTBILL_ACCOUNT_NAME = By.xpath("//input[@data-qa-id='fGUyQBu']");
	public static final By LIST_DIRECTBILL_ACCOUNT_NAME = By.xpath("//div[@data-qa-id='fGUyNq5']");
	public static final By BUTTON_SAVE_PAYMENT = By.xpath("//button[@data-qa-id='fGUyPpV']");
	public static final By SPAN_PAYMENT_METHOD_NAME = By.xpath("//*[@data-qa-id='fGUyQmj']");
	public static final By SPAN_DEFAULT_PAYMENT_METHOD_NAME = By.xpath("//*[@data-qa-id='hGeuXT3']//ancestor::tr[1]//*[@data-qa-id='fGUyQmj']");
	
	public static By getPaymentMethodByName(String paymentMethod){
		return By.xpath("//*[@data-qa-id='fGUyQmj'][contains(text(),'"+paymentMethod+"')]");
	}
	//Comment Section
	public static final By SIDEPANE_COMMENTS = By.xpath("//div[@data-qa-id='fGUyNak']//*[text()='Comments']");
	public static final By SIDEPANE_DOCUMENTATTACHMENT = By.xpath("//div[@data-qa-id='fGUyNak']//*[text()='Document Attachment']");
	public static final By TEXTAREA_COMMENT = By.xpath("//textarea[@data-qa-id='im9VDKW-val']");
	public static final By DROPDOWN_COMMENT_TYPE = By.xpath("//input[@data-qa-id='im9VDLf-click']");
	public static final By LIST_COMMENT_TYPE =  By.xpath("//a[@data-qa-id='im9VDLf-text']");
	public static final By DROPDOWN_COMMENT_SEVERITY = By.xpath("//input[@data-qa-id='im9VDJG-click']");
	public static final By LIST_COMMENT_SEVERITY = By.xpath("//a[@data-qa-id='im9VDJG-text']");
	public static final By BUTTON_SAVE_COMMENT = By.xpath("//button[@data-qa-id='im9VDJn']");
	public static final By CLICK_ADD_COMMENT = By.xpath("//button[@data-qa-id='im9VDJr']");
	
	// Group Modal
	public static final By NAV_GROUP = By.xpath("//*[@href='#groups']");
	public static final By GROUP_BUTTON_ADD = By.xpath("//button[@data-qa-id='gwMjjud']");

	// Footer
	public static final By BUTTON_SAVE = By.xpath("//*[@data-qa-id='fGUyNao']");
	public static final By BUTTON_SAVE_EXIT = By.xpath("//*[@data-qa-id='fGUyR5o']");
	public static final By BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyNxQ']");

}
