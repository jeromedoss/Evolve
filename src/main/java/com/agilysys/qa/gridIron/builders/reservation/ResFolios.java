package com.agilysys.qa.gridIron.builders.reservation;

import com.agilysys.insertanator.objectStores.MasterObject;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.agilysys.qa.gridIron.builders.shared.folios.AddAChargeModal.stepAddChargeForAnItem;
import static com.agilysys.qa.gridIron.builders.shared.folios.RoutingRulesModal.stepRoutingRule;
import static com.agilysys.qa.gridIron.builders.shared.folios.TransferModal.stepTransfer;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails.clickAddACharge;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails.clickAddACredit;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails.clickButtonAddRoutingRule;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails.clickMakePayment;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails.clickButtonSelectAllFolio1;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails.clickButtonTransfer;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails.clickTabFolio1;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails.navigatePaymentSection;
import static com.agilysys.qa.gridIron.pageobject.shared.POPayments.makeCashPayment;
import static com.agilysys.qa.gridIron.pageobject.shared.POAddCreditModal.addCredit;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.waitForElementToLoad;

/*
 * *Author - Harish Baskaran - 2018
 */
public class ResFolios {

	String ChargeName = null;

	public ResFolios(String ChargeName) {
		this.ChargeName = ChargeName;

	}

	public void flowAddChargeForAnItem() {

		navigatePaymentSection();
		flowAddChargeForAnItem1();

	}

	public void flowAddChargeForAnItem1() {

		clickAddACharge();
		stepAddChargeForAnItem(ChargeName);

	}

	public void flowTransferfromMoreButton(String Input) {

		clickTabFolio1();
		clickButtonSelectAllFolio1();
		clickButtonTransfer();
		stepTransfer(Input);

	}

	public void flowAddCreditForAnItem() {

		navigatePaymentSection();
		clickAddACredit();
		addCredit(ChargeName);

	}

	public void flowMakePayment(String Payment) {

		clickMakePayment();
		makeCashPayment(Payment);

	}

	public void flowAddRoutingRule(String Company) {

		navigatePaymentSection();
		clickButtonAddRoutingRule();
		Selenide.sleep(20000);
		//waitForElementToLoad(By.xpath("//*[@data-qa-id='hoB1uYy-item-label-text']"), 60000);
		stepRoutingRule(Company);
	}

}
