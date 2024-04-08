package com.agilysys.qa.gridIron.builders.reservation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsModifyStay;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
/*
 * *Author - Harish Baskaran - 2018
 */
public class NightAudit {

	String ConformationNo = null;

	public NightAudit(String ConformationNo) {
		this.ConformationNo = ConformationNo;
	}

	public void SelectAndClick() {

		int i = 1;

		Selenide.sleep(5000);

		List<String> actualTexts = $$(LocatorsModifyStay.ARRIVAL_ERROR_LIST).texts();

		for (String actual : actualTexts) {

			if (ConformationNo.equalsIgnoreCase(actual)) {

				String path = LocatorsModifyStay.SELECT_ARRIVAL_RES + i + LocatorsModifyStay.ARRIVAL_NO_SHOW;

				click(By.xpath(path));

				Set<String> handles = getWebDriver().getWindowHandles();
				List<String> tabs = new ArrayList<String>(handles);
				switchTo().window(tabs.get(1));
				// Selenide.switchTo().window(2);

			}
			i++;
		}

	}

}
