package com.ipims.appointment;

import com.ipims.MenuViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;

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
	
}
