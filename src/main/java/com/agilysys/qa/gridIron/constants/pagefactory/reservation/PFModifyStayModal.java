package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsModifyStayPage;
import com.agilysys.qa.gridIron.pageobject.shared.widgets.POCalendars;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import com.codeborne.selenide.SelenideElement;

public class PFModifyStayModal extends PageFactoryBase{
	
	public static void selectRate(LocalDate arrivalDate, LocalDate rateDate, String roomTypeName, String ratePlanCode){
		int index = org.joda.time.Days.daysBetween(arrivalDate, rateDate).getDays()+3;				
		openArrow(LocatorsModifyStayPage.getRoomTypeArrow(roomTypeName));
		Selenide.sleep(500);
		click(LocatorsModifyStayPage.getRateByDateElementByRateplan(roomTypeName, ratePlanCode, index));
	}
	
	public static void clickAdjustRate(LocalDate arrivalDate, LocalDate rateDate){
	     if($$(By.cssSelector("btn.btn-checkbox.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched.active")).size()>0){
			 PFModifyStayModal.clickKeepCurrentRates();
		 }
		int index = org.joda.time.Days.daysBetween(arrivalDate, rateDate).getDays()+1;
		click(LocatorsBookAReservationPage.getSelectedRateElement(index));
	}
	
	public static void clickKeepCurrentRates(){
		click(LocatorsModifyStayPage.CHECKBOX_KEEP_CURRENT_RATE);
	}
	
	public static void clickConfirmModifyStayYes(){
		click(LocatorsModifyStayPage.CONFIRM_MODIFY_STAY_YES);
	}

	public static void clickExpandReservationDetails(){

		click(LocatorsModifyStayPage.EXPAND_RESERVATION_DETAILS);
	}
	
	public static void clickBook(){
		click(By.xpath("//span[@data-qa-id='ipxU8Nf']"));
		click(LocatorsModifyStayPage.BUTTON_BOOK);		
	}
	
	public static void setArrivalDate(LocalDate arrivalDate){		
		POCalendars calender = new POCalendars(LocatorsModifyStayPage.INPUT_ARRIVAL_DATE);
		calender.setDate(arrivalDate);
		Selenide.sleep(500);
	}

	public static void setDepartureDate(LocalDate departureDate){
		POCalendars calender = new POCalendars(LocatorsModifyStayPage.INPUT_DEPARTURE_DATE);
		String s = String.valueOf(departureDate.getDayOfMonth());
		ElementsCollection $$ = $$(By.xpath(("//span[@class = 'ng-binding']")));
		SelenideElement selenideElement = $$.stream().filter(x -> x.getText().contains(s)).findFirst().get();
	click(selenideElement);
		Selenide.sleep(500);
	}
}