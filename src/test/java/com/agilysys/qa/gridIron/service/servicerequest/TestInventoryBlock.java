package com.agilysys.qa.gridIron.service.servicerequest;

import com.agilysys.common.model.statuses.RoomInventoryStatus;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.property.model.InventoryBlock;
import com.agilysys.pms.property.model.Room;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.pageobject.housekeeping.POInventoryBlock;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.PMSHelper;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.VerifyInventoryBlock;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.agilysys.qa.constants.EndPoints;

import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestInventoryBlock extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;
	String tenantId = null;
	String propertyId = null;
	Room room = null;
	LocalDate propertyDate;
	
	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();
		
		String propertycode = RandomHelper.getRandomAlphaString(5);
		masterObject = new CreatePopulatedProperty().createProperty("StayAutomation " + tenant.getTenantCode(),
				tenant.getTenantCode(), propertycode, propertycode)
				.createBuilding()
				.createRooms(1)
				.create();
		new PMSHelper().enableIDTech(masterObject.getProperty());
		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());
		new LoginApplication().Login(masterObject);
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {
		open(EndPoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);
		PFSearchTile.waitForSearchPageToLoad();
		room  = new CreateRoom().create(masterObject.getRoomTypes().get(0), masterObject.getBuilding(), masterObject.getProperty());
		PFHeaderDropDowns.navigateToInventoryBlocks();
	}
	
	@TestCase(id = "RST-4749")
	@Test
	public void testCreateInventoryBlockOOO() {
		InventoryBlock inventoryBlock = new InventoryBlock();
		inventoryBlock.setStartDate(propertyDate.plusDays(1));
		inventoryBlock.setEndDate(propertyDate.plusDays(3));
		inventoryBlock.setReason(RandomHelper.getRandomAlphaNumericString(10));
		POInventoryBlock.createInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OUT_OF_ORDER, inventoryBlock);
		VerifyInventoryBlock.verifyInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OUT_OF_ORDER, inventoryBlock);
	}
	
	@TestCase(id = "RST-4750")
	@Test
	public void testCreateInventoryBlockOTM() {
		InventoryBlock inventoryBlock = new InventoryBlock();
		inventoryBlock.setStartDate(propertyDate.plusDays(1));
		inventoryBlock.setEndDate(propertyDate.plusDays(3));
		inventoryBlock.setReason(RandomHelper.getRandomAlphaNumericString(10));
		POInventoryBlock.createInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OFF_THE_MARKET, inventoryBlock);
		VerifyInventoryBlock.verifyInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OFF_THE_MARKET, inventoryBlock);
	}
	
	@TestCase(id = "RST-4751")
	@Test
	public void testCreateInventoryBlockHold() {
		InventoryBlock inventoryBlock = new InventoryBlock();
		inventoryBlock.setStartDate(propertyDate.plusDays(1));
		inventoryBlock.setEndDate(propertyDate.plusDays(3));
		inventoryBlock.setReason(RandomHelper.getRandomAlphaNumericString(10));
		POInventoryBlock.createInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.HOLD, inventoryBlock);
		VerifyInventoryBlock.verifyInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.HOLD, inventoryBlock);
	}
	
	@TestCase(id = "RST-1885")
	@Test
	public void testCreateInventoryBlockWithoutEndDate() {
		InventoryBlock inventoryBlock = new InventoryBlock();
		inventoryBlock.setStartDate(propertyDate.plusDays(1));		
		inventoryBlock.setReason(RandomHelper.getRandomAlphaNumericString(10));
		POInventoryBlock.createInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.HOLD, inventoryBlock);		
		VerifyInventoryBlock.verifyInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.HOLD, inventoryBlock);
	}
	
	@TestCase(id = "RST-4760")
	@Test
	public void testUpdateInventoryBlock() {
		InventoryBlock inventoryBlock = new InventoryBlock();
		inventoryBlock.setStartDate(propertyDate.plusDays(1));
		inventoryBlock.setEndDate(propertyDate.plusDays(3));
		inventoryBlock.setReason(RandomHelper.getRandomAlphaNumericString(10));
		POInventoryBlock.createInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OFF_THE_MARKET, inventoryBlock);
		
		inventoryBlock.setStartDate(propertyDate.plusDays(2));
		inventoryBlock.setEndDate(propertyDate.plusDays(5));
		inventoryBlock.setReason(RandomHelper.getRandomAlphaNumericString(10));
		POInventoryBlock.editInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OUT_OF_ORDER, inventoryBlock);
		VerifyInventoryBlock.verifyInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OUT_OF_ORDER, inventoryBlock);
	}
	
	@TestCase(id = "RST-4756")
	@Test
	public void testClearInventoryBlock() {
		InventoryBlock inventoryBlock = new InventoryBlock();
		inventoryBlock.setStartDate(propertyDate.plusDays(1));
		inventoryBlock.setEndDate(propertyDate.plusDays(3));
		inventoryBlock.setReason(RandomHelper.getRandomAlphaNumericString(10));
		POInventoryBlock.createInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OFF_THE_MARKET, inventoryBlock);
		PFHeaderDropDowns.navigateToHouseKeeping();
		PFHeaderDropDowns.navigateToInventoryBlocks();
		POInventoryBlock.clearInventoryBlock(room.getRoomNumber());
	}
}