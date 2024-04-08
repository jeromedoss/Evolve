package com.agilysys.qa.gridIron.constants.pagefactory.groups;

import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsRoomBlocksSection.*;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.LocatorsCompanyProfilePage.DROPDOWN_GUEST_TYPE;
import static com.agilysys.qa.gridIron.constants.locators.profiles.company.LocatorsCompanyProfilePage.DROPDOWN_LIST_GUEST_TYPE;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.selectByText;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFGroupRoomBlocksSection extends PageFactoryBase {

	public static void clickSelectRoomTypeSelect() {
		click(SELECT_ROOM_TYPE_SELECT);
	}

	public static void clickRoomTypeSelect() {
		click(ROOM_TYPE_SELECT);
	}

	public static void selectByRoomType(String value)
	{
		selectByText(SELECT_ROOM_TYPE_SELECT, LIST_ROOM_TYPE, value);
	}

	public static void clickTabSetup() {
		click(TAB_SETUP);
	}

	public static void setInputGrossRow(String value) {
		$(GROSS_ROW_INPUT).setValue(value);
	}

	public static void setInputNetRow(String value) {
		$(NET_ROW_INPUT).setValue(value);
	}

	public static void setInputRoomRowRate(String value) {
		$(ROOM_ROW_RATE).clear();
		$(ROOM_ROW_RATE).sendKeys(value);
	}

	public static void setInputRoomRowBlocks(String value) {
		click(ROOM_ROW_BLOCKS);
		$(ROOM_ROW_BLOCKS).clear();
		$(ROOM_ROW_BLOCKS).sendKeys(value);
		Selenide.sleep(2000);
	}

}
