package com.ipims.views;

import com.ipims.healthconditions.HealthViewController; // should be taken out
import com.ipims.hsp.ViewPatientInfoViewController;
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

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);
		
		// moved
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
				"");
		list.setItems(items);

		// Add title and main menu 
		HBox hbox = new HBox();
		hbox.setSpacing(10);

		Text title = new Text("View Patient Information");
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
		//if(user.getUsertype() == UserType.PATIENT) {
			HBox hbox2 = new HBox();
			hbox2.setSpacing(10);

			Label PatientLabel = new Label("Patient:");
			
			PatientLabel.setTextFill(Color.BLACK);
			PatientLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

			TextField PatientTextField = new TextField();

			PatientTextField.setMaxSize(250, 55);

			hbox2.getChildren().addAll(PatientLabel, PatientTextField);
			vbox.getChildren().add(hbox2);
		//}

		// Add Medical History box
		vbox.getChildren().add(addMedicalHistory(items));

		// Add Medical Condition box
		//vbox.getChildren().add(addMedicalCondition(parentController, items));

		//Text subTitle = new Text("View Health Conditions");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		//vbox.getChildren().add(subTitle);

		/*ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
				"1. (History) Severe Chest Pain");
		list.setItems(items);*/
		vbox.getChildren().add(list);
		createScene(vbox);
		
	}

	public VBox addMedicalHistory(ObservableList<String> items) {

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
		conditionComboBox.getItems().addAll(
				"Allergies",
				"Chest Pain",
				"Heart Problems",
				"Diabetes"
				);

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
					String updatedHistory = "(History) " + conditionComboBox.getValue() + "\n" + commentsTextField.getText();
					items.add(updatedHistory);
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
}
