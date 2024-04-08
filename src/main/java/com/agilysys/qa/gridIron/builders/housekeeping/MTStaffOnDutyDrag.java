package com.agilysys.qa.gridIron.builders.housekeeping;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTMainSection;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTStaffSection;
import com.codeborne.selenide.Condition;

/*
 * *Author - Harish Baskaran - 2018
 */
public class MTStaffOnDutyDrag {

	public void DragAndDrop() throws Exception {

		click(LocatorsMTMainSection.NAV_STAFF);

		click(LocatorsMTStaffSection.CHECKBOX_SELECTALL_STAFF_OFF);

		WebElement source = $(LocatorsMTStaffSection.LIST_STAFF_OFF).shouldBe(Condition.visible);

		WebElement target = $(LocatorsMTStaffSection.AREA_DRAG_AND_DROP).shouldBe(Condition.visible);

		Point fromLocation = source.getLocation();
		Point toLocation = target.getLocation();

		Robot robot = new Robot();

		Dimension fromSize = source.getSize();
		Dimension toSize = target.getSize();
		// Make Mouse coordinate center of element
		toLocation.x += toSize.width / 2;
		toLocation.y += toSize.height / 2 + 130;
		fromLocation.x += fromSize.width / 2;
		fromLocation.y += fromSize.height / 2 + 130;

		// Move mouse to drag from location
		robot.mouseMove(fromLocation.x, fromLocation.y);
		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

		// Drag events require more than one movement to register
		// Just appearing at destination doesn't work so move halfway first
		robot.mouseMove(((toLocation.x - fromLocation.x) / 2) + fromLocation.x,
				((toLocation.y - fromLocation.y) / 2) + fromLocation.y);

		robot.delay(3000);
		// Move to final position
		robot.mouseMove(toLocation.x, toLocation.y);
		robot.delay(3000);
		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

	}

}
