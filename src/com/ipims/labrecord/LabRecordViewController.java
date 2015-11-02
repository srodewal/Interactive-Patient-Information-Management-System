package com.ipims.labrecord;

import java.util.ArrayList;
import java.util.List;

import com.ipims.MenuViewController;
import com.ipims.database.DatabaseManager;
import com.ipims.models.LabRecord;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;
import com.ipims.views.EnterLabRecordView;
import com.ipims.views.LabRecordView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

public class LabRecordViewController {

	private LabRecordView view;
	private LabRecordManager labManager;
	private LabRecord currentlySelectedLab;

	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
public  LabRecordViewController() {
	view = new LabRecordView();
	view.createLabRecordView(UserSession.getInstance().getCurrentUser(), this);
	labManager = new LabRecordManager();

}

public Scene getScene() {
	return view.getCurrentScene();
}

	public void goBack() {
		 MenuViewController menu = new MenuViewController();
         view.getStage().setScene(menu.getScene());
         
	}
	
	
	public void didSelectItem(int index) {
		
		User user = UserSession.getInstance().getCurrentUser();

		List<LabRecord> list = getListOfLabRecords();

		
		if (user.getUsertype() == UserType.PATIENT || 
				user.getUsertype() == UserType.HSPSTAFF) {
			
		
		LabRecordView updateView = new LabRecordView();
		currentlySelectedLab = list.get(index);
		updateView.createUpdateLabRecordView(new LabRecord(), this);
		view.getStage().setScene(updateView.getCurrentScene());
		view = updateView;
		
	}
	}

	public ObservableList<String> getLabRecordList () {

		List<LabRecord> LabRecordList = getListOfLabRecords();
		List<String> stringLabList = new ArrayList<>();
		for (int i = 0; i < LabRecordList.size(); i++) {
			LabRecord lab = LabRecordList.get(i);
			int index = i+1;
			
			String str = "" + index +". ";
			str+= "Name: "+ DatabaseManager.getInstance().getUser(lab.getPatientId()).getName()+ "\nCalcium: "+ lab.getCalcium()+"\nGlucose: "+ lab.getGlucose()+"\nMagnesium:  "+ lab.getMagnesium()+"\nSodium:  "+ lab.getSodium();
			stringLabList.add(str);
		}
		ObservableList<String> items = FXCollections.observableArrayList (stringLabList);
		return items;
	}

	
	private List<LabRecord>getListOfLabRecords() {

		// If patient logs in, get the list of appointments for that patient
		// If HSP, Nurse or Doctor logs in, get the list all appointments
		//
		List<LabRecord> LabRecordList = null;
		
	   
			LabRecordList = labManager.getLabRecordForPatient(null);
		
		return LabRecordList;
		
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

