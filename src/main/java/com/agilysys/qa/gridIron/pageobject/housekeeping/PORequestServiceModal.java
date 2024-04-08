package com.agilysys.qa.gridIron.pageobject.housekeeping;

import org.joda.time.LocalDate;

import com.agilysys.common.model.statuses.RoomInventoryStatus;
import com.agilysys.pms.servicerequest.model.Serviceable.AreaType;
import com.agilysys.pms.servicerequest.model.Serviceable.Severity;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFHousekeepingHome;
import com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFRequestServiceModal;
import com.agilysys.qa.gridIron.constants.pagefactory.maintenance.PFMaintenanceHome;

public class PORequestServiceModal {
	
	public AreaType areaType = null;
	public String buildingName = null;
	public String roomNumber = null;	
	public String areaName = null;	
	public ServiceRequestCategory serviceRequestCategory = null;
	public String serviceRequestName = null;
	public String notes = null;
	public Severity severity = null;	
	public String resourceName = null;	
	public LocalDate scheduleDate = null;
	public LocalDate blockStartDate = null;
	public LocalDate blockEndDate = null;		
	public RoomInventoryStatus.CanonicalId inventoryBlockType = null;
	public enum ServiceRequestCategory {HK,HKG,MT};
	
	
	public PORequestServiceModal(AreaType areaType, String buildingName, ServiceRequestCategory serviceRequestCategory, String serviceRequestName){
		this.areaType = areaType;
		this.buildingName = buildingName;		
		this.serviceRequestCategory = serviceRequestCategory;
		this.serviceRequestName = serviceRequestName;
	}
	
	public PORequestServiceModal setAreaName(String areaName){
		this.areaName = areaName;
		return this;
	}
	
	public PORequestServiceModal setRoomNumber(String roomNumber){
		this.roomNumber =  roomNumber;
		return this;
	}
	
	public PORequestServiceModal setNotes(String notes){
		this.notes = notes;
		return this;
	}
	
	public PORequestServiceModal setSeverity(Severity severity){
		this.severity = severity;
		return this;
	}
	
	public PORequestServiceModal assignResource(String resourceName){
		this.resourceName = resourceName;
		return this;
	}
	
	public PORequestServiceModal setSchedueDate(LocalDate scheduleDate){
		this.scheduleDate = scheduleDate;
		return this;
	} 
	
	public PORequestServiceModal setBlockDetail(LocalDate blockStartDate, LocalDate blockEndDate, RoomInventoryStatus.CanonicalId roomInventoryStatus){
		this.blockStartDate = blockStartDate;
		this.blockEndDate = blockEndDate;
		this.inventoryBlockType = roomInventoryStatus;
		return this;
	}
	
	public PORequestServiceModal createServiceRequest(){
		
		PFRequestServiceModal.selectAreaType(this.areaType);
		PFRequestServiceModal.selectBuilding(this.buildingName);
		
		if(this.areaName != null){
			PFRequestServiceModal.typeAreaName(areaName);
		}else if(this.roomNumber != null){
			PFRequestServiceModal.typeRoomNumber(roomNumber);
			PFRequestServiceModal.selectServiceRequestCategory(this.serviceRequestCategory);			
		}
		
		PFRequestServiceModal.selectServiceRequest(serviceRequestName);
		PFRequestServiceModal.typeNotes(notes);
		PFRequestServiceModal.selectSeverity(severity);
		
		if(serviceRequestCategory != ServiceRequestCategory.HK && scheduleDate != null){
			PFRequestServiceModal.clickSetSchedule();
			PFRequestServiceModal.typeScheduleDate(scheduleDate);			
		}
		
		if(resourceName != null){
			PFRequestServiceModal.assignRatioButton();
			PFRequestServiceModal.typeAssignedResourceName(resourceName);
		}
		
		if(serviceRequestCategory == ServiceRequestCategory.MT && inventoryBlockType != null){
			PFRequestServiceModal.clickBlockRoom();
			PFRequestServiceModal.typeBlockStartDate(blockStartDate);
			PFRequestServiceModal.typeBlockEndDate(blockEndDate);
			PFRequestServiceModal.selectBlockStatus(inventoryBlockType);
		}
		PFRequestServiceModal.clickDone();
		return this;
	}

	public PORequestServiceModal cancelServiceRequest(){

		PFRequestServiceModal.selectAreaType(this.areaType);
		PFRequestServiceModal.selectBuilding(this.buildingName);

		if(this.areaName != null){
			PFRequestServiceModal.typeAreaName(areaName);
		}else if(this.roomNumber != null){
			PFRequestServiceModal.typeRoomNumber(roomNumber);
			PFRequestServiceModal.selectServiceRequestCategory(this.serviceRequestCategory);
		}

		PFRequestServiceModal.selectServiceRequest(serviceRequestName);
		//PFRequestServiceModal.typeNotes(notes);
		PFRequestServiceModal.selectSeverity(severity);

		if(serviceRequestCategory != ServiceRequestCategory.HK && scheduleDate != null){
			PFRequestServiceModal.clickSetSchedule();
			PFRequestServiceModal.typeScheduleDate(scheduleDate);
		}

		if(resourceName != null){
			PFRequestServiceModal.assignRatioButton();
			PFRequestServiceModal.typeAssignedResourceName(resourceName);
		}

		if(serviceRequestCategory == ServiceRequestCategory.MT && inventoryBlockType != null){
			PFRequestServiceModal.clickBlockRoom();
			PFRequestServiceModal.typeBlockStartDate(blockStartDate);
			PFRequestServiceModal.typeBlockEndDate(blockEndDate);
			PFRequestServiceModal.selectBlockStatus(inventoryBlockType);
		}
		PFRequestServiceModal.clickCancel();
		return this;
	}
	
	public PORequestServiceModal createServiceRequestFromMainMenu(){
		PFHeaderDropDowns.navigateToRequestService();
		createServiceRequest();
		return this;
	}
	
	public PORequestServiceModal createServiceRequestFromHousekeepingHomePage(){
		PFHeaderDropDowns.navigateToHouseKeeping();
		PFHousekeepingHome.clickRequestService();
		createServiceRequest();
		return this;
	}
	
	public PORequestServiceModal createServiceRequestFromMaintenancePage(){
		PFHeaderDropDowns.navigateToMaintenace();
		PFMaintenanceHome.clickRequestService();
		createServiceRequest();
		return this;
	}
}
