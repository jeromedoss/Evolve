package com.agilysys.qa.gridIron.constants.locators.home;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class RoomTile {

	// MAIN ROOMS HEAD TAB
	public static final By TAB_ROOMS = By.xpath("//div[@data-qa-id='fGUyRUS']//li[@ui-sref='.rooms']");

	// LIST OF ROOM NUMBERS
	public static final By LIST_ROOM_NUMBERS = By
			.xpath("//div[@data-qa-id='fGUyPL3']//li[@data-qa-id='fGUyNWz']/div//h3");

	// OCCUPANCY CHECK BOX
	public static final By BUTTON_COLLAPSE_OCCUPANCY = By.xpath("//*[@data-qa-id='fGUyNik-text']");
	public static final By CHECKBUTTON_OCC = By
			.xpath("//*[@data-qa-id='fGUyNik-item' and @title='OCC - Occupied']//button");
	public static final By CHECKBUTTON_VAC = By
			.xpath("//*[@data-qa-id='fGUyNik-item' and @title='VAC - Vacant']//button");

}
