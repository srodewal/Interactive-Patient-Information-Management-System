package com.ipims;


import com.ipims.appointment.ScheduleAppointmentViewController;
import com.ipims.healthconditions.HealthViewController;
import com.ipims.usersession.LoginViewController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuViewController {

	
	
	private Scene menuScene;
	
	public Scene getScene() {
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Menu");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button scheduleAppBtn = new Button("Schedule Appointment(s)");
        HBox scheduleAppBox = new HBox(10);
        scheduleAppBtn.setAlignment(Pos.BOTTOM_RIGHT);
        scheduleAppBox.getChildren().add(scheduleAppBtn);
        grid.add(scheduleAppBtn, 0, 2);
        
        Button updateHealthBtn = new Button("Update Healthcare Condition(s)");
        HBox updateHealthBox = new HBox(10);
        updateHealthBtn.setAlignment(Pos.BOTTOM_RIGHT);
        updateHealthBox.getChildren().add(updateHealthBtn);
        grid.add(updateHealthBtn, 0, 3);
        
        Button logoutBtn = new Button("Logout");
        HBox logoutBox = new HBox(20);
        logoutBtn.setAlignment(Pos.BOTTOM_RIGHT);
        logoutBox.getChildren().add(logoutBtn);
        grid.add(logoutBtn, 0, 4);
        
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        scheduleAppBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	
            	
            	ScheduleAppointmentViewController schedule = new ScheduleAppointmentViewController();
            	
            	Stage stage = (Stage) menuScene.getWindow();
            	stage.setScene(schedule.getScene());
            	
            }
        });
        updateHealthBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	
            	
            	HealthViewController hc = new HealthViewController();
            	
            	Stage stage = (Stage) menuScene.getWindow();
            	stage.setScene(hc.getScene());
            	
            }
        });
        
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	
            	// need to add logout functionality 
            	// currentUser of userSession should be "deleted"
            	
            	LoginViewController login = new LoginViewController();
            	
            	Stage stage = (Stage) menuScene.getWindow();
            	stage.setScene(login.getScene());
            	
            }
        });
        menuScene = new Scene(grid, 300, 275);
		return menuScene;
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

