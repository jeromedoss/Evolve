package com.agilysys.qa.gridIron.builders.booking;

import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal.clickButtonBook;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns.clickButtonLogo;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFProfileTile.clickTabProfiles;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickLinkCreateReservation;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickTabReservations;
import static com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationPage.stepOpenBookReservationModal;
import static com.agilysys.qa.gridIron.pageobject.booking.POGuestInfoTab.stepSelectExistingGuest;
import static com.agilysys.qa.gridIron.pageobject.booking.POGuestInfoTab.stepSelectNewGuest;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByCash;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByCheck;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByDirect;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByVISACard;

import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.utils.RandomHelper;

public class CreateReservationWithDeposit {

	String lastName = null;
	String FirstName = null;
	String PhoneNo = null;
	String CompanyName = null;

	public CreateReservationWithDeposit(String lastName, String FirstName, String PhoneNo, String CompanyName) {
		this.lastName = lastName;
		this.FirstName = FirstName;
		this.PhoneNo = PhoneNo;
		this.CompanyName = CompanyName;

	}

	public CreateReservationWithDeposit(String lastName, String FirstName, String PhoneNo) {
		this.lastName = lastName;
		this.FirstName = FirstName;
		this.PhoneNo = PhoneNo;

	}

	public CreateReservationWithDeposit(String lastName, String CompanyName) {
		this.lastName = lastName;
		this.CompanyName = CompanyName;

	}

	public CreateReservationWithDeposit(String lastName) {
		this.lastName = lastName;

	}

	public void createReservationWithExistingGuestWithCash() {

		stepOpenBookReservationModal();
		stepSelectExistingGuest(lastName);
		stepPaymentByCash();
		POBookAReservationModal.setupDeposit("Cash", "10");
		clickButtonBook();

	}

	public void createReservationWithExistingGuestWithCheck() {

		stepOpenBookReservationModal();
		stepSelectExistingGuest(lastName);
		stepPaymentByCheck();
		POBookAReservationModal.setupDeposit("Check", "10");
		clickButtonBook();

	}

	public void createReservationWithExistingGuestWithCard() {

		stepOpenBookReservationModal();
		stepSelectExistingGuest(lastName);
		stepPaymentByVISACard();
		POBookAReservationModal.setupDeposit("Visa 0026", "10");
		clickButtonBook();
	}

	public void createReservationWithExistingGuestWithDirectBill() {

		String CompanyCode = RandomHelper.getRandomAlphaString(5);

		createCompanyForDirectBill(CompanyCode);

		stepOpenBookReservationModal();
		stepSelectExistingGuest(lastName);
		stepPaymentByDirect(CompanyName);
		POBookAReservationModal.setupDeposit(null, null);
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithCash() {

		stepOpenBookReservationModal();
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByCash();
		POBookAReservationModal.setupDeposit("Cash", "10");
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithCheck() {

		stepOpenBookReservationModal();
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByCheck();
		POBookAReservationModal.setupDeposit("Check", "10");
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithCard() {

		stepOpenBookReservationModal();
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByVISACard();
		POBookAReservationModal.setupDeposit("Visa 0026", "10");
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithDirectBill() {

		String CompanyCode = RandomHelper.getRandomAlphaString(5);

		createCompanyForDirectBill(CompanyCode);

		stepOpenBookReservationModal();
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByDirect(CompanyName);
		POBookAReservationModal.setupDeposit(null, null);
		clickButtonBook();

	}

	private void createCompanyForDirectBill(String CompanyCode) {

		clickButtonLogo();
		clickTabProfiles();

		new CreateCompanyProfile(CompanyName, CompanyCode).flowCreateWithContactsARUsingSaveExit();

		clickTabReservations();
		clickLinkCreateReservation();

	}

}
