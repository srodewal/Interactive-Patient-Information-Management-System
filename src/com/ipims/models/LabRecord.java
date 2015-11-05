package com.ipims.models;

import java.time.LocalDate;

public class LabRecord {
	
	private int patientId;
	private float Glucose;
	private float Sodium;
	private float Calcium;
	private float Magnesium;
	private int LabRecordId = -1;
	private String Patient;
	private LocalDate date;
	
	
	
	public String getPatient(){
		return Patient;
	}
	
	public void setPatient(String patient)
	{
		Patient = patient;
	}
	
	
	public int getPatientId(){
		return patientId;
	}
	
	public void setPatientId(int id)
	{
		patientId = id;
	}

	public int getLabRecordId() {
		return LabRecordId;
	}

	public void setLabRecordId(int id) {
		this.LabRecordId = id;
	}



	public float getGlucose() {
		return Glucose;
	}



	public void setGlucose(float glucose) {
		Glucose = glucose;
	}



	public float getSodium() {
		return Sodium;
	}



	public void setSodium(float sodium) {
		Sodium = sodium;
	}



	public float getCalcium() {
		return Calcium;
	}



	public void setCalcium(float calcium) {
		Calcium = calcium;
	}



	public float getMagnesium() {
		return Magnesium;
	}



	public void setMagnesium(float magnesium) {
		Magnesium = magnesium;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	

	
}
