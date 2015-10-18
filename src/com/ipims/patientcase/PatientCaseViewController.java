package com.ipims.patientcase;

import com.ipims.MenuViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.PatientCaseView;

import javafx.scene.Scene;

public class PatientCaseViewController {

	
	
	private PatientCaseView view;

	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
public  PatientCaseViewController() {
	view = new PatientCaseView();
	view.createPatientCaseView(UserSession.getInstance().getCurrentUser(), this);
}

public Scene getScene() {
	return view.getCurrentScene();
}

	public void goBack() {
		 MenuViewController menu = new MenuViewController();
         view.getStage().setScene(menu.getScene());
         
	}

	
	
	
	
	
	
	
}
