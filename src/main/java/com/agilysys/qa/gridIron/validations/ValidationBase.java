package com.agilysys.qa.gridIron.validations;

import static com.codeborne.selenide.Selenide.$$;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;

import static com.agilysys.qa.gridIron.constants.locators.booking.LocatorsBookAReservationPage.getRatePlanArrow;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.agilysys.qa.gridIron.utils.HelperMethods;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import com.codeborne.selenide.SelenideElement;

public class ValidationBase {

	public static void verifyElementPriceValue(String expectedValue, By locator) {
		if (expectedValue == null) {
			return;
		}

		try {
			String actualValue = HelperMethods
					.formatAmount(BigDecimal.valueOf(Float.parseFloat($(locator).getValue().substring(1))));
			Assert.assertEquals(actualValue, expectedValue);
		} catch (Exception e) {
			Assert.assertEquals($(locator).getValue(), expectedValue);
		}
	}

	public static void verifyElementValue(String expectedValue, By locator) {
		if (expectedValue == null) {
			return;
		}

		Assert.assertEquals($(locator).should(Condition.visible, Duration.ofMillis(Configuration.timeout)).getValue(), expectedValue);

	}

	public static void verifyElementValue(String expectedValue, SelenideElement element) {
		if (expectedValue == null) {
			return;
		}
		try {
			String actualValue = HelperMethods
					.formatAmount(BigDecimal.valueOf(Float.parseFloat(element.getValue().substring(1))));
			Assert.assertEquals(actualValue, expectedValue);
		} catch (Exception e) {
			Assert.assertEquals(element.getValue(), expectedValue);
		}
	}

	public static void verifyElementText(String expectedText, By locator) {
		if (expectedText == null) {
			return;
		}
		Selenide.sleep(2000);
		SelenideElement locatorToBeValidated =$(locator).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		Assert.assertEquals(locatorToBeValidated.getText(), expectedText);
	}

	public static void verifyElementText(String expectedText, SelenideElement element) {
		if (expectedText == null) {
			return;
		}
		element.should(Condition.visible, Duration.ofMillis(Configuration.timeout));
		element.hover();
		Selenide.executeJavaScript("arguments[0].setAttribute('style', 'border: 2px solid red;')", element);
		Assert.assertEquals(element.getText(), expectedText);
	}

	public static void waitForElementValueToLoad(String value, By by) {
		int time = 0;
		while (time <= Configuration.timeout) {
			if ($$(by).filter(Condition.value(value)).size() > 0) {
				return;
			}
			Selenide.sleep(1000);
			time = time + 1000;
		}
	}

	public static void verifyElementNotPresent(By locator) {
		Assert.assertEquals($$(locator).size(), 0, "Element present");
	}

	public static int findElementIndexByValue(String value, By by) {
		waitForElementValueToLoad(value, by);
		ElementsCollection elements = $$(by);
		int i = 0;
		for (SelenideElement element : elements) {
			if (element.getValue().equalsIgnoreCase(value)) {
				return i;
			}
			i++;
		}
		Assert.fail("Element with value " + value + " not found");
		return -1;
	}

	public static void verifyElementAttributeContains(By elementLocator, String attributeName, String expected) {
		String actual = $(elementLocator).attr(attributeName);
		Assert.assertTrue(actual.contains(expected), "Actula is " + actual + ", " + expected);
	}

	public static void verifyElementAttributeContains(SelenideElement elementLocator, String attributeName, String expected) {
		String actual = elementLocator.attr(attributeName);
		Assert.assertTrue(actual.contains(expected), "Actula is " + actual + ", " + expected);
	}

	public static void verifyCheckBox(boolean isChecked, By locator) {
		boolean actual = Arrays.asList($(locator).getAttribute("class").split(" ")).contains("active");
		Assert.assertEquals(actual, isChecked);
	}
}
