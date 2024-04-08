package com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.ar.noninvoice;

import static com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsNonInvoicedChargesSection.BUTTON_MORE;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsNonInvoicedChargesSection;

public class PFNonInvoicesSection {

	public static void clickButtonMore(){
		click(BUTTON_MORE);
	}
	
	public static void clickButtonAddACharge(){
	click(LocatorsNonInvoicedChargesSection.BUTTON_ADD_A_CHARGE);
	}
}
