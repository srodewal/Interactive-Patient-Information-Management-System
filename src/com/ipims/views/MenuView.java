package com.ipims.views;

import com.ipims.MenuViewController;
import com.ipims.models.User;
import com.ipims.models.User.UserType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MenuView extends BaseView {

	
	public void createMenuScene(User user, MenuViewController parentController) {
		
		//If the passed in user is patient, create the menu for patient.
		if (user.getUsertype() == UserType.PATIENT) {
			
			GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        Text scenetitle = new Text("Menu");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        grid.add(scenetitle, 0, 0, 2, 1);

	        Button scheduleAppBtn = new Button("Schedule Appointment");
	        HBox scheduleAppBox = new HBox(10);
	        scheduleAppBtn.setAlignment(Pos.BOTTOM_CENTER);
	        scheduleAppBox.getChildren().add(scheduleAppBtn);
	        grid.add(scheduleAppBtn, 0, 2);
	        
	        Button manageAppoinmentBtn = new Button("Manage Appointment");
	        HBox manageAppBox = new HBox(10);
	        manageAppoinmentBtn.setAlignment(Pos.BOTTOM_CENTER);
	        manageAppBox.getChildren().add(manageAppoinmentBtn);
	        grid.add(manageAppoinmentBtn, 0, 3);
	        
	        Button updateHealthBtn = new Button("Update Health Condition");
	        HBox updateHealthBox = new HBox(10);
	        updateHealthBtn.setAlignment(Pos.BOTTOM_CENTER);
	        updateHealthBox.getChildren().add(updateHealthBtn);
	        grid.add(updateHealthBtn, 0, 4);
	        
	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 1, 6);

	        scheduleAppBtn.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	            	// Pass the control of handling button clicks to the view controller
	            	parentController.handleScheduleAppointment();
	            	
	            }
	        });
	        currentScene = new Scene(grid, 300, 275);
			
		}
		
	}
}
