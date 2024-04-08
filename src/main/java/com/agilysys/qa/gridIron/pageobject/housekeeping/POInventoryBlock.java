package com.agilysys.qa.gridIron.pageobject.housekeeping;

import com.agilysys.common.model.statuses.RoomInventoryStatus;
import com.agilysys.pms.property.model.InventoryBlock;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsInventoryBlock;
import com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFInventoryBlock;
import com.agilysys.qa.gridIron.validations.ValidationBase;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

public class POInventoryBlock {
	
	public static void createInventoryBlock(String roomNumber, RoomInventoryStatus.CanonicalId inventoryStatus, InventoryBlock inventoryBlock){
		PFInventoryBlock.clickRoomNumberCheckBox(roomNumber);
		PFInventoryBlock.clickNewInventoryBlock();
		PFInventoryBlock.selectInventoryBlockType(inventoryStatus);
		PFInventoryBlock.typeInventoryBlockStartDate(inventoryBlock.getStartDate());
		PFInventoryBlock.typeInventoryBlockEndDate(inventoryBlock.getEndDate());
		PFInventoryBlock.typeComment(inventoryBlock.getReason());
		PFInventoryBlock.clickSaveInventoryBlock();
		$(LocatorsInventoryBlock.getInventoryBlockLinkByRoomNumber(roomNumber)).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void clearInventoryBlock(String roomNumber){
		PFInventoryBlock.clickRoomNumberCheckBox(roomNumber);
		PFInventoryBlock.clickClearInventoryBlock();
		PFInventoryBlock.clickConfirmClearInventoryBlock();
		PFInventoryBlock.clickSaveChanges();
		PageFactoryBase.waitForElementToDisappear(LocatorsInventoryBlock.getInventoryBlockLinkByRoomNumber(roomNumber), Configuration.timeout);
	}
	
	public static void editInventoryBlock(String roomNumber, RoomInventoryStatus.CanonicalId inventoryStatus, InventoryBlock inventoryBlock){
		PFInventoryBlock.clickInventoryBlockEditLink(roomNumber);
		PFInventoryBlock.selectInventoryBlockType(inventoryStatus);
		PFInventoryBlock.typeInventoryBlockStartDate(inventoryBlock.getStartDate());
		PFInventoryBlock.typeInventoryBlockEndDate(inventoryBlock.getEndDate());
		PFInventoryBlock.typeComment(inventoryBlock.getReason());
		PFInventoryBlock.clickSaveInventoryBlock();
		$(LocatorsInventoryBlock.getInventoryBlockLinkByRoomNumber(roomNumber)).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	public static void verifyInventoryBlock(String roomNumber, RoomInventoryStatus.CanonicalId inventoryStatus, InventoryBlock inventoryBlock){
		
	}
}
