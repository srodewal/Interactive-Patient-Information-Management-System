package com.ipims.models;

import java.time.LocalDate;

public class Appointment {
	
	private LocalDate date;
	private String time;
	private User doctor;
	private User patient;
	private String category;
	private int appointmentId = -1;
	
	public Appointment(LocalDate localDate, String time, User doctor, User patient, String category) {
		this.date = localDate;
		this.time = time;
		this.doctor = doctor;
		this.patient = patient;
		this.category = category;
		
	}
	
	public Appointment(int id, LocalDate localDate, String time, User doctor, User patient, String category) {
		this.date = localDate;
		this.time = time;
		this.doctor = doctor;
		this.patient = patient;
		this.category = category;
		this.appointmentId = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
	public User getDoctor() {
		return doctor;
	}
	
	public User getPatient() {
		return patient;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getAppointmentId() {
		return appointmentId;
	}
	
	public void setAppointmentId(int id)
	{
		this.appointmentId = id;
	}
}
