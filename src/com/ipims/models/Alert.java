package com.ipims.models;

public class Alert {
	private int alertId = -1;
	private int conditionId;
	private int doctorId;
	private String message;
	private boolean read;
	
	public Alert(int alertId, int conditionId, int doctorId, String message, boolean read)
	{
		this.alertId = alertId;
		this.conditionId = conditionId;
		this.doctorId = doctorId;
		this.message = message;
		this.read = read;
	}
	
	public Alert(int conditionId, int doctorId, String message, boolean read)
	{
		this.conditionId = conditionId;
		this.doctorId = doctorId;
		this.message = message;
		this.read = read;
	}
	
	public int getAlertId() {
		return alertId;
	}
	
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}
	
	public int getConditionId() {
		return conditionId;
	}
	
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}
}
