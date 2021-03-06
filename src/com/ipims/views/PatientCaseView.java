package com.ipims.views;


import com.ipims.Helper;
import com.ipims.healthconditions.PatientCaseViewController;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PatientCaseView extends BaseView{

	private ListView<String> listView;

	public void createPatientCaseView(User user, PatientCaseViewController patientCaseViewController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		Text title = new Text("Access Patient Case");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		hbox.getChildren().add(title);

		Button mainMenuBtn = new Button("Main Menu");
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				patientCaseViewController.goBack();

			}

		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().add(hbox);

		this.listView = new ListView<String>();


		// for success/error message
		final Text actionTarget = new Text();

		// Show Patient Case if Doctor/HSP
		if (user.getUsertype() != UserType.PATIENT ) {
			vbox.getChildren().add(addPatientCaseTopView(patientCaseViewController));
		}


		Text subTitle = new Text("Patient Case");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);
		vbox.getChildren().addAll(this.listView, actionTarget);
		createScene(vbox);

	}

	public VBox addPatientCaseTopView(PatientCaseViewController patientCaseViewController) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		Label patientLabel = new Label("Patient:");
		patientLabel.setTextFill(Color.WHITE);

		// get all patients
		ComboBox<String> patientComboBox = new ComboBox<String>();
		patientComboBox.getItems().addAll(Helper.getPatientList());
		// end get all patients

		patientComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				patientCaseViewController.patientSelected(Helper.getPatientAtIndex(patientComboBox.getSelectionModel().getSelectedIndex()));


			}

		});
		hbox.getChildren().addAll(patientLabel, patientComboBox);
		baseVbox.getChildren().add(hbox);




		return baseVbox;
	}

	public void refreshList(ObservableList<String>list) {
		listView.getItems().clear();
		listView.getItems().addAll(list);
	}
}



















