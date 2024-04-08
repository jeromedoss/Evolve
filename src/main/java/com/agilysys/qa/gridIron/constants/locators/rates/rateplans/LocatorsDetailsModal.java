package com.agilysys.qa.gridIron.constants.locators.rates.rateplans;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsDetailsModal {

	public static final By INPUT_NAME = By.xpath("//*[@data-qa-id='fqvwWW2-val']");

	public static final By DROPDOWN_CALENDAR = By.xpath("//*[@data-qa-id='fqvwW4x-click']");
	public static final By DROPDOWN_VALUE_CALENDAR_BAR = By.xpath("//*[@data-qa-id='fqvwW4x-text' and text() ='BAR']");

	public static final By DROPDOWN_CATEGORY = By.xpath("//*[@data-qa-id='fqvwWYk-click']");
	public static final By DROPDOWN_VALUE_CATEGORY_TRANSIENT = By
			.xpath("//*[@data-qa-id='fqvwWYk-text' and text()='Transient']");
	public static final By DROPDOWN_VALUE_CATEGORY_PACKAGE = By
			.xpath("//*[@data-qa-id='fqvwWYk-text' and text()='Package']");

	public static final By INPUT_CODE = By.xpath("//*[@data-qa-id='fqvwWWe-val']");

	public static final By DROPDOWN_POSTAS = By.xpath("//*[@data-qa-id='fqvwWXf-click']");
	public static final By DROPDOWN_VALUE_NIGHTLY_ROOM_CHARGE = By
			.xpath("//*[@data-qa-id='fqvwWXf-text' and text()='Nightly Room Charge']");

	public static final By INPUT_ADULT = By.xpath("//*[@data-qa-id='fqvwWWv-val']");
	public static final By INPUT_CHILD = By.xpath("//*[@data-qa-id='fqvwWWv-val']");

	public static final By BUTTON_NEXT = By.xpath("//button[@data-qa-id='fuLFBqk' and text() = 'Next']");

}
