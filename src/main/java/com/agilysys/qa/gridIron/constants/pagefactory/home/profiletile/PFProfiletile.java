package com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile;

import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.RADIO_AGENT;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.TAB_PROFILES;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

public class PFProfiletile {

	public static void clickTabProfiles() {
		click(TAB_PROFILES);
	}

	public static void clickRadioAgent() {
		click(RADIO_AGENT);
	}
}
