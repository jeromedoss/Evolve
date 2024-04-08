package com.agilysys.qa.gridIron.service.servicerequest;

import com.agilysys.common.model.FrequencyType;
import com.agilysys.common.model.rate.PetRateSnapshot;
import com.agilysys.common.model.statuses.HousekeepingRoomCondition;
import com.agilysys.common.model.statuses.HousekeepingRoomCondition.CanonicalId;
import com.agilysys.insertanator.adjustments.reservations.AdjustReservation;
import com.agilysys.insertanator.adjustments.reservations.ManipulateReservation;
import com.agilysys.insertanator.creates.reservation.CreateReservation;
import com.agilysys.insertanator.services.create.reservationServices.ServiceReservationFeed;
import com.agilysys.pms.account.model.AccountDetail;
import com.agilysys.pms.property.model.Building;
import com.agilysys.pms.property.model.Room;
import com.agilysys.pms.property.model.RoomStatusUpdate;
import com.agilysys.pms.property.model.RoomType;
import com.agilysys.pms.property.model.GeneralArea;
import com.agilysys.pms.property.model.GeneralAreaType;
import com.agilysys.pms.property.model.AnimalTypes;
import com.agilysys.pms.property.model.PetSettings;
import com.agilysys.pms.rates.model.Component;
import com.agilysys.pms.rates.model.ComponentBundle;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.pms.reservation.model.ReservationSummary;
import com.agilysys.pms.reservation.model.Pet;
import com.agilysys.pms.reservation.model.PetInfo;
import com.agilysys.pms.reservation.model.ReservationWriteOptionalParameters;
import com.agilysys.pms.reservation.model.ReservationCheckoutRequest;
import com.agilysys.qa.client.account.ClientAccountGet;
import com.agilysys.qa.client.account.item.ClientAccountingItemGet;
import com.agilysys.qa.client.area.room.ClientRoomCreate;
import com.agilysys.qa.client.area.room.ClientRoomGet;
import com.agilysys.qa.client.area.room.ClientRoomUpdate;
import com.agilysys.qa.client.area.room.type.ClientRoomTypeUpdate;
import com.agilysys.qa.client.house.keeping.pattern.ClientHousekeepingPatternCreate;
import com.agilysys.qa.client.house.keeping.service.ClientHousekeepingServiceTypeGet;
import com.agilysys.qa.client.house.keeping.staff.ClientStaffMemberUpdate;
import com.agilysys.qa.client.payment.gateway.ClientPaymentGatewayGet;
import com.agilysys.qa.client.pets.ClientAnimalAdd;
import com.agilysys.qa.client.pets.ClientAnimalGet;
import com.agilysys.qa.client.pets.ClientAnimalUpdate;
import com.agilysys.qa.client.pets.ClientReservationForPet;
import com.agilysys.qa.client.reservation.ClientReservationCheckout;
import com.agilysys.qa.client.reservation.ClientReservationUpdate;
import com.agilysys.qa.client.service.request.house.keeping.ClientHousekeepingServiceRequestAssign;
import com.agilysys.qa.client.service.request.house.keeping.ClientHousekeepingServiceRequestCreate;
import com.agilysys.qa.client.service.request.house.keeping.ClientHousekeepingServiceRequestGet;
import com.agilysys.qa.client.service.request.house.keeping.ClientHousekeepingServiceRequestUpdate;
import com.agilysys.qa.client.service.request.status.ClientServiceRequestStatusGet;
import com.agilysys.qa.data.builder.area.room.BuilderRoom;
import com.agilysys.qa.data.builder.area.room.BuilderRoomDetails;
import com.agilysys.qa.data.builder.area.room.BuilderRoomStatusUpdate;
import com.agilysys.qa.data.builder.house.keeping.pattern.BuilderServicePattern;
import com.agilysys.qa.data.builder.pets.BuilderAnimalTypes;
import com.agilysys.qa.data.builder.rate.BuilderComponent;
import com.agilysys.qa.data.builder.service.request.BuilderHousekeepingServiceRequest;
import com.agilysys.qa.gridIron.builders.home.SearchReservationAndOpen;
import com.agilysys.qa.gridIron.builders.reservation.ModifyStayReservation;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsAssignSection;
import com.agilysys.qa.gridIron.constants.locators.housekeeping.main.LocatorsAssignedServicesSection;
import com.agilysys.qa.gridIron.constants.locators.reservation.summary.LocatorsSummaryModal;
import com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFHousekeepingHome;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFModifyStayModal;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFReservationSummary;
import com.agilysys.qa.gridIron.pageobject.reservation.POModifyStayPanel;
import com.agilysys.qa.gridIron.pageobject.shared.widgets.POCalendars;
import com.agilysys.insertanator.constants.GatewayConstants;
import com.agilysys.platform.common.exception.ClientException;
import com.agilysys.platform.common.rguest.exception.RGuestException;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.account.model.PaymentMethod;
import com.agilysys.pms.profile.model.Group;
import com.agilysys.pms.property.model.DateRollStatus;
import com.agilysys.pms.reservation.model.ReservationCancellationRequest;
import com.agilysys.pms.reservation.model.SummaryReport;
import com.agilysys.qa.client.group.ClientGroupRelease;
import com.agilysys.qa.client.payment.method.ClientPaymentMethodGet;
import com.agilysys.qa.client.reservation.ClientReservationCancel;
import com.agilysys.qa.client.reservation.ClientReservationGet;
import com.agilysys.qa.clients.ClientFactory;
import com.agilysys.qa.clients.PmsClientException;
import com.agilysys.qa.data.builder.reservation.BuilderReservationCancellationRequest;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.building.CreateBuilding;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.creates.room.CreateRoom;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.pms.servicerequest.model.StaffMember;
import com.agilysys.pms.servicerequest.model.Role;
import com.agilysys.pms.servicerequest.model.HousekeepingServiceType;
import com.agilysys.pms.servicerequest.model.HousekeepingServiceRequest;
import com.agilysys.pms.servicerequest.model.HousekeepingStaffMemberPropertyAssignment;
import com.agilysys.pms.servicerequest.model.GuestServiceType;
import com.agilysys.pms.servicerequest.model.CompletionCost;
import com.agilysys.pms.servicerequest.model.ServicePattern;
import com.agilysys.pms.servicerequest.model.Serviceable.AreaType;
import com.agilysys.pms.servicerequest.model.Serviceable.Severity;
import com.agilysys.qa.client.area.general.area.ClientGeneralAreaCreate;
import com.agilysys.qa.client.area.general.area.type.ClientGeneralAreaTypeCreate;
import com.agilysys.qa.client.house.keeping.guest.service.ClientGuestServiceTypeCreate;
import com.agilysys.qa.client.house.keeping.role.ClientHousekeepingRoleGet;
import com.agilysys.qa.client.house.keeping.service.ClientHousekeepingServiceTypeCreate;
import com.agilysys.qa.client.house.keeping.staff.ClientStaffMemberCreate;
import com.agilysys.qa.client.maintenance.role.ClientMaintenanceRoleGet;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.data.builder.area.general.area.BuilderGeneralArea;
import com.agilysys.qa.data.builder.area.general.area.BuilderGeneralAreaType;
import com.agilysys.qa.data.builder.house.keeping.service.BuilderGuestServiceType;
import com.agilysys.qa.data.builder.house.keeping.service.BuilderHousekeepingServiceType;
import com.agilysys.qa.data.builder.house.keeping.service.BuilderRoomCompletionCost;
import com.agilysys.qa.data.builder.house.keeping.staff.BuilderHousekeepingStaffMemberPropertyAssignment;
import com.agilysys.qa.data.builder.house.keeping.staff.BuilderStaffMember;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.housekeeping.PFRequestServiceModal;
import com.agilysys.qa.gridIron.pageobject.booking.POBookAReservationModal;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.housekeeping.POHousekeepingHome;
import com.agilysys.qa.gridIron.pageobject.housekeeping.PORequestServiceModal;
import com.agilysys.qa.gridIron.pageobject.housekeeping.PORequestServiceModal.ServiceRequestCategory;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationCheckInModal;
import com.agilysys.qa.gridIron.pageobject.reservation.POReservationSummary;
import com.agilysys.qa.gridIron.pageobject.reservation.PORooms;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.PMSHelper;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.validations.VerifyServiceRequest;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.agilysys.qa.helpers.ClientHelper;
import com.agilysys.qa.helpers.ThreadHelper;
import com.google.common.collect.Maps;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickTabReservations;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.*;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestHKServiceRequest extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;
	String tenantId = null;
	String propertyId = null;
	Building building = null;
	Building building2 = null;
	LocalDate propertyDate = null;
	GeneralAreaType generalAreaType = null;

	HousekeepingServiceType housekeepingServiceType = null;
	HousekeepingServiceType updateHousekeepingServiceType = null;
	GuestServiceType guestServiceType = null;
	Role hkRole = null;
	RoomType roomType;

	StaffMember hkStaff = null;
	String hkStaffRoll = "GRA";
	String hkStaffName = null;
	
	BuilderBookReservation builderBookReservation = null;
	ReservationCreateRequest reservationCreateRequest = null;

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

		hkRole = new ClientHousekeepingRoleGet().getRoleById(masterObject.getProperty(), hkStaffRoll);
		List<Role> maintenanceRoles = new ClientMaintenanceRoleGet().getRoles(tenantId, propertyId);
		housekeepingServiceType = new BuilderHousekeepingServiceType(RandomHelper.getRandomAlphaNumericString(6),
				RandomHelper.getRandomAlphaNumericString(6), hkRole, new BuilderRoomCompletionCost().build()).build();
		housekeepingServiceType = new ClientHousekeepingServiceTypeCreate()
				.createRoomServiceType(masterObject.getProperty(), housekeepingServiceType);

		updateHousekeepingServiceType = new BuilderHousekeepingServiceType(RandomHelper.getRandomAlphaNumericString(6),
				RandomHelper.getRandomAlphaNumericString(6), hkRole, new BuilderRoomCompletionCost().build()).build();
		updateHousekeepingServiceType = new ClientHousekeepingServiceTypeCreate()
				.createRoomServiceType(masterObject.getProperty(), updateHousekeepingServiceType);

		guestServiceType = new BuilderGuestServiceType(RandomHelper.getRandomAlphaNumericString(6),
				RandomHelper.getRandomAlphaNumericString(6), hkRole, new BuilderRoomCompletionCost().build()).build();
		guestServiceType = new ClientGuestServiceTypeCreate().createGuestServiceType(masterObject.getProperty(),
				guestServiceType);

		HousekeepingStaffMemberPropertyAssignment staffMemberPropertyAssignment = new BuilderHousekeepingStaffMemberPropertyAssignment(
				masterObject.getProperty(), hkRole, 1, true, true, false).build();
		hkStaff = new BuilderStaffMember(masterObject.getProperty(), RandomHelper.getRandomAlphaString(10),
				RandomHelper.getRandomAlphaString(10), staffMemberPropertyAssignment).build();
		hkStaff = new ClientStaffMemberCreate().createStaffMember(masterObject.getProperty(), hkStaff);
		hkStaffName = hkStaff.getLastName() + ", " + hkStaff.getFirstName() + " - " + hkStaffRoll;

		roomType = masterObject.getRoomTypes().get(0);
		roomType.setPets(true);
		roomType.setMaxPets(1);
		roomType = new ClientRoomTypeUpdate().updateRoomType(masterObject.getProperty(), roomType);
		enablePetSettings();
		new LoginApplication().Login(masterObject);
	}

	@BeforeMethod
	public void beforeMethod() {
		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());
		new ClientStaffMemberUpdate().putStaffMemberOnDuty(masterObject.getProperty(), hkStaff);
		open(Endpoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);
	}

	@Test
	@TestCase(ids={"RST-1818", "RST-4721"})
	public void testCreateHKServiceRequestForRoom() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HK, housekeepingServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(hkStaffName).setSeverity(Severity.URGENT).createServiceRequestFromMainMenu();

		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), housekeepingServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(id ="RST-4673")
	public void testCanMoveStaffOnDuty() {
		HousekeepingStaffMemberPropertyAssignment staffMemberPropertyAssignment = new BuilderHousekeepingStaffMemberPropertyAssignment(
				masterObject.getProperty(), hkRole, 1, true, false, false).build();
		StaffMember newStaff = new BuilderStaffMember(masterObject.getProperty(), RandomHelper.getRandomAlphaString(10),
				RandomHelper.getRandomAlphaString(10), staffMemberPropertyAssignment).build();
		// call create
		newStaff = new ClientStaffMemberCreate().createStaffMember(masterObject.getProperty(), newStaff);
		// put staff on duty
		StaffMember onDutyStaffMember =
				new ClientStaffMemberUpdate().putStaffMemberOnDuty(masterObject.getProperty(), newStaff);
		Assert.assertTrue(onDutyStaffMember.getStaffMemberPropertyAssignments().iterator().next().isOnDuty(), "Expected staff to be on duty");
	}

	@Test
	@TestCase(ids = {"RST-4722", "RST-2761"})
	public void testCreateUnAssignedHKServiceRequestForRoom() {
		Room room = createRoom();
		new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HK, housekeepingServiceType.getName())
				.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
				.setSeverity(Severity.URGENT).createServiceRequestFromMainMenu();

		open(Endpoints.getBasePMSUrl() + "#/housekeeping/assign?tenantId=" + tenantId + "&propertyId=" + propertyId);
		Assert.assertTrue($(LocatorsAssignSection.UNASSIGNED_SERVICE_REQUEST).getText().replace("\n", " ").contains(room.getRoomNumber()+ " "+masterObject.getRoomTypes().get(0).getTypeCode()), "Should contain in un assigned requests");
	}

	@Test
	@TestCase(id ="RST-1997")
	public void testCreateHKServiceRequestForRoomWithInProgress() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HK, housekeepingServiceType.getName())
				.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
				.assignResource(hkStaffName).setSeverity(Severity.URGENT).createServiceRequestFromMainMenu();
		List<HousekeepingServiceRequest> allServiceRequests = new ClientHousekeepingServiceRequestGet().getAllServiceRequests(masterObject.getProperty());
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), housekeepingServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(ids = {"RST-1733","RST-1747","RST-7218"})
	public void testCreateHKGServiceRequestForRoom() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HKG, guestServiceType.getName())
				.setSeverity(Severity.URGENT)
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(hkStaffName).createServiceRequestFromMainMenu();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), guestServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(id="RST-2762")
	public void testCreateHKGServiceRequestWithSameRoomNoInMultipleBuildings() {
		Room room = createRoom();
		Room room1 = new BuilderRoom(masterObject.getProperty(), building2, masterObject.getRoomTypes().get(0), room.getRoomNumber(), room.getStartDate()).build();
		room1 = new ClientRoomCreate().createRoom(masterObject.getProperty(), room1);
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HKG, guestServiceType.getName())
				.setSeverity(Severity.URGENT)
				.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
				.assignResource(hkStaffName).createServiceRequestFromMainMenu();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), guestServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(id="RST-7210")
	public void testScheduleHKGServiceRequestForRoom() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HKG, guestServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(hkStaffName).setSchedueDate(propertyDate).createServiceRequestFromMainMenu();

		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), guestServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(ids = {"RST-1599", "RST-1600"})
	public void testCreateHKServiceRequestForRoomFromHousekeepingHomePage() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null, building.getName(),
				ServiceRequestCategory.HK, housekeepingServiceType.getName()).setRoomNumber(room.getRoomNumber())
						.setNotes(RandomHelper.getRandomAlphaNumericString(20)).assignResource(hkStaffName)
						.setSeverity(Severity.MINOR).createServiceRequestFromHousekeepingHomePage();

		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), housekeepingServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	public void testCreateHKGServiceRequestForRoomFromHousekeepingHomePage() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null, building.getName(),
				ServiceRequestCategory.HKG, guestServiceType.getName()).setRoomNumber(room.getRoomNumber())
						.setNotes(RandomHelper.getRandomAlphaNumericString(20)).assignResource(hkStaffName)
						.createServiceRequestFromHousekeepingHomePage();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), guestServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
		VerifyServiceRequest.verifyAreaType(AreaType.ROOM);
	}

	@Test
	@TestCase(id = "RST-7212")
	public void testCreateHKGServiceRequestForRoomFromMaintenancePage() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HKG, guestServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(hkStaffName).createServiceRequestFromMaintenancePage();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), guestServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
		VerifyServiceRequest.verifyAreaType(AreaType.ROOM);
	}

	@Test	
	public void testUpdateHKServiceRequestChangeSeverity() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HK, housekeepingServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(hkStaffName).setSeverity(Severity.URGENT).createServiceRequestFromMainMenu();

		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), housekeepingServiceType.getServiceCode());
		poRequestServiceModal.setSeverity(Severity.MINOR);
		PFRequestServiceModal.selectSeverity(poRequestServiceModal.severity);
		PFRequestServiceModal.clickDone();
		PFHeaderDropDowns.navigateToSearch();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), housekeepingServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	public void testUpdateHKServiceRequestChangeStatus() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HK, housekeepingServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(hkStaffName).setSeverity(Severity.URGENT).createServiceRequestFromMainMenu();

		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), housekeepingServiceType.getServiceCode());
		PFRequestServiceModal.selectStatus("Completed");
		PFRequestServiceModal.clickDone();

		PFHeaderDropDowns.navigateToSearch();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), housekeepingServiceType.getServiceCode());
		VerifyServiceRequest.verifyServiceRequestStatus("Completed");
	}
	
	@Test
	@TestCase(ids={"RST-1859","RST-1188","RST-703"})
	public void testUpdateHKGServiceRequestChangeStatus() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HKG, guestServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(hkStaffName)
						.setSeverity(Severity.URGENT).createServiceRequestFromMainMenu();

		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), guestServiceType.getServiceCode());
		PFRequestServiceModal.selectStatus("Completed");
		PFRequestServiceModal.clickDone();

		PFHeaderDropDowns.navigateToSearch();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), guestServiceType.getServiceCode());
		VerifyServiceRequest.verifyServiceRequestStatus("Completed");
	}

	@Test
	@TestCase(id="RST-1610")
	public void testVerifyCanCancelCreatingHKGRequest(){
		Room room = createRoom();
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
		builderBookReservation
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
				masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
			.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
			.setDepositDetails("Cash", "0")
			.build();

		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		Selenide.sleep(4000);
		PORooms.assignRoom(room.getRoomNumber());
		new POReservationCheckInModal().checkInWithAuthorization();
		VerifyReservation.verifyCheckIn();
		PFReservationSummary.clickSVCBadge();
		int beforeRowCount = Selenide.$$(By.xpath("//*[@data-qa-id='fst6SmB']")).size();
		Selenide.refresh();
		//request 1
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
			null, ServiceRequestCategory.HKG, guestServiceType.getName())
			.setNotes(RandomHelper.getRandomAlphaNumericString(20))
			.setRoomNumber(room.getRoomNumber())
			.assignResource(hkStaffName).setSeverity(Severity.NORMAL);

		POReservationSummary.cancelServiceRequest(poRequestServiceModal);
		int afterRowCount = Selenide.$$(By.xpath("//*[@data-qa-id='fst6SmB']")).size();
		//verification
		Assert.assertEquals(afterRowCount,beforeRowCount);
	}

	@Test
	@TestCase(ids={"RST-1516","RST-7219"})
	public void testVerifyHKGUrgentServiceRequestAppearAtTopList(){
		Room room = createRoom();
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
		builderBookReservation
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
				masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
			.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
			.setDepositDetails("Cash", "0")
			.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		Selenide.sleep(4000);
		PORooms.assignRoom(room.getRoomNumber());
		new POReservationCheckInModal().checkInWithAuthorization();
		VerifyReservation.verifyCheckIn();
		//request 1
		PORequestServiceModal poRequestServiceModal1 = new PORequestServiceModal(null,
			null, ServiceRequestCategory.HKG, guestServiceType.getName())
			.setNotes(RandomHelper.getRandomAlphaNumericString(20))
			.setRoomNumber(room.getRoomNumber())
			.assignResource(hkStaffName).setSeverity(Severity.NORMAL);
		POReservationSummary.createServiceRequest(poRequestServiceModal1);
		Selenide.refresh();
		Selenide.sleep(5000);
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
			null, ServiceRequestCategory.HKG, guestServiceType.getName())
			.setNotes(RandomHelper.getRandomAlphaNumericString(20))
			.setRoomNumber(room.getRoomNumber())
			.assignResource(hkStaffName).setSeverity(Severity.URGENT);
		POReservationSummary.createServiceRequest(poRequestServiceModal);
		Selenide.sleep(5000);
		Assert.assertTrue(Selenide.$(By.xpath("//*[@data-qa-id='fst6SmB']")).getText().split("\n")[0].contains("URGENT"));
		doValidDateRoll(masterObject.getProperty(),true);
		Selenide.refresh();
		PFReservationSummary.clickSVCBadge();
		Assert.assertTrue(Selenide.$(By.xpath("//*[@data-qa-id='fst6SmB']")).getText().split("\n")[0].contains("NEW"));
	}

	@Test
	@TestCase(id="RST-1621")
	public void testVerifyCanCreateDuplicateHKGServiceRequest(){
		Room room = createRoom();
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
		builderBookReservation
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
				masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
			.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
			.setDepositDetails("Cash", "0")
			.build();
		POBookAReservationModal.verifyBookConfirmationMessage();
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		Selenide.sleep(4000);
		PORooms.assignRoom(room.getRoomNumber());
		new POReservationCheckInModal().checkInWithAuthorization();
		VerifyReservation.verifyCheckIn();
		//request 1
		PORequestServiceModal poRequestServiceModal1 = new PORequestServiceModal(null,
			null, ServiceRequestCategory.HKG, guestServiceType.getName())
			.setNotes(RandomHelper.getRandomAlphaNumericString(20))
			.setRoomNumber(room.getRoomNumber())
			.assignResource(hkStaffName).setSeverity(Severity.NORMAL);
		POReservationSummary.createServiceRequest(poRequestServiceModal1);
		Selenide.refresh();
		Selenide.sleep(5000);
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
			null, ServiceRequestCategory.HKG, guestServiceType.getName())
			.setNotes(RandomHelper.getRandomAlphaNumericString(20))
			.setRoomNumber(room.getRoomNumber())
			.assignResource(hkStaffName).setSeverity(Severity.NORMAL);
		POReservationSummary.createServiceRequest(poRequestServiceModal);
		Selenide.sleep(5000);
		Assert.assertEquals(Selenide.$(By.xpath("//*[@data-qa-id='fst6SmB']")).getText().split("\n").length,2);
	}

	@Test
	public void testUpdateHKServiceRequestChangeService() {
		Room room = createRoom();
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(AreaType.ROOM,
				building.getName(), ServiceRequestCategory.HK, housekeepingServiceType.getName())
						.setRoomNumber(room.getRoomNumber()).setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.assignResource(hkStaffName).setSeverity(Severity.URGENT).createServiceRequestFromMainMenu();

		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(), housekeepingServiceType.getServiceCode());
		poRequestServiceModal.serviceRequestName = updateHousekeepingServiceType.getName();
		PFRequestServiceModal.selectServiceRequest(poRequestServiceModal.serviceRequestName);
		PFRequestServiceModal.clickDone();
		PFHeaderDropDowns.navigateToSearch();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(),
				updateHousekeepingServiceType.getServiceCode());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}
	
	@Test
	@TestCase(id="RST-3949")
	public void testCreateAndUpdateHKRequestFromReservation() {
		Room room = createRoom();
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
			builderBookReservation
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
					masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
			.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
			.setDepositDetails("Cash", "0")
			.build();
	
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		Selenide.sleep(4000);
		PORooms.assignRoom(room.getRoomNumber());
		new POReservationCheckInModal().checkInWithAuthorization();		
		VerifyReservation.verifyCheckIn();
		
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
				null, ServiceRequestCategory.HK, housekeepingServiceType.getName())
						.setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.setRoomNumber(room.getRoomNumber())
						.assignResource(hkStaffName)
						.setSeverity(Severity.URGENT);
		POReservationSummary.createServiceRequest(poRequestServiceModal);
		Selenide.refresh();
		
		POReservationSummary.openEditServiceRequestModal(housekeepingServiceType.getServiceCode(), room.getRoomNumber());
		VerifyServiceRequest.verify(poRequestServiceModal);
		
		PFRequestServiceModal.selectStatus("Completed");
		PFRequestServiceModal.clickDone();		
		
		PFHeaderDropDowns.navigateToSearch();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(),
				housekeepingServiceType.getServiceCode());		
		VerifyServiceRequest.verifyServiceRequestStatus("Completed");
	}
	
	@Test
	public void testCreateAndUpdateHKGRequestFromReservation() {
		Room room = createRoom();
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
			builderBookReservation
			.setRandomRateSnapshots(2, masterObject.getRatePlans().get(0).getName(),
					masterObject.getRoomTypes().get(0).getTypeCode(), propertyDate)
			.setRandomPersonalDetails().setRandomPhoneNumber().addPaymentMethod("Cash", null, null)
			.setDepositDetails("Cash", "0")
			.build();
	
		POBookAReservationModal.verifyBookConfirmationMessage();		
		PFBookReservationModal.closeBookReservationModal();
		POSearchInMainPage.goToReservation(builderBookReservation.getGuestProfile().getPersonalDetails().getLastName());
		Selenide.sleep(4000);
		PORooms.assignRoom(room.getRoomNumber());
		new POReservationCheckInModal().checkInWithAuthorization();		
		VerifyReservation.verifyCheckIn();
		
		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
				null, ServiceRequestCategory.HKG, guestServiceType.getName())
						.setNotes(RandomHelper.getRandomAlphaNumericString(20))
						.setRoomNumber(room.getRoomNumber())
						.assignResource(hkStaffName).setSeverity(Severity.URGENT);
		
		POReservationSummary.createServiceRequest(poRequestServiceModal);
		Selenide.refresh();
		
		POReservationSummary.openEditServiceRequestModal(guestServiceType.getServiceCode(), room.getRoomNumber());
		VerifyServiceRequest.verify(poRequestServiceModal);
		
		PFRequestServiceModal.selectStatus("Completed");
		PFRequestServiceModal.clickDone();		
		
		PFHeaderDropDowns.navigateToSearch();
		POHousekeepingHome.openEditServiceRequestModal(room.getRoomNumber(),
				guestServiceType.getServiceCode());		
		VerifyServiceRequest.verifyServiceRequestStatus("Completed");
	}

	@Test
	@TestCase(id="RST-7198")
	public void testCreateHKRequestFromReservationWithSameRoomNoInMultipleBuildings() {
		Room room = createRoom();

		Room room1 = new BuilderRoom(masterObject.getProperty(), building2, masterObject.getRoomTypes().get(0), room.getRoomNumber(), room.getStartDate()).build();
		room1 = new ClientRoomCreate().createRoom(masterObject.getProperty(), room1);
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
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
				null, ServiceRequestCategory.HK, housekeepingServiceType.getName())
				.setNotes(RandomHelper.getRandomAlphaNumericString(20))
				.setRoomNumber(room.getRoomNumber())
				.assignResource(hkStaffName)
				.setSeverity(Severity.URGENT);
		POReservationSummary.createServiceRequest(poRequestServiceModal);
		Selenide.refresh();

		POReservationSummary.openEditServiceRequestModal(housekeepingServiceType.getServiceCode(), room.getRoomNumber());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(id="RST-7199")
	public void testCreateHKGRequestFromReservationWithSameRoomNoInMultipleBuildings() {
		Room room = createRoom();
		Room room1 = new BuilderRoom(masterObject.getProperty(), building2, masterObject.getRoomTypes().get(0), room.getRoomNumber(), room.getStartDate()).build();
		room1 = new ClientRoomCreate().createRoom(masterObject.getProperty(), room1);
		reservationCreateRequest = new ReservationCreateRequest();
		reservationCreateRequest.setNumberOfAdults(2);
		reservationCreateRequest.setNumberOfChildren(0);
		builderBookReservation = new BuilderBookReservation(reservationCreateRequest);
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
				null, ServiceRequestCategory.HKG, guestServiceType.getName())
				.setNotes(RandomHelper.getRandomAlphaNumericString(20))
				.setRoomNumber(room.getRoomNumber())
				.assignResource(hkStaffName).setSeverity(Severity.URGENT);
		POReservationSummary.createServiceRequest(poRequestServiceModal);
		Selenide.refresh();

		POReservationSummary.openEditServiceRequestModal(guestServiceType.getServiceCode(), room.getRoomNumber());
		VerifyServiceRequest.verify(poRequestServiceModal);
	}

	@Test
	@TestCase(ids = {"RST-11157", "RST-11148"})
	public void testAssignServiceRequestForReservedPetRoomAndVerifyPetCountInRoomsAndStaff() {
		Room room = createRoom();
		ReservationSummary reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(),
				new ServiceReservationFeed().paymentMethodCash().arrive(propertyDate.toString())
						.depart(propertyDate.plusDays(1).toString()));

		AnimalTypes animalType = createAndGetPet(RandomHelper.getRandomAlphaString(4));
		//create component charge for pet
		Component component =
				new BuilderComponent(new ClientAccountingItemGet()
						.getTransactionItems(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId(),
								false).get(0).getId(), BigDecimal.TEN, FrequencyType.EVERY_N, 1).build();
		component.setnNights(1);
		//create animal charges i.e component bundles and add them to the animal created
		addChargeForPet(animalType.getId(), component);
		//create rateSnapShot for pet
		PetRateSnapshot rateSnapForPet =
				getRate(FrequencyType.EVERY_N, 1, PetRateSnapshot.PetChargeOperation.ADD_CHARGE, component.getId(),
						component.getTransactionItemId(), component.getAmount());
		//update petInfo in the reservation
		new ClientReservationForPet()
				.updatePetInfo(masterObject.getProperty(), reservation.getId(), new ReservationWriteOptionalParameters(),
						getPetInfo(animalType, com.agilysys.qa.helpers.RandomHelper.getRandomAlphaString(4), rateSnapForPet));

		new AdjustReservation().confirmRoom(reservation, masterObject.getProperty(), room);
		openReservation(reservation);

		//create HK request

		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
				null, ServiceRequestCategory.HKG, guestServiceType.getName())
				.setNotes(RandomHelper.getRandomAlphaNumericString(20))
				.setRoomNumber(room.getRoomNumber())
				.assignResource(hkStaffName).setSeverity(Severity.NORMAL);

		POReservationSummary.createServiceRequest(poRequestServiceModal);
		PFHeaderDropDowns.navigateToHouseKeeping();
		PFHousekeepingHome.clickRoomsTab();
		Assert.assertTrue($(LocatorsAssignedServicesSection.getHKRoomRecordByRoomNo(room.getRoomNumber())).getText().replace("\n", " ").contains(room.getRoomNumber()+ " 0 of 1 "+ masterObject.getRoomTypes().get(0).getTypeCode()+" 1"), "count should be matched for roomtype and room number");
		PFHousekeepingHome.clickStaffTab();
		Assert.assertTrue($(LocatorsAssignedServicesSection.getHKStaffRecordByStaffName(hkStaff.getFirstName())).getText().replace("\n", " ").contains(room.getRoomNumber()+ " "+ masterObject.getRoomTypes().get(0).getTypeCode()+" 1"), "count should be matched for roomtype and room number");
	}

	@Test
	@TestCase(ids = {"RST-11156", "RST-11148"})
	public void testAssignServiceRequestForReservedRoomWithoutPetAndVerifyPetCountInRoomsAndStaff() {
		Room room = createRoom();
		ReservationSummary reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(),
				new ServiceReservationFeed().paymentMethodCash().arrive(propertyDate.toString())
						.depart(propertyDate.plusDays(1).toString()));

		new AdjustReservation().confirmRoom(reservation, masterObject.getProperty(), room);
		openReservation(reservation);

		//create HK request

		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
				null, ServiceRequestCategory.HKG, guestServiceType.getName())
				.setNotes(RandomHelper.getRandomAlphaNumericString(20))
				.setRoomNumber(room.getRoomNumber())
				.assignResource(hkStaffName).setSeverity(Severity.NORMAL);

		POReservationSummary.createServiceRequest(poRequestServiceModal);
		PFHeaderDropDowns.navigateToHouseKeeping();
		PFHousekeepingHome.clickRoomsTab();
		Assert.assertTrue($(LocatorsAssignedServicesSection.getHKRoomRecordByRoomNo(room.getRoomNumber())).getText().replace("\n", " ").contains(room.getRoomNumber()+ " 0 of 1 " +masterObject.getRoomTypes().get(0).getTypeCode()+" 1"), "count should be matched for roomtype and room number");
		PFHousekeepingHome.clickStaffTab();
		Assert.assertTrue($(LocatorsAssignedServicesSection.getHKStaffRecordByStaffName(hkStaff.getFirstName())).getText().replace("\n", " ").contains(room.getRoomNumber()+ " "+ masterObject.getRoomTypes().get(0).getTypeCode()+" -"), "count should be matched for roomtype and room number");
	}

	@Test
	@TestCase(ids = {"RST-11155", "RST-11147", "RST-4739"})
	public void testAssignServiceRequestForUnReservedRoomWithoutPetAndVerifyPetCountInRoomsAndStaff() {
		Room room = createRoom();
		//create HK request
		HousekeepingServiceRequest housekeepingServiceRequest = new BuilderHousekeepingServiceRequest(masterObject.getProperty(), propertyDate, new ClientHousekeepingServiceTypeGet()
				.getRoomServiceTypes(masterObject.getProperty()).stream().filter(x -> x.getId().equals("COS")).findAny().get(), new ClientServiceRequestStatusGet()
				.getServiceStatuses(masterObject.getProperty()).stream().filter(x -> x.getId().equals("NEW")).findAny().get(), Severity.NORMAL, room,
				new BuilderRoomDetails(masterObject.getBuilding(), new ClientRoomGet().getRoomById(masterObject.getProperty(), room).getRackNumber()+1, room, roomType)
						.build()).build();

		housekeepingServiceRequest = new ClientHousekeepingServiceRequestCreate().createServiceRequest(masterObject.getProperty(), housekeepingServiceRequest);

		new ClientHousekeepingServiceRequestAssign().assignServiceRequest(masterObject.getProperty(), housekeepingServiceRequest, hkStaff);
		PFHeaderDropDowns.navigateToHouseKeeping();
		PFHousekeepingHome.clickRoomsTab();
		Assert.assertTrue($(LocatorsAssignedServicesSection.getHKRoomRecordByRoomNo(room.getRoomNumber())).getText().replace("\n", " ").contains(room.getRoomNumber()+ " 0 of 1 " +masterObject.getRoomTypes().get(0).getTypeCode()+" 1"), "count should be matched for roomtype and room number");
		PFHousekeepingHome.clickStaffTab();
		Assert.assertTrue($(LocatorsAssignedServicesSection.getHKStaffRecordByStaffName(hkStaff.getFirstName())).getText().replace("\n", " ").contains(room.getRoomNumber()+ " "+ masterObject.getRoomTypes().get(0).getTypeCode()+" -"), "count should be matched for roomtype and room number");
	}

	@Test
	@TestCase(ids = {"RST-11168"})
	public void testAssignServiceRequestForReservedPetRoomAndVerifyPetSymbol() {
		Room room = createRoom();
		ReservationSummary reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(),
				new ServiceReservationFeed().paymentMethodCash().arrive(propertyDate.toString())
						.depart(propertyDate.plusDays(1).toString()));

		AnimalTypes animalType = createAndGetPet(com.agilysys.qa.helpers.RandomHelper.getRandomAlphaString(4));
		//create component charge for pet
		Component component =
				new BuilderComponent(new ClientAccountingItemGet()
						.getTransactionItems(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId(),
								false).get(0).getId(), BigDecimal.TEN, FrequencyType.EVERY_N, 1).build();
		component.setnNights(1);
		//create animal charges i.e component bundles and add them to the animal created
		addChargeForPet(animalType.getId(), component);
		//create rateSnapShot for pet
		PetRateSnapshot rateSnapForPet =
				getRate(FrequencyType.EVERY_N, 1, PetRateSnapshot.PetChargeOperation.ADD_CHARGE, component.getId(),
						component.getTransactionItemId(), component.getAmount());
		//update petInfo in the reservation
		new ClientReservationForPet()
				.updatePetInfo(masterObject.getProperty(), reservation.getId(), new ReservationWriteOptionalParameters(),
						getPetInfo(animalType, com.agilysys.qa.helpers.RandomHelper.getRandomAlphaString(4), rateSnapForPet));

		new AdjustReservation().confirmRoom(reservation, masterObject.getProperty(), room);
		openReservation(reservation);

		//create HK request

		PORequestServiceModal poRequestServiceModal = new PORequestServiceModal(null,
				null, ServiceRequestCategory.HKG, guestServiceType.getName())
				.setNotes(RandomHelper.getRandomAlphaNumericString(20))
				.setRoomNumber(room.getRoomNumber())
				.assignResource(hkStaffName).setSeverity(Severity.NORMAL);

		POReservationSummary.createServiceRequest(poRequestServiceModal);
		open(Endpoints.getBasePMSUrl() + "#/housekeeping/assign?tenantId=" + tenantId + "&propertyId=" + propertyId);
		Selenide.sleep(5000);
		click(LocatorsAssignSection.EXPAND_ASSIGNED_REQUEST);
//	click(By.xpath("//*[@data-qa-id='fkdsJce']//a[contains(text(),'"+room.getRoomNumber()+"')]"));
		Selenide.sleep(6000);
		Assert.assertTrue($(LocatorsAssignSection.PET_SYMBOL).exists(), "pet symbol should be displayed");
	}

	@Test(priority = 1)
	@TestCase(ids = {"RST-7783"})
	public void testDefaultHKPatternStatusWithCommentsVerify(){
		Room room = createRoom();
		ReservationSummary reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(),
				new ServiceReservationFeed().paymentMethodCash().arrive(propertyDate.toString())
						.depart(propertyDate.plusDays(1).toString()));

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, masterObject.getProperty(), room);
		try {
			new PMSHelper().doValidDateRoll(masterObject.getProperty(), true, masterObject);
			Selenide.sleep(4000);
		}catch (Exception e){
			System.out.printf("Ex " + e.getMessage());
		}
		Selenide.refresh();

		openReservation(reservation);
		extendDepartureDate(propertyDate.plusDays(2));
		PFReservationSummary.clickSaveReservation();
		sleep(500);
		PageFactoryBase.click(LocatorsSummaryModal.SELECT_RATES_FOR_ALL_DAYS);
		sleep(500);
		PFModifyStayModal.clickBook();
		sleep(500);
		POModifyStayPanel.confirmModifyStay();
		PFReservationSummary.clickSVCBadge();
		Selenide.sleep(3000);

		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("COS C-MS"), "status should be C-MS");
		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("COSI C-MS"), "status should be C-MS");
		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("SOSI NEW"), "status should be NEW");
		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("SOS NEW"), "status should be NEW");
//		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_COMMENTS).getText().contains(""), "comments should be null");
	}

	@Test(priority = 1)
	@TestCase(ids = {"RST-7784"})
	public void testCustomHKPatternStatusWithCommentsVerify(){
		List<HousekeepingServiceType> housekeepingServiceTypes = new ClientHousekeepingServiceTypeGet().getRoomServiceTypes(masterObject.getProperty());

		Map<String, int[]> services = Maps.newHashMap();
		services.put(housekeepingServiceTypes.get(0).getId(), new int[1]);
		services.put(housekeepingServiceTypes.get(housekeepingServiceTypes.size()-1).getId(), new int[0]);

		ServicePattern servicePattern = new BuilderServicePattern().setLastDayServices(housekeepingServiceTypes.get(housekeepingServiceTypes.size() - 1))
				.setPropertyId(masterObject.getProperty()).setTenantId(masterObject.getProperty()).setType("Short Stay").build();
		servicePattern.setServices(services);
		servicePattern = new ClientHousekeepingPatternCreate().createPattern(masterObject.getProperty(), servicePattern);

		Room room = createRoom();
		ReservationSummary reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(),
				new ServiceReservationFeed().paymentMethodCash().arrive(propertyDate.toString())
						.depart(propertyDate.plusDays(1).toString()));
		new ClientReservationUpdate()
				.updateHousekeepingPatternId(tenantId, propertyId, reservation.getId(), servicePattern.getId(),
						null);

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, masterObject.getProperty(), room);
		new PMSHelper().doValidDateRoll(masterObject.getProperty(), true, masterObject);
		Selenide.refresh();
		Selenide.sleep(3000);
		openReservation(reservation);
		extendDepartureDate(propertyDate.plusDays(2));
		PFReservationSummary.clickSaveReservation();
		sleep(500);
		PageFactoryBase.click(LocatorsSummaryModal.SELECT_RATES_FOR_ALL_DAYS);
		sleep(500);
		PFModifyStayModal.clickBook();
		sleep(500);
		POModifyStayPanel.confirmModifyStay();
		PFReservationSummary.clickSVCBadge();
		Selenide.sleep(3000);

		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains(housekeepingServiceTypes.get(housekeepingServiceTypes.size()-1).getServiceCode()+" C-MS"), "status should be C-MS");
		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains(housekeepingServiceTypes.get(0).getServiceCode()+" NEW"), "status should be NEW");
//		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_COMMENTS).getText().contains(""), "comments should be null");
	}

	@Test
	@TestCase(ids = {"RST-4652", "RST-4655", "RST-4649"})
	public void testHKServicesStatusCompletedVerifyRoomStatus(){
		Room room = createRoom();
		ReservationSummary reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(),
				new ServiceReservationFeed().paymentMethodCash().arrive(propertyDate.toString())
						.depart(propertyDate.plusDays(1).toString()));

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, masterObject.getProperty(), room);
		Selenide.refresh();
		openReservation(reservation);
		new ModifyStayReservation().EarlyCheckoutReservation();
		List<HousekeepingServiceRequest> allServiceRequests = new ClientHousekeepingServiceRequestGet().getServiceRequestsByReservation(masterObject.getProperty(), reservation);
		for (HousekeepingServiceRequest houseKeepingServiceRequest : allServiceRequests) {
			if (houseKeepingServiceRequest.getServiceTypeId().equals("COS") || houseKeepingServiceRequest.getServiceTypeId().equals("COSI")) {
				houseKeepingServiceRequest.setAssignedTo(hkStaff.getId());
				houseKeepingServiceRequest.setStatusId("COM");
				new ClientHousekeepingServiceRequestUpdate().updateServiceRequest(masterObject.getProperty().getTenantId(),
						masterObject.getProperty().getPropertyId(), houseKeepingServiceRequest.getId(),
						houseKeepingServiceRequest);
			}
		}
		Selenide.refresh();
		Selenide.sleep(4000);
		PFHeaderDropDowns.navigateToHouseKeeping();
		Assert.assertTrue($(LocatorsAssignedServicesSection.getHKStaffRecordByStaffName(hkStaff.getFirstName())).getText().replace("\n", " ").contains(room.getRoomNumber()+ " "+ masterObject.getRoomTypes().get(0).getTypeCode()+" - VAC VI COS"), "room status should be matched for roomtype and room number");
		Assert.assertTrue($(LocatorsAssignedServicesSection.getHKStaffRecordByStaffName(hkStaff.getFirstName())).getText().replace("\n", " ").contains(room.getRoomNumber()+ " "+ masterObject.getRoomTypes().get(0).getTypeCode()+" - VAC VI COSI"), "room status should be matched for roomtype and room number");
	}

	@Test(priority = 1)
	@TestCase(ids = {"RST-7791"})
	public void testDefaultHKServicesWithCommentsVerify(){
		Room room = createRoom();
		ReservationSummary reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(),
				new ServiceReservationFeed().paymentMethodCash().arrive(propertyDate.toString())
						.depart(propertyDate.plusDays(1).toString()));

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, masterObject.getProperty(), room);
		new PMSHelper().doValidDateRoll(masterObject.getProperty(), true, masterObject);

		HousekeepingStaffMemberPropertyAssignment staffMemberPropertyAssignment = new BuilderHousekeepingStaffMemberPropertyAssignment(
				masterObject.getProperty(), hkRole, 1, true, true, false).build();
		StaffMember hkStaff1 = new BuilderStaffMember(masterObject.getProperty(), RandomHelper.getRandomAlphaString(10),
				RandomHelper.getRandomAlphaString(10), staffMemberPropertyAssignment).build();
		hkStaff1 = new ClientStaffMemberCreate().createStaffMember(masterObject.getProperty(), hkStaff1);
		Selenide.refresh();

		openReservation(reservation);
		extendDepartureDate(propertyDate.plusDays(2));
		PFReservationSummary.clickSaveReservation();
		sleep(500);
		PageFactoryBase.click(LocatorsSummaryModal.SELECT_RATES_FOR_ALL_DAYS);
		sleep(500);
		PFModifyStayModal.clickBook();
		sleep(500);
		POModifyStayPanel.confirmModifyStay();
		PFReservationSummary.clickSVCBadge();
		Selenide.sleep(3000);

		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("COS C-MS"), "status should be C-MS");
		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("COSI C-MS"), "status should be C-MS");
		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("SOSI NEW"), "status should be NEW");
		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("SOS NEW"), "status should be NEW");
//		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_COMMENTS).getText().contains(""), "comments should be null");
	}

	@Test
	@TestCase(ids = {"RST-2845"})
	public void testVerifyServiceRequestForSameDayReservationCheckout() {
		Room room = createRoom();
		ReservationSummary reservation = new CreateReservation().createWithNewGuestWithReturn(masterObject.getRatePlans().get(0),
				masterObject.getRoomTypes().get(0), masterObject.getProperty(), new ServiceReservationFeed().sameDayReservation(propertyDate.toString(), "150"));

		ManipulateReservation.getAdjustReservation().confirmRoomAndCheckinReservation(reservation, masterObject.getProperty(), room);
		RoomStatusUpdate roomStatusUpdate = new BuilderRoomStatusUpdate(CanonicalId.DIRTY).build();
		new ClientRoomUpdate().updateRoomStatus(masterObject.getProperty(), room, roomStatusUpdate);
		reservation = checkoutReservation(reservation);
		openReservation(reservation);
		PFReservationSummary.clickSVCBadge();
		Selenide.sleep(3000);

		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("COS NEW"), "status should be NEW");
		Assert.assertTrue($(LocatorsSummaryModal.ALL_SVC_REQUESTS).getText().contains("COSI NEW"), "status should be NEW");
	}

	private ReservationSummary checkoutReservation(ReservationSummary reservation){
		ReservationCheckoutRequest reservationCheckoutRequest = new ReservationCheckoutRequest(false);
		reservationCheckoutRequest.setTerminalId(new ClientPaymentGatewayGet().getTerminalId(masterObject.getProperty(), true));
		reservationCheckoutRequest.setAllowBalance(true);
		reservationCheckoutRequest.setUseDefaultFolioPaymentSettings(false);

		AccountDetail accountDetail = new ClientAccountGet().getAccountDetails(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId(), reservation
				.getAccountId());

		Set<String> paymentSettingIds = new HashSet<>();
		// Set payment setting
		paymentSettingIds.add(accountDetail.getPaymentSettings().get(0).getPaymentSettingId());

		reservationCheckoutRequest.setPaymentSettingIds(paymentSettingIds);

		return new ClientReservationCheckout()
				.checkout(masterObject.getProperty(),reservation, reservationCheckoutRequest);
	}

	private Room createRoom() {
		return new CreateRoom().create(masterObject.getRoomTypes().get(0), masterObject.getBuilding(), masterObject.getProperty());
	}

	private void enablePetSettings(){
		//allow pet set to true and exempt service animal
		PetSettings petSettings = new ClientAnimalGet().getPetPerReservationInfo(masterObject.getProperty());
		petSettings.setPetsAllowed(true);
		petSettings.setExemptServiceAnimal(true);
		new ClientAnimalUpdate().updatePetSetting(masterObject.getProperty(), petSettings);
	}

	private AnimalTypes createAndGetPet(String animalType) {
		AnimalTypes animalTypes = new BuilderAnimalTypes(animalType).build();
		return new ClientAnimalAdd().addAnimal(masterObject.getProperty(), animalTypes);
	}

	private Component addChargeForPet(String animalId, Component component) {
		//create animal charges i.e component bundles and add them to the animal created
		List<ComponentBundle> componentBundles = new ArrayList<>();
		ComponentBundle componentBundle = new ComponentBundle();
		componentBundle.setTypeId(animalId);
		List<Component> components = new ArrayList<>();
		components.add(component);
		componentBundle.setComponents(components);
		componentBundles.add(componentBundle);
		new ClientAnimalAdd().addChargeToAnimal(masterObject.getProperty(), componentBundles);
		return component;
	}

	private PetInfo getPetInfo(AnimalTypes animalType, String petName, PetRateSnapshot rateSnapForPet) {
		//add PetInfo in the reservation create request
		PetInfo expectedPetInfo = new PetInfo();
		List<Pet> pets = new ArrayList<>();
		Pet pet1 = new Pet();
		pet1.setAnimalType(animalType.getAnimal());
		pet1.setAnimalTypeId(animalType.getId());
		pet1.setCreatedDate(LocalDate.now());
		pet1.setName(petName);
		List<PetRateSnapshot> petRateSnapshotsForPets = new ArrayList<>();
		petRateSnapshotsForPets.add(rateSnapForPet);
		pet1.setPetRateSnapshots(petRateSnapshotsForPets);
		pets.add(pet1);
		expectedPetInfo.setPets(pets);
		expectedPetInfo.setComment("adding pets to reservation request");
		return expectedPetInfo;
	}

	private PetRateSnapshot getRate(FrequencyType frequencyType, int nNights, PetRateSnapshot.PetChargeOperation petChargeOperation,
									String petChargeId, String transactionItemId, BigDecimal amount) {
		PetRateSnapshot petRateSnapshot = new PetRateSnapshot();
		petRateSnapshot.setFrequencyType(frequencyType);
		petRateSnapshot.setnNights(nNights);
		petRateSnapshot.setPetChargeOperation(petChargeOperation);
		petRateSnapshot.setPetChargeId(petChargeId);
		petRateSnapshot.setTransactionItemId(transactionItemId);
		petRateSnapshot.setAmount(amount);
		return petRateSnapshot;
	}

	public void openReservation(ReservationSummary reservation) {
		Selenide.refresh();
		clickTabReservations();
		new SearchReservationAndOpen(reservation.getPrimaryGuestInfo().getLastName(), reservation.getPrimaryGuestInfo().getFirstName(),
				reservation.getConfirmationCode()).searchByName();
	}

	private void extendDepartureDate(LocalDate date)
	{
		String s = String.valueOf(date.getDayOfMonth());
		POCalendars poCalendars = new POCalendars(LocatorsSummaryModal.LABEL_DEPARTURE_DATE);
		poCalendars.setDate(date);
		sleep(500);
//		ElementsCollection $$ = $$(By.xpath(("//span[@class = 'ng-binding']")));
//		SelenideElement selenideElement = $$.stream().filter(x -> x.getText().contains(s)).findFirst().get();
//		click(selenideElement);
	}

	public void doValidDateRoll(Property property, boolean override) {
		LocalDate propertyDate = ClientHelper.call(() ->
			ClientFactory.getPropertyManagement().getPropertyDate(property.getTenantId(), property.getPropertyId()));
		DateRollStatus dateRollStatus;
		int seconds = 0;
		String terminalId = new ClientPaymentGatewayGet()
			.getTerminalId(property.getTenantId(), property.getPropertyId(),
				true);
		SummaryReport summaryReport = ClientHelper.callUntil(() -> ClientFactory.getReservationReporting()
				.getSummaryReport(property.getTenantId(), property.getPropertyId(), propertyDate), null, 1000,
			20000,
			null);
		for (Group group : summaryReport.getGroupsWithRemaining()) {
			new ClientGroupRelease().releaseRoomBlocks(property, "", group, propertyDate.toString());
		}
		List<ReservationSummary> remainingArrival = summaryReport.getRemainingArrival();

		remainingArrival.addAll(summaryReport.getRemainingDeparture());

		for (ReservationSummary reservationSummary : remainingArrival) {
			clearReservation(reservationSummary, property);
		}

		ClientHelper.callUntil(() -> ClientFactory.getPropertyManagement()
				.startDateRoll(property.getTenantId(), property.getPropertyId(), propertyDate, terminalId,
					override),
			null, 1000, 30000, null);

		do {
			dateRollStatus = ClientHelper.call(() -> ClientFactory.getPropertyManagement()
				.getDateRollStatus(property.getTenantId(), property.getPropertyId()));
			ThreadHelper.sleep(1000);
			seconds++;
		} while (!dateRollStatus.getStatus().equals(DateRollStatus.Status.COMPLETED) && seconds < 240);

		//Verify that the date roll succeeded
		Assert.assertTrue(dateRollStatus.getStatus().equals(DateRollStatus.Status.COMPLETED),
			String.format("Date roll did not complete successfully after 240 seconds\nExpected status: %s\nReceived status: %s",
				DateRollStatus.Status.COMPLETED.toString(), dateRollStatus.getStatus().toString()));
	}

	String ERROR_MESSAGE_PREFIX = "tenantId: %s -  propertyId: %s";

	public void clearReservation(ReservationSummary reservationSummary, Property property) {
		LocalDate propertyDate = ClientHelper.call(() ->
			ClientFactory.getPropertyManagement().getPropertyDate(property.getTenantId(), property.getPropertyId()));
		reservationSummary = new ClientReservationGet().getReservationById(property,reservationSummary);

		try {
			if (reservationSummary.getStatus().equalsIgnoreCase("RES")) {
				//Get Payment Method
				PaymentMethod cashPaymentMethod = new ClientPaymentMethodGet().getCashPaymentMethod(property);

				//Create Reservation Cancellation Request
				ReservationCancellationRequest reservationCancellationRequest =
					new BuilderReservationCancellationRequest(masterObject.getCancelationPolicy(), cashPaymentMethod,
						GatewayConstants.getTerminalId()).build();

				// Cancel the reservation
				new ClientReservationCancel()
					.cancelReservation(property, reservationSummary, reservationCancellationRequest);
			} else if (reservationSummary.getStatus().equalsIgnoreCase("INH"))
				if (propertyDate.isBefore(reservationSummary.getDepartureDate())) {
					//Check-out
					new AdjustReservation().earlyCheckOut(reservationSummary, property);
				} else {
					new AdjustReservation().checkout(reservationSummary, property);
				}

		}
		catch (ClientException | RGuestException e)
		{
			throw new PmsClientException(String.format("Failed to clear the reservation. " + ERROR_MESSAGE_PREFIX + " - confirmation Number %s.",
				property.getTenantId(), property.getPropertyId(), reservationSummary.getConfirmationCode()), e);
		}
	}

}