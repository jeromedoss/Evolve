package com.agilysys.qa.gridIron.constants.pagefactory.reservation;

import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsChangeRoomModal;
import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsCurrentlySelectedRoom;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class PFCurrentlySelectedRoom extends PageFactoryBase {

    public static void clickSelectRoom() {
        click(LocatorsCurrentlySelectedRoom.BUTTON_SELECT);
    }

    public static void clickChangeRoomType() {
        click(LocatorsCurrentlySelectedRoom.LINK_CHANGE_ROOM_TYPE);
    }

    public static void selectRoomType(String roomTypeCode, String roomTypeName) {
        click(LocatorsCurrentlySelectedRoom.DRODPOWN_ROOM_TYPE);
        click(LocatorsCurrentlySelectedRoom.getRoomTypeCheckBoxByRoomTypeName(roomTypeName));
    }

    public static void selectRoomTypes(List<String> roomTypes) {
        for (String roomType : roomTypes) {
            selectByText(LocatorsCurrentlySelectedRoom.DRODPOWN_ROOM_TYPE, LocatorsCurrentlySelectedRoom.DRODPOWN_OPTIONS_ROOM_TYPE, roomType);
        }

    }

    public static void clickSeeRates() {
        click(LocatorsCurrentlySelectedRoom.BUTTON_SEE_RATES);
    }

    public static void typeRoomNumber(String roomNumber) {
        type(LocatorsChangeRoomModal.INPUT_ROOM_NUMBER, roomNumber);
    }

    public static void saveRoomNumber() {
        click(LocatorsChangeRoomModal.BUTTON_SAVE);
        //click(LocatorsModifyStayPage.BUTTON_BOOK);
        Selenide.sleep(5000);
        waitForElementToLoad(LocatorsCurrentlySelectedRoom.LINK_RELEASE_ROOM, 20000);
        scrollElementToVisibleArea($(LocatorsCurrentlySelectedRoom.LINK_RELEASE_ROOM));
    }

    public static void clickChangeRoom() {
        click(LocatorsCurrentlySelectedRoom.BUTTON_CHANGE);
    }

    public static void clickReleaseRoom() {
        click(LocatorsCurrentlySelectedRoom.LINK_RELEASE_ROOM);
        $(LocatorsCurrentlySelectedRoom.LINK_RELEASE_ROOM).should(Condition.hidden, Duration.ofMillis(Configuration.timeout));
    }

    public static void clickDoNotMoveGuestCheckBox() {
        click(LocatorsCurrentlySelectedRoom.CHECKBOX_DO_NOT_MOVE);
    }

    public static void typeDoNotMoveGuestReason(String reason) {
        type(LocatorsCurrentlySelectedRoom.TEXTAREA_DO_NOT_MOVE_REASON, reason);
    }

    public static void clickSaveDoNotMoveGuest() {
        click(LocatorsCurrentlySelectedRoom.BUTTON_DO_NOT_MOVE_SAVE);
        waitForElementToDisappear(LocatorsCurrentlySelectedRoom.BUTTON_DO_NOT_MOVE_SAVE, Configuration.timeout);
    }

    public static void clickExclamationDoNotMoveGuest() {
        click(LocatorsCurrentlySelectedRoom.EXCLAMATION_DO_NOT_MOVE_GUEST);
    }

    public static void clickConfirmRoom() {
        click(LocatorsCurrentlySelectedRoom.BUTTON_CONFIRM);
        Selenide.sleep(4000);
        $(LocatorsCurrentlySelectedRoom.LINK_RELEASE_ROOM).scrollIntoView(false);
        $(LocatorsCurrentlySelectedRoom.LINK_RELEASE_ROOM).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
    }

    public static void clickReleaseUnconfirmedRoom() {
        Selenide.sleep(1000);
        click(LocatorsCurrentlySelectedRoom.BUTTON_RELEASE);
        $(LocatorsCurrentlySelectedRoom.BUTTON_SELECT).should(Condition.enabled, Duration.ofMillis(Configuration.timeout));
    }
}