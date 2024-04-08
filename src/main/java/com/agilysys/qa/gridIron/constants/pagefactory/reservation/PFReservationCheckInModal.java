package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsCheckinModal;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsFooterButtonsSection;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Configuration;

public class PFReservationCheckInModal extends PageFactoryBase{
	
	public void clickCheckInWithAuth(){
		click(LocatorsCheckinModal.BUTTON_CHECK_IN_WITH_AUTH);
		waitForElementToDisappear(LocatorsCheckinModal.BUTTON_CHECK_IN, Configuration.timeout);
	}
	
	public void clickCheckInWithoutAuth(){
		click(LocatorsCheckinModal.BUTTON_CHECK_IN_WITHOUT_AUTH);
		waitForElementToDisappear(LocatorsCheckinModal.BUTTON_CHECK_IN, Configuration.timeout);
	}
	
	public void confirmUndoCheckIn(){
		click(LocatorsCheckinModal.BUTTON_CONFIRM_AND_RETAIN_ROOM);
		waitForElementToDisappear(LocatorsCheckinModal.BUTTON_CONFIRM_AND_RETAIN_ROOM, Configuration.timeout);
	}
}
