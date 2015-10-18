package com.ipims.usersession;
//A test from Kevin G.
import java.util.*;

import com.ipims.MenuViewController;
import com.ipims.views.LoginView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.ipims.models.*;
public class LoginViewController  {

	private LoginView view;

	public LoginViewController () {
		view = new LoginView();
		view.createLoginView(this);
	}
	
	public Scene getScene() {
		return view.getCurrentScene();
	}

	public void handleLoginButtonClick(String username, String password, Map<String, String> validUsers) {
		validUsers.put("test", ""); // for easy access in testing
		//System.out.println(validUsers.values()); // for testing
		if(validUsers.containsKey(username) && validUsers.containsValue(password)) { // check to see if valid user
			UserSession.getInstance().login(username, password);
			MenuViewController menu = new MenuViewController();
			Stage stage = (Stage) view.getCurrentScene().getWindow();
			
			stage.setScene(menu.getScene());
		}
		else {
			view.showErrorMessage("Invalid username/password");
		}
	}
	
	public void handleNewUserButtonClick() {
		RegistrationViewController menu = new RegistrationViewController();
		view.getStage().setScene(menu.getScene());
	}

}
