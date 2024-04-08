package com.agilysys.qa.gridIron.constants.locators.rates.packages;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsComponentsModal {

	public static final By BUTTON_ADD_COMPONENTS = By
			.xpath("//button[@data-qa-id='hKaNszm'  and text() = 'Add Fixed Components']");

	public static final By LIST_ITEMS = By.xpath("//*[@data-qa-id='fbRy3A7']/tr/td[@data-qa-id='fbRy3HL']/p");

	public static final By BUTTON_NEXT_1 = By.xpath("//button[@data-qa-id='hKaNtDT' and text() ='Next']");

	public static final By BUTTON_NEXT_2 = By.xpath("//button[@data-qa-id='hKaNtBu' and text() ='Next']");
	public static final By BUTTON_SAVE = By.xpath("//button[@data-qa-id='hKaNtCX' and text() ='Save']");

	public static final By LIST_DESCRIPTION = By.xpath("//td[@data-qa-id='fuLFBsm']");
	public static final By LIST_FREQUENCY = By.xpath("//*[@data-qa-id='gyX4ozp']");
	public static final By LIST_QUANTITY = By.xpath("//*[@data-qa-id='fuLFBsf']");
	public static final By LIST_AMOUNT = By.xpath("//*[@data-qa-id='fuLFBre']");

	public static final String PackageChargeRow = "//*[@data-qa-id='fbRy3A7']/tr[";
	public static final String PackageChargeAddButton = "]/td[@data-qa-id='fbRy3Fk']/a[@data-qa-id='fbRy3G6']";


	public static final By LINK_COMPONENTS = By.xpath("//*[@data-qa-id='fuLFBrA']");
}
