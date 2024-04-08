package com.agilysys.qa.gridIron.constants.pagefactory.home;

import static com.agilysys.qa.gridIron.constants.locators.home.SearchTile.BUTTON_GO;
import static com.agilysys.qa.gridIron.constants.locators.home.SearchTile.INPUT_SEARCH;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import org.joda.time.LocalDate;

import com.agilysys.qa.gridIron.constants.locators.home.SearchTile;
import com.agilysys.qa.gridIron.utils.HelperMethods;
import com.agilysys.qa.gridIron.wrappers.PageFactoryBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.Configuration;import java.time.Duration;

public class PFSearchTile extends PageFactoryBase{

	public static void setInputSearch(String input) {		
		clearAndType(INPUT_SEARCH, input);
	}
	
	public static void setSearchDate(LocalDate searchDate){
		waitForPropertyDateToLoad();
		clearAndType(SearchTile.INPUT_DATE, HelperMethods.convertDateToString(searchDate));
	}

	public static void clickButtonGo() {
		doubleClick(BUTTON_GO);
		doubleClick(BUTTON_GO);
	}

	public static void waitForSearchPageToLoad(){
		waitForPropertyDateToLoad();
		$(INPUT_SEARCH).should(Condition.visible, Duration.ofMillis(Configuration.timeout));
	}
	
	private static void waitForPropertyDateToLoad(){
		int time=0;
		while(time<=Configuration.timeout){
			if(!$(SearchTile.INPUT_DATE).getValue().trim().isEmpty()){
				return;
			}
			Selenide.sleep(2000);
			time = time+2000;			
		}		
	}
}