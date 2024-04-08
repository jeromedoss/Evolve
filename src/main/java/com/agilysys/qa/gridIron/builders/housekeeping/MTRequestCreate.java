package com.agilysys.qa.gridIron.builders.housekeeping;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsRequestServiceDialog;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTMainSection;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;

/*
 * *Author - Harish Baskaran - 2018
 */
public class MTRequestCreate extends PageFactoryBase {

	String Service = null;
	String Name = null;
	String Role = null;

	public MTRequestCreate(String Service, String LastName, String FirstName, String Role) {
		this.Service = Service;
		this.Name = LastName + ", " + FirstName + " - ";
		this.Role = Role;

	}

	public void CreateWithoutSchedule(String Building, String Room) {

		click(HeaderDropDowns.NAV_ROOM_MGMT);
		click(HeaderDropDowns.NAV_REQUEST_SERVICE);

		click(LocatorsRequestServiceDialog.DROPDOWN_AREA_SELECT);
		click(LocatorsRequestServiceDialog.DROPDOWN_AREA_OPTION_ROOM);


		$(LocatorsRequestServiceDialog.INPUT_ROOM_NO).sendKeys(Room);
		click(LocatorsRequestServiceDialog.HEADER_MODAL);

		click(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_SELECT);
		click(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_OPTION_MT_SELECT);

		click(LocatorsRequestServiceDialog.DROPDOWN_SERVICE_SELECT);
		$$(LocatorsRequestServiceDialog.DROPDOWN_LIST_SERVICE).findBy(Condition.text(Service));

		click(LocatorsRequestServiceDialog.ASSIGN_RADIO);
		$(LocatorsRequestServiceDialog.STAFF_NAME_SELECT).setValue(Name + Role);
		click(LocatorsRequestServiceDialog.BUTTON_DONE);

	}

	public void CreateWithSchedule(String Building, String Room) {

		$(LocatorsMTMainSection.LABEL_ROOM_MT_REQUESTS).shouldBe(Condition.visible);

		$(HeaderDropDowns.NAV_ROOM_MGMT);

		$(HeaderDropDowns.NAV_REQUEST_SERVICE);

		$(LocatorsRequestServiceDialog.DROPDOWN_AREA_SELECT);
		$(LocatorsRequestServiceDialog.DROPDOWN_AREA_OPTION_ROOM);


		$(LocatorsRequestServiceDialog.INPUT_ROOM_NO).sendKeys(Room);
		$(LocatorsRequestServiceDialog.HEADER_MODAL);

		$(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_SELECT);
		$(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_OPTION_MT_SELECT);

		$(LocatorsRequestServiceDialog.DROPDOWN_SERVICE_SELECT);
		$$(LocatorsRequestServiceDialog.DROPDOWN_LIST_SERVICE).findBy(Condition.text(Service));

		$(LocatorsRequestServiceDialog.SET_SCHEDULE_RADIO);

		$(LocatorsRequestServiceDialog.ASSIGN_RADIO);

		$(LocatorsRequestServiceDialog.STAFF_NAME_SELECT).setValue(Name + Role);

		$(LocatorsRequestServiceDialog.BUTTON_DONE);
	}
}
