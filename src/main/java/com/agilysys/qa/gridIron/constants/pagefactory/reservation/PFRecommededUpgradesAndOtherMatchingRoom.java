package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsCurrentlySelectedRoom;
import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsOtherMatchingRooms;
import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsRecommendedUpgrades;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import com.agilysys.qa.helpers.ThreadHelper;

public class PFRecommededUpgradesAndOtherMatchingRoom extends PageFactoryBase{
	
	public static void upgradeRoom(){
		click(LocatorsRecommendedUpgrades.TEXT_UPGRADE_ROOM_TYPE_CODE);
		$(LocatorsCurrentlySelectedRoom.BUTTON_RELEASE).shouldBe(Condition.enabled);
	}
	
	public static String getRecommendedUpgradeRoomNumber(){
		return $(LocatorsRecommendedUpgrades.TEXT_UPGRADE_ROOM_TYPE_NUMBER).getText();
	}
	
	public static void clickOtherMatchingRoom(int rowNo){
		click(LocatorsOtherMatchingRooms.getOtherMatchingRoomNumber(rowNo));		
	}

	public static void clickOtherMatchingRow(int rowNo) {
		jsClick(LocatorsOtherMatchingRooms.getOtherMatchingRoomRow(rowNo));
		ThreadHelper.sleep(3000);
		waitForElementToLoad(LocatorsCurrentlySelectedRoom.LINK_RELEASE_ROOM, Configuration.timeout);
		$(LocatorsCurrentlySelectedRoom.LINK_RELEASE_ROOM).should(Condition.enabled,  Duration.ofMillis(Configuration.timeout));
	}
	
	public static String getOtherMatchingRoomNumber(int rowNo){
		waitForElementToLoad(LocatorsOtherMatchingRooms.getOtherMatchingRoomNumber(rowNo), Configuration.timeout);
		return $(LocatorsOtherMatchingRooms.getOtherMatchingRoomNumber(rowNo)).getText();
	}

}
