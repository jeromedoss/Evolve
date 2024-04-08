package com.agilysys.qa.gridIron.pageobject.groups;

import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickCancellationPolicy;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickDefiniteStatus;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickInquiryStatus;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickNightlyRoomCharge;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickProspectStatus;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickRadioGroupBook;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickRadioGroupPayment;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickSelectCancellationPolicy;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickSelectPostRoomRevenueAs;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickSelectStatus;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.clickTentativeStatus;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.setInputGroupCode;
import static com.agilysys.qa.gridIron.constants.pagefactory.groups.PFGroupBooking.setInputGroupName;

/*
 * *Author - Harish Baskaran - 2018
 */
public class POGroupBooking {

	private static void stepCreate(String groupName, String groupCode, String status) {

		setInputGroupName(groupName);
		setInputGroupCode(groupCode);
		clickSelectPostRoomRevenueAs();
		clickNightlyRoomCharge();
		clickSelectStatus();

		switch (status) {

		case "DEFINITE":
			clickDefiniteStatus();
			break;

		case "TENTATIVE":
			clickTentativeStatus();
			break;

		case "PROSPECT":
			clickProspectStatus();
			break;

		case "INQUIRY":
			clickInquiryStatus();
			break;

		default:
			break;
		}

		clickSelectCancellationPolicy();
		clickCancellationPolicy();
		clickRadioGroupPayment();
		clickRadioGroupBook();
	}

	public static void stepCreateDefiniteGroup(String GroupName, String GroupCode) {

		stepCreate(GroupName, GroupCode, "DEFINITE");
	}

	public static void stepCreateTentativeGroup(String GroupName, String GroupCode) {

		stepCreate(GroupName, GroupCode, "TENTATIVE");
	}

	public static void stepCreateInquiryGroup(String GroupName, String GroupCode) {

		stepCreate(GroupName, GroupCode, "INQUIRY");
	}

	public static void stepCreateProspectGroup(String GroupName, String GroupCode) {

		stepCreate(GroupName, GroupCode, "PROSPECT");
	}
}
