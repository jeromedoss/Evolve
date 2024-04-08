package com.agilysys.qa.gridIron.constants.pagefactory.login;

import static com.agilysys.qa.gridIron.constants.locators.login.LocatorsMissingTenantIdDialog.BUTTON_SAVE;
import static com.agilysys.qa.gridIron.constants.locators.login.LocatorsMissingTenantIdDialog.HEADER_MODAL;
import static com.agilysys.qa.gridIron.constants.locators.login.LocatorsMissingTenantIdDialog.INPUT_TENANT_ID;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

public class PFMissingTenantIdDialog {

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void setInputTenantId(String tenantId) {
		$(INPUT_TENANT_ID).setValue(tenantId);
	}

	public static void clickButtonSave() {
		click(BUTTON_SAVE);
	}
}