package com.agilysys.qa.gridIron.production.smoke;

import static com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage.INPUT_LASTNAME;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.waitForPageToLoad;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.sleep;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.agilysys.common.model.PaymentSetting;
import com.agilysys.insertanator.constants.GatewayConstants;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.platform.common.rguest.exception.RGuestException;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.account.model.PaymentMethod;
import com.agilysys.pms.account.model.TransactionItem;
import com.agilysys.pms.common.exceptions.reservation.ReservationErrorCode;
import com.agilysys.pms.profile.model.v1.GuestProfile;
import com.agilysys.pms.property.client.PropertyManagementClient;
import com.agilysys.pms.property.model.AllocationIdResult;
import com.agilysys.pms.property.model.CancellationPolicy;
import com.agilysys.pms.property.model.ReservationAllocationHold;
import com.agilysys.pms.property.model.RoomType;
import com.agilysys.pms.rates.model.RatePlanDetail;
import com.agilysys.pms.reservation.model.FullDayRateSnapshot;
import com.agilysys.pms.reservation.model.MultipleReservationCreateRequest;
import com.agilysys.pms.reservation.model.MultipleReservationRequestAsyncJobId;
import com.agilysys.pms.reservation.model.MultipleReservationResponse;
import com.agilysys.pms.reservation.model.RateSnapshot;
import com.agilysys.pms.reservation.model.ReservationByDateRangeOptionalParameters;
import com.agilysys.pms.reservation.model.ReservationCreateRequest;
import com.agilysys.pms.reservation.model.ReservationSummary;
import com.agilysys.pms.reservation.model.ReservationWriteOptionalParameters;
import com.agilysys.pms.servicerequest.model.HousekeepingStaffMemberPropertyAssignment;
import com.agilysys.pms.servicerequest.model.Role;
import com.agilysys.pms.servicerequest.model.StaffMember;
import com.agilysys.qa.auth.TokenHandler;
import com.agilysys.qa.client.account.item.ClientAccountingItemGet;
import com.agilysys.qa.client.area.room.ClientRoomCreate;
import com.agilysys.qa.client.area.room.allocation.ClientRoomAllocationCreate;
import com.agilysys.qa.client.area.room.allocation.ClientRoomAllocationGet;
import com.agilysys.qa.client.house.keeping.role.ClientHousekeepingRoleGet;
import com.agilysys.qa.client.house.keeping.staff.ClientStaffMemberCreate;
import com.agilysys.qa.client.house.keeping.staff.ClientStaffMemberUpdate;
import com.agilysys.qa.client.payment.method.ClientPaymentMethodGet;
import com.agilysys.qa.client.profile.guest.ClientGuestProfileGet;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.client.rate.plan.ClientRatePlanGet;
import com.agilysys.qa.client.reservation.ClientReservationCheckin;
import com.agilysys.qa.client.reservation.ClientReservationGet;
import com.agilysys.qa.clients.ClientFactory;
import com.agilysys.qa.clients.PmsClientException;
import com.agilysys.qa.constants.EndPoints;
import com.agilysys.qa.data.builder.allocation.reservation.BuilderReservationAllocationHold;
import com.agilysys.qa.data.builder.area.room.BuilderRoom;
import com.agilysys.qa.data.builder.common.BuilderPaymentMethodSetting;
import com.agilysys.qa.data.builder.common.BuilderPaymentSetting;
import com.agilysys.qa.data.builder.house.keeping.staff.BuilderHousekeepingStaffMemberPropertyAssignment;
import com.agilysys.qa.data.builder.house.keeping.staff.BuilderStaffMember;
import com.agilysys.qa.data.builder.reservation.BuilderMultipleReservationCreateRequest;
import com.agilysys.qa.data.builder.reservation.BuilderReservationCreateRequest;
import com.agilysys.qa.gridIron.builders.booking.BuilderBookReservation;
import com.agilysys.qa.gridIron.builders.housekeeping.HKStaffOnDutyDrag;
import com.agilysys.qa.gridIron.builders.housekeeping.MTStaffOnDutyDrag;
import com.agilysys.qa.gridIron.builders.login.LoginApplication;
import com.agilysys.qa.gridIron.builders.reservation.ModifyStayReservation;
import com.agilysys.qa.gridIron.builders.reservation.ResFolios;
import com.agilysys.qa.gridIron.constants.locators.LocatorsReport;
import com.agilysys.qa.gridIron.constants.locators.home.ProfileTile;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTMainSection;
import com.agilysys.qa.gridIron.constants.locators.maintenance.LocatorsMTStaffSection;
import com.agilysys.qa.gridIron.constants.locators.profiles.guest.LocatorsGuestProfilePage;
import com.agilysys.qa.gridIron.constants.locators.reservation.LocatorsModifyStay;
import com.agilysys.qa.gridIron.constants.locators.reservation.rooms.LocatorsCurrentlySelectedRoom;
import com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails;
import com.agilysys.qa.gridIron.constants.pagefactory.booking.PFBookReservationModal;
import com.agilysys.qa.gridIron.constants.pagefactory.home.PFHeaderDropDowns;
import com.agilysys.qa.gridIron.constants.pagefactory.reservation.PFRecommededUpgradesAndOtherMatchingRoom;
import com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFFoliosDetails;
import com.agilysys.qa.gridIron.data.models.RecurringCharge;
import com.agilysys.qa.gridIron.pageobject.home.searchtile.POSearchInMainPage;
import com.agilysys.qa.gridIron.pageobject.profiles.guest.POGuestProfilePage;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.validations.VerifyReservation;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;
import com.agilysys.qa.helpers.ClientHelper;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import com.agilysys.qa.helpers.RandomHelper;

public class TestProductionSmokeTest extends StayUIWrappers {
    static MasterObject masterObject;
    static Tenant tenant;
    Property property;

    String tenantId;
    String propertyId;
    private LocalDate propertyDate = null;
    private ReservationCreateRequest reservationCreateRequest = null;
    private List<ReservationSummary> reservations = new ArrayList<>();
    private String terminalId;
    private AllocationIdResult allocationIdResult;
    private ModifyStayReservation modifyStayReservation;
    private ClientReservationCheckin clientReservationCheckin;
    private ClientPropertyDateGet clientPropertyDateGet;

    @BeforeClass
    public void oneTimeSetup() {

        modifyStayReservation = new ModifyStayReservation();
        clientReservationCheckin = new ClientReservationCheckin();
        clientPropertyDateGet = new ClientPropertyDateGet();

        tenantId = Endpoints.getTenantId();
        propertyId = Endpoints.getPropertyId();

        property = new Property();
        property.setTenantId(tenantId);
        property.setPropertyId(propertyId);

        masterObject = new MasterObject();

        masterObject.setProperty(property);
        new LoginApplication().Login(masterObject);

        TokenHandler.getInstance()
              .cachedLogin(Endpoints.getUserName(), Endpoints.getPassword(), masterObject.getProperty().getTenantId());

        propertyDate = clientPropertyDateGet.getPropertyDate(property);

        RoomType roomType = new PropertyManagementClient(EndPoints.getPropertyServiceURI()).getProxy()
              .getRoomTypeById(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId(),
                    "f3a2809f-5735-4b12-9d3c-700720c1d435");
        List<RoomType> roomTypes = new ArrayList<>();

        roomTypes.add(roomType);
        masterObject.setRoomTypes(roomTypes);

        RatePlanDetail ratePlan = new ClientRatePlanGet()
              .getRatePlan(masterObject.getProperty(), "3e25e679-c137-4c47-b400-ed65ac284d0e", null);

        masterObject.setRatePlans(Collections.singletonList(ratePlan));

        TransactionItem transactionItem = new ClientAccountingItemGet()
              .getTransactionItem(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId(),
                    "6eb1df77-313c-43af-be32-2177cb75e738");

        masterObject.setItems(Collections.singletonList(transactionItem));

        CancellationPolicy cancellationPolicy = new CancellationPolicy();
        cancellationPolicy.setId("f42e5939-f415-44bf-b3d1-71981e00d6f9");
        cancellationPolicy.setCode("zzb2ewov");
        cancellationPolicy.setItemId("19098eb9-5715-416e-8441-d88142ccfa67");
        cancellationPolicy.setDescription("a3t3bw8lwy");
        masterObject.setCancelationPolicy(cancellationPolicy);

        GuestProfile guestProfile = new ClientGuestProfileGet()
              .getGuestProfileById(masterObject.getProperty().getTenantId(), masterObject.getProperty().getPropertyId(),
                    "5d40170a5f15030001026bda", null);
        masterObject.setGuests(Collections.singletonList(guestProfile));

        // create reservation

        for (int i = 0; i < 5; i++) {

            buildReservationCreateRequest();
            reservations.add(createReservation(reservationCreateRequest, allocationIdResult).get(0));

        }
        terminalId = GatewayConstants.getTerminalId();

    }

    @BeforeMethod
    public void beforeSetup() {
        propertyDate = clientPropertyDateGet.getPropertyDate(property);
        reservationCreateRequest = new ReservationCreateRequest();
    }

    @Test
    public void testAddReservationAndAddRecurringCharge() {
        reservationCreateRequest = buildReservationCreateRequest();
        reservationCreateRequest.setNumberOfAdults(1);
        reservationCreateRequest.setNumberOfChildren(1);

        // add recurring charge
        List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
        RecurringCharge recurringCharge =
              new RecurringCharge(masterObject.getItems().get(0).getName(), BigDecimal.valueOf(20), 1);
        recurringCharge.setFirstNightOnlyFrequency();
        recurringCharges.add(recurringCharge);

        BuilderBookReservation builderBookReservation =
              new BuilderBookReservation(reservationCreateRequest).addPaymentMethod("Cash", null, null)
                    .setGuestSearchString("test").setDepositDetails("Cash", "50")
                    .addRecurringCharge(recurringCharges, propertyDate, "Posting charges").build();

        String confirmationNumber = PFBookReservationModal.getReservationConfirmationNumber();

        PFBookReservationModal.closeBookReservationModal();

        POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(confirmationNumber,
              masterObject.getGuests().get(0).getPersonalDetails().getLastName());

        VerifyReservation
              .verifyEstimatedChargesLineItemQuantity(propertyDate, masterObject.getItems().get(0).getName(), 1);
        VerifyReservation.verifyEstimatedChargesLineItemAmount(propertyDate, masterObject.getItems().get(0).getName(),
              BigDecimal.valueOf(20));
        VerifyReservation.verifyEstimatedChargesLineItemNotPresent(propertyDate.plusDays(1),
              masterObject.getItems().get(0).getName());

    }

    @Test(priority = -1)
    public void testCheckinReservation() {
        checkInReservation();
    }

    @Test(priority = -15)
    public void testUndoCheckInReservation() {
        checkInReservation();
        modifyStayReservation.UndoCheckinReservation();
        $(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("RES");

    }

    @Test
    public void testCheckoutReservation() {

        ReservationSummary reservationSummary = prepareCheckoutData();
        POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(reservationSummary.getConfirmationCode(),
              reservationSummary.getPrimaryGuestInfo().getLastName());

        $(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("DPT");
    }

    @Test
    public void testUndoCheckout() {
        ReservationSummary reservationSummary = prepareCheckoutData();
        POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(reservationSummary.getConfirmationCode(),
              reservationSummary.getPrimaryGuestInfo().getLastName());

        $(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("DPT");

        modifyStayReservation.UndoCheckoutReservation();
        $(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("INH");

    }

    @Test(priority = -1)
    public void testCancelReservation() {
        cancelReservation();
    }

    @Test(priority = -10)
    public void testUndoCancelReservation() {
        cancelReservation();
        modifyStayReservation.UndoCancelReservation();
    }

    @Test
    public void testAddCharge() {

        buildReservationCreateRequest();
        ReservationSummary reservationSummary = reservations.get(3);
        POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(reservationSummary.getConfirmationCode(),
              reservationSummary.getPrimaryGuestInfo().getLastName());

        new ResFolios(masterObject.getItems().get(0).getName()).flowAddChargeForAnItem1();

        PFFoliosDetails.clickTabFolio1();
        Assert.assertTrue(
              $(LocatorsFoliosDetails.LINE_ITEM_1).getText().contains(masterObject.getItems().get(0).getName()));

    }

    @Test
    public void testGeneralAvailabilityReport() {
        PFHeaderDropDowns.navigateToGeneralAvailability();
        click(LocatorsReport.GENERAL_AVAILABILITY_APPLY);
        waitForPageToLoad();
        PageFactoryBase
              .waitForElementToLoad(LocatorsReport.GENERAL_AVAILABILITY_GENERATED_DATES, Configuration.timeout);

        SelenideElement element = $(LocatorsReport.GENERAL_AVAILABILITY_GENERATED_DATES);
        element.getText().contains(propertyDate.getMonthOfYear() + "/" + propertyDate.getDayOfMonth());

    }

    @Test
    public void testGroupRoomControlReport() {
        PFHeaderDropDowns.navigateToGeneralAvailability();
        click(LocatorsReport.GROUP_ROOM_CONTROL_APPLY);
        waitForPageToLoad();
        SelenideElement groupRoomControlDates = $(LocatorsReport.GROUP_ROOM_CONTROL_GENERATED_DATES);
        groupRoomControlDates.shouldBe(Condition.visible, Duration.ofMillis(Configuration.timeout));
        groupRoomControlDates.getText().contains(propertyDate.getMonthOfYear() + "/" + propertyDate.getDayOfMonth());

    }

    @Test
    public void testArrivalreport() {
        PFHeaderDropDowns.navigateToAllReports();
        click(LocatorsReport.ARRIVAL_REPORT);
        click(LocatorsReport.ARRIVAL_REPORT_GENERATE);
        $(LocatorsReport.ARRIVAL_REPORT_PRINT).is(Condition.visible);

    }

    @Test
    public void testNightAuditReport() {
        PFHeaderDropDowns.navigateToNightAudit();
        click(LocatorsReport.NIGHT_AUDIT_TAB);
        click(LocatorsReport.LEDGER_SUMMARY_REPORT);
        click(LocatorsReport.LEDGER_SUMMARY_REPORT_GENERATE);
        $(LocatorsReport.LEDGER_SUMMARY_REPORT_PRINT).is(Condition.visible);

    }

    @Test
    public void testzDateRoll() {
        makePropertyReadyForDateRoll(masterObject, true);

        PFHeaderDropDowns.navigateToNightAudit();
        click(LocatorsReport.DATE_ROLL_TAB);

        click(LocatorsReport.ROLL_THE_DATE);
        click(LocatorsReport.DATEROLL_CONFIRM);

        if ($(LocatorsReport.DATEROLL_YES_VALUE).is(Condition.visible)) {
            click(LocatorsReport.DATEROLL_YES_VALUE);
            click(LocatorsReport.ROLL_THE_DATE_FINAL);
        }
        propertyDate = clientPropertyDateGet.getPropertyDate(property);
        waitForPageToLoad();
        $(LocatorsReport.PROPERTY_DATE).getText().contains(String.valueOf(propertyDate.plusDays(1).getDayOfMonth()));
    }

    @Test
    public void testVerifyHouseKeepingStaff() {

        List<Role> roles = new ClientHousekeepingRoleGet().getRoles(masterObject.getProperty());
        Role role = roles.stream().filter(x -> "GRA".equals(x.getId())).findAny().get();

        HousekeepingStaffMemberPropertyAssignment staffMemberPropertyAssignment =
              new BuilderHousekeepingStaffMemberPropertyAssignment(masterObject.getProperty(), role, 1, true, false,
                    false).build();
        StaffMember staffMember =
              new BuilderStaffMember(masterObject.getProperty(), RandomHelper.getRandomAlphaString(5),
                    RandomHelper.getRandomAlphaString(5), staffMemberPropertyAssignment).build();
        // create staff member
        staffMember = new ClientStaffMemberCreate().createStaffMember(masterObject.getProperty(), staffMember);

        // put staff on duty
        StaffMember onDutyStaffMember =
              new ClientStaffMemberUpdate().putStaffMemberOnDuty(masterObject.getProperty(), staffMember);
        Assert.assertTrue(onDutyStaffMember.getStaffMemberPropertyAssignments().iterator().next().isOnDuty(),
              "Expected staff to be on duty");

    }

    @Test
    public void testCreateGuestprofile() {
        // create guest
        GuestProfile guestProfile = POGuestProfilePage.createGuestProfile();
        String lastName = guestProfile.getPersonalDetails().getLastName();
        String firstName = guestProfile.getPersonalDetails().getFirstName();

        Selenide.sleep(3000);
        POSearchInMainPage.searchForGuestProfile(lastName);
        click($$(ProfileTile.LIST_GUESTS).findBy(Condition.text(lastName)));

        $(INPUT_LASTNAME).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
        Assert.assertEquals(firstName, $(LocatorsGuestProfilePage.INPUT_FIRSTNAME).getValue());
        Assert.assertEquals(lastName, $(LocatorsGuestProfilePage.INPUT_LASTNAME).getValue());
    }

    public RateSnapshot builderRateSnapshot(RoomType roomType, RatePlanDetail availableRatePlan, int adultsIncluded,
          int childrenIncluded, LocalDate date) {
        RateSnapshot rateSnapshot = new FullDayRateSnapshot();
        rateSnapshot.setOverbooked(Boolean.FALSE);
        rateSnapshot.setOverridden(Boolean.FALSE);
        rateSnapshot.setCompInfo(null);
        rateSnapshot.setComponentRateSnapshots(null);

        rateSnapshot.setCancellationPolicyId(availableRatePlan.getCancellationPolicyId());
        rateSnapshot.setRatePlanId(availableRatePlan.getId());
        rateSnapshot.setRoomTypeId(roomType.getId());
        rateSnapshot.setTransactionItemId(availableRatePlan.getTransactionItemId());
        rateSnapshot.setAdultsIncluded(adultsIncluded);
        rateSnapshot.setChildrenIncluded(childrenIncluded);
        rateSnapshot.setPreOccupancyRate(new BigDecimal(100));
        rateSnapshot.setExtraAdultCharge(new BigDecimal(30));
        rateSnapshot.setExtraChildrenCharge(new BigDecimal(50));
        rateSnapshot.setRatePlanName(availableRatePlan.getName());
        rateSnapshot.setRoomTypeCode(roomType.getTypeCode());
        rateSnapshot.setDate(date);
        return rateSnapshot;
    }

    public ReservationCreateRequest buildReservationCreateRequest() {
        LocalDate arrivalDate = propertyDate;
        LocalDate departureDate = propertyDate.plusDays(2);
        RoomType roomType = masterObject.getRoomTypes().get(0);
        RatePlanDetail ratePlan = new ClientRatePlanGet()
              .getRatePlan(masterObject.getProperty(), masterObject.getRatePlans().get(0).getId(), null);
        boolean overbook = true;

        // rate snapshots
        Set<RateSnapshot> rateSnapshots = new HashSet<>();

        LocalDate date = arrivalDate;
        do {
            rateSnapshots.add(builderRateSnapshot(roomType, ratePlan, 2, 0, date));
            date = date.plusDays(1);
        } while (date.isBefore(departureDate));
        // deposit fee
        BigDecimal depositFee = new BigDecimal(RandomHelper.getRandomDouble(20, 100));

        /********************************/
        // payment settings
        List<PaymentSetting> paymentSettings = new ArrayList<>();
        PaymentMethod paymentMethod = new ClientPaymentMethodGet().getCashPaymentMethod(masterObject.getProperty());
        paymentSettings
              .add(new BuilderPaymentSetting(paymentMethod, new BuilderPaymentMethodSetting(true).build()).build());
        // create allocation
        ReservationAllocationHold reservationAllocationHold =
              new BuilderReservationAllocationHold(arrivalDate, departureDate, roomType).build();
        allocationIdResult = null;
        try {
            allocationIdResult = new ClientRoomAllocationCreate()
                  .createReservationAllocationHold(masterObject.getProperty(), !overbook, reservationAllocationHold);
        } catch (PmsClientException e) {
            System.out.println("** AllocationConflictException **");
            com.agilysys.pms.property.model.Room expectedRoom =
                  new BuilderRoom(masterObject.getProperty(), "58d54b11-af59-48a1-bb55-b9f66700e4e5", roomType,
                        RandomHelper.getRandomNumericString(6), propertyDate).build();

            com.agilysys.pms.property.model.Room actualRoom =
                  new ClientRoomCreate().createRoom(masterObject.getProperty(), expectedRoom);

            allocationIdResult = new ClientRoomAllocationCreate()
                  .createReservationAllocationHold(masterObject.getProperty(), !overbook, reservationAllocationHold);
        }

        // Create Reservation
        reservationCreateRequest = new BuilderReservationCreateRequest(masterObject.getProperty(), allocationIdResult,
              masterObject.getGuests().get(0), 2, 0, paymentSettings, rateSnapshots)
              .setDepositPolicyInfo(ratePlan.getDepositPolicyId(), depositFee).build();
        return reservationCreateRequest;

    }

    private List<ReservationSummary> createReservation(ReservationCreateRequest reservationCreateRequest,
          AllocationIdResult allocationIdResult) {
        ReservationWriteOptionalParameters reservationWriteOptionalParameters =
              new ReservationWriteOptionalParameters();

        MultipleReservationCreateRequest multipleReservationCreateRequest =
              new BuilderMultipleReservationCreateRequest(property,
                    Collections.singleton(allocationIdResult.getAllocationId()),
                    masterObject.getGuests().get(0).getId(), 1, 1, reservationCreateRequest.getPaymentSettings(),
                    reservationCreateRequest.getRateSnapshots()).build();
        MultipleReservationRequestAsyncJobId response = null;
        try {
            response = ClientHelper.callUntil(() -> ClientFactory.getReservationService()
                        .createMultipleReservations(tenantId, propertyId, multipleReservationCreateRequest,
                              reservationWriteOptionalParameters), null, 500, 15000, "Failed to create reservation",
                  ReservationErrorCode.TRACKING_INFO_REQUIRED, ReservationErrorCode.COMP_INFO_REQUIRED);
        } catch (RGuestException e) {
            throw new PmsClientException("Failed to create multiple reservation", e);
        }

        DateTime now = DateTime.now();
        MultipleReservationResponse multipleReservationResponse;
        do {
            multipleReservationResponse = new ClientReservationGet()
                  .getMultipleReservationSummary(property.getTenantId(), property.getPropertyId(),
                        response.getAsyncJobId());
            ThreadHelper.sleep(1000);
        } while (multipleReservationResponse.getReservationStatus().equalsIgnoreCase("IN_PROGRESS") &&
              DateTime.now().isBefore(now.plusSeconds(30)));

        return multipleReservationResponse.getReservationSummaries();

    }

    private ReservationSummary prepareCheckoutData() {
        ReservationByDateRangeOptionalParameters reservationByDateRangeOptionalParameters =
              new ReservationByDateRangeOptionalParameters();
        reservationByDateRangeOptionalParameters.setStatuses("INH");
        List<ReservationSummary> reservationsForDateRange = new ClientReservationGet()
              .findReservationsForDateRange(property, propertyDate, propertyDate.plusDays(2),
                    reservationByDateRangeOptionalParameters);

        ReservationSummary reservationSummary = null;
        Optional<ReservationSummary> first =
              reservationsForDateRange.stream().filter(x -> x.getDepartureDate().equals(propertyDate)).findFirst();
        boolean present = first.isPresent();
        if (present) {

            reservationSummary = reservationsForDateRange.get(0);
            POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(reservationSummary.getConfirmationCode(),
                  reservationsForDateRange.get(0).getPrimaryGuestInfo().getLastName());
            modifyStayReservation.EarlyCheckoutWithBalanceReservation();

        } else {

            reservationSummary = reservations.get(2);

            POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(reservationSummary.getConfirmationCode(),
                  reservationSummary.getPrimaryGuestInfo().getLastName());
            PFRecommededUpgradesAndOtherMatchingRoom.clickOtherMatchingRow(1);
            clientReservationCheckin.checkin(property, reservationSummary);
            Selenide.refresh();
            modifyStayReservation.EarlyCheckoutWithBalanceReservation();

        }
        POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(reservationSummary.getConfirmationCode(),
              reservationSummary.getPrimaryGuestInfo().getLastName());
        $(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("DPT");

        return reservationSummary;

    }

    private void checkInReservation() {
        ReservationByDateRangeOptionalParameters reservationByDateRangeOptionalParameters =
              new ReservationByDateRangeOptionalParameters();
        reservationByDateRangeOptionalParameters.setStatuses("RES");
        List<ReservationSummary> reservationsForDateRange = new ClientReservationGet()
              .findReservationsForDateRange(property, propertyDate, propertyDate.plusDays(2),
                    reservationByDateRangeOptionalParameters);

        ReservationSummary reservationSummary=null;

        if (reservationsForDateRange.size() > 0) {
            reservationSummary = reservationsForDateRange.get(0);
            POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(
                  reservationSummary.getConfirmationCode(),
                  reservationSummary.getPrimaryGuestInfo().getLastName());

        } else {
            reservationSummary = reservations.get(0);
            POSearchInMainPage
                  .goToReservationByConfirmationNumberAndGuestName(reservationSummary.getConfirmationCode(),
                        reservationSummary.getPrimaryGuestInfo().getLastName());

        }
        String roomId = new ClientRoomAllocationGet().getAllocatedRoomsForReservation(property,
              new ClientReservationGet().getReservationById(property, reservationSummary).getAllocationId())
              .getRoomDetailsByDate().get(reservationSummary.getArrivalDate()).getRoomId();
        if (roomId == null) {
            PFRecommededUpgradesAndOtherMatchingRoom.clickOtherMatchingRow(1);
        }

        new ModifyStayReservation().CheckinCheckReservation();
        VerifyReservation.verifyCheckIn();
        $(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("INH");

    }

    private void cancelReservation() {
        ReservationByDateRangeOptionalParameters reservationByDateRangeOptionalParameters =
              new ReservationByDateRangeOptionalParameters();
        reservationByDateRangeOptionalParameters.setStatuses("RES");
        List<ReservationSummary> reservationsForDateRange = new ClientReservationGet()
              .findReservationsForDateRange(property, propertyDate, propertyDate,
                    reservationByDateRangeOptionalParameters);

        if (reservationsForDateRange.size() > 0) {
            POSearchInMainPage.goToReservationByConfirmationNumberAndGuestName(
                  reservationsForDateRange.get(0).getConfirmationCode(),
                  reservationsForDateRange.get(0).getPrimaryGuestInfo().getLastName());
        } else {
            POSearchInMainPage
                  .goToReservationByConfirmationNumberAndGuestName(reservations.get(1).getConfirmationCode(),
                        reservations.get(1).getPrimaryGuestInfo().getLastName());

        }

        click(LocatorsModifyStay.BUTTON_CANCEL);
        sleep(3000);
        if ($(LocatorsModifyStay.CANCEL_USERNAME).isDisplayed()) {
            PageFactoryBase.clearAndType(LocatorsModifyStay.CANCEL_USERNAME, Endpoints.getUserName());
            PageFactoryBase.clearAndType(LocatorsModifyStay.CANCEL_PASSWORD, Endpoints.getPassword());
            click(LocatorsModifyStay.PAY_CANCEL_CONFIRM);
        } else {
            click(LocatorsModifyStay.BUTTON_CANCEL_OK);
        }
        waitForPageToLoad();
        $(LocatorsModifyStay.BUTTON_UNDO_CANCEL).is(Condition.visible);
        $(LocatorsModifyStay.LABEL_RESERVATION_STATUS).getText().contains("CXL");

    }

}

