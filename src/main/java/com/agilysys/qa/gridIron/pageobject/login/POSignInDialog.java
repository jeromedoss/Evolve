package com.agilysys.qa.gridIron.pageobject.login;

import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFSignInDialog.clickButtonSignin;
import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFSignInDialog.clickHeaderModal;
import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFSignInDialog.setInputPassword;
import static com.agilysys.qa.gridIron.constants.pagefactory.login.PFSignInDialog.setInputUsername;

import com.agilysys.qa.gridIron.utils.Endpoints;

/*
 * *Author - Harish Baskaran - Aug/2018
 */
public class POSignInDialog {

	public void SignIn() {
		String user = Endpoints.getUserName();
		String pass = Endpoints.getPassword();

		//clickHeaderModal();
		setInputUsername(user);
		setInputPassword(pass);
		clickButtonSignin();

	}

}
