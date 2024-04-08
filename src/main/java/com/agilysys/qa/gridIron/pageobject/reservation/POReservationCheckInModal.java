package com.agilysys.qa.gridIron.pageobject.reservation;

import org.openqa.selenium.Keys;

import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsCheckinModal;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationCheckInModal;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationFooterOperations;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class POReservationCheckInModal {
	
	PFReservationCheckInModal pfReservationCheckInModal = new PFReservationCheckInModal();
	PFReservationFooterOperations pfReservationFooterOperations = new PFReservationFooterOperations();
	
	public void checkInWithAuthorization(){
		pfReservationFooterOperations.clickCheckIn();
		pfReservationCheckInModal.clickCheckInWithAuth();
	}
	
	public void checkInWithoutAuthorization(){
		pfReservationFooterOperations.clickCheckIn();
		pfReservationCheckInModal.clickCheckInWithoutAuth();
	}
	
	public void checkInWithNewCreditCard(String swipeData){
		pfReservationFooterOperations.clickCheckIn();
		Selenide.sleep(3000);
		Selenide.actions().sendKeys(Keys.NUMPAD0, swipeData, Keys.ENTER).build().perform();
		PageFactoryBase.waitForElementToDisappear(LocatorsCheckinModal.BUTTON_CHECK_IN, Configuration.timeout);
	}
	
	public void performUndoCheckIn(){
		pfReservationFooterOperations.clickUndoCheckIn();
		pfReservationCheckInModal.confirmUndoCheckIn();
	}
}
