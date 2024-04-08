package com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile;

import static com.agilysys.qa.gridIron.constants.locators.home.ReservationTile.LINK_CREATE_RESERVATION;
import static com.agilysys.qa.gridIron.constants.locators.home.ReservationTile.LIST_RESERVATION_GUEST;
import static com.agilysys.qa.gridIron.constants.locators.home.ReservationTile.TAB_RESERVATIONS;
import static com.agilysys.qa.gridIron.constants.locators.home.SearchTile.INPUT_SEARCH;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.qa.gridIron.constants.locators.home.ReservationTile;
import com.codeborne.selenide.Condition;

public class PFReservationTile {

	public static void selectListReservationsGuest(String expected) {
		$$(LIST_RESERVATION_GUEST).findBy(Condition.text(expected)).click();
	}

	public static void clickTabReservations() {
		click(TAB_RESERVATIONS);
	}

	public static void clickLinkCreateReservation() {
		click(LINK_CREATE_RESERVATION);
	}
	
	public static void clickReservation(String searchString){
		$(INPUT_SEARCH).scrollIntoView(true);
		click($$(ReservationTile.LIST_RESERVATION_GUEST).findBy(Condition.text(searchString)));//.shouldBe(Condition.exist).click();
	}
}