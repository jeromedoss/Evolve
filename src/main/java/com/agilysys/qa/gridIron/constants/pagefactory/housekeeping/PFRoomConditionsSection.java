package com.agilysys.qa.gridIron.constants.pagefactory.housekeeping;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsRoomConditionsSection;
import com.codeborne.selenide.Condition;

public class PFRoomConditionsSection {

	public static void clickHeaderModal() {
		click(LocatorsRoomConditionsSection.HEADER_MODAL);
	}

	public static void setInputRoomSearch(String roomNumber) {
		$(LocatorsRoomConditionsSection.ROOM_SEARCH).setValue(roomNumber);
	}

	public static void clickLinkRoomNumber() {
		click(LocatorsRoomConditionsSection.LINK_ROOM);
	}
}
