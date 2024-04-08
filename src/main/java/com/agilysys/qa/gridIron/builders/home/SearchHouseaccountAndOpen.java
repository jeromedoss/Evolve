package com.agilysys.qa.gridIron.builders.home;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.clickTabAccounts;
import static com.agilysys.qa.gridIron.pageobject.home.accountstile.POHouseAccountPage.stepSearchAccount;

import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;

/*
 * *Author - Harish Baskaran - 2018
 */
public class SearchHouseaccountAndOpen {

	static String AccountName = null;

	@SuppressWarnings("static-access")
	public SearchHouseaccountAndOpen(String AccountName) {

		this.AccountName = AccountName;

	}

	public void searchByName() {

		clickTabAccounts();

		new POSearchInMainPage().search(AccountName);

		stepSearchAccount(AccountName);

	}
}
