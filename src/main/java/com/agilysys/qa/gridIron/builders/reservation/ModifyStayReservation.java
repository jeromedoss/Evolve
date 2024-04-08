package com.agilysys.qa.gridIron.builders.reservation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

import java.time.Duration;

import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsCancelModal;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsModifyStay;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class ModifyStayReservation extends PageFactoryBase  {

	public void CheckinCheckReservation() {
		$(LocatorsModifyStay.BUTTON_CHECKIN).shouldBe(Condition.visible);
		click(LocatorsModifyStay.BUTTON_CHECKIN);

		click(LocatorsModifyStay.HEADER_CHECKIN_MODAL_CHECK);

		click(LocatorsModifyStay.BUTTON_CHECKIN_WITH_CHECK);

		if ($(LocatorsModifyStay.BUTTON_CONFIRM_ROOMNOTREADY).is(Condition.visible)) {
			click(LocatorsModifyStay.BUTTON_CONFIRM_ROOMNOTREADY);

		}
		$(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("INH");

		$(LocatorsModifyStay.BUTTON_UNDO_CHECKIN).shouldBe(Condition.visible, Duration.ofMillis(Configuration.timeout));

	}

	public void CheckinWithAuthReservation() {

		click(LocatorsModifyStay.BUTTON_CHECKIN);

		click(LocatorsModifyStay.HEADER_CHECKIN_MODAL_CHECK);

		click(LocatorsModifyStay.BUTTON_CHECKIN_WITH_AUTH_CC);

		$(LocatorsModifyStay.BUTTON_UNDO_CHECKIN).shouldBe(Condition.exist);

	}

	public void CheckinWithoutAuthReservation() {

		click(LocatorsModifyStay.BUTTON_CHECKIN);

		click(LocatorsModifyStay.HEADER_CHECKIN_MODAL_CHECK);

		click(LocatorsModifyStay.BUTTON_CHECKIN_WITHOUT_AUTH_CC);

		$(LocatorsModifyStay.BUTTON_UNDO_CHECKIN).shouldBe(Condition.exist);

	}

	public void EarlyCheckoutReservation() {

		click(LocatorsModifyStay.BUTTON_CHECKOUT);
		click(LocatorsModifyStay.EARLY_CHECKOUT_NEXT);
//		click(LocatorsModifyStay.MODAL_EARLY_CHECKOUT);
//		click(LocatorsModifyStay.BUTTON_CONFIRM_EARLY_CHECKOUT);
		$(LocatorsModifyStay.BUTTON_UNDO_CHECKOUT).should(Condition.exist, Duration.ofMillis(Configuration.timeout))
			.shouldBe(Condition.exist);

	}
	public void EarlyCheckoutWithBalanceReservation() {

		click(LocatorsModifyStay.BUTTON_CHECKOUT);

		click(LocatorsModifyStay.MODAL_EARLY_CHECKOUT);
		if ($(LocatorsModifyStay.CHECKOUT_USERNAME).is(Condition.visible)) {
			clearAndType(LocatorsModifyStay.CHECKOUT_USERNAME, Endpoints.getUserName());
			clearAndType(LocatorsModifyStay.CHECKOUT_PASSWORD, Endpoints.getPassword());
		}

		click(LocatorsModifyStay.BUTTON_CONFIRM_EARLY_CHECKOUT);
        sleep(2000);
        $(LocatorsModifyStay.BUTTON_UNDO_CHECKOUT).should(Condition.exist, Duration.ofMillis(Configuration.timeout))
              .shouldBe(Condition.exist);

	}

	public void UndoCancelReservation() {

		click(LocatorsModifyStay.BUTTON_UNDO_CANCEL);

		click(LocatorsModifyStay.MODAL_UNDO_CANCEL);

		click(LocatorsModifyStay.BUTTON_CONFIRM_UNDO_CANCEL);

		$(LocatorsModifyStay.BUTTON_CANCEL).should(Condition.exist,Duration.ofMillis(Configuration.timeout)).shouldBe(Condition.exist);

	}

	public void CancelReservation() {

		click(LocatorsModifyStay.BUTTON_CANCEL);

		click(LocatorsModifyStay.CANCEL_MODAL);

		click(LocatorsModifyStay.CANCEL_CHECK);

		$(LocatorsModifyStay.CANCEL_FEE_NIL).clear();

		$(LocatorsModifyStay.CANCEL_FEE_NIL).setValue("0");

		click(LocatorsModifyStay.BUTTON_CANCEL_OK);

		$(LocatorsModifyStay.BUTTON_UNDO_CANCEL).shouldBe(Condition.exist);

	}

	public void NoShowCancelReservation() {

		click($(LocatorsModifyStay.CANCEL_MODAL));

		Selenide.sleep(2000);

		click($(LocatorsModifyStay.CANCEL_CHECK));

		$(LocatorsModifyStay.CANCEL_FEE_NIL).clear();

		$(LocatorsModifyStay.CANCEL_FEE_NIL).setValue("0");

		click($(LocatorsModifyStay.BUTTON_CANCEL_OK));

		$(LocatorsModifyStay.BUTTON_UNDO_CANCEL).shouldBe(Condition.exist);

	}

	public void UndoCheckinReservation() {

		click(LocatorsModifyStay.BUTTON_UNDO_CHECKIN);

		click(LocatorsModifyStay.MODAL_UNDO_CHECKIN);

		click(LocatorsModifyStay.BUTTON_CONFIRM_UNDO_CHECKIN);

		$(LocatorsModifyStay.BUTTON_CHECKIN).shouldBe(Condition.exist);

	}

	public void UndoCheckoutReservation() {

		click(LocatorsModifyStay.BUTTON_UNDO_CHECKOUT);

		click(LocatorsModifyStay.MODAL_UNDO_CHECKOUT);

		click(LocatorsModifyStay.BUTTON_CONFIRM_UNDO_CHECKOUT);

		$(LocatorsModifyStay.BUTTON_CHECKOUT).shouldBe(Condition.exist);

	}

	//need to confirm
	public void cancelReservation()
	{
		click(LocatorsCancelModal.BUTTON_PAY_AND_CONFIRM_CANCELLATION);
		waitForElementToDisappear(LocatorsCancelModal.BUTTON_CONFIRM_CANCELLATION, Configuration.timeout);
	}

	// public void AssignUsedRoom(String Roomnumber) {
	//
	// $(LocatorsModifyStay.Roomnav());
	//
	// $(LocatorsModifyStay.Roompreferencemodal());
	//
	// Selenide.executeJavaScript("window.scrollBy(0,250);");
	//
	// $(LocatorsModifyStay.Roomchangebutton());
	//
	// $(LocatorsModifyStay.Roominput()).setValue(Roomnumber);
	//
	// $(LocatorsModifyStay.Roomsave());
	//
	// }

}
