package com.agilysys.qa.gridIron.constants.locators.home;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class ProfileTile {

	// PROFILE TAB
	public static final By TAB_PROFILES = By.xpath("//div[@data-qa-id='fGUyRUS']//li[@ui-sref='.profiles']");

	// PROFILE NAME LIST
	public static final By LIST_GUESTS = By.xpath("//*[@data-qa-id='fJkA9X4']//h3");

	public static final By LIST_COMPANIES = By.xpath("//*[@data-qa-id='fJkA9uw']/a");

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyPx6']");

	// PROFILE CREATE LINK
	public static final By LINK_CREATE_PROFILE = By.xpath("//div['@data-qa-id=fGUyQ14']//a[@data-qa-id='fGUyPDk']");

	// PROFILE RADIO BUTTONS
	public static final By RADIO_COMPANY = By.xpath("//*[@data-qa-id='fJkA9v8' and @name='Company']//button");
	public static final By RADIO_GUEST = By.xpath("//*[@data-qa-id='fJkA9v8' and @name='Guest']//button");
	public static final By RADIO_AGENT = By.xpath("//*[@data-qa-id='fJkA9v8' and @name='Travel Agent']//button");

	// PROFILE MODAL - BUTTONS
	public static final By MODAL_RADIO_COMPANY = By.xpath("//button[@data-qa-id='fH2BFSe-click']");
	public static final By MODAL_RADIO_GUEST = By.xpath("//button[@data-qa-id='fH2BFbc-click']");
	public static final By MODAL_RADIO_AGENT = By.xpath("//button[@data-qa-id='fH2BFcj-click']");
	public static final By MODAL_BUTTON_SAVE = By.xpath("//button[@data-qa-id='fGUyP1L']");
	public static final By MODAL_BUTTON_CANCEL = By.xpath("//li[@data-qa-id='fGUyNqF']");
	public static final By MODAL_BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyPx6']//a[@class='icon-close ng-scope']");

}
