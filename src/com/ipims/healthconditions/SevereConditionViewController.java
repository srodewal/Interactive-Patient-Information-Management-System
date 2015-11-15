package com.ipims.healthconditions;



import java.util.ArrayList;
import java.util.List;

import com.ipims.MenuViewController;
import com.ipims.database.DatabaseManager;
import com.ipims.models.Alert;
import com.ipims.models.Patient;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;

import com.ipims.views.SevereConditionView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

public class SevereConditionViewController {

	private SevereConditionView view;
	
	
	public  SevereConditionViewController() {
		
		view = new SevereConditionView();
		String text = "Severe Cases";
		if (UserSession.getInstance().getCurrentUser().getUsertype() == UserType.DOCTOR) {
			text = "Emergency cases";
		} 
		view.createSevereConditionView(text, this);

	}
	public Scene getScene() {
		return this.view.getCurrentScene();
	}


	public void goBack() {
		MenuViewController menu = new MenuViewController();
		this.view.getStage().setScene(menu.getScene());
	}
	
	public  ObservableList<String> alertObsList() {
		List<Alert> alerts = getAllAlertsForLoggedInUser();
		List<String> alertList = new ArrayList<>();
		for (Alert alert : alerts) {
			
			if(alert.isRead() == false) {
				String text = "";
				Patient patient = (Patient)DatabaseManager.getInstance().getUser(alert.getPatientId());
				
				if (UserSession.getInstance().getCurrentUser().getUsertype() == UserType.DOCTOR) {
					
					text = "Patient: " + patient.getName() + " need emergency care.";
				} else {
					text = "Patient: " + patient.getName() + " has added a severe condition.";
				}
				alertList.add(text);
			}
			
		}
		ObservableList<String> items = FXCollections.observableArrayList (alertList);
		return items;
	}
	
	private List<Alert> getAllAlertsForLoggedInUser() {
		List<Alert> alerts = null;
		if (UserSession.getInstance().getCurrentUser().getUsertype() == UserType.DOCTOR) {
			alerts = DatabaseManager.getInstance().getAllEmergencyAlerts();
		} else {
			alerts = DatabaseManager.getInstance().getAllSevereAlerts();
		}
		return alerts;
	}
	
	public void handleMarkRead(int index) {
		
		if(index >= 0) {
			
			List<Alert> alerts = getAllAlertsForLoggedInUser();
			Alert alert = alerts.get(index);
			System.out.println("going to mark read" + alert.getPatientId() +" " + alert.getAlertId());
			DatabaseManager.getInstance().markAlertRead(alert.getAlertId());
			view.refreshList(alertObsList());
		}
		
	}
}
