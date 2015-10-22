package com.ipims.views;


import com.ipims.appointment.AppointmentViewController;
import com.ipims.labrecord.EnterLabRecordViewController;
import com.ipims.labrecord.LabRecordViewController;
import com.ipims.models.Appointment;
import com.ipims.models.LabRecord;
import com.ipims.models.User;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class EnterLabRecordView extends BaseView{

	public void createEnterLabRecordView(User user, EnterLabRecordViewController enterLabRecordViewController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		
		Text title = new Text("Enter Patient Lab Record");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		hbox.getChildren().add(title);

		Button mainMenuBtn = new Button("Main Menu");
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				enterLabRecordViewController.goBack();

			}
			
		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().add(hbox);
		
		// Show Patient Case if Doctor
				//if (user.getUsertype() == UserType.PATIENT ) {
					vbox.getChildren().add(addEnterLabRecord(null, enterLabRecordViewController));
				//}
		
					
		Text subTitle = new Text("View Lab Record");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);			
					
					
		ListView<String> list = new ListView<String>();
		
		list.setItems(enterLabRecordViewController.getLabRecordList());
		list.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
		                System.out.println(newValue);
		                int index = list.getSelectionModel().getSelectedIndex();
		                enterLabRecordViewController.didSelectItem(index);
		    });
		vbox.getChildren().add(list);
		
		// for success/error message
		//final Text actionTarget = new Text();
		
				
		createScene(vbox);
		
	}

	public VBox addEnterLabRecord(LabRecord labrecord,  EnterLabRecordViewController enterLabRecordViewController) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");
		
		Text title = new Text("Enter Lab Record");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		title.setFill(Color.WHITE);

		baseVbox.getChildren().add(title);
		
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER_LEFT);

		HBox hbox2 = new HBox();
		hbox2.setSpacing(10);
		hbox2.setAlignment(Pos.CENTER_RIGHT);

		HBox hbox3 = new HBox();
		hbox3.setSpacing(10);
		hbox3.setAlignment(Pos.CENTER_RIGHT);
		
		HBox hbox4 = new HBox();
		hbox4.setSpacing(10);
		hbox4.setAlignment(Pos.CENTER_RIGHT);
		
		HBox hbox5 = new HBox();
		hbox5.setSpacing(10);
		hbox5.setAlignment(Pos.CENTER_RIGHT);

		Label PatientLabel = new Label("Patient: ");
		PatientLabel.setTextFill(Color.WHITE);
		TextField PatientTextField = new TextField();
		PatientTextField.setPromptText("Patient Name");
		PatientTextField.setMaxSize(120, 5);
		
		Label GlucoseLabel = new Label("Glucose:");
		GlucoseLabel.setTextFill(Color.WHITE);
		TextField GlucoseTextField = new TextField();
		GlucoseTextField.setPromptText("mg/L");
		GlucoseTextField.setMaxSize(120, 5);
		
		Label SodiumLabel = new Label("Sodium:");
		SodiumLabel.setTextFill(Color.WHITE);
		TextField SodiumTextField = new TextField();
		SodiumTextField.setPromptText("mmol/L");
		SodiumTextField.setMaxSize(120, 5);
		
		Label CalciumLabel = new Label("Calcium:");
		CalciumLabel.setTextFill(Color.WHITE);
		TextField CalciumTextField = new TextField();
		CalciumTextField.setPromptText("mg/dL");
		CalciumTextField.setMaxSize(120, 5);

		Label MagnesiumLabel = new Label("Magnesium:");
		MagnesiumLabel.setTextFill(Color.WHITE);
		TextField MagnesiumTextField = new TextField();
		MagnesiumTextField.setPromptText("mg/dL");
		MagnesiumTextField.setMaxSize(120, 5);
		
		hbox.getChildren().addAll(PatientLabel,PatientTextField);
		hbox2.getChildren().addAll(GlucoseLabel,GlucoseTextField);
		hbox3.getChildren().addAll(SodiumLabel, SodiumTextField);
		hbox4.getChildren().addAll(CalciumLabel, CalciumTextField);
		hbox5.getChildren().addAll(MagnesiumLabel, MagnesiumTextField);


		baseVbox.getChildren().addAll(hbox,hbox2,hbox3,hbox4,hbox5);
				
		
				
		// Fill in values for update
		if(labrecord != null) {
			title.setText("Update Appointment");
			PatientTextField.setText("Gregg");
			GlucoseTextField.setText("15mg");
			SodiumTextField.setText("12mg");
			CalciumTextField.setText("10mg");
			MagnesiumTextField.setText("25mg");

			
			// Add update and cancel buttons
			//
			HBox hbBtn = new HBox(10);
			Button updateBtn = new Button("Update Appointment");
			hbBtn.getChildren().add(updateBtn);
			updateBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					
					enterLabRecordViewController.handleUpdateClick(null);
				
				}
			});
			
			Button cancelBtn = new Button("Cancel Appointment");
			hbBtn.getChildren().add(cancelBtn);
			cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					enterLabRecordViewController.handleAppointmentCancellation();
					
				
				}
			});
			baseVbox.getChildren().add(hbBtn);
			
		} else {
			Button EnterLabRecordBtn = new Button("Submit");
			EnterLabRecordBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					

					String enterLabRecordInfo = "Patient Name: "+PatientTextField.getText() + "\n";
					enterLabRecordInfo += "Glucose Level: " + GlucoseTextField.getText() + "\n";
					enterLabRecordInfo += "Sodium Level: " + SodiumTextField.getText() + "\n";
					enterLabRecordInfo += "Calcium Level: " + CalciumTextField.getText() + "\n";
					enterLabRecordInfo += "Magnesium Level: " + MagnesiumTextField.getText() + "\n";

					
					/*if(PatientTextField.getText().equals("")) {
						actionTarget.setFill(Color.RED);
						actionTarget.setText("Error: No patient name entered!");
					}
					
					else {
						items.add(enterLabRecordInfo);
						actionTarget.setFill(Color.GREEN);
						actionTarget.setText("Success");
					}*/
					
				}
			});

			baseVbox.getChildren().add(EnterLabRecordBtn);
		}
		
		return baseVbox;
	}

	
	
	
	
	public void createUpdateEnterLabRecordView(LabRecord labRecord, EnterLabRecordViewController enterLabRecordViewController) {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		
		Text title = new Text("Update Appoinment");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		hbox.getChildren().add(title);

		Button mainMenuBtn = new Button("Go Back");
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				enterLabRecordViewController.handleUpdateGoBack();


			}
		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().add(hbox);
		
		vbox.getChildren().add(addEnterLabRecord(labRecord, enterLabRecordViewController));
		createScene(vbox);
	}
	
	
	
	
}
