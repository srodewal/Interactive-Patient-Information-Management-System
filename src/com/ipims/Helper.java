package com.ipims;

import java.util.ArrayList;
import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Doctor;
import com.ipims.models.Patient;
import com.ipims.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Helper methods that gives out most commonly used data.
 * 
 * @author jithin
 *
 */
public class Helper {

	/**
	 * Observable String list of doctor categories used in UI
	 * 
	 * @return
	 */
	public static ObservableList<String> getCategoryList() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll(Helper.getAllCategories());
		return items;
	}

	/**
	 * Observable string list of doctor name
	 * 
	 * @return
	 */
	public static ObservableList<String> getDoctorList() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll(Helper.getAllDoctors());
		return items;
	}

	/**
	 * Observable string list of patient names.
	 * 
	 * @return
	 */
	public static ObservableList<String> getPatientList() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll(Helper.getAllPatients());
		return items;
	}

	/**
	 * String list of doctor names.
	 * @return
	 */
	public static List<String> getAllDoctors() {
		List<Doctor> docList = DatabaseManager.getInstance().getAllDoctors();
		List<String> list = new ArrayList<>(); 
		for (Doctor doc : docList){
			list.add(doc.getName());
		}

		return list;
	}

	/**
	 * String list of patient names.
	 * @return
	 */
	public static List<String> getAllPatients() {
		List<User> patientList = DatabaseManager.getInstance().getPatientList();
		List<String> list = new ArrayList<>(); 
		for (User user : patientList){
			list.add(user.getName());
		}
		return list;
	}

	/**
	 * String list of category names.
	 * @return
	 */
	public static List<String> getAllCategories() {
		List<String> list = new ArrayList<>(); 
		list.add("Cardiologist");
		list.add("Allergist");
		list.add("Pediatrician");
		list.add("Orthopedic");
		return list;
	}
	
	/**
	 * Time slot for doctor appointments.
	 * 
	 * @return
	 */
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

	/**
	 * Get the patient at index, ordered from the database list.
	 * @param index
	 * @return
	 */
	public static Patient getPatientAtIndex(int index) {
		if(index>=0) {
			List<User> patientList = DatabaseManager.getInstance().getPatientList();
			return (Patient)patientList.get(index);
		}
		return null;
	}

	/**
	 * Get the doctor at index, ordered from the database list.
	 * @param index
	 * @return
	 */
	public static Doctor getDoctorAtIndex(int index) {
		if(index>=0) {
			List<Doctor> docList = DatabaseManager.getInstance().getAllDoctors();
			return (Doctor)docList.get(index);
		}
		return null;
	}
	
	/**
	 * Check if the string is numeric.
	 * @param str
	 * @return true is numeric
	 */
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
