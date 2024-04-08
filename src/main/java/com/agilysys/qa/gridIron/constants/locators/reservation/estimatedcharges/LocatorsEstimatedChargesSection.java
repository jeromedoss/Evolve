package com.agilysys.qa.gridIron.constants.locators.reservation.estimatedcharges;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;

/*
 * *Author - Harish Baskaran - 2018
 */
public class LocatorsEstimatedChargesSection {

	public static final By HEADER_MODAL = By.xpath("//*[@data-qa-id='fGUyPQm']");

	public static final By BUTTON_COLLAPSE = By
			.xpath("//*[@data-qa-id='fGUyPQm']//div[@class='panel-heading']//button");

	public static final By LABEL_ESTIMATED_DUE_AT_CHECKOUT = By.xpath("//*[@data-qa-id='fGUyQPu']");
	public static final By LABEL_ESTIMATED_DUE_AT_CHECKOUT_SHORT = By.xpath("//*[@data-qa-id='fGUyNyz']");
	public static final By BUTTON_ADD_RECURRING_CHARGE = By.xpath("//*[@data-qa-id='fGUyP8b']");
	public static final By LINK_DEPOSIT_POLICY = By.xpath("//*[@data-qa-id='fGUyPHQ']");
	public static final By LINK_CANCELLATION_POLICYnPolicyLink = By.xpath("//*[@data-qa-id='fGUyNve']");
	public static final By LINK_INVENTORY = By.xpath("//*[@data-qa-id='hKaNtBx']");
	public static final By CHECKBOX_DO_NOT_DISCLOSE_RATE = By.xpath("//*[@data-qa-id='fH2BFVW-click']");

	public static final By LINK_TAX_EXEMPT = By.xpath("//*[@data-qa-id='fGUyR6C']");
	public static final By LIST_INPUT_CHARGE = By.xpath("//*[@data-qa-id='fwa5s6J-val']");
	public static final By LIST_LINK_RATE_PLANS = By.xpath("//*[@data-qa-id='fwa5rSt']");
	public static final By LIST_DATE_STAYS = By.xpath("//*[@data-qa-id='fwa5s5u']");
	
	
	public static String getEstimatedChargesLineItemSection(LocalDate date){
		String dayOfMonth = null;
		if(date.getDayOfMonth()>=1 && date.getDayOfMonth()<=9) {
			dayOfMonth = "0" + date.getDayOfMonth();
		}
		else{
			dayOfMonth = String.valueOf(date.getDayOfMonth());
		}

		return "//div[@data-qa-id='fwa5s61']//div[contains(text(),'" + dayOfMonth + ",')][contains(text(),'" +
			date.getYear() + "')]//ancestor::div[@data-qa-id='fwa5s5Z']";
	}
	
	public static By getRatePlanLink(LocalDate date){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		return By.xpath(estimatedChargesLineItemSection+"//a");
	}
	
	public static By getLineItemQuantity(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5rSs']//span[@data-qa-id='hVi5GV1']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getLineItemChargeAmount(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5rSs']//input[@data-qa-id='j2sBPFB-val']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getLineItemDeleteIcon(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5rSs']//span[@data-qa-id='fwa5s6A']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getLineItemTaxAmount(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5rSs']//span[@data-qa-id='g3nzGc6']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getLineItemTotal(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5rSs']//span[@data-qa-id='fwa5s5d']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getPackageLineItemArrow(LocalDate date){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//input[@data-qa-id='j2sBPFB-val']";

		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getPackageLineItemDoNotDiscloseRates(LocalDate date){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//div[@data-qa-id='fuLFAVX']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getPackageRoomChargeAmount(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5s6Q']//input[@data-qa-id='fwa5s5x-val']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getPackageRoomChargeTaxAmount(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5s6Q']//div[@data-qa-id='fwa5s6C']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getPackageRoomChargeTotalAmount(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5s6Q']//div[@data-qa-id='fwa5s6T']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getPackageComponentQuantity(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5s6Y']//div[@data-qa-id='hUmDen5']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getPackageComponentAmount(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5s6Y']//div[@data-qa-id='fwa5s68']//input";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getPackageComponentTaxAmount(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5s6Y']//div[@data-qa-id='fwa5s5Y']";
		return By.xpath(estimatedChargesLineItemSection);
	}
	
	public static By getPackageComponentTotalAmount(LocalDate date, String itemName){
		String estimatedChargesLineItemSection = getEstimatedChargesLineItemSection(date);
		estimatedChargesLineItemSection = estimatedChargesLineItemSection + "//*[contains(text(),'"+itemName+"')]//ancestor::div[@data-qa-id='fwa5s6Y']//div[@data-qa-id='fwa5s5K']";
		return By.xpath(estimatedChargesLineItemSection);
	}	
}