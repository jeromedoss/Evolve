package com.agilysys.qa.gridIron.validations;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.pms.property.model.StorageLocation;
import com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsStorageLocationPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class ValidateStorageLocation {

	private boolean storageLocationName = false;
	private boolean storageLocationCode = false;
	private boolean storageLocationDescription = false;
	private boolean storageLocationActiveState = false;
	private boolean storageLocationInactiveState = false;

	public ValidateStorageLocation VerifyAll() {
		storageLocationName = true;
		storageLocationCode = true;
		storageLocationDescription = true;
		storageLocationActiveState = true;

		return this;
	}

	public ValidateStorageLocation VerifyRequiredFields() {
		storageLocationName = true;
		storageLocationCode = true;
		storageLocationActiveState = true;

		return this;
	}

	public ValidateStorageLocation activateStorageLocationName() {
		storageLocationName = true;
		return this;
	}

	public ValidateStorageLocation activateStorageLocationCode() {
		storageLocationCode = true;
		return this;
	}

	public ValidateStorageLocation activateStorageLocationDescription() {
		storageLocationDescription = true;
		return this;
	}

	public ValidateStorageLocation activateStorageLocationActiveState() {
		storageLocationActiveState = true;
		return this;
	}

	public ValidateStorageLocation activateStorageLocationInactiveState() {
		storageLocationInactiveState = true;
		return this;
	}

	public void Validate(StorageLocation storageLocation) {

		Selenide.refresh();

		$(LocatorsStorageLocationPage.INPUT_SEARCH).setValue(storageLocation.getStorageLocation());

		if (storageLocationName) {
			$$(LocatorsStorageLocationPage.LIST_LINK_LOCATION)
					.findBy(Condition.exactText(storageLocation.getStorageLocation())).shouldBe(Condition.visible);
		}

		if (storageLocationCode) {
			$$(LocatorsStorageLocationPage.LIST_CODE).findBy(Condition.exactText(storageLocation.getCode()))
					.shouldBe(Condition.visible);
		}

		if (storageLocationDescription) {
			$$(LocatorsStorageLocationPage.LIST_DESCRIPTION)
					.findBy(Condition.exactText(storageLocation.getDescription())).shouldBe(Condition.visible);
		}

		if (storageLocationActiveState) {
			$$(LocatorsStorageLocationPage.LIST_STATUS).findBy(Condition.text("Active")).shouldBe(Condition.visible);
		}

		if (storageLocationInactiveState) {

			click(LocatorsStorageLocationPage.CHECKBOX_SHOW_INACTIVE);
			$$(LocatorsStorageLocationPage.LIST_STATUS).findBy(Condition.text("Inactive")).shouldBe(Condition.visible);
		}

	}
}