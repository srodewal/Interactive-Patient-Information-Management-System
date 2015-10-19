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
	
	public List<Appointment> getAllAppointMent(Patient patient) {
		List<Appointment> appointmentList = new ArrayList<>(); 
		
		return appointmentList;
	}

}
