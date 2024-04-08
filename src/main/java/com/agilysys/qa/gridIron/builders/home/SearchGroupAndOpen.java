package com.agilysys.qa.gridIron.builders.home;

import static com.codeborne.selenide.Selenide.$;

import com.agilysys.pms.profile.model.Group;

import com.agilysys.qa.gridIron.constants.locators.home.GroupTile;
import com.agilysys.qa.gridIron.pageobject.home.grouptile.POGroupsPage;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class SearchGroupAndOpen extends PageFactoryBase {

	String GroupName = null;

	public SearchGroupAndOpen(String GroupName) {
		this.GroupName = GroupName;

	}
	
	public SearchGroupAndOpen(Group group) {
		this.GroupName = group.getGroupName();

	}

		public void searchByName() {
		//$("#my-button").scrollIntoView(false);


		//$(GroupTile.TAB_GROUP).vicks

		new POSearchInMainPage().search(GroupName);
		click((GroupTile.TAB_GROUP));

		POGroupsPage.selectGroup(GroupName);
	}

	public void searchByNameFutureDate(LocalDate arrivalDate) {
		Selenide.refresh();
		String date = String.format("%02d" , arrivalDate.getMonthOfYear())+"/"+String.format("%02d" , arrivalDate.getDayOfMonth())+"/"+String.format("%02d" , arrivalDate.getYear());
		$(GroupTile.GROUP_DATE).setValue(date);

		click(GroupTile.TAB_GROUP);

		new POSearchInMainPage().search(GroupName);

		POGroupsPage.selectGroup(GroupName);

	}
}
