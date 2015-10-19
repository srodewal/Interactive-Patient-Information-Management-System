package com.ipims.views;

import com.ipims.healthconditions.HealthViewController;
import com.ipims.medication.PrescribeMedViewController;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.patientcase.PatientCaseViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PatientCaseView extends BaseView{

	
	public void createPatientCaseView(User user, PatientCaseViewController patientCaseViewController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		
		Text title = new Text("Access Patient Case");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		hbox.getChildren().add(title);

		Button mainMenuBtn = new Button("Main Menu");
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				patientCaseViewController.goBack();

			}
			
		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().add(hbox);
		
		ListView<String> list = new ListView<String>();
		/*ObservableList<String> items = FXCollections.observableArrayList (
				"1. Gregg prescribed advil
				*/
		ObservableList<String> items = FXCollections.observableArrayList (
			);
		//list.setItems(items);
		
		// for success/error message
		final Text actionTarget = new Text();
		
		// Show Patient Case if Doctor
		//if (user.getUsertype() == UserType.PATIENT ) {
			vbox.getChildren().add(addPatientCase(items, actionTarget));
		//}
		
		list.setItems(items); // moved

		Text subTitle = new Text("View Patient Case");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);


		
		vbox.getChildren().addAll(list, actionTarget);
		
		
		currentScene = new Scene(vbox, 500, 700);
	}

	public VBox addPatientCase(ObservableList<String> items, Text actionTarget) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		Label PatientLabel = new Label("Patient:");
		PatientLabel.setTextFill(Color.WHITE);
		TextField PatientTextField = new TextField();
		PatientTextField.setPromptText("Patient Name");
		PatientTextField.setMaxSize(120, 5);

		
		hbox.getChildren().addAll(PatientLabel,PatientTextField);
		baseVbox.getChildren().add(hbox);


		Button PatientCaseBtn = new Button("Submit");
		PatientCaseBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				
				
				String patientCaseInfo = "Medical History:--------\nHealth Condition:-------";
				
				if(PatientTextField.getText().equals("")) {
					actionTarget.setFill(Color.RED);
					actionTarget.setText("Error: No patient name entered!");
				}
				
				else {
					items.add(patientCaseInfo);
					actionTarget.setFill(Color.GREEN);
					actionTarget.setText("Success");
				}
				
			}
		});

		baseVbox.getChildren().add(PatientCaseBtn);

		return baseVbox;
	}

	
}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

