package com.ipims.appointment;

import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;

import com.ipims.models.Patient;


/**
 * Manages appointment creation and modification.
 * @author jithin
 *
 */
public class AppointmentManager {

	/**
	 * Get all the appointment for the patient.
	 * @param patient
	 * @return Return the list of appointments
	 */
	public List<Appointment> getAppointmentForPatient(Patient patient) {
		List<Appointment> appointmentList = null;
		if (patient == null) {
			appointmentList = DatabaseManager.getInstance().getAllAppointments();
		} else {
			appointmentList = DatabaseManager.getInstance().getAppointmentForPatient(patient);
		}

		return appointmentList;
	}

	/**
	 * Create a new appointment.
	 * @param appointment
	 * @return
	 */
	public boolean newAppointment(Appointment appointment) {
		if (appointment.getAppointmentId() == -1) {
			DatabaseManager.getInstance().newAppointment(appointment);
			return true;
		}
		return false;
	}

	/**
	 * Update the appointment with the new value.
	 * @param oldApp
	 * @param newApp
	 * @return
	 */
	public boolean updateAppointment(Appointment oldApp, Appointment newApp) {
		if (oldApp.getAppointmentId() > 0) {
			newApp.setAppointmentId(oldApp.getAppointmentId());
			DatabaseManager.getInstance().updateAppointment(newApp);
			return true;
		}

		return false;
	}

	/**
	 * Delete the appointment.
	 * @param appoinment
	 * @return
	 */
	public boolean deleteAppoinment(Appointment appoinment) {
		if (appoinment.getAppointmentId() != -1) {
			DatabaseManager.getInstance().deleteAppoinment(appoinment);
			return true;
		}
		return false;
	}



}
