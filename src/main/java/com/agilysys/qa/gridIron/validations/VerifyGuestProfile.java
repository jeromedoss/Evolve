package com.agilysys.qa.gridIron.validations;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;

import org.testng.Assert;

import com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

public class VerifyGuestProfile extends ValidationBase{
	
	public static void verifyPaymentMethod(String paymentMethodName, boolean isDefault){
		String actual = $(LocatorsGuestProfilePage.getPaymentMethodByName(paymentMethodName)).should(Condition.visible, Duration
			.ofMillis(Configuration.timeout)).getText();
		Assert.assertEquals(actual, paymentMethodName, "Payment method not found on guest profile");
		
		String defaultPaymentMethod = $(LocatorsGuestProfilePage.SPAN_DEFAULT_PAYMENT_METHOD_NAME).getText();
		if(isDefault){
			Assert.assertTrue(defaultPaymentMethod.equalsIgnoreCase(paymentMethodName), "Default payment method not equal to "+paymentMethodName+". Default payment method set as "+defaultPaymentMethod);			
		}else{
			Assert.assertFalse(defaultPaymentMethod.equalsIgnoreCase(paymentMethodName), paymentMethodName +" payment method should not be default. "+"Default payment method equals to "+ defaultPaymentMethod);			
		}
	}

}
