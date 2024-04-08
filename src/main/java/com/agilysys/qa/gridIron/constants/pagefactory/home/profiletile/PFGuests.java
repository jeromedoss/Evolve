package com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile;

import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.LINK_CREATE_PROFILE;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.MODAL_BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.MODAL_RADIO_AGENT;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.MODAL_RADIO_COMPANY;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.MODAL_RADIO_GUEST;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.TAB_PROFILES;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFGuests extends PageFactoryBase{

	public static void clickTabProfiles() {
		click(TAB_PROFILES);
		Selenide.sleep(2000);
		waitForElementToLoad(LINK_CREATE_PROFILE, Configuration.timeout);
	}

	public static void clickLinkCreateProfile() {
		click(LINK_CREATE_PROFILE);
		Selenide.sleep(2000);
	}

	public static void clickModalRadioGuest() {
		click(MODAL_RADIO_GUEST);
	}

	public static void clickModalButtonSave() {
		click(MODAL_BUTTON_SAVE);
	}

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
		Selenide.sleep(2000);
	}

	public static void clickModalRadioCompany() {
		click(MODAL_RADIO_COMPANY);
	}

	public static void clickModalRadioAgent() {
		click(MODAL_RADIO_AGENT);
	}
}
