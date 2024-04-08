package com.agilysys.qa.gridIron.pageobject.home.reservationtile;

import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.TAB_SUMMARY;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.selectListReservationsGuest;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POReservationsPage {

	public void Search(String expected) {

		selectListReservationsGuest(expected);

		$(TAB_SUMMARY).shouldBe(Condition.visible);

	}
}
