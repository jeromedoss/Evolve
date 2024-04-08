package com.agilysys.qa.gridIron.builders.shared.folios;

import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.qa.client.property.date.ClientPropertyDateGet;
import com.agilysys.qa.gridIron.utils.RandomHelper;
import com.codeborne.selenide.Selenide;
import org.joda.time.LocalDate;
import org.joda.time.MonthDay;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.time.Month;

import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFRoutingRulesModal.clickButtonAnyCategory;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFRoutingRulesModal.clickButtonSave;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFRoutingRulesModal.clickSelectDestination;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFRoutingRulesModal.selectSearchForAnAccount;
import static com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFRoutingRulesModal.setInputDestinationAccountName;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;

/*
 * *Author - Harish Baskaran - 2018
 */
public class
RoutingRulesModal {

	public static void stepRoutingRule(String Input) {
		click(By.xpath("//input[@data-qa-id=\"ikEC7cj-val\"]"));
		Selenide.actions().click().sendKeys(RandomHelper.getRandomAlphaString(5)).build().perform();
		click(By.xpath("//textarea[@data-qa-id=\"ikEC7dM-val\"]"));
		Selenide.actions().click().sendKeys(RandomHelper.getRandomAlphaString(5)).build().perform();
		clickButtonAnyCategory();
		selectSearchForAnAccount();
		setInputDestinationAccountName(Input);
		clickSelectDestination();
		clickButtonSave();
	}
}
