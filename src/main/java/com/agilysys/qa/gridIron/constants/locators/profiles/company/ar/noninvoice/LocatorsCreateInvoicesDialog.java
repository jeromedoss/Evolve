package com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsCreateInvoicesDialog {

	public static final By MODAL_BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyPn8']");
	public static final By MODAL_BUTTON_SAVE = By.xpath("//*[@data-qa-id='fGUyQkW']");
	public static final By MODAL_BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyR6Y']//a");
	public static final By MODAL_HEADER = By.xpath("//*[@data-qa-id='fGUyR6Y']");
	public static final By DROPDOWN_TERM = By.xpath("//*[@data-qa-id='fGUyNw2-click']");
	public static final By DROPDOWN_TERM_30 = By.xpath("//*[@data-qa-id='fGUyNw2-text' and text()='30 days']");
	public static final By DROPDOWN_TERM_60 = By.xpath("//*[@data-qa-id='fGUyNw2-text' and text()='60 days']");
	public static final By DROPDOWN_TERM_90 = By.xpath("//*[@data-qa-id='fGUyNw2-text' and text()='90 days']");
	public static final By DROPDOWN_TERM_120 = By.xpath("//*[@data-qa-id='fGUyNw2-text' and text()='120 days']");

}
