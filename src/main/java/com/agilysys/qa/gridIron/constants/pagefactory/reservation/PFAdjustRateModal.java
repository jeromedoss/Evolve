package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.common.model.rate.CompInfo;
import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFAdjustRateModal extends PageFactoryBase{

	public static void typeAdjustRate(String adjustRate){
		$(LocatorsBookAReservationPage.INPUT_ADJUST_RATE_AMOUNT).clear();
		$(LocatorsBookAReservationPage.INPUT_ADJUST_RATE_AMOUNT).sendKeys(adjustRate);
	}
	
	public static void typeAdjustCharge(String adjustCharge){
		$(LocatorsBookAReservationPage.INPUT_ADJUST_CHARGE_AMOUNT).clear();
		$(LocatorsBookAReservationPage.INPUT_ADJUST_CHARGE_AMOUNT).sendKeys(adjustCharge);
	}

	public static void clickApplyToRemainingDates(boolean applyToRemainingDates){
		if(applyToRemainingDates){
			click(LocatorsBookAReservationPage.BUTTON_ADJUST_RATE_APPLY_REMAINING_DATE);			
		}
	}

	public static void typeAdjustComment(String adjustRateComment){
		$(LocatorsBookAReservationPage.TEXTAREA_ADJUST_RATE_COMMENTS).sendKeys(adjustRateComment);
	}

	public static void selectCompReason(String compReason, boolean isCertificateRequired){
		Selenide.sleep(2000);
		if($$(LocatorsBookAReservationPage.LINK_COMP_I_DONT_HAVE_CERTIFICATE).size()>0){
			click(LocatorsBookAReservationPage.LINK_COMP_I_DONT_HAVE_CERTIFICATE);
		}
		selectByText(LocatorsBookAReservationPage.DROPDOWN_ADJUST_RATE_COMP_REASON,LocatorsBookAReservationPage.LIST_ADJUST_RATE_COMP_REASON,
				compReason);
		if(isCertificateRequired)
		{
			click(LocatorsBookAReservationPage.LINK_ISSUE_CERTIFICATE);
		}

	}

	public static void setCompCertificateNumber(String compcertificateNumber){
		type(LocatorsBookAReservationPage.INPUT_COMP_CERTIFICATE_NUMBER, compcertificateNumber);
	}

	public static void saveAdjustModalAndConfirm(){
		click(LocatorsBookAReservationPage.BUTTON_ADJUST_SAVE);
		click(LocatorsBookAReservationPage.BUTTON_ADJUST_RATE_CONFIRM_YES);
	}
	
	public static void saveAdjustModal(){
		click(LocatorsBookAReservationPage.BUTTON_ADJUST_SAVE);
	}
	
	public static void adjustRate(RateSnapshot rateSnapshot){
		typeAdjustRate(rateSnapshot.getPreOccupancyRate().toString());
		typeAdjustComment(rateSnapshot.getOverriddenComment());
		CompInfo compInfo = rateSnapshot.getCompInfo(); 
		if(compInfo !=null){
			if(compInfo.getCompCertificateNumber() != null){
				//Redeem Comp
				setCompCertificateNumber(compInfo.getCompCertificateNumber());
			}else{
				//Issue new comp or set comp reason which does not require any certificate
				selectCompReason(compInfo.getReason(), compInfo.isCertificateRequired());
			}					
		}
		saveAdjustModalAndConfirm();
		waitForElementToDisappear(LocatorsBookAReservationPage.BUTTON_ADJUST_SAVE, Configuration.timeout);
	}

}
