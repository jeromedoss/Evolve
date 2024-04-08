package com.agilysys.qa.gridIron.wrappers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.agilysys.insertanator.adjustments.reservations.AdjustReservation;
import com.agilysys.insertanator.constants.GatewayConstants;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.platform.common.exception.ClientException;
import com.agilysys.platform.common.rguest.exception.RGuestException;
import com.agilysys.platform.user.model.Property;
import com.agilysys.pms.account.model.PaymentMethod;
import com.agilysys.pms.profile.model.Group;
import com.agilysys.pms.property.model.DateRollStatus;
import com.agilysys.pms.property.model.RoomDTO;
import com.agilysys.pms.reservation.model.ReservationCancellationRequest;
import com.agilysys.pms.reservation.model.ReservationSummary;
import com.agilysys.pms.reservation.model.SummaryReport;
import com.agilysys.qa.client.area.room.ClientRoomGet;
import com.agilysys.qa.client.group.ClientGroupRelease;
import com.agilysys.qa.client.payment.gateway.ClientPaymentGatewayGet;
import com.agilysys.qa.client.payment.method.ClientPaymentMethodGet;
import com.agilysys.qa.client.reservation.ClientReservationAssignRoom;
import com.agilysys.qa.client.reservation.ClientReservationCancel;
import com.agilysys.qa.client.reservation.ClientReservationCheckin;
import com.agilysys.qa.client.reservation.ClientReservationGet;
import com.agilysys.qa.clients.ClientFactory;
import com.agilysys.qa.clients.PmsClientException;
import com.agilysys.qa.data.builder.reservation.BuilderReservationCancellationRequest;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.helpers.ClientHelper;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import com.codeborne.selenide.WebDriverRunner;

/*
 * *Author - Harish Baskaran - 2018
 */
public class GenericWrappers {

	Process hub = null;
	List<Process> nodes = new ArrayList<>();
	static int nodeCount = 1;

	public void loadObjects() {
		try {
			Endpoints.setEnvironmentUrl(Endpoints.getBasePMSUrl(), Endpoints.getBaseUrlPlatform());
			Endpoints.getJiraDetails();
			nodeCount = Endpoints.getNodesCount();
			System.out.println("---------------------------------------");
			System.out.println("ENV  - " + Endpoints.getEnvironment());
			System.out.println("PMS  - " + Endpoints.getBasePMSUrl());
			System.out.println("CORE - " + Endpoints.getBaseUrlPlatform());
			System.out.println("NODE - " + nodeCount);
			System.out.println("JIRA - " + Endpoints.jiraCycleId);
			System.out.println("---------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void quitBrowser() {
		WebDriverRunner.closeWebDriver();
	}

	public void invokeApp(boolean remote, String remoteUrl, boolean headless) {
		try {

//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("user-data-dir=C:\\Users\\"+System.getProperty("user.name").toLowerCase()+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
			cap.setCapability("pageLoadStrategy", "normal");
			//cap.setCapability(ChromeOptions.CAPABILITY, options);
			cap.setCapability("chrome.switches", Arrays.asList("--disable-notifications"));
			System.setProperty("webdriver.chrome.driver", new File("src\\main\\java\\com\\agilysys\\qa\\gridIron\\utils\\grid\\chromedriver.exe").getAbsolutePath());
			Configuration.browserCapabilities = cap;
			Configuration.browser = "chrome";
			Configuration.timeout = 180000;
			//Configuration.remoteConnectionTimeout = 180000;

		/*	if (remote && !remoteUrl.isEmpty()) {
				Configuration.remote = remoteUrl;

			} else {*/
			//	setupSeleniumGrid();
		//		Configuration.remote = "http://localhost:4444/wd/hub";
			/*}*/

			if (headless) {
				Configuration.headless = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deloadObjects(boolean remote) {
		//if (!remote) {
			destroySeleniumGrid();
		//}

	}

	public void setupSeleniumGrid() {

		// Initiate hub
		hub = initiateProcess("cmd /c START /MIN grid.bat");
		Selenide.sleep(5000);
		// Initiate nodes
		for (int i = 1; i <= nodeCount; i++) {
			nodes.add(initiateProcess("cmd /c START /MIN node.bat " + (9150 + i)));
			Selenide.sleep(1000);
		}
		Selenide.sleep(3000);
	}

	public void destroySeleniumGrid() {
		// Destroy nodes
		for (int i = 1; i <= nodeCount; i++) {
			deleteProcess("cmd /c FOR /F \"tokens=5 delims= \" %P IN ('netstat -a -n -o ^| findstr :" + (9150 + i)
					+ "') DO TaskKill.exe /PID %P /F");
			Selenide.sleep(1000);
		}

		// Destroy hub
		deleteProcess(
				"cmd /c FOR /F \"tokens=5 delims= \" %P IN ('netstat -a -n -o ^| findstr :4444') DO TaskKill.exe /PID %P /F");
		System.out.println("Destroyed hub successfully");

		// Close all command prompt
//		deleteProcess("cmd /c taskkill /IM cmd.exe /F");
//		System.out.println("Destroyed cmds successfully");

		// Remove chrome driver
		deleteProcess("cmd /c taskkill /IM chromedriver.exe /F");
		System.out.println("Destroyed drivers successfully");

	}

	public Process initiateProcess(String command) {
		String cwd = System.getProperty("user.dir");
		String file = cwd + "/src/main/java/com/agilysys/qa/gridIron/utils/grid/";
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command, null, new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public Process deleteProcess(String command) {
		String cwd = System.getProperty("user.dir");
		String file = cwd + "/src/main/java/com/agilysys/qa/gridIron/utils/grid/";
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command, null, new File(file));
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}


	public void makePropertyReadyForDateRoll(MasterObject masterObject, boolean override) {
		Property property= masterObject.getProperty();

		LocalDate propertyDate = ClientHelper.call(() ->
			ClientFactory.getPropertyManagement().getPropertyDate(property.getTenantId(), property.getPropertyId()));

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
			clearReservationAccordingToStatus(reservationSummary, masterObject);
		}

	}

	public void doDateRoll(Property property) {
		DateRollStatus dateRollStatus;
		int seconds = 0;
		String terminalId =
			new ClientPaymentGatewayGet().getTerminalId(property.getTenantId(), property.getPropertyId(), true);
		LocalDate propertyDate = ClientHelper.call(() -> ClientFactory.getPropertyManagement()
			.getPropertyDate(property.getTenantId(), property.getPropertyId()));
		ClientHelper.callUntil(() -> ClientFactory.getPropertyManagement()
				.startDateRoll(property.getTenantId(), property.getPropertyId(), propertyDate, terminalId, true), null,
			1000, 30000, null);

		do {
			dateRollStatus = ClientHelper.call(() -> ClientFactory.getPropertyManagement()
				.getDateRollStatus(property.getTenantId(), property.getPropertyId()));
			ThreadHelper.sleep(1000);
			seconds++;
		} while (!dateRollStatus.getStatus().equals(DateRollStatus.Status.COMPLETED) && seconds < 240);

		//Verify that the date roll succeeded
		Assert.assertTrue(dateRollStatus.getStatus().equals(DateRollStatus.Status.COMPLETED), String.format(
			"Date roll did not complete successfully after 240 seconds\nExpected status: %s\nReceived status: %s",
			DateRollStatus.Status.COMPLETED.toString(), dateRollStatus.getStatus().toString()));
	}

	String ERROR_MESSAGE_PREFIX = "tenantId: %s -  propertyId: %s";

	public void clearReservation(ReservationSummary reservationSummary, MasterObject masterObject) {
		Property property = masterObject.getProperty();
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

	public void clearReservationAccordingToStatus(ReservationSummary reservationSummary, MasterObject masterObject) {
		Property property = masterObject.getProperty();
		LocalDate propertyDate = ClientHelper.call(() ->
			ClientFactory.getPropertyManagement().getPropertyDate(property.getTenantId(), property.getPropertyId()));
		reservationSummary = new ClientReservationGet().getReservationById(property,reservationSummary);

		try {
			if (reservationSummary.getStatus().equalsIgnoreCase("RES")) {
				//Get Payment Method
				PaymentMethod cashPaymentMethod = new ClientPaymentMethodGet().getCashPaymentMethod(property);

				List<RoomDTO> rooms = new ClientRoomGet().findRooms(masterObject.getProperty());
				rooms.removeIf(x->x.getReservation()!=null);

				new ClientReservationAssignRoom()
					.assignRoom(masterObject.getProperty(), reservationSummary, rooms.get(0));
				new ClientReservationCheckin().checkin(masterObject.getProperty(), reservationSummary);

			} else if (reservationSummary.getStatus().equalsIgnoreCase("INH") && reservationSummary.getDepartureDate().equals(propertyDate))
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
