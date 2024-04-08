package com.agilysys.qa.gridIron.constants.pagefactory.shared.folios;

import static com.codeborne.selenide.Selenide.$;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import org.joda.time.LocalDate;

import com.agilysys.common.model.FrequencyType;
import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAddRecurringChargeModal;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

public class PFAddRecurringChargeModal extends PageFactoryBase{
	
	public static void typeSearch(String itemName){
		type(LocatorsAddRecurringChargeModal.INPUT_SEARCH, itemName);
	}
	
	public static void typeItemChargeAmount(String itemName, BigDecimal chargeAmount){
		clearAndType(LocatorsAddRecurringChargeModal.getItemCharge(itemName), chargeAmount.toPlainString());
	}
	
	public static void typeItemQuantity(String itemName, int quantity){
		clearAndType(LocatorsAddRecurringChargeModal.getItemQuantity(itemName), String.valueOf(quantity));
	}
	
	public static void clickItemAdd(String itemName){
		click(LocatorsAddRecurringChargeModal.getItemAddlink(itemName));
	}
	
	public static void moveFromItemSelectScreen(){
		click(LocatorsAddRecurringChargeModal.BUTTON_SELECT_ITEMS_STEP_NEXT);
		$(LocatorsAddRecurringChargeModal.BUTTON_BACK).should(Condition.enabled, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clickSelectedItem(String itemName){
		click(LocatorsAddRecurringChargeModal.getSelectItem(itemName));
	}
	
	public static void typeFrequencyStartDate(LocalDate startDate){
		typeDate(LocatorsAddRecurringChargeModal.INPUT_FREQUENCY_START_DATE, startDate);
	}
	
	public static void typeFrequencyEndDate(LocalDate endDate){
		typeDate(LocatorsAddRecurringChargeModal.INPUT_FREQUENCY_END_DATE, endDate);
	}
	
	public static void setFrequencyType(FrequencyType frequencyType){
		if(frequencyType == null){
			return;
		}else if(frequencyType == FrequencyType.EVERY_N){
			click(LocatorsAddRecurringChargeModal.RADIO_FREQUENCY_EVERY_N);
		}else if(frequencyType == FrequencyType.FIRST){
			click(LocatorsAddRecurringChargeModal.RADIO_FREQUENCY_FIRST_NIGHT);
		}else if(frequencyType == FrequencyType.DAYS_OF_WEEK){
			click(LocatorsAddRecurringChargeModal.RADIO_FREQUENCY_DAYS_OF_WEEK);
		}
	}
	
	public static void typeNoOfNights(String noOfNights){
		//click(LocatorsAddRecurringChargeModal.RADIO_FREQUENCY_EVERY_N);
		clearAndType(LocatorsAddRecurringChargeModal.INPUT_EVERY_N_NIGHT, noOfNights);
	}
	
	public static void setDaysOfWeek(List<String> days){
		if(days == null){
			return;
		}		
		days.forEach((day)->{
			click(LocatorsAddRecurringChargeModal.getDaysOfWeek(day.substring(0, 3)));
		});
	}
	
	public static void clickCheckAvailability(){
		click(LocatorsAddRecurringChargeModal.BUTTON_CHECK_AVAILABILITY);
	}
	public static void moveFromFrequencySetup(){
		$(LocatorsAddRecurringChargeModal.BUTTON_FREQUENCY_STEP_NEXT).shouldBe(Condition.enabled);
		click(LocatorsAddRecurringChargeModal.BUTTON_FREQUENCY_STEP_NEXT);
		$(LocatorsAddRecurringChargeModal.ADD_CHARGE).should(Condition.enabled, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void typeReason(String reason){
		type(LocatorsAddRecurringChargeModal.TEXTAREA_REASON, reason);
	}
	
	public static void clickAddCharge(){
		click(LocatorsAddRecurringChargeModal.ADD_CHARGE);
		$(LocatorsAddRecurringChargeModal.HEADER_MODAL).shouldNotBe(Condition.exist);
	}
}
