package com.ipims.views;

import java.util.ArrayList;
import java.util.List;

import com.ipims.Helper;
import com.ipims.database.DatabaseManager;
import com.ipims.healthconditions.HealthViewController; // should be taken out
import com.ipims.hsp.ViewPatientInfoViewController;
import com.ipims.models.Patient;
import com.ipims.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

public class ViewPatientInfoView extends BaseView {
	public void createViewPatientInfoView(ViewPatientInfoViewController parentController) {			
			
			final Text actionTarget = new Text();
		
			VBox vbox = new VBox();
			vbox.setPadding(new Insets(25));
			vbox.setSpacing(8);

			HBox hbox = new HBox();
			hbox.setSpacing(10);
			
			Label PatientLabel = new Label("Patient:");
			PatientLabel.setTextFill(Color.BLACK);
			PatientLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
			
			Label titleLabel = new Label("View Patient Information");
			titleLabel.setTextFill(Color.BLACK);
			titleLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
			
			/*TextField PatientTextField = new TextField();
			PatientTextField.setMaxSize(250, 55);
			*/
			
			// get all patients
			ComboBox<String> patientComboBox = new ComboBox<String>();
			List<Patient> allPatients = new ArrayList<Patient>();
			allPatients = DatabaseManager.getInstance().getAllPatients();
			for(int i = 0; i < allPatients.size(); i++) {
				patientComboBox.getItems().add(allPatients.get(i).getName());
			}
			// end get all patients

			Button mainMenuBtn = new Button("Menu");
			mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					parentController.goBack();


				}
			});
			
			
			hbox.getChildren().addAll(titleLabel, mainMenuBtn);
			vbox.getChildren().add(hbox);
			
			ListView<String> list = new ListView<String>();
			ObservableList<String> items = FXCollections.observableArrayList (
					"Patient Information goes here!"
				);
		
			
			list.setItems(items); // moved
			
			HBox patientHbox = new HBox();
			patientHbox.setSpacing(10);
			
			Button submitBtn = new Button("Submit");
			submitBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					actionTarget.setFill(Color.GREEN);
					actionTarget.setText("Submit Button Pressed!");
					
					// clear list
					items.clear();
					
					// get user from database
					Patient tempPatient = Helper.getPatientAtIndex(patientComboBox.getSelectionModel().getSelectedIndex());
					User tempUser = DatabaseManager.getInstance().getUser(tempPatient.getUserId());
					
					String name = "Name: " + tempUser.getName();
					items.add(name);
					String dob = "DOB: " + tempUser.getDateOfBirth();
					items.add(dob);
					String address = "Address: " + tempUser.getAddress();
					items.add(address);
					String ssn = "SSN: " + tempUser.getSsn();
					items.add(ssn);
					String phoneNum = "Phone Number: " + tempUser.getPhoneNumber();
					items.add(phoneNum);
					String email = "Email: " + tempUser.getEmail();
					items.add(email);
					String healthInsurance = "Health Insurance Provider: " + tempUser.getInsurance();
					items.add(healthInsurance);
					String sex = "Sex: " + tempUser.getSex();
					items.add(sex);
					String race = "Race: " + tempUser.getRace();
					items.add(race);
				}
			});
			
			patientHbox.getChildren().addAll(PatientLabel, patientComboBox, submitBtn);
			
			vbox.getChildren().add(patientHbox);
			vbox.getChildren().add(list);
			
			// removed this button due to redundancy
			/*Button viewAnotherBtn = new Button("View Another Patient");
			viewAnotherBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					items.clear();
					//PatientTextField.clear();
					patientComboBox.getSelectionModel().clearSelection();
					actionTarget.setFill(Color.GREEN);
					actionTarget.setText("Button pressed! List cleared.");
				}
			});*/
			
			// to display success or error message
			vbox.getChildren().add(actionTarget);
			
			
			createScene(vbox);

	}

	
}
