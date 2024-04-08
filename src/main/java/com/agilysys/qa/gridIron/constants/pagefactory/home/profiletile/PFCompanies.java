package com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile;

import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.LIST_COMPANIES;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.RADIO_COMPANY;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;

public class PFCompanies {

	public static void clickRadioCompany() {
		click(RADIO_COMPANY);
	}

	public static void clickSpecificCompany(String companyName) {
		$$(LIST_COMPANIES).findBy(Condition.text(companyName)).click();
	}
}
