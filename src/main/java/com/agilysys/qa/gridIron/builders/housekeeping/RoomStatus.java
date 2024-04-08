package com.agilysys.qa.gridIron.builders.housekeeping;

import static com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFRoomConditionsSection.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFRoomConditionsSection.clickLinkRoomNumber;
import static com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFRoomConditionsSection.setInputRoomSearch;

/*
 * *Author - Harish Baskaran - 2018
 */
public class RoomStatus {

	private String roomNumber = null;

	public RoomStatus(String roomNumber) {
		this.roomNumber = roomNumber;

	}

	public void flowCheckStatus() {

		clickHeaderModal();
		setInputRoomSearch(roomNumber);
		clickHeaderModal();
		clickLinkRoomNumber();

	}
}
