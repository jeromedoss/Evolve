package com.agilysys.qa.gridIron.validations;

import com.agilysys.common.model.statuses.RoomInventoryStatus;
import com.agilysys.pms.property.model.InventoryBlock;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsInventoryBlock;
import com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFInventoryBlock;
import com.agilysys.qa.gridIron.utils.HelperMethods;

public class VerifyInventoryBlock extends ValidationBase{
	
	public static void verifyInventoryBlock(String roomNumber, RoomInventoryStatus.CanonicalId inventoryStatus, InventoryBlock inventoryBlock){
		PFInventoryBlock.clickInventoryBlockEditLink(roomNumber);
		verifyElementValue(HelperMethods.convertDateToString(inventoryBlock.getStartDate()), LocatorsInventoryBlock.INPUT_INVENTORY_BLOCK_START_DATE);
		verifyElementValue(HelperMethods.convertDateToString(inventoryBlock.getEndDate()), LocatorsInventoryBlock.INPUT_INVENTORY_BLOCK_END_DATE);
		verifyElementValue(inventoryBlock.getReason(), LocatorsInventoryBlock.INPUT_INVENTORY_BLOCK_COMMENTS);
		
		String blockType = null;
		if(inventoryStatus == RoomInventoryStatus.CanonicalId.OUT_OF_ORDER){
			blockType = "OOO - Out of Order";
		}else if(inventoryStatus == RoomInventoryStatus.CanonicalId.OFF_THE_MARKET){
			blockType = "OTM - Off the Market";
		}else if(inventoryStatus == RoomInventoryStatus.CanonicalId.HOLD){
			blockType = "HOLD - On Hold";
		}
		verifyElementValue(blockType, LocatorsInventoryBlock.DROPDOWN_INVENTORY_STATUS);
	}

}
