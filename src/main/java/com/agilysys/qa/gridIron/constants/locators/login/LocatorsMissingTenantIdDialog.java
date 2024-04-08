package com.agilysys.qa.gridIron.constants.locators.login;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - Aug/2018
 */
public class LocatorsMissingTenantIdDialog {

	public static final By CONTAINER_MODAL = By.xpath("//*[@data-qa-id='fH2BFQx']/..");

	public static final By FORM_MODAL = By.xpath("//*[@data-qa-id='fH2BFQx']");

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyNj8']");

	public static final By INPUT_TENANT_ID = By.xpath("//input[@data-qa-id='fGUyRH5-val']");

	public static final By BUTTON_SAVE = By.xpath("//button[@data-qa-id='fGUyRX9']");
	public static final By BUTTON_CANCEL = By.xpath("//a[@data-qa-id='fGUyPMX']");
	public static final By BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyNj8']//a");

}
