package com.agilysys.qa.gridIron.constants.locators.reservation.folios;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsFoliosSection {

	public static final By INPUT_SEARCH = By.xpath("//*[@data-qa-id='fiwAvZn-val']");
	public static final By BUTTON_PRINT = By.xpath("//*[@data-qa-id='fiwAvZ2']");
	public static final By BUTTON_EMAIL = By.xpath("//*[@data-qa-id='fiwAvZt']");
	public static final By BUTTON_MORE = By.xpath("//*[@data-qa-id='fiwAvZi']");
	public static final By BUTTON_ADJUST = By.xpath("//*[@data-qa-id='fiwAvZ3']");
	public static final By BUTTON_TRANSFER = By.xpath("//*[@data-qa-id='fiwAvZ4']");
	public static final By DROPDOWN_ADD_CHARGE = By.xpath("//a[text()='Add a Charge']");
	public static final By DROPDOWN_ADD_CREDIT = By.xpath("//a[text()='Add a Credit']");
	public static final By DROPDOWN_MAKE_PAYMENT = By.xpath("//a[text()='Make a Payment']");
	public static final By DROPDOWN_ADD_ROUTING_RULE = By.xpath("//a[text()='Add Routing Rule']");

	public static final By DROPDOWN_ADD_FOLIO = By.xpath("//a[text()='Add New Folio']");
	public static final By DROPDOWN_RENAME_FOLIO = By.xpath("//a[text()='Rename Folio']");
	public static final By DROPDOWN_TRANSFER_AMOUNT = By.xpath("//a[text()='Transfer Amount']");
	public static final By DROPDOWN_DELETE_FOLIO = By.xpath("//a[text()='Delete Folio']");
	
	public static final By TAB_SUMMARY = By.xpath("//*[@data-qa-id='fiwAvZm']//a");
	public static final By LIST_TAB_FOLIO = By.xpath("//*[@data-qa-id='fiwAvaA']//a");
	public static final By ICON_DEFAULT_FOLIO = By.xpath("//i[@data-qa-id='fGUyPeW']");
	public static final By BUTTON_PLUS_ADD_FOLIO = By.xpath("//*[@data-qa-id='fiwAvZP']");
	public static final By DROPDOWN_DEFAULT_FOLIO = By.xpath("//*[@data-qa-id='g6YW37x-click']");
	public static final By LIST_DROPDOWN_DEFAULT_FOLIO = By.xpath("//*[@data-qa-id='g6YW37x-item']");
	public static final By LIST_FOLIOS = By.xpath("//*[@data-qa-id='fGUyQaX']");
	public static final By LIST_FOLIOS_VALUE = By.xpath("//*[@data-qa-id='fGUyPmB']");
	public static final By LINK_ADD_ROUTING_RULE = By.xpath("//*[@data-qa-id='g6YW32B']");

	public static final By LABEL_FOLIO_TOTAL = By.xpath("//*[@data-qa-id='fiRpmpz']/td[9]");
	public static final By CHECKBOX_MAIN_SELECT = By.xpath("//*[@data-qa-id='fiRpmgR-click']");

	public static final By LABEL_FOLIO_ITEM_TOTAL = By.xpath("//*[@data-qa-id='fiRpmuw']/td[9]");
	public static final By CHECKBOX_ITEM_SELECT = By.xpath("//*[@data-qa-id='fiRpmuw']//td[2]//button");

	public static final By LIST_ITEM_POST_DATE = By.xpath("//*[@data-qa-id='fiRpmuw']//td[3]");
	public static final By LINK_ITEM_NAME = By.xpath("//*[@data-qa-id='g6YW38A']");
	public static final By LINK_ITEM_DATE = By.xpath("//*[@data-qa-id='gxUexem']");
	public static final By LABEL_ITEM_QUANTITY = By.xpath("//*[@data-qa-id='fiRpmuw']//td[5]");
	public static final By LABEL_ITEM_AMOUNT = By.xpath("//*[@data-qa-id='fiRpmuw']//td[6]");
	public static final By LABEL_ITEM_TAX = By.xpath("//*[@data-qa-id='fiRpmuw']//td[7]");
	public static final By LABEL_ITEM_PAMENT = By.xpath("//*[@data-qa-id='fiRpmuw']//td[8]");
	public static final By BUTTON_COLLAPSE_ITEM = By.xpath("//*[@data-qa-id='fiRpmuw']//td[1]");

	public static final By LABEL_EXPANDED_ITEM_ORIGINAL_CHARGE_DATE = By.xpath("//*[@data-qa-id='fiRpmy7']");
	public static final By LABEL_EXPANDED_ITEM_TRANSFER_CHARGE_DATE = By.xpath("//*[@data-qa-id='fiRpmu8']");
	public static final By LABEL_EXPANDED_ITEM_ADJUSTED_CHARGE_DATE = By.xpath("//*[@data-qa-id='fiRpmuh']");
	public static final By LABEL_TRANSFERRED_ITEM_COMMENT = By.xpath("//*[@data-qa-id='fiRpmuU']");

	public static final By LABEL_TRANSFER_SOURCE_CHECK = By.xpath("//*[@data-qa-id='gjdPrM5'][1]");
	public static final By LABEL_TRANSFER_DESTINATION_CHECK = By.xpath("//*[@data-qa-id='gtvpAhP'][2]/a");

	private static final String getLineItemByName(String lineItemName){
		return "//td[@data-qa-id='fiRpmwB']//*[contains(text(),'"+lineItemName+"')]//ancestor::tr[@data-qa-id='mKe67p3']";
	}

	private static final String getLineItemByReason(String reason){
		return "//td[@data-qa-id='fiRpmwB']//*[contains(text(),'"+reason+"')]//ancestor::tr[@data-qa-id='mKe67p3']";
	}
	
	public static By getFolioTabByName(String folioName){
		return By.xpath("//div[@data-qa-id='fiwAvZj']/ul//*[contains(text(),'"+folioName+"')]");
	}
	
	public static final By getLineItemDescription(String lineItemName){
		return By.xpath(getLineItemByName(lineItemName)+"//*[@data-qa-id='g6Y38A']");
	}
	
	public static final By getLineItemCheckBox(String lineItemName){
		return By.xpath(getLineItemByName(lineItemName)+"//button[@data-qa-id='fiRpmxb-click']");
	}
	
	public static final By getLineItemDate(String lineItemName){
		return By.xpath(getLineItemByName(lineItemName)+"//td[@data-qa-id='fiRpmwU']");
	}
	

	public static final By getLineItemTotal(String lineItemName){
		return By.xpath(getLineItemByName(lineItemName)+"//td[@data-qa-id='mKe68XP']");
	}
	public static final By getLineItemTotalByReason(String reason){
		return By.xpath(getLineItemByReason(reason)+"//td[@data-qa-id='mKe68XP']");
	}
}
