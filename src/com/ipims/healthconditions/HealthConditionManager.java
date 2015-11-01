package com.ipims.healthconditions;

import java.util.ArrayList;
import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.models.HealthCondition;
import com.ipims.models.Patient;

public class HealthConditionManager {
	
	public void viewHealth(Patient patient) {
		
	}
	
	public void prescribeMedicine(Patient patient, String medicine) {
		
	}
	
	public void sendAlert() {
		
	}
	
	public void saveHealthCondition(Patient patient, HealthCondition condition) {
		
		DatabaseManager.getInstance().newHealthCondition(condition, patient);
		System.out.println("Sent to database!");
	}
	
	public List<HealthCondition> getPatientHealthList(Patient patient) {
		List<HealthCondition> list = DatabaseManager.getInstance().getPatientConditions(patient);
		
		return list;
	}
	
	public static List<HealthCondition> getHealthHistoryList () {
		 List<HealthCondition> list = new ArrayList<>();
		 
		 HealthCondition healthCondition = new HealthCondition();
		 healthCondition.setCurrent(false);
		 healthCondition.setHealthConcern("Allergies");
		 list.add(healthCondition);
		 
		 healthCondition = new HealthCondition();
		 healthCondition.setCurrent(false);
		 healthCondition.setHealthConcern("Chest Pain");
		 list.add(healthCondition);
		 
		 healthCondition = new HealthCondition();
		 healthCondition.setCurrent(false);
		 healthCondition.setHealthConcern("Heart Problems");
		 list.add(healthCondition);
		 
		 healthCondition = new HealthCondition();
		 healthCondition.setCurrent(false);
		 healthCondition.setHealthConcern("Diabetes");
		 list.add(healthCondition);
		 
		 healthCondition = new HealthCondition();
		 healthCondition.setCurrent(false);
		 healthCondition.setHealthConcern("Skin Problems");
		 list.add(healthCondition);
		 
		 return list;
	}
	
	public static List<HealthCondition> getHealthConditions () {
		 List<HealthCondition> list = new ArrayList<>();
		 
		 HealthCondition healthCondition = new HealthCondition();
		 healthCondition.setCurrent(true);
		 healthCondition.setSeverity(2);
		 healthCondition.setHealthConcern("Chest Pain");
		 list.add(healthCondition);
		 
		 healthCondition = new HealthCondition();
		 healthCondition.setCurrent(true);
		 healthCondition.setSeverity(5);
		 healthCondition.setHealthConcern("Heart Pain");
		 list.add(healthCondition);
		 
		 healthCondition = new HealthCondition();
		 healthCondition.setCurrent(true);
		 healthCondition.setSeverity(2);
		 healthCondition.setHealthConcern("Diabetes");
		 list.add(healthCondition);
		 
		 
		 return list;
	}
	
	
	public static HealthCondition healthConditionAtIndex (int index) {
		return getHealthConditions().get(index);
	}
	
	public static HealthCondition healthHistoryAtIndex (int index) {
		return getHealthHistoryList().get(index);
	}
}
