package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import com.agilysys.qa.gridIron.constants.locators.reservation.guestinformation.LocatorsGuestInformationSection;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;

public class PFReservationGuestInfo extends PageFactoryBase{
	
	public static void expandGuestInformation(){
		
		$(LocatorsGuestInformationSection.DROPDOWN_ADDRESS_TYPE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		expandSection(LocatorsGuestInformationSection.BUTTON_GUESTINFO_COLLAPSE);		
	}
	
	public static void collapaseGuestInformation(){
		$(LocatorsGuestInformationSection.DROPDOWN_ADDRESS_TYPE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		collapseSection(LocatorsGuestInformationSection.BUTTON_GUESTINFO_COLLAPSE);		
	}
}
