package com.agilysys.qa.gridIron.pageobject.booking;

import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab.checkSaveToProfile;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab.clickAddConfirmationNumber;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab.clickAddTravelAgent;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab.selectGuestType;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab.selectMarketSegment;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab.selectSourceOfBusiness;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab.selectThirdPartySource;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab.selectTravelAgent;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab.setConfirmation;

import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POSourcesTab {

	public static void addTrackingInfo(String sourceOfBusiness, String guestType, String marketSegment, boolean saveToProfile ) {
		PFBookReservationModal.goToSourcesTab();
		selectSourceOfBusiness(sourceOfBusiness);		
		selectGuestType(guestType);
		selectMarketSegment(marketSegment);
		if(saveToProfile){
			checkSaveToProfile();
		}		
	}

	public static void AddTravelAgentDetails(String input) {
		PFBookReservationModal.goToSourcesTab();
		clickAddTravelAgent();		
		selectTravelAgent(input);
	}
	
	public static void AddThirdPartyDetails(String thirdPartySource, String confirmation) {
		PFBookReservationModal.goToSourcesTab();
		clickAddConfirmationNumber();
		selectThirdPartySource(thirdPartySource);
		setConfirmation(confirmation);
	}

	
}