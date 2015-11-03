package com.ipims.views;

import java.util.ArrayList;
import java.util.List;

import com.ipims.Helper;
import com.ipims.database.DatabaseManager;
import com.ipims.labrecord.EnterLabRecordViewController;
import com.ipims.labrecord.LabRecordViewController;
import com.ipims.models.LabRecord;
import com.ipims.models.Patient;
import com.ipims.models.Prescription;
import com.ipims.models.User;
import com.ipims.patientcase.PatientCaseViewController;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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



public class LabRecordView extends BaseView {

	private ListView<String> listView;
	
	public void createLabRecordView(User user, LabRecordViewController labRecordViewController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		
		Text title = new Text("Access Patient Lab Record");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		hbox.getChildren().add(title);

		Button mainMenuBtn = new Button("Main Menu");
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				labRecordViewController.goBack();

			}
			
		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().add(hbox);
		
	
		// This is what shows the view
		vbox.getChildren().add(addLabRecord(null, labRecordViewController));

		
		
		Text subTitle = new Text("View Lab Record");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);
				
		listView = new ListView<String>();

		
		vbox.getChildren().add(listView);
	
		
		createScene(vbox);
		
	}
	
	

	public VBox addLabRecord(LabRecord labrecord,  LabRecordViewController LabRecordViewController) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		ComboBox<String> catPatientBox = new ComboBox<String>();
		//if (UserSession.getInstance().getCurrentUser().getUsertype() == UserType.LABSTAFF) {
			Label patientLabel = new Label("Patient:");
			patientLabel.setTextFill(Color.WHITE);
			catPatientBox.getItems().addAll(Helper.getPatientList());
			
			hbox.getChildren().add(patientLabel);
			hbox.getChildren().add(catPatientBox);
		//}
			
		
			
		baseVbox.getChildren().add(hbox);


		
		// Fill in values for update
		if(labrecord != null) {
			
			//Add Glucose, Magnesium, sodium, and calcium fields
			
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
			
				
			hbox2.getChildren().addAll(GlucoseLabel,GlucoseTextField);
			hbox3.getChildren().addAll(SodiumLabel, SodiumTextField);
			hbox4.getChildren().addAll(CalciumLabel, CalciumTextField);
			hbox5.getChildren().addAll(MagnesiumLabel, MagnesiumTextField);

			baseVbox.getChildren().addAll(hbox2, hbox3, hbox4, hbox5);

			// Add what should go into all fields when lab record is selected
			
			String Calcium = String.valueOf(labrecord.getGlucose());
			String Glucose = String.valueOf(labrecord.getGlucose());
			String Sodium = String.valueOf(labrecord.getSodium());
			String Magnesium = String.valueOf(labrecord.getMagnesium());
			
			
			CalciumTextField.setText(Calcium);
			GlucoseTextField.setText(Glucose);
			SodiumTextField.setText(Sodium);
			MagnesiumTextField.setText(Magnesium);

			if (catPatientBox != null) {
				catPatientBox.setValue(DatabaseManager.getInstance().getUser(labrecord.getPatientId()).getName());
			}
			
			
			// Add update and cancel buttons
			//
			
			HBox hbBtn = new HBox(10);
			Button updateBtn = new Button("Update Lab Record");
			hbBtn.getChildren().add(updateBtn);
			updateBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					
					
					LabRecord newLab = new LabRecord();

					
					float Calcium = Float.parseFloat(CalciumTextField.getText());
					float Glucose = Float.parseFloat(GlucoseTextField.getText());
					float Sodium = Float.parseFloat(SodiumTextField.getText());
					float Magnesium = Float.parseFloat(MagnesiumTextField.getText());
					Patient patient = Helper.getPatientAtIndex(catPatientBox.getSelectionModel().getSelectedIndex());					
					int patientid = patient.getUserId();
					
					newLab.setPatientId(patientid);
					newLab.setGlucose(Glucose);
					newLab.setSodium(Sodium);
					newLab.setCalcium(Calcium);
					newLab.setMagnesium(Magnesium);

					
					LabRecordViewController.handleUpdateClick(newLab);
				
				}
			});
			
			Button cancelBtn = new Button("Delete Lab Record");
			hbBtn.getChildren().add(cancelBtn);
			cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					LabRecordViewController.handleLabRecordDeletion();
					
				
				}
			});
			baseVbox.getChildren().add(hbBtn);
		}
		
		else{
		
		//Button LabRecordBtn = new Button("Submit");
		 catPatientBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				
				//GET LAB RECORD
	

				Patient	patient = Helper.getPatientAtIndex(catPatientBox.getSelectionModel().getSelectedIndex());
				int patientid = patient.getUserId();
			

				listView.setItems(LabRecordViewController.getPatientLabRecordList(patientid));
				listView.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					
					System.out.println(newValue);
					int index = listView.getSelectionModel().getSelectedIndex();
					LabRecordViewController.didSelectItem(index, patientid);
				});
		
				if(listView.getItems().isEmpty() == true){
					
					showInfo("Patient has no lab records");

				}
				
			}
		});

		//baseVbox.getChildren().add(LabRecordBtn);
		}
		
		return baseVbox;
	}
	

	public void createUpdateLabRecordView(LabRecord labRecord, LabRecordViewController LabRecordViewController) {
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(15));
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
				
				
				LabRecordViewController.handleUpdateGoBack();


			}
		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().addAll(hbox);
		
		vbox.getChildren().add(addLabRecord(labRecord, LabRecordViewController));
		createScene(vbox);
	}

	
}
