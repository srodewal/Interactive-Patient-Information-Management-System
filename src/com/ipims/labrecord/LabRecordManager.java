package com.ipims.labrecord;

import java.util.ArrayList;
import java.util.List;

import com.ipims.Helper;
import com.ipims.appointment.AppointmentManager;
import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;
import com.ipims.models.LabRecord;
import com.ipims.models.Patient;
import com.ipims.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class LabRecordManager {
	
	
	

	public List<LabRecord> getLabRecordsofPatient(Patient patient) {
		
		List<LabRecord> labRecordList = new ArrayList<>(); 

	// pulledLabRecord = DatabaseManager.getInstance().getLabRecord(patient);
		
		return labRecordList;

	}
	
	public void EnterLabRecord(Patient patient) {
		
		
		 //  DatabaseManager.getInstance().EnterLabRecord(patient);
			
			
	}
	
	public void UpdateLabRecord(Patient patient) {
		
     
	 //  DatabaseManager.getInstance().UpdateLabRecord(patient);
		
		
	}
	
	public static List<String> getAllPatients() {
		List<User> patientList = DatabaseManager.getInstance().getPatientList();
		List<String> list = new ArrayList<>(); 
		for (User user : patientList){
			list.add(user.getName());
		}
		return list;
	}
	
	public ObservableList<String> getPatientList() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll(Helper.getAllPatients());
		return items;
	}
	
	
}
