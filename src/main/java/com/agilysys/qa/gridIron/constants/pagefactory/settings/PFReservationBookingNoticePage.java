
package com.agilysys.qa.gridIron.constants.pagefactory.settings;

import static com.agilysys.qa.gridIron.constants.locators.settings.reservationbookingnotice.LocatorsResevationBookingNoticePage.BUTTON_ADD;
import static com.agilysys.qa.gridIron.constants.locators.settings.reservationbookingnotice.LocatorsResevationBookingNoticePage.BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.settings.reservationbookingnotice.LocatorsResevationBookingNoticePage.BUTTON_TODAY;
import static com.agilysys.qa.gridIron.constants.locators.settings.reservationbookingnotice.LocatorsResevationBookingNoticePage.BUTTON_UPDATE;
import static com.agilysys.qa.gridIron.constants.locators.settings.reservationbookingnotice.LocatorsResevationBookingNoticePage.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.settings.reservationbookingnotice.LocatorsResevationBookingNoticePage.INPUT_DATE_START;
import static com.agilysys.qa.gridIron.constants.locators.settings.reservationbookingnotice.LocatorsResevationBookingNoticePage.LIST_LINK_MESSAGES;
import static com.agilysys.qa.gridIron.constants.locators.settings.reservationbookingnotice.LocatorsResevationBookingNoticePage.TEXTAREA_MESSAGE;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;

public class PFReservationBookingNoticePage {

	public static void clickButtonAdd() {
		click(BUTTON_ADD);
	}

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void setNoticeMessage(String noticeMessage) {
		$(TEXTAREA_MESSAGE).setValue(noticeMessage);
	}

	public static void clickStartDate() {
		click(INPUT_DATE_START);
	}

	public static void clickButtonToday() {
		click(BUTTON_TODAY);
	}

	public static void clickButtonSave() {
		click(BUTTON_SAVE);
	}

	public static void clickExistingNoticeMessage(String existingMessage) {
		$$(LIST_LINK_MESSAGES).findBy(Condition.exactText(existingMessage)).shouldBe(Condition.visible);
	}

	public static void clickButtonUpdate() {
		click(BUTTON_UPDATE);
	}
}
