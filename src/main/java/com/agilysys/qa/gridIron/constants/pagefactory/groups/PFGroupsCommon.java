package com.agilysys.qa.gridIron.constants.pagefactory.groups;

import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupsCommon.*;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupsCommon.BUTTON_ALERT_CLOSE;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupsCommon.TAB_FOLIOS_ROUTING_RULES;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupsCommon.TAB_ROOM_BLOCKS;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFGroupsCommon extends PageFactoryBase {

	public static void clickButtonSave() {
		click(BUTTON_SAVE);//.hover();
	//	$(BUTTON_SAVE).shouldBe(Condition.exist).hover();
		Selenide.sleep(3000);
	}

	public static void clickButtonCloseAlert() {
		click(BUTTON_ALERT_CLOSE);
		Selenide.sleep(3000);
	}

	public static void checkTabFoliosRoutingRules() {
		$(TAB_FOLIOS_ROUTING_RULES).shouldBe(Condition.enabled).shouldBe(Condition.visible);
		Selenide.sleep(3000);
	}

	public static void clickTabFoliosRoutingRules() {
		click(TAB_FOLIOS_ROUTING_RULES);
		Selenide.sleep(3000);
	}

	public static void clickTabRoomBlocks() {
		Selenide.executeJavaScript("window.scrollTo(0, 0);");
		click(TAB_ROOM_BLOCKS);
		Selenide.sleep(3000);
	}

	public static void closeAlert() {

		click(ROOM_BLOCK_ALERT);
		Selenide.sleep(3000);
	}

	public static void clickTabReservations() {
		click(TAB_RESERVATIONS);
		Selenide.sleep(3000);
	}
}