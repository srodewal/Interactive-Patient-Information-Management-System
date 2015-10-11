package com.ipims.views;

import com.ipims.MenuViewController;
import com.ipims.healthconditions.HealthViewController;
import com.ipims.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HealthView extends BaseView {

	public void createHealthview(User user, HealthViewController parentController) {
//		GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//
//        Text scenetitle = new Text("Update Healthcare Condition");
//        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//        grid.add(scenetitle, 0, 0, 2, 1);
//
//        Label patientName = new Label("Patient Name: ");
//        grid.add(patientName, 0, 1);
//
//        TextField patientNameTextField = new TextField();
//        grid.add(patientNameTextField, 1, 1);
//
//        Label condition = new Label("Enter Updated Conditions: ");
//        grid.add(condition, 0, 2, 2, 2);
//
//        TextField conditionTextField = new TextField();
//        grid.add(conditionTextField, 0, 4, 2, 2);
//
//        Button submitBtn = new Button("Submit");
//        HBox submitBox = new HBox(10);
//        submitBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        submitBox.getChildren().add(submitBtn);
//        grid.add(submitBtn, 1, 6);
//        
//        Button backBtn = new Button("Back");
//        HBox backBox = new HBox(10);
//        backBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        backBox.getChildren().add(backBtn);
//        grid.add(backBtn, 1, 7);
//
//        final Text actiontarget = new Text();
//        grid.add(actiontarget, 1, 6);
//
//        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent e) {
//                actiontarget.setFill(Color.GREEN);
//               actiontarget.setText("Condition recorded.");
//            	
//            	
//            }
//        });
//        
//        backBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent e) {
//                actiontarget.setFill(Color.GREEN);
//                actiontarget.setText("Back Pressed!"); 
//            	
//            	
//            }
//        });
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		
		Text title = new Text("Appoinments");
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
		
		vbox.getChildren().add(addMedicalHistory());
		vbox.getChildren().add(addMedicalCondition());
		
		Text subTitle = new Text("View Health Conditions");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);
		
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
				"1. (History) Severe Chest Pain");
		list.setItems(items);
		vbox.getChildren().add(list);
		
        currentScene = new Scene(vbox, 500, 700);
	}
	
	public VBox addMedicalHistory() {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		Text title = new Text("Medical History");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		title.setFill(Color.WHITE);

		baseVbox.getChildren().add(title);

//		HBox hbox = new HBox();
//		hbox.setSpacing(10);
//
//		Label dateLabel = new Label("Date:");
//		dateLabel.setTextFill(Color.WHITE);
//
//		DatePicker datePicker = new DatePicker();
//		datePicker.setPromptText("mm/dd/yyyy");
//		datePicker.setMaxSize(120, 5);
//
//		Label timeLabel = new Label("Time:");
//		timeLabel.setTextFill(Color.WHITE);
//		TextField timeTextField = new TextField();
//		timeTextField.setPromptText("HH:MM");
//		timeTextField.setMaxSize(110, 5);
//
//		hbox.getChildren().addAll(dateLabel, datePicker, timeLabel, timeTextField);
//		baseVbox.getChildren().add(hbox);
//
//		HBox hbox2 = new HBox();
//		hbox2.setSpacing(10);
//
//		Label docLabel = new Label("Doctor:");
//		docLabel.setTextFill(Color.WHITE);
//
//		ComboBox<String> docComboBox = new ComboBox<String>();
//		docComboBox.getItems().addAll(
//				"John",
//				"ASD" 
//				);
//
//		Label categoryLabel = new Label("Category:");
//		categoryLabel.setTextFill(Color.WHITE);
//		ComboBox<String> catComboBox = new ComboBox<String>();
//		catComboBox.getItems().addAll(
//				"Heart",
//				"Eye" 
//				);
//
//		hbox2.getChildren().addAll(docLabel, docComboBox, categoryLabel, catComboBox);
//		baseVbox.getChildren().add(hbox2);
//
//
//		Button scheduleAppBtn = new Button("Submit");
//		scheduleAppBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent e) {
//				// Pass the control of handling button clicks to the view controller
//
//
//			}
//		});
//
//		baseVbox.getChildren().add(scheduleAppBtn);

		return baseVbox;
	}
	
	public VBox addMedicalCondition() {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		Text title = new Text("Current Medical Condition");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		title.setFill(Color.WHITE);

		baseVbox.getChildren().add(title);


		return baseVbox;
	}
	

}
