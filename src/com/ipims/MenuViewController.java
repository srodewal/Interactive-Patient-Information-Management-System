package com.ipims;


import com.ipims.appointment.ScheduleAppointmentViewController;

import com.ipims.models.User;
import com.ipims.views.MenuView;

import javafx.scene.Scene;

public class MenuViewController {
	private MenuView view;
	
	public Scene getScene() {

        view = new MenuView();
        view.createMenuScene(new User(), this);
		return view.getCurrentScene();
	}
	
	public void handleScheduleAppointment() {

    	ScheduleAppointmentViewController schedule = new ScheduleAppointmentViewController();
    	view.getStage().setScene(schedule.getScene());
    	
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

