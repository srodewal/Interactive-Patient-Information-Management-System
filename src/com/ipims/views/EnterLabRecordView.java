package com.ipims.views;


import java.time.LocalDate;
import java.util.List;

import com.ipims.Helper;
import com.ipims.appointment.AppointmentViewController;
import com.ipims.database.DatabaseManager;
import com.ipims.labrecord.EnterLabRecordViewController;
import com.ipims.labrecord.LabRecordManager;
import com.ipims.labrecord.LabRecordViewController;
import com.ipims.models.Appointment;
import com.ipims.models.LabRecord;
import com.ipims.models.Patient;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

public class EnterLabRecordView extends BaseView{

	private ListView<String> listView;

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
		
		// This is what shows the view
			vbox.getChildren().add(addEnterLabRecord(null, enterLabRecordViewController));

				
			
		Text subTitle = new Text("View Lab Record");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);			
					
					
		listView = new ListView<String>();
	
		
		vbox.getChildren().add(listView);
		
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
		
		HBox hbox1 = new HBox();
		hbox1.setSpacing(10);
		hbox1.setAlignment(Pos.CENTER_LEFT);


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

		
		ComboBox<String> catPatientBox = new ComboBox<String>();

			Label patientLabel = new Label("Patient:");
			patientLabel.setTextFill(Color.WHITE);
			catPatientBox.getItems().addAll(Helper.getPatientList());
			
			hbox.getChildren().add(patientLabel);
			hbox.getChildren().add(catPatientBox);
			
			Label dateLabel = new Label("Date:");
			dateLabel.setTextFill(Color.WHITE);

			DatePicker datePicker = new DatePicker();
			datePicker.setPromptText("mm/dd/yyyy");
			datePicker.setMaxSize(120, 5);

			// following code makes it impossible to schedule appointment in the past
			LocalDate currentDate = LocalDate.now(); // current date
			/*if(datePicker.isBefore(currentDate)) {
			}*/
			final Callback<DatePicker, DateCell> dayCellFactory = 
					new Callback<DatePicker, DateCell>() {
				@Override
				public DateCell call(final DatePicker datePicker) {
					return new DateCell() {
						@Override
						public void updateItem(LocalDate item, boolean empty) {
							super.updateItem(item, empty);

							if (item.isBefore(currentDate)) {
								setDisable(true);
								setStyle("-fx-background-color: #ffc0cb;");
							}   
						}
					};
				}
			};
			datePicker.setDayCellFactory(dayCellFactory);
		
			hbox1.getChildren().add(dateLabel);
			hbox1.getChildren().add(datePicker);
		
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


		baseVbox.getChildren().addAll(hbox,hbox1,hbox2,hbox3,hbox4,hbox5);
				
		
		// Fill in values for update
		if(labrecord != null) {
			
			
			
			//PULL LAB_RECORD FROM DATABASE Using index of labrecord chosen
			//THEN DISPLAY SPECIFIC FIELDS FROM LABRECORD OBJECT PULLED
			String Calcium = String.valueOf(labrecord.getGlucose());
			String Glucose = String.valueOf(labrecord.getGlucose());
			String Sodium = String.valueOf(labrecord.getSodium());
			String Magnesium = String.valueOf(labrecord.getMagnesium());
			datePicker.setValue(labrecord.getDate());

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
					// USE UPDATE FUNCTION FROM DATABASE TO UPDATE LABRECORD
					
					
					LabRecord newLab = new LabRecord();

					
					float Calcium = Float.parseFloat(CalciumTextField.getText());
					float Glucose = Float.parseFloat(GlucoseTextField.getText());
					float Sodium = Float.parseFloat(SodiumTextField.getText());
					float Magnesium = Float.parseFloat(MagnesiumTextField.getText());
					Patient patient = Helper.getPatientAtIndex(catPatientBox.getSelectionModel().getSelectedIndex());					
					int patientid = patient.getUserId();
					
					newLab.setPatientId(patientid);
					newLab.setDate(datePicker.getValue());
					newLab.setGlucose(Glucose);
					newLab.setSodium(Sodium);
					newLab.setCalcium(Calcium);
					newLab.setMagnesium(Magnesium);

					enterLabRecordViewController.handleUpdateClick(newLab);
					

				}
			});
			
			Button cancelBtn = new Button("Delete Lab Record");
			hbBtn.getChildren().add(cancelBtn);
			cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					
					
					// USE DELETE FUNCTION FROM DATABASE TO DELETE LABRECORD
					
					enterLabRecordViewController.handleLabRecordDeletion();
					
				
				}
			});
			baseVbox.getChildren().add(hbBtn);
			
		} else {
			Button EnterLabRecordBtn = new Button("Submit");
			EnterLabRecordBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					
				
					LabRecord newLab = new LabRecord();
					
					float Calcium = Float.parseFloat(CalciumTextField.getText());
					float Glucose = Float.parseFloat(GlucoseTextField.getText());
					float Sodium = Float.parseFloat(SodiumTextField.getText());
					float Magnesium = Float.parseFloat(MagnesiumTextField.getText());
					Patient patient = Helper.getPatientAtIndex(catPatientBox.getSelectionModel().getSelectedIndex());					
					int patientid = patient.getUserId();
					
					//Add user inputs to labRecord model class variables

					newLab.setPatientId(patientid);
					newLab.setDate(datePicker.getValue());
					newLab.setGlucose(Glucose);
					newLab.setSodium(Sodium);
					newLab.setCalcium(Calcium);
					newLab.setMagnesium(Magnesium);
					
					
					enterLabRecordViewController.handleSubmitClick(newLab);
					
					
					listView.setItems(enterLabRecordViewController.getPatientLabRecordList(patientid));
					listView.getSelectionModel().selectedItemProperty().addListener(
							(ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
								
								System.out.println(newValue);
								int index = listView.getSelectionModel().getSelectedIndex();
								enterLabRecordViewController.didSelectItem(index, patientid);
							});
					
					
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
