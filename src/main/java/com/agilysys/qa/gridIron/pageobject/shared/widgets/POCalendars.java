package com.agilysys.qa.gridIron.pageobject.shared.widgets;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.agilysys.qa.gridIron.constants.locators.shared.widgets.Calendars;
import com.agilysys.qa.gridIron.constants.pagefactory.shared.widgets.PFCalendars;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class POCalendars extends PageFactoryBase{

	static String strYear;
	String strMonth;
	static String strDate;
	static int intMonth;

	Calendar cal = Calendar.getInstance();

	public POCalendars(By BUTTON_CALENDAR) {
		click(BUTTON_CALENDAR);

	}

	private void myCalendars() {

		Selenide.sleep(3000);
		String currentDate = $$(Calendars.LIST_DAYS_BUTTONS)
				.findBy(Condition.attribute("Class", "btn btn-default btn-sm btn-info active")).getText();

		click(Calendars.BUTTON_CENTER, 1);
		String currentMonth = $$(Calendars.LIST_MONTH_YEAR_BUTTONS)
				.findBy(Condition.attribute("Class", "btn btn-default btn-info active")).getText();

		click(Calendars.BUTTON_CENTER, 1);

		String currentYear = $$(Calendars.LIST_MONTH_YEAR_BUTTONS)
				.findBy(Condition.attribute("Class", "btn btn-default btn-info active")).getText();

		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(currentDate));
		cal.set(Calendar.MONTH, Integer.parseInt(currentMonth) - 1);
		cal.set(Calendar.YEAR, Integer.parseInt(currentYear));

	}

	private void addDays() {
		Date dcal = cal.getTime();
		String[] dates = dcal.toString().split(" ");

		Object[] sel = $$(Calendars.LIST_DAYS_BUTTONS).toArray();

		for (int i = 0; i < $$(Calendars.LIST_DAYS_BUTTONS).size(); i++) {
			if ($((SelenideElement) sel[i]).getText().equals(dates[2])) {
				click((SelenideElement) sel[i]);
				break;
			}
		}
	}

	private void addMonths() {
		Date dcal = cal.getTime();
		String[] dates = dcal.toString().split(" ");
		String month = null;

		switch (dates[1].toUpperCase()) {
		case "JAN":
			month = "01";
			break;
		case "FEB":
			month = "02";
			break;
		case "MAR":
			month = "03";
			break;
		case "APR":
			month = "04";
			break;
		case "MAY":
			month = "05";
			break;
		case "JUN":
			month = "06";
			break;
		case "JUL":
			month = "07";
			break;
		case "AUG":
			month = "08";
			break;
		case "SEP":
			month = "09";
			break;
		case "OCT":
			month = "10";
			break;
		case "NOV":
			month = "11";
			break;
		case "DEC":
			month = "12";
			break;
		default:
			System.out.println("Month failed to assign");
			break;
		}

		Object[] sel = $$(Calendars.LIST_MONTH_YEAR_BUTTONS).toArray();

		for (int i = 0; i < $$(Calendars.LIST_MONTH_YEAR_BUTTONS).size(); i++) {

			if ($((SelenideElement) sel[i]).getText().equals(month)) {
				click((SelenideElement) sel[i]);
				break;
			}
		}
	}

	private void addYears() {
		Date dcal = cal.getTime();
		String[] dates = dcal.toString().split(" ");

		Selenide.sleep(1000);

		if (Integer.parseInt(dates[5]) > Integer.parseInt($$(Calendars.LIST_YEAR_BUTTONS).last().getText())) {
			PFCalendars.clickButtonNextMonth();
		}

		if (Integer.parseInt(dates[5]) < Integer.parseInt($$(Calendars.LIST_YEAR_BUTTONS).first().getText())) {
			PFCalendars.clickButtonPreviousMonth();
		}

		Object[] sel = $$(Calendars.LIST_YEAR_BUTTONS).toArray();
		for (int i = 0; i < $$(Calendars.LIST_YEAR_BUTTONS).size(); i++) {

			if ($((SelenideElement) sel[i]).getText().equals(dates[5])) {
				click((SelenideElement) sel[i]);
				break;
			}
		}
	}

	public void stepAdd(int year, int month, int days) {

		myCalendars();

		cal.add(Calendar.DAY_OF_MONTH, days);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.YEAR, year);

		addYears();
		addMonths();
		addDays();
	}

	public void stepNewDate(String year, String month, String days) {

		click(Calendars.BUTTON_CENTER, 1);
		click(Calendars.BUTTON_CENTER, 1);

		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(days));
		cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
		cal.set(Calendar.YEAR, Integer.parseInt(year));

		addYears();
		addMonths();
		addDays();
	}

	public void setDate(LocalDate inputDate) {

		waitForElementToLoad(Calendars.BUTTON_CENTER, Configuration.timeout);
		sleep(500);
		click(Calendars.BUTTON_CENTER,0);
		sleep(500);
		click(Calendars.BUTTON_CENTER,0);

		//String strYear = null;
		String strMonth = null;
		String strDate = null;

		try {

			// Get the individual date, month and year from the date by using
			// 'SimpleDateFormat '


			// Get all the data of the date
			//strYear = (new SimpleDateFormat("yyyy")).format(inputDate);
			//strMonth = (new SimpleDateFormat("MM")).format(inputDate);
			//strDate = (new SimpleDateFormat("dd")).format(inputDate);
			
			cal.set(Calendar.DAY_OF_MONTH, inputDate.getDayOfMonth());
			cal.set(Calendar.MONTH, inputDate.getMonthOfYear()-1);
			cal.set(Calendar.YEAR, inputDate.getYear());

			sleep(1000);
			addYears();
			sleep(1000);
			addMonths();
			sleep(1000);
			addDays();

		} catch (Exception e) {
			System.out.println("Date parsing failed");
		}	
	}
}
