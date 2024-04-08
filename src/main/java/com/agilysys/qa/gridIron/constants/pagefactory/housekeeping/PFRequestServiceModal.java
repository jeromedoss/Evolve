package com.agilysys.qa.gridIron.constants.pagefactory.housekeeping;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import java.time.Duration;
import com.codeborne.selenide.*;
import org.joda.time.LocalDate;
import com.agilysys.common.model.statuses.RoomInventoryStatus.CanonicalId;
import com.agilysys.pms.servicerequest.model.Serviceable.AreaType;
import com.agilysys.pms.servicerequest.model.Serviceable.Severity;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsRequestServiceDialog;
import com.agilysys.qa.gridIron.pageobject.housekeeping.PORequestServiceModal;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
public class PFRequestServiceModal extends PageFactoryBase {
    public static void selectAreaType(AreaType areaType) {
        if (areaType == null) {
            return;
        }
        click(LocatorsRequestServiceDialog.DROPDOWN_AREA_SELECT);
        if (areaType == AreaType.GENERAL_AREA) {
            click(LocatorsRequestServiceDialog.DROPDOWN_AREA_OPTION_GENERAL_AREA);
        } else if (areaType == AreaType.ROOM) {
            click(LocatorsRequestServiceDialog.DROPDOWN_AREA_OPTION_ROOM);
        }
    }
    public static void selectBlock() {
        click(LocatorsRequestServiceDialog.DROPDOWN_BLOCKSTATUS);
        click(LocatorsRequestServiceDialog.DROPDOWN_OPTION_BLOCKSTATUS_OTM);
    }
    public static void selectBuilding(String buildingName) {
        selectByText(LocatorsRequestServiceDialog.DROPDOWN_BUILDING, LocatorsRequestServiceDialog.DROPDOWN_LIST_BUILDING, buildingName);
    }
    public static void typeRoomNumber(String roomNumber) {
        clearAndType(LocatorsRequestServiceDialog.INPUT_ROOM_NO, roomNumber);
        click(LocatorsRequestServiceDialog.HEADER_MODAL);
    }
    public static void typeAreaName(String areaName) {
        type(LocatorsRequestServiceDialog.INPUT_GENERALAREA_NAME, areaName);
        click(LocatorsRequestServiceDialog.HEADER_MODAL);
    }
    public static void selectServiceRequestCategory(PORequestServiceModal.ServiceRequestCategory serviceRequestCategory) {
        if (serviceRequestCategory == null) {
            return;
        }
        click(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_SELECT);
        if (serviceRequestCategory == PORequestServiceModal.ServiceRequestCategory.HK) {
            click(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_OPTION_HK_SELECT);
        } else if (serviceRequestCategory == PORequestServiceModal.ServiceRequestCategory.HKG) {
            click(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_OPTION_HKG_SELECT);
        } else if (serviceRequestCategory == PORequestServiceModal.ServiceRequestCategory.MT) {
            click(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_OPTION_MT_SELECT);
        }
    }
    public static void selectStatus(String serviceRequestStatus) {
        selectByText(LocatorsRequestServiceDialog.DROPDOWN_STATUS_SELECT, LocatorsRequestServiceDialog.DROPDOWN_LIST_STATUS, serviceRequestStatus);
    }
    public static void selectServiceRequest(String serviceRequestName) {
        selectByText(LocatorsRequestServiceDialog.DROPDOWN_SERVICE_SELECT, LocatorsRequestServiceDialog.DROPDOWN_LIST_SERVICE, serviceRequestName);
    }
    public static void selectSeverity(Severity severity) {
        if (severity == null) {
            return;
        }
        click(LocatorsRequestServiceDialog.DRODDOWN_SEVERITY_SELECT);
        if (severity == Severity.NORMAL) {
            click(LocatorsRequestServiceDialog.DROPDOWN_OPTION_SEVERITY_NORMAL);
        } else if (severity == Severity.MINOR) {
            click(LocatorsRequestServiceDialog.DROPDOWN_OPTION_SEVERITY_MINOR);
        } else if (severity == Severity.URGENT) {
            click(LocatorsRequestServiceDialog.DROPDOWN_OPTION_SEVERITY_URGENT);
        }
    }
    public static void typeNotes(String notes) {
        type(LocatorsRequestServiceDialog.TEXTAREA_NOTES, notes);
    }
    public static void clickAssign() {
        click(LocatorsRequestServiceDialog.ASSIGN_RADIO);
    }
    public static void assignRatioButton() {
        click(LocatorsRequestServiceDialog.ASSIGN_RADIO_BUTTON);
    }
    public static void typeAssignedResourceName(String resourceName) {
        click(LocatorsRequestServiceDialog.STAFF_NAME_SELECT);
        type(LocatorsRequestServiceDialog.STAFF_NAME_SELECT, resourceName);
        click(By.xpath("//p[contains(@title, '" + resourceName + "')]"));
        click(LocatorsRequestServiceDialog.HEADER_MODAL);
    }
    public static void clickUnAssign() {
        click(LocatorsRequestServiceDialog.UNASSIGNED_RADIO);
    }
    public static void clickSetSchedule() {
        click(LocatorsRequestServiceDialog.SET_SCHEDULE_RADIO);
    }
    public static void typeScheduleDate(LocalDate scheduleDate) {
        $(LocatorsRequestServiceDialog.INPUT_SCHEDULE_DATE).clear();
        typeDate(LocatorsRequestServiceDialog.INPUT_SCHEDULE_DATE, scheduleDate);
    }
    public static void clickBlockRoom() {
        click(LocatorsRequestServiceDialog.SET_BLOCK_RADIO);
    }
    public static void selectBlockStatus(CanonicalId inventoryBlockType) {
        if (inventoryBlockType == null) {
            return;
        }
        click(LocatorsRequestServiceDialog.DROPDOWN_BLOCKSTATUS);
        if (inventoryBlockType == CanonicalId.OUT_OF_ORDER) {
            click(LocatorsRequestServiceDialog.DROPDOWN_OPTION_BLOCKSTATUS_OOO);
        } else if (inventoryBlockType == CanonicalId.OFF_THE_MARKET) {
            click(LocatorsRequestServiceDialog.DROPDOWN_OPTION_BLOCKSTATUS_OTM);
        } else if (inventoryBlockType == CanonicalId.HOLD) {
            click(LocatorsRequestServiceDialog.DROPDOWN_OPTION_BLOCKSTATUS_HOLD);
        }
    }

    public static void typeBlockStartDate(LocalDate startDate) {
        $(LocatorsRequestServiceDialog.INPUT_BLOCK_START_DATE).clear();
        type(LocatorsRequestServiceDialog.INPUT_BLOCK_START_DATE, startDate.toString("MM/dd/yyyy"));
    }

    public static void typeBlockEndDate(LocalDate endDate) {
        click(By.xpath("//input[@name='blocRoomEndDate']/ancestor::date-picker/descendant::*[@data-qa-id='g6nJuXz-click']"));
        click(By.xpath("//*[@data-qa-id='kFtLapu']/descendant::*[contains(@class,'uib-day text-center ng-scope')]/button/span[text()='" + endDate.toString("MM/dd/yyyy").split("/")[1] + "']"));
        //    Selenide.sleep(500);
//        $(LocatorsRequestServiceDialog.INPUT_BLOCK_END_DATE).clear();
//        typeDate(LocatorsRequestServiceDialog.INPUT_BLOCK_END_DATE, endDate);
    }

    public static void clickDone(){
        click(LocatorsRequestServiceDialog.BUTTON_DONE);
        $(LocatorsRequestServiceDialog.BUTTON_DONE).should(Condition.hidden, Duration.ofMillis(Configuration.timeout));
    }
    public static void clickCancel(){
        click(LocatorsRequestServiceDialog.BUTTON_CANCEL);
    }
}