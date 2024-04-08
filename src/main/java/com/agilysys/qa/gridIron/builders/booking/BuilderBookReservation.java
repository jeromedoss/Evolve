package com.agilysys.qa.gridIron.builders.booking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.agilysys.common.model.TrackingInfo;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import org.joda.time.LocalDate;
import com.agilysys.pms.profile.model.Address;
import com.agilysys.pms.profile.model.AddressDetails;
import com.agilysys.pms.profile.model.Email;
import com.agilysys.pms.profile.model.EmailDetails;
import com.agilysys.pms.profile.model.PersonalDetails;
import com.agilysys.pms.profile.model.Phone;
import com.agilysys.pms.profile.model.PhoneDetails;
import com.agilysys.pms.profile.model.v1.GuestProfile;
import com.agilysys.pms.property.model.RoomType;
import com.agilysys.pms.rates.model.RatePlanDetail;
import com.agilysys.pms.rates.model.RatePlanSummary;
import com.agilysys.pms.reservation.model.FullDayRateSnapshot;
import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.pms.reservation.model.ThirdPartyConfirmation;
import com.agilysys.qa.gridIron.builders.shared.folios.BuilderAddRecurringCharge;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookAReservationPage;
import com.agilysys.qa.gridIron.data.models.RecurringCharge;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationPage;

public class BuilderBookReservation {

	ReservationCreateRequest reservationCreateRequest;
	GuestProfile guestProfile;
	String guestProfileSearchString;
	boolean trackingInfoSaveToProfile = false;
	boolean deposit = false;
	List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
	String depositPaymentMethod;
	String depositAmount = null;
	List<String> travelAgents = new ArrayList<>();
	List<RecurringCharge> recurringCharges = null;
	LocalDate recurringChargePostDate = null;
	String recurringChargeReason = null;
	boolean recurringChargeCheckAvailabilty = false;
	String recurringChargeOverrideUsername = null;
	String recurringChargeOverridePassword = null;

	public BuilderBookReservation(ReservationCreateRequest reservationCreateRequest) {
		this.reservationCreateRequest = reservationCreateRequest;
		this.guestProfile = new GuestProfile();
		this.guestProfile.setPersonalDetails(new PersonalDetails());
	}

	public BuilderBookReservation setPersonalDetails(PersonalDetails personalDetails) {
		guestProfile.setPersonalDetails(personalDetails);
		return this;
	}

	public BuilderBookReservation setRandomPersonalDetails() {
		guestProfile.getPersonalDetails().setLastName(RandomHelper.getRandomAlphaString(10));
		guestProfile.getPersonalDetails().setFirstName(RandomHelper.getRandomAlphaString(10));
		guestProfile.getPersonalDetails().setMiddleInitial(RandomHelper.getRandomAlphaString(10));
		return this;
	}

	public BuilderBookReservation setRandomRateSnapshots(int noOfNights, String ratePlanName, String roomTypeCode,
			LocalDate arrivalDate) {
		Set<RateSnapshot> rateSnapshots = new HashSet<>();
		for (int i = 0; i < noOfNights; i++) {
			RateSnapshot rateSnapshot = new FullDayRateSnapshot();
			rateSnapshot.setRoomTypeCode(roomTypeCode);
			rateSnapshot.setRatePlanName(ratePlanName);
			rateSnapshot.setDate(arrivalDate.plusDays(i));
			rateSnapshots.add(rateSnapshot);
		}
		this.reservationCreateRequest.setRateSnapshots(rateSnapshots);
		return this;
	}

	public BuilderBookReservation setRandomRateSnapshots(int noOfNights, RatePlanSummary ratePlanDetail,
			RoomType roomType, LocalDate arrivalDate) {
		Set<RateSnapshot> rateSnapshots = new HashSet<>();
		for (int i = 0; i < noOfNights; i++) {
			RateSnapshot rateSnapshot = new FullDayRateSnapshot();
			rateSnapshot.setRoomTypeName(roomType.getName());
			rateSnapshot.setRoomTypeCode(roomType.getTypeCode());
			rateSnapshot.setRatePlanName(ratePlanDetail.getName());
			rateSnapshot.setRatePlanCode(ratePlanDetail.getCode());
			rateSnapshot.setDate(arrivalDate.plusDays(i));
			rateSnapshots.add(rateSnapshot);
		}
		this.reservationCreateRequest.setRateSnapshots(rateSnapshots);
		return this;
	}
	
	public BuilderBookReservation setRandomRateSnapshots(int noOfNights, RatePlanDetail ratePlanDetail,
			RoomType roomType, LocalDate arrivalDate) {
		Set<RateSnapshot> rateSnapshots = new HashSet<>();
		for (int i = 0; i < noOfNights; i++) {
			RateSnapshot rateSnapshot = new FullDayRateSnapshot();
			rateSnapshot.setRoomTypeName(roomType.getName());
			rateSnapshot.setRoomTypeCode(roomType.getTypeCode());
			rateSnapshot.setRatePlanName(ratePlanDetail.getName());
			rateSnapshot.setRatePlanCode(ratePlanDetail.getCode());
			rateSnapshot.setDate(arrivalDate.plusDays(i));
			rateSnapshots.add(rateSnapshot);
		}
		this.reservationCreateRequest.setRateSnapshots(rateSnapshots);
		return this;
	}

	public BuilderBookReservation setRandomPhoneNumber() {
		Phone phone = new Phone();
		phone.setNumber(RandomHelper.getRandomAlphaNumericString(10));
		addPhoneDetails(phone);
		return this;
	}

	public BuilderBookReservation addAddressDetails(Address address) {
		AddressDetails addressDetails = guestProfile.getAddressDetails();
		if (addressDetails == null) {
			addressDetails = new AddressDetails();
		}
		List<Address> addresses = addressDetails.getAddresses();
		if (addresses == null) {
			addresses = new ArrayList<Address>();
		}
		addresses.add(address);
		addressDetails.setAddresses(addresses);
		guestProfile.setAddressDetails(addressDetails);
		return this;
	}

	public BuilderBookReservation addPhoneDetails(Phone phone) {
		PhoneDetails phoneDetails = new PhoneDetails();
		List<Phone> phones = new ArrayList<Phone>();
		phones.add(phone);
		phoneDetails.setPhones(phones);
		guestProfile.setPhoneDetails(phoneDetails);
		return this;
	}

	public BuilderBookReservation addEmailDetails(Email email) {
		EmailDetails emailDetails = new EmailDetails();
		List<Email> emails = new ArrayList<Email>();
		emails.add(email);
		emailDetails.setEmailAddresses(emails);
		guestProfile.setEmailDetails(emailDetails);
		return this;
	}

	public BuilderBookReservation setRateSnapshots(Set<RateSnapshot> rateSnapshots) {
		reservationCreateRequest.setRateSnapshots(rateSnapshots);
		return this;
	}

	public BuilderBookReservation setTrackingInfo(TrackingInfo trackingInfo, boolean saveToProfile) {
		reservationCreateRequest.setTrackingInfo(trackingInfo);
		return this;
	}

	public BuilderBookReservation addTravelAgent(String travelAgentSearchString) {
		travelAgents.add(travelAgentSearchString);
		return this;
	}

	public BuilderBookReservation addThirdPartyConfirmations(ThirdPartyConfirmation thirdPartyConfirmation) {
		Set<ThirdPartyConfirmation> thirdPartyConfirmations = reservationCreateRequest.getThirdPartyConfirmation();
		if (thirdPartyConfirmations == null) {
			thirdPartyConfirmations = new HashSet<ThirdPartyConfirmation>();
		}
		thirdPartyConfirmations.add(thirdPartyConfirmation);
		reservationCreateRequest.setThirdPartyConfirmation(thirdPartyConfirmations);
		return this;
	}

	public BuilderBookReservation addRecurringCharge(List<RecurringCharge> recurringCharges, LocalDate postDate,
			String reason) {
		this.recurringCharges = recurringCharges;
		this.recurringChargeReason = reason;
		this.recurringChargePostDate = postDate;
		return this;
	}

	public BuilderBookReservation performCheckAvailability() {
		recurringChargeCheckAvailabilty = true;
		return this;
	}

	public BuilderBookReservation performRecurringChargeOverride(String overrideUsername, String overridePassword) {
		recurringChargeOverrideUsername = overrideUsername;
		recurringChargeOverridePassword = overridePassword;
		return this;
	}

	public BuilderBookReservation addPaymentMethod(String paymentMethod, String swipeData, String directBillAccount) {
		paymentMethods.add(new PaymentMethod(paymentMethod, swipeData, directBillAccount));
		return this;
	}

	public BuilderBookReservation setDepositDetails(String depositPaymentMethod, String depositAmount) {
		deposit = true;
		this.depositPaymentMethod = depositPaymentMethod;
		this.depositAmount = depositAmount;
		return this;
	}

	public BuilderBookReservation setDepositPaymentMethod(String depositPaymentMethod) {
		deposit = true;
		this.depositPaymentMethod = depositPaymentMethod;
		return this;
	}

	public ReservationCreateRequest getReservationCreateRequest() {
		return this.reservationCreateRequest;
	}

	public GuestProfile getGuestProfile() {
		return this.guestProfile;
	}

	public BuilderBookReservation setGuestSearchString(String guestProfileSearchString) {
		this.guestProfileSearchString = guestProfileSearchString;
		return this;
	}

	public class PaymentMethod {
		public String paymentMethodName;
		public String swipeData;
		public String directBillAccount;

		public PaymentMethod(String paymentMethodName, String swipeData, String directBillAccount) {
			this.paymentMethodName = paymentMethodName;
			this.swipeData = swipeData;
			this.directBillAccount = directBillAccount;
		}
	}

	public BuilderBookReservation build() {
		LocalDate arrivalDate = getArrivalDate();
		POBookAReservationPage.searchAndSetRates(reservationCreateRequest, arrivalDate);
		Selenide.sleep(4000);
		PFBookAReservationPage.clickButtonBook();
		if (guestProfileSearchString != null) {
			POBookAReservationModal.searchForExistingGuest(guestProfileSearchString);
		} else {
			POBookAReservationModal.addNewGuest(this.guestProfile);
		}

		POBookAReservationModal.addTrackingInfo(reservationCreateRequest.getTrackingInfo(), trackingInfoSaveToProfile);
		POBookAReservationModal.addTravelAgentDetails(travelAgents);
		POBookAReservationModal.addThirdPartyConfirmationDetails(reservationCreateRequest.getThirdPartyConfirmation());
		POBookAReservationModal.addReservtionAlias(reservationCreateRequest.getReservationAlias());

		// Add RecurringCharges
		if (recurringCharges != null) {
			POBookAReservationModal.openAddRecurringChargeModal();
			new BuilderAddRecurringCharge(recurringCharges).setReason(this.recurringChargeReason)
					.setCheckAvailability(recurringChargeCheckAvailabilty)
					.setOverrideUserCredentials(recurringChargeOverrideUsername, recurringChargeOverridePassword)
					.setPostDate(recurringChargePostDate).build();
		}

		int i = 0;
		for (PaymentMethod paymentMethod : paymentMethods) {
			POBookAReservationModal.addPaymentMethod(i, paymentMethod.paymentMethodName, paymentMethod.swipeData,
					paymentMethod.directBillAccount);
			i++;
		}
		if (deposit){
			POBookAReservationModal.setupDeposit(depositPaymentMethod, depositAmount);}
		POBookAReservationModal.bookReservation(reservationCreateRequest.getWalkIn());
		return this;
	}

	private LocalDate getArrivalDate() {
		LocalDate arrivalDate = null;
		Iterator i = reservationCreateRequest.getRateSnapshots().iterator();
		while (i.hasNext()) {
			RateSnapshot rateSnapshot = (RateSnapshot) i.next();
			if (arrivalDate == null) {
				arrivalDate = rateSnapshot.getDate();
			}

			if (rateSnapshot.getDate().isBefore(arrivalDate)) {
				arrivalDate = rateSnapshot.getDate();
			}
		}
		return arrivalDate;
	}
}