package com.agilysys.qa.gridIron.constants.locators.login;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - Aug/2018
 */
public class LocatorsSignInDialog {

	public static final By CONTAINER_MODAL = By.xpath("//*[@title='Sign In']/..");
	public static final By HEADER_MODAL = By.xpath("//*[@title='Sign In']");
	public static final By INPUT_USERNAME = By.xpath("//div/input[@name='userid']");
	public static final By LOGIN = By.xpath("//form/div/div/button[@Class='ng-scope']");
	public static final By INPUT_PASSWORD = By.xpath("//input[@name='password']");
	public static final By BUTTON_SIGN_IN = By.xpath("//form/div/div/button");

}
