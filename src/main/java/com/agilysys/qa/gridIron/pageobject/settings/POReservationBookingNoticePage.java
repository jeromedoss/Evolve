package com.agilysys.qa.gridIron.pageobject.settings;

import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFReservationBookingNoticePage.clickButtonAdd;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFReservationBookingNoticePage.clickButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFReservationBookingNoticePage.clickButtonToday;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFReservationBookingNoticePage.clickButtonUpdate;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFReservationBookingNoticePage.clickExistingNoticeMessage;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFReservationBookingNoticePage.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFReservationBookingNoticePage.clickStartDate;
import static com.agilysys.qa.gridIron.constants.pagefactory.settings.PFReservationBookingNoticePage.setNoticeMessage;

//import com.agilysys.pms.property.model.ReservationBookingNotice;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POReservationBookingNoticePage {

	private String NoticeMessage = null;

	public POReservationBookingNoticePage(String NoticeMessage) {
		this.NoticeMessage = NoticeMessage;
	}

//	public POReservationBookingNoticePage(ReservationBookingNotice reservationBookingNotice) {
//		this.NoticeMessage = reservationBookingNotice.getMessage();
//	}

	public void stepCreateActiveNotice() {

		clickButtonAdd();
		clickHeaderModal();
		setNoticeMessage(NoticeMessage);
		clickStartDate();
		clickButtonToday();
		clickButtonSave();
	}

	public void stepUpdateActiveNotice(String OldMessage) {

		clickExistingNoticeMessage(OldMessage);
		clickHeaderModal();
		setNoticeMessage(NoticeMessage);
		clickButtonUpdate();
	}
}
