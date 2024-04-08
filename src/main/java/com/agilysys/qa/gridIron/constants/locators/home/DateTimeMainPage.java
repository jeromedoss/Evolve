package com.agilysys.qa.gridIron.constants.locators.home;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class DateTimeMainPage {

	public static final By DATE_HEADER = By.xpath("//*[@class='chrome-main ng-scope']//time[1]");
	public static final By TIME_HEADER = By.xpath("//*[@class='chrome-main ng-scope']//time[2]");

	public static final By LIST_ARRIVAL_DATE = By.xpath("//*[@data-qa-id='fGUyQES']");
	public static final By LIST_DEPARTURE_DATE = By.xpath("//*[@data-qa-id='fGUyPuC']");

	public static final By DATE_RANGE_GROUP = By.xpath("//*[@data-qa-id='fGUyPqz']");

	public static final By DATEPICKER = By.xpath("//*[@data-qa-id='fGUyPTE-val' and @name='dateInputBox']");

}
