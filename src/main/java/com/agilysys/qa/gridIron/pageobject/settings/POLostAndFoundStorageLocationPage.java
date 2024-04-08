package com.agilysys.qa.gridIron.pageobject.settings;

import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundStorageLocationPage.clickButtonAdd;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundStorageLocationPage.clickButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundStorageLocationPage.clickExistingLocation;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundStorageLocationPage.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundStorageLocationPage.clickTabLocation;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundStorageLocationPage.setInputCode;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundStorageLocationPage.setInputLocation;

import com.agilysys.pms.property.model.StorageLocation;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POLostAndFoundStorageLocationPage {

	private String locationName = null;
	private String locationCode = null;

	public POLostAndFoundStorageLocationPage() {

	}

	public POLostAndFoundStorageLocationPage(String locationName, String locationCode) {
		this.locationName = locationName;
		this.locationCode = locationCode;
	}

	public POLostAndFoundStorageLocationPage(StorageLocation storageLocation) {
		this.locationName = storageLocation.getStorageLocation();
		this.locationCode = storageLocation.getCode();
	}

	public void stepCreateActiveLocation() {

		clickTabLocation();
		clickButtonAdd();
		stepModalMandatetoryFields();

	}

	public void stepUpdateActiveLocation(String existingLocation) {

		clickTabLocation();
		clickExistingLocation(existingLocation);
		stepModalMandatetoryFields();

	}

	private void stepModalMandatetoryFields() {

		clickHeaderModal();
		setInputLocation(locationName);
		setInputCode(locationCode);
		clickButtonSave();

	}
}