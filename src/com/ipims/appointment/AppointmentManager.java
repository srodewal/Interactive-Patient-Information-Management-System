package com.ipims.appointment;

import java.util.ArrayList;
import java.util.List;

import com.ipims.models.Appointment;
import com.ipims.models.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentManager {
	
	public boolean confirmAppointment(Appointment appoinment) {
		
		if (appoinment != null) {
			return true;
		}
		return false;
	}
	
	public List<Appointment> getAppointmentForPatient(Patient patient) {
		List<Appointment> appointmentList = new ArrayList<>(); 
		
		return appointmentList;
	}
	
	public boolean scheduleAppointment(Appointment appointment) {
		return false;
	}
	
	public boolean updateAppointment(Appointment appointment) {
		return false;
	}
	
	public static List<Patient> listAllPatients() {
		List<Patient> list = new ArrayList<>(); 
		
		return list;
	}
	
	public static List<String> getAllDoctors() {
		List<String> list = new ArrayList<>(); 
		list.add("John");
		list.add("Smith");
		return list;
	}
	
	public static List<String> getAllCategories() {
		List<String> list = new ArrayList<>(); 
		list.add("Heart");
		list.add("Eye");
		list.add("Ortho");
		
		return list;
	}
	

}
