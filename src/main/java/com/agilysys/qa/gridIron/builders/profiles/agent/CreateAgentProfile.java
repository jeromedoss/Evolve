package com.agilysys.qa.gridIron.builders.profiles.agent;

import static com.agilysys.qa.gridIron.pageobject.home.profiletile.CreateNewProfile.stepClickCreateTAFromMain;
import static com.agilysys.qa.gridIron.pageobject.profiles.agent.POAgentProfilePage.stepCreateWithSave;
import static com.agilysys.qa.gridIron.pageobject.profiles.agent.POAgentProfilePage.stepCreateWithSaveExit;

import com.agilysys.qa.gridIron.utils.RandomHelper;

/*
 * *Author - Harish Baskaran - 2018
 */
public class CreateAgentProfile {

	String agentName = null;
	String IATA = RandomHelper.getRandomNumericString(5);

	public CreateAgentProfile(String agentName) {
		this.agentName = agentName;
	}

	public void flowCreateUsingSave() {

		stepClickCreateTAFromMain();
		stepCreateWithSave(agentName, IATA);

	}

	public void flowCreateUsingSaveExit() {

		stepClickCreateTAFromMain();
		stepCreateWithSaveExit(agentName, IATA);
	}

	public void flowCreateUsingSaveWithIATA(String IATA) {

		stepClickCreateTAFromMain();
		stepCreateWithSave(agentName, IATA);

	}

	public void flowCreateUsingSaveExitWithIATA(String IATA) {

		stepClickCreateTAFromMain();
		stepCreateWithSaveExit(agentName, IATA);

	}

}
