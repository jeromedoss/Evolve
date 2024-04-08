package com.agilysys.qa.gridIron.constants.pagefactory.booking;

import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.sources.LocatorsSourcesTab.*;
import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookReservationModal.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFSourcesTab extends PageFactoryBase {

	public static void selectSourceOfBusiness(String sourceOfBusiness) {
		selectByText(DROPDOWN_SOURCE_OF_BUSINESS, LIST_SOURCE_OF_BUSINESS, sourceOfBusiness);
	}

	public static void selectGuestType(String guestType) {
		selectByText(DROPDOWN_GUEST_TYPE, LIST_GUEST_TYPE, guestType);
	}
	
	
	public static void selectMarketSegment(String guestType) {
		selectByText(DROPDOWN_MARKET_SEGMENT, LIST_MARKET_SEGMENT, guestType);
	}

	public static void checkSaveToProfile() {
		click(CHECKBOX_SAVE_TO_PROFILE);
	}

	public static void clickAddTravelAgent() {
		click(LINK_ADD_TRAVEL_AGENT);
	}

	public static void selectTravelAgent(String input) {
		type(INPUT_TA_SEARCH, input);
		click(By.xpath("//a[contains(@title,'" + input + "')]"));
	}

	public static void clickAddConfirmationNumber() {
		click(LINK_ADD_CONFIRMATION_NUMBER);
	}

	public static void selectThirdPartySource(String input) {
		click(DROPDOWN_THIRD_PARTY_SOURCE);
		Selenide.actions().click().sendKeys(input).build().perform();
		click(HEADER_MODAL);
	}

	public static void setConfirmation(String input) {
		type(INPUT_CONFIRMATION, input);
	}
}
