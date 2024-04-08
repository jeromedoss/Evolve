package com.agilysys.qa.gridIron.constants.pagefactory.booking;

import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.deposits.LocatorsDepositTab.DROPDOWN_DEPOSIT;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.deposits.LocatorsDepositTab.DROPDOWN_VALUE_DEPOSIT;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.deposits.LocatorsDepositTab.INPUT_DEPOSIT_AMOUNT;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.deposits.LocatorsDepositTab.INPUT_PASSWORD;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.deposits.LocatorsDepositTab.INPUT_USERNAME;
import static com.agilysys.qa.gridIron.constants.locators.booking.wizard.deposits.LocatorsDepositTab.LABEL_DEPOSIT_AMOUNT;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;

public class PFDepositTab extends PageFactoryBase {

	public static void selectDepositPaymentMethod(String paymentMethod) {
		click(LABEL_DEPOSIT_AMOUNT);
		selectByText(DROPDOWN_DEPOSIT, DROPDOWN_VALUE_DEPOSIT, paymentMethod);
	}

	public static void setNoDeposit() {
		typeDepositAmount("0.00");
	}

	public static void typeDepositAmount(String depositAmount) {
		if (depositAmount == null) {
			return;
		}
		type(INPUT_DEPOSIT_AMOUNT, depositAmount);
		click(INPUT_DEPOSIT_AMOUNT);
	}

	public static void setCredentials() {

		clearAndType(INPUT_USERNAME, Endpoints.getUserName());
		clearAndType(INPUT_PASSWORD, Endpoints.getPassword());

	}
}