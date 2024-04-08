package com.agilysys.qa.gridIron.constants.locators.booking;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsBookAReservationPage {
	public static final By INPUT_ARRIVAL_DATE = By.xpath("//input[@data-qa-id='fGUyPHd-val']");
	public static final By INPUT_ARRIVAL_DATE_PICKER = By.xpath("//span[@data-qa-id='fGUyPHd-click']");
	public static final By INPUT_DEPARTURE_DATE = By.xpath("//input[@data-qa-id='fGUyRMP-val']");
	public static final By RANDOM_CLICK = By.xpath("//*[@data-qa-id='fGUyNvU-text']");
	public static final By INPUT_DEPARTURE_DATE_PICKER = By.xpath("//span[@data-qa-id='fGUyRMP-click']");
	public static final By INPUT_NO_OF_NIGHTS = By.xpath("//input[@data-qa-id='fGUyPZv-val']");
	public static final By INPUT_ADULT_COUNT = By.xpath("//ag-label[@title='Adults']/..//input");
	public static final By INPUT_CHILD_COUNT = By.xpath("//ag-label[@title='Children']/..//input");
	public static final By BUTTON_INCLUDE_UNAVAILABLE = By.xpath("//button[@data-qa-id='fGUyPkS-click']");
	public static final By BUTTON_INCLUDE_NONBEDDED = By.xpath("//button[@data-qa-id='hC4sRxu-click']");		
	public static final By BUTTON_CHECK_RATES = By.xpath("//button[@data-qa-id='fGUyQsJ']");
	
	public static final By INPUT_ADJUST_RATE_AMOUNT = By.xpath("//input[@data-qa-id='g57GZfV-val']");
	public static final By INPUT_ADJUST_CHARGE_AMOUNT = By.xpath("//input[@data-qa-id='gZYQT2A-val']");
	public static final By BUTTON_ADJUST_RATE_APPLY_REMAINING_DATE = By.xpath("//button[@data-qa-id='fzzbnkg-click']");
	public static final By TEXTAREA_ADJUST_RATE_COMMENTS = By.xpath("//textarea[@data-qa-id='g57GbCt-val']");
	public static final By DROPDOWN_ADJUST_RATE_COMP_REASON = By.xpath("//input[@data-qa-id='fzzbmjq-click']");
	public static final By INPUT_COMP_CERTIFICATE_NUMBER = By.xpath("//input[@data-qa-id='gsWcM9s-val']");
	public static final By LINK_COMP_I_DONT_HAVE_CERTIFICATE = By.xpath("//a[@data-qa-id='gsWcM8o']");
	public static final By LINK_ISSUE_CERTIFICATE = By.xpath("//a[@data-qa-id='gsWcMGA']");
	
	public static final By LIST_ADJUST_RATE_COMP_REASON = By.xpath("//a[@data-qa-id='fzzbmjq-text']");
	public static final By BUTTON_ADJUST_SAVE = By.xpath("//button[@data-qa-id='fzzbmjs']");
	public static final By LINK_ADJUST_CANCEL = By.xpath("//a[@data-qa-id='fzzbnkh']");
	public static final By BUTTON_ADJUST_RATE_CONFIRM_YES = By.xpath("//li[@ng-if='dialogConfig.yes']/button");
	public static final By BUTTON_ADJUST_RATE_CONFIRM_NO = By.xpath("//li[@ng-if='dialogConfig.no']/button");
	
	public static final By DIV_RATE_SEARCH_RESULT_HEADER = By.xpath("//div[@data-qa-id='fGUyQoy']");
	public static final By BUTTON_BOOK = By.xpath("//button[@data-qa-id='fGUyPiX']");
	
	public static By getRatePlanArrow(String ratePlanName){
		return By.xpath("//div[@data-qa-id='fGUyQfE']//span[contains(text(),'"+ratePlanName+"')]/preceding-sibling::span[@data-qa-id='fGUyPJ5']");
	}
	
	public static By getRateByDateElementByRateplan(String ratePlanName, String roomTypeCode, int index){
		//div[@data-qa-id='fGUyPSK' and contains(string(), 'zo42es')]//tr[contains(string(),'yixlff')]/td[4]
		return By.xpath("//div[@data-qa-id='fGUyPSK' and contains(string(), '"+ratePlanName+"')]//tr[contains(string(),'"+roomTypeCode+"')]/td["+index+"]");
		//return By.xpath("//div[@data-qa-id='fGUyQfE']//*[contains(text(),'"+ratePlanName+"')]//ancestor::div[1]/following-sibling::div//*[contains(text(),'"+roomTypeCode+"')]//ancestor::tr/td["+index+"]");
	}
	
	public static By getSelectedRateElementByIndex(int index){
		//return By.xpath("//td[@data-qa-id='fGUyQbo']/td["+index+"]//button");
		return By.xpath("//td[@data-qa-id='fGUyQbo']["+index+"]/ul/li");
	}



	public static By getSelectedRateElement(int index){

		return By.xpath("//td[@data-qa-id='fGUyQbo']["+index+"]//button");
	}
}