package com.agilysys.qa.gridIron.constants.locators.groups;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsGroupBookingSection {
	
	public static final By GROUP_NAME_INPUT = By.xpath("//*[@data-qa-id='fGUyQVZ-val']");
	public static final By GROUP_CODE_INPUT = By.xpath("//*[@data-qa-id='fGUyPNT-val']");

	public static final By ARRIVAL_DATE_PICKER_BUTTON = By.xpath("//*[@data-qa-id='fGUyQDE-click']");
	public static final By ARRIVAL_DATE_PICKER = By.xpath("//*[@data-qa-id='fGUyQDE']");
	public static final By ARRIVAL_DATE_INPUT = By.xpath("//*[@data-qa-id='fGUyQDE-val']");
	public static final By DEPARTUTRE_DATE_PICKER_BUTTON = By.xpath("//*[@data-qa-id='fGUyRia-click']");
	public static final By DEPARTURE_DATE_PICKER = By.xpath("//*[@data-qa-id='fGUyRia']");
	public static final By DEPARTURE_DATE_INPUT = By.xpath("//*[@data-qa-id='fGUyRia-val']");

	public static final By MANAGER_SELECT = By.xpath("//*[@data-qa-id='fGUyRLH-click'][2]");

	public static final By POST_ROOM_REVENUE_AS_SELECT = By.xpath("//*[@data-qa-id='fGUyPU3']");
	public static final By NIGHTLY_ROOM_CHARGE = By
			.xpath("//*[@data-qa-id='fGUyPU3-text' and text()='Nightly Room Charge']//..");
	
	public static final By STATUS_SELECT = By.xpath("//*[@data-qa-id='fH2BFQf-click']");
	public static final By INQUIRY = By.xpath("//*[@data-qa-id='fH2BFQf-text' and text()='Inquiry']//..");
	public static final By PROSPECT = By.xpath("//*[@data-qa-id='fH2BFQf-text' and text()='Prospect']//..");
	public static final By TENTATIVE = By.xpath("//*[@data-qa-id='fH2BFQf-text' and text()='Tentative']//..");
	public static final By DEFINITE = By.xpath("//*[@data-qa-id='fH2BFQf-text' and text()='Definite']//..");
	

	public static final By DEPOSIT_POLICY_SELECT = By.xpath("//*[@data-qa-id='fGUyRMN']");
	
	public static final By CANCELLATION_POLICY_SELECT = By.xpath("//*[@data-qa-id='fGUyQhi']");
	public static final By CANCELLATION_POLICY = By.xpath("//*[@data-qa-id='fGUyRYk-text']//..");

	public static final By GROUP_DESCRIPTION_INPUT = By.xpath("//*[@data-qa-id='fGUyPyY-val']");

	public static final By SUPPRESS_RATES_CHECKBOX = By.xpath("//*[@data-qa-id='fGUyP32-click']");

	public static final By TRANSIENT_CHECKBOX = By.xpath("//*[@data-qa-id='fGUyPuQ-click']");

	public static final By WHOLESALER_CHECKBOX = By.xpath("//*[@data-qa-id='fGUyRVP-click']");

	public static final By GROUP_PAYMENT_RADIO = By.xpath("//*[@data-qa-id='h5zwwyZ-click']");

	public static final By GROUP_DESC_TEXT = By.xpath("//*[@data-qa-id='fGUyPyY-val']");

	public static final By SELF_PAYMENT_RADIO = By.xpath("//*[@data-qa-id='h5zwwyd-click']");

	public static final By GROUP_BOOK_RADIO = By.xpath("//*[@data-qa-id='h5zwwym-click']");

	public static final By SELF_BOOK_RADIO = By.xpath("//*[@data-qa-id='h5zwwyo-click']");
	public static final By TEXT_ROLLING_RELEASE = By.xpath("//*[contains(text(), 'Rolling Release')]");

}
