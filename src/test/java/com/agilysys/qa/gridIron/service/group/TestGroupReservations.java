package com.agilysys.qa.gridIron.service.group;

import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.profile.model.Group;
import com.agilysys.qa.client.group.ClientGroupGet;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.gridIron.builders.groups.GroupReservationCreate;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFSearchTile;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.ValidateGroups;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.joda.time.LocalDate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.agilysys.qa.constants.EndPoints;

import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2019
 */

public class TestGroupReservations extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	String tenantId = null;
	String propertyId = null;

	LocalDate propertyDate = null;

	String lastName;
	String firstName;
	String phoneNumber;

	Property property;
	Group group;
	MasterObject masterObject1;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty().createBuilding()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createGroups(1).create();

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();
		propertyDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());

		property = masterObject.getProperty();
		new LoginApplication().Login(masterObject);

	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {

		open(EndPoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);
		PFSearchTile.waitForSearchPageToLoad();

		masterObject1 = new CreatePopulatedProperty().useExistingProperty(masterObject.getProperty()).createGroups(1)
				.create();

		group = new ClientGroupGet().getById(masterObject1.getProperty(), masterObject1.getGroups().get(0));

		lastName = RandomHelper.getRandomAlphaString(6);
		firstName = RandomHelper.getRandomAlphaString(6);
		phoneNumber = RandomHelper.getRandomPhoneNumber();
	}

	@Test
	@TestCase(ids = {"RST-3887"})
	public void testCreateReservationWithAutoCreate() {

		new GroupReservationCreate(lastName, firstName, phoneNumber).GroupCreateAutoReservation(
				masterObject1.getGroups().get(0).getGroupName(), masterObject1.getRoomTypes().get(0).getTypeCode());

		new ValidateGroups().activateGroupCode(masterObject1.getRoomTypes().get(0).getTypeCode())
				.activateGroupName(masterObject1.getRoomTypes().get(0).getName())
				.activateGroupReservationEndDate(propertyDate.plusDays(2).toString())
				.activateGroupReservationRate("300").activateGroupReservationStartDate(propertyDate.toString())
				.activateGroupReservationStatus("RES").activateGroupRoomsPickedUp("1").activateGroupRoomsRemaining("0")
				.verify(group).verifyBookingTile(group).verifyReservation()
				.verifyRoomBlocks(group, masterObject1.getRoomTypes().get(0).getTypeCode());

	}

	@Test
	@TestCase(ids = {"RST-163"})
	public void testCreateReservationWithCreateButton() {

		new GroupReservationCreate(lastName, firstName, phoneNumber).GroupCreateReservation(
				masterObject1.getGroups().get(0).getGroupName(), masterObject1.getRoomTypes().get(0).getTypeCode());

		new ValidateGroups().activateGroupCode(masterObject1.getRoomTypes().get(0).getTypeCode())
				.activateGroupName(masterObject1.getRoomTypes().get(0).getName())
				.activateGroupReservationEndDate(propertyDate.plusDays(2).toString())
				.activateGroupReservationRate("300").activateGroupReservationStartDate(propertyDate.toString())
				.activateGroupReservationStatus("RES").activateGroupRoomsPickedUp("1").activateGroupRoomsRemaining("0")
				.verify(group).verifyBookingTile(group).verifyReservation()
				.verifyRoomBlocks(group, masterObject1.getRoomTypes().get(0).getTypeCode());

	}

	@Test
	@TestCase(ids = {"RST-480"})
	public void testCreateReservationWithMakeButton() {

		new GroupReservationCreate(lastName, firstName, phoneNumber).GroupMakeReservation(
				masterObject1.getGroups().get(0).getGroupName(), masterObject1.getRoomTypes().get(0).getTypeCode());
		
		new ValidateGroups().activateGroupCode(masterObject1.getRoomTypes().get(0).getTypeCode())
		.activateGroupName(masterObject1.getRoomTypes().get(0).getName())
		.activateGroupReservationEndDate(propertyDate.plusDays(2).toString())
		.activateGroupReservationRate("300").activateGroupReservationStartDate(propertyDate.toString())
		.activateGroupReservationStatus("RES").activateGroupRoomsPickedUp("1").activateGroupRoomsRemaining("0")
		.verify(group).verifyBookingTile(group).verifyReservation()
		.verifyRoomBlocks(group, masterObject1.getRoomTypes().get(0).getTypeCode());

	}

}
