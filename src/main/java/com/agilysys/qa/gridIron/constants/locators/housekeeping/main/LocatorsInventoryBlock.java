package com.agilysys.qa.gridIron.constants.locators.housekeeping.main;

import org.openqa.selenium.By;

public class LocatorsInventoryBlock {
	
	public static By INPUT_ROOM_SEARCH = By.xpath("//input[@data-qa-id='fd9XZHP-val']");
	public static By BUTTON_NEW = By.xpath("//button[@data-qa-id='fd9XZJm']");
	public static By BUTTON_CLEAR = By.xpath("//button[@data-qa-id='fd9XZJN']");

	public static By DROPDOWN_INVENTORY_STATUS = By.xpath("//input[@data-qa-id='fd9XZJG-click']");
	public static By LIST_INVENTORY_STATUS = By.xpath("//a[@data-qa-id='fd9XZJG-text']");
	
	public static By INPUT_INVENTORY_BLOCK_START_DATE = By.xpath("//input[@data-qa-id='fd9XZK2-val']");
	public static By INPUT_INVENTORY_BLOCK_END_DATE = By.xpath("//input[@data-qa-id='fd9XZKT-val']");
	public static By INPUT_INVENTORY_BLOCK_COMMENTS = By.xpath("//textarea[@data-qa-id='fd9XZH7-val']");
	public static By BUTTON_SAVE_INVENTORY_BLOCK = By.xpath("//button[@data-qa-id='fd9XZEX']");
	public static By BUTTON_YES_CLEAR_INVENTORY_BLOCK = By.xpath("//button[@ng-click='yes()']");
	public static By BUTTON_FOOTER_SAVE = By.xpath("//button[@data-qa-id='fiRpmnm']");
	
	public static By getCheckBoxByRoomNumber(String roomNumber){
		return By.xpath("//a[@data-qa-id='fd9XZGg'][contains(text(),'"+roomNumber+"')]//ancestor::tr[1]//button[@data-qa-id='fd9XZKg-click']");
	}
	
	public static By getInventoryBlockLinkByRoomNumber(String roomNumber){
		return By.xpath("//a[@data-qa-id='fd9XZGg'][contains(text(),'"+roomNumber+"')]//ancestor::tr[1]//a[@data-qa-id='fd9XZHS']");
	}
}

