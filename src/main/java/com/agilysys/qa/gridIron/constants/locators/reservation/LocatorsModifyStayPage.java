package com.agilysys.qa.gridIron.constants.locators.reservation;

import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsModifyStayPage {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyQmv']");
	public static final By BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyQmv']//div[@class='panel-heading']//a");

	public static final By INPUT_ARRIVAL_DATE = By.xpath("//*[@data-qa-id='iPKV56E-click']");
	public static final By INPUT_DEPARTURE_DATE = By.xpath("//*[@data-qa-id='iPKV56L-click']");

	public static final By EXPAND_RESERVATION_DETAILS = By.xpath("//*[@data-qa-id='ijVAmNe']");
	public static By CHECKBOX_KEEP_CURRENT_RATE = By.xpath("//button[@data-qa-id='m7gtJyv-click']");
	public static By CHECKBOX_INCLUDE_UNAVAILABLE_RATES = By.xpath("//button[@data-qa-id='fGUyPHx-click']");

	public static By CONFIRM_MODIFY_STAY_YES = By.xpath("//li[@data-qa-id='gA651xP']/button[@data-qa-id='gA651xT']");
	public static By BUTTON_BOOK = By.xpath("//button[@data-qa-id='fGUyRZH']");


	public static By ROOM1 = By.xpath("(//td[@class='rate ng-scope' and @data-qa-id='jRU1Yd9'])[1]");
	public static By ROOM2 = By.xpath("(//td[@class='rate ng-scope' and @data-qa-id='jRU1Yd9'])[2]");
	public static By getRoomTypeArrow(String roomTypeName){
		return By.xpath("//div[@data-qa-id='fGUyRLY']//*[contains(text(),'"+roomTypeName+"')]//ancestor::a//span[@data-qa-id='fGUyQbz']");
	}
	
	public static By getRateByDateElementByRateplan(String roomTypeName, String ratePlanCode, int index){
		return By.xpath("//div[@data-qa-id='fGUyRLY']//*[contains(text(),'"+roomTypeName+"')]//ancestor::div[1]/following-sibling::div//*[contains(text(),'"+ratePlanCode+"')]//ancestor::tr/td["+index+"]");
	}
	
	public static By getSelectedRateElementByIndex(int index){
		return LocatorsBookAReservationPage.getSelectedRateElementByIndex(index);
	}

		
	
	
}
