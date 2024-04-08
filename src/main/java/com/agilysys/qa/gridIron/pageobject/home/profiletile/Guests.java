package com.agilysys.qa.gridIron.pageobject.home.profiletile;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFProfileTile.clickRadioGuest;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFProfileTile.selectListGuests;

/*
 * *Author - Harish Baskaran - 2018
 */
public class Guests {

	public static void searchInProfileTab(String guestName) {

		clickRadioGuest();
		selectListGuests(guestName);
	}
}
