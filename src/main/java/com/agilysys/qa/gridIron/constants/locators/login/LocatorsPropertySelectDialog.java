package com.agilysys.qa.gridIron.constants.locators.login;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - Aug/2018
 */
public class LocatorsPropertySelectDialog {

	public static final By CONTAINER_MODAL = By.xpath("//*[@name='propertySelectForm']/..");
	public static final By FORM_MODAL = By.xpath("//*[@name='propertySelectForm']");
	public static final By HEADER_MODAL = By.xpath("//*[@title='Property Select']");
	public static final By TYPEHEAD_PROPERTY_NAME = By.xpath("//input[@placeholder='Select a Property']");
	public static final By BUTTON_OK = By.xpath("//button[text()='ok']");

}
