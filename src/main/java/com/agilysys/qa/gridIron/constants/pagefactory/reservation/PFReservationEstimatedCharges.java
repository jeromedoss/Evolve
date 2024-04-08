package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.math.BigDecimal;
import java.time.Duration;

import org.joda.time.LocalDate;

import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsModifyStay;
import com.agilysys.qa.gridIron.constants.locators.reservation.estimatedcharges.LocatorsEstimatedChargesSection;
import com.agilysys.qa.gridIron.utils.HelperMethods;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFReservationEstimatedCharges extends PageFactoryBase{
	
	public static void expandEstimatedCharges(){
		expandSection(LocatorsEstimatedChargesSection.BUTTON_COLLAPSE);
		$(LocatorsEstimatedChargesSection.CHECKBOX_DO_NOT_DISCLOSE_RATE).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void loadEstimatedCharges(){
		$(LocatorsEstimatedChargesSection.LABEL_ESTIMATED_DUE_AT_CHECKOUT_SHORT).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void colloapseEstimatedCharges(){
		expandSection(LocatorsEstimatedChargesSection.BUTTON_COLLAPSE);
	}
	
	public static BigDecimal getEstimatedDueAtCheckOut(){
		return HelperMethods.convertToBigDecimal($(LocatorsEstimatedChargesSection.LABEL_ESTIMATED_DUE_AT_CHECKOUT).getText());
	}
	
	public static void clickRatePlan(LocalDate rateDate){
		click(LocatorsEstimatedChargesSection.getRatePlanLink(rateDate));
	}
	
	public static void expandPackage(LocalDate date){
		expandEstimatedCharges();
		openArrow(LocatorsEstimatedChargesSection.getPackageLineItemArrow(date));
		clickDoNotDisclose(date);
	}
	
	public static void clickDoNotDisclose(LocalDate date){
		Selenide.sleep(500);
		if($$(LocatorsEstimatedChargesSection.getPackageLineItemDoNotDiscloseRates(date)).size()>0){
			click(LocatorsEstimatedChargesSection.getPackageLineItemDoNotDiscloseRates(date));
		}
	}
	
	public static void clickAdjustRate(LocalDate date, String itemName){
		click(LocatorsEstimatedChargesSection.getLineItemChargeAmount(date, itemName));
	}
	
	public static void clickDeleteCharge(LocalDate date, String itemName){
		click(LocatorsEstimatedChargesSection.getLineItemDeleteIcon(date, itemName));
	}
	
	public static void openPackageRoomChargeAdjustModal(LocalDate date, String itemName){
		expandPackage(date);
		click(LocatorsEstimatedChargesSection.getPackageRoomChargeAmount(date, itemName));
	}
	
	public static void clickAddRecurringCharge(){
		click(LocatorsEstimatedChargesSection.BUTTON_ADD_RECURRING_CHARGE);
	}
}
