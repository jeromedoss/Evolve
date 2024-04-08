package com.agilysys.qa.gridIron.constants.locators.reservation.payments;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsAddPaymentModal {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyQqC']");
	public static final By BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyQqC']//a");

	public static final By CHECKBOX_THIRD_PARTY = By.xpath("//*[@data-qa-id='fGUyQdw-click']");
	public static final By CHECKBOX_DO_NOT_DISCLOSE = By.xpath("//*[@data-qa-id='fGUyNpc-click']");
	public static final By CHECKBOX_RESERVATION_DEFAULT = By.xpath("//*[@data-qa-id='fGUyPoA-click']");
	public static final By CHECKBOX_ADD_TO_DEFAULT_FOLIO = By.xpath("//*[@data-qa-id='fGUyR4L-click']");
	public static final By CHECKBOX_PROFILE_DEFAULT = By.xpath("//*[@data-qa-id='fGUyR9L-click']");
	public static final By CHECKBOX_DEFAULT_PAYMENT = By.xpath("//*[@data-qa-id='fGUyRUD-click']");
	public static final By DROPDOWN_PAYMENT = By.xpath("//*[@data-qa-id='fGUyRoy-click']");
	public static final By LIST_PAYMENT_METHODS = By.xpath("//a[@data-qa-id='fGUyRoy-text']");
	public static final By BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyQi7']");
	public static final By BUTTON_SAVE = By.xpath("//*[@data-qa-id='fGUyPpV']");
	public static final By INPUT_SEARCH_ACCOUNTS = By.xpath("//*[@data-qa-id='fGUyQBu']");
	public static final By LIST_SEARCH_MATCH = By.xpath("//*[@data-qa-id='fGUyNe4']/..");
}
