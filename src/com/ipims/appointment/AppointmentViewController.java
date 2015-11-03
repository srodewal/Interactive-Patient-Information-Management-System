package com.ipims.appointment;

import java.util.ArrayList;
import java.util.List;

import com.ipims.MenuViewController;

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
			
			String str = "" + index +".";
			if (app.getDoctor() != null) {
				str += ". Dr. "+app.getDoctor().getName();
			} 
			str+= " (Category: "+ app.getCategory()+") on "+ app.getDate()+" at "+ app.getTime();
			stringAppList.add(str);
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

		// Only Patient or HSP staff can update
		//
		if (user.getUsertype() == UserType.PATIENT || 
				user.getUsertype() == UserType.HSPSTAFF) {

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
		appManager.updateAppointment(currentlySelectedApp, updatedAppointment);
		currentlySelectedApp = null;
		handleUpdateGoBack();
		view.showInfo("Appoinment updated.");
	}

	public void handleSubmitClick(Appointment newAppoinment) {
		appManager.newAppointment(newAppoinment);
		view.refreshList(getAppoinmentList());
	}

	public void handleAppointmentCancellation() {
		appManager.deleteAppoinment(currentlySelectedApp);
		currentlySelectedApp = null;
		handleUpdateGoBack();
		view.showInfo("Appoinment cancelled!!");
	}



}
