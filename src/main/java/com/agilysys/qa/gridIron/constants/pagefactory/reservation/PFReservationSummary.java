package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.agilysys.qa.gridIron.constants.locators.batchoperation.LocatorsRegistrationPage;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsRequestServiceDialog;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsFooterButtonsSection;
import com.agilysys.qa.gridIron.constants.locators.reservation.summary.LocatorsLateCheckoutModal;
import com.agilysys.qa.gridIron.constants.locators.reservation.summary.LocatorsSummaryModal;
import com.agilysys.qa.gridIron.constants.locators.reservation.summary.LocatorsVipModal;
import com.agilysys.qa.gridIron.pageobject.shared.widgets.POCalendars;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;


public class PFReservationSummary extends PageFactoryBase{
	
	public static void expandReservationSummary(){		
		$(LocatorsSummaryModal.LABEL_ROOM_TYPE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		expandSection(LocatorsSummaryModal.BUTTON_SUMMARY_COLLAPSE);		
	}
	
	public static void collapseReservationSummary(){
		$(LocatorsSummaryModal.LABEL_ROOM_TYPE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		collapseSection(LocatorsSummaryModal.BUTTON_SUMMARY_COLLAPSE);		
	}
	
	public static void clickLateCheckOutBadge(){
		click(LocatorsSummaryModal.BADGE_LATE_CHECKOUT);
	}
	
	public static void setLateCheckoutHours(String hours){
		clearAndType(LocatorsLateCheckoutModal.INPUT_HOURS, hours);
	}
	
	public static void setLateCheckoutMinutes(String minutes){
//		$(LocatorsLateCheckoutModal.INPUT_MINUTES).clear();
	//	ThreadHelper.sleep(2000);

		while (!$(LocatorsLateCheckoutModal.INPUT_MINUTES).getValue().equals(minutes) ||
			$(LocatorsLateCheckoutModal.INPUT_MINUTES).getValue().equals("00")) {
			System.out.println("* Trying *");
			$(LocatorsLateCheckoutModal.INPUT_MINUTES).clear();
			$(LocatorsLateCheckoutModal.INPUT_MINUTES).sendKeys(Keys.BACK_SPACE);
			$(LocatorsLateCheckoutModal.INPUT_MINUTES).sendKeys(Keys.BACK_SPACE);
			$(LocatorsLateCheckoutModal.INPUT_MINUTES).setValue(minutes);
		}
		//clearAndType(LocatorsLateCheckoutModal.INPUT_MINUTES, minutes);


	}
	
	public static void clickSaveLateCheckout(){
		click(LocatorsLateCheckoutModal.BUTTON_SAVE);
		waitForElementToDisappear(LocatorsLateCheckoutModal.BUTTON_SAVE, Configuration.timeout);
		Selenide.sleep(500);
		$(LocatorsSummaryModal.LABEL_ARRIVAL_DATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clickRemoveLateCheckout(){
		click(LocatorsLateCheckoutModal.BUTTON_REMOVE_LATE_CHECKOUT);
		Selenide.sleep(500);
		waitForElementToDisappear(LocatorsLateCheckoutModal.BUTTON_REMOVE_LATE_CHECKOUT, Configuration.timeout);
		Selenide.sleep(500);
		$(LocatorsSummaryModal.LABEL_ARRIVAL_DATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clickLinkReservationBadge(){
		click(LocatorsSummaryModal.BADGE_LINKED_RESERVATION);
	}
	
	public static void clickGuestMessageBadge(){
		click(LocatorsSummaryModal.BADGE_GUEST_MESSAGE);
	}
	
	public static void clickNRGBadge(){
		click(LocatorsSummaryModal.BADGE_NRG);
		waitForBadgeSaveToComplete(LocatorsSummaryModal.BADGE_NRG);
		$(LocatorsSummaryModal.BADGE_NRG).shouldBe(Condition.visible);
		Selenide.sleep(500);
		$(LocatorsSummaryModal.LABEL_ARRIVAL_DATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clickPetBadge(){
		click(LocatorsSummaryModal.BADGE_PET);
	}
	
	public static void selectPet(String pet){
		selectByText(LocatorsSummaryModal.PET_MODAL_DROPDWON_PET, LocatorsSummaryModal.PET_MODAL_DROPDWON_OPTIONS_PET, pet);
	}
	
	public static void typePetName(String petName){
		clearAndType(LocatorsSummaryModal.PET_MODAL_PETNAME, petName);
	}
	
	public static void deletePet(){
		click(LocatorsSummaryModal.PET_MODAL_DELETE_PET);
	}
	
	public static void clickConfirmDelete(){
		click(LocatorsSummaryModal.PET_MODAL_CONFIRM_DELETE_PET);
	}
	
	public static void savePetModal(){
		click(LocatorsSummaryModal.PET_MODAL_SAVE);
		waitForElementToDisappear(LocatorsSummaryModal.PET_MODAL_SAVE, Configuration.timeout);
		Selenide.sleep(500);
		$(LocatorsSummaryModal.LABEL_ARRIVAL_DATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clickSVCBadge(){
		click(LocatorsSummaryModal.BADGE_SVC);
	}
	
	public static void clickCreateRequestService(){
		click(LocatorsSummaryModal.BUTTON_REQUEST_SERVICE);
		$(LocatorsRequestServiceDialog.TEXTAREA_NOTES).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clickServiceRequestEditLink(String serviceCode, String roomNo){
		click(LocatorsSummaryModal.getServiceRequestEditLinkByServiceCode(serviceCode));
		$(LocatorsRequestServiceDialog.INPUT_ROOM_NO).should(Condition.value(roomNo),  Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clickVIPBadge(){
		click(LocatorsSummaryModal.BADGE_VIP);		
	}
	
	public static void selectVIP(String vipStatus){
		selectByText(LocatorsVipModal.DROPDOWN_VIP, LocatorsVipModal.DROPDOWN_LIST_TEXTS_VIP, vipStatus);
	}
	
	public static void clickSaveVIPStatus(){
		click(LocatorsVipModal.BUTTON_SAVE);
		waitForElementToDisappear(LocatorsVipModal.BUTTON_SAVE, Configuration.timeout);
		Selenide.sleep(500);
		$(LocatorsSummaryModal.LABEL_ARRIVAL_DATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	
	public static void waitForBadgeSaveToComplete(By badgeLocator){
		Selenide.sleep(2000);
		int time =0;
		while(time<=Configuration.timeout){
				if($(badgeLocator).findElement(By.xpath("//div[contains(@ng-show,'showLoading')]")).getAttribute("class").contains("ng-hide")){
					Selenide.sleep(1000);
					return;
				}
			Selenide.sleep(2000);
			time = time +2000;
		}
		$(badgeLocator).find(By.xpath("//div[contains(@ng-show,'showLoading')]"));
	}
	public static void setArrivalDate(LocalDate arrivalDate){
		POCalendars poCalender = new POCalendars(LocatorsSummaryModal.LABEL_ARRIVAL_DATE);
		poCalender.setDate(arrivalDate);
	}
	
	public static void setDepartureDate(LocalDate departureDate){
		POCalendars poCalender = new POCalendars(LocatorsSummaryModal.LABEL_DEPARTURE_DATE);
		poCalender.setDate(departureDate);
	}
	
	public static void typeReservationAlias(String reservationAlias){
		clearAndType(LocatorsSummaryModal.INPUT_RESERVATION_ALIAS, reservationAlias);
	}
	
	public static void typeCompany(String companyName){
		selectTypeHead(LocatorsSummaryModal.TYPEHEAD_COMPANY, companyName);
	}
	
	public static void saveAndWaitForActionToComplete(){
		click(LocatorsFooterButtonsSection.BUTTON_SAVE_CHANGES);
		waitForElementToDisappear(LocatorsFooterButtonsSection.BUTTON_SAVE_CHANGES, Configuration.timeout);
	}
	
	public static void clickSaveReservation(){
		click(LocatorsFooterButtonsSection.BUTTON_SAVE_CHANGES);		
	}
	
	public static void clickAddTravelAgent(){
		clickAndWaitForNextElement(LocatorsSummaryModal.LINK_ADD_TRAVEL_AGENT, LocatorsSummaryModal.INPUT_TRAVEL_AGENT);
	}
	
	public static void typeTravelAgent(String travelAgent){
		$(LocatorsSummaryModal.INPUT_TRAVEL_AGENT).scrollIntoView(false);
		$(LocatorsSummaryModal.INPUT_TRAVEL_AGENT).sendKeys(travelAgent);
		click(By.xpath("//a[@title='"+travelAgent+"']" ));
	}
	
	public static void typeBookedBy(String bookedBy){
		clearAndType(LocatorsSummaryModal.INPUT_BOOKED_BY, bookedBy);
//		System.out.println($(LocatorsSummaryModal.INPUT_BOOKED_BY).getValue());
//		if(!$(LocatorsSummaryModal.INPUT_BOOKED_BY).getValue().equals(bookedBy)){
//			System.out.println("&&");
//		}

		System.out.println("TTT");

//		long deadline = System.currentTimeMillis() + Configuration.timeout;
//		while(!$(LocatorsSummaryModal.INPUT_BOOKED_BY).getValue().equals(bookedBy)  && System.currentTimeMillis() <= deadline) {
//			$(LocatorsSummaryModal.INPUT_BOOKED_BY).clear();
//			type(LocatorsSummaryModal.INPUT_BOOKED_BY, bookedBy);
//			System.out.println("**");
//		}
//		System.out.println("Test");
	}
	
	public static void typeSourceOfBusiness(String sourceOfBusiness){
		type(LocatorsSummaryModal.INPUT_SOURCE_OF_BUSINESS,  sourceOfBusiness);
		click(LocatorsSummaryModal.HEADER_MODAL);
	}
	
	public static void selectGuestType(String guestType){
		selectByText(LocatorsSummaryModal.DROPDOWN_GUEST_TYPE,  LocatorsSummaryModal.DROPDOWN_OPTIONS_GUEST_TYPE, guestType);
	}
	
	public static void typeMarketSegment(String marketSegment){
		type(LocatorsSummaryModal.INPUT_MARKET_SEGMENT,  marketSegment);
		click(LocatorsSummaryModal.HEADER_MODAL);
	}
	

	public static void clickAddThirdPartyConfirmation(){
		click(LocatorsSummaryModal.LINK_ADD_THIRD_PARTY_CONFIRMATION);
	}
	public static void setThirdPartySource(String thirdPartySource){
		type(LocatorsSummaryModal.INPUT_THIRD_PARTY_SOURCE, thirdPartySource);
	}
	
	public static void setThirdPartyConfirmationNumber(String confirmationNumber){
		type(LocatorsSummaryModal.INPUT_THIRD_CONFIRMATION_NUMBER, confirmationNumber);
	}
	
	public static String getReservationConfirmationNumber(){
		return $(LocatorsSummaryModal.LABEL_CONFORMARION_NUMBER).getText().split("-")[1].trim();
	}
}