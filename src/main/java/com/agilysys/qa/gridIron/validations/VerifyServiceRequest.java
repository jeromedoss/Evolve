package com.agilysys.qa.gridIron.validations;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.joda.time.LocalDate;

import com.agilysys.common.model.statuses.RoomInventoryStatus;
import com.agilysys.common.model.statuses.RoomInventoryStatus.CanonicalId;
import com.agilysys.pms.servicerequest.model.Serviceable.AreaType;
import com.agilysys.pms.servicerequest.model.Serviceable.Severity;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsRequestServiceDialog;
import com.agilysys.qa.gridIron.pageobject.housekeeping.PORequestServiceModal;
import com.agilysys.qa.gridIron.pageobject.housekeeping.PORequestServiceModal.ServiceRequestCategory;
import com.agilysys.qa.gridIron.utils.HelperMethods;
import org.openqa.selenium.By;

public class VerifyServiceRequest extends ValidationBase{
	
	public static void verifyAreaType(AreaType areaType){
		String expectedValue = "";
		if(areaType == AreaType.ROOM){
			expectedValue = "Room";
		}else if (areaType == AreaType.GENERAL_AREA){
			expectedValue = "General Area";
		}else{
			return;
		}
		
		if($(LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_SELECT).getValue().toLowerCase().contains("maintenance")){
			verifyElementValue(expectedValue, LocatorsRequestServiceDialog.DROPDOWN_AREA_SELECT);
		}else{
			verifyElementText(expectedValue, LocatorsRequestServiceDialog.SPAN_AREA);
		}
		
						
	}
	
	public static void verifyBuildingName(String expectedValue){
		verifyElementValue(expectedValue, LocatorsRequestServiceDialog.DROPDOWN_BUILDING);
	}

	public static void verifyAreaName(String expectedValue){
		verifyElementValue(expectedValue, LocatorsRequestServiceDialog.INPUT_GENERALAREA_NAME);
	}
	
	public static void verifyRoomName(String expectedValue){
		verifyElementValue(expectedValue, LocatorsRequestServiceDialog.INPUT_ROOM_NO);
	}
	
	public static void verifyServiceRequestCategory(ServiceRequestCategory serviceRequestCategory){
		String expectedValue = null;
		if(serviceRequestCategory == ServiceRequestCategory.HK){
			expectedValue = "HK (Housekeeping)";
		}else if(serviceRequestCategory == ServiceRequestCategory.HKG){
			expectedValue = "HKG (Guest Service Request)";
		}else if(serviceRequestCategory == ServiceRequestCategory.MT){
			expectedValue = "MT (Maintenance)";
		}else{
			return;
		}
		
		verifyElementValue(expectedValue, LocatorsRequestServiceDialog.DROPDOWN_CATEGORY_SELECT);
	}
	
	public static void verifyServiceRequestTypeName(String expectedValue){
		verifyElementValue(expectedValue, LocatorsRequestServiceDialog.DROPDOWN_SERVICE_SELECT);
	}
	
	public static void verifyServiceRequestStatus(String expectedValue){
		verifyElementValue(expectedValue, LocatorsRequestServiceDialog.DROPDOWN_STATUS_SELECT);
	}
	
	public static void verifySerivceRequestNotes(String expectedValue){
		verifyElementValue(expectedValue, LocatorsRequestServiceDialog.TEXTAREA_NOTES);
	}
	
	public static void verifySeverity(Severity severity){
		String expectedValue = null;
		if(severity == Severity.NORMAL){
			expectedValue = "NORMAL";			
		}else if(severity == Severity.MINOR){
			expectedValue = "MINOR";
		}else if(severity == Severity.URGENT){
			expectedValue = "URGENT";
		}else{
			return;
		}		
		verifyElementValue(expectedValue, LocatorsRequestServiceDialog.DRODDOWN_SEVERITY_SELECT);
	}
		
	public static void verifyResourceName(String expectedValue){
		verifyElementValue(expectedValue, LocatorsRequestServiceDialog.STAFF_NAME_SELECT);
	}

	public static void verifyScheduleDate(LocalDate scheduleDate){
		String expectedValue = HelperMethods.convertDateToString(scheduleDate);
		verifyElementValue(expectedValue , LocatorsRequestServiceDialog.INPUT_SCHEDULE_DATE);
	}

	
	public static void verify(PORequestServiceModal serviceRequest){
		VerifyServiceRequest.verifyAreaType(serviceRequest.areaType);
		VerifyServiceRequest.verifyBuildingName(serviceRequest.buildingName);
		VerifyServiceRequest.verifyRoomName(serviceRequest.roomNumber);
		VerifyServiceRequest.verifyAreaName(serviceRequest.areaName);
		VerifyServiceRequest.verifyServiceRequestCategory(serviceRequest.serviceRequestCategory);
		VerifyServiceRequest.verifyServiceRequestTypeName(serviceRequest.serviceRequestName);
		VerifyServiceRequest.verifySerivceRequestNotes(serviceRequest.notes);
		VerifyServiceRequest.verifyResourceName(serviceRequest.resourceName);
		VerifyServiceRequest.verifySeverity(serviceRequest.severity);
		VerifyServiceRequest.verifyScheduleDate(serviceRequest.scheduleDate);
		
	}

}
