package com.agilysys.qa.gridIron.pageobject.login;

import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFMissingTenantIdDialog.clickButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFMissingTenantIdDialog.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFMissingTenantIdDialog.setInputTenantId;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POMissingTenantIdDialog {

	public void TenantModal(String tenantId) {

		clickHeaderModal();
		setInputTenantId(tenantId);
		clickButtonSave();

	}

}
