package com.ipims.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;
import com.ipims.models.LabRecord;
import com.ipims.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.ipims.models.Patient;


public class StatisticalReporter {

	//This function retrieves the lab record information from each patient that has a lab record,
	//and determines which percent of patients fall into the healthy or abnormal categories
	public static ObservableList<String> analyzeHealth() {
		int inRangeCA = 0, inRangeNA = 0, inRangeMG = 0, inRangeGL = 0, total = 0;
		float caPercent = 0, naPercent = 0, mgPercent = 0, glPercent = 0;
		String calcium, sodium, magnesium, glucose;
		List<LabRecord> patientNumList = null;
		patientNumList = DatabaseManager.getInstance().getAllLabRecord();
		total = DatabaseManager.getInstance().getNumberOfRegisteredPatients();
		System.out.println("Total is: " + total);

		for(LabRecord record: patientNumList) {
			System.out.println("A lab record is being processed"); // test
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

		caPercent = (float) inRangeCA / total * 100;
		naPercent = (float) inRangeNA / total * 100;
		mgPercent = (float) inRangeMG / total * 100;
		glPercent = (float) inRangeGL / total * 100;

		calcium = "The percent of patients with normal calcium is: " + Float.toString(caPercent);
		sodium = "The percent of patients with normal sodium is: " + Float.toString(naPercent);
		magnesium = "The percent of patients with normal magnesium is: " + Float.toString(mgPercent);
		glucose = "The percent of patients with normal glucose is: " + Float.toString(glPercent);

		//List<String> healthReport = Arrays.asList(calcium, sodium, magnesium, glucose);
		ObservableList<String> healthReport = FXCollections.observableArrayList (
				);
		healthReport.add(calcium);
		healthReport.add(sodium);
		healthReport.add(magnesium);
		healthReport.add(glucose);

		return healthReport;
	}

	//This function analyzes the admission rate, which we defined only as the total number of registered patients.
	//It goes into the database, gets the total number of patients, and reports it in the User Interface.
	public static ObservableList<String> analyzeAdmissionRate() {
		int number_of_patients = 0;
		String output_number = null;

		//number_of_patients = DatabaseManager.getInstance().getNumberOfRegisteredPatients();
		// 
		List<Patient> patientList = new ArrayList<Patient>();
		patientList = DatabaseManager.getInstance().getAllPatients();
		for(int i = 0; i < patientList.size(); i++) {
			number_of_patients++;
		}
		output_number = "The admission rate is calculated as the total number of registered patients, which is: " + Integer.toString(number_of_patients);

		//List<String> admissionRate = Arrays.asList(output_number);
		ObservableList<String> admissionRate = FXCollections.observableArrayList (
				);

		admissionRate.add(output_number);

		return admissionRate;

	}

	// to do
	public static ObservableList<String> analyzeTypeOfPatients() {
		int allergyCount = 0;
		int chestCount = 0;
		int heartCount = 0;
		int diabeticCount = 0;
		int skinCount = 0;

		String allergy, chestPain, heartProb, diabetic, skinProb;
		
		List<Patient> patientList = new ArrayList<Patient>();
		patientList = DatabaseManager.getInstance().getAllPatients();
		for(int i = 0; i < patientList.size(); i++) {
			//patientList.get(i).getConditions
		}

		ObservableList<String> typePatients = FXCollections.observableArrayList (
				);
		typePatients.add("Work in progress");
		return typePatients;

	}

	public static ObservableList<String> analyzePatientPopulation() {
		int caucasionCount= 0;
		int africanAmericanCount = 0;
		int indianCount = 0;
		int pacificCount = 0;
		int hispanicCount = 0;
		int otherCount = 0;
		int maleCount = 0;
		int femaleCount = 0;

		String caucasian, african, indian, pac, hisp, other, male, female;

		List<Patient> patientList = new ArrayList<Patient>();

		patientList = DatabaseManager.getInstance().getAllPatients();

		for(Patient patient: patientList)
		{
			if(patient.getRace().equals("Caucasian"))
			{
				caucasionCount++;
			}
			else if(patient.getRace().equals("African American"))
			{
				africanAmericanCount++;
			}
			else if(patient.getRace().equals("American Indian"))
			{
				indianCount++;
			}
			else if(patient.getRace().equals("Pacific Islander"))
			{
				pacificCount++;
			}
			else if(patient.getRace().equals("Hispanic"))
			{
				hispanicCount++;
			}
			else //if(patient.getRace().equals("Other"))
			{
				otherCount++;
			}
			
			if(patient.getSex().equals("Male"))
			{
				maleCount++;
			}
			else //if(patient.getSex().equals("Female"))
			{
				femaleCount++;
			}
		}

		caucasian = "Number of Caucasian Patients: " + Integer.toString(caucasionCount);
		african = "Number of African American Patients: " + Integer.toString(africanAmericanCount);
		indian = "Number of American Indian Patients: " + Integer.toString(indianCount);
		pac = "Number of Pacific Islander Patients: " + Integer.toString(pacificCount);
		hisp = "Number of Hispanic Patients: " + Integer.toString(hispanicCount);
		other = "Number of Patients that selected Other: " + Integer.toString(otherCount);
		male = "Number of Male Patients: " + Integer.toString(maleCount);
		female = "Number of Female Patients: " + Integer.toString(femaleCount);
		

		//List<String> patientPopulations = Arrays.asList(caucasian, african, indian, pac, hisp, other, male, female);
		ObservableList<String> patientPopulations = FXCollections.observableArrayList (
				);
		patientPopulations.add(caucasian);
		patientPopulations.add(african);
		patientPopulations.add(indian);
		patientPopulations.add(pac);
		patientPopulations.add(hisp);
		patientPopulations.add(other);
		patientPopulations.add(male);
		patientPopulations.add(female);
		return patientPopulations;
	}
}
