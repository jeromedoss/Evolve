package com.agilysys.qa.gridIron.constants.locators.groups;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsRoomBlocksSection {

	public static final By STATUS_SELECT = By.xpath("//*[@data-qa-id='fGUyPCz']");
	public static final By ARRIVAL_DATE_PICKER = By.xpath("//*[@data-qa-id='fGUyRSf']");
	public static final By DEPARTURE_DATE_PICKER = By.xpath("//*[@data-qa-id='fGUyQ8H']");
	public static final By BOOKIND_ALLOWED_START_DATE_PICKER = By.xpath("//*[@data-qa-id='fGUyPcM']");
	public static final By BOOKIND_ALLOWED_END_DATE_PICKER = By.xpath("//*[@data-qa-id='fGUyRRT']");
	public static final By CUTTOFF_DATE_PICKER = By.xpath("//*[@data-qa-id='fGUyQYH']");
	public static final By CHECKBOX_AUTO_RELEASE = By.xpath("//*[@data-qa-id='fH2BFQj-click']");
	public static final By INPUT_WASH = By.xpath("//*[@data-qa-id='fGUyNtn-val']");
	public static final By INPUT_ADULT_INCLUDED = By.xpath("//*[@data-qa-id='fGUyQv8-val']");
	public static final By INPUT_CHILDREN_INCLUDED = By.xpath("//*[@data-qa-id='fGUyQUR-val']");
	public static final By INPUT_EXTRA_ADULT_CHARGE = By.xpath("//*[@data-qa-id='fGUyRXJ-val']");
	public static final By INPUT_EXTRA_CHILDREN_CHARGE = By.xpath("//*[@data-qa-id='fGUyQJQ-val']");

	public static final By TAB_SETUP = By.xpath("//*[@data-qa-id='fGUyPSC']/a");
	public static final By TABLE_SETUP = By.xpath("//*[@data-qa-id='fGUyPnm']");
	public static final By SELECT_ROOM_TYPE_SELECT = By.xpath("//input[@data-qa-id='iySyzSa-click']");
	public static final By ROOM_TYPE_SELECT = By.xpath("//*[@data-qa-id='fGUyPCD']//li[1]");

	public static final By DROPDOWN_ROOM_TYPE = By.xpath("//*[@data-qa-id='iySyzSa-list']");
	public static final By LIST_ROOM_TYPE = By.xpath("//a[@data-qa-id='iySyzSa-text']");

	public static final By SETUP_DATES_HEADER = By.xpath("//*[@data-qa-id='fGUyQyd']");
	public static final By GROSS_ROW = By.xpath("//*[@data-qa-id='fGUyNWF']");
	public static final By GROSS_ROW_INPUT = By.xpath("//*[@data-qa-id='iySyyzP']");
	public static final By NET_ROW = By.xpath("//*[@data-qa-id='fGUyP2Y']");
	public static final By NET_ROW_INPUT = By.xpath("//*[@data-qa-id='fGUyPwX']");
	public static final By ROOM_ROWD = By.xpath("//*[@data-qa-id='fGUyRMb']//*[@data-qa-id='fGUyQKt']");
	public static final By ROOM_ROW_HEADER = By.xpath("//*[@data-qa-id='fGUyP9j']");
	public static final By ROOM_ROW_RATE = By.xpath("//*[@data-qa-id='iySyzM8']");
	public static final By ROOM_ROW_BLOCKS = By.xpath("//*[@data-qa-id='iySyzS3']");
	public static final By ROOM_ROW_DELETE = By.xpath("//*[@data-qa-id='fGUyQks']");
	public static final By SAVE_CHANGES = By.xpath("//*[@data-qa-id='fGUyPgT']");

	public static final By STATUS_TAB = By.xpath("//*[@data-qa-id='fGUyQYt']/a");
	public static final By TOTAL_PICKUP = By.xpath("//*[@data-qa-id='hMTxT7d']");
	public static final By TOTAL_REMAIN = By.xpath("//*[@data-qa-id='hMTxT7w']");
	public static final String ROOM_STATUS = ("//*[@data-qa-id='hMTxT73' and text() ='");
	public static final String ROOM_PICKUP = ("']//following::span[@data-qa-id='hMTxT7K']");
	public static final String ROOM_REMAIN = ("']//following::span[@data-qa-id='hMTxT7T']");
	public static final By ROOM_BLOCK_ALERT = By.xpath("//a[@ng-show=\"showUsermenu\"]/ancestor::nav/following" +
		"-sibling::div[1]//button");

		public static By getStatusRoomRemain(String roomCode) {
			return By.xpath("//div[@data-qa-id='iySyzVV']");
	}

		public static By getStatusRoomPickup(String roomCode) {
			return By.xpath("//div[@data-qa-id='iySyzN3']");
		}

	/*public static By getStatusRoomRemain(String roomCode) {
		return By.xpath(ROOM_STATUS + roomCode + ROOM_REMAIN);
	}

	public static By getStatusRoomPickup(String roomCode) {
		return By.xpath(ROOM_STATUS + roomCode + ROOM_PICKUP);
	}*/
}
