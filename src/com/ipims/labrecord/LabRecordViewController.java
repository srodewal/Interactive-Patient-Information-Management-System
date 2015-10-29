package com.ipims.labrecord;

import com.ipims.MenuViewController;
import com.ipims.models.LabRecord;
import com.ipims.usersession.UserSession;
import com.ipims.views.EnterLabRecordView;
import com.ipims.views.LabRecordView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

public class LabRecordViewController {

	private LabRecordView view;

	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
public  LabRecordViewController() {
	view = new LabRecordView();
	view.createLabRecordView(UserSession.getInstance().getCurrentUser(), this);
}

public Scene getScene() {
	return view.getCurrentScene();
}

	public void goBack() {
		 MenuViewController menu = new MenuViewController();
         view.getStage().setScene(menu.getScene());
         
	}
	
	
	public void didSelectItem(int index) {
		LabRecordView updateView = new LabRecordView();
		updateView.createUpdateLabRecordView(new LabRecord(), this);
		view.getStage().setScene(updateView.getCurrentScene());
		view = updateView;
	}
	
	public ObservableList<String> getLabRecordList () {
		ObservableList<String> items = FXCollections.observableArrayList (
			
			// Get Lab Record values from data base	when choosing patient name
				
				
			"Patient:-----\nGlucose Level:-----\nSodium Level:----\nCalcium level:-----\nMagnesium Level:----",
			"Patient:-----\nGlucose Level:-----\nSodium Level:----\nCalcium level:-----\nMagnesium Level:----");
		
		return items;
	}
	
	
	public void handleUpdateGoBack() {
		LabRecordView backToAppoinment = new LabRecordView();
		backToAppoinment.createLabRecordView(UserSession.getInstance().getCurrentUser(), this);
		view.getStage().setScene(backToAppoinment.getCurrentScene());
		view = backToAppoinment;
	}
	
	public void handleUpdateClick(LabRecord updatedLabRecord) {
		handleUpdateGoBack();
	}
	
	
	public void handleLabRecordDeletion() {
		handleUpdateGoBack();
	}

	
	
	
	
}

