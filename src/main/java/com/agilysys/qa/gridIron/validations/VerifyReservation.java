package com.agilysys.qa.gridIron.validations;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.agilysys.pms.profile.model.Address;
import com.agilysys.pms.profile.model.AddressDetails;
import com.agilysys.pms.profile.model.Email;
import com.agilysys.pms.profile.model.EmailDetails;
import com.agilysys.pms.profile.model.Phone;
import com.agilysys.pms.profile.model.PhoneDetails;
import com.agilysys.pms.reservation.model.ThirdPartyConfirmation;
import com.agilysys.common.model.TrackingInfo;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsFooterButtonsSection;
import com.agilysys.qa.gridIron.constants.locators.reservation.estimatedcharges.LocatorsEstimatedChargesSection;
import com.agilysys.qa.gridIron.constants.locators.reservation.guestinformation.LocatorsGuestInformationSection;
import com.agilysys.qa.gridIron.constants.locators.reservation.payments.LocatorsPaymentsSection;
import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsCurrentlySelectedRoom;
import com.agilysys.qa.gridIron.constants.locators.reservation.summary.LocatorsReservationDetailsModal;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFPaymentMethodsSection;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationEstimatedCharges;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationFoliosSection;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationGuestInfo;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationSummary;
import com.agilysys.qa.gridIron.data.models.ReservationPaymentMethod;
import com.agilysys.qa.gridIron.utils.HelperMethods;
import com.codeborne.selenide.Condition;

public class VerifyReservation extends ValidationBase {

	public static void verifyGuestAddress(AddressDetails addressDetails) {
		addressDetails.getAddresses().forEach((Address address) -> {
			verifyGuestAddress(address);
		});
	}

	public static void verifyGuestAddress(Address expectedAddress) {
		PFReservationGuestInfo.expandGuestInformation();
		int index = findElementIndexByValue(expectedAddress.getAddressLine1(),
				LocatorsGuestInformationSection.INPUT_STREET);
		verifyElementValue(expectedAddress.getAddressType(),
				$$(LocatorsGuestInformationSection.DROPDOWN_ADDRESS_TYPE).get(index));
		verifyElementValue(expectedAddress.getAddressLine1(),
				$$(LocatorsGuestInformationSection.INPUT_STREET).get(index));
		verifyElementValue(expectedAddress.getAddressLine2(),
				$$(LocatorsGuestInformationSection.INPUT_ADD_ADDRESS_LINE_2).get(index));
		verifyElementValue(expectedAddress.getAddressLine3(),
				$$(LocatorsGuestInformationSection.INPUT_ADD_ADDRESS_LINE_3).get(index));
		verifyElementValue(expectedAddress.getAddressLine4(),
				$$(LocatorsGuestInformationSection.INPUT_ADD_ADDRESS_LINE_4).get(index));
		verifyElementValue(expectedAddress.getAddressLine5(),
				$$(LocatorsGuestInformationSection.INPUT_ADD_ADDRESS_LINE_5).get(index));
		verifyElementValue(expectedAddress.getCityName(), $$(LocatorsGuestInformationSection.INPUT_CITY).get(index));
		verifyElementValue(expectedAddress.getStateProvinceCode(),
				$$(LocatorsGuestInformationSection.INPUT_STATE).get(index));
		verifyElementValue(expectedAddress.getPostalCode(), $$(LocatorsGuestInformationSection.INPUT_ZIP).get(index));
		verifyElementValue(expectedAddress.getCountryCode(),
				$$(LocatorsGuestInformationSection.INPUT_COUNTRY).get(index));
		verifyElementValue(expectedAddress.getCountyCode(),
				$$(LocatorsGuestInformationSection.INPUT_COUNTY).get(index));
		PFReservationGuestInfo.collapaseGuestInformation();
	}

	public static void verifyGuestPhone(PhoneDetails phoneDetails) {
		phoneDetails.getPhones().forEach((Phone phone) -> {
			verifyGuestPhone(phone);
		});
	}

	public static void verifyGuestPhone(Phone expectedPhone) {
		PFReservationGuestInfo.expandGuestInformation();
		int index = findElementIndexByValue(expectedPhone.getNumber(),
				LocatorsGuestInformationSection.INPUT_PHONE_NUMBER);
		verifyElementValue(expectedPhone.getPhoneType(),
				$$(LocatorsGuestInformationSection.DROPDOWN_PHONE_TYPE).get(index));
		verifyElementValue(expectedPhone.getNumber(),
				$$(LocatorsGuestInformationSection.INPUT_PHONE_NUMBER).get(index));
		verifyElementValue(expectedPhone.getExtension(),
				$$(LocatorsGuestInformationSection.INPUT_EXTENSION).get(index));
		PFReservationGuestInfo.collapaseGuestInformation();
	}

	public static void verifyGuestEmail(EmailDetails emailDetails) {
		emailDetails.getEmailAddresses().forEach((Email email) -> {
			verifyGuestEmail(email);
		});
	}

	public static void verifyGuestEmail(Email expectedEmail) {
		PFReservationGuestInfo.expandGuestInformation();
		int index = findElementIndexByValue(expectedEmail.getEmailAddress(),
				LocatorsGuestInformationSection.INPUT_EMAIL);
		verifyElementValue(expectedEmail.getType(), $$(LocatorsGuestInformationSection.DROPDOWN_EMAIL_TYPE).get(index));
		verifyElementValue(expectedEmail.getEmailAddress(), $$(LocatorsGuestInformationSection.INPUT_EMAIL).get(index));
		PFReservationGuestInfo.collapaseGuestInformation();
	}

	public static void verifyTrackingInfo(TrackingInfo expectedTrackingInfo) {
		PFReservationSummary.expandReservationSummary();
		waitForElementValueToLoad(expectedTrackingInfo.getSourceCode(),
				LocatorsReservationDetailsModal.INPUT_SOURCE_OF_BUSINESS);
		verifyElementValue(expectedTrackingInfo.getSourceCode(),
				LocatorsReservationDetailsModal.INPUT_SOURCE_OF_BUSINESS);
		verifyElementValue(expectedTrackingInfo.getSegmentCode(), LocatorsReservationDetailsModal.INPUT_MARKET_SEGMENT);
		verifyElementValue(expectedTrackingInfo.getGuestType(), LocatorsReservationDetailsModal.INPUT_GUEST_TYPE);
		PFReservationSummary.collapseReservationSummary();
	}

	public static void verifyTravelAgents(List<String> expectedTravelAgents) {
		PFReservationSummary.expandReservationSummary();
		expectedTravelAgents.forEach((String travelAgent) -> {
			waitForElementValueToLoad(travelAgent, LocatorsReservationDetailsModal.INPUT_TRAVEL_AGENT);
			Assert.assertEquals(
					$$(LocatorsReservationDetailsModal.INPUT_TRAVEL_AGENT).filter(Condition.value(travelAgent)).size(),
					1, "Travel Agent not found in reservation");
		});
		PFReservationSummary.collapseReservationSummary();
	}

	public static void verifyThirdPartyConfirmations(Set<ThirdPartyConfirmation> expectedThirdPartyConfirmations) {
		PFReservationSummary.expandReservationSummary();
		expectedThirdPartyConfirmations.forEach((ThirdPartyConfirmation thirdPartyConfirmation) -> {
			waitForElementValueToLoad(thirdPartyConfirmation.getConfirmationName(),
					LocatorsReservationDetailsModal.INPUT_THIRD_PARTY_SOURCE);
			Assert.assertEquals(
					$$(LocatorsReservationDetailsModal.INPUT_THIRD_PARTY_SOURCE)
							.filter(Condition.value(thirdPartyConfirmation.getConfirmationName())).size(),
					1, "Third party source not found in reservation");
			Assert.assertEquals(
					$$(LocatorsReservationDetailsModal.INPUT_CONFORMATION_NUMBER)
							.filter(Condition.value(thirdPartyConfirmation.getConfirmationNumber())).size(),
					1, "Third part confirmation number not found in reservation");
		});
		PFReservationSummary.collapseReservationSummary();
	}

	public static void verifyPaymentMethod(String paymentMethodName, boolean isDefault) {
		Assert.assertTrue($(LocatorsPaymentsSection.getPaymentMethodByName(paymentMethodName)).getText()
				.equalsIgnoreCase(paymentMethodName));
		String defaultPaymentMethod = PFPaymentMethodsSection.getDefaultPaymentMethodName();
		if (isDefault) {
			Assert.assertTrue(defaultPaymentMethod.equalsIgnoreCase(paymentMethodName),
					"Default payment method not equal to " + paymentMethodName + ". Default payment method set as "
							+ defaultPaymentMethod);
		} else {
			Assert.assertFalse(defaultPaymentMethod.equalsIgnoreCase(paymentMethodName),
					paymentMethodName + " payment method should not be default. " + "Default payment method equals to "
							+ defaultPaymentMethod);
		}
	}

	public static void verifyDeposit(String paymentMethodName, String expectedDepositAmount) {
		PFReservationFoliosSection.clickDefaultFolio();
		Assert.assertEquals(PFReservationFoliosSection.getLineItemTotalWithoutCurrency(paymentMethodName),
				expectedDepositAmount);
	}

	public static void verifyDeposit(String paymentMethodName, BigDecimal expectedDepositAmount) {
		PFReservationFoliosSection.clickDefaultFolio();
		Assert.assertEquals(PFReservationFoliosSection.getLineItemTotal(paymentMethodName),
				"-" + HelperMethods.formatAmount(expectedDepositAmount));
	}

	public static void verifyFolioLineItemTotal(String lineItem, BigDecimal total) {
		Assert.assertEquals(PFReservationFoliosSection.getLineItemTotal(lineItem), HelperMethods.formatAmount(total));
		//Assert.assertEquals(PFReservationFoliosSection.getLineItemTotal(lineItem), total.toString());
	}

	public static void verifyFolioLineItemTotalByReason(String reason, BigDecimal total) {
		Assert.assertEquals(PFReservationFoliosSection.getLineItemTotalByReason(reason),
				HelperMethods.formatAmount(total));
		//Assert.assertEquals(PFReservationFoliosSection.getLineItemTotalByReason(reason), total.toString());
	}

	public static void verifyEstimatedChargesLineItemNotPresent(LocalDate date, String lineItemName) {
		PFReservationEstimatedCharges.expandEstimatedCharges();
		verifyElementNotPresent(LocatorsEstimatedChargesSection.getLineItemChargeAmount(date, lineItemName));
	}

	public static void verifyEstimatedChargesLineItemAmount(LocalDate date, String lineItemName,
															BigDecimal expectedAmount) {
		PFReservationEstimatedCharges.expandEstimatedCharges();
		verifyElementValue(HelperMethods.formatAmount(expectedAmount),
				$(LocatorsEstimatedChargesSection.getLineItemChargeAmount(date, lineItemName)));
	}

	public static void verifyEstimatedChargesLineItemQuantity(LocalDate date, String lineItemName,
															  int expectedQuantity) {
		PFReservationEstimatedCharges.expandEstimatedCharges();
		verifyElementText(String.valueOf(expectedQuantity),
				$(LocatorsEstimatedChargesSection.getLineItemQuantity(date, lineItemName)));
	}

	public static void verifyPackageRoomChargeAmount(LocalDate date, String lineItemName, BigDecimal expectedAmount){
		sleep(500);
		PFReservationEstimatedCharges.expandPackage(date);
		verifyElementValue(HelperMethods.CommaRemoveInAmount(HelperMethods.formatAmount(expectedAmount)),
				$(LocatorsEstimatedChargesSection.getPackageRoomChargeAmount(date, lineItemName)));
	}

	public static void verifyPackageComponentAmount(LocalDate date, String lineItemName, BigDecimal expectedAmount) {
		PFReservationEstimatedCharges.expandPackage(date);
		System.out.println(LocatorsEstimatedChargesSection.getPackageComponentAmount(date, lineItemName));
		verifyElementValue(HelperMethods.formatAmount(expectedAmount),
				$(LocatorsEstimatedChargesSection.getPackageComponentAmount(date, lineItemName)));

	}

	public static void verifyPackageComponentQuantity(LocalDate date, String lineItemName, int quantity) {
		PFReservationEstimatedCharges.expandPackage(date);
		verifyElementText(String.valueOf(quantity),
				$(LocatorsEstimatedChargesSection.getPackageComponentQuantity(date, lineItemName)));
	}

	public static void verifyAuthAmount(String paymentMethod, BigDecimal expectedAuthAmount) {
		PFPaymentMethodsSection.clickPaymentMethod(paymentMethod);
		BigDecimal actualAuthAmount = PFPaymentMethodsSection.getAuthAmount();
		Assert.assertEquals(actualAuthAmount, expectedAuthAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	public static void verifyBadgeHighlighted(By badgeLocator) {
		verifyElementAttributeContains(badgeLocator, "class", "selected");
	}

	public static void verifyBadgeNotHighlighted(By badgeLocator) {
		Assert.assertTrue(!$(badgeLocator).getAttribute("class").contains("selected"));
	}

	public static void verifyReservationRoomNumber(String expectedRoomNumber) {
		verifyElementText(expectedRoomNumber, LocatorsCurrentlySelectedRoom.TEXT_CURRENTLY_SELECTED_ROOM);
	}

	public static void verifyCheckIn() {
		$(LocatorsFooterButtonsSection.BUTTON_CHECKOUT).shouldBe(Condition.enabled);
		$(LocatorsFooterButtonsSection.BUTTON_UNDO_CHECKIN).shouldBe(Condition.enabled);
	}

	public static void verifyUndoCheckIn() {
		$(LocatorsFooterButtonsSection.BUTTON_CHECKIN).shouldBe(Condition.enabled);
		$(LocatorsFooterButtonsSection.BUTTON_CANCEL_RESERVATION).shouldBe(Condition.enabled);
	}

	public static void verifyReservationCancel() {
		$(LocatorsFooterButtonsSection.BUTTON_UNDO_CANCEL).shouldBe(Condition.enabled);
	}

	public static void verifyReservationUndoCancel() {
		$(LocatorsFooterButtonsSection.BUTTON_CANCEL_RESERVATION).shouldBe(Condition.enabled);
	}
}