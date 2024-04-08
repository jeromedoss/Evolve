
package com.agilysys.qa.gridIron.pageobject.booking;

import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookAReservationPage.clickButtonBook;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookAReservationPage.clickButtonCheckRates;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookAReservationPage.clickSelectRate;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.filters.PFFilters.selectRoomTypeFilter;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.filters.PFRatesFilter.selectPackageFilter;
import static com.agilysys.qa.gridIron.constants.pagefactory.booking.filters.PFRatesFilter.selectRatePlanFilter;

import java.util.Set;

import org.joda.time.LocalDate;

import com.agilysys.common.model.rate.CompInfo;
import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookAReservationPage;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFAdjustRateModal;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POBookAReservationPage {

	public static void stepOpenBookReservationModal() {

		clickButtonCheckRates();
		clickSelectRate();
		clickButtonBook();

	}

	public static void stepOpenAndSelectRoomtype(String roomType) {

		clickButtonCheckRates();
		selectRoomTypeFilter(roomType);
		clickSelectRate();
		clickButtonBook();

	}

	public static void stepOpenAndSelectRatePlan(String ratePlan) {

		clickButtonCheckRates();
		selectRatePlanFilter(ratePlan);
		clickSelectRate();
		clickButtonBook();

	}

	public static void stepOpenAndSelectPackage(String packageName) {
		clickButtonCheckRates();
		selectPackageFilter(packageName);
		clickSelectRate();
		clickButtonBook();
	}

	public static void searchAndSetRates(ReservationCreateRequest reservationCreateRequest, LocalDate arrivalDate){
		Set<RateSnapshot> rateSnapshots = reservationCreateRequest.getRateSnapshots();
		PFHeaderDropDowns.navigateToBookReservation();

		PFBookAReservationPage.setArrivalDate(arrivalDate);
		LocalDate departureDate = arrivalDate.plusDays(rateSnapshots.size());
		PFBookAReservationPage.setDepartureDate(departureDate);
		//PFBookAReservationPage.typeNoOfNights(String.valueOf(rateSnapshots.size()));
		PFBookAReservationPage.typeAdultCount(String.valueOf(reservationCreateRequest.getNumberOfAdults()));
		PFBookAReservationPage.typeChildCount(String.valueOf(reservationCreateRequest.getNumberOfChildren()));
		PFBookAReservationPage.clickButtonCheckRates();
		Selenide.sleep(5000);

		rateSnapshots.forEach((RateSnapshot rateSnapshot)->{
			//same
			PFBookAReservationPage.selectRate(arrivalDate, rateSnapshot.getDate(), rateSnapshot.getRatePlanName(),
					rateSnapshot.getRoomTypeCode());
			if(rateSnapshot.getOverriddenComment() != null){
				Selenide.sleep(500);
				PFBookAReservationPage.clickAdjustRate(arrivalDate, rateSnapshot.getDate());
				PFAdjustRateModal.typeAdjustRate(rateSnapshot.getPreOccupancyRate().toString());
				PFAdjustRateModal.typeAdjustComment(rateSnapshot.getOverriddenComment());
				CompInfo compInfo = rateSnapshot.getCompInfo();
				if(compInfo !=null){
					if(compInfo.getCompCertificateNumber() != null){
						//Redeem Comp
						PFAdjustRateModal.setCompCertificateNumber(compInfo.getCompCertificateNumber());
					}else{
						//Issue new comp or set comp reason which does not require any certificate
						PFAdjustRateModal.selectCompReason(compInfo.getReason(), compInfo.isCertificateRequired());
					}
				}
				PFAdjustRateModal.saveAdjustModalAndConfirm();
			}
		});
	}
}
