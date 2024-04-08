package com.agilysys.qa.gridIron.pageobject.profiles.guest;


import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage.ClickSavebutton;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage.clickMarketingSideMenu;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage.selectGuestType;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage.selectMarketSegment;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage.selectSourceOfBusiness;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;

import com.agilysys.pms.profile.model.Address;
import com.agilysys.pms.profile.model.Email;
import com.agilysys.pms.profile.model.Identity;
import com.agilysys.pms.profile.model.PersonalDetails;
import com.agilysys.pms.profile.model.Phone;
import com.agilysys.pms.profile.model.PhoneDetails;
import com.agilysys.pms.profile.model.v1.GuestProfile;
import com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfileContact;
import com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfileIdentity;
import com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage;
import com.agilysys.qa.gridIron.pageobject.home.profiletile.CreateNewProfile;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;



/*
 * *Author - Harish Baskaran - 2018
 */
public class POGuestProfilePage {
	
	public static void setPersonalDetails(PersonalDetails personalDetails){
		PFGuestProfilePage.typeLastName(personalDetails.getLastName());
		PFGuestProfilePage.typeFirstName(personalDetails.getFirstName());
		PFGuestProfilePage.typeMiddleInital(personalDetails.getMiddleInitial());
		PFGuestProfilePage.typeAlias(personalDetails.getAlias());
		PFGuestProfilePage.typeDOB(personalDetails.getBirthDate());
		
		PFGuestProfilePage.typeCompanyName(personalDetails.getCompanyName());
		PFGuestProfilePage.typeCompanyTitle(personalDetails.getCompanyTitle());
		PFGuestProfilePage.typeLanguage(personalDetails.getLanguage());
		PFGuestProfilePage.typePronounced(personalDetails.getNamePronunciation());
		
		PFGuestProfilePage.typeAnniversary(personalDetails.getAnniversary());
		
		PFGuestProfilePage.selectGender(personalDetails.getGender());
		PFGuestProfilePage.selectSuffix(personalDetails.getSuffix());
		PFGuestProfilePage.selectTitle(personalDetails.getTitle());		
	}
	
	public static void addAddressDetails(int addressCount, Address address){		
		PFGuestProfileContact pfGuestProfileContact = new PFGuestProfileContact(addressCount, 0, 0);		
		pfGuestProfileContact.clickContactSideMenu();
		if(addressCount>0){
			pfGuestProfileContact.clickAddNewAddress();
		}
		pfGuestProfileContact.typeAddressLine1(address.getAddressLine1());		
		pfGuestProfileContact.typeAddressLine2(address.getAddressLine2());
		pfGuestProfileContact.selectAddressType(address.getAddressType());		
		pfGuestProfileContact.typeCity(address.getCityName());
		pfGuestProfileContact.typeAddressLine3(address.getAddressLine3());
		pfGuestProfileContact.typeAddressLine4(address.getAddressLine4());
		pfGuestProfileContact.typeAddressLine5(address.getAddressLine5());
		pfGuestProfileContact.typeState(address.getStateProvinceCode());
		pfGuestProfileContact.typePostalCode(address.getPostalCode());
		pfGuestProfileContact.typeCountry(address.getCountryCode());		
		pfGuestProfileContact.typeCounty(address.getCountyCode());
		pfGuestProfileContact.setPrivateAddress(address.getIsPrivateAddress());
		pfGuestProfileContact.setDefaultAddress(address.getIsDefault());
	}
	
	public static void addTrackingInfo(String sourceOfBusiness, String guestType, String marketSegment) {
		clickMarketingSideMenu();
		selectSourceOfBusiness(sourceOfBusiness);
		selectGuestType(guestType);
		selectMarketSegment(marketSegment);
	}
	
	public static void addPhoneDetails(int phoneCount, Phone phone){
		PFGuestProfileContact pfGuestProfileContact = new PFGuestProfileContact(0, phoneCount, 0);
		pfGuestProfileContact.clickContactSideMenu();		
		if(phoneCount>0){
			pfGuestProfileContact.clickAddNewPhone();
		}
		pfGuestProfileContact.typePhonenumberinput(phone.getNumber());
		pfGuestProfileContact.selectPhoneType(phone.getPhoneType());		
		pfGuestProfileContact.typePhoneExtension(phone.getExtension());
		pfGuestProfileContact.checkIsPhonePrivate(phone.getIsPrivate());
	}
	
	public static void addEmailDetails(int emailCount, Email email){
		PFGuestProfileContact pfGuestProfileContact = new PFGuestProfileContact(0, 0, emailCount);
		pfGuestProfileContact.clickContactSideMenu();		
		if(emailCount>0){
			pfGuestProfileContact.clickAddNewEmail();
		}
		pfGuestProfileContact.typeEmail(email.getEmailAddress());
		pfGuestProfileContact.selectEmailType(email.getType());
		pfGuestProfileContact.checkIsEmailPrivate(email.getIsPrivate());
	}
	
	public static void addIdDetail(int idCount, Identity identity){
		PFGuestProfileIdentity.clickSidepaneId();
		PFGuestProfileIdentity pfGuestProfileIdentity = new PFGuestProfileIdentity(idCount);
		pfGuestProfileIdentity.clickAddId();
		pfGuestProfileIdentity.selectIdType(identity.getType());
		pfGuestProfileIdentity.typeIdNumber(identity.getIdNumber());
		pfGuestProfileIdentity.typeIssuingAgency(identity.getIssuingAgency());
		pfGuestProfileIdentity.setExpirateDate(identity.getExpirationDate());
	}
	
	public static void addComment(String comment, String commentType, String commentSeverity){
		Selenide.sleep(3000);
		PFGuestProfilePage.clickSidepaneComments();
		PFGuestProfilePage.clickAddCommentButton();
		PFGuestProfilePage.selectCommentType(commentType);
		PFGuestProfilePage.selectCommentSeverity(commentSeverity);
		PFGuestProfilePage.typeComment(comment);
		PFGuestProfilePage.clickCommentSave();		
	}
	
	public static void addPaymentMethod(String paymentMethod, boolean isDefault, String directBillAccount, String swipeData){
		PFGuestProfilePage.clickSidepanePayments();		
		PFGuestProfilePage.clickAddPaymentMethod();
		PFGuestProfilePage.clickPaymentIsDefault(isDefault);
		PFGuestProfilePage.selectPaymentMethod(paymentMethod);		
		PFGuestProfilePage.typeDirectBillAccount(directBillAccount);
		if(swipeData !=null){
			Selenide.sleep(3000);
			Selenide.actions().sendKeys(Keys.NUMPAD0, swipeData, Keys.ENTER).build().perform();
		}
		PFGuestProfilePage.clickSavePaymentMethod();
	}
	
	public static void addGuest(GuestProfile guestProfile, String sourceOfBusiness, String guestType, String marketSegment){
		PersonalDetails personalDetails = guestProfile.getPersonalDetails();
		List<Address> addresses = guestProfile.getAddressDetails().getAddresses();
		List<Phone> phones = guestProfile.getPhoneDetails().getPhones();
		List<Email> emails = guestProfile.getEmailDetails().getEmailAddresses();
		
		CreateNewProfile.stepClickCreateGuestFromMain();
		setPersonalDetails(personalDetails);
		Selenide.sleep(3000);
		if(!addresses.isEmpty() && addresses != null){
			int i=0;
			for(Address address : addresses){
				addAddressDetails(i, address);
				i++;
			}
		}
		
		if(!phones.isEmpty() && phones != null){
			int i=0;
			for(Phone phone : phones){
				addPhoneDetails(i, phone);
				i++;
			}
		}
		
		if(!emails.isEmpty() && emails != null){
			int i=0;
			for(Email email : emails){
				addEmailDetails(i, email);
				i++;
			}
		}
		
		if(guestProfile.getIdentityDetails() != null){
			List<Identity> identities = guestProfile.getIdentityDetails().getIdentities();
			if(!identities.isEmpty() && identities != null){
				int i=0;
				for(Identity identity : identities){
					addIdDetail(i, identity);
					i++;
				}
			}						
		}
		addTrackingInfo(sourceOfBusiness, guestType, marketSegment);
	}
	
	public static GuestProfile createGuestProfile(){
		GuestProfile guestProfile = new GuestProfile();
		
		PersonalDetails personalDetails = new PersonalDetails();
        personalDetails.setLastName(RandomHelper.getRandomAlphaNumericString(10));
        personalDetails.setFirstName(RandomHelper.getRandomAlphaNumericString(10));
        
        Phone phone = new Phone();
        phone.setPhoneType("Business");
        phone.setNumber(RandomHelper.getRandomAlphaNumericString(10));
        phone.setExtension(RandomHelper.getRandomAlphaNumericString(5));
        phone.setIsPrivate(true);
        
        
        PhoneDetails phoneDetails = new PhoneDetails();
        List<Phone> phones = new ArrayList<Phone>();
        phones.add(phone);
        phoneDetails.setPhones(phones);
        
        guestProfile.setPersonalDetails(personalDetails);
        guestProfile.setPhoneDetails(phoneDetails);
        
        addGuest(guestProfile, null, null, null);
        ClickSavebutton();        
        return guestProfile;         
	}
	
	public static void saveProfilePage(){
		PFGuestProfilePage.ClickSavebutton();
	}
}