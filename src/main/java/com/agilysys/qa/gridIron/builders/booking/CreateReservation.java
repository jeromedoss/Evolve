package com.agilysys.qa.gridIron.builders.booking;

import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal.clickButtonBook;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns.navigateToProfiles;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickLinkCreateReservation;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickTabReservations;
import static com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationPage.stepOpenBookReservationModal;
import static com.agilysys.qa.gridIron.pageobject.booking.POGuestInfoTab.stepSelectExistingGuest;
import static com.agilysys.qa.gridIron.pageobject.booking.POGuestInfoTab.stepSelectNewGuest;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByCash;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByCheck;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByDirect;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByVISACard;
import static com.agilysys.qa.gridIron.pageobject.booking.POSourcesTab.AddThirdPartyDetails;
import static com.agilysys.qa.gridIron.pageobject.booking.POSourcesTab.AddTravelAgentDetails;
import static com.agilysys.qa.gridIron.pageobject.booking.POSourcesTab.addTrackingInfo;

import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class CreateReservation {

	String lastName = null;
	String FirstName = null;
	String PhoneNo = null;
	String CompanyName = null;

	public CreateReservation(String lastName, String FirstName, String PhoneNo, String CompanyName) {
		this.lastName = lastName;
		this.FirstName = FirstName;
		this.PhoneNo = PhoneNo;
		this.CompanyName = CompanyName;

	}

	public CreateReservation(String lastName, String FirstName, String PhoneNo) {
		this.lastName = lastName;
		this.FirstName = FirstName;
		this.PhoneNo = PhoneNo;

	}

	public CreateReservation(String lastName, String CompanyName) {
		this.lastName = lastName;
		this.CompanyName = CompanyName;

	}

	public CreateReservation(String lastName) {
		this.lastName = lastName;

	}

	public void createReservationWithExistingGuestWithCash() {

		stepOpenBookReservationModal();
		stepSelectExistingGuest(lastName);
		stepPaymentByCash();
		clickButtonBook();

	}

	public void createReservationWithExistingGuestWithCheck() {

		stepOpenBookReservationModal();
		stepSelectExistingGuest(lastName);
		stepPaymentByCheck();
		clickButtonBook();

	}

	public void createReservationWithExistingGuestWithCard() {

		stepOpenBookReservationModal();
		stepSelectExistingGuest(lastName);
		stepPaymentByVISACard();
		clickButtonBook();

	}

	public void createReservationWithExistingGuestWithDirectBill() {

		String CompanyCode = RandomHelper.getRandomAlphaString(5);

		createCompanyForDirectBill(CompanyCode);

		stepOpenBookReservationModal();
		stepSelectExistingGuest(lastName);
		stepPaymentByDirect(CompanyName);
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithCash() {

		stepOpenBookReservationModal();
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByCash();
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithCheck() {

		stepOpenBookReservationModal();
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByCheck();
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithCard() {

		stepOpenBookReservationModal();
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByVISACard();
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithDirectBill() {

		String CompanyCode = RandomHelper.getRandomAlphaString(5);

		createCompanyForDirectBill(CompanyCode);

		stepOpenBookReservationModal();
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByDirect(CompanyName);
		clickButtonBook();

	}
	
	public void createReservationWithTrackingInfo(String sourceOfBusiness, String guestType, String marketSegment, boolean saveToProfile) {
        stepOpenBookReservationModal();
        stepSelectNewGuest(lastName, FirstName, PhoneNo);       
        stepPaymentByCash();
        addTrackingInfo(sourceOfBusiness, guestType, marketSegment, saveToProfile);
        clickButtonBook();
    }
    
    public void createReservationWithThirdPartyDetails(String thirdPartySource, String confirmation) {
        stepOpenBookReservationModal();
        stepSelectNewGuest(lastName, FirstName, PhoneNo);
        stepPaymentByCash();
        AddThirdPartyDetails(thirdPartySource, confirmation);
        clickButtonBook();
    }
    
    public void createReservationWithTravelAgent(String travelAgent) {
        stepOpenBookReservationModal();
        stepSelectNewGuest(lastName, FirstName, PhoneNo);
        stepPaymentByCash();
        AddTravelAgentDetails(travelAgent);
        clickButtonBook();
    }

	private void createCompanyForDirectBill(String CompanyCode) {

		navigateToProfiles();
		Selenide.refresh();

		new CreateCompanyProfile(CompanyName, CompanyCode).flowCreateWithContactsARUsingSaveExit();

		clickTabReservations();
		clickLinkCreateReservation();

	}
}