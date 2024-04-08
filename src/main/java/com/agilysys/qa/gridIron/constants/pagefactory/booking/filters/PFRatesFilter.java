package com.agilysys.qa.gridIron.constants.pagefactory.booking.filters;

import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage.BUTTON_CHECK_RATES;
import static com.agilysys.qa.gridIron.constants.locators.booking.filters.LocatorsRatesFilter.BUTTON_COLLAPSE_PACKAGES;
import static com.agilysys.qa.gridIron.constants.locators.booking.filters.LocatorsRatesFilter.BUTTON_COLLAPSE_RATE_PLANS;
import static com.agilysys.qa.gridIron.constants.locators.booking.filters.LocatorsRatesFilter.LIST_CHECKBOX_PACKAGES;
import static com.agilysys.qa.gridIron.constants.locators.booking.filters.LocatorsRatesFilter.LIST_CHECKBOX_RATE_PLANS;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
public class PFRatesFilter {

	public static void selectRatePlanFilter(String ratePlan) {
		$(BUTTON_CHECK_RATES).scrollTo();
		click(BUTTON_COLLAPSE_RATE_PLANS);
		$$(LIST_CHECKBOX_RATE_PLANS).findBy(Condition.text(ratePlan));
	}
	
	public static void selectPackageFilter(String packageName) {
		$(BUTTON_CHECK_RATES).scrollTo();
		click(BUTTON_COLLAPSE_PACKAGES);
		$$(LIST_CHECKBOX_PACKAGES).findBy(Condition.text(packageName));
	}
}
