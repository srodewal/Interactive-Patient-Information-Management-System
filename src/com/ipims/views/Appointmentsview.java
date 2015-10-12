package com.ipims.views;

import java.util.List;

import com.ipims.appointment.AppointmentViewController;
import com.ipims.models.Appointment;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Appointmentsview extends BaseView {

	public void createAppointmentsView(User user, AppointmentViewController parentController) {

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
		
		ListView<String> list = new ListView<String>();
		/*ObservableList<String> items = FXCollections.observableArrayList (
				"1. Dr. John (Category: Eye) on 10/12/2015 at 14:35",
				"2. Dr. John (Category: Eye) on 10/1/2015 at 10:00");*/
		ObservableList<String> items = FXCollections.observableArrayList (
			);
		//list.setItems(items);
		
		// Show schedule appointment if patient or HSP staff
		if (user.getUsertype() == UserType.PATIENT ) {
			vbox.getChildren().add(addScheduleAppoinment(items));
		}
		
		list.setItems(items); // moved

		Text subTitle = new Text("Manage/View Appoinments");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);


		
		vbox.getChildren().add(list);
		
		Button cancelBtn = new Button("Cancel Appointment");
		HBox hbBtn = new HBox(10);
		//hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(cancelBtn);
		vbox.getChildren().add(cancelBtn);
		
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// call destructor to remove appointment object
				// remove currently selected appointment
				int index = list.getSelectionModel().getSelectedIndex();
				
				items.remove(index);
			}
		});
		
		currentScene = new Scene(vbox, 500, 700);
	}

	public VBox addScheduleAppoinment(ObservableList<String> items) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		Text title = new Text("Schedule Appoinment");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		title.setFill(Color.WHITE);

		baseVbox.getChildren().add(title);

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		Label dateLabel = new Label("Date:");
		dateLabel.setTextFill(Color.WHITE);

		DatePicker datePicker = new DatePicker();
		datePicker.setPromptText("mm/dd/yyyy");
		datePicker.setMaxSize(120, 5);

		Label timeLabel = new Label("Time:");
		timeLabel.setTextFill(Color.WHITE);
		TextField timeTextField = new TextField();
		timeTextField.setPromptText("HH:MM");
		timeTextField.setMaxSize(110, 5);

		hbox.getChildren().addAll(dateLabel, datePicker, timeLabel, timeTextField);
		baseVbox.getChildren().add(hbox);

		HBox hbox2 = new HBox();
		hbox2.setSpacing(10);

		Label docLabel = new Label("Doctor:");
		docLabel.setTextFill(Color.WHITE);

		ComboBox<String> docComboBox = new ComboBox<String>();
		docComboBox.getItems().addAll(
				"John",
				"Bob" 
				);

		Label categoryLabel = new Label("Category:");
		categoryLabel.setTextFill(Color.WHITE);
		ComboBox<String> catComboBox = new ComboBox<String>();
		catComboBox.getItems().addAll(
				"Heart",
				"Eye" 
				);

		hbox2.getChildren().addAll(docLabel, docComboBox, categoryLabel, catComboBox);
		baseVbox.getChildren().add(hbox2);


		Button scheduleAppBtn = new Button("Submit");
		scheduleAppBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				//System.out.println(docComboBox.getValue()); // for testing
				//Appointment newApp = new Appointment(datePicker.getValue(), timeTextField.getText(), docComboBox.getAccessibleText(), UserSession.getInstance().getCurrentUser(), catComboBox.getAccessibleText());
				String scheduledApp = "Dr. " + (String)docComboBox.getValue();
				scheduledApp += " (Category: " + catComboBox.getValue() + " ) on ";
				scheduledApp += datePicker.getValue() + " at ";
				scheduledApp += timeTextField.getText();
				//items.add(scheduledApp);
				if(!items.contains(scheduledApp)) {
					items.add(scheduledApp);
				}
			}
		});

		baseVbox.getChildren().add(scheduleAppBtn);

		return baseVbox;
	}
}
