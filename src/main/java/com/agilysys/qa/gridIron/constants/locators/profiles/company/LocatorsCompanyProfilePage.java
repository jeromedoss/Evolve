package com.agilysys.qa.gridIron.constants.locators.profiles.company;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsCompanyProfilePage {

	// SUMMARY DETAILS FIELD
	public static final By INPUT_NAME = By.xpath("//input[@data-qa-id='fGUyPpu-val']");
	public static final By INPUT_CODE = By.xpath("//input[@data-qa-id='fGUyPoM-val']");
	public static final By INPUT_PRONOUNCED = By.xpath("//input[@data-qa-id='fGUyPMM-val']");
	public static final By INPUT_WEBSITE = By.xpath("//input[@data-qa-id='fGUyQY3-val']");
	public static final By DROPDOWN_RATE_PLAN = By.xpath("//input[@data-qa-id='fGUyQcN-click']");
	public static final By DROPDOWN_LIST_RATE_PLAN = By.xpath("//a[@data-qa-id='fGUyQcN-text']");

	// MARKETING FIELD
	public static final By DROPDOWN_GUEST_TYPE = By.xpath("//input[@data-qa-id='fGUyPJw-click']");
	public static final By DROPDOWN_LIST_GUEST_TYPE = By.xpath("//a[@data-qa-id='fGUyPJw-text']");
	// this needs THE CHECK FOR ANCHOR TAG

	public static final By DROPDOWN_SOURCE_OF_BUSINESS = By.xpath("//input[@data-qa-id='fGUyPm7-click']");
	public static final By DROPDOWN_LIST_SOURCE_OF_BUSINESS = By.xpath("//a[@data-qa-id='fGUyPm7-text']");
	// this needs THE CHECK FOR ANCHOR TAG

	public static final By DROPDOWN_MARKET_SEGMENT = By.xpath("//input[@data-qa-id='fGUyQGV-click']");
	public static final By DROPDOWN_LIST_MARKET_SEGMENT = By.xpath("//a[@data-qa-id='fGUyQGV-text']");
	// this needs THE CHECK FOR ANCHOR TAG

	// ADDRESS FIELD
	public static final By BUTTON_ADD = By.xpath("//button[@data-qa-id='fGUyPWA']");
	public static final By DROPDOWN_ADRESSS_TYPE = By.xpath("//input[@data-qa-id='fGUyRGt-click']");
	public static final By DROPDOWN_LIST_ADDRESS_TYPES = By.xpath("//a[@data-qa-id='fGUyRGt-text']");
	// this needs THE CHECK FOR ANCHOR TAG

	public static final By INPUT_STREET = By.xpath("//input[@data-qa-id='fGUyP4j-val']");
	public static final By LINK_ADD_ADDRESS_LINE = By.xpath("//a[@data-qa-id='fGUyPGP']");
	public static final By INPUT_ADDRESS_LINE_2 = By.xpath("//input[@data-qa-id='fGUyQki-val']");
	public static final By INPUT_ADDRESS_LINE_3 = By.xpath("//input[@data-qa-id='fGUyQJU-val']");
	public static final By INPUT_ADDRESS_LINE_4 = By.xpath("//input[@data-qa-id='fGUyRMh-val']");
	public static final By INPUT_ADDRESS_LINE_5 = By.xpath("//input[@data-qa-id='fGUyRdC-val']");
	public static final By INPUT_CITY = By.xpath("//input[@data-qa-id='fGUyPdQ-val']");
	public static final By INPUT_STATE = By.xpath("//input[@data-qa-id='fGUyPdh-val']");
	public static final By INPUT_ZIP = By.xpath("//input[@data-qa-id='fGUyQLa-val']");
	public static final By TYPEHEAD_COUNTRY = By.xpath("//input[@data-qa-id='fGUyPmo-click'][2]");
	public static final By INPUT_COUNTY = By.xpath("//input[@data-qa-id='fGUyQ4L-val']");
	public static final By BUTTON_KEEP_PRIVATE = By.xpath("//button[@data-qa-id='fGUyP8F-click']");
	public static final By LIST_BUTTON_DEFAULT = By.xpath("//button[@data-qa-id='fGUyRPL-click']");
	
	// PHONE NUMBERS
	public static final By DROPDOWN_PHONE_TYPE = By.xpath("//input[@data-qa-id='fGUyNkv-click']");
	public static final By DROPDOWN_LIST_PHONE_TYPE = By.xpath("//a[@data-qa-id='fGUyNkv-text']");
	// this needs THE CHECK FOR ANCHOR TAG
	
	public static final By INPUT_NUMBER = By.xpath("//input[@data-qa-id='fGUyP7m-val']");
	public static final By INPUT_EXTENSION = By.xpath("//input[@data-qa-id='fGUyP4a-val']");
	public static final By CHECK_KEEP_PRIVATE_PHONE = By.xpath("//button[@data-qa-id='fGUyPaq-click']");
	
	// EMAIL ADDRESSES
	public static final By DROPDOWN_EMAIL_TYPE = By.xpath("//input[@data-qa-id='fGUyRLK-click']");
	public static final By DROPDOWN_LIST_EMAIL_TYPE = By.xpath("//a[@data-qa-id='fGUyRLK-text']");
	// this needs THE CHECK FOR ANCHOR TAG
	
	public static final By INPUT_EMAIL_ADDRESS = By.xpath("//input[@data-qa-id='fGUyQot-val']");
	public static final By CHECK_KEEP_PRIVATE_EMAIL = By.xpath("//button[@data-qa-id='fGUyRKm-click']");
	
	
	// SIDE PANEL BUTTONS
	public static final By NAV_SUMMARY = By.xpath("//div[@data-qa-id='fGUyP22']//li[2]");
	public static final By NAV_MARKETING = By.xpath("//div[@data-qa-id='fGUyP22']//li[3]");
	public static final By NAV_CONTACT = By.xpath("//div[@data-qa-id='fGUyP22']//li[4]");
	public static final By NAV_CONTACTS = By.xpath("//div[@data-qa-id='fGUyP22']//li[5]");
	public static final By NAV_RESVS = By.xpath("//div[@data-qa-id='fGUyP22']//li[6]");
	public static final By NAV_GROUP = By.xpath("//div[@data-qa-id='fGUyP22']//li[7]");
	public static final By NAV_HISTORY = By.xpath("//div[@data-qa-id='fGUyP22']//li[8]");

	// BUTTONS
	public static final By BUTTON_SAVE = By.xpath("//*[@data-qa-id='fGUyNao']");
	public static final By BUTTON_SAVE_EXIT = By.xpath("//*[@data-qa-id='fGUyR5o']");
	public static final By BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyNxQ']");

	public static final By TAB_PROFILE = By.xpath("//li[@data-qa-id='fGUyNnt']");
	public static final By TAB_AR = By.xpath("//li[@data-qa-id='fGUyPt9']");



	public static final By CONTACTS_ADD= By.xpath("//li[@data-qa-id='fGUyQif']/button[@data-qa-id='fGUyNzK']");
	public static final By CREATE_NEWPROFILE= By.xpath("//span[@data-qa-id='j8cQTqQ']/parent::a[@data-qa-id='hYMcL6C']");

	public static final By LAST_NAME= By.xpath("//input[@data-qa-id='kjo66Q4-val']");
	public static final By FIRST_NAME= By.xpath("//input[@data-qa-id='kjo66Q7-val']");
	public static final By CONTACT = By.xpath("//span[@data-qa-id='fGUyPeP']");
	public static final By PHONE = By.xpath("//input[@data-qa-id='fGUyP4b-val']");
	public static final By SAVE_ALL=By.xpath("//button[@data-qa-id='fGUyRXv']");
}
