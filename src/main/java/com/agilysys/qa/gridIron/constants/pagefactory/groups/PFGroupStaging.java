package com.agilysys.qa.gridIron.constants.pagefactory.groups;

import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupStagingSection.BUTTON_SAVE_STAGING;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupStagingSection.CHECKBOX_AUTO_CREATE_RESERVATION;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupStagingSection.DROPDOWN_ROOM_TYPE;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupStagingSection.DROPDOWN_ROOM_TYPE_LIST;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupStagingSection.INPUT_FIRSTNAME;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupStagingSection.INPUT_LASTNAME;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupStagingSection.INPUT_PHONENUMBER;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupStagingSection.MODAL_BATCH_OPERATIONS;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFGroupStaging extends PageFactoryBase {

	public static void setInputLastname(String lastName) {
		$(INPUT_LASTNAME).should(Condition.visible, Duration.ofMillis(Configuration.timeout)).setValue(lastName);
		//$(INPUT_LASTNAME).setValue(lastName);
	}

	public static void setInputFirstname(String firstName) {
		$(INPUT_FIRSTNAME).setValue(firstName);
	}
	
	public static void setInputPhonenumber(String phoneNumber) {
		$(INPUT_PHONENUMBER).setValue(phoneNumber);
	}

	public static void setRoomType(String roomType) {
		click(DROPDOWN_ROOM_TYPE);
		$$(DROPDOWN_ROOM_TYPE_LIST).findBy(Condition.text(roomType)).click();
	}

	public static void clickButtonSave() {
		click(BUTTON_SAVE_STAGING);
	}

	public static void selectAutoCreateReservation() {
		click(CHECKBOX_AUTO_CREATE_RESERVATION);
	}
	
	public static void waitForModalProcessingToClose() {
		$(MODAL_BATCH_OPERATIONS).shouldBe(Condition.visible);
		$(MODAL_BATCH_OPERATIONS).should(Condition.disappear, Duration.ofMillis(Configuration.timeout));
		Selenide.sleep(3000);
	}
}
