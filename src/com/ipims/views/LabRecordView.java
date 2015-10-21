package com.ipims.views;

import com.ipims.labrecord.LabRecordViewController;
import com.ipims.models.User;
import com.ipims.patientcase.PatientCaseViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
			vbox.getChildren().add(addLabRecord(items, actionTarget));
		//}
		
		list.setItems(items); // moved

		Text subTitle = new Text("View Lab Record");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);


		
		vbox.getChildren().addAll(list, actionTarget);
		
		createScene(vbox);
		
	}

	public VBox addLabRecord(ObservableList<String> items, Text actionTarget) {

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


		Button LabRecordBtn = new Button("Submit");
		LabRecordBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				
				
				String labRecordInfo = "Lab Record Information:Blood Pressure\nGlucose\netc...";
				
				if(PatientTextField.getText().equals("")) {
					actionTarget.setFill(Color.RED);
					actionTarget.setText("Error: No patient name entered!");
				}
				
				else {
					items.add(labRecordInfo);
					actionTarget.setFill(Color.GREEN);
					actionTarget.setText("Success");
				}
				
			}
		});

		baseVbox.getChildren().add(LabRecordBtn);

		return baseVbox;
	}

	
}
