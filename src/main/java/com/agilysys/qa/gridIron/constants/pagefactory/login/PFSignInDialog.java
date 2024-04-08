package com.agilysys.qa.gridIron.constants.pagefactory.login;

import static com.agilysys.qa.gridIron.constants.locators.home.SearchTile.BUTTON_GO;
import static com.agilysys.qa.gridIron.constants.locators.login.LocatorsSignInDialog.*;
import static com.agilysys.qa.gridIron.wrappers.PageFactoryBase.click;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PFSignInDialog {

	public static void clickHeaderModal() {
		click(HEADER_MODAL);
	}

	public static void setInputUsername(String username) {
		click(INPUT_USERNAME);
		$(INPUT_USERNAME).setValue(username);
		click(LOGIN);
	}

	public static void setInputPassword(String password) {
		$(INPUT_PASSWORD).setValue(password);
	}

	public static void clickButtonSignin() {
		click(BUTTON_SIGN_IN);
		$(BUTTON_GO).shouldBe(visible);
	}
	
	public static boolean isLoginPage(){
		if($$(INPUT_USERNAME).size() > 0){
			return true;
		}
		return false;
	}
}