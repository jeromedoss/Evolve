package com.agilysys.qa.gridIron.pageobject.shared;

import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddACreditModal.clickDone;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddACreditModal.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddACreditModal.selectItem;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddACreditModal.*;

import java.math.BigDecimal;

import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POAddCreditModal {

	public static void addCredit(String itemName) {
		clickHeaderModal();
		typeSearch(itemName);
		selectItem(itemName);
		clickDone();
	}
	
	public static void addCredit(String itemName, String source, BigDecimal amount, String folio, String reason) {
		clickHeaderModal();
		typeSearch(itemName);
		selectItem(itemName);		
		selectSource(source);
		selectFolio(folio);
		typeAmount(amount);
		typeReason(reason);
		clickDone();
	}
}
