package com.agilysys.qa.gridIron.service.account;

import com.agilysys.pms.account.model.*;
import com.agilysys.pms.property.model.AccountDisplaySettingsView;
import com.agilysys.qa.client.account.folio.ClientFolioCreate;
import com.agilysys.qa.client.account.folio.ClientFolioGet;
import com.agilysys.qa.client.account.transaction.ClientAccountPostCharge;
import com.agilysys.qa.client.account.transaction.ClientAccountPostTransfer;
import com.agilysys.qa.clients.ClientFactory;
import com.agilysys.qa.data.builder.account.BuilderCharge;
import com.agilysys.qa.data.builder.account.BuilderFolioSummary;
import com.agilysys.qa.data.builder.account.BuilderLineItemTransfer;
import com.agilysys.qa.data.builder.account.BuilderPostChargesRequest;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.qa.gridIron.builders.home.SearchReservationAndOpen;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.reservation.ResFolios;
import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.agilysys.qa.helpers.ClientHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

//import edu.emory.mathcs.backport.java.util.Arrays;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickTabReservations;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails.navigatePaymentSection;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.waitForElementToLoad;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestReservationRoutingRuleAndTransfer extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	String tenantId = null;
	String propertyId = null;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createRooms(10).createPaymentMethodAllCC().createRoomTypes(1).createItems(3).createReservation(5)
				.create();

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();

		new LoginApplication().Login(masterObject);

	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {

		open(Endpoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);

		clickTabReservations();

	}

	@Test
	@TestCase(id = "RST-275")
	public void testAddRoutingRule() {

		new SearchReservationAndOpen(masterObject.getReservations().get(2).getPrimaryGuestInfo().getLastName(),
				masterObject.getReservations().get(2).getPrimaryGuestInfo().getFirstName(),
				masterObject.getReservations().get(2).getConfirmationCode()).searchByName();

		new ResFolios(masterObject.getItems().get(1).getName()).flowAddRoutingRule(masterObject.getReservations().get(1).getPrimaryGuestInfo().getLastName());

		new ResFolios(masterObject.getItems().get(1).getName()).flowAddChargeForAnItem1();

		$(LocatorsFoliosDetails.LABEL_SUMMARY_TOTAL).shouldHave(Condition.text("$0.00"));
	}

	@Test
	@TestCase(ids = {"RST-7644","RST-3600","RST-4373"})
	public void testTransfer() {

		new SearchReservationAndOpen(masterObject.getReservations().get(3).getPrimaryGuestInfo().getLastName(),
				masterObject.getReservations().get(3).getPrimaryGuestInfo().getFirstName(),
				masterObject.getReservations().get(3).getConfirmationCode()).searchByName();

		new ResFolios(masterObject.getItems().get(1).getName()).flowAddCreditForAnItem();

		new ResFolios(masterObject.getItems().get(1).getName())
				.flowTransferfromMoreButton(masterObject.getReservations().get(1).getPrimaryGuestInfo().getLastName());

		Selenide.sleep(4000);

		click(LocatorsFoliosDetails.LABEL_QUANTITY_FOLIO1);

		Selenide.sleep(4000);

		click(LocatorsFoliosDetails.BUTTON_COLLAPSE_ITEM);

		$(LocatorsFoliosDetails.LABEL_TRANSFER_SOURCE).shouldBe(Condition.visible)
				.shouldHave(Condition.text(masterObject.getReservations().get(3).getPrimaryGuestInfo().getLastName()));
		$(LocatorsFoliosDetails.LABEL_TRANSFER_DESTINATION).shouldBe(Condition.visible)
				.shouldHave(Condition.text(masterObject.getReservations().get(1).getPrimaryGuestInfo().getLastName()));
	}

	@Test
	public void testVerifyAccountDisplaySettingsView() {

		updateAccountDisplaySettingsView(false, false);

		FolioDetail defaultFolio = new ClientFolioGet().getFolios(masterObject.getProperty(), masterObject.getReservations().get(4)).stream().filter(x -> x.isDefaultFolio())
				.findAny().get();

		Charge charge = new BuilderCharge(masterObject.getReservations().get(4).getAccountId(), new BigDecimal(25), masterObject.getItems().get(0).getId(), 1).build();
		charge.setFolioId(defaultFolio.getId());
		charge.setPostingDate(masterObject.getReservations().get(4).getArrivalDate());
		// post charge
		PostChargesRequest builderPostChargesRequest = new BuilderPostChargesRequest(charge).build();
		new ClientAccountPostCharge().postCharges(tenantId, propertyId, masterObject.getReservations().get(4).getAccountId(), true, false, builderPostChargesRequest);
		defaultFolio = new ClientFolioGet().getFolios(masterObject.getProperty(), masterObject.getReservations().get(4)).stream().filter(x -> x.isDefaultFolio())
				.findAny().get();


		FolioSummary createdFolio = new BuilderFolioSummary(RandomHelper.getRandomAlphaNumericString(4), false).build();
		createdFolio = new ClientFolioCreate().createFolio(masterObject.getProperty(), masterObject.getReservations().get(4), createdFolio);
		LineItemTransfer lineItemTransfer =
				new BuilderLineItemTransfer(masterObject.getReservations().get(4), defaultFolio.getLineItems()).setDestinationFolioId(createdFolio).setReason(RandomHelper.getRandomAlphaString(4))
						.build();
		new ClientAccountPostTransfer().transferFolioLines(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId(),
						masterObject.getReservations().get(4).getAccountId(), lineItemTransfer);

		new SearchReservationAndOpen(masterObject.getReservations().get(4).getPrimaryGuestInfo().getLastName(),
				masterObject.getReservations().get(4).getPrimaryGuestInfo().getFirstName(),
				masterObject.getReservations().get(4).getConfirmationCode()).searchByName();

		Selenide.sleep(4000);
		navigatePaymentSection();
		click(LocatorsFoliosDetails.LABEL_QUANTITY_FOLIO1);
		Selenide.sleep(4000);
		Assert.assertTrue($(LocatorsFoliosDetails.LABEL_CONTENT_FOLIO1).getText().contains("No charges were found."));
	}

	public void updateAccountDisplaySettingsView(Boolean showTransferredItem, Boolean showCompedItem) {
		AccountDisplaySettingsView accountDisplaySettingsView = new AccountDisplaySettingsView();
		accountDisplaySettingsView.setPropertyId(propertyId);
		accountDisplaySettingsView.setTenantId(tenantId);
		accountDisplaySettingsView.setShowTransferredItem(showTransferredItem);
		accountDisplaySettingsView.setShowCompedItem(showCompedItem);
		ClientHelper.call(
				() -> ClientFactory.getPropertyManagement().updateAccountDisplaySetting(tenantId, propertyId, accountDisplaySettingsView),
				null, "Failed to update PosPmsItems in property");
	}
}
