package com.agilysys.qa.gridIron.constants.pagefactory.settings;

import static com.agilysys.qa.gridIron.constants.locators.settings.LocatorsSettingsPage.LINK_LOST_FOUND;
import static com.agilysys.qa.gridIron.constants.locators.settings.LocatorsSettingsPage.LINK_RESERVATION_BOOKING_NOTICE;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

public class PFSettingsPage {

	public static void clickLinkLostFound() {
		click(LINK_LOST_FOUND);
	}

	public static void clickLinkReservationBookingNotice() {
		click(LINK_RESERVATION_BOOKING_NOTICE);
	}
}