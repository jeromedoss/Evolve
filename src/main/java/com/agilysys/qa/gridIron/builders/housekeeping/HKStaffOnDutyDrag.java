package com.agilysys.qa.gridIron.builders.housekeeping;

import static com.codeborne.selenide.Selenide.$;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsHkMain;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.staff.LocatorsStaffPageSection;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTStaffSection;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import com.agilysys.qa.helpers.ThreadHelper;

/*
 * *Author - Harish Baskaran - 2018
 */
public class HKStaffOnDutyDrag extends PageFactoryBase {

	public void DragAndDrop() throws Exception {

		click(LocatorsHkMain.NAV_STAFF);

		click(LocatorsStaffPageSection.CHECKBOX_SELECTALL_STAFF_OFF);


		WebElement source = $(LocatorsMTStaffSection.DRAG_STAFF_OFF_SOURCE);
		WebElement target = $(LocatorsMTStaffSection.DRAG_STAFF_ON_DESTINATION);
		 ((SelenideElement) source).is(Condition.visible);
		 ((SelenideElement) target).is(Condition.visible);
		int x_source = source.getLocation().getX() + 100;
		int y_source = source.getLocation().getY() + 100;

		int x1_source = target.getLocation().getX() + 500;
		int y1_source = target.getLocation().getY() + 500;

		Robot dragAndDrop = new Robot();

		dragAndDrop.mouseMove(x_source, y_source);
		dragAndDrop.mousePress(InputEvent.BUTTON1_MASK);

		dragAndDrop.mouseMove(x1_source, y1_source);
		dragAndDrop.mouseMove(x1_source, y1_source);

		ThreadHelper.sleep(2000);
		dragAndDrop.mousePress(InputEvent.BUTTON1_MASK);

		ThreadHelper.sleep(2000);
		dragAndDrop.mouseRelease(InputEvent.BUTTON1_MASK);

	}

}
