package com.ipims;


import com.ipims.appointment.ScheduleAppointmentViewController;

import com.ipims.models.User;
import com.ipims.views.MenuView;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuViewController {
	private MenuView view;
	
	public Scene getScene() {

        view = new MenuView();
		return view.getMenuScene(new User(), this);
	}
	
	public void handleScheduleAppointment() {

    	ScheduleAppointmentViewController schedule = new ScheduleAppointmentViewController();
    	
    	Stage stage = (Stage) view.currentScene.getWindow();
    	stage.setScene(schedule.getScene());
    	
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

