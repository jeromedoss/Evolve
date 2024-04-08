package com.agilysys.qa.gridIron.constants.locators.booking.filters;

import org.openqa.selenium.By;

public class LocatorsFilters {

	public static final By BUTTON_COLLAPSE_ROOM_TYPES = By.xpath("//*[@title='Room Types']//h3");
	public static final By LIST_CHECKBOX_ROOM_TYPES = By.xpath("//*[@data-qa-id='fGUyPAM-item']");
	// $$(LocatorsRatesFilter.LIST_CHECKBOX_PROPERTY).findBy(Condition.text("BAR")).vicks

	public static final By BUTTON_COLLAPSE_ROOM_CLASSES = By.xpath("//*[@title='Room Classes']//h3");
	public static final By LIST_CHECKBOX_ROOM_CLASSES = By.xpath("//*[@data-qa-id='fGUyNWo-item']");

	public static final By BUTTON_COLLAPSE_SMOKING = By.xpath("//*[@title='Smoking']//h3");
	public static final By LIST_CHECKBOX_SMOKING = By.xpath("//*[@data-qa-id='fGUyQAE-item']");

	public static final By BUTTON_CHECKBOX_ADA = By.xpath("//*[@data-qa-id='fGUyQV2-click']");
	public static final By BUTTON_CHECKBOX_PETS = By.xpath("//*[@data-qa-id='hB1gJ9g-click']");
}
