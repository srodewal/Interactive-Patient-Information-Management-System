package com.ipims.healthconditions;

import com.ipims.database.DatabaseManager;
import com.ipims.models.HealthCondition;
import com.ipims.models.Patient;

public class HealthConditionManager {

	public void updateHealth(Patient patient, HealthCondition condition) {
		DatabaseManager.getInstance().newHealthCondition(condition);
		System.out.println("Sent to database!");
	}
	
	public void viewHealth(Patient patient) {
		
	}
	
	public void prescribeMedicine(Patient patient, String medicine) {
		
	}
	
	public void sendAlert() {
		
	}
}
