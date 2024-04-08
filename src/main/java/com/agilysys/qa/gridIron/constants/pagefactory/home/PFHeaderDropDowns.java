package com.agilysys.qa.gridIron.constants.pagefactory.home;

import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_ALLSETTINGS;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_ALL_REPORTS;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_BATCH_OPERATIONS;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_BOOK_RESERVATION;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_DASHBOARD;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_FRONTDESK;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_GENERAL_AVAILABILITY;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_GROUP_ROOMS_CONTROL;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_GUEST_ACCOUNTING;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_HOME;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_HOUSE_KEEPING;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_INTERFACES;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_INTERFACE_LOGS;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_INVENTORY_BLOCKS;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_LOST_FOUND;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_MAINTENANCE;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_NIGHT_AUDIT;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_PROFILES;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_RATES;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_REPORTS;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_REQUEST_SERVICE;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_RESERVATION;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_ROOM_CONDITIONS;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_ROOM_MGMT;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_SEARCH;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_SETTINGS;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_TAPECHART;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_TA_COMMISSION_REPORT;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_USER_ACCOUNTS;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

import java.time.Duration;

import com.agilysys.qa.gridIron.constants.locators.home.ProfileTile;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsAssignedServicesSection;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsHkMain;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.openqa.selenium.By;

import com.agilysys.qa.helpers.ThreadHelper;

public class PFHeaderDropDowns extends PageFactoryBase {

	public static void refresh() {
		ThreadHelper.sleep(1000);
		Selenide.refresh();
		waitForPageToLoad();
	}

	public static void clickButtonLogo() {
		// $(BUTTON_LOGO);
		navigateToSearch();
	}

	public static void menuSearch(By mainHeader, By subMenu ){
		refresh();
		waitForPageToLoad();
		Selenide.sleep(3000);
		$(mainHeader).shouldBe(Condition.visible,Duration.ofMillis(Configuration.timeout));
		clickAndWaitForNextElement(mainHeader, subMenu);
		jsClick(subMenu);
		PageFactoryBase.waitForPageToLoad();
	}

	public static void navigateToSearch() {
//		refresh();
//		Selenide.sleep(2000);
//		clickAndWaitForNextElement(NAV_RESERVATION, NAV_SEARCH);
//		PFSearchTile.waitForSearchPageToLoad();
		menuSearch(NAV_RESERVATION,NAV_SEARCH);
	}

	public static void navigateToBookReservation() {
//		refresh();
//		click(NAV_RESERVATION);
//		click(NAV_BOOK_RESERVATION);

		menuSearch(NAV_RESERVATION,NAV_BOOK_RESERVATION);
	}

	public static void navigateToProfiles() {
//		refresh();
//		click(NAV_RESERVATION);
//		click(NAV_PROFILES);
//		PFSearchTile.waitForSearchPageToLoad();

		menuSearch(NAV_RESERVATION,NAV_PROFILES);
	}

	public static void navigateToGeneralAvailability() {
//		click(NAV_DASHBOARD);
//		click(NAV_GENERAL_AVAILABILITY);

		menuSearch(NAV_DASHBOARD,NAV_GENERAL_AVAILABILITY);
	}

	public static void navigateToGroupRoomControl() {
//		click(NAV_DASHBOARD);
//		click(NAV_GROUP_ROOMS_CONTROL);

		menuSearch(NAV_DASHBOARD,NAV_GROUP_ROOMS_CONTROL);
	}

	public static void navigateToTapeChart() {
//		click(NAV_DASHBOARD);
//		click(NAV_TAPECHART);

		menuSearch(NAV_DASHBOARD,NAV_TAPECHART);
	}

	public static void navigateToHome() {
//		click(NAV_FRONTDESK);
//		click(NAV_HOME);

		menuSearch(NAV_FRONTDESK,NAV_HOME);
	}

	public static void navigateToBatchOperations() {
//		refresh();
//		click(NAV_FRONTDESK);
//		click(NAV_BATCH_OPERATIONS);

		menuSearch(NAV_FRONTDESK,NAV_BATCH_OPERATIONS);
	}

	public static void navigateToHouseKeeping() {
		refresh();
		click(NAV_ROOM_MGMT);
		click(NAV_HOUSE_KEEPING);
		$(LocatorsHkMain.BUTTON_REQUEST_SERVICE).shouldBe(Condition.enabled);

	/*	menuSearch(NAV_ROOM_MGMT,NAV_HOUSE_KEEPING);
		$(LocatorsHkMain.BUTTON_REQUEST_SERVICE).shouldBe(Condition.enabled);*/
	}

	public static void navigateToMaintenace() {
//		refresh();
//		click(NAV_ROOM_MGMT);
//		click(NAV_MAINTENANCE);

		menuSearch(NAV_ROOM_MGMT,NAV_MAINTENANCE);
	}

	public static void navigateToRoomConditions() {
//		click(NAV_ROOM_MGMT);
//		click(NAV_ROOM_CONDITIONS);

		menuSearch(NAV_ROOM_MGMT,NAV_ROOM_CONDITIONS);
	}

	public static void navigateToInventoryBlocks() {
//		refresh();
//		click(NAV_ROOM_MGMT);
//		click(NAV_INVENTORY_BLOCKS);

		menuSearch(NAV_ROOM_MGMT,NAV_INVENTORY_BLOCKS);
	}

	public static void navigateToRequestService() {
//		refresh();
//		click(NAV_ROOM_MGMT);
//		click(NAV_REQUEST_SERVICE);

		menuSearch(NAV_ROOM_MGMT,NAV_REQUEST_SERVICE);
	}

	public static void navigateToLostFound() {
//		click(NAV_ROOM_MGMT);
//		click(NAV_LOST_FOUND);

		menuSearch(NAV_ROOM_MGMT,NAV_LOST_FOUND);
	}

	public static void navigateToNightAudit() {
		click(NAV_NIGHT_AUDIT);
	}

	public static void navigateToAllReports() {
//		click(NAV_REPORTS);
//		click(NAV_ALL_REPORTS);

		menuSearch(NAV_REPORTS,NAV_ALL_REPORTS);
	}

	public static void navigateToTACommissionReports() {
//		click(NAV_REPORTS);
//		click(NAV_TA_COMMISSION_REPORT);

		menuSearch(NAV_REPORTS,NAV_TA_COMMISSION_REPORT);
	}

	public static void navigateToInterfaceLogs() {
//		click(NAV_REPORTS);
//		click(NAV_INTERFACE_LOGS);

		menuSearch(NAV_REPORTS,NAV_INTERFACE_LOGS);
	}

	public static void navigateToAllSettings() {
//		refresh();
//		click(NAV_SETTINGS);
//		click(NAV_ALLSETTINGS);

		menuSearch(NAV_SETTINGS,NAV_ALLSETTINGS);
	}

	public static void navigateToRates() {
//		refresh();
//		click(NAV_SETTINGS);
//		click(NAV_RATES);

		menuSearch(NAV_SETTINGS,NAV_RATES);
	}

	public static void navigateToUserAccounts() {
//		click(NAV_SETTINGS);
//		click(NAV_USER_ACCOUNTS);

		menuSearch(NAV_SETTINGS,NAV_USER_ACCOUNTS);
	}

	public static void navigateToGuestAccounting() {
//		click(NAV_SETTINGS);
//		click(NAV_GUEST_ACCOUNTING);

		menuSearch(NAV_SETTINGS,NAV_GUEST_ACCOUNTING);
	}

	public static void navigateToInterfaces() {
//		click(NAV_SETTINGS);
//		click(NAV_INTERFACES);

		menuSearch(NAV_SETTINGS,NAV_INTERFACES);
	}

}