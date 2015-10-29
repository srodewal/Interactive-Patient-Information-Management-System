package com.ipims.patientcase;

import com.ipims.Helper;
import com.ipims.MenuViewController;
import com.ipims.models.Patient;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;
import com.ipims.views.PatientCaseView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

public class PatientCaseViewController {



	private PatientCaseView view;
	private Patient selectedPatient;

	//--------------- View Controller methods ---------------
	//--------------------------------------------------------

	public  PatientCaseViewController() {
		User user = UserSession.getInstance().getCurrentUser();
		view = new PatientCaseView();
		view.createPatientCaseView(user, this);
		
		if (user.getUsertype() == UserType.PATIENT) {
			this.selectedPatient = (Patient)user;
			patientSelected(this.selectedPatient);
		}
	}

	public Scene getScene() {
		return view.getCurrentScene();
	}

	public void goBack() {
		MenuViewController menu = new MenuViewController();
		view.getStage().setScene(menu.getScene());

	}


	public ObservableList<String> patientCaseDetails() {
		ObservableList<String> items = FXCollections.observableArrayList();
		if (this.selectedPatient != null) {
			
		} 
		return items;
	}
	
	
 
	public void patientSelected(Patient patient) {
		this.view.refreshListView("test");
	}




}
