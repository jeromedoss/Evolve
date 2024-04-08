package com.agilysys.qa.gridIron.constants.locators.housekeeping.main;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsAssignSection {

    public static final By CONTAINER_UNASSIGNED = By.xpath("//*[@data-qa-id='fkdsJc1']");
    public static final By CONTAINER_ASSIGNED = By.xpath("//*[@data-qa-id='fkdsJc6']");

    public static final By CHECKBOX_SELECTALL_UNASSGINED = By.xpath(
            "//*[@data-uid='agPms.main.housekeepingAssign.unassignedServiceRequests54.agCheckbox']//button[@data-qa-id='fkdsJdj-click']");
    public static final By TAB_GUEST_SERVICE = By.xpath("//*[@data-qa-id='g2JXNno']/a");
    public static final By TAB_ROOM_SERVICE = By.xpath("//*[@data-qa-id='fkdsJdn']/a");
    public static final By BUTTON_AUTO_ASSIGN = By.xpath("//*[@data-qa-id='fkdsJeB']/button");
    public static final By BUTTON_EVEN_SPLIT = By.xpath("//a[text() = 'EVEN SPLIT']/..");
    public static final By LABEL_STAFF_PRIORITY = By.xpath("//a[text() = 'STAFF PRIORITY']/..");
    public static final By UNASSIGNED_SERVICE_REQUEST = By.xpath("//*[@data-qa-id='fkdsJd5']");
    public static final By EXPAND_ASSIGNED_REQUEST = By.xpath("//*[@data-qa-id='g2JXP6H']");
    public static final By CLICK_ASSIGNED_ROOMNO = By.xpath("//*[@data-qa-id='fkdsJce']");
    public static final By PET_SYMBOL = By.xpath("//span[@data-qa-id='hB1gJ8Y']");
}
