package com.agilysys.qa.gridIron.builders.login;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.qa.gridIron.pageobject.login.POMissingTenantIdDialog;
import com.agilysys.qa.gridIron.pageobject.login.POPropertySelectDialog;
import com.agilysys.qa.gridIron.pageobject.login.POSignInDialog;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LoginApplication {

	public void LoginSingleProperty(String TenantId) {

		process(TenantId);

	}

	public void LoginSinglePropertyUsingMasterObject(MasterObject masterObject) {

		process(masterObject.getProperty().getTenantId());

	}

	public void LoginMultiProperty(String TenantID, String PropertyName) {

		process(TenantID);

		new POPropertySelectDialog().PropertySelect(PropertyName);

	}

	public void Login(String TenantID, String PropertyID) {

		open(Endpoints.getBasePMSUrl() + "#/login?tenantId=" + TenantID + "&propertyId=" + PropertyID);

		new POSignInDialog().SignIn();

	}

	public void LoginMultiPropertyUsingMasterObject(MasterObject masterObject) {

		process(masterObject.getProperty().getTenantId());

		new POPropertySelectDialog().PropertySelect(masterObject.getProperty().getName());

	}

	public void Login(MasterObject masterObject) {

		String TenantID = masterObject.getProperty().getTenantId();
		String PropertyID = masterObject.getProperty().getPropertyId();
		System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
		open(Endpoints.getBasePMSUrl() + "#/login?tenantId=" + TenantID + "&propertyId=" + PropertyID);
		WebDriver driver = getWebDriver();
		driver.manage().window().maximize();

		new POSignInDialog().SignIn();

	}

	private void process(String TenantID) {

		open(Endpoints.getBasePMSUrl() + "#/login");

		new POMissingTenantIdDialog().TenantModal(TenantID);

		new POSignInDialog().SignIn();
	}

}
