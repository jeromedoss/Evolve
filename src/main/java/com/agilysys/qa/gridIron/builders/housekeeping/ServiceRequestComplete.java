package com.agilysys.qa.gridIron.builders.housekeeping;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsAssignedServicesSection;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsHkMain;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsHkPrintSection;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsRequestServiceDialog;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTAssignedServicesSection;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTMainSection;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
/*
 * *Author - Harish Baskaran - 2018
 */
public class ServiceRequestComplete {

	public void HKComplete() {

		click(HeaderDropDowns.NAV_ROOM_MGMT);

		click(HeaderDropDowns.NAV_HOUSE_KEEPING);

		Selenide.refresh();

		$(LocatorsAssignedServicesSection.DROPDOWN_SERVICE).shouldBe(Condition.visible);

		int size = $$(LocatorsAssignedServicesSection.LIST_SERVICE_ID).size();

		for (int i = 1; i <= size; i++) {

			Selenide.executeJavaScript("window.scrollBy(0,250);");

			click(By.xpath("//*[@data-qa-id='fuLFBtS'][" + i + "]//td[@data-qa-id='g5mTRJi']/a"));

			click(LocatorsRequestServiceDialog.STATUS_DD);

			click(LocatorsRequestServiceDialog.STATUS_COMPLETED);

			click(LocatorsRequestServiceDialog.BUTTON_DONE);

			Selenide.sleep(3000);
		}

	}

	public boolean HKStatusCheck() {

		click(HeaderDropDowns.NAV_ROOM_MGMT);

		click(HeaderDropDowns.NAV_HOUSE_KEEPING);

		click(LocatorsHkMain.NAV_PRINT);

		click(LocatorsHkPrintSection.BUTTON_EXPAND);

		Selenide.sleep(2000);

		List<String> texts = $$(LocatorsHkPrintSection.LIST_STATUS).texts();

		int success = 0;

		for (String element : texts) {

			if ("COM".equalsIgnoreCase(element)) {
				success++;
			}
		}

		int size = $$(LocatorsHkPrintSection.LIST_STATUS).size();

		if (success == size) {

			return true;

		}

		return false;

	}

	public boolean MTStatusCheck() {

		List<String> texts = $$(LocatorsMTAssignedServicesSection.LIST_SERVICES).texts();

		int success = 0;

		for (String element : texts) {

			if ("COM".equalsIgnoreCase(element)) {
				success++;
			}
		}

		int size = $$(LocatorsMTAssignedServicesSection.LIST_SERVICES).size();

		if (success == size) {

			return true;

		}

		return false;

	}

	public void MTComplete() {

		click(HeaderDropDowns.NAV_ROOM_MGMT);

		click(HeaderDropDowns.NAV_MAINTENANCE);

		click(LocatorsMTMainSection.LABEL_ROOM_MT_REQUESTS);

		$(LocatorsMTAssignedServicesSection.LIST_SERVICES).shouldBe(Condition.visible);

		int size = $$(LocatorsMTAssignedServicesSection.LIST_SERVICES).size();

		for (int i = 1; i <= size; i++) {

			$(By.xpath("//*[@data-qa-id='fpsjz8X'][" + i + "]//td[@data-qa-id='fpsjz8k']/a"));

			click(LocatorsRequestServiceDialog.STATUS_DD);

			click(LocatorsRequestServiceDialog.STATUS_COMPLETED);

			click(LocatorsRequestServiceDialog.BUTTON_DONE);

			Selenide.sleep(3000);
		}

	}
}
