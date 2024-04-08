package com.agilysys.qa.gridIron.constants.pagefactory.home.roomtile;

import static com.agilysys.qa.gridIron.constants.locators.home.RoomTile.BUTTON_COLLAPSE_OCCUPANCY;
import static com.agilysys.qa.gridIron.constants.locators.home.RoomTile.CHECKBUTTON_OCC;
import static com.agilysys.qa.gridIron.constants.locators.home.RoomTile.CHECKBUTTON_VAC;
import static com.agilysys.qa.gridIron.constants.locators.home.RoomTile.TAB_ROOMS;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

public class PFRoomTile {

	public static void clickTabRooms() {
		click(TAB_ROOMS);
	}

	public static void clickButtonCollapseOccupancy() {
		click(BUTTON_COLLAPSE_OCCUPANCY);
	}

	public static void clickCheckbuttonOCC() {
		click(CHECKBUTTON_OCC);
	}

	public static void clickCheckbuttonVAC() {
		click(CHECKBUTTON_VAC);
	}
}
