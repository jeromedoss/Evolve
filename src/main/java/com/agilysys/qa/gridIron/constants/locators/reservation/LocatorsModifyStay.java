package com.agilysys.qa.gridIron.constants.locators.reservation;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsModifyStay {

	public static final By RoomRow = By.xpath("//*[@data-qa-id='fGUyQNv'][1]");
	// public static final By ReleaseRoomLink =
	// By.xpath("//*[@data-qa-id='fGUyPdu']");
	// public static final By RoomSelectButton =
	// By.xpath("//*[@data-qa-id='fGUyNus']");
	// public static final By RoomInput =
	// By.xpath("//*[@data-qa-id='fGUyPxv-val']");
	// public static final By RoomSave = By.xpath("//*[@data-qa-id='fGUyPr1']");
	// public static final By RoomNumber =
	// By.xpath("//*[@data-qa-id='g9hSedm']");
	public static final By RoomNav = By
			.xpath("//div[@data-qa-id='fGUyNoY']//following::span[@class='ng-scope nav-room-wrapper']");

	public static final By MODAL_ROOM_PREFERENCE = By.xpath("//*[@data-qa-id='gSxQtXU']");
	public static final By MESSAGE_ERROR = By
			.xpath("//*[@data-qa-id='fGUyPab']//div[@class='alert-msg ng-binding ng-scope']");

	public static final By BUTTON_ROOM_CHANGE = By.xpath("//*[@data-qa-id='fGUyPfR']");

	public static final By BUTTON_CHECKIN = By.xpath("//*[@data-qa-id='fGUyQH9']");

	public static final By BUTTON_CONFIRM_ROOMNOTREADY = By.xpath("//button[@ng-click='yes()']");
	public static final By BUTTON_CHECKIN_WITH_CHECK = By.xpath("//*[@data-qa-id='fGUyRAu']");
	public static final By BUTTON_CHECKIN_WITHOUT_AUTH_CC = By.xpath("//*[@data-qa-id='fGUyQ54']");
	public static final By BUTTON_CHECKIN_WITH_AUTH_CC = By.xpath("//*[@data-qa-id='fGUyRAu']");
	public static final By HEADER_CHECKIN_MODAL_CHECK = By.xpath("//*[@data-qa-id='fGUyPZo']");

	public static final By BUTTON_CANCEL = By.xpath("//*[@data-qa-id='fGUyRdt']");
	public static final By INPUT_CANCEL_PASSWORD = By.xpath("//*[@data-qa-id='1WwKV1']");
	public static final By BUTTON_CANCEL_OK = By.xpath("//*[@data-qa-id='1WwKV1']"); // s8
	public static final By CANCEL_CHECK = By.xpath("//*[@data-qa-id='fGUyRWL-click']");
	public static final By CANCEL_MODAL = By.xpath("//*[@data-qa-id='1WwKWg']");
	public static final By CANCEL_FEE_NIL = By.xpath("//*[@data-qa-id='fGUyR3i-val']");
	public static final By CANCEL_PASSWORD = By.xpath("//*[@data-qa-id='1WwKV1']");

	public static final By CANCEL_USERNAME = By.xpath("//*[@data-qa-id='fGUyPsS-val']");
	public static final By PAY_CANCEL_CONFIRM= By.xpath("//*[@data-qa-id='1WwKWZ']");

	public static final By BUTTON_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyRe8']");
	public static final By BUTTON_CONFIRM_EARLY_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyRUq']");
	public static final By EARLY_CHECKOUT_NEXT = By.xpath("//*[@data-qa-id='fGUyRUq']");
	public static final By MODAL_EARLY_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyPCw']");
	public static final By CHECKOUT_USERNAME = By.xpath("//*[@data-qa-id='fGUyRSh-val']");
	public static final By CHECKOUT_PASSWORD = By.xpath("//*[@data-qa-id='fGUyPqU-val']");


	public static final By MODAL_UNDO_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyQ5C']");
	public static final By BUTTON_CONFIRM_UNDO_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyQtu']");
	public static final By BUTTON_UNDO_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyQXB']");

	public static final By MODAL_UNDO_CHECKIN = By.xpath("//*[@data-qa-id='fGUyPNv']");
	public static final By BUTTON_UNDO_CHECKIN = By.xpath("//*[@data-qa-id='fGUyPyW']");
	public static final By BUTTON_CONFIRM_UNDO_CHECKIN = By.xpath("//*[@data-qa-id='fGUyQAH']");

	public static final By MODAL_UNDO_CANCEL = By.xpath("//*[@data-qa-id='fJm7mw1']");
	public static final By BUTTON_UNDO_CANCEL = By.xpath("//*[@data-qa-id='fGUyRGw']");
	public static final By BUTTON_CONFIRM_UNDO_CANCEL = By.xpath("//*[@data-qa-id='fJm7mnM']");

	public static final By BUTTON_CHILD_LINK = By.xpath("//*[@data-qa-id='fGUyNhr']");

	public static final By DATE_ARRIVAL = By.xpath("//*[@data-qa-id='fGUyPfx']/div");
	public static final By DATE_DEPARTURE = By.xpath("//*[@data-qa-id='fGUyQ1i']/div");

	// *[@data-qa-id='fGUyQ9i']/td[3] - noshow
	// *[@data-qa-id='fGUyPFe']//td[1] - names list
	public static final By ARRIVAL_ERROR_NOS = By.xpath("//*[@data-qa-id='fGUyRAD']");
	public static final By ARRIVAL_ERROR_LIST = By
			.xpath("//*[@data-qa-id='fGUyPFe']//td[2]/div[@data-qa-id='fGUyPXC']/a");
	public static String SELECT_ARRIVAL_RES = ("//*[@data-qa-id='fGUyPFe'][");
	public static String ARRIVAL_NO_SHOW = ("]//td[3]/a");

	public static final By LABEL_RESERVATION_STATUS = By.xpath("//*[@id='reservation-title']/div[@class='badges']");

}
