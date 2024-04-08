package com.agilysys.qa.gridIron.pageobject.reservation;

import static com.codeborne.selenide.Selenide.$;

import java.math.BigDecimal;
import java.time.Duration;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;

import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.qa.gridIron.constants.locators.reservation.estimatedcharges.LocatorsEstimatedChargesSection;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFAdjustRateModal;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationEstimatedCharges;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class POEstimatedCharges {
	
	public static void adjustNightlyRoomCharge(RateSnapshot rateSnapshot, boolean isPackage){
		PFReservationEstimatedCharges.expandEstimatedCharges();
		
		if(isPackage){
			PFReservationEstimatedCharges.openPackageRoomChargeAdjustModal(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode());			
		}else{
			PFReservationEstimatedCharges.clickAdjustRate(rateSnapshot.getDate(), rateSnapshot.getRoomTypeCode());
		}
		PFAdjustRateModal.adjustRate(rateSnapshot);
		Selenide.sleep(1000);
		$(By.xpath(LocatorsEstimatedChargesSection.getEstimatedChargesLineItemSection(rateSnapshot.getDate()))).should(Condition.appear, Duration
			.ofMillis(Configuration.timeout));
	}
	
	public static void adjustCharge(LocalDate date, String itemName, BigDecimal price, String rateChangeComment){
		PFReservationEstimatedCharges.expandEstimatedCharges();
		PFReservationEstimatedCharges.clickAdjustRate(date, itemName);
		PFAdjustRateModal.typeAdjustCharge(price.toString());
		PFAdjustRateModal.typeAdjustComment(rateChangeComment);
		PFAdjustRateModal.saveAdjustModal();
		Selenide.sleep(1000);
		$(By.xpath(LocatorsEstimatedChargesSection.getEstimatedChargesLineItemSection(date))).should(Condition.appear, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void deleteCharge(LocalDate date, String itemName){
		PFReservationEstimatedCharges.expandEstimatedCharges();
		PFReservationEstimatedCharges.clickDeleteCharge(date, itemName);
		Selenide.sleep(1000);
		$(By.xpath(LocatorsEstimatedChargesSection.getEstimatedChargesLineItemSection(date))).should(Condition.appear, Duration.ofMillis(Configuration.timeout));
	}
	
	public static BigDecimal getEstimatedDueAtCheckOut(){
		PFReservationEstimatedCharges.expandEstimatedCharges();
		return PFReservationEstimatedCharges.getEstimatedDueAtCheckOut();
	}
	
	

}
