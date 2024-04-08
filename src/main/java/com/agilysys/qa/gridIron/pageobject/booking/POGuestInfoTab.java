package com.agilysys.qa.gridIron.pageobject.booking;


import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFGuestInfoTab.*;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POGuestInfoTab {

	public static void stepSelectNewGuest(String LastName, String FirstName, String PhoneNo) {

		clickInputGuestProfile();
		clickAddNewGuestProfile();
		typeLastName(LastName);
		typeFirstname(FirstName);
		expandContactSection();
		typePhoneNumber(PhoneNo);

	}

	public static void stepSelectExistingGuest(String GuestName) {

		typeGuestProfileSearch(GuestName);
		selectGuestProfile();

	}
}
