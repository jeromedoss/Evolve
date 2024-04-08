package com.agilysys.qa.gridIron.constants.pagefactory.booking;

import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookReservationModal.BUTTON_BOOK;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookReservationModal.BUTTON_BOOK_AS_WALKIN;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookReservationModal.BUTTON_CANCEL;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookReservationModal.CONFIRMATION_TAB;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookReservationModal.LABEL_RESERVATION_CONFIRMATION_NO;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookReservationModal.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.constants.locators.reservation.summary.LocatorsSummaryModal;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
public class PFBookReservationModal extends PageFactoryBase{

	public static void clickButtonBook() {
		click(BUTTON_BOOK);
		waitForElementToLoad(CONFIRMATION_TAB, Configuration.timeout);
	}
	
	public static void clickButtonBookAsWalkIn() {
		click(BUTTON_BOOK_AS_WALKIN);
		$(LocatorsSummaryModal.LABEL_ARRIVAL_DATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static String getReservationConfirmationNumber(){
		return $(LABEL_RESERVATION_CONFIRMATION_NO).getText();
	}
	
	public static void closeBookReservationModal(){
		click(BUTTON_CANCEL);
	}
	
	public static void goToGuestInfoTab() {
		click(NAVIGATION_GUEST_INFO);
	}
	
	public static void goToSourcesTab() {
		click(NAVIGATION_SOURCES);
	}
	
	public static void goToEstimatedChargesTab() {
		click(NAVIGATION_ESTIMATED_CHARGES);
		click(ESTIMATED_CHARGE_LOADED_ELEMENT);;
		
	}
	
	public static void goToOthersTab() {
		click(NAVIGATION_OTHERS);
	}
	
	public static void typeReservationAlias(String reservationAlias){
		if(null ==  reservationAlias){
			return;
		}
		if($(RESERVATION_ALIAS_ARROW).getAttribute("class").contains("close")){
			click(RESERVATION_ALIAS_ARROW);
		}
		type(INPUT_RESERVATION_ALIAS, reservationAlias);		
	}
	
	public static void clickAddRecurringCharges(){
		click(LINK_ADD_RECURRING_CHARGES);
	}
	
	public static void goToPaymentTab() {
		click(NAVIGATION_PAYMENT);		
	}
	
	public static void goToDepositTab() {
		click(NAVIGATION_DEPOSIT);
	}
}