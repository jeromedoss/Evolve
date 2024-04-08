package com.agilysys.qa.gridIron.validations;

import com.agilysys.pms.profile.model.Address;
import com.agilysys.pms.profile.model.CompanySummary;
import com.agilysys.pms.profile.model.Email;
import com.agilysys.pms.profile.model.Phone;
import com.agilysys.qa.gridIron.constants.locators.profiles.company.LocatorsCompanyProfilePage;

public class VerifyCompanyProfile extends ValidationBase {
	
	public static void verifyCompanySummary(CompanySummary companySummary){
		verifyElementValue(companySummary.getCompanyName(), LocatorsCompanyProfilePage.INPUT_NAME );
		verifyElementValue(companySummary.getCompanyCode(), LocatorsCompanyProfilePage.INPUT_CODE );
		verifyElementValue(companySummary.getPronounced(), LocatorsCompanyProfilePage.INPUT_PRONOUNCED);
		verifyElementValue(companySummary.getCompanyWebsite(), LocatorsCompanyProfilePage.INPUT_WEBSITE);
		if(companySummary.getNegotiatedRatePlanId() != null){
			waitForElementValueToLoad(companySummary.getNegotiatedRatePlanId(), LocatorsCompanyProfilePage.DROPDOWN_RATE_PLAN );
			verifyElementValue(companySummary.getNegotiatedRatePlanId(), LocatorsCompanyProfilePage.DROPDOWN_RATE_PLAN );
		}
			
	}
	
	public static void verifyMarketing(String guestType, String sourceOfBusiness,  String marketSegment){
		verifyElementValue(guestType, LocatorsCompanyProfilePage.DROPDOWN_GUEST_TYPE );
		verifyElementValue(sourceOfBusiness, LocatorsCompanyProfilePage.DROPDOWN_SOURCE_OF_BUSINESS );
		verifyElementValue(marketSegment, LocatorsCompanyProfilePage.DROPDOWN_MARKET_SEGMENT);
	}

	public static void verifyAddress(Address address){
		verifyElementValue(address.getAddressType(), LocatorsCompanyProfilePage.DROPDOWN_ADRESSS_TYPE );
		verifyElementValue(address.getAddressLine1(), LocatorsCompanyProfilePage.INPUT_STREET );
		verifyElementValue(address.getAddressLine2(), LocatorsCompanyProfilePage.INPUT_ADDRESS_LINE_2 );
		verifyElementValue(address.getAddressLine3(), LocatorsCompanyProfilePage.INPUT_ADDRESS_LINE_3 );
		verifyElementValue(address.getAddressLine4(), LocatorsCompanyProfilePage.INPUT_ADDRESS_LINE_4 );
		verifyElementValue(address.getAddressLine5(), LocatorsCompanyProfilePage.INPUT_ADDRESS_LINE_5 );
		verifyElementValue(address.getCityName(), LocatorsCompanyProfilePage.INPUT_CITY );
		verifyElementValue(address.getStateProvinceCode(), LocatorsCompanyProfilePage.INPUT_STATE );
		verifyElementValue(address.getPostalCode(), LocatorsCompanyProfilePage.INPUT_ZIP );
		verifyElementValue(address.getCountryCode(), LocatorsCompanyProfilePage.TYPEHEAD_COUNTRY );
		verifyElementValue(address.getCountyCode(), LocatorsCompanyProfilePage.INPUT_COUNTY );
	}
	
	public static void verifyPhone(Phone phone){
		verifyElementValue(phone.getPhoneType(), LocatorsCompanyProfilePage.DROPDOWN_PHONE_TYPE );
		verifyElementValue(phone.getNumber(), LocatorsCompanyProfilePage.INPUT_NUMBER );
		verifyElementValue(phone.getExtension(), LocatorsCompanyProfilePage.INPUT_EXTENSION );
	}
	
	public static void verifyEmail(Email email){
		verifyElementValue(email.getType(), LocatorsCompanyProfilePage.DROPDOWN_EMAIL_TYPE );
		verifyElementValue(email.getEmailAddress(), LocatorsCompanyProfilePage.INPUT_EMAIL_ADDRESS );
	}

}
