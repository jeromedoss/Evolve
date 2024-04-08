package com.agilysys.qa.gridIron.constants.locators.reservation.loyalty;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsLoyaltySection {

	public static final By LINK_MANAGE_LOYALTY_PROGRAM = By.xpath("//*[@data-qa-id='fGUyQNJ']");

	public static final By HEADER_POPUP_MODAL = By.xpath("//*[@data-qa-id='fGUyPX8']");
	public static final By LOYALTY_HEADER =By.xpath("//span[text()='Loyalty']");
	public static final By LINK_ADD_LOYALTY = By.xpath("//*[@data-qa-id='fGUyPhW']");
	public static final By BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyQJ4']");
	public static final By BUTTON_SAVE = By.xpath("//*[@data-qa-id='fGUyRoj']");
	public static final By BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyPX8']//div[@class='panel-heading']//a");

	public static final By BUTTON_LOYALTY_DEFAULT = By.xpath("//*[@data-qa-id='fH2BFcL']");
	public static final By DROPDOWN_LOYALTY_PROGRAMS = By.xpath("//*[@data-qa-id='fGUyPNC-click']");
	public static final By DROPDOWN_LIST_LOYALTY_PROGRAMS = By.xpath("//*[@data-qa-id='fGUyPNC-list']/li/a");
	public static final By DROPDOWN_LIST_LOYALTY_PROGRAM = By.xpath("//*[@data-qa-id='fGUyPNC-item']");
	public static final By INPUT_MEMBER_ID = By.xpath("//*[@data-qa-id='fGUyP1B-val']");
	public static final By CHECKBOX_ADD_TO_PROFILE = By.xpath("//*[@data-qa-id='fH2BFcV-click']");
	public static final By BUTTON_CLEAR_PROGRAM = By.xpath("//*[@data-qa-id='fGUyRpz']");
	public static final By BUTTON_PROFILE_DEFAULT = By.xpath("//*[@data-qa-id='fH2BFcz']");

	public static final By LIST_PROGRAM = By.xpath("//*[@data-qa-id='fGUyQ42']");
	public static final By LIST_MEMBER_ID = By.xpath("//*[@data-qa-id='fGUyRo1']");

}
