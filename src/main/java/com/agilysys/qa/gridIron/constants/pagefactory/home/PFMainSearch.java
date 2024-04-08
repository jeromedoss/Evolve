package com.agilysys.qa.gridIron.constants.pagefactory.home;

import static com.agilysys.qa.gridIron.constants.locators.home.GroupTile.LIST_GROUPS;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

public class PFMainSearch {

	public static void selectGroupFromSearchResult(String groupName) {

		$(LIST_GROUPS).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		$(LIST_GROUPS).scrollIntoView(false);
		click(LIST_GROUPS);
	}
}
