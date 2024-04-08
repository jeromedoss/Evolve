package com.agilysys.qa.gridIron.constants.locators.batchoperation.checkout;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsBatchCheckOutSection {

	public static final By TAB_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyRdp'][4]/a");

	public static final By INPUT_SEARCH = By.xpath("//*[@data-qa-id='fGUyNkg-val']");
	public static final By CHECKBOX_SELECT_ALL_RESERVATIONS = By
			.xpath("//*[@data-qa-id='fQuntTr']//button[@data-qa-id='fQswbPK-click']");

	public static final By CHECKBOX_SHOW_REMAINING_CHECKOUTS = By
			.xpath("//*[@data-qa-id='fGUyQUc']//button[@data-qa-id='fH2BFSs-click']");

	public static final By CHECKBOX_SHOW_CHECKOUTS_WITH_AVAILABLE_PAYMENT = By
			.xpath("//*[@data-qa-id='fGUyRbq']//button[@data-qa-id='fH2BFbg-click']");

	public static final By BUTTON_RUN = By.xpath("//*[@data-qa-id='fGUyP6N']//button[text()='Run']");
	public static final By DROPDOWN_VALUE_SETTLE_ONLY = By.xpath("//*[@data-qa-id='fGUyP6N']//li[1]");
	public static final By DROPDOWN_VALUE_SETTLE_AND_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyP6N']//li[2]");
	public static final By DROPDOWN_VALUE_FORCE_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyP6N']//li[3]");

	public static final By LIST_COLUMN_RESERVATION = By.xpath("//*[@data-qa-id='fGUyRRS']");
	public static final By LIST_COLUMN_STATUS = By.xpath("//*[@data-qa-id='fGUyRRS']/td[10]/p");

	public static final By MODAL_CHECKOUT_PROCESSING = By.xpath("//*[@data-qa-id='fGUyR1L']");

}
