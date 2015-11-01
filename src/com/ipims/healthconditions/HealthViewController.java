package com.ipims.healthconditions;

import java.util.ArrayList;
import java.util.List;

import com.ipims.Helper;
import com.ipims.MenuViewController;
import com.ipims.models.HealthCondition;
import com.ipims.models.Patient;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;
import com.ipims.views.HealthView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

public class HealthViewController {

	private HealthView view;
	private HealthConditionManager manager;
	private Patient currentlySelectedPerson;
	
	public HealthViewController() {
		this.manager = new HealthConditionManager();
		User user = UserSession.getInstance().getCurrentUser();
		if (user.getUsertype() == UserType.PATIENT) {
			this.currentlySelectedPerson = (Patient)user;
		}
		this.view = new HealthView();
		this.view.createHealthview(UserSession.getInstance().getCurrentUser(), this);
	}
	
	public Scene getScene() {
		return this.view.getCurrentScene();
	}


	public void goBack() {
		MenuViewController menu = new MenuViewController();
		this.view.getStage().setScene(menu.getScene());

	}

	public   List<String> getHealthHistoryObsList() {
		List<String> items = new ArrayList<>();
		List<HealthCondition> healthList = HealthConditionManager.getHealthHistoryList();
		for (HealthCondition healthCondition : healthList) {
			items.add(healthCondition.getHealthConcern());
		}
		return items;
	}

	public  List<String> getHealthConditionObsList() {
		List<String> items = new ArrayList<>();
		List<HealthCondition> healthList = HealthConditionManager.getHealthConditions();
		for (HealthCondition healthCondition : healthList) {
			items.add(healthCondition.getHealthConcern());
		}

		return items;
	}

	public ObservableList<String> getWholeHealthConditionObsList () {

		List<HealthCondition> list = manager.getPatientHealthList(this.currentlySelectedPerson);
		List<String> resultList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			HealthCondition app = list.get(i);
			int index = i+1;
			
			String str = "" + index +".";
			if (app.isCurrent()) {
				str += " (HealthCondition) : ";
			} else {
				str += " (History) : ";
			}
			str+= app.getHealthConcern()+" Comments: "+ app.getComments();
			resultList.add(str);
		}
		ObservableList<String> items = FXCollections.observableArrayList (resultList);
		return items;
	}
	
	public void patientAtIndexSelected(int index) {
		this.currentlySelectedPerson = Helper.getPatientAtIndex(index);
		this.view.refreshList(getWholeHealthConditionObsList());
	}
	

	public void handleSubmitHc(HealthCondition newHc) {
		manager.saveHealthCondition(this.currentlySelectedPerson, newHc);
		this.view.showErrorMessage("Condition saved.");
		this.view.refreshList(getWholeHealthConditionObsList());
	}




}

