package com.agilysys.qa.gridIron.constants.locators.rates.rateplans;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsRatePage {

	public static final By TAB_RATE_PLAN = By.xpath("//*[@data-qa-id='htzxNJ2']//following::a[text()='Rate Plans']");
	public static final By BUTTON_MORE = By.xpath("//button[text()='More']");
	public static final By BUTTON_NEW_RATE_PLAN = By.xpath("//a[text()='New Rate Plan']/..");
	public static final By HEADER_MODAL_RATE = By.xpath("//*[@data-qa-id='fuLFBt2']");
	public static final By LINK_BASE_RATE = By.xpath("//*[@data-qa-id='fqvwWXS']");
	public static final By MODAL_RATE_BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fuLFBt2']//a");

}
