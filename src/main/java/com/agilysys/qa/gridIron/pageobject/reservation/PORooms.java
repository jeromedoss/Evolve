package com.agilysys.qa.gridIron.pageobject.reservation;

import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;

import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsCurrentlySelectedRoom;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFCurrentlySelectedRoom;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import java.time.Duration;

public class PORooms {
	
	public static void assignRoom(String roomNumber){
		PFCurrentlySelectedRoom.clickSelectRoom();
		PFCurrentlySelectedRoom.typeRoomNumber(roomNumber);
		PFCurrentlySelectedRoom.saveRoomNumber();
	}
	
	public static void addDoNotMoveGuest(String reason){
		PFCurrentlySelectedRoom.clickDoNotMoveGuestCheckBox();
		PFCurrentlySelectedRoom.typeDoNotMoveGuestReason(reason);
		PFCurrentlySelectedRoom.clickSaveDoNotMoveGuest();
	}
	
	public static void removeDoNotMoveGuest(){
		PFCurrentlySelectedRoom.clickDoNotMoveGuestCheckBox();
		Selenide.sleep(5000);
	}
	
	public static void confirmRoom(){
		PFCurrentlySelectedRoom.clickConfirmRoom();		
	}
	
	public static void releaseUnconfirmedRoom(){
		click(By.xpath("//button[text()= 'Yes']"));
		PFCurrentlySelectedRoom.clickReleaseUnconfirmedRoom();		
	}
	
	public static void changeRoomType(String roomTypeCode, String roomTypeName){
		PFCurrentlySelectedRoom.clickChangeRoomType();
		PFCurrentlySelectedRoom.selectRoomType(roomTypeCode, roomTypeName);
		PFCurrentlySelectedRoom.clickSeeRates();
	}

}
