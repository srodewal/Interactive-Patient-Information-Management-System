package com.ipims.appointment;

import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;

import com.ipims.models.Patient;



public class AppointmentManager {

	public boolean confirmAppointment(Appointment appoinment) {

		if (appoinment != null) {
			return true;
		}
		return false;
	}

	public List<Appointment> getAppointmentForPatient(Patient patient) {
		List<Appointment> appointmentList = null;
		if (patient == null) {
			appointmentList = DatabaseManager.getInstance().getAllAppointments();
		} else {
			appointmentList = DatabaseManager.getInstance().getAppointmentForPatient(patient);
		}

		return appointmentList;
	}

	public boolean scheduleAppointment(Appointment appointment) {
		return false;
	}

	public boolean newAppointment(Appointment appointment) {
		if (appointment.getAppointmentId() == -1) {
			DatabaseManager.getInstance().newAppointment(appointment);
			return true;
		}
		return false;
	}

	public boolean updateAppointment(Appointment oldApp, Appointment newApp) {
		if (oldApp.getAppointmentId() > 0) {
			newApp.setAppointmentId(oldApp.getAppointmentId());
			DatabaseManager.getInstance().updateAppointment(newApp);
			return true;
		}

		return false;
	}

	public boolean deleteAppoinment(Appointment appoinment) {
		if (appoinment.getAppointmentId() != -1) {
			DatabaseManager.getInstance().deleteAppoinment(appoinment);
			return true;
		}
		return false;
	}



}
