package com.agilysys.qa.gridIron.builders.housekeeping;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsRequestServiceDialog;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class HKGRequestCreate extends PageFactoryBase {

	String Service = null;
	String Name = null;
	String Role = null;

	public HKGRequestCreate(String Service, String LastName, String FirstName, String Role) {
		this.Service = Service;
		this.Name = LastName + ", " + FirstName + " - ";
		this.Role = Role;

	}

	public void Create(String Building, String Room) {

		click(HeaderDropDowns.NAV_ROOM_MGMT);

		click(HeaderDropDowns.NAV_REQUEST_SERVICE);

		click(LocatorsRequestServiceDialog.DROPDOWN_AREA_SELECT);
		click(LocatorsRequestServiceDialog.DROPDOWN_AREA_OPTION_ROOM);

		$(LocatorsRequestServiceDialog.INPUT_ROOM_NO).sendKeys(Room);
		click(LocatorsRequestServiceDialog.HEADER_MODAL);

		click(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_SELECT);
		click(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_OPTION_HKG_SELECT);
		click(LocatorsRequestServiceDialog.HEADER_MODAL);

		click(LocatorsRequestServiceDialog.DROPDOWN_SERVICE_SELECT);
		$$(LocatorsRequestServiceDialog.DROPDOWN_LIST_SERVICE).findBy(Condition.text(Service)).click();
		
		Selenide.executeJavaScript("window.scrollTo(0, 250)");

		click(LocatorsRequestServiceDialog.ASSIGN_RADIO);

		click(LocatorsRequestServiceDialog.STAFF_NAME_SELECT);

		Selenide.actions().click().sendKeys(Name + Role).build().perform();

		click(LocatorsRequestServiceDialog.BUTTON_DONE);

	}
}
