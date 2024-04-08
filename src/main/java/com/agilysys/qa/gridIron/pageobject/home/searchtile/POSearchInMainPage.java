package com.agilysys.qa.gridIron.pageobject.home.searchtile;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile.clickButtonGo;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile.setInputSearch;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;

import org.joda.time.LocalDate;

import com.agilysys.qa.gridIron.constants.locators.home.ProfileTile;
import com.agilysys.qa.gridIron.constants.locators.reservation.summary.LocatorsSummaryModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFProfileTile;
import com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POSearchInMainPage extends PageFactoryBase {

	public void search(String input) {
		setInputSearch(input);
		clickButtonGo();
	}
	
	public static void searchForReservation(String searchString){
		PFHeaderDropDowns.navigateToSearch();
		PFSearchTile.waitForSearchPageToLoad();		
        setInputSearch(searchString);
        clickButtonGo();
	}
	
	public static void searchForReservation(String searchString, LocalDate searchDate){
		PFHeaderDropDowns.navigateToSearch();
		PFSearchTile.waitForSearchPageToLoad();		
		PFSearchTile.setSearchDate(searchDate);
		setInputSearch(searchString);
        clickButtonGo();
	}
	
	public static void goToReservation(String searchString){
		searchForReservation(searchString);
		PFReservationTile.clickTabReservations();
		PFReservationTile.clickReservation(searchString);
		$(LocatorsSummaryModal.LABEL_ARRIVAL_DATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		waitForPageToLoad();
		Selenide.sleep(5000);
	}
	
	public static void goToReservationByConfirmationNumberAndGuestName(String confirmationNumber, String guestName){
		searchForReservation(confirmationNumber);
		PFReservationTile.clickTabReservations();
		PFReservationTile.clickReservation(guestName);
		$(LocatorsSummaryModal.LABEL_ARRIVAL_DATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void goToReservation(String searchString, LocalDate searchDate){
		searchForReservation(searchString , searchDate);
		PFReservationTile.clickTabReservations();
		PFReservationTile.clickReservation(searchString);
		$(LocatorsSummaryModal.LABEL_ARRIVAL_DATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void searchForGuestProfile(String searchString){
		PFHeaderDropDowns.navigateToProfiles();
		PFSearchTile.waitForSearchPageToLoad();
		PFProfileTile.clickRadioGuest();
        setInputSearch(searchString);
        clickButtonGo();
	}
	
	public static void searchForCompanyProfile(String searchString){
		PFHeaderDropDowns.navigateToProfiles();
		PFSearchTile.waitForSearchPageToLoad();
		PFProfileTile.clickRadioCompany();
        setInputSearch(searchString);
        clickButtonGo();
	}
	
	public static void goToGuestProfile(String searchString){
		Selenide.sleep(3000);
		searchForGuestProfile(searchString);
		click($$(ProfileTile.LIST_GUESTS).findBy(Condition.text(searchString)));
	}
	
	public static void goToCompanyProfile(String searchString){
		searchForCompanyProfile(searchString);
		click($$(ProfileTile.LIST_COMPANIES).findBy(Condition.text(searchString)));
	}
	
}
