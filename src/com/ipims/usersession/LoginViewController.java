package com.ipims.usersession;
//A test from Kevin G.


import com.ipims.MenuViewController;

import com.ipims.views.LoginView;
import javafx.scene.Scene;


public class LoginViewController  {

	private LoginView view;

	public LoginViewController () {
		view = new LoginView();
		view.createLoginView(this);
	}
	
	public Scene getScene() {
		return view.getCurrentScene();
	}

	public void handleLoginButtonClick(String username, String password) {
		
		
		if(username.length() <= 20 && password.length() <= 20){
		UserSession.getInstance().login(username, password);
		if (UserSession.getInstance().getCurrentUser() == null) {
			view.showErrorMessage("Could not login please check the username / password");
		} else {
			MenuViewController vc = new MenuViewController();
			view.getStage().setScene(vc.getScene());
		}
		}
		
		else 
			view.showErrorMessage("Username and Password is allowed up to 20 characters");
	}
	
	public void handleNewUserButtonClick() {
		RegistrationViewController menu = new RegistrationViewController();
		view.getStage().setScene(menu.getScene());
	}

}
