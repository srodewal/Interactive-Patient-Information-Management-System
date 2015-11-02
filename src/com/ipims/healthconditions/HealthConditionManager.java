package com.ipims.healthconditions;

import java.util.ArrayList;
import java.util.List;

import com.ipims.database.DatabaseManager;
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
		if (condition.isCurrent() && condition.getSeverity() > 4) {

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
	
	public void updateHealthCondition(HealthCondition oldCondition, HealthCondition updatedCondition) {
		if (oldCondition != null && oldCondition.getHealthConditionId() > -1) {
			DatabaseManager.getInstance().updateHealthCondition(oldCondition, updatedCondition);
		}
	}

	/**
	 * Saves the health condition to db. And calls to check if its severe.
	 * @param patient
	 * @param condition
	 */
	public void saveHealthCondition(HealthCondition condition) {

		DatabaseManager.getInstance().newHealthCondition(condition);
		System.out.println("Sent to database!");
		checkForSeverityAndSendAlert(condition);
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
