package com.agilysys.qa.gridIron.pageobject.groups;

import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupRoomBlocksSection.*;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupsCommon.clickTabRoomBlocks;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupBookingSection;
import com.agilysys.qa.gridIron.constants.locators.groups.LocatorsRoomBlocksSection;
import com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupsCommon;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POGroupRoomBlocksSection extends PageFactoryBase {

	public static void stepCreateRoomBlocks(String roomType) {

		clickTabRoomBlocks();
		$(LocatorsGroupBookingSection.TEXT_ROLLING_RELEASE).scrollIntoView(true);

		selectByRoomType(roomType);
		clickTabSetup();

		$(LocatorsGroupBookingSection.TEXT_ROLLING_RELEASE).scrollIntoView(true);

		setInputGrossRow("1");

		setInputRoomRowRate("50");
		setInputRoomRowBlocks("1");

		actions().sendKeys(Keys.ESCAPE);

	}
	public static void stepChangeRateInRoomBlocks() {

		clickTabRoomBlocks();
		$(LocatorsGroupBookingSection.TEXT_ROLLING_RELEASE).scrollIntoView(true);
		clickTabSetup();
		$(LocatorsGroupBookingSection.TEXT_ROLLING_RELEASE).scrollIntoView(true);
		setInputRoomRowRate("150");
		click(LocatorsRoomBlocksSection.SAVE_CHANGES);
		scrollElementToVisibleArea($(byText("Make Reservation")));
		PFGroupsCommon.clickTabReservations();
	}
}
