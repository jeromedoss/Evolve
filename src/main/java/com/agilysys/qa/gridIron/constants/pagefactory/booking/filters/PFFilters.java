package com.agilysys.qa.gridIron.constants.pagefactory.booking.filters;

import static com.agilysys.qa.gridIron.constants.locators.booking.filters.LocatorsFilters.BUTTON_COLLAPSE_ROOM_TYPES;
import static com.agilysys.qa.gridIron.constants.locators.booking.filters.LocatorsFilters.LIST_CHECKBOX_ROOM_TYPES;
import static com.agilysys.qa.gridIron.constants.locators.booking.filters.LocatorsRatesFilter.BUTTON_COLLAPSE_RATE_PLANS;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
public class PFFilters {

	public static void selectRoomTypeFilter(String roomType) {
		$(BUTTON_COLLAPSE_RATE_PLANS).scrollTo();
		click(BUTTON_COLLAPSE_ROOM_TYPES);
		$$(LIST_CHECKBOX_ROOM_TYPES).findBy(matchText(roomType)).click();
	}

}
