package com.agilysys.qa.gridIron.pageobject.reservation;

import java.util.List;
import java.util.Set;

import com.agilysys.pms.reservation.model.ThirdPartyConfirmation;
import com.agilysys.common.model.TrackingInfo;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationSummary;
import com.agilysys.qa.gridIron.pageobject.housekeeping.PORequestServiceModal;
import com.agilysys.qa.gridIron.pageobject.housekeeping.PORequestServiceModal;

public class POReservationSummary {

	public static void addVIPStatus(String vipStatus) {
		PFReservationSummary.clickVIPBadge();
		PFReservationSummary.selectVIP(vipStatus);
		PFReservationSummary.clickSaveVIPStatus();
	}

	public static void removeVIPStatus() {
		PFReservationSummary.clickVIPBadge();
		PFReservationSummary.clickSaveVIPStatus();
	}

	public static void addLateCheckout(String hours, String minutes) {
		PFReservationSummary.clickLateCheckOutBadge();
		PFReservationSummary.setLateCheckoutMinutes(minutes);
		PFReservationSummary.setLateCheckoutHours(hours);
		PFReservationSummary.clickSaveLateCheckout();
	}

	public static void removeLateCheckout() {
		PFReservationSummary.clickLateCheckOutBadge();
		PFReservationSummary.clickRemoveLateCheckout();
	}

	public static void toggleNRG() {
		PFReservationSummary.clickNRGBadge();
	}

	public static void addPet(String petType, String petName) {
		PFReservationSummary.clickPetBadge();
		PFReservationSummary.selectPet(petType);
		PFReservationSummary.typePetName(petName);
		PFReservationSummary.savePetModal();
	}

	public static void removePet() {
		PFReservationSummary.clickPetBadge();
		PFReservationSummary.deletePet();
		PFReservationSummary.savePetModal();
		PFReservationSummary.clickConfirmDelete();
	}

	public static void updateReservationAlias(String reservationAlias) {
		PFReservationSummary.typeReservationAlias(reservationAlias);

	}

	public static void updateTrackingInfo(TrackingInfo trackingInfo) {
		PFReservationSummary.typeSourceOfBusiness(trackingInfo.getSourceCode());
		PFReservationSummary.selectGuestType(trackingInfo.getGuestType());
		PFReservationSummary.typeMarketSegment(trackingInfo.getSegmentCode());

	}

	public static void updateTravelAgent(List<String> travelAgents) {
		travelAgents.forEach((travelAgent) -> {
			PFReservationSummary.clickAddTravelAgent();
			PFReservationSummary.typeTravelAgent(travelAgent);
		});
	}
	
	public static void createServiceRequest(PORequestServiceModal poRequestServiceModal) {
		PFReservationSummary.clickSVCBadge();
		PFReservationSummary.clickCreateRequestService();
		poRequestServiceModal.createServiceRequest();
	}

	public static void cancelServiceRequest(PORequestServiceModal poRequestServiceModal) {
		PFReservationSummary.clickSVCBadge();
		PFReservationSummary.clickCreateRequestService();
		poRequestServiceModal.cancelServiceRequest();
	}
	
	public static void openEditServiceRequestModal(String serviceCode, String roomNo) {
		PFReservationSummary.clickSVCBadge();
		PFReservationSummary.clickServiceRequestEditLink(serviceCode, roomNo);
	}

	public static void addThirdPartyConfirmation(Set<ThirdPartyConfirmation> thirdPartyConfirmations) {
		thirdPartyConfirmations.forEach((thirdPartyConfirmation) -> {
			PFReservationSummary.clickAddThirdPartyConfirmation();
			PFReservationSummary.setThirdPartySource(thirdPartyConfirmation.getConfirmationName());
			PFReservationSummary.setThirdPartyConfirmationNumber(thirdPartyConfirmation.getConfirmationNumber());
		});
	}

	public static void saveReservation() {
		PFReservationSummary.saveAndWaitForActionToComplete();
	}
}
