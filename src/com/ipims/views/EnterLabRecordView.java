package com.ipims.views;

import com.ipims.labrecord.EnterLabRecordViewController;
import com.ipims.labrecord.LabRecordViewController;
import com.ipims.models.User;

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
			vbox.getChildren().add(addEnterLabRecord(items, actionTarget));
		//}
		
		list.setItems(items); // moved

		Text subTitle = new Text("View Lab Record");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);
		
		
		vbox.getChildren().addAll(list, actionTarget);
		
		createScene(vbox);
		
	}

	public VBox addEnterLabRecord(ObservableList<String> items, Text actionTarget) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");
		
		
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
				
		
		
		Button EnterLabRecordBtn = new Button("Submit");
		EnterLabRecordBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				
				
				String enterLabRecordInfo = "Patient Name: "+PatientTextField.getText() + "\n";
				enterLabRecordInfo += "Glucose Level: " + GlucoseTextField.getText() + "\n";
				enterLabRecordInfo += "Sodium Level: " + SodiumTextField.getText() + "\n";
				enterLabRecordInfo += "Calcium Level: " + CalciumTextField.getText() + "\n";
				enterLabRecordInfo += "Magnesium Level: " + MagnesiumTextField.getText() + "\n";

				
				if(PatientTextField.getText().equals("")) {
					actionTarget.setFill(Color.RED);
					actionTarget.setText("Error: No patient name entered!");
				}
				
				else {
					items.add(enterLabRecordInfo);
					actionTarget.setFill(Color.GREEN);
					actionTarget.setText("Success");
				}
				
			}
		});

		baseVbox.getChildren().add(EnterLabRecordBtn);

		return baseVbox;
	}

	
	
	
	
	
	
	
	
	
}
