package com.ipims.models;

public class HealthCondition {

	private int healthConditionId;
	private String healthConcern;
	private String comments;
	private int severity;
	private boolean current;
	
	public int getHealthConditionId() {
		return healthConditionId;
	}
	
	public void setHealthConditionId(int healthConditionId) {
		this.healthConditionId = healthConditionId;
	}
	
	public String getHealthConcern() {
		return healthConcern;
	}
	
	public void setHealthConcern(String healthConcern) {
		this.healthConcern = healthConcern;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public int getSeverity() {
		return severity;
	}
	
	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean pastOrCurrent) {
		this.current = pastOrCurrent;
	}
	
}
