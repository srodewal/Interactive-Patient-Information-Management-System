package com.ipims.appointment;

import java.util.ArrayList;
import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;
import com.ipims.models.Doctor;
import com.ipims.models.Patient;
import com.ipims.models.User;


public class AppointmentManager {
	
	public boolean confirmAppointment(Appointment appoinment) {
		
		if (appoinment != null) {
			return true;
		}
		return false;
	}
	
	public List<Appointment> getAppointmentForPatient(Patient patient) {
		List<Appointment> appointmentList = DatabaseManager.getInstance().getAppointmentForPatient(patient); 
		return appointmentList;
	}
	
	public boolean scheduleAppointment(Appointment appointment) {
		return false;
	}
	
	public boolean updateAppointment(Appointment appointment) {
		return false;
	}
	
	
	

}
