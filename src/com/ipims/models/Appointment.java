package com.ipims.models;

import java.time.LocalDate;

public class Appointment {
	
	private LocalDate date;
	private String time;
	private String doctor;
	private User patient;
	private String category;
	public Appointment(LocalDate localDate, String time, String doctor, User patient, String category) {
		this.date = localDate;
		this.time = time;
		this.doctor = doctor;
		this.patient = patient;
		this.category = category;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getDoctor() {
		return doctor;
	}
	
	public User getPatient() {
		return patient;
	}
	
	public String getCategory() {
		return category;
	}
}
