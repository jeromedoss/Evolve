package com.agilysys.qa.gridIron.constants.pagefactory.booking;

import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage.BUTTON_BOOK;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage.BUTTON_CHECK_RATES;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage.DIV_RATE_SEARCH_RESULT_HEADER;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage.INPUT_ADULT_COUNT;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage.INPUT_CHILD_COUNT;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage.INPUT_NO_OF_NIGHTS;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage.getRatePlanArrow;
import static com.agilysys.qa.gridIron.constants.locators.booking.filters.LocatorsGuestsCount.INPUT_ADULTS;
import static com.agilysys.qa.gridIron.constants.locators.booking.rates.LocatorsRatesPlans.SELECT_RATE;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import com.codeborne.selenide.SelenideElement;
import org.joda.time.LocalDate;

import com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage;
import com.agilysys.qa.gridIron.pageobject.shared.widgets.POCalendars;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
public class PFBookAReservationPage extends PageFactoryBase{

	public static void setArrivalDate(LocalDate arrivalDate){		
		POCalendars calender = new POCalendars(LocatorsBookAReservationPage.INPUT_ARRIVAL_DATE_PICKER);
		calender.setDate(arrivalDate);
		Selenide.sleep(500);
		click(LocatorsBookAReservationPage.RANDOM_CLICK);
	}
	
	public static void setDepartureDate(LocalDate departureDate){
		POCalendars calender = new POCalendars(LocatorsBookAReservationPage.INPUT_DEPARTURE_DATE_PICKER);
		calender.setDate(departureDate);
		Selenide.sleep(500);
		click(LocatorsBookAReservationPage.RANDOM_CLICK);
	}
	
	public static void typeNoOfNights(String noOfNights){
		clearAndType(INPUT_NO_OF_NIGHTS, noOfNights);
	}
	
	public static void typeAdultCount(String adultCount){
		clearAndType(INPUT_ADULT_COUNT, adultCount);
	}
	
	public static void typeChildCount(String childCount){
		clearAndType(INPUT_CHILD_COUNT, childCount);
	}
	
	public static void clickButtonCheckRates() {
		doubleClick(BUTTON_CHECK_RATES);
		$(DIV_RATE_SEARCH_RESULT_HEADER).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}

	//same
	public static void selectRate(LocalDate arrivalDate, LocalDate rateDate, String ratePlanName, String roomTypeCode){
		try {
			int index = org.joda.time.Days.daysBetween(arrivalDate, rateDate).getDays() + 3;

			if ($(LocatorsBookAReservationPage.getRatePlanArrow(ratePlanName)).getAttribute("class").contains("closed")) {
				scrollElementToVisibleArea($(getRatePlanArrow(ratePlanName)));
				click(LocatorsBookAReservationPage.getRatePlanArrow(ratePlanName));
			}
			Selenide.sleep(500);
			//Selenide.executeJavaScript("window.scrollTo(0, 250)");
			SelenideElement element = $(LocatorsBookAReservationPage.getRateByDateElementByRateplan(ratePlanName, roomTypeCode, index));
			click(element);

			//click(LocatorsBookAReservationPage.getRateByDateElementByRateplan(ratePlanName, roomTypeCode, index));
		}catch (Exception e)
		{
			System.out.println("Error " + e.getMessage());
		}
	}
	
	public static void clickAdjustRate(LocalDate arrivalDate, LocalDate rateDate){
		int index = org.joda.time.Days.daysBetween(arrivalDate, rateDate).getDays()+1;
		click(LocatorsBookAReservationPage.getSelectedRateElementByIndex(index));
	}
	
	public static void cancelAdjustRate(){
		click(LocatorsBookAReservationPage.LINK_ADJUST_CANCEL);
	}
	
	public static void clickSelectRate() {
		$(INPUT_ADULTS).scrollTo();
		Selenide.sleep(2000);	
		$(SELECT_RATE).shouldBe(visible,enabled);
		click(SELECT_RATE);
	}

	public static void clickButtonBook() {
		click(BUTTON_BOOK);
	}
}
