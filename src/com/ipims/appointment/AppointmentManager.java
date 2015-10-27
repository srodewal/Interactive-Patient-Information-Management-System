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
		List<Appointment> appointmentList = new ArrayList<>(); 
		
		return appointmentList;
	}
	
	public boolean scheduleAppointment(Appointment appointment) {
		return false;
	}
	
	public boolean updateAppointment(Appointment appointment) {
		return false;
	}
	
	public static List<String> getAllDoctors() {
		List<Doctor> docList = DatabaseManager.getInstance().getAllDoctors();
		List<String> list = new ArrayList<>(); 
		for (Doctor doc : docList){
			list.add(doc.getName());
		}
		
		return list;
	}
	
	public static List<String> getAllPatients() {
		List<User> patientList = DatabaseManager.getInstance().getPatientList();
		List<String> list = new ArrayList<>(); 
		for (User user : patientList){
			list.add(user.getName());
		}
		return list;
	}
	
	public static List<String> getAllCategories() {
		List<String> list = new ArrayList<>(); 
		list.add("Heart");
		list.add("Eye");
		list.add("Ortho");
		
		return list;
	}
	
	public static Patient getPatientAtIndex(int index) {
		List<User> patientList = DatabaseManager.getInstance().getPatientList();
		return (Patient)patientList.get(index);
	}
	
	public static Doctor getDoctorAtIndex(int index) {
		List<Doctor> docList = DatabaseManager.getInstance().getAllDoctors();
		return (Doctor)docList.get(index);
	}
	

}
