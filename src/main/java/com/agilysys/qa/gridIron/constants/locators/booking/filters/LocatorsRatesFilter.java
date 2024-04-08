package com.agilysys.qa.gridIron.constants.locators.booking.filters;

import org.openqa.selenium.By;

public class LocatorsRatesFilter {

	public static final By CHECKBOX_UNAVAILABLE_RATES = By.xpath("//*[@data-qa-id='fGUyPkS-click']");
	public static final By CHECKBOX_NON_BEDDED_ROOMS = By.xpath("//*[@data-qa-id='hC4sRxu-click']");

	public static final By INPUT_RATE_SEARCH = By.xpath("//*[@data-qa-id='fGUyPrF-val']");
	
	public static final By BUTTON_COLLAPSE_RATE_PLANS = By.xpath("//*[@title='Rate Plans']//h3");
	public static final By LIST_CHECKBOX_RATE_PLANS = By.xpath("//*[@data-qa-id='fGUyPbr-item']");
	// $$(LocatorsRatesFilter.LIST_CHECKBOX_RATE_PLANS).findBy(Condition.text("BAR")).vicks
	
	public static final By BUTTON_COLLAPSE_PACKAGES = By.xpath("//*[@title='Packages']//h3");
	public static final By LIST_CHECKBOX_PACKAGES = By.xpath("//*[@title='Packages']//*[@data-qa-id='fGUyPbr-item']");

}
