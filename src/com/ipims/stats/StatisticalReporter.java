package com.ipims.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;
import com.ipims.models.LabRecord;
import com.ipims.models.User;
import com.ipims.models.Patient;


public class StatisticalReporter {

	//This function retrieves the lab record information from each patient that has a lab record,
	//and determines which percent of patients fall into the healthy or abnormal categories
	public List<String> analyzeHealth() {
		int inRangeCA = 0, inRangeNA = 0, inRangeMG = 0, inRangeGL = 0, total = 0;
		float caPercent = 0, naPercent = 0, mgPercent = 0, glPercent = 0;
		String calcium, sodium, magnesium, glucose;
		List<LabRecord> patientNumList = null;
		patientNumList = DatabaseManager.getInstance().getAllLabRecord();

		for(LabRecord record: patientNumList) {
			total++;
			if (8.5 < record.getCalcium() && record.getCalcium() < 10.2) {
				inRangeCA++;
			}

			if (135 <= record.getSodium() && record.getSodium() <= 145) {
				inRangeNA++;
			}

			if (1.5 <= record.getMagnesium() && record.getMagnesium() <= 2.5) {
				inRangeMG++;
			}

			if (70 <= record.getGlucose() && record.getGlucose() <= 115) {
				inRangeGL++;
			}
		}

		caPercent = (float) inRangeCA / total;
		naPercent = (float) inRangeNA / total;
		mgPercent = (float) inRangeMG / total;
		glPercent = (float) inRangeGL / total;

		calcium = "The percent of patients with normal calcium is: " + Float.toString(caPercent);
		sodium = "The percent of patients with normal sodium is: " + Float.toString(naPercent);
		magnesium = "The percent of patients with normal magnesium is: " + Float.toString(mgPercent);
		glucose = "The percent of patients with normal glucose is: " + Float.toString(glPercent);

		List<String> healthReport = Arrays.asList(calcium, sodium, magnesium, glucose);

		return healthReport;
	}

	//This function analyzes the admission rate, which we defined only as the total number of registered patients.
	//It goes into the database, gets the total number of patients, and reports it in the User Interface.
	public List<String> analyzeAdmissionRate() {
		int number_of_patients = 0;
		String output_number = null;

		number_of_patients = DatabaseManager.getInstance().getNumberOfRegisteredPatients();
		output_number = "The admission rate is calculated as the total number of registered patients, which is: " + Integer.toString(number_of_patients);

		List<String> admissionRate = Arrays.asList(output_number);

		return admissionRate;

	}

	public void analyzeTypeOfPatients() {
		int allergyCount = 0;
		int chestCount = 0;
		int heartCount = 0;
		int diabeticCount = 0;
		int skinCount = 0;

		String allergy, chestPain, heartProb, diabetic, skinProb;

		//List<Patient> typeList = null;
		//typeList = DatabaseManager.getInstance().getAllPatients();

		//for(Patient patient: typeList)
		//{

		//}

		//List<String> typeOfPatients = Arrays.alist();
		//return typeOfPatients;
	}

	public List<String> analyzePatientPopulation() {
		int caucasionCount= 0;
		int africanAmericanCount = 0;
		int indianCount = 0;
		int pacificCount = 0;
		int hispanicCount = 0;
		int otherCount = 0;
		int maleCount = 0;
		int femaleCount = 0;

		String caucasian, african, indian, pac, hisp, other, male, female;

		List<Patient> patientList = null;
		patientList = DatabaseManager.getInstance().getAllPatients();

		for(Patient patient: patientList)
		{
			if(patient.getRace() == "Caucasian")
			{
				caucasionCount++;
			}
			if(patient.getRace() == "African American")
			{
				africanAmericanCount++;
			}
			if(patient.getRace() == "American Indian")
			{
				indianCount++;
			}
			if(patient.getRace() == "Pacific Islander")
			{
				pacificCount++;
			}
			if(patient.getRace() == "Hispanic")
			{
				hispanicCount++;
			}
			if(patient.getRace() == "Other")
			{
				otherCount++;
			}
			if(patient.getSex() == "Male")
			{
				maleCount++;
			}
			if(patient.getSex() == "Female")
			{
				femaleCount++;
			}
		}

		caucasian = "Number of Caucasian Patients: " + caucasionCount;
		african = "Number of African American Patients: " + africanAmericanCount;
		indian = "Number of American Indian Patients: " + indianCount;
		pac = "Number of Pacific Islander Patients: " + pacificCount;
		hisp = "Number of Hispanic Patients: " + hispanicCount;
		other = "Number of Patients that selected Other: " + otherCount;
		male = "Number of Male Patients: " + maleCount;
		female = "Number of Female Patients" + femaleCount;

		List<String> patientPopulations = Arrays.asList(caucasian, african, indian, pac, hisp, other, male, female);
		return patientPopulations;
	}
}
