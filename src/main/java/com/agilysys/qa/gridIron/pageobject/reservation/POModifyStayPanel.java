package com.agilysys.qa.gridIron.pageobject.reservation;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import com.codeborne.selenide.Condition;
import org.joda.time.LocalDate;

import com.agilysys.common.model.rate.CompInfo;
import com.agilysys.pms.property.model.RoomType;
import com.agilysys.pms.rates.model.RatePlanSummary;
import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsModifyStayPage;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFAdjustRateModal;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFModifyStayModal;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class POModifyStayPanel extends PageFactoryBase {
	
	public static void modifyStay(Set<RateSnapshot> rateSnapshots, boolean keepCurrentRates, LocalDate arrivalDate, LocalDate departureDate){

		PFModifyStayModal.clickExpandReservationDetails();

		if(keepCurrentRates){
			PFModifyStayModal.clickKeepCurrentRates();
		}		
		
		if(arrivalDate != null){
			PFModifyStayModal.setArrivalDate(arrivalDate);
		}
		
		if(departureDate!=null){
			PFModifyStayModal.setDepartureDate(departureDate);
		}
		//1
		if(rateSnapshots != null){
			rateSnapshots.forEach((RateSnapshot rateSnapshot)->{
				PFModifyStayModal.selectRate(arrivalDate, rateSnapshot.getDate(), rateSnapshot.getRoomTypeName(), rateSnapshot.getRatePlanCode());
				if(rateSnapshot.getOverriddenComment() != null){
					Selenide.sleep(500);
					PFModifyStayModal.clickAdjustRate(arrivalDate, rateSnapshot.getDate());
					PFAdjustRateModal.adjustRate(rateSnapshot);
				}
			});	
		}
		PFModifyStayModal.clickBook();		
	}
	
	
	public static void modifyStay(Set<RateSnapshot> rateSnapshots, boolean keepCurrentRates, LocalDate arrivalDate){

		PFModifyStayModal.clickExpandReservationDetails();

		if(keepCurrentRates){
			PFModifyStayModal.clickKeepCurrentRates();
		}		
		if(rateSnapshots != null){
			rateSnapshots.forEach((RateSnapshot rateSnapshot)->{
				PFModifyStayModal.selectRate(arrivalDate, rateSnapshot.getDate(), rateSnapshot.getRoomTypeName(), rateSnapshot.getRatePlanCode());
				if(rateSnapshot.getOverriddenComment() != null){
					Selenide.sleep(500);
					PFModifyStayModal.clickAdjustRate(arrivalDate, rateSnapshot.getDate());
					PFAdjustRateModal.adjustRate(rateSnapshot);
				}
			});	
		}
		PFModifyStayModal.clickBook();		
	}
	
	public static void confirmModifyStay(){
		PFModifyStayModal.clickConfirmModifyStayYes();
		waitForModifyToStayToComplete();
	}
	
	public static void waitForModifyToStayToComplete(){
		//PageFactoryBase.waitForElementToDisappear(LocatorsModifyStayPage.BUTTON_BOOK, Configuration.timeout);
		waitForElementToDisappear(LocatorsModifyStayPage.BUTTON_BOOK, Configuration.timeout);
	}

}
