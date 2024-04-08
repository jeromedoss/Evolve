package com.agilysys.qa.gridIron.constants.pagefactory.shared.folios;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsTransferModal;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFTransferModal {

	public static void selectSearchForAccount() {
		Selenide.sleep(2000);
		click(LocatorsTransferModal.DROPDOWN_DESTINATION);
		Selenide.sleep(2000);
		click(LocatorsTransferModal.DROPDOWN_VALUE_SEARCH_FOR_ACCOUNT);
	}

	public static void selectTransferMatch(String Input) {
		$(LocatorsTransferModal.INPUT_NAME).setValue(Input);
		click(LocatorsTransferModal.SELECT_MATCH_TRANSFER);
	}

	public static void setTextAreaReason() {
		click(LocatorsTransferModal.TEXTAREA_REASON);
		$(LocatorsTransferModal.TEXTAREA_REASON).sendKeys("Reason");
	}

	public static void clickButtonSave() {
		click(LocatorsTransferModal.BUTTON_SAVE);
	}

	public static void selectARTransferMatch(String Input) {
		Selenide.sleep(2000);
		$(LocatorsTransferModal.AR_DROPDOWN_DESTINATION).setValue(Input);
		click(LocatorsTransferModal.SELECT_MATCH_TRANSFER);
	}

	public static void setARTextAreaReason() {
		click(LocatorsTransferModal.AR_TEXTAREA_REASON);
		$(LocatorsTransferModal.AR_TEXTAREA_REASON).sendKeys("Reason");
	}

	public static void clickARButtonSave() {
		click(LocatorsTransferModal.AR_BUTTON_SAVE);
	}
}
