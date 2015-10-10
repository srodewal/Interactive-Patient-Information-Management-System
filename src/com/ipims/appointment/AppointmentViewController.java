package com.ipims.appointment;

import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;
import com.ipims.views.MenuView;

import javafx.scene.Scene;

public class AppointmentViewController {
	private Appointmentsview view;


	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
	public  AppointmentViewController() {
		view = new Appointmentsview();
		view.createAppointmentsView(UserSession.getInstance().getCurrentUser());
	}
	
	public Scene getScene() {
		return view.getCurrentScene();
	}
	
}
