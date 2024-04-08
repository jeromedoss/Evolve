package com.agilysys.qa.gridIron.builders.profiles.company;

import static com.agilysys.qa.gridIron.pageobject.home.profiletile.CreateNewProfile.stepClickCreateCompanyFromMain;
import static com.agilysys.qa.gridIron.pageobject.profiles.company.POCompanyProfilePage.stepCreateARAccountSave;
import static com.agilysys.qa.gridIron.pageobject.profiles.company.POCompanyProfilePage.stepCreateCompanyProfileSave;
import static com.agilysys.qa.gridIron.pageobject.profiles.company.POCompanyProfilePage.stepCreateCompanyProfileSaveExit;

import java.math.BigDecimal;

import com.agilysys.pms.account.model.Company;
import com.agilysys.pms.profile.model.CompanySummary;
import com.agilysys.qa.gridIron.constants.pagefactory.profiles.company.PFCompanyProfilePage;
import com.agilysys.qa.gridIron.pageobject.profiles.company.POCompanyProfilePage;

/*
 * *Author - Harish Baskaran - 2018
 */
public class CreateCompanyProfile {

	String companyName = null;
	String companyCode = null;
	BigDecimal credit = new BigDecimal(5000);
	String terms = "30 days";

	public CreateCompanyProfile(String companyName, String companyCode) {
		this.companyName = companyName;
		this.companyCode = companyCode;
	}

	public CreateCompanyProfile(CompanySummary companyProfile) {
		this.companyName = companyProfile.getCompanyName();
		this.companyCode = companyProfile.getCompanyCode();
	}

	POCompanyProfilePage PO = new POCompanyProfilePage();

	public void flowCreateWithoutARUsingSave() {

		stepClickCreateCompanyFromMain();
		
		stepCreateCompanyProfileSave(companyName, companyCode);

	}

	public void flowCreateWithoutARUsingSaveExit() {

		stepClickCreateCompanyFromMain();
		stepCreateCompanyProfileSaveExit(companyName, companyCode);

	}
	public void flowCreateWithContactsARUsingSaveExit(){
		stepClickCreateCompanyFromMain();
		stepCreateCompanyProfileSave(companyName, companyCode);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		PFCompanyProfilePage.clickContacts();
		try {
			PFCompanyProfilePage.createNewProfile();
		}catch(Exception e){}
		POCompanyProfilePage.stepCreateARAccountSaveExit(credit, terms);
	}

	public void flowCreateWithARUsingSave() {

		stepClickCreateCompanyFromMain();
		stepCreateCompanyProfileSave(companyName, companyCode);
		stepCreateARAccountSave(credit, terms);

	}

	public void flowCreateWithARUsingSaveExit() {

		stepClickCreateCompanyFromMain();
		stepCreateCompanyProfileSave(companyName, companyCode);
		POCompanyProfilePage.stepCreateARAccountSaveExit(credit, terms);

	}

}
