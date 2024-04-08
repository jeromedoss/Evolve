package com.agilysys.qa.gridIron.constants.locators.profiles.company.ar.invoice;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsInvoicesSection {

    public static final By INPUT_SEARCH = By.xpath("//*[@data-qa-id='fGUyPAd']//*[@data-qa-id='fGUyRJz-val']");

    public static final By CHECKBOX_SELECTALL = By.xpath("//*[@data-qa-id='fGUyNbX-click']");

    public static final By LABEL_STATUS = By.xpath("//*[@data-qa-id='fGUyRpN']/td[6]");
    public static final By LABEL_BALANCE = By.xpath("//*[@data-qa-id='fGUyR4n']");

    public static final By LABEL_EMPTY_INVOICE = By.xpath("//*[@data-qa-id='fGUyQMa']");

    public static final By BUTTON_MAKE_PAYMENT = By
            .xpath("//*[@data-qa-id='fGUyNsj']");

    public static final By BUTTON_RETRIEVE_INVOICES = By.xpath("//a[.='Retrieve Invoices']");

    public static final By MORE_BUTTON_INVOICES = By.xpath("//li[@data-qa-id='14wGKh']//button");
    public static final By BUTTON_CREATE_STATEMENT = By.xpath("//*[@data-qa-id='fGUyNbV']//button");

    public static final By INPUT_USERNAME = By.xpath("//*[@data-qa-id='h9ZQJTD-val']");
    public static final By INPUT_PASSWORD = By.xpath("//*[@data-qa-id='h9ZQJUH-val']");

    public static final By DROPDOWN_PAYMENT = By.xpath("//input[@data-qa-id='fGUyQ7s-click']");
    public static final By LIST_DROPDOWN_PAYMENT = By.xpath("//a[@data-qa-id='fGUyQ7s-text']");
}
