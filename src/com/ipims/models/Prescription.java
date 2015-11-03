package com.ipims.models;


public class Prescription {

	private int userId;
	private int prescriptionId;
	private String date;
	private String prescriptionText;
	private boolean current;
	private boolean medicine;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrescriptionText() {
		return prescriptionText;
	}
	public void setPrescriptionText(String prescriptionText) {
		this.prescriptionText = prescriptionText;
	}
	public boolean isCurrent() {
		return current;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
	public boolean isMedicine() {
		return medicine;
	}
	public void setMedicine(boolean medicine) {
		this.medicine = medicine;
	}
}
