package com.agilysys.qa.gridIron.constants.locators.shared.folios;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsAddACreditModal {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyRjp']");
	public static final By BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyRjp']//div[@class='panel-heading']//a");

	public static final By INPUT_SEARCH = By.xpath("//*[@data-qa-id='fGUyNeL-val']");
	public static final By DROPDOWN_SOURCE = By.xpath("//*[@data-qa-id='fGUyPSh-click']");
	public static final By DROPDOWN_OPTIONS_SOURCE = By.xpath("//*[@data-qa-id='fGUyPSh-text']");
	public static final By INPUT_AMOUNT = By.xpath("//*[@data-qa-id='fGUyR5k-val']");
	public static final By DROPDOWN_FOLIO = By.xpath("//*[@data-qa-id='fGUyPpF-click']");
	public static final By DROPDOWN_OPTIONS_FOLIO = By.xpath("//*[@data-qa-id='fGUyPpF-text']");
	public static final By TEXTAREA_REASON = By.xpath("//*[@data-qa-id='fGUyQ1a-val']");	
	public static final By LABEL_PRICE = By.xpath("//*[@data-qa-id='fGUyNru']");
	public static final By BUTTON_DONE = By.xpath("//button[@data-qa-id='fGUyP1Q']");
	
	public static By getItemByName(String itemName){
		return By.xpath("//*[@data-qa-id='fGUyRam']//*[contains(text(),'"+itemName+"')]");
	}

}
