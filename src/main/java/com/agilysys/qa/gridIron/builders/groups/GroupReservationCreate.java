package com.agilysys.qa.gridIron.builders.groups;

import com.agilysys.qa.gridIron.builders.booking.CreateReservation;
import com.agilysys.qa.gridIron.builders.home.SearchGroupAndOpen;
import com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupStagingSection;
import com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupReservations;
import com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupStaging;
import com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupsCommon;
import com.agilysys.qa.gridIron.pageobject.groups.POGroupRoomBlocksSection;
import com.agilysys.qa.gridIron.pageobject.groups.POGroupStaging;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class GroupReservationCreate {

	static String lastName;
	static String firstName;
	static String phoneNumber;

	public GroupReservationCreate(String lastName, String firstName, String phoneNumber) {

		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;

	}

	public static void GroupCreateAutoReservation(String groupName, String roomType) {

		searchGroupAndOpen(groupName);

		PFGroupReservations.clickButtonAdd();
		POGroupStaging.stepStagingTable(lastName, firstName, phoneNumber);
		POGroupStaging.stepStagingTableSelectRoomType(roomType);
		PFGroupStaging.selectAutoCreateReservation();
		PFGroupStaging.clickButtonSave();
		PFGroupsCommon.checkTabFoliosRoutingRules();
	}

	public static void GroupCreateReservation(String groupName, String roomType) {

		searchGroupAndOpen(groupName);

		PFGroupReservations.clickButtonAdd();
		POGroupStaging.stepStagingTable(lastName, firstName, phoneNumber);
		POGroupStaging.stepStagingTableSelectRoomType(roomType);
		PFGroupStaging.clickButtonSave();
		PFGroupsCommon.checkTabFoliosRoutingRules();
		
		PFGroupReservations.clickStagingCheckbox();
		PFGroupReservations.clickButtonCreateReservation();
		PFGroupStaging.waitForModalProcessingToClose();
		PFGroupsCommon.checkTabFoliosRoutingRules();
		
		
	}
	
	public static void GroupMakeReservation(String groupName, String roomType) {
		
		searchGroupAndOpen(groupName);
		POGroupRoomBlocksSection.stepChangeRateInRoomBlocks();
		PFGroupReservations.clickButtonMakeReservation();
		
		new CreateReservation(lastName, firstName, phoneNumber).createReservationWithNewGuestWithCash();
	}


	private static void searchGroupAndOpen(String groupName) {

		new SearchGroupAndOpen(groupName).searchByName();
		PFGroupsCommon.clickTabReservations();
	}
}
