package com.agilysys.qa.gridIron.constants.locators.reservation.summary;

import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsSummaryModal {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyNqj']");
	public static final By LABEL_CONFORMARION_NUMBER = By.xpath("//*[@data-qa-id='fGUyNqj']//h3");
	public static final By BUTTON_SUMMARY_COLLAPSE = By.xpath("//*[@data-qa-id='fGUyNqj']//button");
	public static final By LINK_ROOM = By.xpath("//*[@data-qa-id='fGUyPKs']");
	public static final By LABEL_ROOM_TYPE = By.xpath("//*[@data-qa-id='fGUyRVk']");
	public static final By LINK_EXTRA_GUEST = By.xpath("//*[@data-qa-id='fGUyP3j']");
	public static final By LABEL_EXTRA_ADULT_COUNT = By.xpath("//*[@data-qa-id='fGUyP3j']//span[1]");
	public static final By LABEL_EXTRA_CHILD_COUNT = By.xpath("//*[@data-qa-id='fGUyP3j']//span[2]");
	public static final By TYPEHEAD_GROUP = By.xpath("//*[@data-qa-id='h7SeQRw']");
	public static final By TYPEHEAD_DROPDOWN_VALUE_GROUP = By.xpath("//*[@data-qa-id='h7SeQS1']");
	public static final By TYPEHEAD_COMPANY = By.xpath("//*[@data-qa-id='fGUyNgX-val']");
	public static final By TYPEHEAD_DROPDOWN_VALUE_COMPANY = By.xpath("//*[@data-qa-id='fGUyNgX']//a");

	public static final By MANDATE_TYPEHEAD_SOURCE_OF_BUSINESS = By.xpath("//*[@data-qa-id='fGUyNsR-click'][2]");
	public static final By MANDATE_TYPEHEAD_GUEST_TYPE = By.xpath("//*[@data-qa-id='fGUyRps-click'][2]");
	public static final By MANDATE_TYPEHEAD_MARKET_SEGMENT = By.xpath("//*[@data-qa-id='fGUyQQ3-click'][2]");

	public static final By LABEL_ARRIVAL_DATE = By.xpath("//div[@data-qa-id='fGUyNy2']//div[@data-qa-id='fGUyPe6']");
	public static final By LABEL_ARRIVAL_DAY = By.xpath("//*[@data-qa-id='fGUyNy2']//*[@data-qa-id='fGUyQUW']");
	public static final By LABEL_ARRIVAL_MONTH = By.xpath("//*[@data-qa-id='fGUyNy2']//*[@data-qa-id='fGUyPe5']");
	public static final By LABEL_ARRIVAL_YEAR = By.xpath("//*[@data-qa-id='fGUyNy2']//*[@data-qa-id='fGUyPuf']");
	public static final By LINK_DATE_ARRIVAL = By.xpath("//*[@data-qa-id='fGUyNy2']//*[@data-qa-id='fGUyNhc']");

	public static final By LABEL_DEPARTURE_DATE = By.xpath("//*[@data-qa-id='fGUyPWf']//*[@data-qa-id='fGUyPe6']");
	public static final By LABEL_DEPARTURE_DAY = By.xpath("//*[@data-qa-id='fGUyPWf']//*[@data-qa-id='fGUyQUW']");
	public static final By LABEL_DEPARTURE_MONTH = By.xpath("//*[@data-qa-id='fGUyPWf']//*[@data-qa-id='fGUyPe5']");
	public static final By LABEL_DEPARTURE_YEAR = By.xpath("//*[@data-qa-id='fGUyPWf']//*[@data-qa-id='fGUyPuf']");
	public static final By LINK_DATE_DEPARTURE = By.xpath("//*[@data-qa-id='fGUyPWf']//*[@data-qa-id='fGUyNhc']");

	public static final By LABEL_STAY_NIGHT_COUNT = By.xpath("//*[@data-qa-id='fGUyNxx']");

	public static final By BADGE_LATE_CHECKOUT = By.xpath("//i[@class='late']//ancestor::div[@data-qa-id='fGUyPTr']");
	public static final By BADGE_LINKED_RESERVATION = By.xpath("//i[@class='lr']//ancestor::div[@data-qa-id='fGUyPTr']");
	public static final By BADGE_GUEST_MESSAGE = By.xpath("//i[@class='msg']//ancestor::div[@data-qa-id='fGUyPTr']");
	public static final By BADGE_NRG = By.xpath("//i[@class='nrg']//ancestor::div[@data-qa-id='fGUyPTr']");
	public static final By BADGE_PET = By.xpath("//i[@class='pet']//ancestor::div[@data-qa-id='fGUyPTr']");
	public static final By BADGE_SVC = By.xpath("//i[@class='svc']//ancestor::div[@data-qa-id='fGUyPTr']");
	public static final By BADGE_VIP = By.xpath("//i[@class='vip']//ancestor::div[@data-qa-id='fGUyPTr']");

	public static final By PET_MODAL_COMMENT = By.xpath("//textarea[@data-qa-id='hB1gJAu-val']");
	public static final By PET_MODAL_DROPDWON_PET = By.xpath("//input[@data-qa-id='hB1gJCD-click']");
	public static final By PET_MODAL_DROPDWON_OPTIONS_PET = By.xpath("//a[@data-qa-id='hB1gJCD-text']");
	public static final By PET_MODAL_PETNAME = By.xpath("//input[@data-qa-id='hB1gJCm-val']");
	public static final By PET_MODAL_DELETE_PET = By.xpath("//div[@data-qa-id='hB1gJCn']");
	public static final By PET_MODAL_CONFIRM_DELETE_PET = By.xpath("//button[@data-qa-id='hB1gJAs']");	
	public static final By PET_MODAL_LINK_ADD_PET = By.xpath("//a[@data-qa-id='hB1gJDB']");
	public static final By PET_MODAL_SAVE = By.xpath("//button[@data-qa-id='hB1gJCL']");
	
	public static final By INPUT_RESERVATION_ALIAS = By.xpath("//input[@data-qa-id='h8Z8aNz-val']");
	
	public static final By INPUT_SOURCE_OF_BUSINESS = By.xpath("//input[@data-qa-id='fGUyPEm-click'][@placeholder]");
	public static final By DROPDOWN_GUEST_TYPE = By.xpath("//input[@data-qa-id='h24GLP6-click']");
	public static final By DROPDOWN_OPTIONS_GUEST_TYPE = By.xpath("//a[@data-qa-id='h24GLP6-text']");
	public static final By INPUT_MARKET_SEGMENT = By.xpath("//input[@data-qa-id='fGUyQWM-click'][@placeholder]");
	
	public static final By LINK_ADD_TRAVEL_AGENT = By.xpath("//a[@data-qa-id='fGUyNhs']");
	public static final By INPUT_TRAVEL_AGENT = By.xpath("//input[@data-qa-id='fGUyNgW-val']");
	
	public static final By INPUT_BOOKED_BY = By.xpath("//input[@data-qa-id='fGUyP1p-val']");
	
	public static final By LINK_ADD_THIRD_PARTY_CONFIRMATION = By.xpath("//a[@data-qa-id='fGUyRRd']");
	public static final By INPUT_THIRD_PARTY_SOURCE = By.xpath("//input[@data-qa-id='fGUyR77-click'][@placeholder]");
	public static final By INPUT_THIRD_CONFIRMATION_NUMBER = By.xpath("//input[@data-qa-id='fGUyQ9e-val']");
	
	public static final By BUTTON_REQUEST_SERVICE = By.xpath("//button[@data-qa-id='fst6Skm']");
	public static final By SELECT_RATES_FOR_ALL_DAYS = By.xpath("//*[@data-qa-id='jRU1Yd6']");
	public static final By ALL_SVC_REQUESTS = By.xpath("//tbody[@data-qa-id='fst6SmB']");
	public static final By ALL_SVC_COMMENTS = By.xpath("//tr[@data-qa-id='fst6SkN']");
	
	public static final By getServiceRequestEditLinkByServiceCode(String serviceCode){
		return By.xpath("//*[@data-qa-id='fst6Sm5']//*[contains(text(),'"+serviceCode+"')]//ancestor::tr//a");
	}
		
}
