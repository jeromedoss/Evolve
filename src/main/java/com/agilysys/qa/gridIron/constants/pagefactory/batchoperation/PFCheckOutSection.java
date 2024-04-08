package com.agilysys.qa.gridIron.constants.pagefactory.batchoperation;

import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkout.LocatorsBatchCheckOutSection.BUTTON_RUN;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkout.LocatorsBatchCheckOutSection.CHECKBOX_SELECT_ALL_RESERVATIONS;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkout.LocatorsBatchCheckOutSection.CHECKBOX_SHOW_CHECKOUTS_WITH_AVAILABLE_PAYMENT;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkout.LocatorsBatchCheckOutSection.CHECKBOX_SHOW_REMAINING_CHECKOUTS;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkout.LocatorsBatchCheckOutSection.DROPDOWN_VALUE_FORCE_CHECKOUT;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkout.LocatorsBatchCheckOutSection.MODAL_CHECKOUT_PROCESSING;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkout.LocatorsBatchCheckOutSection.TAB_CHECKOUT;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
public class PFCheckOutSection {

	public static void clickTabCheckout() {
		click(TAB_CHECKOUT);
		Selenide.sleep(2000);
	}

	public static void clickCheckboxShowRemainingCheckouts() {
		click(CHECKBOX_SHOW_REMAINING_CHECKOUTS);
		Selenide.sleep(2000);
	}

	public static void clickCheckboxShowCheckoutsWithAvailablePayment() {
		click(CHECKBOX_SHOW_CHECKOUTS_WITH_AVAILABLE_PAYMENT);
		Selenide.sleep(2000);
	}

	public static void clickCheckboxSelectAllReservations() {
		Selenide.sleep(4000);
		click(CHECKBOX_SELECT_ALL_RESERVATIONS);
		Selenide.sleep(2000);
	}

	public static void clickButtonRun() {
		click(BUTTON_RUN);
		Selenide.sleep(2000);
	}

	public static void ClickDropdownValueForceCheckout() {
		click(DROPDOWN_VALUE_FORCE_CHECKOUT);
		Selenide.sleep(2000);
	}

	public static void waitForModalCheckoutProcessingToClose() {
		$(MODAL_CHECKOUT_PROCESSING).shouldBe(Condition.visible);
		$(MODAL_CHECKOUT_PROCESSING).should(Condition.disappear, Duration.ofMillis(Configuration.timeout));
		Selenide.sleep(3000);
	}
}
