package com.agilysys.qa.gridIron.constants.locators.shared.widgets;

import org.openqa.selenium.By;

public class Calendars {
	
	public static final By BUTTON_CLEAR = By.xpath("//button[text()='Clear']");
	public static final By BUTTON_TODAY = By.xpath("//button[text()='Today']");
	public static final By BUTTON_PREVIOUS_MONTH = By.xpath("//button[@ng-click='move(-1)']");
	public static final By BUTTON_NEXT_MONTH = By.xpath("//button[@ng-click='move(1)']");
	public static final By BUTTON_CENTER = By.xpath("//*[contains(text(),'Today')]/parent::span/parent::li/preceding-sibling::li//table//tr[1]/th[2]/button[1]");
	public static final By LIST_YEAR_BUTTONS = By.xpath("//tbody//td[contains(@class,'uib-year')]//span");
	public static final By LIST_MONTH_BUTTONS = By.xpath("//tbody//td[contains(@class,'uib-month')]//span");
	public static final By LIST_MONTH_YEAR_BUTTONS = By.xpath("//tbody//button[not(@disabled)]/span");
	public static final By LIST_DAYS_BUTTONS = By.xpath("//tbody//span[contains(@class,'ng-binding')][not(contains(@class,'text-muted'))]/ancestor::button[not(@disabled)]");
	public static final By CURRENT_DATE = By.xpath("//time[1]");
	
	//btn btn-default btn-sm btn-info active - check the active DAY button for reference 
	//btn btn-default btn-info active - check the active MONTH button for reference
	//btn btn-default btn-info active - check the active YEAR button for reference
	
	

}