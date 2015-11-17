package com.ipims.appointment;

import java.util.ArrayList;
import java.util.List;

import com.ipims.Helper;
import com.ipims.MenuViewController;
import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;
import com.ipims.models.Doctor;
import com.ipims.models.Patient;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

/**
 * View Controller that controls the appointment view.
 * @author jithin
 *
 */
public class AppointmentViewController {

	private Appointmentsview view;
	private AppointmentManager appManager;
	private Appointment currentlySelectedApp;

	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	public  AppointmentViewController() {
		appManager = new AppointmentManager();
		view = new Appointmentsview();
		view.createAppointmentsView(UserSession.getInstance().getCurrentUser(), this);

	}

	public Scene getScene() {
		return view.getCurrentScene();
	}

	public ObservableList<String> getAppoinmentList () {

		List<Appointment> appointmentList = getListOfAppointment();
		List<String> stringAppList = new ArrayList<>();
		for (int i = 0; i < appointmentList.size(); i++) {
			Appointment app = appointmentList.get(i);
			int index = i+1;

			String str = "" + index +". ";
			if (app.getDoctor() != null) {
				str += app.getDoctor().getName();
			} 
			str+= " (Category: "+ app.getCategory()+") on "+ app.getDate()+" at "+ app.getTime();
			stringAppList.add(str);
		}

		// Show no appointments if the list is empty.
		if(stringAppList.isEmpty()) {
			stringAppList.add("No appoinments.");
		}

		ObservableList<String> items = FXCollections.observableArrayList (stringAppList);
		return items;
	}

	private List<Appointment>getListOfAppointment() {

		// If patient logs in, get the list of appointments for that patient
		// If HSP, Nurse or Doctor logs in, get the list all appointments
		//
		List<Appointment> appointmentList = null;

		if(UserSession.getInstance().getCurrentUser().getUsertype() == UserType.PATIENT) {
			Patient patient = (Patient)UserSession.getInstance().getCurrentUser();
			appointmentList = appManager.getAppointmentForPatient(patient);

		} else {
			appointmentList = appManager.getAppointmentForPatient(null);
		}

		return appointmentList;
	}

	public void goBack() {
		MenuViewController menu = new MenuViewController();
		view.getStage().setScene(menu.getScene());   
	}

	public void didSelectItem(int index) {

		User user = UserSession.getInstance().getCurrentUser();
		List<Appointment> list = getListOfAppointment();

		if(list.isEmpty()) {
			return;
		}

		// Only Patient or HSP staff can update
		//
		if (index > -1 && (user.getUsertype() == UserType.PATIENT || 
				user.getUsertype() == UserType.HSPSTAFF)) {

			Appointmentsview updateView = new Appointmentsview();
			currentlySelectedApp = list.get(index);
			updateView.createUpdateAppoinmentView(currentlySelectedApp, this);
			view.getStage().setScene(updateView.getCurrentScene());
			view = updateView;
		}
	}

	public void handleUpdateGoBack() {
		Appointmentsview backToAppoinment = new Appointmentsview();
		backToAppoinment.createAppointmentsView(UserSession.getInstance().getCurrentUser(), this);
		view.getStage().setScene(backToAppoinment.getCurrentScene());
		view = backToAppoinment;
	}

	public void handleUpdateClick(Appointment updatedAppointment) {

		if (validateInputAppoinment(updatedAppointment) == true) {

			// If the dates or times are different, check if that conflicts any other appointment.
			//
			if ( (currentlySelectedApp.getDate().equals(updatedAppointment.getDate()) == false ||
					currentlySelectedApp.getTime().equals(updatedAppointment.getTime()) == false)
					&& checkForConflict(updatedAppointment) == false) {
				return;
			} 
				
			appManager.updateAppointment(currentlySelectedApp, updatedAppointment);
			currentlySelectedApp = null;
			handleUpdateGoBack();
			view.showInfo("Appoinment updated.");
		}

	}

	public void handleSubmitClick(Appointment newAppoinment) {

		if (validateInputAppoinment(newAppoinment) == true &&
				checkForConflict(newAppoinment) == true) {
			
			appManager.newAppointment(newAppoinment);
			view.refreshList(getAppoinmentList());
		}
	}

	public void handleAppointmentCancellation() {
		
		appManager.deleteAppoinment(currentlySelectedApp);
		currentlySelectedApp = null;
		handleUpdateGoBack();
		view.showInfo("Appoinment cancelled!!");
	}

	/**
	 * Validate the inputs are correct.
	 * @param newAppoinment
	 * @return
	 */
	private boolean validateInputAppoinment(Appointment newAppoinment) {

		// Check if all the fields are entered.
		if (newAppoinment.getDate() == null || newAppoinment.getTime() == null ||
				(newAppoinment.getDoctor() == null && newAppoinment.getCategory() == null)) {
			
			view.showErrorMessage("Cannot submit the appoinment. Please enter all the required fields.");
			return false;
		}

		// Fill category if its empty
		if (newAppoinment.getCategory() == null) {
			newAppoinment.setCategory(newAppoinment.getDoctor().getCategory());
		}
		
		if (newAppoinment.getDoctor() == null) {
			for (Doctor doc : DatabaseManager.getInstance().getAllDoctors()) {
				if (newAppoinment.getCategory().equals(doc.getCategory())) {
					newAppoinment.setDoctor(doc);
					break;
				}
			}
		}
		
		// Check if the time is valid.
		String []parts = newAppoinment.getTime().split(":");
		if (parts.length != 2 || Helper.isNumeric(parts[0]) == false || Helper.isNumeric(parts[1]) == false  ) {

			view.showErrorMessage("Please enter a valid time.");
			return false;
		} else {
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);
			if (hours < 0 || hours > 24 || minutes < 0 || minutes > 59) {

				view.showErrorMessage("Please enter a valid time.");
				return false;
			}
		}

		return true;
	}

	/**
	 * Check if the appointment time conflicts with another one for the same doctor.
	 * @param newAppoinment
	 * @return
	 */
	private boolean checkForConflict(Appointment newAppoinment) {
		
		String datetext = newAppoinment.getDate().toString();
		boolean result = true;
		List<Appointment> listOfAppoinments = DatabaseManager.getInstance().getAppointmentsForDoctor(newAppoinment.getDoctor().getUserId(), datetext);
		for (Appointment app : listOfAppoinments) {
			if (app.getTime().equals(newAppoinment.getTime())) {
				view.showErrorMessage("An appointment already exists for the same time frame. Please enter a different time.");
				result = false;
				break;
			}
		}
		return result;
	}

}
