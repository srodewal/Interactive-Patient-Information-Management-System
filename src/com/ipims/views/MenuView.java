package com.ipims.views;

import com.ipims.MenuViewController;
import com.ipims.models.User;
import com.ipims.models.User.UserType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MenuView extends BaseView {


	public void createMenuScene(User user, MenuViewController parentController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		
		Text scenetitle = new Text("Menu");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		vbox.getChildren().add(scenetitle);
		
		
		
		//If the passed in user is patient, create the menu for patient.
		if (user.getUsertype() == UserType.PATIENT) {
			
			Text welcomeTitle = new Text("Welcome User");
			welcomeTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
			vbox.getChildren().add(welcomeTitle);
			
			vbox.getChildren().add(appoinmentButton(parentController));
			vbox.getChildren().add(updateHealthButton(parentController));
			vbox.getChildren().add(labRecordButton(parentController));
			vbox.getChildren().add(generateStatsButton(parentController));
			
			

		} else if (user.getUsertype() == UserType.HSPSTAFF) {
			
		}
		vbox.getChildren().add(logoutButton(parentController));
		currentScene = new Scene(vbox, 300, 350);
	}

	private Button appoinmentButton(MenuViewController parentController) {
		Button scheduleAppBtn = new Button("Appointments");
		scheduleAppBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleAppointments();

			}
		});
		return scheduleAppBtn;
	}

	private Button updateHealthButton(MenuViewController parentController) {
		Button updateHealthBtn = new Button("Health Condition(s)/Concern(s)");
		updateHealthBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleHealthCondition();

			}
		});
		return updateHealthBtn;
	}
	
	private Button labRecordButton(MenuViewController parentController) {
		Button btn = new Button("Lab Record");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				//parentController.handleHealthCondition();

			}
		});
		return btn;
	}
	
	private Button generateStatsButton(MenuViewController parentController) {
		Button btn = new Button("Generate Statistical Reports");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleGenerateStatsReports();
			}
		});
		return btn;
	}

	private Button logoutButton(MenuViewController parentController) {
		Button logoutBtn = new Button("Logout");
		logoutBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleLogout();

			}
		});
		return logoutBtn;
	}
}
