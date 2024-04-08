package com.agilysys.qa.gridIron.pageobject.home.accountstile;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.clickButtonCreate;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.clickLinkCreateAccount;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.clickTabAccounts;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.selectListHouseAccounts;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.setDropdownCategory;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFHouseAccountPage.setInputName;

import com.agilysys.qa.gridIron.constants.locators.home.AccountsTile;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POHouseAccountPage {

	String accountName = null;
	String Type = null;

	AccountsTile PO = new AccountsTile();

	public POHouseAccountPage() {

	}

	public POHouseAccountPage(String accountName, String Type) {
		this.accountName = accountName;
		this.Type = Type;
	}

	public void stepModalFirstPage() {

		clickHeaderModal();
		setInputName(accountName);
		setDropdownCategory(Type);
		clickHeaderModal();
		clickButtonCreate();

	}

	public void stepOpenModalToCreate() {

		clickTabAccounts();
		clickLinkCreateAccount();

	}

	public static void stepSearchAccount(String AccountName) {

		selectListHouseAccounts(AccountName);

	}
}
