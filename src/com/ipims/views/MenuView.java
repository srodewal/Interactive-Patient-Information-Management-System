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

	public Scene getMenuScene(User user, MenuViewController parentController) {
		Scene scene = null;
		
		if (user.getUsertype() == UserType.UNKNOWN) {
			
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
	        scheduleAppBtn.setAlignment(Pos.BOTTOM_RIGHT);
	        scheduleAppBox.getChildren().add(scheduleAppBtn);
	        grid.add(scheduleAppBtn, 0, 2);
	        
	        Button updateHealthBtn = new Button("Update Health Condition");
	        HBox updateHealthBox = new HBox(10);
	        updateHealthBtn.setAlignment(Pos.BOTTOM_RIGHT);
	        updateHealthBox.getChildren().add(updateHealthBtn);
	        grid.add(updateHealthBtn, 0, 3);
	        
	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 1, 6);

	        scheduleAppBtn.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	            	
	            	parentController.handleScheduleAppointment();
	            	
	            	
	            }
	        });
	        scene = new Scene(grid, 300, 275);
			
		}
		
		return scene;
	}
}
