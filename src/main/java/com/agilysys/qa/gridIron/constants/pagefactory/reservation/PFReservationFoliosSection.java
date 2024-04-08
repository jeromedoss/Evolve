package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import com.agilysys.qa.gridIron.constants.locators.reservation.folios.LocatorsFoliosSection;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFReservationFoliosSection extends PageFactoryBase{


	public static void clickDefaultFolio(){
		Selenide.sleep(5000);
		click(LocatorsFoliosSection.ICON_DEFAULT_FOLIO);
		$(LocatorsFoliosSection.CHECKBOX_MAIN_SELECT).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clickFolioTab(String folioName){
		click(LocatorsFoliosSection.getFolioTabByName(folioName));
	}
	
	public static String getLineItemTotalWithoutCurrency(String lineItemName){		
		String total = $(LocatorsFoliosSection.getLineItemTotal(lineItemName)).getText();		
		return total.split("\\$")[1];
	}
	
	public static String getLineItemTotal(String lineItemName){
		String total = $(LocatorsFoliosSection.getLineItemTotal(lineItemName)).getText();		
		return total;
	}
	
	public static String getLineItemTotalByReason(String reason){
		//String total = $(LocatorsFoliosSection.getLineItemTotalByReason(reason)).getText();
		String total = $(LocatorsFoliosSection.getLineItemTotalByReason(reason)).should(Condition.visible, Duration.ofMillis(Configuration.timeout)).getText();
		return total;
	}
	
	public static void clickFolioLineItem(String lineItemName){
		scrollElementToVisibleArea($(LocatorsFoliosSection.getLineItemDescription(lineItemName)));
		Selenide.sleep(1000);
		click(LocatorsFoliosSection.getLineItemDescription(lineItemName));
	}	
}
