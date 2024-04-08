package com.agilysys.qa.gridIron.builders.houseaccounts;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.clickButtonDone;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.clickCheckboxTypeMeetingRoom;

import com.agilysys.qa.gridIron.pageobject.home.accountstile.POHouseAccountPage;

/*
 * *Author - Harish Baskaran - 2018
 */
public class CreateHouseAccount {

	String AccountName = null;
	String Type = null;

	public CreateHouseAccount(String AccountName, String Type) {
		this.AccountName = AccountName;
		this.Type = Type;
	}

	public void createHAWithoutAddingCharge() {

		POHouseAccountPage HA = new POHouseAccountPage(AccountName, Type);

		clickCheckboxTypeMeetingRoom();
		HA.stepOpenModalToCreate();
		HA.stepModalFirstPage();
		clickButtonDone();

	}

}
