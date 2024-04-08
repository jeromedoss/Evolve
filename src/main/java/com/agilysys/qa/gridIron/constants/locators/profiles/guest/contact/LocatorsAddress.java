package com.agilysys.qa.gridIron.constants.locators.profiles.guest.contact;

import org.openqa.selenium.By;

public class LocatorsAddress {
	public static final By SIDEPANE_CONTACT = By.xpath("(//div[@data-qa-id='fGUyNak']//span[contains(.,'Contact')])[1]");
	public static final By BUTTON_ADD_ADDRESS = By.xpath("//button[@data-qa-id='fGUyPWA']");
	public static final By DROPDOWN_ADDRESS_TYPE = By.xpath("//input[@data-qa-id='fGUyRGt-click']");
	public static final By LIST_ADDRESS_TYPE = By.xpath("//a[@data-qa-id='fGUyRGt-text']");
	public static final By LINK_ADD_ADDRESS_LINE= By.xpath("//div[@data-qa-id='fGUyQC2']/a");
	public static final By INPUT_ADDRESS_LINE1 = By.xpath("//input[@data-qa-id='fGUyP4j-val']");
	public static final By INPUT_ADDRESS_LINE2 = By.xpath("//input[@data-qa-id='fGUyQki-val']");
	public static final By INPUT_ADDRESS_LINE3 = By.xpath("//input[@data-qa-id='fGUyQJU-val']");
	public static final By INPUT_ADDRESS_LINE4 = By.xpath("//input[@data-qa-id='fGUyRMh-val']");
	public static final By INPUT_ADDRESS_LINE5 = By.xpath("//input[@data-qa-id='fGUyRdC-val']");
	public static final By INPUT_CITY= By.xpath("//input[@data-qa-id='fGUyPdQ-val']");
	public static final By INPUT_STATE = By.xpath("//input[@data-qa-id='fGUyPdh-val']");
	public static final By INPUT_POSTAL_CODE = By.xpath("//input[@data-qa-id='fGUyQLa-val']");
	public static final By INPUT_COUNTRY = By.xpath("//input[@data-qa-id='fGUyPmo-click'][@placeholder]");
	public static final By INPUT_COUNTY = By.xpath("//input[@data-qa-id='fGUyQ4L-val']");
	public static final By CHECKBOX_ADDRESS_KEEP_PRIVATE = By.xpath("//button[@data-qa-id='fGUyP8F-click']");
	public static final By RADIO_USE_THIS_ADDRESS = By.xpath("//button[@data-qa-id='fGUyRPL-click']");
	public static final By HEADER = By.xpath("//div[@data-qa-id='fGUyRFu']");
}
