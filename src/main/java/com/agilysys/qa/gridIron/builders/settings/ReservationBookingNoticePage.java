package com.agilysys.qa.gridIron.builders.settings;

//import com.agilysys.pms.property.model.ReservationBookingNotice;
import com.agilysys.qa.gridIron.pageobject.settings.POReservationBookingNoticePage;

public class ReservationBookingNoticePage {

	String noticeMessage = null;

//	public ReservationBookingNoticePage(ReservationBookingNotice reservationBookingNotice) {
//		this.noticeMessage = reservationBookingNotice.getMessage();
//	}

	public void stepCreateActiveBookingNotice() {
		new POReservationBookingNoticePage(noticeMessage).stepCreateActiveNotice();
	}

	public void stepUpdateActiveBookingNotice(String oldNoticeMessage) {
		new POReservationBookingNoticePage(noticeMessage).stepUpdateActiveNotice(oldNoticeMessage);
	}
}
