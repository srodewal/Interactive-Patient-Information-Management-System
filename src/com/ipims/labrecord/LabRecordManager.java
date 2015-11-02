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
	
	
	

	public List<LabRecord> getLabRecordForPatient(Patient patient) {

		List<LabRecord> LabRecordList = null;
		int patientid;
		
		if (patient == null) {
			LabRecordList = DatabaseManager.getInstance().getAllLabRecord();
		} else {
			
			patientid = patient.getUserId();
			LabRecordList = DatabaseManager.getInstance().getLabRecordsForPatient(patientid);
		}

		return LabRecordList;
		
		
	}
	
	
	
	public boolean newLabRecord(LabRecord labrecord) {
		if (labrecord.getLabRecordId() == -1) {
			DatabaseManager.getInstance().newLabRecord(labrecord);
			return true;
		}
		return false;
	}
	
	
}
