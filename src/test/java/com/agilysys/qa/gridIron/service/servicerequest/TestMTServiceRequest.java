package com.agilysys.qa.gridIron.service.servicerequest;

import com.agilysys.common.model.statuses.RoomInventoryStatus;
import com.agilysys.qa.client.area.room.ClientRoomCreate;
import com.agilysys.qa.data.builder.area.room.BuilderRoom;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.building.CreateBuilding;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.property.model.*;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.pms.servicerequest.model.MaintenanceServiceType;
import com.agilysys.pms.servicerequest.model.MaintenanceStaffMember;
import com.agilysys.pms.servicerequest.model.MaintenanceStaffMemberPropertyAssignment;
import com.agilysys.pms.servicerequest.model.Role;
import com.agilysys.pms.servicerequest.model.Serviceable.AreaType;
import com.agilysys.pms.servicerequest.model.Serviceable.Severity;
import com.agilysys.qa.client.area.general.area.ClientGeneralAreaCreate;
import com.agilysys.qa.client.area.general.area.type.ClientGeneralAreaTypeCreate;
import com.agilysys.qa.client.maintenance.role.ClientMaintenanceRoleGet;
import com.agilysys.qa.client.maintenance.service.ClientMaintenanceServiceCreate;
import com.agilysys.qa.client.maintenance.staff.ClientMaintenanceStaffMemberCreate;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.data.builder.area.general.area.BuilderGeneralArea;
import com.agilysys.qa.data.builder.area.general.area.BuilderGeneralAreaType;
import com.agilysys.qa.data.builder.maintenance.service.BuilderMaintenanceServiceType;
import com.agilysys.qa.data.builder.maintenance.staff.BuilderMaintenanceStaffMember;
import com.agilysys.qa.data.builder.maintenance.staff.BuilderMaintenanceStaffMemberPropertyAssignment;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTMainSection;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFRequestServiceModal;
import com.agilysys.qa.gridIron.constants.pagefactory.maintenance.PFMaintenanceHome;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.housekeeping.PORequestServiceModal;
import com.agilysys.qa.gridIron.pageobject.housekeeping.PORequestServiceModal.ServiceRequestCategory;
import com.agilysys.qa.gridIron.pageobject.maintenance.POMaintenanceHome;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationCheckInModal;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationSummary;
import com.agilysys.qa.gridIron.pageobject.reservation.PORooms;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.PMSHelper;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.VerifyInventoryBlock;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.validations.VerifyServiceRequest;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestMTServiceRequest extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;
	String tenantId = null;
	String propertyId = null;

	Building building = null;
	Building building2 = null;
	LocalDate propertyDate = null;
	GeneralAreaType generalAreaType = null;

	MaintenanceServiceType maintenanceServiceType = null;
	MaintenanceServiceType updateMaintenanceServiceType = null;

	MaintenanceStaffMember maintenanceStaffMember = null;
	String mtStaffRoll = "MTE";
	String mtStaffName = null;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);
		masterObject = new CreatePopulatedProperty().createProperty("StayAutomation " + tenant.getTenantCode(),
				tenant.getTenantCode(), propertycode, propertycode).createBuilding().createRooms(3).createBuilding()
				.createReservation(1).create();
		new PMSHelper().enableIDTech(masterObject.getProperty());
		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		building = masterObject.getBuilding();

		generalAreaType = new ClientGeneralAreaTypeCreate().createGeneralAreaType(masterObject.getProperty(),
				new BuilderGeneralAreaType(masterObject.getProperty()).build());
		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());

		// Two building required to populate building dropdown in service
		// request modal.
		building2 = new CreateBuilding().create(masterObject.getProperty());
		GeneralArea generalArea2 = new BuilderGeneralArea(masterObject.getProperty(), building2,
				RandomHelper.getRandomAlphaNumericString(5), generalAreaType, propertyDate).build();
		generalArea2 = new ClientGeneralAreaCreate().createGeneralArea(masterObject.getProperty(), generalArea2);

		List<Role> maintenanceRoles = new ClientMaintenanceRoleGet().getRoles(tenantId, propertyId);

		maintenanceServiceType = new BuilderMaintenanceServiceType(RandomHelper.getRandomAlphaNumericString(5),
				RandomHelper.getRandomAlphaNumericString(5), maintenanceRoles.get(0)).build();
		maintenanceServiceType = new ClientMaintenanceServiceCreate().createServiceType(masterObject.getProperty(),
				maintenanceServiceType);

		updateMaintenanceServiceType = new BuilderMaintenanceServiceType(RandomHelper.getRandomAlphaNumericString(5),
				RandomHelper.getRandomAlphaNumericString(5), maintenanceRoles.get(0)).build();
		updateMaintenanceServiceType = new ClientMaintenanceServiceCreate()
				.createServiceType(masterObject.getProperty(), updateMaintenanceServiceType);

		MaintenanceStaffMemberPropertyAssignment maintenanceStaffMemberPropertyAssignment = new BuilderMaintenanceStaffMemberPropertyAssignment(
				masterObject.getProperty(), maintenanceRoles.get(0), 1, true, true, false).build();
		maintenanceStaffMember = new BuilderMaintenanceStaffMember(masterObject.getProperty(),
				RandomHelper.getRandomAlphaString(10), RandomHelper.getRandomAlphaString(10))
						.setStaffMemberPropertyAssignments(maintenanceStaffMemberPropertyAssignment).build();
		maintenanceStaffMember = new ClientMaintenanceStaffMemberCreate().createStaffMember(masterObject.getProperty(),
				maintenanceStaffMember);
		mtStaffName = maintenanceStaffMember.getLastName() + ", " + maintenanceStaffMember.getFirstName() + " - "
				+ maintenanceRoles.get(0).getCode();
		new LoginApplication().Login(masterObject);
	}

	@BeforeMethod
	public void beforeMethod() {
		open(Endpoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);
	}

	@Test
	@TestCase(id = "RST-7230")
	public void testCreateMaintenanceRequestForRoom() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName).createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(id = "RST-1716")
	public void testCreateMaintenanceRequestForRoomWithMinorSeverity() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName).setSeverity(Severity.MINOR).createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	public void testCreateMaintenanceRequestForRoomWithNormalSeverity() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName).setSeverity(Severity.NORMAL).createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(ids={"RST-1714","RST-1719"})
	public void testCreateMaintenanceRequestForRoomWithUrgentSeverity() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, "Electrical")
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName).setSeverity(Severity.URGENT).createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), "ELECT");
		VerifyServiceRequest.verify(poRequestServiceModal);
	}
	
	@Test
	@TestCase(ids={"RST-1720"})
	public void testCreateMaintenanceRequestForRoomUnAssigned() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, "Other")
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), "OTHER");
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(ids={"RST-1721","RST-7224"})
	public void testScheduleMaintenanceRequestForRoom() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, "Heating, Ventilation, and Air Conditioning")
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName).setSchedueDate(propertyDate).createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), "HVAC");
		VerifyServiceRequest.verify(poRequestServiceModal);
	}
	
	@Test
	@TestCase(ids = {"RST-1882","RST-1722", "RST-795"})
	public void testCreateMaintenanceRequestForRoomWithInventoryBlockOOO() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, "Plumbing")
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName)
						.setBlockDetail(propertyDate, propertyDate, RoomInventoryStatus.CanonicalId.OUT_OF_ORDER)
						.createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), "PLMB");
		VerifyServiceRequest.verify(poRequestServiceModal);
		PFRequestServiceModal.clickCancel();

		PFHeaderDropDowns.navigateToInventoryBlocks();
		InventoryBlock inventoryBlock = new InventoryBlock();
		inventoryBlock.setStartDate(propertyDate);
		inventoryBlock.setEndDate(propertyDate);
		VerifyInventoryBlock.verifyInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OUT_OF_ORDER,
				inventoryBlock);
	}

	@Test
	@TestCase(ids = {"RST-1884","RST-1723"})
	public void testCreateMaintenanceRequestForRoomWithInventoryBlockOTM() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName)
						.setBlockDetail(propertyDate, propertyDate, RoomInventoryStatus.CanonicalId.OFF_THE_MARKET)
						.createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
		PFRequestServiceModal.clickCancel();

		PFHeaderDropDowns.navigateToInventoryBlocks();
		InventoryBlock inventoryBlock = new InventoryBlock();
		inventoryBlock.setStartDate(propertyDate);
		inventoryBlock.setEndDate(propertyDate);
		VerifyInventoryBlock.verifyInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.OFF_THE_MARKET,
				inventoryBlock);
	}

	@Test
	@TestCase(id = "RST-1883")
	public void testCreateMaintenanceRequestForRoomWithInventoryBlockHold() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName)
						.setBlockDetail(propertyDate, propertyDate, RoomInventoryStatus.CanonicalId.HOLD)
						.createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
		PFRequestServiceModal.clickCancel();

		PFHeaderDropDowns.navigateToInventoryBlocks();
		InventoryBlock inventoryBlock = new InventoryBlock();
		inventoryBlock.setStartDate(propertyDate);
		inventoryBlock.setEndDate(propertyDate);
		VerifyInventoryBlock.verifyInventoryBlock(room.getRoomNumber(), RoomInventoryStatus.CanonicalId.HOLD,
				inventoryBlock);
	}

	@Test
	@TestCase(ids = {"RST-7231","RST-7225"})
	public void testCreateMaintenanceRequestForGeneralArea() {
		GeneralArea generalArea = createGeneralArea();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.GENERAL_AREA,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setAreaName(generalArea.getName()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName).setSchedueDate(propertyDate).createServiceRequestFromMainMenu();
		POMaintenanceHome.goToGeneralAreaMTRequest(generalArea.getName(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	public void testCreateMaintenanceServiceRequestForRoomFromMaintenancePage() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName)
						.setBlockDetail(propertyDate, propertyDate, RoomInventoryStatus.CanonicalId.HOLD)
						.createServiceRequestFromMaintenancePage();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	public void testCreateMaintenanceServiceRequestForGeneralAreaFromMaintenancePage() {
		GeneralArea generalArea = createGeneralArea();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.GENERAL_AREA,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setAreaName(generalArea.getName()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName).createServiceRequestFromMaintenancePage();
		POMaintenanceHome.goToGeneralAreaMTRequest(generalArea.getName(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	public void testUpdateMaintenanceRequestChangeService() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(mtStaffName).createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		// Update service request
		poRequestServiceModal.serviceRequestName = updateMaintenanceServiceType.getName();
		PFRequestServiceModal.selectServiceRequest(poRequestServiceModal.serviceRequestName);
		PFRequestServiceModal.clickDone();

		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), updateMaintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(ids = {"RST-1762","RST-802"})
	public void testUpdateMaintenanceRequestAssignResource() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		// Update service request
		poRequestServiceModal.assignResource(mtStaffName);

		PFRequestServiceModal.assignRatioButton();
		PFRequestServiceModal.typeAssignedResourceName(poRequestServiceModal.resourceName);
		PFRequestServiceModal.clickDone();

		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test	
	@TestCase(ids = {"RST-1765","RST-753"})
	public void testUpdateMaintenanceRequestChangeSeverity() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.setSeverity(Severity.MINOR).createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		// Update service request
		poRequestServiceModal.setSeverity(Severity.URGENT);
		PFRequestServiceModal.selectSeverity(poRequestServiceModal.severity);
		PFRequestServiceModal.clickDone();

		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(ids = {"RST-1752","RST-2776"})
	public void testUpdateMaintenanceRequestChangeStatus() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.createServiceRequestFromMainMenu();
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		// Update service request
		PFRequestServiceModal.selectStatus("Completed");
		PFRequestServiceModal.clickDone();

		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verifyServiceRequestStatus("Completed");
	}

	@Test
	@TestCase(ids = {"RST-818","RST-1770","RST-747"})
	public void testDeleteMaintenanceRequest() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.createServiceRequestFromMainMenu();

		POMaintenanceHome.deleteRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		PFHeaderDropDowns.navigateToMaintenace();
		PFMaintenanceHome.clickRoomMTRequests();
		VerifyServiceRequest.verifyElementNotPresent(LocatorsMTMainSection
				.getMaintenanceServiceRequestDelete(room.getRoomNumber(), maintenanceServiceType.getServiceCode()));
	}
	
	@Test
	@TestCase(id="RST-683")
	public void testCreateAndUpdateMTRequestFromReservation() {
		Room room = createRoom();
		ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		BuilderBookReservation builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
			builderBookReservation
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
					masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
			.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
			.setDepositDetails("Cash", "0")
			.build();
	
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		new POReservationCheckInModal().checkInWithAuthorization();		
		VerifyReservation.verifyCheckIn();
		
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
				null, ServiceRequestCategory.MT, maintenanceServiceType.getName())
						.setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.setRoomNumber(room.getRoomNumber())
						.assignResource(mtStaffName).setSeverity(Severity.URGENT);
		
		POReservationSummary.createServiceRequest(poRequestServiceModal);
		Selenide.refresh();
		
		POReservationSummary.openEditServiceRequestModal(maintenanceServiceType.getServiceCode(), room.getRoomNumber());
		VerifyServiceRequest.verify(poRequestServiceModal);
		
		PFRequestServiceModal.selectStatus("Completed");
		PFRequestServiceModal.clickDone();		
		
		POMaintenanceHome.goToRoomMTRequest(room.getRoomNumber(), maintenanceServiceType.getServiceCode());
		VerifyServiceRequest.verifyServiceRequestStatus("Completed");
	}

	@Test
	@TestCase(id="RST-7200")
	public void testCreateMTRequestFromReservationWithSameRoomNoInMultipleBuildings() {
		Room room = createRoom();

		Room room1 = new BuilderRoom(masterObject.getProperty(), building2, masterObject.getRoomTypes().get(0), room.getRoomNumber(), room.getStartDate()).build();
		room1 = new ClientRoomCreate().createRoom(masterObject.getProperty(), room1);

		ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		BuilderBookReservation builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
		builderBookReservation
				.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
						masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
				.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
				.setDepositDetails("Cash", "0")
				.build();

		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		PORooms.assignRoom(room.getRoomNumber());
		new POReservationCheckInModal().checkInWithAuthorization();
		VerifyReservation.verifyCheckIn();

		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
				null, ServiceRequestCategory.MT, maintenanceServiceType.getName())
				.setNotes(RandomHelper.getRandomAlphaNumericString(20))
				.setRoomNumber(room.getRoomNumber())
				.assignResource(mtStaffName).setSeverity(Severity.URGENT);

		POReservationSummary.createServiceRequest(poRequestServiceModal);
		Selenide.refresh();

		POReservationSummary.openEditServiceRequestModal(maintenanceServiceType.getServiceCode(), room.getRoomNumber());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	private Room createRoom() {
		return new CreateRoom().create(masterObject.getRoomTypes().get(0), masterObject.getBuilding(), masterObject.getProperty());
	}

	private GeneralArea createGeneralArea() {
		GeneralArea generalArea = new BuilderGeneralArea(masterObject.getProperty(), masterObject.getBuilding(),
				RandomHelper.getRandomAlphaNumericString(5), generalAreaType, propertyDate).build();
		return new ClientGeneralAreaCreate().createGeneralArea(masterObject.getProperty(), generalArea);
	}
}