package com.agilysys.qa.gridIron.features;

import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.platform.user.model.Property;
//import com.agilysys.pms.property.model.ReservationBookingNotice;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
//import com.agilysys.qa.data.builder.property.BuilderReservationBookingNotice;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.settings.ReservationBookingNoticePage;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.ValidateReservationBookingNotice;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns.navigateToAllSettings;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFSettingsPage.clickLinkReservationBookingNotice;
import static com.agilysys.qa.gridIron.utils.RandomHelper.getRandomAlphaString;

/*
 * *Author - Harish Baskaran - Oct/2018
 */

public class TestReservationBookingNotice extends StayUIWrappers {

	static String NoticeMessage = getRandomAlphaString(10);

	String tenantId = null;
	String propertyId = null;

	MasterObject masterObject;
	Property property;

	@BeforeClass
	public void beforeClass() {

		masterObject = new MainDriver().getMasterObject();
		new LoginApplication().Login(masterObject);
		property = masterObject.getProperty();
	}

	@AfterClass
	public void afterclass() {
		//Selenide.closeWebDriver();

	}

	@BeforeMethod
	public void beforeMethod() {

		navigateToAllSettings();
		clickLinkReservationBookingNotice();

	}

//	@Test
//	@TestCase(ids = { "RST-10692", "RST-10695" })
//	public void testCreateNoticeAndUpdateNotice() {
//
//		ReservationBookingNotice reservationBookingNotice = new BuilderReservationBookingNotice()
//				.setStartDate(new ClientPropertyDateGet().getPropertyDate(property)).setMessage(NoticeMessage).build();
//
//		new ReservationBookingNoticePage(reservationBookingNotice).stepCreateActiveBookingNotice();
//
//		new ValidateReservationBookingNotice().VerifyRequiredFields().Validate(reservationBookingNotice);
//
//		ReservationBookingNotice updateReservationBookingNotice = new BuilderReservationBookingNotice()
//				.setStartDate(new ClientPropertyDateGet().getPropertyDate(property)).setMessage(NoticeMessage + "new")
//				.build();
//
//		new ReservationBookingNoticePage(updateReservationBookingNotice)
//				.stepUpdateActiveBookingNotice(reservationBookingNotice.getMessage());
//
//		new ValidateReservationBookingNotice().VerifyRequiredFields().Validate(updateReservationBookingNotice);
//
//	}

}
