package com.agilysys.qa.gridIron.service.account;

import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.account.model.*;
import com.agilysys.qa.client.account.ClientAccountCreate;
import com.agilysys.qa.client.account.category.house.account.ClientHouseAccountCategoryGet;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.data.builder.account.BuilderCharge;
import com.agilysys.qa.data.builder.account.BuilderCreateAccountSummary;
import com.agilysys.qa.gridIron.builders.home.SearchHouseaccountAndOpen;
import com.agilysys.qa.gridIron.builders.houseaccounts.CreateHouseAccount;
import com.agilysys.qa.gridIron.builders.houseaccounts.HAFolios;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.locators.houseaccounts.LocatorsHouseaccountFolios;
import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.LIST_HOUSEACCOUNTS;
import static com.agilysys.qa.gridIron.constants.locators.home.AccountsTile.TAB_ACCOUNTS;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.*;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestCreateHouseAccountAndFolios extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;
	Property property;

	static String accountName = null;

	String tenantId = null;
	String propertyId = null;

	BigDecimal amount;
	TransactionItem item;
	Integer quantity = 1;

	AccountDetail houseAccountDetail1 = null;
	AccountDetail destinationHouseAccountDetail = null;
	ClientAccountCreate clientAccountCreate = null;

	List<HouseAccountCategory> houseAccountCategories = null;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createProperty("StayAutomation " + tenant.getTenantCode(),
				tenant.getTenantCode(), propertycode, propertycode).createGuests(1).createReservation(1).createItems(2)
				.create();

		property = masterObject.getProperty();

		amount = masterObject.getItems().get(0).getDefaultPrice();
		item = masterObject.getItems().get(0);

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();

		new LoginApplication().Login(masterObject);

	}

	@AfterClass
	public void afterclass() {
		//Selenide.closeWebDriver();

	}

	@BeforeMethod
	public void beforeMethod() {

		clientAccountCreate = new ClientAccountCreate();

		// create house account
		List<HouseAccountCategory> houseAccountCategories = new ClientHouseAccountCategoryGet()
				.findHouseAccountCategories(masterObject.getProperty());

		CreateAccountSummary createAccountSummary = new BuilderCreateAccountSummary(masterObject.getProperty(),
				AccountStatus.OPEN, AccountType.HOUSE).setHouseAccountCategoryId(houseAccountCategories.get(0))
						.setName("HouseAccount_" + RandomHelper.getRandomNumericString(8))
						.setNumber(RandomHelper.getRandomNumericString(8)).build();
		houseAccountDetail1 = clientAccountCreate.createAccount(masterObject.getProperty(), createAccountSummary);

		accountName = houseAccountDetail1.getName();

		open(Endpoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);

		$(TAB_ACCOUNTS).should(Condition.visible,  Duration.ofMillis(Configuration.timeout));

		Selenide.refresh();

		click(TAB_ACCOUNTS);

	}

	@Test
	@TestCase(id = "RST-192")
	public void testCreateHouseAccount() {

		accountName = RandomHelper.getRandomAlphaString(8);

		new CreateHouseAccount(accountName, "Concierge").createHAWithoutAddingCharge();

		Selenide.executeJavaScript("window.scrollTo(0, 0);");

		new POSearchInMainPage().search(accountName);

		$$(LIST_HOUSEACCOUNTS).findBy(Condition.text(accountName)).should(Condition.exist);

	}

	@Test
	@TestCase(id = "RST-12888")
	public void testAddCharge() {

		new SearchHouseaccountAndOpen(accountName).searchByName();

		new BuilderCharge(houseAccountDetail1, amount, item, quantity)
				.setPostingDate(new ClientPropertyDateGet().getPropertyDate(property)).build();

		new HAFolios(item).flowAddChargeForAnItem();

		Selenide.sleep(2000);

		$(LocatorsHouseaccountFolios.SUMMARY_TOTAL).shouldHave(Condition.text("$30.00"));
	}

	@Test
	@TestCase(id = "RST-12889")
	public void testAddCredit() {

		new SearchHouseaccountAndOpen(accountName).searchByName();

		new HAFolios(item).flowAddCreditForAnItem();

		Selenide.sleep(2000);

		$(LocatorsHouseaccountFolios.SUMMARY_TOTAL).shouldHave(Condition.text("-$30.00"));
	}

	@Test
	@TestCase(id = "RST-545")
	public void testMakePayment() {

		testAddCharge();

		new HAFolios(item).flowMakePayment(amount.toString());

		Selenide.sleep(2000);

		$(LocatorsHouseaccountFolios.SUMMARY_TOTAL).shouldHave(Condition.text("$0.00"));
	}

	@Test
	public void testTransfer() {

		testAddCredit();

		new HAFolios(item).flowTransferfromMoreButton(masterObject.getReservations().get(0).getPrimaryGuestInfo().getLastName());

		click(LocatorsHouseaccountFolios.FOLIO_CHARGE_AMOUNT_LIST);
		Selenide.sleep(2000);
		click(LocatorsFoliosDetails.BUTTON_COLLAPSE_ITEM);
		Selenide.sleep(2000);
		$(LocatorsHouseaccountFolios.TRANSFER_SOURCE_CHECK).shouldBe(Condition.visible)
				.shouldHave(Condition.text(accountName));
		$(LocatorsHouseaccountFolios.TRANSFER_DESTINATION_CHECK).shouldBe(Condition.visible)
				.shouldHave(Condition.text(masterObject.getReservations().get(0).getPrimaryGuestInfo().getLastName()));
	}
}
