package com.ipims.views;

import com.ipims.Helper;
import com.ipims.healthconditions.HealthConditionManager;
import com.ipims.healthconditions.HealthViewController;
import com.ipims.models.HealthCondition;

import com.ipims.models.User;
import com.ipims.models.User.UserType;
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


public class HealthView extends BaseView {

	
	private ListView<String> listView;
	public void createHealthview(User user, HealthViewController parentController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);


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
		if(user.getUsertype() != UserType.PATIENT) {
			HBox hbox2 = new HBox();
			hbox2.setSpacing(10);

			Label PatientLabel = new Label("Patient:");

			PatientLabel.setTextFill(Color.BLACK);
			PatientLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

			ComboBox<String> patientComboBox = new ComboBox<String>();
			patientComboBox.getItems().addAll(Helper.getPatientList());

			patientComboBox.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					parentController.patientAtIndexSelected(patientComboBox.getSelectionModel().getSelectedIndex());
		
					
				}
				
			});
			hbox2.getChildren().addAll(PatientLabel, patientComboBox);
			vbox.getChildren().add(hbox2);
		}

		
		// Add Medical History box
		vbox.getChildren().add(addMedicalHistory(parentController));

		// Add Medical Condition box
		vbox.getChildren().add(addMedicalCondition(parentController));

		Text subTitle = new Text("View Health Conditions");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);

		listView = new ListView<String>();
		listView.getItems().addAll(parentController.getWholeHealthConditionObsList());
		vbox.getChildren().add(listView);
		createScene(vbox);

	}

	public VBox addMedicalHistory(HealthViewController parentController) {

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
		conditionComboBox.getItems().addAll(parentController.getHealthHistoryObsList());

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
					
					HealthCondition healthCondition = HealthConditionManager.healthHistoryAtIndex(conditionComboBox.getSelectionModel().getSelectedIndex());
					parentController.handleSubmitHc(healthCondition);
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

	public VBox addMedicalCondition(HealthViewController parentController) {

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
		conditionComboBox.getItems().addAll(parentController.getHealthConditionObsList());

		Label commentsLabel = new Label("Comments:");
		commentsLabel.setTextFill(Color.WHITE);

		TextField commentsTextField = new TextField();

		commentsTextField.setMaxSize(400, 55);

		Button submitBtn = new Button("Submit");

		baseVbox.getChildren().addAll(title, 
				conditionLabel, 
				conditionComboBox, 
				commentsLabel, 
				commentsTextField,
				submitBtn);

		submitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller 
				if(!conditionComboBox.equals(null)) {
					HealthCondition healthCondition = HealthConditionManager.healthConditionAtIndex(conditionComboBox.getSelectionModel().getSelectedIndex());
					parentController.handleSubmitHc(healthCondition);
				}

			}
		});

		return baseVbox;
	}

	public void refreshList(ObservableList<String>list) {
		listView.getItems().clear();
		listView.getItems().addAll(list);
	}
}
