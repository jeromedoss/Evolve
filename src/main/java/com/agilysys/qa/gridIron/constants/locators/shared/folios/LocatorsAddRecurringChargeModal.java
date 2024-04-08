package com.agilysys.qa.gridIron.constants.locators.shared.folios;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsAddRecurringChargeModal {

	public static By HEADER_MODAL = By.xpath("//div[@data-qa-id='fiRpmrc']//h3[text()='Add a Charge']");
	public static By BUTTON_CLOSE = By.xpath("//div[@data-qa-id='fiRpmrc']//a[@data-ng-click='closeModal()']");

	//Add items screen
	public static By INPUT_SEARCH = By.xpath("//input[@data-qa-id='fbRy3Gj-val']");
	public static By BUTTON_SELECT_ITEMS_STEP_NEXT = By.xpath("//button[@data-qa-id='hKaNt4d']");
	
	private static String getItemRow(String itemName){
		return "//tbody[@data-qa-id='fbRy3A7']//*[contains(text(),'"+itemName+"')]//ancestor::tr";
	}
	
	public static By getItemCharge(String itemName){
		return By.xpath(getItemRow(itemName)+"//input[@data-qa-id='fprNAZF-val']");
	}
	
	public static By getItemQuantity(String itemName){
		return By.xpath(getItemRow(itemName)+"//input[@data-qa-id='fbRy3FQ-val']");
	}
	
	public static By getItemAddlink(String itemName){
		return By.xpath(getItemRow(itemName)+"//a[@data-qa-id='fbRy3G6']");
	}

	//Set frequency Screen
	public static By INPUT_FREQUENCY_START_DATE = By.xpath("//input[@data-qa-id='gnzri3R-val']");
	public static By INPUT_FREQUENCY_END_DATE = By.xpath("//input[@data-qa-id='gnzri4Y-val']");
	public static By RADIO_FREQUENCY_EVERY_N = By.xpath("//button[@data-qa-id='gnzri5G-click']");
	public static By RADIO_FREQUENCY_FIRST_NIGHT = By.xpath("//button[@data-qa-id='gnzri5Y-click']");
	public static By RADIO_FREQUENCY_DAYS_OF_WEEK = By.xpath("//button[@data-qa-id='gnzri4s-click']");
	public static By INPUT_EVERY_N_NIGHT = By.xpath("//input[@data-qa-id='gnzri5N-val']");
	public static By BUTTON_FREQUENCY_STEP_NEXT = By.xpath("//button[@data-qa-id='hKaNtCB']");
	public static By BUTTON_CHECK_AVAILABILITY = By.xpath("//button[@data-qa-id='gnzrhxL']");
	
	public static By getSelectItem(String itemName){		
		return By.xpath("//tbody[@data-qa-id='gnzri2t']//*[contains(text(),'"+itemName+"')]");
	}
	
	public static By getDaysOfWeek(String day){
		return By.xpath("//label[@data-qa-id='gnzri53-label-text'][contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'"+day+"')]");
	}
	
	//Post Charge
	public static By BUTTON_BACK = By.xpath("//div[@data-qa-id='fiRpmNP']//button[contains(text(), 'Back')]");
	public static By TEXTAREA_REASON = By.xpath("//textarea[@data-qa-id='kuehsNa-val']");
	public static By ADD_CHARGE = By.xpath("//button[@data-qa-id='fiRpmsf']");
//	public static By getExpandIcon(String itemName){
//		return By.xpath("//td[contains(text(),'ai4')]//ancestor::tr//i[@data-qa-id='fprNAhF']");
//	}
}
