package com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest;

import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.BUTTON_ADD_ID;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.BUTTON_DELETE_ID;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.BUTTON_EXPIRATION_DATE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.DROPDOWN_ID_TYPE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_ID_NUMBER;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_ISSUING_AGENCY;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.LIST_ID_TYPE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.SIDEPANE_ID;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.joda.time.LocalDate;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;

public class PFGuestProfileIdentity extends PageFactoryBase {

	public int identityIndex = 0;

	public PFGuestProfileIdentity(int identityIndex) {
		if (identityIndex > 0) {
			this.identityIndex = identityIndex;
		}
	}

	public static void clickSidepaneId() {
		click(SIDEPANE_ID);
	}

	public void clickAddId() {
		click(BUTTON_ADD_ID);
		click(BUTTON_ADD_ID);
	}

	public void selectIdType(String idType) {
		selectByText(DROPDOWN_ID_TYPE, identityIndex, LIST_ID_TYPE, idType);
	}

	public void typeIdNumber(String idNumber) {
		type(INPUT_ID_NUMBER, identityIndex, idNumber);
	}

	public void setExpirateDate(LocalDate localDate) {
		click($$(BUTTON_EXPIRATION_DATE).get(identityIndex));
	}

	public void typeIssuingAgency(String issuingAgency) {
		type(INPUT_ISSUING_AGENCY, identityIndex, issuingAgency);
	}

	public void clickDeleteId() {
		click($$(BUTTON_DELETE_ID).get(identityIndex));
	}
}