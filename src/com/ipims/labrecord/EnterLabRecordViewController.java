package com.ipims.labrecord;

import java.time.LocalDate;

import com.ipims.MenuViewController;
import com.ipims.models.Appointment;
import com.ipims.models.LabRecord;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;
import com.ipims.views.EnterLabRecordView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

public class EnterLabRecordViewController {

	
	private EnterLabRecordView view;

	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
public  EnterLabRecordViewController() {
	view = new EnterLabRecordView();
	view.createEnterLabRecordView(UserSession.getInstance().getCurrentUser(), this);
}

public Scene getScene() {
	return view.getCurrentScene();
}

	public void goBack() {
		 MenuViewController menu = new MenuViewController();
         view.getStage().setScene(menu.getScene());
         
	}
	

	public void didSelectItem(int index) {
		EnterLabRecordView updateView = new EnterLabRecordView();
		updateView.createUpdateEnterLabRecordView(new LabRecord(), this);
		view.getStage().setScene(updateView.getCurrentScene());
		view = updateView;
	}
	
	public ObservableList<String> getLabRecordList () {
		ObservableList<String> items = FXCollections.observableArrayList (
			"Patient:-----\nGlucose Level:-----\nSodium Level:----\nCalcium level:-----\nMagnesium Level:----",
			"Patient:-----\nGlucose Level:-----\nSodium Level:----\nCalcium level:-----\nMagnesium Level:----");
		return items;
	}
	
	public void handleUpdateGoBack() {
		EnterLabRecordView backToAppoinment = new EnterLabRecordView();
		backToAppoinment.createEnterLabRecordView(UserSession.getInstance().getCurrentUser(), this);
		view.getStage().setScene(backToAppoinment.getCurrentScene());
		view = backToAppoinment;
	}
	
	public void handleUpdateClick(LabRecord updatedLabRecord) {
		handleUpdateGoBack();
	}
	
	
	public void handleAppointmentCancellation() {
		handleUpdateGoBack();
	}


	
}
