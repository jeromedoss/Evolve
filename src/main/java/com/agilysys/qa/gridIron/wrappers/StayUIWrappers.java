package com.agilysys.qa.gridIron.wrappers;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_RESERVATION;
import static com.agilysys.qa.gridIron.constants.locators.home.HeaderDropDowns.NAV_SEARCH;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.clickAndWaitForNextElement;
import static com.codeborne.selenide.Selenide.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlSuite;

import com.agilysys.qa.gridIron.constants.locators.groups.LocatorsGroupsCommon;
import com.agilysys.qa.gridIron.constants.pagefactory.login.PFSignInDialog;
import com.agilysys.qa.gridIron.pageobject.login.POSignInDialog;
import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.listeners.TestNGCustomReportListener;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

/*
 * *Author - Harish Baskaran - 2018
 */
@Listeners({ TestNGCustomReportListener.class })
public class StayUIWrappers extends GenericWrappers {

	public String dataSheetName;
	boolean remote = false;

	@Parameters({ "remote", "remoteUrl", "headless" })
	@BeforeSuite
	public void beforeSuite(@Optional("false") boolean remote, @Optional("") String remoteUrl, @Optional("false") boolean headless) {
		loadObjects();
		this.remote = remote;
		invokeApp(remote, remoteUrl, headless);
	}

	
	@BeforeClass
	public void beforeClass() {
		
	}

	@AfterClass(alwaysRun=true)
	public void defaultAfterClass() {
		quitBrowser();
	}

	@AfterMethod(alwaysRun=true)
	public static void
	wrapperAfterMethod(ITestResult result) {
		if (1 ==1 ){ //result.getStatus() == ITestResult.FAILURE) {
			Selenide.refresh();

			// Accept alert if present
			try {
				WebDriverWait wait = new WebDriverWait(com.codeborne.selenide.WebDriverRunner.getWebDriver(), 3);
				wait.until(ExpectedConditions.alertIsPresent());
				com.codeborne.selenide.WebDriverRunner.getWebDriver().switchTo().alert().accept();
			} catch (Exception e) {
				
			}
			PageFactoryBase.waitForPageToLoad();

			sleep(1000);
			clickAndWaitForNextElement(NAV_RESERVATION, NAV_SEARCH);//.should(Condition.visible, Duration.ofMillis(Configuration.timeout));
			click(NAV_SEARCH);//.should(Condition.visible, Duration.ofMillis(Configuration.timeout));
            Selenide.sleep(1000);
			if($$(LocatorsGroupsCommon.BUTTON_SAVE_POPUP).size() >0){
				click(LocatorsGroupsCommon.BUTTON_SAVE_POPUP);
			}
			//Log in if required
			if(PFSignInDialog.isLoginPage()){
				new POSignInDialog().SignIn();
			}

		}
	}

	@AfterSuite(alwaysRun=true)
	public void afterSuite() {
		
	//	deloadObjects(remote);
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		System.out.println(dtf.format(now));
	}


	
}