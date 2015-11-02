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
		
	
		// Show Patient Case if Doctor
		//if (user.getUsertype() == UserType.PATIENT ) {
		//}
		
		
		
		ListView<String> list = new ListView<String>();
		
		/*list.setItems(labRecordViewController.getLabRecordList());
		list.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
		                System.out.println(newValue);
		                int index = list.getSelectionModel().getSelectedIndex();
		                labRecordViewController.didSelectItem(index);
		    });
		*/
		
		vbox.getChildren().add(addLabRecord(null, labRecordViewController, list));
		
		Text subTitle = new Text("View Lab Record");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);
		
		vbox.getChildren().add(list);
		
		
		// for success/error message
		//final Text actionTarget = new Text();
		

	
		
		createScene(vbox);
		
	}
	
	

	public VBox addLabRecord(LabRecord labrecord,  LabRecordViewController LabRecordViewController, ListView<String> list) {

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
			
			//title.setText("Update Appointment");
			//PatientTextField.setText("Gregg");
			//GlucoseTextField.setText("15mg");
			//SodiumTextField.setText("12mg");
			//CalciumTextField.setText("10mg");
			//MagnesiumTextField.setText("25mg");
			//
			
			
			
			
			
			// Add update and cancel buttons
			//
			
			HBox hbBtn = new HBox(10);
			Button updateBtn = new Button("Update Lab Record");
			hbBtn.getChildren().add(updateBtn);
			updateBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					
					LabRecordViewController.handleUpdateClick(null);
				
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
		
		Button LabRecordBtn = new Button("Submit");
		LabRecordBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				
				//GET LAB RECORD
				
				String labRecordInfo = "Lab Record Information:Blood Pressure\nGlucose\netc...";
				
				// setting lab record in database
				Patient tempPatient = Helper.getPatientAtIndex(catPatientBox.getSelectionModel().getSelectedIndex());
				
				/*labrecord.setCalcium(Integer.valueOf(CalciumTextField.getText()));
				labrecord.setGlucose(Integer.valueOf(GlucoseTextField.getText()));
				labrecord.setMagnesium(Integer.valueOf(MagnesiumTextField.getText()));
				labrecord.setSodium(Integer.valueOf(SodiumTextField.getText()));
				//labrecord.setPatient(tempPatient);
				labrecord.setPatientId(tempPatient.getUserId());
				*/
				
				// clear list
				ObservableList<String> items = LabRecordViewController.getLabRecordList();
				items.clear();
				
				// show all lab records for patient
				List<LabRecord> lrList = new ArrayList<LabRecord>();
				lrList = DatabaseManager.getInstance().getLabRecordsForPatient(tempPatient.getUserId());
				//System.out.println("List " + tempPatient.getUserId() + "\n");
				for(int i = 0; i < lrList.size(); i++) {
					String lrInfo = "Calcium: " + Float.toString(lrList.get(i).getCalcium());
					lrInfo += "Magnesium: " + Float.toString(lrList.get(i).getMagnesium());
					lrInfo += "Glucose: " + Float.toString(lrList.get(i).getGlucose());
					lrInfo += "Sodium: " + Float.toString(lrList.get(i).getSodium());
					items.add(lrInfo);
				}
				
				
	
				
				
			/*	if(PatientTextField.getText().equals("")) {
					actionTarget.setFill(Color.RED);
					actionTarget.setText("Error: No patient name entered!");
				}
				
				else {
					items.add(labRecordInfo);
					actionTarget.setFill(Color.GREEN);
					actionTarget.setText("Success");
				}
			*/	
			}
		});

		baseVbox.getChildren().add(LabRecordBtn);
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
		
		//vbox.getChildren().add(addLabRecord(labRecord, LabRecordViewController));
		createScene(vbox);
	}
	

	
}
