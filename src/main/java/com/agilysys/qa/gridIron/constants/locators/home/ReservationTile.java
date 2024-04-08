package com.agilysys.qa.gridIron.constants.locators.home;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class ReservationTile {

	// MAIN RESERVATIONS HEAD TAB
	public static final By TAB_RESERVATIONS = By.xpath("//div[@data-qa-id='fGUyRUS']//li[@ui-sref='.reservations']");

	// BOOK RESERVATION LINK
	public static final By LINK_CREATE_RESERVATION = By.xpath("//a[@data-qa-id='fGUyPeC']");

	// LIST OF RESERVATIONS GUEST NAMES
	public static final By LIST_RESERVATION_GUEST = By.xpath("//li[@data-qa-id='fGUyNWx']/a/div/div/h3");

	// ROOM NUMBER INPUT FIELD
	public static final By INPUT_ROOM_NUMBER = By.xpath("//input[@data-qa-id='fNFx1ii-val']");

	// RESERVATION STATUS FILTER
	public static final By BUTTON_COLLAPSE_RESERVATION_STATUS = By.xpath("//h3[@data-qa-id='fGUyRhn-text']");

	public static final By CHECKBOX_ARRIVING = By
			.xpath("//*[@data-uid='agPms.main.search.results.agCheckbox.arriving']//button");

	public static final By CHECKBOX_CANCELLED = By
			.xpath("//*[@data-uid='agPms.main.search.results.agCheckbox.cancelled']//button");

	public static final By CHECKBOX_DEPARTED = By
			.xpath("//*[@data-uid='agPms.main.search.results.agCheckbox.departed']//button");

	public static final By CHECKBOX_DEPARTING = By
			.xpath("//*[@data-uid='agPms.main.search.results.agCheckbox.departing']//button");

	public static final By CHECKBOX_INHOUSE = By
			.xpath("//*[@data-uid='agPms.main.search.results.agCheckbox.inHouse']//button");

	public static final By CHECKBOX_NOSHOW = By
			.xpath("//*[@data-uid='agPms.main.search.results.agCheckbox.noShow']//button");

	public static final By CHECKBOX_RESERVED = By
			.xpath("//*[@data-uid='agPms.main.search.results.agCheckbox.reserved']//button");

	// ROOM TYPE FILTER
	public static final By BUTTON_COLLAPSE_ROOM_TYPE = By.xpath("//h3[@data-qa-id='fGUyNX9-text']");

	// get the room types list

	// get their check boxes

	// VIP STATUS FILTER
	public static final By BUTTON_COLLAPES_VIP = By.xpath("//h3[@data-qa-id='fGUyPfG-text']");

	public static final By CHECKBOX_VIP_NONE = By
			.xpath("//*[@data-uid='agPms.main.search.results.agCheckbox.none']//button");

}
