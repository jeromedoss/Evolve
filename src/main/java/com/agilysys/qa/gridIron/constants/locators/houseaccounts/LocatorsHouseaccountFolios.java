package com.agilysys.qa.gridIron.constants.locators.houseaccounts;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsHouseaccountFolios {

	public static final By BUTTON_FOLIO_MORE = By.xpath("//*[@data-qa-id='fiwAvZi']");
	public static final By BUTTON_ADD_CHARGE = By.xpath("//*[@data-qa-id='fiwAvZi']//a[text()='Add a Charge']/..");
	public static final By BUTTON_ADD_CREDIT = By.xpath("//*[@data-qa-id='fiwAvZi']//a[text()='Add a Credit']/..");
	public static final By BUTTON_MAKE_PAYMENT = By.xpath("//*[@data-qa-id='fiwAvZi']//a[text()='Make a Payment']/..");
	public static final By BUTTON_TRANSFER = By.xpath("//*[@data-qa-id='fiwAvZ4']");

	public static final By TAB_SUMMARY = By.xpath("//*[@data-qa-id='fiwAvZm']/a");
	public static final By SUMMARY_TOTAL = By.xpath("//*[@data-qa-id='fiwAvZK']");

	public static final By TAB_FOLIO1 = By.xpath("//*[@data-qa-id='fiwAvaA']/a");
	public static final By TOTAL_FOLIO1 = By.xpath("//*[@data-qa-id='fiRpmpz']/td[9]");
	public static final By FOLIO1_LIST_OF_ITEMS = By
			.xpath("//*[@data-qa-id='fiRpmuw']/td[4]//a[@data-qa-id='fiRpmvy']");
	public static final By FOLIO_CHARGE_AMOUNT_LIST = By.xpath("//*[@data-qa-id='g6YW3Jx']");

	public static final By ITEM_EXPAND_BUTTON = By.xpath("//*[@data-qa-id='fiRpmxK']");
	public static final By TRANSFER_SOURCE_CHECK = By.xpath("//*[@data-qa-id='gjdPrM5'][1]");
	public static final By TRANSFER_DESTINATION_CHECK = By.xpath("//*[@data-qa-id='gtvpAhP'][2]/a");

	public static final By FOLIO_SELECTALL_BUTTON = By.xpath("//*[@data-qa-id='fiRpmgR-click']");

}
