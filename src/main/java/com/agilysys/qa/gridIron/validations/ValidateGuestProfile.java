package com.agilysys.qa.gridIron.validations;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFProfileTile.clickTabProfiles;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage.getFirstnameinput;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage.getLastnameinput;
import static com.agilysys.qa.gridIron.constants.pagefactory.profiles.guest.PFGuestProfilePage.getPhonenumberinput;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$$;

import org.testng.Assert;

import com.agilysys.pms.profile.model.v1.GuestProfileCreateRequest;
import com.agilysys.qa.gridIron.constants.locators.home.ProfileTile;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.codeborne.selenide.Condition;

public class ValidateGuestProfile {

	private boolean firstName = false;
	private boolean lastName = false;
	private boolean phoneNo = false;
	private boolean eMail = false;

	public ValidateGuestProfile eMail() {
		firstName = true;
		lastName = true;
		phoneNo = true;
		eMail = true;

		return this;
	}

	public ValidateGuestProfile activateFirstname() {
		firstName = true;
		return this;
	}

	public ValidateGuestProfile activateLastName() {
		lastName = true;
		return this;
	}

	public ValidateGuestProfile activatePhoneNo() {
		phoneNo = true;
		return this;
	}

	public ValidateGuestProfile activateEmail() {
		eMail = true;
		return this;
	}

	private void SearchOpenGuestProfile(String lastName, String firstName) {

		clickTabProfiles();
		new POSearchInMainPage().search(lastName);

		String GuestExpected = lastName + ", " + firstName;

		click($$(ProfileTile.LIST_GUESTS).findBy(Condition.text(GuestExpected)));
	}

	public void Validate(GuestProfileCreateRequest guestProfileCreateRequest) {

		SearchOpenGuestProfile(guestProfileCreateRequest.getPersonalDetails().getLastName(),
				guestProfileCreateRequest.getPersonalDetails().getFirstName());

		if (lastName) {
			Assert.assertEquals(getLastnameinput(), guestProfileCreateRequest.getPersonalDetails().getLastName());
		}

		if (firstName) {
			Assert.assertEquals(getFirstnameinput(), guestProfileCreateRequest.getPersonalDetails().getFirstName());
		}

		if (phoneNo) {
			Assert.assertEquals(getPhonenumberinput(),
					guestProfileCreateRequest.getPhoneDetails().getPhones().get(0).getNumber());
		}

		if (eMail) {

		}

	}

}
