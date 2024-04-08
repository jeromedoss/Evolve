package com.agilysys.qa.gridIron.service.rate;

import com.agilysys.pms.property.model.RoomType;
import com.agilysys.qa.client.area.room.type.ClientRoomTypeGet;
import com.agilysys.qa.client.area.room.type.ClientRoomTypeUpdate;
import com.agilysys.qa.gridIron.constants.locators.settings.LocatorsSettingsPage;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.rates.model.RatePlanDetail;
import com.agilysys.pms.rates.model.RatePlanStrategyDetail;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.rates.RatesPlan;
import com.agilysys.qa.gridIron.utils.ClientPropertyDate;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.MainDriver;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import com.agilysys.qa.gridIron.validations.ValidateRatePlans;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_DASHBOARD;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_ROOM_AVAILABILITY;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_SETTINGS;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.TOTAL_ROOM_AVAILABILITY_COUNT;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_ALLSETTINGS;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickLinkCreateReservation;
import static com.agilysys.qa.gridIron.constants.pagefactory.home.reservationtile.PFReservationTile.clickTabReservations;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/*
 * *Author - Harish Baskaran - Aug/2018
 */

public class TestCreateRatePlans extends StayUIWrappers {

	static MasterObject masterObject;
	static Tenant tenant;

	static String RateName = RandomHelper.getRandomAlphaString(5);
	static String RateCode = RandomHelper.getRandomAlphaString(5);

	static String PkgRateName = RandomHelper.getRandomAlphaString(5);
	static String PkgRateCode = RandomHelper.getRandomAlphaString(5);

	String tenantId = null;
	String propertyId = null;
	Property property;

	@BeforeClass
	public void beforeClass() {

		tenant = new MainDriver().getTenant();

		String propertycode = RandomHelper.getRandomAlphaString(5);

		masterObject = new CreatePopulatedProperty()
				.createProperty("StayAutomation " + tenant.getTenantCode(), tenant.getTenantCode(), propertycode,
						propertycode)
				.createCancelationPolicy("CXL", "cancel description").createRoomTypes(2).createRooms(2).createItems(2)
				.create();

		tenantId = masterObject.getProperty().getTenantId();
		propertyId = masterObject.getProperty().getPropertyId();

		property = masterObject.getProperty();

		new LoginApplication().Login(masterObject);
	}

	@AfterClass
	public void afterClass() {
		//Selenide.closeWebDriver();
	}

	@BeforeMethod
	public void beforeMethod() {

		open(Endpoints.getBasePMSUrl() + "#/search/reservations?tenantId=" + tenantId + "&propertyId=" + propertyId);

		clickTabReservations();
		clickLinkCreateReservation();
	}

	@Test
	@TestCase(id = "RST-4454")
	public void testRatePlanCreate() {

		RatePlanStrategyDetail ratePlanStrategyDetail = new RatePlanStrategyDetail();
		ratePlanStrategyDetail.setBasePrice(new BigDecimal(100));

		List<RatePlanStrategyDetail> ratePlanStrategies = new ArrayList<RatePlanStrategyDetail>();
		ratePlanStrategies.add(ratePlanStrategyDetail);

		RatePlanDetail ratePlanDetail = new RatePlanDetail();

		ratePlanDetail.setName(RateName);
		ratePlanDetail.setCode(RateCode);
		ratePlanDetail.setAdultsIncluded(2);
		ratePlanDetail.setChildrenIncluded(0);
		ratePlanDetail.setBookingStartDate(new ClientPropertyDate().getPropertyDate(tenantId,propertyId));
		ratePlanDetail.setCancellationPolicyId(masterObject.getCancelationPolicy().getCode() + " - "
				+ masterObject.getCancelationPolicy().getDescription());
		ratePlanDetail.setRatePlanStrategies(ratePlanStrategies);

		new RatesPlan(ratePlanDetail).CreateRatePlan();

		new ValidateRatePlans().VerifyRequiredFields().Validate(ratePlanDetail);

	}

	@Test
	@TestCase(id = "RST-1295")
	public void testCreatePackageRatePlan() {

		RatePlanStrategyDetail ratePlanStrategyDetail = new RatePlanStrategyDetail();
		ratePlanStrategyDetail.setBasePrice(new BigDecimal(100));

		List<RatePlanStrategyDetail> ratePlanStrategies = new ArrayList<RatePlanStrategyDetail>();
		ratePlanStrategies.add(ratePlanStrategyDetail);

		RatePlanDetail ratePlanDetail = new RatePlanDetail();

		ratePlanDetail.setName(PkgRateName);
		ratePlanDetail.setCode(PkgRateCode);
		ratePlanDetail.setAdultsIncluded(2);
		ratePlanDetail.setChildrenIncluded(0);
		ratePlanDetail.setBookingStartDate(new ClientPropertyDate().getPropertyDate(tenantId,propertyId));
		ratePlanDetail.setCancellationPolicyId(masterObject.getCancelationPolicy().getCode() + " - "
				+ masterObject.getCancelationPolicy().getDescription());
		ratePlanDetail.setRatePlanStrategies(ratePlanStrategies);
		ratePlanDetail.setTransactionItemId(masterObject.getItems().get(0).getName());

		new RatesPlan(ratePlanDetail).CreatePackageRatePlan();

		new ValidateRatePlans().VerifyRequiredFields().Validate(ratePlanDetail);
		new ValidateRatePlans().VerifyComponents().ValidateComponents(ratePlanDetail, "1",
				String.valueOf(masterObject.getItems().get(0).getDefaultPrice()));

	}

	@Test
	public void testVerifyRoomTypeAvailabilityCount() {
		navigateToAllSettingsRoomTypes();
		ThreadHelper.sleep(3000);
		RoomType rohRoomType = new ClientRoomTypeGet().findRoomTypes(tenantId, propertyId).stream().filter(x -> x.getTypeCode().equals("ROH")).findAny().get();
		rohRoomType.setAssociatedRoomTypeIds(Collections.singletonList(masterObject.getRoomTypes().get(0).getId()));
		new ClientRoomTypeUpdate().updateRoomType(property, rohRoomType);
		Selenide.refresh();
		navigateToRoomAvailability();
		ThreadHelper.sleep(3000);
		Assert.assertTrue($(TOTAL_ROOM_AVAILABILITY_COUNT).getText().contains("28"), "count should be equal");
	}

	public static void navigateToAllSettingsRoomTypes() {
		click(NAV_SETTINGS);
		click(NAV_ALLSETTINGS);
		click(LocatorsSettingsPage.LINK_ROOM);
	}

	private void navigateToRoomAvailability(){
		click(NAV_DASHBOARD);
		click(NAV_ROOM_AVAILABILITY);
	}
}
