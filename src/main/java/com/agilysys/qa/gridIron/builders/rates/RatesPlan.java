package com.agilysys.qa.gridIron.builders.rates;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns.navigateToRates;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.pms.rates.model.RatePlanDetail;
import com.agilysys.qa.gridIron.constants.locators.rates.LocatorsRatesPage;
import com.agilysys.qa.gridIron.constants.locators.rates.packages.LocatorsComponentsModal;
import com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsStrategiesModal;
import com.agilysys.qa.gridIron.pageobject.rates.packages.POPackageMainPage;
import com.agilysys.qa.gridIron.pageobject.rates.rateplans.PORateMainPage;
import com.agilysys.qa.gridIron.pageobject.rates.rateplans.addrateplans.POComponentsPage;
import com.agilysys.qa.gridIron.pageobject.rates.rateplans.addrateplans.PODetailsPage;
import com.agilysys.qa.gridIron.pageobject.rates.rateplans.addrateplans.POPoliciesPage;
import com.agilysys.qa.gridIron.pageobject.rates.rateplans.addrateplans.POSchedulePage;
import com.agilysys.qa.gridIron.pageobject.rates.rateplans.addrateplans.POStrategiesPage;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
/*
 * *Author - Harish Baskaran - 2018
 */
public class RatesPlan {

	LocatorsRatesPage PO = new LocatorsRatesPage();

	PORateMainPage PO1 = new PORateMainPage();
	POPackageMainPage PA1 = new POPackageMainPage();
	PODetailsPage PO2 = new PODetailsPage();
	POPoliciesPage PO3 = new POPoliciesPage();
	POSchedulePage PO4 = new POSchedulePage();
	POStrategiesPage PO5 = new POStrategiesPage();
	POComponentsPage PO6 = new POComponentsPage();

	RatePlanDetail ratePlanDetail = null;

	public RatesPlan(RatePlanDetail ratePlanDetail) {
		this.ratePlanDetail = ratePlanDetail;
	}

	public void CreateRatePlan() {

		navigateToRates();

		PO1.AddRatePlan();

		PO2.FirstModal(ratePlanDetail.getName(), ratePlanDetail.getCode(),
				String.valueOf(ratePlanDetail.getAdultsIncluded()),
				String.valueOf(ratePlanDetail.getChildrenIncluded()));

		PO3.SecondModal();

		PO4.ThirdModal();

		PO5.FourthModal(ratePlanDetail.getRatePlanStrategies().get(0).getBasePrice());

		click(LocatorsStrategiesModal.BUTTON_SAVE);

	}

	public void CreatePackageRatePlan() {

		navigateToRates();

		PA1.AddPackageRatePlan();

		PO2.FirstModal(ratePlanDetail.getName(), ratePlanDetail.getCode(),
				String.valueOf(ratePlanDetail.getAdultsIncluded()),
				String.valueOf(ratePlanDetail.getChildrenIncluded()));

		PO3.SecondModal();

		PO4.ThirdModal();

		PO5.FourthModal(ratePlanDetail.getRatePlanStrategies().get(0).getBasePrice());

		PO6.FifthModal(ratePlanDetail.getTransactionItemId());

		click(LocatorsComponentsModal.BUTTON_SAVE);

	}

}