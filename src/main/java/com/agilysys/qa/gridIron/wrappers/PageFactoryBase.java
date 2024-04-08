package com.agilysys.qa.gridIron.wrappers;


import static com.agilysys.qa.gridIron.constants.locators.shared.folios.LocatorsFoliosDetails.BUTTON_MORE;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.source;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

import org.apache.commons.io.FileUtils;
//import org.elasticsearch.river.RiverIndexName.Conf;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.agilysys.qa.gridIron.constants.locators.home.SearchTile;
import com.agilysys.qa.gridIron.pageobject.shared.widgets.POCalendars;
import com.agilysys.qa.helpers.ThreadHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.commands.Click;
import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.InvalidStateException;



public class PageFactoryBase  {
	public static By MENU_BAR = By.xpath("//nav[@role='menubar']");

	public static void type(By locator, String value) {
		$(locator).shouldBe(Condition.visible,Duration.ofMillis(Configuration.timeout));
//		if (value == null || value.isEmpty()) {
//			return;
//		}
//		$(locator).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
//		for(char c: value.toCharArray() ) {
//			$(locator).sendKeys(Character.toString(c));
//		}
		clearAndType(locator, value);
	}

	public static void type(By locator, int index, String value) {
		if (value == null || value.isEmpty()) {
			return;
		}
		$$(locator).get(index).sendKeys(value);

	}

	public static void clearAndType(By locator, String value) {
		if (value == null) {
			return;
		}
		jsClick(locator);
		scrollElementToVisibleArea($(locator));
		long deadline = System.currentTimeMillis() + Configuration.timeout;
		jsClick(locator);
		$(locator).clear();
		while ( !$(locator).getValue().contains(value) &&
				(System.currentTimeMillis() <= deadline)) {
			$(locator).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
			for (char c : value.toCharArray()) {
				$(locator).sendKeys(Character.toString(c));
				ThreadHelper.sleep(100);
			}
		}
	}

	public static void clearAndType(SelenideElement element, String value) {

	}

	public static void typeDate(By locator, LocalDate date) {
		if (date == null) {
			return;
		}
		clearAndType(locator, date.toString("MM/dd/yyyy"));
	}

	public static void selectByText(By dropdownLocator, By optionsLocator, String value) {
		if (value == null || value.isEmpty()) {
			return;
		}
		SelenideElement dropdownElement = $(dropdownLocator);

		click(dropdownElement);
		int time = 0;
		while (time <= Configuration.timeout) {
			Selenide.sleep(500);
			ElementsCollection listElements = $$(optionsLocator);
			if (listElements.filter(Condition.text(value)).size() > 0) {
				listElements.findBy(Condition.text(value)).click();
				return;
			}
			time = time + 2000;
			dropdownElement.click();
			Selenide.sleep(500);
			dropdownElement.click();
			Selenide.sleep(500);
		}
	}

	public static void waitForDropDownToLoad(By dropdownLocator, By optionsLocator) {
		SelenideElement dropdownElement = $(dropdownLocator);

		click(dropdownElement);
		int time = 0;
		while (time <= Configuration.timeout) {

			ElementsCollection listElements = $$(optionsLocator);
			if (listElements.size() > 0) {
				dropdownElement.click();
				return;
			}
			time = time + 2000;
			dropdownElement.click();
			Selenide.sleep(500);
			dropdownElement.click();
			Selenide.sleep(500);
		}
	}

	public static void selectByText(By dropdownLocator, int index, By optionsLocator, String value) {
		if (value == null || value.isEmpty()) {
			return;
		}
		SelenideElement dropdownElement = $$(dropdownLocator).get(index);
		scrollElementToVisibleArea(dropdownElement);
		ElementsCollection listElements = $$(optionsLocator);
		click(dropdownElement.shouldBe(Condition.visible));
		int time = 0;
		while (time <= 60000) {
			if (listElements.filter(Condition.text(value)).size() > 0) {
				listElements.findBy(Condition.text(value)).click();
				return;
			}
			time = time + 2000;
			dropdownElement.click();
			Selenide.sleep(500);
			dropdownElement.click();
		}
	}

	public static void acceptGoogleAlertIfPresent() {
		int time = 0;
		while (time <= Configuration.timeout) {
			if ($$(By.xpath("//button[@class='dismissButton'][text()='OK']")).size() > 0) {
				$(By.xpath("//button[@class='dismissButton'][text()='OK']")).click();
				break;
			}
			Selenide.sleep(1000);
			time = time + 1000;
		}
	}

	public static void expandSection(By locator) {
		SelenideElement element = $(locator);
		if (element.getAttribute("class").contains("icon-expand")) {
			click(element);
		}
	}

	public static void collapseSection(By locator) {
		SelenideElement element = $(locator);
		if (element.getAttribute("class").contains("icon-collapse")) {
			click(element);
		}
	}

	public static void openArrow(By locator) {
		if ($(locator).getAttribute("class").contains("closed")) {
			click(locator);
		}
	}

	public static void doubleClick(By locator) {
		SelenideElement element = $(locator);
		waitForElementToLoad(locator, 3000);
		scrollElementToVisibleArea(element);
		element.hover().click();
		element.should(Condition.visible, Duration.ofMillis(Configuration.timeout)).click();
		//element.hover().click();
	}

	public static void click(By locator) {
		waitForElementToLoad(locator, Configuration.timeout);
		SelenideElement element = $(locator);
		click(element);

	}


	public static void clickAndWaitForNextElement(By locator, By nextElement) {

		long deadline = System.currentTimeMillis() + Configuration.timeout;
		while(System.currentTimeMillis() <= deadline) {
			click(locator);
			long time = 0;
			while (time <= 5000) {
				Selenide.sleep(1000);
				time = time + 1000;
				if ($$(nextElement).size() > 0) {
					return;
				}

			}
			System.out.println("TEST");
		}
	}

	public static void click(By locator, int index) {
		SelenideElement element = $$(locator).get(index);
		click(element);
	}

	public static void click(SelenideElement element) {
		scrollElementToVisibleArea(element);
		actions().moveToElement(element).perform();
		try {
			element.click();
		} catch (InvalidStateException e) {
			WebDriver driver = getWebDriver();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		}
	}



	public static void jsClick(SelenideElement element) {
			WebDriver driver = getWebDriver();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
	}

	public static void jsClick(By locator){
		SelenideElement element = $(locator);
		jsClick(element);
	}


	public static void waitForElementToDisappear(By locator, long timeoutInMilliSeconds) {
		long time = 0;
		while (time <= timeoutInMilliSeconds) {
			try {
				if ($$(locator).size() == 0) {
					return;
				} else if ($(locator).getAttribute("offsetHeight").equals("0")) {
					return;
				}
			}
			catch (ElementNotFound e){
				return;
			}
			Selenide.sleep(1000);
			time = time + 1000;
		}
		//Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) getWebDriver());

		//Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination

		File DestFile = new File("c:/test.png");

		//Copy file at destination

		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals($$(locator).size(), 0, "Element present");
	}

	public static void waitForElementToLoad(By locator, long timeoutInMilliSeconds) {
		long time = 0;

		while (time <= timeoutInMilliSeconds) {

			if (!($$(locator).size() == 0) && !$(locator).getAttribute("offsetHeight").equals("0") && $(locator).is(Condition.visible)) {
				return;
			}
			Selenide.sleep(1000);
			time = time + 1000;
		}
		Assert.assertNotEquals($$(locator).size(), 0, "Element not present");
	}

	public static boolean waitForPageToLoad(){
//		String dateTimeInTopRightCorner = "//time[@class='chrome-main-date ng-binding']";
//
//		waitForElementToLoad(By.xpath(dateTimeInTopRightCorner), Configuration.timeout);
//		WebElement element = getWebDriver().findElement(By.xpath(dateTimeInTopRightCorner));
//		String arrColor = element.getCssValue("color");
//		long time = 0;
//		long timeoutInMilliSeconds = Configuration.timeout;
//		while(!arrColor.equals("rgba(236, 170, 0, 1)") && time <= timeoutInMilliSeconds){
//			element = getWebDriver().findElement(By.xpath(dateTimeInTopRightCorner));
//			arrColor = element.getCssValue("color");
//			Selenide.sleep(1000);
//			time = time + 1000;
//		}
		WebDriver driver = getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
				}
				catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState")
						.toString().equals("complete");
			}
		};
		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	public static void scrollElementToVisibleArea(SelenideElement selenideElement) {

//        if(!getWebDriver().getCurrentUrl().contains("/login?")){
//		int i = 1;
//		int menuX = $(MENU_BAR).getCoordinates().inViewPort().x;
//		int menuRightX = menuX + $(MENU_BAR).getSize().width;
//
//		int menuY = $(MENU_BAR).getCoordinates().inViewPort().y;
//		int menuBottomY = menuY + $(MENU_BAR).getSize().height;
//
//		WebDriver driver = getWebDriver();
//		int elementY = selenideElement.getCoordinates().inViewPort().y;
//		int elementBottomY = elementY + selenideElement.getSize().height;
//		Long viewPortHeight = (Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight");
//		viewPortHeight = (long) (viewPortHeight * .80);
//
//		int toScroll = 0;
//		if (elementY <= menuBottomY) {
//			toScroll = menuBottomY - elementY;
//		} else if (elementBottomY > viewPortHeight) {
//			toScroll = (int) (viewPortHeight - elementBottomY);
//		}
//
//		if (toScroll != 0) {
//			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + String.valueOf(0 - toScroll) + ")");
//		}



		String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
			+ "var elementTop = arguments[0].getBoundingClientRect().top;"
			+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";



		((JavascriptExecutor) getWebDriver()).executeScript(scrollElementIntoMiddle, selenideElement);
	}

	public static void selectTypeHead(By locator, String value) {
		type(locator, value);
		click(By.xpath("//a[contains(@title,'" + value + "')]"));

	}

	public static void selectDate(By calendarLocator, LocalDate date) {
		new POCalendars(calendarLocator).setDate(date);
	}

	public static void setCheckBox(By locator, boolean check) {
		boolean isChecked = Arrays.asList($(locator).getAttribute("class").split(" ")).contains("active");
		if (check && !isChecked) {
			click(locator);
		}

		if (!check && isChecked) {
			click(locator);
		}
	}
}