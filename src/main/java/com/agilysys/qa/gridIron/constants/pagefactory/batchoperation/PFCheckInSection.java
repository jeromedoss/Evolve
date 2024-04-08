package com.agilysys.qa.gridIron.constants.pagefactory.batchoperation;

import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.BUTTON_ASSIGN_ROOMS;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.BUTTON_AUTO_ASSIGN;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.BUTTON_CHECKIN;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.BUTTON_CLICK_YES;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.BUTTON_SAVE_AND_CLOSE;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.CHECKBOX_SELECT_ALL;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.CHECKBOX_SHOW_CHECKED_IN;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.LIST_COLUMN_CONFORMATION;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.MODAL_CHECKIN_PROCESSING;
import static com.agilysys.qa.gridIron.constants.locators.batchoperation.checkin.LocatorsBatchCheckinSection.TAB_CHECKIN;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
public class PFCheckInSection {

	public static void clickTabCheckin() {
		click(TAB_CHECKIN);
		Selenide.sleep(2000);
		$(LIST_COLUMN_CONFORMATION).shouldNotBe(empty);
	}

	public static void clickButtonAssignRooms() {
		click(BUTTON_ASSIGN_ROOMS);
		Selenide.sleep(2000);
		$(BUTTON_AUTO_ASSIGN).should(appear).shouldBe(enabled);
	}

	public static void clickButtonAutoAssign() {
		click(BUTTON_AUTO_ASSIGN);
		Selenide.sleep(2000);
		$(BUTTON_SAVE_AND_CLOSE).should(appear).shouldBe(enabled);
	}

	public static void clickButtonSaveAndClose() {
		click(BUTTON_SAVE_AND_CLOSE);
		Selenide.sleep(2000);
		$$(LIST_COLUMN_CONFORMATION).last().shouldNotBe(empty);
		click(BUTTON_CLICK_YES);
	}

	public static void clickCheckboxSelectAll() {
		Selenide.sleep(4000);
		click(CHECKBOX_SELECT_ALL);
		Selenide.sleep(2000);
	}

	public static void clickButtonCheckin() {
		click(BUTTON_CHECKIN);
		Selenide.sleep(2000);
	}

	public static void waitForModalCheckinProcessingToClose() {
		$(MODAL_CHECKIN_PROCESSING).shouldBe(Condition.visible);
		$(MODAL_CHECKIN_PROCESSING).should(Condition.disappear, Duration.ofMillis(Configuration.timeout));
		Selenide.sleep(3000);
		click(CHECKBOX_SHOW_CHECKED_IN);
	}
}