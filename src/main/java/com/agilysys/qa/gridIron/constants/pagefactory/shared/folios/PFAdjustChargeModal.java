package com.agilysys.qa.gridIron.constants.pagefactory.shared.folios;

import static com.codeborne.selenide.Selenide.$;

import java.math.BigDecimal;
import java.time.Duration;

import com.agilysys.pms.account.model.LineItemAdjustment.AdjustmentType;
import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsAdjustChargeModal;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFAdjustChargeModal extends PageFactoryBase {
	
	public static void selectAdjustmentType(AdjustmentType adjustmentType){
		if(adjustmentType == null){
			return;
		}
		
		if(adjustmentType == adjustmentType.CORRECTION){			
			click(LocatorsAdjustChargeModal.RADIO_POSTED_INCORRECTLY);
		}else if(adjustmentType == adjustmentType.ADJUSTMENT){
			click(LocatorsAdjustChargeModal.RADIO_GUEST_SATISFICATION);
		}
	}
	
	public static void setPercentage(BigDecimal percentage){
		Selenide.sleep(2000);
		click(LocatorsAdjustChargeModal.RADIO_PERCENTAGE);
		clearAndType(LocatorsAdjustChargeModal.INPUT_PERCENTAGE, percentage.toString());
	}
	
	public static void setDollarAmount(BigDecimal dollarAmount){
		Selenide.sleep(2000);
		click(LocatorsAdjustChargeModal.RADIO_DOLLAR_AMOUNT);
		clearAndType(LocatorsAdjustChargeModal.INPUT_DOLLAR_AMOUNT, dollarAmount.toString());
	}
	
	public static void setChargeAmount(BigDecimal chargeAmount){		
		clearAndType(LocatorsAdjustChargeModal.INPUT_TOTAL, chargeAmount.toString());
	}
	
	public static void selectAdjustMethod(String adjustMethod){
//		waitForElementToLoad(LocatorsAdjustChargeModal.INPUT_TOTAL, Configuration.timeout);
		waitForElementToLoad(LocatorsAdjustChargeModal.RADIO_POSTED_INCORRECTLY, Configuration.timeout);
		selectAdjustmentType(AdjustmentType.CORRECTION);
		selectByText(LocatorsAdjustChargeModal.DROPDOWN_ADJUST_METHOD, LocatorsAdjustChargeModal.DROPDOWN_OPTIONS_ADJUST_METHOD, adjustMethod);
	}
	
	public static void typeReason(String reason){	
		type(LocatorsAdjustChargeModal.TEXTAREA_ADJUST_REASON, reason);
	}
	
	public static void saveAdjustCharge(){
		click(LocatorsAdjustChargeModal.BUTTON_SAVE);
		waitForElementToDisappear(LocatorsAdjustChargeModal.BUTTON_SAVE, Configuration.timeout);
	}	
}
