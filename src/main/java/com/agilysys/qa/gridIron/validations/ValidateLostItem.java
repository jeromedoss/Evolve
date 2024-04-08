package com.agilysys.qa.gridIron.validations;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.agilysys.pms.property.model.LostItem;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import com.agilysys.qa.gridIron.constants.locators.settings.lostandfound.LocatorsTypePage;

public class ValidateLostItem {

	protected boolean lostItemName = false;
	protected boolean lostItemCode = false;
	protected boolean lostItemActiveStatus = false;
	protected boolean lostItemInactiveStatus = false;

	public ValidateLostItem VerifyAll() {
		lostItemName = true;
		lostItemCode = true;
		lostItemActiveStatus = true;

		return this;
	}

	public ValidateLostItem activatelostItemName() {
		lostItemName = true;
		return this;
	}

	public ValidateLostItem activatelostItemCode() {
		lostItemCode = true;
		return this;
	}

	public ValidateLostItem activatelostItemActiveStatus() {
		lostItemActiveStatus = true;
		return this;
	}

	public ValidateLostItem activatelostItemInactiveStatus() {
		lostItemInactiveStatus = true;
		return this;
	}

	public void Validate(LostItem lostItem) {

		Selenide.refresh();

		$(LocatorsTypePage.INPUT_SEARCH).setValue(lostItem.getName());

		if (lostItemName) {
			$$(LocatorsTypePage.LIST_LINK_NAME).findBy(Condition.exactText(lostItem.getName()))
					.shouldBe(Condition.visible);
		}

		if (lostItemCode) {
			$$(LocatorsTypePage.LIST_COLUMN_CODE).findBy(Condition.exactText(lostItem.getCode()))
					.shouldBe(Condition.visible);
		}

		if (lostItemActiveStatus) {
			$$(LocatorsTypePage.LIST_COLUMN_STATUS).findBy(Condition.exactText("Active")).shouldBe(Condition.visible);
		}

		if (lostItemInactiveStatus) {
			click(LocatorsTypePage.CHECKBOX_SHOW_INACTIVE);
			$$(LocatorsTypePage.LIST_COLUMN_STATUS).findBy(Condition.exactText("Active")).shouldBe(Condition.visible);
		}
	}
}