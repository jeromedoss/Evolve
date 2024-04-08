package com.agilysys.qa.gridIron.service.profile;

import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.insertanator.services.lookup.items.ServiceGetItem;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.profile.model.BookingStatus;
import com.agilysys.pms.profile.model.Group;
import com.agilysys.pms.profile.model.ProfileReference;
import com.agilysys.pms.profile.model.ProfileReferenceType;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.data.builder.group.BuilderGroup;
import com.agilysys.qa.gridIron.builders.groups.GroupCreate;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.utils.Endpoints;
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

import static com.agilysys.qa.gridIron.constants.pagefactory.home.accountstile.PFProfileTile.clickTabProfiles;
import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Oct/2018
 */

public class TestCreateGroups extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	Group group = null;

	TransactionItem nightlyRoomCharge;

	private LocalDate arrivalDate = null;
	private LocalDate departureDate = null;

	String tenantId = null;
	String propertyId = null;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createRooms(5).createRoomTypes(2).createCancelationPolicy("CXL", "cancel description").createGuests(1)
				.create();

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();

		arrivalDate = new ClientPropertyDateGet().getPropertyDate(masterObject.getProperty());
		nightlyRoomCharge = new ServiceGetItem().lookup("Nightly Room Charge", masterObject.getProperty());

		departureDate = arrivalDate.plusDays(1);

		new LoginApplication().Login(masterObject);

	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {

		open(Endpoints.getBasePMSUrl() + "#/search/groups?tenantId=" + tenantId + "&propertyId=" + propertyId);

		clickTabProfiles();

	}

	@TestCase(ids={"RST-390","RST-153"})
	@Test
	public void testCreateDefiniteGroup() {

		group = new BuilderGroup(masterObject.getProperty(), arrivalDate, departureDate,
				masterObject.getCancelationPolicy(), masterObject.getUsers().get(0), nightlyRoomCharge,
				BookingStatus.DEFINITE,
				new ProfileReference(masterObject.getGuests().get(0).getId(), ProfileReferenceType.GUEST))
						.build();
		
		new GroupCreate(masterObject.getGuests().get(0).getPersonalDetails().getFirstName(), group.getGroupName(),
				group.getGroupCode()).createDefiniteGroup(masterObject.getRoomTypes().get(0).getTypeCode());

		new ValidateGroups().activateGroupName(group.getGroupName()).verify(group);

	}

	@TestCase(id="RST-391")
	@Test
	public void testCreateTentativeGroup() {

		group = new BuilderGroup(masterObject.getProperty(), arrivalDate, departureDate,
				masterObject.getCancelationPolicy(), masterObject.getUsers().get(0), nightlyRoomCharge,
				BookingStatus.TENTATIVE,
				new ProfileReference(masterObject.getGuests().get(0).getId(), ProfileReferenceType.GUEST))				
						.build();

		new GroupCreate(masterObject.getGuests().get(0).getPersonalDetails().getFirstName(), group.getGroupName(),
				group.getGroupCode()).createTentativeGroup(masterObject.getRoomTypes().get(0).getTypeCode());

		new ValidateGroups().activateGroupName(group.getGroupName()).verify(group);

	}

	@TestCase(id="RST-392")
	@Test
	public void testCreateProspectGroup() {

		group = new BuilderGroup(masterObject.getProperty(), arrivalDate, departureDate,
				masterObject.getCancelationPolicy(), masterObject.getUsers().get(0), nightlyRoomCharge,
				BookingStatus.PROSPECT,
				new ProfileReference(masterObject.getGuests().get(0).getId(), ProfileReferenceType.GUEST))
						.build();

		new GroupCreate(masterObject.getGuests().get(0).getPersonalDetails().getFirstName(), group.getGroupName(),
				group.getGroupCode()).createProspectGroup();

		new ValidateGroups().activateGroupName(group.getGroupName()).verify(group);

	}

	@TestCase(ids ={"RST-393","RST-3781"})
	@Test
	public void testCreateInquiryGroup() {

		group = new BuilderGroup(masterObject.getProperty(), arrivalDate, departureDate,
				masterObject.getCancelationPolicy(), masterObject.getUsers().get(0), nightlyRoomCharge,
				BookingStatus.INQUIRY,
				new ProfileReference(masterObject.getGuests().get(0).getId(), ProfileReferenceType.GUEST))
						.build();

		new GroupCreate(masterObject.getGuests().get(0).getPersonalDetails().getFirstName(), group.getGroupName(), group.getGroupCode()).createInquiryGroup();

		new ValidateGroups().activateGroupName(group.getGroupName()).verify(group);

	}

}