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

    public void handleRegister(User user) {
    	UserSession.getInstance().register(user);
    	//view.showErrorMessage("This wont work without database setup");
    }
    
    public void handleBackButton() {
    	LoginViewController loginVC = new LoginViewController();
    	view.getStage().setScene(loginVC.getScene());
    }
    
    public void handleError() {
    	view.showErrorMessage("Error! Please make sure all available field are filled out.");
    }
}
