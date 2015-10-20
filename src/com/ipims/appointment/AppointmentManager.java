package com.ipims.appointment;

import java.util.ArrayList;
import java.util.List;

import com.ipims.models.Appointment;
import com.ipims.models.Patient;

public class AppointmentManager {
	
	public boolean confirmAppointment(Appointment appoinment) {
		
		if (appoinment != null) {
			return true;
		}
		return false;
	}
	
	public List<Appointment> listAppointmentForPatient(Patient patient) {
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
	
	public static List<Patient> listAllDoctors() {
		List<Patient> list = new ArrayList<>(); 
		
		return list;
	}
	
	public static List<Patient> listAllCategories() {
		List<Patient> list = new ArrayList<>(); 
		
		return list;
	}

}
