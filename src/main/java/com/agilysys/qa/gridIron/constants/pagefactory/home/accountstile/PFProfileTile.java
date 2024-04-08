package com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.qa.gridIron.constants.locators.home.ProfileTile;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;

public class PFProfileTile extends PageFactoryBase{

	public static void clickTabProfiles() {
		click(ProfileTile.TAB_PROFILES);
	}
	
	public static void checkTabProfilesVisibility() {		
		$(ProfileTile.TAB_PROFILES).shouldBe(Condition.visible);
	}

	public static void clickRadioGuest() {		
		click(ProfileTile.RADIO_GUEST);
	}
	
	public static void clickRadioCompany() {
		click(ProfileTile.RADIO_COMPANY);
	}
	
	public static void clickRadioAgent() {
		click(ProfileTile.RADIO_AGENT);
	}

	public static void selectListGuests(String guestName) {
		click($$(ProfileTile.LIST_GUESTS).findBy(Condition.text(guestName)));
	}
}