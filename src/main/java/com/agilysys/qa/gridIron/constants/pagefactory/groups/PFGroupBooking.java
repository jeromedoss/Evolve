package com.agilysys.qa.gridIron.constants.pagefactory.groups;

import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupBookingSection.*;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

public class PFGroupBooking extends PageFactoryBase {

	public static void setInputGroupName(String groupName) {
		$(GROUP_NAME_INPUT).should(Condition.visible, Duration.ofMillis(Configuration.timeout)).setValue(groupName);
	}

	public static void setInputGroupCode(String groupCode) {
		$(GROUP_CODE_INPUT).setValue(groupCode);
	}

	public static void clickSelectPostRoomRevenueAs() {
		click(POST_ROOM_REVENUE_AS_SELECT);
	}

	public static void clickNightlyRoomCharge() {
		click(NIGHTLY_ROOM_CHARGE);
	}

	public static void clickSelectStatus() {
		click(STATUS_SELECT);
	}

	public static void clickDefiniteStatus() {
		click(DEFINITE);
	}

	public static void clickTentativeStatus() {
		click(TENTATIVE);
	}

	public static void clickProspectStatus() {
		click(PROSPECT);
	}

	public static void clickInquiryStatus() {
		click(INQUIRY);
	}

	public static void clickSelectCancellationPolicy() {
		$(CANCELLATION_POLICY_SELECT).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		click(CANCELLATION_POLICY_SELECT);
	}

	public static void clickCancellationPolicy() {
		click(CANCELLATION_POLICY);
	}

	public static void clickRadioGroupPayment() {
		$(GROUP_DESC_TEXT).scrollIntoView(false);
		click(GROUP_PAYMENT_RADIO);
	}

	public static void clickRadioGroupBook() {
		click(GROUP_BOOK_RADIO);
	}
}