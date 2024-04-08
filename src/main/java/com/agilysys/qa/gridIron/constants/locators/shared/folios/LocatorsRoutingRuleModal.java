package com.agilysys.qa.gridIron.constants.locators.shared.folios;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsRoutingRuleModal {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='hoB1ua1-click']");//hoB1ua1-click//fGUyQBu
	public static final By BUTTON_CLOSE = By.xpath("//*[@data-qa-id='fGUyPQh']");

	public static final By DATE_DESCRIPTION = By.xpath("//*[@data-qa-id='fUc9uhL']");
	public static final By DATE_START = By.xpath("//*[@data-qa-id='fGUyPHf']");
	public static final By DATE_END = By.xpath("//*[@data-qa-id='fGUyPcu']");

	public static final By BUTTON_ANY_CATEGORY = By.xpath("//*[@data-qa-id='ikEC7eE-item-click']");
	//"//*[@data-uid='agPms.main.reservation.addAReservationRule.agCheckbox.anyCategory']//button[@data-qa-id='fGUyNkW-item-click']");

	public static final By EXPAND_DESTINATION = By.xpath("//*[@data-qa-id='ikEC7dW']");
	public static final By TYPEHEAD_DESTINATION = By.xpath("//input[2][@data-qa-id='ikEC7aJ-click']");
	// Search for an account
	public static final By INPUT_DESTINATION_ACCOUNT_NAME = By.xpath("//*[@data-qa-id='fGUyQBu']");
	public static final By SELECT_DESTINATION = By.xpath("//*[@data-qa-id='fGUyNe4']/..");

	public static final By TEXTAREA_REASON = By.xpath("//*[@data-qa-id='fGUyQNg-val']");

	public static final By BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyPC2']");
	public static final By BUTTON_SAVE = By.xpath("//*[@data-qa-id='ikEC7Zt']");
	public static final By FOLIO_DROPDOWN = By.xpath("//*[@data-qa-id='ikEC7g6-click']");
	public static final By APPROPRIATE_FOLIO = By.xpath("//*[@data-qa-id= 'ikEC7g6-text']");
	public static final By EXPAND_CHARGES = By.xpath("//span[contains(text(),'Step 2')]");

}
