package com.agilysys.qa.gridIron.constants.locators.booking;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsBookReservationModal {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyQUB']");
	
	public static final By NAVIGATION_GUEST_INFO = By.xpath("//li[@data-qa-id='fGUyPLj']/a");
	public static final By NAVIGATION_SOURCES = By.xpath("//li[@data-qa-id='fGUyP5Z']/a");
	public static final By NAVIGATION_ESTIMATED_CHARGES = By.xpath("//li[@data-qa-id='fGUyRX5']/a");
	public static final By NAVIGATION_OTHERS = By.xpath("//li[@data-qa-id='h8Z8a7n']/a");
	public static final By NAVIGATION_PAYMENT = By.xpath("//li[@data-qa-id='fGUyNjF']/a");
	public static final By NAVIGATION_DEPOSIT = By.xpath("//li[@data-qa-id='fGUyPx5']/a");
	
	public static final By ESTIMATED_CHARGE_LOADED_ELEMENT = By.xpath("//span[@data-qa-id='fvGhV4U']");
	
	public static final By RESERVATION_ALIAS_ARROW = By.xpath("//span[@data-qa-id='hBBZNLK']");
	public static final By INPUT_RESERVATION_ALIAS = By.xpath("//input[@data-qa-id='hBBZNLY-val']");
	
	public static final By LINK_ADD_RECURRING_CHARGES = By.xpath("//a[@data-qa-id='gxUexjX']");
	
	public static final By BUTTON_BOOK = By.xpath("//*[@data-qa-id='fGUyPFf']");
	public static final By BUTTON_BOOK_AS_WALKIN = By.xpath("//*[@data-qa-id='fGUyNoM']");
	public static final By BUTTON_GOTO_RESERVATION = By.xpath("//button[@data-qa-id='fGUyQkj']");
	public static final By BUTTON_CLOSE = By.xpath("//a[@data-ng-click='closeModal()']");
	public static final By BUTTON_CANCEL = By.xpath("//a[@ng-click='cancelModal()']");
	public static final By LABEL_RESERVATION_CONFIRMATION_NO = By.xpath("//label[@data-qa-id='iKmRrsF-text']");
	
	public static final By CONFIRMATION_TAB = By.xpath("//li[@data-qa-id='fGUyQMv']");
	
	public static final By MESSAGE_SUCCESS_CONFORMATION = By.xpath("//*[@data-qa-id='fGUyQ9p']");

}