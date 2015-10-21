package com.ipims.appointment;

import java.time.LocalDate;

import com.ipims.MenuViewController;
import com.ipims.database.DatabaseManager;
import com.ipims.models.Appointment;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;


public class AppointmentViewController {
	private Appointmentsview view;


	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
	public  AppointmentViewController() {
		view = new Appointmentsview();
		view.createAppointmentsView(UserSession.getInstance().getCurrentUser(), this);
		
	}
	
	public Scene getScene() {
		return view.getCurrentScene();
	}
	
	public void goBack() {
		 MenuViewController menu = new MenuViewController();
         view.getStage().setScene(menu.getScene());   
	}
	
	public void didSelectItem(int index) {
		Appointmentsview updateView = new Appointmentsview();
		updateView.createUpdateAppoinmentView(new Appointment(LocalDate.now(), "12:23", null, null, ""), this);
		view.getStage().setScene(updateView.getCurrentScene());
		view = updateView;
	}
	
	public ObservableList<String> getAppoinmentList () {
		ObservableList<String> items = FXCollections.observableArrayList (
				"1. Dr. John (Category: Eye) on 10/12/2015 at 14:35",
				"2. Dr. John (Category: Eye) on 10/1/2015 at 10:00");
		return items;
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
	
	
	public void handleAppointmentCancellation() {
		handleUpdateGoBack();
	}
	
	
	
}
