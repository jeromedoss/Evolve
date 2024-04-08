package com.agilysys.qa.gridIron.service.account;

import com.agilysys.pms.account.model.AccountsReceivableSettings;
import com.agilysys.pms.account.model.NextAccountNumberInfo;
import com.agilysys.pms.profile.model.v1.CompanyAccountCreateRequest;
import com.agilysys.qa.client.account.ClientAccountNumberGet;
import com.agilysys.qa.client.profile.company.ClientCompanyProfileCreate;
import com.agilysys.qa.data.builder.account.BuilderAccountsReceivableSettings;
import com.agilysys.qa.data.builder.profile.BuilderCompanyAccountCreateRequest;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.insertanator.services.lookup.items.ServiceGetItem;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.profile.model.BookingStatus;
import com.agilysys.pms.profile.model.Group;
import com.agilysys.pms.profile.model.ProfileReference;
import com.agilysys.pms.profile.model.ProfileReferenceType;
import com.agilysys.qa.client.group.ClientGroupCreate;
import com.agilysys.qa.data.builder.account.BuilderCharge;
import com.agilysys.qa.data.builder.account.BuilderCredit;
import com.agilysys.qa.data.builder.group.BuilderGroup;
import com.agilysys.qa.gridIron.builders.groups.GroupFolios;
import com.agilysys.qa.gridIron.builders.home.SearchGroupAndOpen;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.reservation.ResFolios;
import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails;
import com.agilysys.qa.gridIron.utils.ClientPropertyDate;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.agilysys.qa.verify.group.VerifyGroup;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsFoliosAndRoutingRulesSection.TAB_FOLIOS;
import static com.agilysys.qa.gridIron.constants.locators.home.GroupTile.TAB_GROUP;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Oct/2018
 */

public class TestGroupFolios extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;
	static Property property;

	String tenantId = null;
	String propertyId = null;

	BigDecimal amount;
	TransactionItem item;
	Integer quantity = 1;

	Group group;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createProperty("StayAutomation " + tenant.getTenantCode(),
				tenant.getTenantCode(), propertycode, propertycode).createPaymentMethodAllCC().createItems(3)
				.createGroups(5).createCompanies(1).create();

		property = masterObject.getProperty();

		amount = masterObject.getItems().get(0).getDefaultPrice();
		item = masterObject.getItems().get(0);

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();

		createCompanyAccountReceivable();
		new LoginApplication().Login(masterObject);
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {

		open(Endpoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);

		//$(TAB_GROUP).vicks

	}

	@Test
	@TestCase(id = "RST-637")
	public void testAddCharge() {

		group = new Group();
		group.setGroupName(masterObject.getGroups().get(0).getGroupName());
		group.setGroupCode(masterObject.getGroups().get(0).getGroupCode());

		new BuilderCharge(group, amount, item, quantity)
				.setPostingDate(new ClientPropertyDate().getPropertyDate(tenantId,propertyId)).build();

		new SearchGroupAndOpen(group.getGroupName()).searchByName();

		click(TAB_FOLIOS);

		new GroupFolios(item).flowAddCharge();

		$(LocatorsFoliosDetails.LABEL_SUMMARY_TOTAL).shouldHave(Condition.text("$30.00"));
	}

	@Test
	public void testAddChargeForFutureDatedGroup() {

		LocalDate propertyDate = new ClientPropertyDate().getPropertyDate(tenantId,propertyId);
		LocalDate arrivalDate = propertyDate.plusDays(1);
		LocalDate departureDate = arrivalDate.plusDays(2);

		Group group = new BuilderGroup(masterObject.getProperty(), RandomHelper.getRandomAlphaString(5), RandomHelper.getRandomAlphaString(5),
				masterObject.getCancelationPolicy(), masterObject.getUsers().get(0),
				new ServiceGetItem().lookup("Nightly Room Charge", masterObject.getProperty()), arrivalDate,
				departureDate, arrivalDate, arrivalDate.minusDays(1), arrivalDate, BookingStatus.INQUIRY,
				new ProfileReference(masterObject.getGuests().get(0).getId(), ProfileReferenceType.GUEST)).build();
		// create & verify
		Group createdGroup = new ClientGroupCreate().createGroup(masterObject.getProperty(), "", group);
		new VerifyGroup().activateVerifyStartDate().verify(createdGroup, group);

		new SearchGroupAndOpen(group.getGroupName()).searchByNameFutureDate(arrivalDate);

		click(TAB_FOLIOS);

		new GroupFolios(item).flowAddCharge();

		$(LocatorsFoliosDetails.LABEL_SUMMARY_TOTAL).shouldHave(Condition.text("$30.00"));

	}

	@Test
	@TestCase(id = "RST-657")
	public void testAddCredit() {

		group = new Group();
		group.setGroupName(masterObject.getGroups().get(1).getGroupName());
		group.setGroupCode(masterObject.getGroups().get(1).getGroupCode());

		new BuilderCredit(group, amount, item, quantity)
				.setPostingDate(new ClientPropertyDate().getPropertyDate(tenantId,propertyId)).build();

		new SearchGroupAndOpen(group.getGroupName()).searchByName();

		click(TAB_FOLIOS);

		new GroupFolios(item).flowAddCredit();

		$(LocatorsFoliosDetails.LABEL_SUMMARY_TOTAL).shouldHave(Condition.text("-$30.00"));
	}

	@Test
	@TestCase(id = "RST-845")
	public void testMakePayment() {

		group = new Group();
		group.setGroupName(masterObject.getGroups().get(2).getGroupName());
		group.setGroupCode(masterObject.getGroups().get(2).getGroupCode());

		new BuilderCharge(group, amount, item, quantity)
				.setPostingDate(new ClientPropertyDate().getPropertyDate(tenantId,propertyId)).build();

		new SearchGroupAndOpen(group.getGroupName()).searchByName();

		click(TAB_FOLIOS);

		new GroupFolios(item).flowAddCharge();

		new GroupFolios(item).flowMakePayment("30");

		$(LocatorsFoliosDetails.LABEL_SUMMARY_TOTAL).shouldHave(Condition.text("$0.00"));
	}

	// @Test
	// public void TestAddRoutingRule() {
	//
	// new
	// SearchGroupAndOpen(masterObject.getGroups().get(0).getName()).searchByName();
	//
	// new Folios(masterObject.getItems().get(1).getName())
	// .AddRoutingRule(masterObject.getReservations().get(1).getGuestLastName());
	//
	// new
	// Folios(masterObject.getItems().get(1).getName()).AddChargeForAnItem1();
	//
	// $(new
	// ReservationFoliosNTBD().getSummarytotal()).shouldHave(Condition.text("$0.00"));
	// }

	@Test
	public void testTransfer() {

		new SearchGroupAndOpen(masterObject.getGroups().get(4).getGroupName()).searchByName();

		click(TAB_FOLIOS);

		new GroupFolios(masterObject.getItems().get(0).getName()).flowAddCredit();

		new ResFolios(masterObject.getItems().get(0).getName())
				.flowTransferfromMoreButton(masterObject.getCompanies().get(0).getCompanySummary()
						.getCompanyName());

		Selenide.sleep(4000);

		click(LocatorsFoliosDetails.LABEL_QUANTITY_FOLIO1);

		Selenide.sleep(4000);

		click(LocatorsFoliosDetails.BUTTON_COLLAPSE_ITEM);

		$(LocatorsFoliosDetails.LABEL_TRANSFER_SOURCE).shouldBe(Condition.visible)
				.shouldHave(Condition.text(masterObject.getGroups().get(4).getGroupName()));
		$(LocatorsFoliosDetails.LABEL_TRANSFER_DESTINATION).shouldBe(Condition.visible)
				.shouldHave(Condition.text(masterObject.getCompanies().get(0).getCompanySummary()
						.getCompanyName()));
	}

	private void createCompanyAccountReceivable()
	{
		NextAccountNumberInfo nextAccountNumberInfo =
				new ClientAccountNumberGet().getNextArAccountNumber(property.getTenantId(), property.getPropertyId());
		String defaultAccountNumber = nextAccountNumberInfo.getNextAccountNumber().toString();
		BigDecimal creditLimit = new BigDecimal(com.agilysys.qa.helpers.RandomHelper.getRandomInt(10000, 50000));
		int defaultTerms = 30;
		AccountsReceivableSettings accountsReceivableSettings = new BuilderAccountsReceivableSettings(creditLimit,
				defaultTerms, true)
				.setAccountNumber(defaultAccountNumber).build();
		CompanyAccountCreateRequest companyAccountCreateRequest = new BuilderCompanyAccountCreateRequest(accountsReceivableSettings).build();
		// call create
		new ClientCompanyProfileCreate().createCompanyAccount(property, masterObject.getCompanies().get(0), companyAccountCreateRequest);
	}
}
