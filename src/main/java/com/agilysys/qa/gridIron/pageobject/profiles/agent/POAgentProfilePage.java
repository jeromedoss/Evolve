package com.agilysys.qa.gridIron.pageobject.profiles.agent;

import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.agent.PFAgentProfilePage.clickButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.agent.PFAgentProfilePage.clickButtonSaveExit;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.agent.PFAgentProfilePage.setInputIATA;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.agent.PFAgentProfilePage.setInputName;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POAgentProfilePage {

	public static void stepCreateWithSave(String agentName, String IATA) {

		setMandatory(agentName, IATA);
		clickButtonSave();

	}

	public static void stepCreateWithSaveExit(String agentName, String IATA) {

		setMandatory(agentName, IATA);
		clickButtonSaveExit();

	}

	private static void setMandatory(String agentName, String IATA) {

		setInputName(agentName);
		setInputIATA(IATA);
	}
}
