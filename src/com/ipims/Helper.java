package com.ipims;

import java.util.ArrayList;
import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Doctor;
import com.ipims.models.Patient;
import com.ipims.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Helper {

	public static ObservableList<String> getCategoryList() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll(Helper.getAllCategories());
		return items;
	}

	public static ObservableList<String> getDoctorList() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll(Helper.getAllDoctors());
		return items;
	}

	public static ObservableList<String> getPatientList() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll(Helper.getAllPatients());
		return items;
	}

	public static List<String> getAllDoctors() {
		List<Doctor> docList = DatabaseManager.getInstance().getAllDoctors();
		List<String> list = new ArrayList<>(); 
		for (Doctor doc : docList){
			list.add(doc.getName());
		}

		return list;
	}

	public static List<String> getAllPatients() {
		List<User> patientList = DatabaseManager.getInstance().getPatientList();
		List<String> list = new ArrayList<>(); 
		for (User user : patientList){
			list.add(user.getName());
		}
		return list;
	}

	public static List<String> getAllCategories() {
		List<String> list = new ArrayList<>(); 
		list.add("Heart");
		list.add("Eye");
		list.add("Ortho");
		list.add("Dental");
		return list;
	}
	
	public static List<String> getTimeSlots() {
		List<String> list = new ArrayList<>(); 
		list.add("9:00");
		list.add("10:00");
		list.add("11:00");
		list.add("12:00");
		list.add("14:00");
		list.add("15:00");
		list.add("16:00");
		list.add("17:00");
		list.add("18:00");
		return list;
	}

	public static Patient getPatientAtIndex(int index) {
		if(index>=0) {
			List<User> patientList = DatabaseManager.getInstance().getPatientList();
			return (Patient)patientList.get(index);
		}
		return null;
	}

	public static Doctor getDoctorAtIndex(int index) {
		if(index>=0) {
			List<Doctor> docList = DatabaseManager.getInstance().getAllDoctors();
			return (Doctor)docList.get(index);
		}
		return null;
	}
	
	public static boolean isNumeric(String str)  {  
	  try  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  {  
	    return false;  
	  }  
	  return true;  
	}
}
