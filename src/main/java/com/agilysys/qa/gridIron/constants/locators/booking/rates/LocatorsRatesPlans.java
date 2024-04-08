package com.agilysys.qa.gridIron.constants.locators.booking.rates;

import org.openqa.selenium.By;

public class LocatorsRatesPlans {

	public static final By SELECT_RATE = By
			.xpath("//*[@data-qa-id='fGUyPSK']//table[@data-qa-id='fGUyQ2S']/tbody/tr/td[2]");

	public static final By RATE_FIRST_DAY_FIRST_ROW = By.xpath("//*[@data-qa-id='fGUyPt2'][1]/td[3]");
	public static final By RATE_SECOND_DAY_SECOND_ROW = By.xpath("//*[@data-qa-id='fGUyPt2'][2]/td[4]");

	public static final By BUTTON_COLLAPSE_RATE = By.xpath("//*[@data-qa-id='fGUyPSK']//a");

}
