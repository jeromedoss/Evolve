package com.agilysys.qa.gridIron.builders.home;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFProfileTile.clickTabProfiles;
import static com.agilysys.qa.gridIron.pageobject.home.profiletile.Guests.searchInProfileTab;

import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;

/*
 * *Author - Harish Baskaran - 2018
 */
public class SearchGuestAndOpen {

	public static void searchByName(String guestName) {

		clickTabProfiles();
		new POSearchInMainPage().search(guestName);
		searchInProfileTab(guestName);

	}
}
