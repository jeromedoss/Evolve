package com.agilysys.qa.gridIron.constants.locators.reservation.gueststayhistory;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsGuestStayHistorySection {

	public static final By TAB_SUMMARY = By.xpath("//*[@data-qa-id='gjcivNv']//a");
	public static final By TAB_CURRENT = By.xpath("//*[@data-qa-id='gjcivPY']//a");
	public static final By TAB_FUTURE = By.xpath("//*[@data-qa-id='gjcivPK']//a");
	public static final By TAB_PAST = By.xpath("//*[@data-qa-id='gjcivRu']//a");

	public static final By LABEL_DATE_LAST_STAY_ARRIVAL = By.xpath("//*[@data-qa-id='gza8HDp']");
	public static final By LABEL_DATE_LAST_STAY_DEPARTURE = By.xpath("//*[@data-qa-id='gza8HDu']");

	public static final By LABEL_DATE_CURRENT_ARRIVAL = By.xpath("//*[@data-qa-id='gjcivR8']");
	public static final By LABEL_DATE_CURRENT_DEPARTURE = By.xpath("//*[@data-qa-id='gjcivPL']");

	public static final By LABEL_DATE_FUTURE_ARRIVAL = By.xpath("//*[@data-qa-id='gjcivMS']");
	public static final By LABEL_DATE_FUTURE_DEPARTURE = By.xpath("//*[@data-qa-id='gjcivMj']");

	public static final By LABEL_DATE_PAST_ARRIVAL = By.xpath("//*[@data-qa-id='gjcivPM']");
	public static final By LABEL_DATE_PAST_DEPARTURE = By.xpath("//*[@data-qa-id='gjcivMh']");

}
