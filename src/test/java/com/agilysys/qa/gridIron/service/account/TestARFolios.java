package com.agilysys.qa.gridIron.service.account;

import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.pms.account.model.Charge;
import com.agilysys.pms.account.model.Credit;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.profile.model.CompanySummary;
import com.agilysys.pms.profile.model.v1.CompanyProfile;
import com.agilysys.qa.data.builder.account.BuilderCharge;
import com.agilysys.qa.data.builder.account.BuilderCredit;
import com.agilysys.qa.gridIron.builders.home.SearchCompanyAndOpen;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.profiles.company.ARFolios;
import com.agilysys.qa.gridIron.builders.profiles.company.CreateCompanyProfile;
import com.agilysys.qa.gridIron.constants.locators.home.ProfileTile;
import com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.invoice.LocatorsInvoicesSection;
import com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.invoice.LocatorsRetrieveClosedInvoicesDialog;
import com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.noninvoice.LocatorsNonInvoicedChargesSection;
import com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.PFCompanyProfilePage;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.utils.ClientPropertyDate;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.ValidateFolios;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Configuration;

import java.time.Duration;

import com.codeborne.selenide.commands.Click;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.jsClick;
import static com.codeborne.selenide.Selenide.*;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestARFolios extends StayUIWrappers {

    static String companyName = null;
    static String companyCode = null;
    static String chargeName = null;
    String tenantId = null;
    String propertyId = null;

    static MasterObject masterObject;
    com.agilysys.pms.property.model.Property property;

    BigDecimal amount;
    TransactionItem item;
    Integer quantity = 1;

    ProfileTile PT = new ProfileTile();

    @BeforeClass
    public void beforeClass() {

        masterObject = new MainDriver().getMasterObject();


        masterObject = new CreatePopulatedProperty().useExistingProperty(masterObject.getProperty()).createReservation(1).createItems(3)
                .create();

        chargeName = masterObject.getItems().get(0).getName();
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

        open(Endpoints.getBasePMSUrl() + "#/search/profiles?tenantId=" + tenantId + "&propertyId=" + propertyId);

        $(ProfileTile.TAB_PROFILES).should(Condition.visible, Duration.ofMillis(Configuration.timeout));

        Selenide.refresh();

        click(ProfileTile.TAB_PROFILES);

    }

    @TestCase(id = "RST-12882")
    @Test
    public void testCreateProfileWithAR() {

        companyName = "Company " + RandomHelper.getRandomAlphaString(5);
        companyCode = RandomHelper.getRandomAlphaString(6);

        CompanySummary companyProfile = new CompanySummary();
        companyProfile.setCompanyName(companyName);
        companyProfile.setCompanyCode(companyCode);
        new CreateCompanyProfile(companyProfile).flowCreateWithContactsARUsingSaveExit();

        new POSearchInMainPage().search(companyProfile.getCompanyName());

        click(ProfileTile.TAB_PROFILES);

        click(ProfileTile.RADIO_COMPANY);
        click($$(ProfileTile.LIST_COMPANIES).findBy(Condition.text(companyProfile.getCompanyName())));

    }

    @TestCase(id = "RST-3547")
    @Test
    public void testAddCharge() {

        companyName = "Company " + RandomHelper.getRandomAlphaString(5);
        companyCode = RandomHelper.getRandomAlphaString(6);

        CompanySummary companySummary = new CompanySummary();
        companySummary.setCompanyName(companyName);
        companySummary.setCompanyCode(companyCode);

        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setCompanySummary(companySummary);


        Charge charge = new BuilderCharge(companyProfile, amount, item, quantity)
                .setPostingDate(new ClientPropertyDate().getPropertyDate(tenantId, propertyId)).build();

        new CreateCompanyProfile(companySummary).flowCreateWithContactsARUsingSaveExit();

        new SearchCompanyAndOpen(companySummary.getCompanyName()).searchByName();

        PFCompanyProfilePage.clickTabAR();

        new ARFolios(item).AddCharge();

        new ValidateFolios().VerifyRequired().ValidateARNoninvoice(charge, item);

    }

    @TestCase(id = "RST-3550")
    @Test
    public void testAddCredit() {

        companyName = "Company " + RandomHelper.getRandomAlphaString(5);
        companyCode = RandomHelper.getRandomAlphaString(6);

        CompanySummary companySummary = new CompanySummary();
        companySummary.setCompanyName(companyName);
        companySummary.setCompanyCode(companyCode);

        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setCompanySummary(companySummary);

        Credit credit = new BuilderCredit(companyProfile, amount, item, quantity)
                .setPostingDate(new ClientPropertyDate().getPropertyDate(tenantId, propertyId)).build();

        new CreateCompanyProfile(companySummary).flowCreateWithContactsARUsingSaveExit();

        new SearchCompanyAndOpen(companySummary.getCompanyName()).searchByName();

        PFCompanyProfilePage.clickTabAR();

            new ARFolios(item).AddCredit();

        new ValidateFolios().VerifyRequired().ValidateARNoninvoice(credit, item);
    }

    @TestCase(ids = {"RST-3576", "RST-1982"})
    @Test
    public void testMakePayment() {

        companyName = "Company " + RandomHelper.getRandomAlphaString(5);
        companyCode = RandomHelper.getRandomAlphaString(6);

        CompanySummary companySummary = new CompanySummary();
        companySummary.setCompanyName(companyName);
        companySummary.setCompanyCode(companyCode);

        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setCompanySummary(companySummary);

        Charge charge = new BuilderCharge(companyProfile, amount, item, quantity)
                .setPostingDate(new ClientPropertyDate().getPropertyDate(tenantId, propertyId)).build();

        new CreateCompanyProfile(companySummary).flowCreateWithContactsARUsingSaveExit();

        new SearchCompanyAndOpen(companySummary.getCompanyName()).searchByName();

        PFCompanyProfilePage.clickTabAR();

        new ARFolios(item).AddCharge();

        new ValidateFolios().VerifyRequired().ValidateARNoninvoice(charge, item);

        new ARFolios(item.getName()).MakePayment();

        Selenide.executeJavaScript("window.scrollBy(0,600);");

        //clicking on more button in invoices
        click(LocatorsInvoicesSection.MORE_BUTTON_INVOICES);
        click(By.xpath("//div[@data-qa-id=\"14wGVy\"]//child::button"));
        //clicking on retrieve invoices
        click(LocatorsInvoicesSection.BUTTON_RETRIEVE_INVOICES);

        //clicking on done button based on option selected
        click(LocatorsRetrieveClosedInvoicesDialog.BUTTON_DONE);

        $(LocatorsInvoicesSection.LABEL_STATUS)
                .shouldHave(Condition.or("ModifiedOrClosed", Condition.text("Modified"), Condition.text("Closed")));

    }

    @TestCase(id = "RST-7409")
    @Test
    public void testTransfer() {

        companyName = "Company " + RandomHelper.getRandomAlphaString(5);
        companyCode = RandomHelper.getRandomAlphaString(6);

        CompanySummary companySummary = new CompanySummary();
        companySummary.setCompanyName(companyName);
        companySummary.setCompanyCode(companyCode);

        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setCompanySummary(companySummary);

        Credit credit = new BuilderCredit(companyProfile, amount, item, quantity)
                .setPostingDate(new ClientPropertyDate().getPropertyDate(tenantId, propertyId)).build();

        new CreateCompanyProfile(companySummary).flowCreateWithContactsARUsingSaveExit();

        new SearchCompanyAndOpen(companySummary.getCompanyName()).searchByName();

        PFCompanyProfilePage.clickTabAR();

        new ARFolios(item.getName()).AddCredit();

        new ValidateFolios().VerifyRequired().ValidateARNoninvoice(credit, item);

        new ARFolios(item.getName()).Transfer(masterObject.getReservations().get(0).getPrimaryGuestInfo().getLastName());

        $(LocatorsNonInvoicedChargesSection.MESSAGE_EMPTY_NON_INVOICE).shouldHave(Condition.text("No Charges"));

    }
}
