package com.agilysys.qa.gridIron.constants.locators.shared.folios;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsFoliosDetails {

	// NAVIGATION LEFT SIDE PANEL BUTTONS
	public static final By NAV_PAYMENT = By
			.xpath("//div[@data-qa-id='fGUyNoY']//span[contains(@class,'ng-scope nav-payment-wrapper')]");

	// FOLIO MODAL
	public static final By BUTTON_MORE = By
			.xpath("//button[contains(normalize-space(),'More')]");
	public static final By BUTTON_ADD_A_CHARGE = By
			.xpath("//a[contains(text(),'Add a Charge')]");
	public static final By BUTTON_ADD_A_CREDIT = By
			.xpath("//a[contains(text(),'Add a Credit')]");
	public static final By BUTTON_MAKE_PAYMENT = By
			.xpath("//a[contains(text(),'Make a Payment')]");

	public static final By BUTTON_ADD_NEW_FOLIO = By
			.xpath("//a[contains(text(),'Add New Folio')]");
	
	public static final By BUTTON_ADD_ROUTING_RULE = By
			.xpath("//a[contains(text(),'Add Routing Rule')]");
	public static final By MODAL_ADD_ROUTING_RULE = By.xpath("//*[@data-qa-id='fGUyPQh']");

	public static final By TAB_SUMMARY = By.xpath("//*[@data-qa-id='fiwAva9']");
	public static final By LABEL_SUMMARY_TOTAL = By.xpath("//*[@data-qa-id='fGUyRfw']");
	public static final By LABEL_SUMMARY_TOTAL_NEW = By.xpath("//*[@data-qa-id='iDTNMUq']");

	public static final By TAB_FOLIO_1 = By.xpath("//*[@data-qa-id='fiwAvaA']/a");
	public static final By LABEL_TOTAL_FOLIO_1 = By.xpath("//*[@data-qa-id='fiRpmpz']/td[9]");
	public static final By LIST_FOLIO1_ITEMS = By.xpath("//*[@data-qa-id='fiRpmuw']/td[4]//a[@data-qa-id='fiRpmvy']");

	public static final By BUTTON_FOLIO_PRINT_1 = By.xpath("//*[@data-qa-id='fiwAva9']");
	public static final By BUTTON_FOLIO_PRINT_2 = By.xpath("//*[@data-qa-id='fGUyR3S']");

	public static final By LABEL_QUANTITY_FOLIO1 = By.xpath("//*[@data-qa-id='g6YW3Jx']");
	public static final By LABEL_CONTENT_FOLIO1 = By.xpath("//*[@data-qa-id='mKe68dw']");

	public static final By BUTTON_COLLAPSE_ITEM = By.xpath("//*[@data-qa-id='mKe66nf']");
	public static final By LABEL_TRANSFER_SOURCE = By.xpath("//*[@data-qa-id='gjdPrM5'][1]");
	public static final By LABEL_TRANSFER_DESTINATION = By.xpath("//*[@data-qa-id='gtvpAhP'][2]/a");

	// Folios 1
	public static final By BUTTON_SELECTALL_FOLIO1 = By.xpath("//*[@data-qa-id='fiRpmgR']");// *[@data-qa-id='fiRpmqh']//th[2]");
	public static final By BUTTON_TRANSFER = By.xpath("//*[@data-qa-id='fiwAvZ4']");
	
	public static final By INPUT_ADD_NEW_FOLIO_NAME = By.xpath("//*[@data-qa-id='fGUyRc6-val']");
	public static final By BUTTON_ADD_NEW_FOLIO_SAVE = By.xpath("//*[@data-qa-id='fGUyRPg']");

	public static final By LINE_ITEM_1 = By.xpath("//*[@data-qa-id='fiRpmwB']");



}