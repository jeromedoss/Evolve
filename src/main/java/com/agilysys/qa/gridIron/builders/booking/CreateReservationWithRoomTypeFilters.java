package com.agilysys.qa.gridIron.builders.booking;

import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal.clickButtonBook;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns.navigateToProfiles;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickLinkCreateReservation;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickTabReservations;
import static com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationPage.stepOpenAndSelectRoomtype;
import static com.agilysys.qa.gridIron.pageobject.booking.POGuestInfoTab.stepSelectExistingGuest;
import static com.agilysys.qa.gridIron.pageobject.booking.POGuestInfoTab.stepSelectNewGuest;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByCash;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByCheck;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByDirect;
import static com.agilysys.qa.gridIron.pageobject.booking.POPaymentMethodTab.stepPaymentByVISACard;

import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class CreateReservationWithRoomTypeFilters {

	String lastName = null;
	String FirstName = null;
	String PhoneNo = null;
	String CompanyName = null;

	public CreateReservationWithRoomTypeFilters(String lastName, String FirstName, String PhoneNo, String CompanyName) {
		this.lastName = lastName;
		this.FirstName = FirstName;
		this.PhoneNo = PhoneNo;
		this.CompanyName = CompanyName;

	}

	public CreateReservationWithRoomTypeFilters(String lastName, String FirstName, String PhoneNo) {
		this.lastName = lastName;
		this.FirstName = FirstName;
		this.PhoneNo = PhoneNo;

	}

	public CreateReservationWithRoomTypeFilters(String lastName, String CompanyName) {
		this.lastName = lastName;
		this.CompanyName = CompanyName;

	}

	public CreateReservationWithRoomTypeFilters(String lastName) {
		this.lastName = lastName;

	}

	public void createReservationWithExistingGuestWithCash(String roomType) {

		stepOpenAndSelectRoomtype(roomType);
		stepSelectExistingGuest(lastName);
		stepPaymentByCash();
		clickButtonBook();

	}

	public void createReservationWithExistingGuestWithCheck(String roomType) {

		stepOpenAndSelectRoomtype(roomType);
		stepSelectExistingGuest(lastName);
		stepPaymentByCheck();
		clickButtonBook();

	}

	public void createReservationWithExistingGuestWithCard(String roomType) {

		stepOpenAndSelectRoomtype(roomType);
		stepSelectExistingGuest(lastName);
		stepPaymentByVISACard();
		clickButtonBook();

	}

	public void createReservationWithExistingGuestWithDirectBill(String roomType) {

		String CompanyCode = RandomHelper.getRandomAlphaString(5);

		createCompanyForDirectBill(CompanyCode);

		stepOpenAndSelectRoomtype(roomType);
		stepSelectExistingGuest(lastName);
		stepPaymentByDirect(CompanyName);
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithCash(String roomType) {

		stepOpenAndSelectRoomtype(roomType);
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByCash();
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithCheck(String roomType) {

		stepOpenAndSelectRoomtype(roomType);
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByCheck();
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithCard(String roomType) {

		stepOpenAndSelectRoomtype(roomType);
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByVISACard();
		clickButtonBook();

	}

	public void createReservationWithNewGuestWithDirectBill(String roomType) {

		String CompanyCode = RandomHelper.getRandomAlphaString(5);

		createCompanyForDirectBill(CompanyCode);

		stepOpenAndSelectRoomtype(roomType);
		stepSelectNewGuest(lastName, FirstName, PhoneNo);
		stepPaymentByDirect(CompanyName);
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