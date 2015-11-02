package com.ipims.labrecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ipims.MenuViewController;
import com.ipims.appointment.AppointmentManager;
import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;
import com.ipims.models.LabRecord;
import com.ipims.models.Patient;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;
import com.ipims.views.EnterLabRecordView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

public class EnterLabRecordViewController {

	
	private EnterLabRecordView view;
	private LabRecordManager labManager;
	private LabRecord currentlySelectedLab;

	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
public  EnterLabRecordViewController() {
	labManager = new LabRecordManager();
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
	

	public void didSelectItem(int index, int patientid) {
		
		User user = UserSession.getInstance().getCurrentUser();

		List<LabRecord> list = getListOfLabRecords(patientid);

		
		if (user.getUsertype() == UserType.LABSTAFF) {
			
		
		EnterLabRecordView updateView = new EnterLabRecordView();
		currentlySelectedLab = list.get(index);
		updateView.createUpdateEnterLabRecordView(new LabRecord(), this);
		view.getStage().setScene(updateView.getCurrentScene());
		view = updateView;
		
	}
	}

	

	public ObservableList<String> getPatientLabRecordList (int patientid) {

		List<LabRecord> LabRecordList = getListOfLabRecords(patientid);
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
	

	private List<LabRecord>getListOfLabRecords(int patientid) {

		// Pass patientid in order to get all lab records for that patient 

		List<LabRecord> LabRecordList = null;
		
			LabRecordList = labManager.getLabRecordsForPatient(patientid);
		
		return LabRecordList;
		
	}



	
	public void handleUpdateGoBack() {
		EnterLabRecordView backToAppoinment = new EnterLabRecordView();
		backToAppoinment.createEnterLabRecordView(UserSession.getInstance().getCurrentUser(), this);
		view.getStage().setScene(backToAppoinment.getCurrentScene());
		view = backToAppoinment;
	}
	

	public void handleUpdateClick(LabRecord updatedLabRecord) {
		
		labManager.updateLabRecord(currentlySelectedLab, updatedLabRecord);
		currentlySelectedLab = null;
		handleUpdateGoBack();
		view.showErrorMessage("LabRecord updated.");
		
	}
	
	public void handleLabRecordDeletion() {
		handleUpdateGoBack();
	}

	public void handleSubmitClick(LabRecord newLabRecord) {
		labManager.newLabRecord(newLabRecord);
	}


	
}

