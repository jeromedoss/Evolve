package com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsNonInvoicedChargesSection {

	public static final By BUTTON_MORE = By.xpath("//*[@data-qa-id='fGUyPXV']//button[text()='More']");
	public static final By BUTTON_ADD_A_CHARGE = By.xpath("//*[@data-qa-id='fGUyPXV']//li[2]");
	public static final By BUTTON_ADD_A_CREDIT = By.xpath("//*[@data-qa-id='fGUyPXV']//li[3]");
	public static final By BUTTON_ADJUST_CHARGES = By.xpath("//*[@data-qa-id='fGUyPXV']//li[4]");
	public static final By BUTTON_TRANSFER_CHARGES = By.xpath("//*[@data-qa-id='fGUyPXV']//li[5]");

	public static final By BUTTON_CREATE_INVOICE = By.xpath("//*[@data-qa-id='fGUyPHB']");

	public static final By INPUT_SEARCH = By.xpath("//*[@data-qa-id='fGUyQd5']//input[@data-qa-id='fGUyRJz-val']");

	public static final By CHECBOX_SELECTALL = By.xpath("//button[@data-qa-id='fGUyNpJ-click']");

	public static final By LABEL_GRAND_TOTAL_VALUE = By.xpath("//*[@data-qa-id='fGUyNqq']");
	public static final By LABEL_GRAND_TAX_VALUE = By.xpath("//*[@data-qa-id='fGUyQ3Z']");
	public static final By LABEL_GRAND_CHARGE_VALUE = By.xpath("//*[@data-qa-id='fGUyNyC']");

	public static final By LABEL_TOTAL_VALUE = By.xpath("//*[@data-qa-id='hDtPmnD']");
	public static final By LABEL_TAX_VALUE = By.xpath("//*[@data-qa-id='hDtPmnM']");
	public static final By LABEL_CHARGE_VALUE = By.xpath("//*[@data-qa-id='hDtPmmh']");

	public static final By LABEL_DATE_VALUE = By.xpath("//*[@data-qa-id='hDtPmmc']");
	public static final By LABEL_ITEM_DESCRIPTION_VALUE = By.xpath("//*[@data-qa-id='fGUyP1n']");

	public static final By MESSAGE_EMPTY_NON_INVOICE = By.xpath("//*[@data-qa-id='fGUyP7D']");

	public static final By LABEL_NON_GROUP_RELATED = By.xpath("//*[@data-qa-id='hDFev32']");

}
