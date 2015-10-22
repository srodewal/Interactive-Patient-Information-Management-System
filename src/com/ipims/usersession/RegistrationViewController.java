package com.ipims.usersession;

import com.ipims.models.User;
import com.ipims.views.Registrationview;

import javafx.scene.Scene;

public class RegistrationViewController {

	Registrationview view;
	
	public RegistrationViewController() {
		view = new Registrationview();
		view.createRegistrationMenu(this);
	}

    public Scene getScene() {
    	return view.getCurrentScene();
    }

    public void handleRegister(User user, String password) {
    	UserSession.getInstance().register(user, password);
    	view.showErrorMessage("User registered. Please login again.");
    	handleBackButton();
    }
    
    public void handleBackButton() {
    	LoginViewController loginVC = new LoginViewController();
    	view.getStage().setScene(loginVC.getScene());
    }
    
    public void handleError() {
    	view.showErrorMessage("Error! Please make sure all available field are filled out.");
    }
    
    public void handleExistingUserError() {
    	view.showErrorMessage("Username already exists");
    }
}

