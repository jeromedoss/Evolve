package com.agilysys.qa.gridIron.pageobject.home.profiletile;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFGuests.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFGuests.clickLinkCreateProfile;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFGuests.clickModalButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFGuests.clickModalRadioAgent;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFGuests.clickModalRadioCompany;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFGuests.clickModalRadioGuest;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFGuests.clickTabProfiles;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

import com.agilysys.qa.gridIron.constants.locators.profiles.company.LocatorsCompanyProfilePage;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;


/*
 * *Author - Harish Baskaran - 2018
 */
public class CreateNewProfile {

	private static void create(String state) {

		PFHeaderDropDowns.navigateToSearch();
		clickTabProfiles();
		clickLinkCreateProfile();
		//	clickHeaderModal();

		Selenide.sleep(1000);

		switch (state) {

			case "GUEST":
				clickModalRadioGuest();
				break;

			case "COMPANY":
				clickModalRadioCompany();
				break;

			case "AGENT":
				clickModalRadioAgent();
				break;
		}

		sleep(500);
		clickModalButtonSave();
	}

	public static void stepClickCreateGuestFromMain() {

		create("GUEST");

	}

	public static void stepClickCreateCompanyFromMain() {

		create("COMPANY");
		$(LocatorsCompanyProfilePage.INPUT_NAME).shouldBe(Condition.visible);

	}

	public static void stepClickCreateTAFromMain() {

		create("AGENT");

	}
}
