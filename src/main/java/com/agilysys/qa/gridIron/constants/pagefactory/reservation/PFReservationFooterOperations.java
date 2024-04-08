package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond;

import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsCancelModal;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsCheckinModal;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsFooterButtonsSection;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsModifyStay;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

public class PFReservationFooterOperations extends PageFactoryBase{

	public void clickCancelReservation(){
		if ($(LocatorsModifyStay.INPUT_CANCEL_PASSWORD).is(Condition.visible)) {

			clearAndType(LocatorsModifyStay.INPUT_CANCEL_PASSWORD, Endpoints.getPassword());

		}
		click(LocatorsFooterButtonsSection.BUTTON_CANCEL_RESERVATION);
		waitForElementToLoad(LocatorsCancelModal.TEXTAREA_REASON,Configuration.timeout);
		if ($(LocatorsModifyStay.INPUT_CANCEL_PASSWORD).is(Condition.visible)) {

			clearAndType(LocatorsModifyStay.INPUT_CANCEL_PASSWORD, Endpoints.getPassword());

		}
		$(LocatorsCancelModal.TEXTAREA_REASON).shouldBe(Condition.visible);
	}
	
	public void clickUndoCancelReservation(){
		click(LocatorsFooterButtonsSection.BUTTON_UNDO_CANCEL);		
		$(LocatorsCancelModal.BUTTON_CONFIRM_UNDO_CANCEL).shouldBe(Condition.visible);
	}
	
	public void clickCheckIn(){
		click(LocatorsFooterButtonsSection.BUTTON_CHECKIN);		
		$(LocatorsCheckinModal.LABEL_WAIT_FOR_INPUT).shouldBe(Condition.visible);
	}
	
	public void clickUndoCheckIn(){
		click(LocatorsFooterButtonsSection.BUTTON_UNDO_CHECKIN);
	}
}
