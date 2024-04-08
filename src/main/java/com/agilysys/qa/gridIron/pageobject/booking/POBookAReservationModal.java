package com.agilysys.qa.gridIron.pageobject.booking;

import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookReservationModal.MESSAGE_SUCCESS_CONFORMATION;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;
import java.util.Set;

import com.agilysys.pms.profile.model.Address;
import com.agilysys.pms.profile.model.AddressDetails;
import com.agilysys.pms.profile.model.Email;
import com.agilysys.pms.profile.model.EmailDetails;
import com.agilysys.pms.profile.model.PersonalDetails;
import com.agilysys.pms.profile.model.Phone;
import com.agilysys.pms.profile.model.PhoneDetails;
import com.agilysys.pms.profile.model.v1.GuestProfile;
import com.agilysys.pms.reservation.model.ThirdPartyConfirmation;
import com.agilysys.common.model.TrackingInfo;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFDepositTab;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFGuestInfoTab;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFPaymentMethodTab;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFSourcesTab;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class POBookAReservationModal {

	public static void searchForExistingGuest(String searchString) {
		PFGuestInfoTab.typeGuestProfileSearch(searchString);
		PFGuestInfoTab.selectGuestProfile();
	}

	public static void addNewGuest(GuestProfile guestProfile) {
		Selenide.sleep(1000);
		PFGuestInfoTab.typeGuestProfileSearch("");
		PFGuestInfoTab.clickAddNewGuestProfile();

		PersonalDetails personalDetails = guestProfile.getPersonalDetails();
		PFGuestInfoTab.typeLastName(personalDetails.getLastName());
		PFGuestInfoTab.typeFirstname(personalDetails.getFirstName());
		PFGuestInfoTab.typeMiddleInitial(personalDetails.getMiddleInitial());
		if (personalDetails.getCompanyName() != null) {
			PFGuestInfoTab.expandContactSection();
			PFGuestInfoTab.typeCompanyName(personalDetails.getCompanyName());
		}
		AddressDetails addressDetails = guestProfile.getAddressDetails();
		if (addressDetails != null && addressDetails.getAddresses().size() > 0) {
			Address address = guestProfile.getAddressDetails().getAddresses().get(0);
			PFGuestInfoTab.expandAddressSection();
			PFGuestInfoTab.selectAddressType(address.getAddressType());
			PFGuestInfoTab.typeAddressLine1(address.getAddressLine1());
			PFGuestInfoTab.typeAddressLine2(address.getAddressLine2());
			PFGuestInfoTab.typeAddressLine3(address.getAddressLine3());
			PFGuestInfoTab.typeAddressLine4(address.getAddressLine4());
			PFGuestInfoTab.typeAddressLine5(address.getAddressLine5());
			PFGuestInfoTab.typeCity(address.getCityName());
			PFGuestInfoTab.typeState(address.getStateProvinceCode());
			PFGuestInfoTab.typePostalCode(address.getPostalCode());
			PFGuestInfoTab.typeCountry(address.getCountryCode());
			PFGuestInfoTab.typeCounty(address.getCountyCode());
		}

		PhoneDetails phoneDetails = guestProfile.getPhoneDetails();
		if (phoneDetails != null && phoneDetails.getPhones().size() > 0) {
			Phone phone = guestProfile.getPhoneDetails().getPhones().get(0);
			PFGuestInfoTab.expandContactSection();
			PFGuestInfoTab.typePhoneNumber(phone.getNumber());
			PFGuestInfoTab.selectPhoneType(phone.getPhoneType());
			PFGuestInfoTab.typePhoneExtn(phone.getExtension());
		}

		EmailDetails emailDetails = guestProfile.getEmailDetails();
		if (emailDetails != null && emailDetails.getEmailAddresses().size() > 0) {
			Email email = guestProfile.getEmailDetails().getEmailAddresses().get(0);
			PFGuestInfoTab.expandContactSection();
			PFGuestInfoTab.typeEmailAddress(email.getEmailAddress());
			PFGuestInfoTab.selectEmailType(email.getType());
		}
	}

	public static void addReservtionAlias(String reservationAlias) {
		if (reservationAlias != null) {
			PFBookReservationModal.goToOthersTab();
			PFBookReservationModal.typeReservationAlias(reservationAlias);
		}
	}

	public static void openAddRecurringChargeModal() {
		PFBookReservationModal.goToEstimatedChargesTab();
		PFBookReservationModal.clickAddRecurringCharges();
	}

	public static void addTrackingInfo(TrackingInfo trackingInfo, boolean saveToProfile) {
		
		if (trackingInfo != null) {
			PFBookReservationModal.goToSourcesTab();			
			PFSourcesTab.selectGuestType(trackingInfo.getGuestType());
			PFSourcesTab.selectMarketSegment(trackingInfo.getSegmentCode());
			PFSourcesTab.selectSourceOfBusiness(trackingInfo.getSourceCode());
			if (saveToProfile) {
				PFSourcesTab.checkSaveToProfile();
			}
		}
	}

	public static void addTravelAgentDetails(List<String> travelAgents) {
		if (travelAgents != null) {
			PFBookReservationModal.goToSourcesTab();
			travelAgents.forEach((String travelAgentName) -> {
				PFSourcesTab.clickAddTravelAgent();
				PFSourcesTab.selectTravelAgent(travelAgentName);
			});
		}
	}

	public static void addThirdPartyConfirmationDetails(Set<ThirdPartyConfirmation> thirdPartyConfirmations) {
		if (thirdPartyConfirmations != null) {
			PFBookReservationModal.goToSourcesTab();
			thirdPartyConfirmations.forEach((ThirdPartyConfirmation thirdPartyConfirmation) -> {
				PFSourcesTab.clickAddConfirmationNumber();
				PFSourcesTab.selectThirdPartySource(thirdPartyConfirmation.getConfirmationName());
				PFSourcesTab.setConfirmation(thirdPartyConfirmation.getConfirmationNumber());
			});
		}
	}

	public static void addPaymentMethod(int index, String paymentMethod, String cardData, String directBillAccount) {
		PFPaymentMethodTab PFPaymentMethodTab = new PFPaymentMethodTab(index);
		PFBookReservationModal.goToPaymentTab();
		if (index > 0) {
			PFPaymentMethodTab.clickAddNewPaymentMethod();
		}
		PFPaymentMethodTab.selectPaymentMethod(paymentMethod);

		if (cardData != null) {
			PFPaymentMethodTab.swipeCard(cardData);
		}

		if (directBillAccount != null) {
			PFPaymentMethodTab.typeDirectBillAccount(directBillAccount);
		}
	}

	public static void setupDeposit(String paymentMethod, String depositAmount) {
		PFBookReservationModal.goToDepositTab();
		PFDepositTab.selectDepositPaymentMethod(paymentMethod);
		PFDepositTab.typeDepositAmount(depositAmount);
		System.out.println(depositAmount);
		if ("Cash".equalsIgnoreCase(paymentMethod) )
			if (!"0".equalsIgnoreCase(depositAmount) && !"null".equalsIgnoreCase(depositAmount))
				PFDepositTab.setCredentials();
	}

	public static void bookReservation(boolean walkIn) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (walkIn) {
			PFBookReservationModal.clickButtonBookAsWalkIn();
		} else {
			PFBookReservationModal.clickButtonBook();
		}
	}

	public static void verifyBookConfirmationMessage() {
		$(MESSAGE_SUCCESS_CONFORMATION).should(Condition.text("The reservation has been made successfully."));
	}

}