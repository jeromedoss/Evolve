package com.agilysys.qa.gridIron.constants.pagefactory.profiles.agent;

import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.home.ProfileTile.MODAL_BUTTON_CANCEL;
import static com.agilysys.qa.gridIron.constants.locators.profiles.agent.LocatorsAgentProfilePage.BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.agent.LocatorsAgentProfilePage.BUTTON_SAVE_EXIT;
import static com.agilysys.qa.gridIron.constants.locators.profiles.agent.LocatorsAgentProfilePage.INPUT_IATA;
import static com.agilysys.qa.gridIron.constants.locators.profiles.agent.LocatorsAgentProfilePage.INPUT_NAME;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class PFAgentProfilePage {

	public static void setInputName(String agentName) {
		$(INPUT_NAME).setValue(agentName);
	}

	public static void setInputIATA(String IATA) {
		$(INPUT_IATA).setValue(IATA);
	}

	public static void clickButtonSave() {
		click(BUTTON_SAVE);
	}

	public static void clickButtonSaveExit() {
		click(BUTTON_SAVE_EXIT);
		
		if ($(HEADER_MODAL).is(Condition.visible))
			click(MODAL_BUTTON_CANCEL);
	}
}
