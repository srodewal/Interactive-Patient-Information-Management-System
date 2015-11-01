package com.ipims.patientcase;

import java.util.List;

import com.ipims.Helper;
import com.ipims.MenuViewController;
import com.ipims.healthconditions.HealthConditionManager;
import com.ipims.labrecord.LabRecordManager;
import com.ipims.models.HealthCondition;
import com.ipims.models.LabRecord;
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
		HealthConditionManager healthManager = new HealthConditionManager();
		LabRecordManager labRecordManager = new LabRecordManager();

		if (this.selectedPatient != null) {
			List<HealthCondition> healthList = healthManager.getPatientHealthList(selectedPatient);
			List<LabRecord> recordList = labRecordManager.getLabRecordForPatient(selectedPatient);

			String healthConditionInfo = "Health Condition: \n";
			String healthHistoryInfo = "Health History: \n";
			String labRecordInfo = "Lab Records: \n";

			for (HealthCondition condition : healthList) {
				if (condition.isCurrent()) {
					healthConditionInfo += "  -"+ condition.getHealthConcern() + " || Comments: " + condition.getComments();
					healthConditionInfo += "\n";
				} else {
					healthHistoryInfo += "  -"+ condition.getHealthConcern() + " || Comments: " + condition.getComments();
					healthHistoryInfo += "\n";
				}
			}

			for (LabRecord labrecord : recordList) {
				labRecordInfo += "  - Magnesium: " + labrecord.getMagnesium() 
				+ " Calcium: " + labrecord.getCalcium()
				+ " Glucose: "+ labrecord.getGlucose()
				+ " Sodium: "+ labrecord.getSodium();
				labRecordInfo += "\n";	
			}
			
			String patientTitle = "Patient name: " + selectedPatient.getName() + " DOB: " + selectedPatient.getDateOfBirth();
			
			items.add(patientTitle);
			items.add(" ");
			
			items.add(healthConditionInfo);
			items.add(" ");
			items.add(healthHistoryInfo);
			items.add(" ");

			items.add(labRecordInfo);
			items.add(" ");
		} 
		return items;
	}



	public void patientSelected(Patient patient) {
		this.selectedPatient = patient;
		view.refreshList(patientCaseDetails());
	}




}
