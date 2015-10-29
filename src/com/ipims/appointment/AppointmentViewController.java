package com.ipims.appointment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ipims.MenuViewController;
import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;
import com.ipims.models.Patient;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;


public class AppointmentViewController {

	private Appointmentsview view;
	private AppointmentManager appManager;

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
			stringAppList.add("" + i +". Dr. "+app.getDoctor()+" (Category: "+ app.getCategory()+") on "+app.getDate()+" on "+ app.getTime());
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

		// Only Patient or HSP staff can update
		//
		if (user.getUsertype() == UserType.PATIENT || 
				user.getUsertype() == UserType.HSPSTAFF) {

			Appointmentsview updateView = new Appointmentsview();
			updateView.createUpdateAppoinmentView(new Appointment(LocalDate.now(), "12:23", null, null, ""), this);
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
		handleUpdateGoBack();
	}

	public void handleSubmitClick(Appointment newAppoinment) {
		DatabaseManager.getInstance().newAppointment(newAppoinment);
	}

	public void handleAppointmentCancellation() {
		handleUpdateGoBack();
	}



}
