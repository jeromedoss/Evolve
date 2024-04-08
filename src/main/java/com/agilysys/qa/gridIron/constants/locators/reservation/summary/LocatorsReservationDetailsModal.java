package com.agilysys.qa.gridIron.constants.locators.reservation.summary;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsReservationDetailsModal {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyPJM']");

	public static final By INPUT_SOURCE_OF_BUSINESS = By.xpath("//input[@data-qa-id='fGUyPEm-click'][@placeholder]");
	public static final By INPUT_GUEST_TYPE = By.xpath("//input[@data-qa-id='h24GLP6-click']");
	public static final By INPUT_MARKET_SEGMENT = By.xpath("//input[@data-qa-id='fGUyQWM-click'][@placeholder]");

	public static final By LINK_ADD_TRAVEL_AGENT = By.xpath("//a[@data-qa-id='fGUyNhs']");

	public static final By INPUT_TRAVEL_AGENT = By.xpath("//*[@data-qa-id='fGUyNgW-val']");
	public static final By BUTTON_DELETE_TRAVEL_AGENT = By.xpath("//*[@data-qa-id='fGUyNjS']");

	public static final By LINK_ADD_CONFORMATION_NUMBER = By.xpath("//*[@data-qa-id='fGUyRRd']");

	public static final By INPUT_THIRD_PARTY_SOURCE = By.xpath("//input[@data-qa-id='fGUyR77-click'][@placeholder='']");
	public static final By INPUT_CONFORMATION_NUMBER = By.xpath("//input[@data-qa-id='fGUyQ9e-val']");
}
