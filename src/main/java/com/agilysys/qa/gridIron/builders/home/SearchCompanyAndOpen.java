package com.agilysys.qa.gridIron.builders.home;

import com.agilysys.qa.gridIron.constants.pagefactory.home.profiletile.PFProfiletile;
import com.agilysys.qa.gridIron.pageobject.home.profiletile.Companies;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;

/*
 * *Author - Harish Baskaran - 2018
 */
public class SearchCompanyAndOpen {

	String CompanyName = null;

	public SearchCompanyAndOpen(String CompanyName) {
		this.CompanyName = CompanyName;

	}

	public void searchByName() {

		PFProfiletile.clickTabProfiles();

		new POSearchInMainPage().search(CompanyName);

		new Companies().search(CompanyName);

	}
}
