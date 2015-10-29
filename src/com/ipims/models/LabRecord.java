package com.ipims.models;


public class LabRecord {
	
	private int patientId;
	private float Glucose;
	private float Sodium;
	private float Calcium;
	private float Magnesium;
	private int appointmentId = -1;
	
	
	
	public int getPatientId(){
		return patientId;
	}
	
	public void setPatientId(int id)
	{
		patientId = id;
	}

	public int getLabRecordId() {
		return appointmentId;
	}

	public void setLabRecordId(int id) {
		this.appointmentId = id;
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
	
	

	
}
