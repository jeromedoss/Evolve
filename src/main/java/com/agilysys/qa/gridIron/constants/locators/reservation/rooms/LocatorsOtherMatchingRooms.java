package com.agilysys.qa.gridIron.constants.locators.reservation.rooms;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsOtherMatchingRooms {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyQTz']");
	public static final By SELECT_ROOMS = By.xpath("//*[@data-qa-id='g9hSer9']");
	public static final By BUTTON_COLLAPSE = By.xpath("//*[@data-qa-id='fGUyQTz']//div[@class='panel-heading']//button");
	public static final By INPUT_SEARCH = By.xpath("//*[@data-qa-id='fGUyP84-val']");
	public static final By LIST_ROOM_ROWS = By.xpath("//*[@data-qa-id='fGUyQNv']");
	public static final By BUTTON_FILTER = By.xpath("//*[@data-qa-id='fGUyRkh']//button[@tabindex='801']");
	public static final By BUTTON_CLEAR_FILTER = By.xpath("//*[@data-qa-id='fGUyRkh']//button[@class='clear-all-btn']");
	
	public static By getOtherMatchingRoomRow(int rowNo){
		return By.xpath("//*[@data-qa-id='fGUyQNv']["+rowNo+"]//td[@data-qa-id='fGUyRbQ']");
	}
	
	public static By getOtherMatchingRoomNumber(int rowNo){
		return By.xpath("//*[@data-qa-id='fGUyQNv']["+rowNo+"]//a[@data-qa-id='g9hSedm']");
	}
	
}
