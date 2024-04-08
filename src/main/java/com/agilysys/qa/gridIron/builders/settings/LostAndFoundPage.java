package com.agilysys.qa.gridIron.builders.settings;

import com.agilysys.pms.property.model.LostItem;
import com.agilysys.pms.property.model.StorageLocation;
import com.agilysys.qa.gridIron.pageobject.settings.POLostAndFoundStorageLocationPage;
import com.agilysys.qa.gridIron.pageobject.settings.POLostAndFoundTypePage;

public class LostAndFoundPage {

	LostItem lostItem = null;
	StorageLocation storageLocation = null;

	public LostAndFoundPage(LostItem lostItem) {
		this.lostItem = lostItem;
	}

	public LostAndFoundPage(StorageLocation storageLocation) {
		this.storageLocation = storageLocation;
	}

	public void stepCreateActiveType() {
		new POLostAndFoundTypePage(lostItem).stepCreateActiveType();
	}

	public void stepUpdateActiveType(String typeName) {
		new POLostAndFoundTypePage(lostItem).stepUpdateActiveType(typeName);
	}

	public void stepCreateActiveStorage() {
		new POLostAndFoundStorageLocationPage(storageLocation).stepCreateActiveLocation();
	}

	public void stepUpdateActiveStorage(String storageName) {
		new POLostAndFoundStorageLocationPage(storageLocation).stepUpdateActiveLocation(storageName);
	}
}
