package com.agilysys.qa.gridIron.constants.locators.rates.packages;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsPackagePage {

	public static final By TAB_PACKAGE = By.xpath("//*[@data-qa-id='htzxNJ2']//following::a[text()='Packages']");
	public static final By BUTTON_MORE = By
			.xpath("//*[@data-uid='agPms.main.rates.agActionbarDropdown.more.1']/button[text()='More']");
	public static final By BUTTON_NEW_PACKAGE = By.xpath("//*[text()='New Package']/..");

}
