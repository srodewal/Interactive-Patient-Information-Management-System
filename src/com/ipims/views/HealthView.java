package com.ipims.views;

import java.util.ArrayList;
import java.util.List;

import com.ipims.database.DatabaseManager;
import com.ipims.healthconditions.HealthViewController;
import com.ipims.models.HealthCondition;
import com.ipims.models.Patient;
import com.ipims.models.User;
import com.ipims.models.User.UserType;

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


public class HealthView extends BaseView {

	public void createHealthview(User user, HealthViewController parentController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);
		
		// moved
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
				"1. (History) Severe Chest Pain");
		list.setItems(items);

		// Add title and main menu 

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		Text title = new Text("Health");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		hbox.getChildren().add(title);

		Button mainMenuBtn = new Button("Main Menu");
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				parentController.goBack();


			}
		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().add(hbox);

		// Add patient selector if the user is not Patient
		//if(user.getUsertype() == UserType.PATIENT) {
			HBox hbox2 = new HBox();
			hbox2.setSpacing(10);

			Label PatientLabel = new Label("Patient:");
			
			PatientLabel.setTextFill(Color.BLACK);
			PatientLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

			//TextField PatientTextField = new TextField();

			//PatientTextField.setMaxSize(250, 55);
			ComboBox<String> patientComboBox = new ComboBox<String>();
			List<Patient> allPatients = new ArrayList<Patient>();
			allPatients = DatabaseManager.getInstance().getAllPatients();
			for(int i = 0; i < allPatients.size(); i++) {
				patientComboBox.getItems().add(allPatients.get(i).getName());
			}

			hbox2.getChildren().addAll(PatientLabel, patientComboBox);
			vbox.getChildren().add(hbox2);
		//}

		// Add Medical History box
		vbox.getChildren().add(addMedicalHistory(parentController, items));

		// Add Medical Condition box
		vbox.getChildren().add(addMedicalCondition(parentController, items));

		Text subTitle = new Text("View Health Conditions");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);

		/*ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
				"1. (History) Severe Chest Pain");
		list.setItems(items);*/
		vbox.getChildren().add(list);
		createScene(vbox);
		
	}

	public VBox addMedicalHistory(HealthViewController parentController, ObservableList<String> items) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		Text title = new Text("Medical History");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		title.setFill(Color.WHITE);

		Label conditionLabel = new Label("History:");
		conditionLabel.setTextFill(Color.WHITE);

		ComboBox<String> conditionComboBox = new ComboBox<String>();
		conditionComboBox.getItems().addAll(
				"Allergies",
				"Chest Pain",
				"Heart Problems",
				"Diabetes"
				);

		Label commentsLabel = new Label("Comments:");
		commentsLabel.setTextFill(Color.WHITE);

		TextField commentsTextField = new TextField();

		commentsTextField.setMaxSize(400, 55);


		Button submitBtn = new Button("Submit");
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				// pass conditions to database
				if(!conditionComboBox.equals(null)) {
					String updatedHistory = "(History) " + conditionComboBox.getValue() + "\n" + commentsTextField.getText();
					items.add(updatedHistory);
					
					// send to database
					HealthCondition newHc = new HealthCondition();
					newHc.setComments(commentsTextField.getText());
					newHc.setHealthConcern(conditionComboBox.getValue());
					//newHc.setHealthConditionId(0);
					newHc.setCurrent(false); // determines if history or current condition
					//newHc.setSeverity(0);
					
					//parentController.handleHc(, newHc);
				}
	
			}
		});
		
		baseVbox.getChildren().addAll(title, 
				conditionLabel,
				conditionComboBox, 
				commentsLabel,
				commentsTextField,
				submitBtn);

		return baseVbox;
	}

	public VBox addMedicalCondition(HealthViewController parentController, ObservableList<String> items) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		Text title = new Text("Current Medical Condition");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		title.setFill(Color.WHITE);
		Label conditionLabel = new Label("Condition:");
		conditionLabel.setTextFill(Color.WHITE);

		ComboBox<String> conditionComboBox = new ComboBox<String>();
		conditionComboBox.getItems().addAll(
				"Chest Pain",
				"Heart Problems",
				"Diabetes"
				);

		Label commentsLabel = new Label("Comments:");
		commentsLabel.setTextFill(Color.WHITE);

		TextField commentsTextField = new TextField();

		commentsTextField.setMaxSize(400, 55);

		Button submitBtn = new Button("Submit");
		
		
		// add checkbox for severity
		CheckBox severity = new CheckBox("Send Alert");
		severity.setSelected(false);
		
		baseVbox.getChildren().addAll(title, 
				conditionLabel, 
				conditionComboBox, 
				commentsLabel, 
				commentsTextField,
				severity,
				submitBtn);
		
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				//if(!conditionComboBox.equals(null) && !PatientTextField.getText().equals("")) 
				if(!conditionComboBox.equals(null))
				{
					System.out.println("Searching for patient"); // test
	
					String updatedCondition = "(Condition) " + conditionComboBox.getValue() + "\n" + commentsTextField.getText();
					items.add(updatedCondition);
					
					// send to database
					HealthCondition newHc = new HealthCondition();
					newHc.setComments(commentsTextField.getText());
					newHc.setHealthConcern(conditionComboBox.getValue());
					//newHc.setHealthConditionId(0);
					newHc.setCurrent(true); // determines if history or current condition
					//newHc.setSeverity(0);
					
					//parentController.handleHc(, newHc);
				}
				
				if(severity.isSelected()) {
					// send alert
					parentController.sendAlert(); // alerts system to patient with "severe condition"
				}
				
				if(conditionComboBox.getValue().equals("Chest Pain")) {
					// send alert
					parentController.sendAlert(); // alerts system to patient with "severe condition"
				}

			}
		});

		return baseVbox;
	}


}
