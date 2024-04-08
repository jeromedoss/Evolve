package com.agilysys.qa.gridIron.constants.locators.reservation.rooms;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsCurrentlySelectedRoom {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyQzw']");

	public static final By TEXT_CURRENTLY_SELECTED_ROOMTYPE_CODE = By.xpath("//*[@data-qa-id='fGUyPJv']");
	public static final By TEXT_CURRENTLY_SELECTED_ROOMTYPE_NAME = By.xpath("//*[@data-qa-id='fGUyQm9']");

	public static final By TEXT_CURRENTLY_SELECTED_ROOM = By.xpath("//*[@data-qa-id='fGUyQyV']");

	public static final By LINK_CHANGE_ROOM_TYPE = By.xpath("//*[@data-qa-id='mKWqQro']");
	public static final By DRODPOWN_ROOM_TYPE = By.xpath("//div[@data-qa-id='fGUyPnx']//button");
	public static final By DRODPOWN_OPTIONS_ROOM_TYPE = By.xpath("//a[@data-qa-id='fGUyRWk-text']");
	public static final By BUTTON_SEE_RATES = By.xpath("//button[@data-qa-id='fGUyPCm']");

	public static final By BUTTON_SELECT = By.xpath("//*[@data-qa-id='fGUyNus']");
	public static final By LINK_RELEASE_ROOM = By.xpath("//a[@data-qa-id='fGUyPdu']");
	public static final By BUTTON_CHANGE = By.xpath("//*[@data-qa-id='fGUyPfR']");
	public static final By BUTTON_CONFIRM = By.xpath("//*[@data-qa-id='fGUyNiF']");
	public static final By BUTTON_RELEASE = By.xpath("//*[@data-qa-id='fGUyQ5S']");
	public static final By LINK_CONNECTING_ROOMS_SELECT_ALL = By.xpath("//*[@data-qa-id='fGUyPBo']");
	public static final By CHECKBOX_DO_NOT_MOVE = By.xpath("//*[@data-qa-id='fGUyPrq-click']");
	public static final By BUTTON_DO_NOT_MOVE_CANCEL = By.xpath("//*[@data-qa-id='fGUyQKa']");
	public static final By BUTTON_DO_NOT_MOVE_SAVE = By.xpath("//*[@data-qa-id='kv41Eid']");
	public static final By TEXTAREA_DO_NOT_MOVE_REASON = By.xpath("//*[@data-qa-id='kv41Eiq-val']");
	public static final By EXCLAMATION_DO_NOT_MOVE_GUEST = By.xpath("//*[@data-qa-id='fGUyQQk']");

	public static final By getRoomTypeCheckBoxByRoomTypeName(String roomTypeName){
		return By.xpath("//div[@data-qa-id='fGUyPnx']//label[contains(text(),'"+roomTypeName+"')]");
	}

}
