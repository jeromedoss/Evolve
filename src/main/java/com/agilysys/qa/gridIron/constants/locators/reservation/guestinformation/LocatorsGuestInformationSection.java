package com.agilysys.qa.gridIron.constants.locators.reservation.guestinformation;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsGuestInformationSection {

	public static final By HEADER_MODAL = By.xpath("//div[@data-qa-id='fGUyR2s']");

	public static final By BUTTON_GUESTINFO_COLLAPSE = By
			.xpath("//div[@data-qa-id='jXmBbdT']//following-sibling::button");
	public static final By BUTTON_GUESTINFO_EDIT = By.xpath("//div[@data-qa-id='fGUyR2s']//button[text()='Edit']");

	public static final By LINK_ASSOCIATE_TO_DIFF_PROFILE = By.xpath("//a[@data-qa-id='fGUyPRt']");

	public static final By DROPDOWN_ADDRESS_TYPE = By.xpath("//input[@data-qa-id='fGUyRGt-click']");
	public static final By DROPDOWN_LIST_ADDRESS_TYPE = By.xpath("//a[@data-qa-id='fGUyRGt-text']");
	
	public static final By INPUT_STREET = By.xpath("//input[@data-qa-id='fGUyP4j-val']");

	public static final By LINK_ADD_ADDRESS_LINE = By.xpath("//a[@data-qa-id='fGUyPGP']");
	public static final By INPUT_ADD_ADDRESS_LINE_2 = By.xpath("//input[@data-qa-id='fGUyQki-val']");
	public static final By INPUT_ADD_ADDRESS_LINE_3 = By.xpath("//input[@data-qa-id='fGUyQJU-val']");
	public static final By INPUT_ADD_ADDRESS_LINE_4 = By.xpath("//input[@data-qa-id='fGUyRMh-val']");
	public static final By INPUT_ADD_ADDRESS_LINE_5 = By.xpath("//input[@data-qa-id='fGUyRdC-val']");

	public static final By INPUT_CITY = By.xpath("//input[@data-qa-id='fGUyPdQ-val']");

	public static final By INPUT_STATE = By.xpath("//input[@data-qa-id='fGUyPdh-val']");
	public static final By INPUT_ZIP = By.xpath("//input[@data-qa-id='fGUyQLa-val']");
	public static final By INPUT_COUNTRY = By.xpath("//input[@data-qa-id='fGUyPmo-click'][@placeholder]");
	public static final By INPUT_COUNTY = By.xpath("//input[@data-qa-id='fGUyQ4L-val']");
	public static final By BUTTON_ADDRESS_ADD = By.xpath("//button[@data-qa-id='fGUyPWA']");
	public static final By BUTTON_ADDRESS_DELETE = By.xpath("//*[@data-qa-id='fGUyQef']");
	public static final By RADIO_ADDRESS_DEFAULT = By.xpath("//*[@data-qa-id='fGUyRPL-click']");

	public static final By LINK_GO_TO_FULL_PROFILE = By.xpath("//*[@data-qa-id='fGUyQZj']");

	public static final By INPUT_MAIN_PHONE = By.xpath("//*[@data-qa-id='fGUyPWq-val']");
	public static final By INPUT_MAIN_EMAIL = By.xpath("//*[@data-qa-id='fGUyQ8e-val']");

	public static final By BUTTON_PHONE_ADD = By.xpath("//button[@data-qa-id='fGUyREC']");
	public static final By DROPDOWN_PHONE_TYPE = By.xpath("//input[@data-qa-id='fGUyNkv-click']");
	public static final By DROPDOWN_LIST_PHONE_TYPE = By.xpath("//a[@data-qa-id='fGUyNkv-text']");
	public static final By INPUT_PHONE_NUMBER = By.xpath("//input[@data-qa-id='fGUyP7m-val']");
	public static final By INPUT_EXTENSION = By.xpath("//input[@data-qa-id='fGUyP4a-val']");
	public static final By RADIO_PHONE_DEFAULT = By.xpath("//button[@data-qa-id='fGUyPdg-click']");
	public static final By BUTTON_PHONE_DELETE = By.xpath("//button[@data-qa-id='fGUyPap']");

	public static final By BUTTON_EMAIL_ADD = By.xpath("//button[@data-qa-id='fGUyPiR']");
	public static final By DROPDOWN_EMAIL_TYPE = By.xpath("//input[@data-qa-id='fGUyRLK-click']");
	public static final By DROPDOWN_LIST_EMAIL_TYPE = By.xpath("//a[@data-qa-id='fGUyRLK-text']");	
	public static final By INPUT_EMAIL = By.xpath("//input[@data-qa-id='fGUyQot-val']");
	public static final By RADIO_EMAIL_DEFAULT = By.xpath("//button[@data-qa-id='fGUyPmj-click']");
	public static final By BUTTON_EMAIL_DELETE = By.xpath("//button[@data-qa-id='fGUyQeY']");

}
