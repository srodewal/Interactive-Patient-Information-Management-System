package com.ipims.models;

public class Alert {
	private int alertId = -1;
	private int conditionId;
	private int patientId;
	private boolean read;
	private boolean emergency;
	
	public Alert(int alertId, int conditionId, int patientId, boolean read, boolean emergency)
	{
		this.alertId = alertId;
		this.conditionId = conditionId;
		this.patientId = patientId;
		this.read = read;
		this.emergency = emergency;
	}
	
	public Alert(int conditionId, int patientId, boolean read, boolean emergency)
	{
		this.conditionId = conditionId;
		this.patientId = patientId;
		this.read = read;
		this.emergency = emergency;
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

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isEmergency() {
		return emergency;
	}

	public void setEmergency(boolean emergency) {
		this.emergency = emergency;
	}
}
