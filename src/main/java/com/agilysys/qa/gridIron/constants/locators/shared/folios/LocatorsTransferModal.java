package com.agilysys.qa.gridIron.constants.locators.shared.folios;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsTransferModal {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyPaQ']");
	public static final By DROPDOWN_DESTINATION = By.xpath("//*[@data-qa-id='fGUyRfq-click']");

	public static final By DROPDOWN_VALUE_SEARCH_FOR_ACCOUNT = By.xpath("//*[@ng-if='$last']/..");
	//		.xpath("//*[@data-qa-id='fGUyRfq-text' and text() ='SEARCH FOR AN ACCOUNT']/..");
	public static final By INPUT_NAME = By.xpath("//*[@data-qa-id='fGUyQBu']");
	public static final By SELECT_MATCH_TRANSFER = By.xpath("//*[@data-qa-id='fGUyNe4']/..");
	public static final By TEXTAREA_REASON = By.xpath("//*[@data-qa-id='fGUyNqQ']//textarea");
	public static final By BUTTON_SAVE = By.xpath("//*[@data-qa-id='fGUyREt']");

	public static final By DATE_CHARGE = By.xpath("//*[@data-qa-id='fGUyPig']");

	public static final By AR_DROPDOWN_DESTINATION = By.xpath("//*[@data-qa-id='fGUyQBu']");
	public static final By AR_TEXTAREA_REASON = By.xpath("//*[@data-qa-id='fGUyQ95-val']");

	public static final By AR_BUTTON_SAVE = By.xpath("//*[@data-qa-id='fGUyNyw']");

}
