package com.agilysys.qa.evolve.support;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final WebDriver driver = null;
    private static WebDriverWait wait = null;
    public BasePage(WebDriver driver) {
        System.out.println("<<<<<<<< page constructor invoked BasePage Constructor >>>>>>>>");
        PageFactory.initElements(driver, this);
        driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    static{
        launchUrl("http://lab-stay-aks-009-server.westus.cloudapp.azure.com/#/search/reservations?tenantId=294052&propertyId=QAINT1297354");
    }





    protected static void launchUrl(String URL){
        driver.get(URL);
        System.out.println("URL Launched");
    }


    protected void waitForSeconds(int sec){
        try {
            Thread.sleep(sec * 1000);
        }catch (Exception j){
            System.out.println("Sleep Exception");
        }
    }

    protected WebElement waitForElementVisibility(WebElement element) {
        WebElement result;
        return result = wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean verifyElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    protected void jsScrollIntoView(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        waitForSeconds(1);
    }
}
