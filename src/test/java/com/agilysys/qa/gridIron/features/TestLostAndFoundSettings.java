package com.agilysys.qa.gridIron.features;

import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.property.model.LostItem;
import com.agilysys.pms.property.model.StorageLocation;
import com.agilysys.qa.data.builder.lost.item.BuilderLostItem;
import com.agilysys.qa.data.builder.storage.location.BuilderStorageLocation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.settings.LostAndFoundPage;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.ValidateLostItem;
import com.agilysys.qa.gridIron.validations.ValidateStorageLocation;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns.navigateToAllSettings;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFSettingsPage.clickLinkLostFound;

/*
 * *Author - Harish Baskaran - Oct/2018
 */

public class TestLostAndFoundSettings extends StayUIWrappers {

	static String Input = com.agilysys.qa.gridIron.utils.RandomHelper.getRandomAlphaString(10);
	static String Code = com.agilysys.qa.gridIron.utils.RandomHelper.getRandomAlphaString(10);

	String tenantId = null;
	String propertyId = null;

	static MasterObject masterObject;

	@BeforeClass
	public void beforeClass() {

		masterObject = new MainDriver().getMasterObject();

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();

		Property property = new Property();
		property.setTenantId(tenantId);
		property.setPropertyId(propertyId);
		masterObject = new CreatePopulatedProperty().useExistingProperty(property).create();
		

		new LoginApplication().Login(masterObject);
	}

	@AfterClass
	public void afterclass() {
		//Selenide.closeWebDriver();

	}

	@BeforeMethod
	public void beforeMethod() {

		navigateToAllSettings();
		clickLinkLostFound();

	}

	@Test
	@TestCase(ids = { "RST-9302", "RST-9303" })
	public void testCreateTypeAndUpdateType() {

		LostItem lostItem = new BuilderLostItem(Code, Input).build();

		new LostAndFoundPage(lostItem).stepCreateActiveType();

		new ValidateLostItem().VerifyAll().Validate(lostItem);

		LostItem updateLostItem = new BuilderLostItem(Code, Input + "new").build();

		new LostAndFoundPage(updateLostItem).stepUpdateActiveType(lostItem.getName());

		new ValidateLostItem().VerifyAll().Validate(updateLostItem);
	}

	@Test
	@TestCase(ids = { "RST-9304", "RST-9305" })
	public void testCreateLocationAndUpdateLocation() {

		StorageLocation storageLocation = new BuilderStorageLocation(Code, Input).build();

		new LostAndFoundPage(storageLocation).stepCreateActiveStorage();

		new ValidateStorageLocation().VerifyRequiredFields().Validate(storageLocation);

		StorageLocation UpdateStorageLocation = new BuilderStorageLocation(Code, Input + "new").build();

		new LostAndFoundPage(UpdateStorageLocation).stepUpdateActiveStorage(storageLocation.getStorageLocation());

		new ValidateStorageLocation().VerifyRequiredFields().Validate(UpdateStorageLocation);
	}

}
