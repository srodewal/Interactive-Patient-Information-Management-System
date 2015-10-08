package com.ipims;


import com.ipims.appointment.ScheduleAppointmentViewController;
import com.ipims.models.User;
import com.ipims.views.MenuView;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuViewController {
	private MenuView view;
	private Scene menuScene;
	
	public Scene getScene() {
		
        view = new MenuView();
        menuScene = view.getMenuScene(new User(), this);
		return menuScene;
	}
	
	public void handleScheduleAppointment() {

    	ScheduleAppointmentViewController schedule = new ScheduleAppointmentViewController();
    	
    	Stage stage = (Stage) menuScene.getWindow();
    	stage.setScene(schedule.getScene());
    	
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

