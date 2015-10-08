package com.ipims.usersession;

import com.ipims.MenuViewController;
import com.ipims.views.LoginView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginViewController  {

	private LoginView view;

	public Scene getScene() {
		view = new LoginView();
		view.createLoginView(this);
		return view.getCurrentScene();
	}

	public void handleLoginButtonClick(String username, String password) {
		UserSession.getInstance().login(username, password);
		MenuViewController menu = new MenuViewController();
		Stage stage = (Stage) view.getCurrentScene().getWindow();
		stage.setScene(menu.getScene());
	}
	
	public void handleNewUserButtonClick() {
		RegistrationViewController menu = new RegistrationViewController();
		view.getStage().setScene(menu.getScene());
	}
}
