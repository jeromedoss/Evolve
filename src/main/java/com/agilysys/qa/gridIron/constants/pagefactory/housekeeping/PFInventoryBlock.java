package com.agilysys.qa.gridIron.constants.pagefactory.housekeeping;

import org.joda.time.LocalDate;

import com.agilysys.common.model.statuses.RoomInventoryStatus;
import com.agilysys.common.model.statuses.RoomInventoryStatus.CanonicalId;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsInventoryBlock;
import com.agilysys.qa.gridIron.utils.HelperMethods;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class PFInventoryBlock extends PageFactoryBase{

	public static void clickNewInventoryBlock(){
		click(LocatorsInventoryBlock.BUTTON_NEW);
	}
	
	public static void clickClearInventoryBlock(){
		click(LocatorsInventoryBlock.BUTTON_CLEAR);
	}
	
	public static void clickConfirmClearInventoryBlock(){
		click(LocatorsInventoryBlock.BUTTON_YES_CLEAR_INVENTORY_BLOCK);
	}
	
	public static void clickRoomNumberCheckBox(String roomNumber){
		click(LocatorsInventoryBlock.getCheckBoxByRoomNumber(roomNumber));
	}
	
	public static void typeInventoryBlockStartDate(LocalDate startDate){
		typeDate(LocatorsInventoryBlock.INPUT_INVENTORY_BLOCK_START_DATE, startDate);		
	}
	
	public static void typeInventoryBlockEndDate(LocalDate endDate){
		typeDate(LocatorsInventoryBlock.INPUT_INVENTORY_BLOCK_END_DATE, endDate);
	}
	
	public static void selectInventoryBlockType(CanonicalId inventoryStatus){
		String blockType = null;
		if(inventoryStatus == RoomInventoryStatus.CanonicalId.OUT_OF_ORDER){
			blockType = "OOO - Out of Order";
		}else if(inventoryStatus == RoomInventoryStatus.CanonicalId.OFF_THE_MARKET){
			blockType = "OTM - Off the Market";
		}else if(inventoryStatus == RoomInventoryStatus.CanonicalId.HOLD){
			blockType = "HOLD - On Hold";
		}
		selectByText(LocatorsInventoryBlock.DROPDOWN_INVENTORY_STATUS, LocatorsInventoryBlock.LIST_INVENTORY_STATUS, blockType);
	}
	
	public static void typeComment(String comment){
		clearAndType(LocatorsInventoryBlock.INPUT_INVENTORY_BLOCK_COMMENTS, comment);		
	}
	
	public static void clickSaveInventoryBlock(){
		click(LocatorsInventoryBlock.BUTTON_SAVE_INVENTORY_BLOCK);
		$(LocatorsInventoryBlock.BUTTON_SAVE_INVENTORY_BLOCK).shouldBe(Condition.disappear);
	}
	
	public static void clickSaveChanges(){
		click(LocatorsInventoryBlock.BUTTON_FOOTER_SAVE);
		$(LocatorsInventoryBlock.BUTTON_FOOTER_SAVE).shouldBe(Condition.disappear);
	}
	
	public static void clickInventoryBlockEditLink(String roomNumber){
		click(LocatorsInventoryBlock.getInventoryBlockLinkByRoomNumber(roomNumber));
	}
}