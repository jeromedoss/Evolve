package com.agilysys.qa.evolve.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Collections;

public class DriverBase {
    public static WebDriver driver;

    @BeforeSuite
    private static WebDriver driverInit() {
        System.out.println("Driver init Method");
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();

            // Disable the "Chrome is being controlled by automated software" notification
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            // Maximize the browser window on startup
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);

            System.out.println("Driver initiated");
        }
        return driver;
    }

    @AfterSuite
    private static void quitDriver(){
        driver.quit();
    }

}
