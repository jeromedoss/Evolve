package com.agilysys.qa.gridIron.builders.groups;

import static com.agilysys.qa.gridIron.builders.home.SearchGuestAndOpen.searchByName;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupsCommon.checkTabFoliosRoutingRules;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupsCommon.clickButtonCloseAlert;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupsCommon.clickButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage.clickButtonGroupAdd;
import static com.agilysys.qa.gridIron.pageobject.groups.POGroupBooking.stepCreateDefiniteGroup;
import static com.agilysys.qa.gridIron.pageobject.groups.POGroupBooking.stepCreateInquiryGroup;
import static com.agilysys.qa.gridIron.pageobject.groups.POGroupBooking.stepCreateProspectGroup;
import static com.agilysys.qa.gridIron.pageobject.groups.POGroupBooking.stepCreateTentativeGroup;
import static com.agilysys.qa.gridIron.pageobject.groups.POGroupRoomBlocksSection.stepCreateRoomBlocks;

import com.agilysys.pms.profile.model.Group;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */

public class GroupCreate {

	String guestName;
	String groupName;
	String groupCode;

	public GroupCreate(String guestName) {
		this.guestName = guestName;
		this.groupName = RandomHelper.getRandomAlphaString(5);
		this.groupCode = RandomHelper.getRandomAlphaString(5);
	}

	public GroupCreate(String guestName, String groupName, String groupCode) {
		this.guestName = guestName;
		this.groupName = groupName;
		this.groupCode = groupCode;
	}

	public GroupCreate(Group group) {
		this.guestName = group.getLinkedGuestProfileDetails().getLinkedProfiles().get(0).getFirstName();
		this.groupName = group.getGroupName();
		this.groupCode = group.getGroupCode();
	}

	public void createDefiniteGroup(String roomType) {

		searchGuestAndClickGroupButton();

		stepCreateDefiniteGroup(groupName, groupCode);
		stepCreateRoomBlocks(roomType);
		clickButtonSave();

		checkTabFoliosRoutingRules();
	}

	public void createTentativeGroup(String roomType) {

		searchGuestAndClickGroupButton();

		stepCreateTentativeGroup(groupName, groupCode);
		stepCreateRoomBlocks(roomType);
		clickButtonSave();
		clickButtonCloseAlert();
		clickButtonSave();
		checkTabFoliosRoutingRules();
	}

	public void createInquiryGroup() {

		searchGuestAndClickGroupButton();

		stepCreateInquiryGroup(groupName, groupCode);
		Selenide.sleep(2000);
		clickButtonSave();
		checkTabFoliosRoutingRules();
	}

	public void createProspectGroup() {

		searchGuestAndClickGroupButton();

		stepCreateProspectGroup(groupName, groupCode);
		Selenide.sleep(2000);
		clickButtonSave();
		checkTabFoliosRoutingRules();

	}

	private void searchGuestAndClickGroupButton() {

		searchByName(guestName);
		clickButtonGroupAdd();
	}
}
