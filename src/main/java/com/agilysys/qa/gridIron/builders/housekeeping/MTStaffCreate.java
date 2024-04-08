package com.agilysys.qa.gridIron.builders.housekeeping;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.qa.gridIron.constants.locators.housekeeping.setup.LocatorsStaffSetupSection;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTMainSection;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class MTStaffCreate extends PageFactoryBase {

	private String FirstName = "First_" + RandomHelper.getRandomAlphaString(5);
	private String LastName = "Staff_" + RandomHelper.getRandomAlphaString(5);

	public MTStaffCreate() {
	}

	public MTStaffCreate(String LastName, String FirstName) {
		this.LastName = LastName;
		this.FirstName = FirstName;

	}

	public void CreateMTEStaff() {

		click(LocatorsMTMainSection.NAV_SETUP);

		AddStaff("MTE");
	}

	public void CreateMTSStaff() {

		click(LocatorsMTMainSection.NAV_SETUP);

		AddStaff("MTS");
	}

	private void AddStaff(String Role) {

		Selenide.sleep(2000);

		click(LocatorsStaffSetupSection.BUTTON_ADD_NEW);

		$(LocatorsStaffSetupSection.STAFF_LASTNAME).setValue(LastName);

		$(LocatorsStaffSetupSection.STAFF_FIRSTNAME).setValue(FirstName);

		click(LocatorsStaffSetupSection.DROPDOWN_ROLE);

		$$(LocatorsStaffSetupSection.DROPDOWN_LIST_ROLE).findBy(Condition.text(Role)).click();

		Selenide.sleep(2000);

		click(LocatorsStaffSetupSection.BUTTON_SAVE);

	}
}
