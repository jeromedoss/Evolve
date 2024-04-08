package com.agilysys.qa.gridIron.validations;

import static com.codeborne.selenide.Selenide.$$;

//import com.agilysys.pms.property.model.ReservationBookingNotice;
import com.agilysys.qa.gridIron.constants.locators.settings.reservationbookingnotice.LocatorsResevationBookingNoticePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class ValidateReservationBookingNotice {

	private boolean NoticeMessage = false;
	private boolean StartDate = false;
	private boolean EndDate = false;
	private boolean ActiveState = false;
	private boolean InactiveState = false;

	public ValidateReservationBookingNotice VerifyAll() {

		NoticeMessage = true;
		StartDate = true;
		EndDate = true;

		return this;
	}

	public ValidateReservationBookingNotice VerifyRequiredFields() {
		NoticeMessage = true;
		ActiveState = true;
		StartDate = true;
		return this;
	}

	public ValidateReservationBookingNotice activateNoticeMessage() {
		NoticeMessage = true;
		return this;
	}

	public ValidateReservationBookingNotice activateStartDatee() {
		StartDate = true;
		return this;
	}

	public ValidateReservationBookingNotice activateEndDate() {
		EndDate = true;
		return this;
	}

	public ValidateReservationBookingNotice activateActiveState() {
		ActiveState = true;
		return this;
	}

	public ValidateReservationBookingNotice activateInactiveState() {
		InactiveState = true;
		return this;
	}

//	public void Validate(ReservationBookingNotice reservationBookingNotice) {
//
//		Selenide.refresh();
//
//		Selenide.$(LocatorsResevationBookingNoticePage.INPUT_SEARCH).setValue(reservationBookingNotice.getMessage());
//
//		if (NoticeMessage) {
//			$$(LocatorsResevationBookingNoticePage.LIST_LINK_MESSAGES)
//					.findBy(Condition.text(reservationBookingNotice.getMessage())).shouldBe(Condition.visible);
//		}
//
//		if (StartDate) {
//
//			$$(LocatorsResevationBookingNoticePage.LIST_DATE_START)
//					.findBy(Condition.text(reservationBookingNotice.getStartDate().toString()))
//					.shouldBe(Condition.visible);
//		}
//
//		if (EndDate) {
//			$$(LocatorsResevationBookingNoticePage.LIST_DATE_END)
//					.findBy(Condition.text(reservationBookingNotice.getEndDate().toString()))
//					.shouldBe(Condition.visible);
//		}
//
//		if (ActiveState) {
//			$$(LocatorsResevationBookingNoticePage.LIST_STATUS).findBy(Condition.text("Active"))
//					.shouldBe(Condition.visible);
//		}
//
//		if (InactiveState) {
//			$$(LocatorsResevationBookingNoticePage.LIST_STATUS).findBy(Condition.text("Inactive"))
//					.shouldBe(Condition.visible);
//		}
//	}

}
