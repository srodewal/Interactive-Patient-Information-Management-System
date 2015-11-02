package com.ipims.views;

import com.ipims.MenuViewController;
import com.ipims.models.User;
import com.ipims.models.User.UserType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;


import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MenuView extends BaseView {


	public void createMenuScene(User user, MenuViewController parentController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(15);
		vbox.setAlignment(Pos.CENTER);

		Text appTitle = new Text("IPIMS");
		appTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
		vbox.getChildren().add(appTitle);
		
		//If the passed in user is patient, create the menu for patient.

		Text welcomeTitle = new Text("Welcome User: " + user.getName() + " (" + user.getUserTypeString() + ")");
		welcomeTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(welcomeTitle);
		

		if (user.getUsertype() == UserType.PATIENT) {
			
			vbox.getChildren().add(appoinmentButton(parentController));
			vbox.getChildren().add(updateHealthButton(parentController));
			vbox.getChildren().add(ViewPatientInfoButton(parentController));
			vbox.getChildren().add(LabRecordButton(parentController));
			vbox.getChildren().add(EnterLabRecordButton(parentController));

		} else if (user.getUsertype() == UserType.HSPSTAFF) {
			vbox.getChildren().add(appoinmentButton(parentController));
			vbox.getChildren().add(updateHealthButton(parentController));
			vbox.getChildren().add(PatientCaseButton(parentController));
			vbox.getChildren().add(ViewPatientInfoButton(parentController));
			vbox.getChildren().add(LabRecordButton(parentController));
			vbox.getChildren().add(generateStatsButton(parentController));
			vbox.getChildren().add(PrescribeMedButton(parentController)); // added
			
		} else if (user.getUsertype() == UserType.DOCTOR) {
			
			vbox.getChildren().add(appoinmentButton(parentController));
			vbox.getChildren().add(updateHealthButton(parentController));
			vbox.getChildren().add(PrescribeMedButton(parentController));
			vbox.getChildren().add(PatientCaseButton(parentController));
			vbox.getChildren().add(ViewPatientInfoButton(parentController));
			
		} else if (user.getUsertype() == UserType.NURSE) {
			vbox.getChildren().add(appoinmentButton(parentController));
			vbox.getChildren().add(updateHealthButton(parentController));
			
			vbox.getChildren().add(PatientCaseButton(parentController));
			vbox.getChildren().add(ViewPatientInfoButton(parentController));
			
			
		} else if (user.getUsertype() == UserType.LABSTAFF) {
			
			vbox.getChildren().add(LabRecordButton(parentController));
			vbox.getChildren().add(EnterLabRecordButton(parentController));
			
		}

		vbox.getChildren().add(logoutButton(parentController));
		
		createScene(vbox);
	}

	private Button appoinmentButton(MenuViewController parentController) {
		Button scheduleAppBtn = createButton("Appointments");
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
		Button updateHealthBtn = createButton("Health Condition(s)/Concern(s)");
		updateHealthBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleHealthCondition();

			}
		});
		return updateHealthBtn;
	}
	
	
	private Button generateStatsButton(MenuViewController parentController) {
		Button btn = createButton("Generate Statistical Reports");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleGenerateStatsReports();
			}
		});
		return btn;
	}
	
	
	
	
	private Button PrescribeMedButton(MenuViewController parentController) {
		
		Button PrescribeMedBtn = createButton("Prescribe Medication(s)/Lab Test(s)");
		PrescribeMedBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handlePrescribeMed();

			}
		});
		return PrescribeMedBtn;
	}
	
	private Button PatientCaseButton(MenuViewController parentController) {
		
		Button PatientCaseBtn = createButton("Access Patient Case");
		PatientCaseBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handlePatientCase();

			}
		});
		return PatientCaseBtn;
	}
	
	
	private Button LabRecordButton(MenuViewController parentController) {
		
		Button LabRecordBtn = createButton("View Lab Record");
		LabRecordBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleLabRecord();

			}
		});
		return LabRecordBtn;
	}
	
	private Button EnterLabRecordButton(MenuViewController parentController) {
		
		Button EnterLabRecordBtn = createButton("Enter Lab Record");
		EnterLabRecordBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleEnterLabRecord();

			}
		});
		return EnterLabRecordBtn;
	}


	private Button logoutButton(MenuViewController parentController) {
		Button logoutBtn = createButton("Logout");
		logoutBtn.setStyle("-fx-base: #f28282;");
		logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleLogout();

			}
		});
		return logoutBtn;
	}
	
	private Button ViewPatientInfoButton(MenuViewController parentController) {
		Button vpBtn = createButton("View Patient Info");
		vpBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				parentController.handleViewPatientInfo();
			}
		});
		return vpBtn;
	}
	
	private Button createButton(String buttonString) {
		Button btn = new Button(buttonString);
		btn.setPrefSize(250, 50);
		return btn;
	}
}
