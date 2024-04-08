package com.agilysys.qa.gridIron.constants.locators.login;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - Aug/2018
 */
public class LocatorsChangePasswordDialog {

	public static final By CONTAINER_MODAL = By.xpath("//*[@title='Change Password']/..");

	public static final By HEADER_MODAL = By.xpath("//*[@title='Change Password']");

	public static final By INPUT_CURRENT_PASSWORD = By.xpath("//*[@title='Current Password']//following::input");
	public static final By INPUT_NEW_PASSWORD = By.xpath("//*[@title='New Password']//following::input");
	public static final By INPUT_RETYPE_PASSWORD = By.xpath("//*[@title='Retype New Password']//following::input");

	public static final By BUTTON_SUBMIT = By.xpath("//button[text()='Submit']");
	public static final By BUTTON_CANCEL = By.xpath("//a[text()='Cancel']");
	public static final By BUTTON_CLOSE = By.xpath("//*[@data-ng-click='closeModal()']");

}
