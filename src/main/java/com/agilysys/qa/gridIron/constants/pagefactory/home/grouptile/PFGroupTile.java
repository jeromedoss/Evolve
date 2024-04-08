package com.agilysys.qa.gridIron.constants.pagefactory.home.grouptile;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.constants.locators.home.GroupTile;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFGroupTile {

	public static void checkVisibilityTabGroup() {
		$(GroupTile.TAB_GROUP).shouldBe(Condition.visible);
	}

	public static void clickTabGroup() {
		Selenide.sleep(2000);
		click(GroupTile.TAB_GROUP);
	}
}
