package com.ipims;


import com.ipims.appointment.ScheduleAppointmentViewController;

import com.ipims.models.User;
import com.ipims.usersession.UserSession;
import com.ipims.views.MenuView;

import javafx.scene.Scene;

public class MenuViewController {
	private MenuView view;
	
	public MenuViewController() {
		view = new MenuView();
        view.createMenuScene(UserSession.getInstance().getCurrentUser(), this);
	}
	public Scene getScene() {
		return view.getCurrentScene();
	}
	
	public void handleScheduleAppointment() {

    	ScheduleAppointmentViewController schedule = new ScheduleAppointmentViewController();
    	view.getStage().setScene(schedule.getScene());
    	
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

