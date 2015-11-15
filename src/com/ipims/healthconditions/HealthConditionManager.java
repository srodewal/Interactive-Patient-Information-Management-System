package com.ipims.healthconditions;

import java.util.ArrayList;
import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Alert;
import com.ipims.models.HealthCondition;
import com.ipims.models.Patient;

/**
 * Manages the health condition of patient.
 * 
 * @author jithin
 *
 */
public class HealthConditionManager {


	public void prescribeMedicine(Patient patient, String medicine) {

	}

	////////////////////////////////////////////////////////////////////////
	///////////////// HEALTH CONDITION METHODS
	////////////////////////////////////////////////////////////////////////
	
	/**
	 * Sends an alert to the doctor/hsp when severe health condition is reported.
	 * @param condition
	 */
	private void checkForSeverityAndSendAlert(HealthCondition condition) {
		if (condition.isCurrent()) {
			if(condition.getSeverity() > 4) {
				
				Alert alert = new Alert(condition.getHealthConditionId(), condition.getPatientId(), false, false);
				DatabaseManager.getInstance().newAlert(alert);
				
			} else if (condition.getHealthConcern().equals("Emergency")) {
				
				Alert alert = new Alert(condition.getHealthConditionId(), condition.getPatientId(), false, true);
				DatabaseManager.getInstance().newAlert(alert);
			}
		}
		
	}
	
	/**
	 * Delete the health condition.
	 * @param condition
	 */
	public void deleteHealthCondition(HealthCondition condition) {
		if (condition != null && condition.getHealthConditionId() >= 0) {
			DatabaseManager.getInstance().deleteHealthCondition(condition);
		}
		
	}
	
	/**
	 * Updates the health condition.
	 * @param oldCondition
	 * @param updatedCondition
	 */
	public void updateHealthCondition(HealthCondition oldCondition, HealthCondition updatedCondition) {
		if (oldCondition != null && oldCondition.getHealthConditionId() > -1) {
			updatedCondition.setHealthConditionId(oldCondition.getHealthConditionId());
			DatabaseManager.getInstance().updateHealthCondition(updatedCondition);
			
			if (oldCondition.getHealthConcern().equals(updatedCondition.getHealthConcern()) == false) {
				checkForSeverityAndSendAlert(updatedCondition);
			}
		}
	}

	/**
	 * Saves the health condition to db. And calls to check if its severe.
	 * @param patient
	 * @param condition
	 */
	public void saveHealthCondition(HealthCondition condition) {

		HealthCondition hc = DatabaseManager.getInstance().newHealthCondition(condition);
		checkForSeverityAndSendAlert(hc);
	}

	/**
	 * List of Health Condition/History of a patient
	 * @return
	 */
	public List<HealthCondition> getPatientHealthList(Patient patient) {
		List<HealthCondition> list = DatabaseManager.getInstance().getPatientConditions(patient);
		return list;
	}

	/**
	 * List of predefined Health History
	 * @return
	 */
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
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Nausea");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Ear ache");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Abdominal Pain");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Coughing");
		list.add(healthCondition);

                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Blood Clots");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Fatigue");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Gas");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Headache");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Neck Pain");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Influenza");
		list.add(healthCondition);
		return list;
	}

	/**
	 * List of predefined Health Condition
	 * @return
	 */
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

		healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
		healthCondition.setSeverity(1);
		healthCondition.setHealthConcern("Emergency");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(4);
		healthCondition.setHealthConcern("Skin Problems");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(2);
		healthCondition.setHealthConcern("Nausea");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(4);
		healthCondition.setHealthConcern("Ear ache");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(3);
		healthCondition.setHealthConcern("Abdominal Pain");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(false);
		healthCondition.setHealthConcern("Coughing");
		list.add(healthCondition);

                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(1);
		healthCondition.setHealthConcern("Blood Clots");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(1);
		healthCondition.setHealthConcern("Fatigue");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(5);
		healthCondition.setHealthConcern("Gas");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(3);
		healthCondition.setHealthConcern("Headache");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(4);
		healthCondition.setHealthConcern("Neck Pain");
		list.add(healthCondition);
                
                healthCondition = new HealthCondition();
		healthCondition.setCurrent(true);
                healthCondition.setSeverity(3);
		healthCondition.setHealthConcern("Influenza");
		list.add(healthCondition);

		return list;
	}

	/**
	 * Get the Health Condition at index from the predefined list.
	 * @param index Index starting from 0.
	 * @return HealthCondition
	 */
	public static HealthCondition healthConditionAtIndex (int index) {
		return getHealthConditions().get(index);
	}

	/**
	 * Get the Health History at index from the predefined list.
	 * @param index Index starting from 0.
	 * @return HealthHistory
	 */
	public static HealthCondition healthHistoryAtIndex (int index) {
		return getHealthHistoryList().get(index);
	}
}
