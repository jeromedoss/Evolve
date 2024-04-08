package com.agilysys.qa.gridIron.pageobject.settings;

import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundTypePage.clickButtonAdd;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundTypePage.clickButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundTypePage.clickExistingType;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundTypePage.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundTypePage.setInputCode;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFLostAndFoundTypePage.setInputType;

import com.agilysys.pms.property.model.LostItem;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POLostAndFoundTypePage {

	private String typeName = null;
	private String typeCode = null;

	
	public POLostAndFoundTypePage(String typeName, String typeCode) {
		this.typeName = typeName;
		this.typeCode = typeCode;
	}


	public POLostAndFoundTypePage(LostItem lostItem) {
		this.typeName = lostItem.getName();
		this.typeCode = lostItem.getCode();
	}

	public void stepCreateActiveType() {

		clickButtonAdd();
		stepModalMandatetoryFields();

	}

	public void stepUpdateActiveType(String existingType) {

		clickExistingType(existingType);
		stepModalMandatetoryFields();
	}

	private void stepModalMandatetoryFields() {

		clickHeaderModal();
		setInputType(typeName);
		setInputCode(typeCode);
		clickButtonSave();
	}
}