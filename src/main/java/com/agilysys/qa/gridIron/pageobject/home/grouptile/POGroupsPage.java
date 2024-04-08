package com.agilysys.qa.gridIron.pageobject.home.grouptile;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFMainSearch.selectGroupFromSearchResult;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POGroupsPage {

	public static void selectGroup(String groupName) {
		selectGroupFromSearchResult(groupName);
	}

}