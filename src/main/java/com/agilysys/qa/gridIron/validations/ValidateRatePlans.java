package com.agilysys.qa.gridIron.validations;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.testng.Assert;

import com.agilysys.pms.rates.model.RatePlanDetail;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import com.agilysys.qa.gridIron.constants.locators.rates.LocatorsRatesPage;
import com.agilysys.qa.gridIron.constants.locators.rates.packages.LocatorsComponentsModal;
import com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsDetailsModal;
import com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsPoliciesModal;
import com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsRatePage;
import com.agilysys.qa.gridIron.constants.locators.rates.rateplans.LocatorsStrategiesModal;

public class ValidateRatePlans {

	private boolean RatePlanName = false;
	private boolean RatePlanCode = false;
	private boolean RatePlanAdultsIncluded = false;
	private boolean RatePlanChildrenIncluded = false;
	private boolean RatePlanCancellationPolicy = false;
	private boolean RatePlanDepositPolicy = false;
	private boolean RatePlanBasePrice = false;
	private boolean ComponentsPrice = false;
	private boolean ComponentsName = false;
	private boolean ComponentsQuantity = false;

	LocatorsRatePage RP = null;

	public ValidateRatePlans() {
		RP = new LocatorsRatePage();
	}

	public ValidateRatePlans VerifyAll() {
		RatePlanName = true;
		RatePlanCode = true;
		RatePlanAdultsIncluded = true;
		RatePlanChildrenIncluded = true;
		RatePlanCancellationPolicy = true;
		RatePlanDepositPolicy = true;
		RatePlanBasePrice = true;

		return this;
	}

	public ValidateRatePlans VerifyRequiredFields() {
		RatePlanName = true;
		RatePlanCode = true;
		RatePlanAdultsIncluded = true;
		RatePlanChildrenIncluded = true;
		RatePlanCancellationPolicy = true;
		RatePlanBasePrice = true;
		return this;
	}

	public ValidateRatePlans VerifyComponents() {
		ComponentsPrice = true;
		ComponentsName = true;
		ComponentsQuantity = true;

		return this;
	}

	public void Validate(RatePlanDetail ratePlanDetail) {

		Selenide.sleep(2000);

		Selenide.refresh();

		click($$(LocatorsRatesPage.LIST_RATE_PLANS).findBy(Condition.text(ratePlanDetail.getName()))
				.shouldBe(Condition.exist, Condition.visible).closest("li"));

		if (RatePlanName) {
			Assert.assertEquals($(LocatorsDetailsModal.INPUT_NAME).getValue(), ratePlanDetail.getName());
		}

		if (RatePlanCode) {
			Assert.assertEquals($(LocatorsDetailsModal.INPUT_CODE).getValue(), ratePlanDetail.getCode());
		}

		if (RatePlanAdultsIncluded) {
			Assert.assertEquals($$(LocatorsDetailsModal.INPUT_ADULT).get(0).getValue(),
					String.valueOf(ratePlanDetail.getAdultsIncluded()));
		}

		if (RatePlanChildrenIncluded) {
			Assert.assertEquals($$(LocatorsDetailsModal.INPUT_CHILD).get(1).getValue(),
					String.valueOf(ratePlanDetail.getChildrenIncluded()));
		}

		if (RatePlanBasePrice) {
			click(LocatorsRatePage.LINK_BASE_RATE);
			click(LocatorsRatePage.HEADER_MODAL_RATE);
			String BaseRate = $(LocatorsStrategiesModal.INPUT_BASE_RATE).getValue();
			Selenide.sleep(2000);
			click(LocatorsRatePage.HEADER_MODAL_RATE);
			click(LocatorsRatePage.MODAL_RATE_BUTTON_CLOSE);
			Assert.assertTrue(
					BaseRate.contains(ratePlanDetail.getRatePlanStrategies().get(0).getBasePrice().toString()),
					"Base Rate is not Matched");
		}

		if (RatePlanCancellationPolicy) {
			click(LocatorsPoliciesModal.DROPDOWN_CANCELLATION_POLICY);
			Assert.assertEquals(
					$$(LocatorsPoliciesModal.DROPDOWN_LIST_CANCELLATION_POLICIES)
							.findBy(Condition.attribute("Class", "ng-scope selected-item")).getText(),
					ratePlanDetail.getCancellationPolicyId());
		}

	}

	public void ValidateComponents(RatePlanDetail ratePlanDetail, String Quantity, String Amount) {

		if (ComponentsPrice) {
			Assert.assertTrue($$(LocatorsComponentsModal.LIST_AMOUNT).get(0).getText().contains(Amount),
					"Component Price is different");
		}
		if (ComponentsQuantity) {
			Assert.assertTrue($$(LocatorsComponentsModal.LIST_QUANTITY).get(0).getText().contains(Quantity),
					"Quantity is different");
		}
		if (ComponentsName) {
			Assert.assertTrue($$(LocatorsComponentsModal.LIST_DESCRIPTION).get(0).getText()
					.contains(ratePlanDetail.getTransactionItemId()), "Item is different");
		}
	}
}
