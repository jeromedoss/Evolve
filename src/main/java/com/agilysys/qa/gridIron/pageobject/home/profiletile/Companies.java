package com.agilysys.qa.gridIron.pageobject.home.profiletile;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFCompanies.clickRadioCompany;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFCompanies.clickSpecificCompany;

/*
 * *Author - Harish Baskaran - 2018
 */
public class Companies {

	public void search(String companyName) {

		clickRadioCompany();
		clickSpecificCompany(companyName);

	}
}
