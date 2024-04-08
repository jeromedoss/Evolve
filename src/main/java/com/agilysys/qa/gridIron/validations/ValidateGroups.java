package com.agilysys.qa.gridIron.validations;

import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupBookingSection.GROUP_CODE_INPUT;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupBookingSection.GROUP_NAME_INPUT;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsReservations.DATE_RESERVATION_ARRIVE;
import static com.agilysys.qa.gridIron.constants.locators.groups.LocatorsReservations.DATE_RESERVATION_DEPART;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns.navigateToSearch;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.grouptile.PFGroupTile.checkVisibilityTabGroup;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.grouptile.PFGroupTile.clickTabGroup;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.testng.Assert;

import com.agilysys.pms.profile.model.Group;
import com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupBookingSection;
import com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupsCommon;
import com.agilysys.qa.gridIron.constants.locators.groups.LocatorsReservations;
import com.agilysys.qa.gridIron.constants.locators.groups.LocatorsRoomBlocksSection;
import com.agilysys.qa.gridIron.constants.locators.home.GroupTile;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import com.codeborne.selenide.SelenideElement;

public class ValidateGroups {

	private Map<Boolean, String> groupName = new HashMap<Boolean, String>();;
	private Map<Boolean, String> groupCode = new HashMap<Boolean, String>();;
	private Map<Boolean, String> groupReservationStartDate = new HashMap<Boolean, String>();;
	private Map<Boolean, String> groupReservationEndDate = new HashMap<Boolean, String>();;
	private Map<Boolean, String> groupRoomsRemaining = new HashMap<Boolean, String>();
	private Map<Boolean, String> groupRoomsPickedUp = new HashMap<Boolean, String>();
	private Map<Boolean, String> groupReservationRate = new HashMap<Boolean, String>();
	private Map<Boolean, String> groupReservationStatus = new HashMap<Boolean, String>();;

	public ValidateGroups() {
		Selenide.refresh();
	}

	public ValidateGroups activateGroupName(String Name) {
		groupName.put(true, Name);
		return this;

	}

	public ValidateGroups activateGroupCode(String Code) {
		groupCode.put(true, Code);
		return this;

	}

	public ValidateGroups activateGroupReservationStartDate(String date) {
		groupReservationStartDate.put(true, date);
		return this;

	}

	public ValidateGroups activateGroupReservationEndDate(String date) {
		groupReservationEndDate.put(true, date);
		return this;

	}

	public ValidateGroups activateGroupRoomsRemaining(String remainingRooms) {
		groupRoomsRemaining.put(true, remainingRooms);
		return this;

	}

	public ValidateGroups activateGroupRoomsPickedUp(String pickedRooms) {
		groupRoomsPickedUp.put(true, pickedRooms);
		return this;

	}

	public ValidateGroups activateGroupReservationRate(String rate) {
		groupReservationRate.put(true, rate);
		return this;

	}

	public ValidateGroups activateGroupReservationStatus(String status) {
		groupReservationStatus.put(true, status);
		return this;

	}

	public ValidateGroups verify(Group group) {

		navigateToSearch();

		checkVisibilityTabGroup();
		clickTabGroup();

		new POSearchInMainPage().search(group.getGroupName());

		if (groupName.containsValue(true)) {
			$$(GroupTile.LIST_GROUPS).findBy(Condition.text(group.getGroupName())).shouldBe(Condition.exist);
		}

		return this;
	}

	public ValidateGroups verifyBookingTile(Group group) {
		click($$(GroupTile.LIST_GROUPS).findBy(Condition.text(group.getGroupName())));

		if (groupName.containsValue(true)) {
			Assert.assertEquals($(GROUP_NAME_INPUT).shouldBe(Condition.exist).getValue(), group.getGroupName());
		}

		if (groupCode.containsValue(true)) {
			Assert.assertEquals($(GROUP_CODE_INPUT).shouldBe(Condition.exist).getValue(), group.getGroupCode());
		}

		return this;
	}

	public ValidateGroups verifyReservation() {

		click(LocatorsGroupsCommon.TAB_RESERVATIONS);

		if (groupReservationStartDate.containsValue(true)) {

			String actualDate = $(DATE_RESERVATION_ARRIVE).shouldBe(Condition.visible).getAttribute("innerHTML");
			String[] dates = actualDate.split(", ");
			String[] month = dates[0].split(" ");
			String date1 = dates[1] + "-" + month[0] + "-" + month[1];
			String actualDateInFormat = null;

			try {
				Date date = new SimpleDateFormat("yyyy-MMM-dd").parse(date1);

				actualDateInFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);

			} catch (Exception e) {
			}

			Assert.assertEquals(groupReservationStartDate.get(true), actualDateInFormat);

		}

		if (groupReservationEndDate.containsValue(true)) {

			String actualDate = $(DATE_RESERVATION_DEPART).shouldBe(Condition.visible).getAttribute("innerHTML");
			String[] dates = actualDate.split(", ");
			String[] month = dates[0].split(" ");
			String date1 = dates[1] + "-" + month[0] + "-" + month[1];
			String actualDateInFormat = null;

			try {
				Date date = new SimpleDateFormat("yyyy-MMM-dd").parse(date1);

				actualDateInFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);

			} catch (Exception e) {
			}

			Assert.assertEquals(groupReservationEndDate.get(true), actualDateInFormat);
		}

		if (groupReservationStatus.containsValue(true)) {
			Assert.assertEquals(
					$(LocatorsReservations.RESERVATION_STATUS).shouldBe(Condition.visible).getAttribute("innerHTML"),
					groupReservationStatus.get(true));
		}

		if (groupReservationRate.containsKey(true)) {
			Assert.assertTrue($(LocatorsReservations.RESERVATION_ROOM_TYPE).shouldBe(Condition.visible)
					.getAttribute("innerHTML").contains(groupReservationRate.get(true)));
		}

		return this;
	}

	public ValidateGroups verifyRoomBlocks(Group group, String roomCode) {

		click(LocatorsGroupsCommon.TAB_ROOM_BLOCKS);

	//	$(LocatorsRoomBlocksSection.STATUS_TAB).shouldBe(Condition.appear, Condition.visible).scrollTo();

		if (groupRoomsRemaining.containsKey(true)) {
			Assert.assertEquals(
					$$(LocatorsRoomBlocksSection.getStatusRoomRemain(roomCode))
							.filterBy(Condition.attribute("innerHTML", groupRoomsRemaining.get(true))).size(),
					Integer.parseInt(groupRoomsRemaining.get(true)));
		}

		if (groupRoomsPickedUp.containsValue(true)) {
			Assert.assertEquals(
					$$(LocatorsRoomBlocksSection.getStatusRoomPickup(roomCode))
							.filterBy(Condition.attribute("innerHTML", groupRoomsRemaining.get(true))).size(),
				Integer.parseInt(groupRoomsRemaining.get(true)));
		}
		return this;
	}
}
