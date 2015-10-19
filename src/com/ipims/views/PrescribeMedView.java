package com.ipims.views;

import java.time.LocalDate;

import com.ipims.appointment.AppointmentViewController;
import com.ipims.medication.PrescribeMedViewController;
import com.ipims.models.User;
import com.ipims.models.User.UserType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class PrescribeMedView extends BaseView {

	
	public void createPrescribeMedView(User user, PrescribeMedViewController prescribeMedViewController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		
		Text title = new Text("Prescribe Medications");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		hbox.getChildren().add(title);

		Button mainMenuBtn = new Button("Main Menu");
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				prescribeMedViewController.goBack();


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
		
		// Show Prescribe Medication if Doctor
		//if (user.getUsertype() == UserType.PATIENT ) {
			vbox.getChildren().add(addPrescription(items, actionTarget));
		//}
		
		list.setItems(items); // moved

		Text subTitle = new Text("View Prescriptions");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);


		
		vbox.getChildren().addAll(list, actionTarget);
		
		
		currentScene = new Scene(vbox, 500, 700);
	}

	public VBox addPrescription(ObservableList<String> items, Text actionTarget) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		Text title = new Text("Prescribe Medication");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		title.setFill(Color.WHITE);

		baseVbox.getChildren().add(title);

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		Label PatientLabel = new Label("Patient:");
		PatientLabel.setTextFill(Color.WHITE);
		TextField PatientTextField = new TextField();
		PatientTextField.setPromptText("Patient Name");
		PatientTextField.setMaxSize(120, 5);
		

		

		Label MedicationLabel = new Label("Medication:");
		MedicationLabel.setTextFill(Color.WHITE);
		TextField MedicationTextField = new TextField();
		MedicationTextField.setPromptText("Medication");
		MedicationTextField.setMaxSize(110, 5);

		hbox.getChildren().addAll(PatientLabel,PatientTextField, MedicationLabel, MedicationTextField);
		baseVbox.getChildren().add(hbox);

		HBox hbox2 = new HBox();
		hbox2.setSpacing(10);


		Button PrescribeMedBtn = new Button("Submit");
		PrescribeMedBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				
				
				String prescribeMed = PatientTextField.getText() + " prescribed " + MedicationTextField.getText();
				if(MedicationTextField.getText().equals("") || PatientTextField.getText().equals("")) {
					actionTarget.setFill(Color.RED);
					actionTarget.setText("Error: Unable to Prescribe Medication!");
				}
				else if(!items.contains(prescribeMed)) {
					items.add(prescribeMed);
					actionTarget.setFill(Color.GREEN);
					actionTarget.setText("Medication Prescribed!");
				}
				else {
					actionTarget.setFill(Color.RED);
					actionTarget.setText("Error: Prescription already entered!");
				}
			}
		});

		baseVbox.getChildren().add(PrescribeMedBtn);

		return baseVbox;
	}
}