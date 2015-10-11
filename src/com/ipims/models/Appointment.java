package com.ipims.models;

public class Appointment {
	
	private String date;
	private String time;
	private String doctor;
	private User patient;
	private String category;
	public Appointment(String date, String time, String doctor, User patient, String category) {
		this.date = date;
		this.time = time;
		this.doctor = doctor;
		this.patient = patient;
		this.category = category;
	}
	
	public String getDate() {
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
