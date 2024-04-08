package com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.summary;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsSummarySection {


	public static final By LINK_AR_CREATE = By.xpath("//a[@data-qa-id='fGUyPXg']");
	public static final By INPUT_CREDIT_LIMIT = By.xpath("//*[@data-qa-id='fGUyRTm-val']");
	public static final By DROPDOWN_TERMS = By.xpath("//*[@data-qa-id='fGUyQ3q-click']");
	public static final By DROPDOWN_OPTIONS_TERMS = By.xpath("//*[@data-qa-id='fGUyQ3q-text']");
	

	public static final By AccountContactDD = By.xpath("//input[@data-qa-id='fGUyRJf-click']\n");
	public static final By DROPDOWN_VALUE_ACCOUNT_CONTACT = By.xpath("//input[@data-qa-id='fGUyRJf-click' and  @placeholder=\"Search\"]");

	public static final By INPUT_ACCOUNT_NUMBER = By.xpath("//input[@data-qa-id='fGUyPww-val']");
	public static final By BUTTON_PREFERRED_EMAIL = By.xpath("//button[@data-qa-id='fH2BFUo-click']");
	public static final By BUTTON_PREFERRED_PRINT = By.xpath("//button[@data-qa-id='fH2BFbu-click']");
	public static final By LINK_ROUTING_RULE = By.xpath("//a[@data-qa-id='fGUyRDZ']");
	public static final By LINK_TAX_EXEMPT = By.xpath("//a[@data-qa-id='fGUyQ88']");
	public static final By LINK_ACCOUNT_STATUS = By.xpath("//a[@data-qa-id='fGUyNx1']");

	public static final By BUTTON_MAKE_PAYMEN = By.xpath("//*[@data-qa-id='fGUyRp8']//button[@data-qa-id='fGUyQbq']");

}
