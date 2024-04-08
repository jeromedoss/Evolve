package com.agilysys.qa.gridIron.pageobject.groups;

import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupStaging.setInputFirstname;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupStaging.setInputLastname;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupStaging.setInputPhonenumber;

import com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupStaging;

public class POGroupStaging {

	public static void stepStagingTable(String lastName, String firstName, String phoneNumber) {
		setInputLastname(lastName);
		setInputFirstname(firstName);
		setInputPhonenumber(phoneNumber);
	}
	
	public static void stepStagingTableSelectRoomType(String roomType) {
		PFGroupStaging.setRoomType(roomType);
	}
}
