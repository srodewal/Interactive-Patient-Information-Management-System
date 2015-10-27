package com.ipims.models;


public class LabRecord {
	
	private String Patient;
	private int Glucose;
	private int Sodium;
	private int Calcium;
	private int Magnesium;
	private int appointmentId = -1;
	
	
	
	public String getPatient(){
		return Patient;
	}
	
	

	public int getLabRecordId() {
		return appointmentId;
	}

	public void setLabRecordId(int id) {
		this.appointmentId = id;
	}



	public int getGlucose() {
		return Glucose;
	}



	public void setGlucose(int glucose) {
		Glucose = glucose;
	}



	public int getSodium() {
		return Sodium;
	}



	public void setSodium(int sodium) {
		Sodium = sodium;
	}



	public int getCalcium() {
		return Calcium;
	}



	public void setCalcium(int calcium) {
		Calcium = calcium;
	}



	public int getMagnesium() {
		return Magnesium;
	}



	public void setMagnesium(int magnesium) {
		Magnesium = magnesium;
	}
	
	

	
}
