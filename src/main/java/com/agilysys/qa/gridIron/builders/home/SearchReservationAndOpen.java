package com.agilysys.qa.gridIron.builders.home;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickTabReservations;

import com.agilysys.qa.gridIron.pageobject.home.reservationtile.POReservationsPage;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;

/*
 * *Author - Harish Baskaran - 2018
 */
public class SearchReservationAndOpen {

	String lastName = null;
	String FirstName = null;
	String ConfNo = null;

	public SearchReservationAndOpen(String lastName, String FirstName, String ConfNo) {
		this.lastName = lastName;
		this.FirstName = FirstName;
		this.ConfNo = ConfNo;
	}

	public void searchByName() {

		clickTabReservations();
		new POSearchInMainPage().search(lastName);

		String expected = lastName + ", " + FirstName;

		new POReservationsPage().Search(expected);

	}

}