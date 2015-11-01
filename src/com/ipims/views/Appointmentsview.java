package com.ipims.views;

import java.time.LocalDate;

import com.ipims.Helper;
import com.ipims.appointment.AppointmentManager;
import com.ipims.appointment.AppointmentViewController;
import com.ipims.models.Appointment;
import com.ipims.models.Doctor;
import com.ipims.models.Patient;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

public class Appointmentsview extends BaseView {

	private ListView<String> listView;
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


		// Show schedule appointment if patient or HSP staff
		if (user.getUsertype() == UserType.PATIENT || 
				user.getUsertype() == UserType.HSPSTAFF) {

			vbox.getChildren().add(addScheduleAppoinment(null, parentController));
		}



		Text subTitle = new Text("Manage/View Appoinments");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);


		listView = new ListView<String>();
		listView.setItems(parentController.getAppoinmentList());
		listView.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					
					System.out.println(newValue);
					int index = listView.getSelectionModel().getSelectedIndex();
					parentController.didSelectItem(index);
				});
		vbox.getChildren().add(listView);
		createScene(vbox);

	}


	public VBox addScheduleAppoinment(Appointment appointment, AppointmentViewController parentController) {

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
		docComboBox.getItems().addAll(Helper.getDoctorList());

		Label categoryLabel = new Label("Category:");
		categoryLabel.setTextFill(Color.WHITE);
		ComboBox<String> catComboBox = new ComboBox<String>();
		catComboBox.getItems().addAll(Helper.getCategoryList());

		hbox2.getChildren().addAll(docLabel, docComboBox, categoryLabel, catComboBox);

		ComboBox<String> catPatientBox = new ComboBox<String>();
		if (UserSession.getInstance().getCurrentUser().getUsertype() != UserType.PATIENT) {
			Label patientLabel = new Label("Patient:");
			patientLabel.setTextFill(Color.WHITE);
			catPatientBox.getItems().addAll(Helper.getPatientList());

			hbox2.getChildren().add(patientLabel);
			hbox2.getChildren().add(catPatientBox);
		}
		baseVbox.getChildren().add(hbox2);

		// Fill in values for update
		if(appointment != null) {
			title.setText("Update Appointment");
			datePicker.setValue(appointment.getDate());
			timeTextField.setText(appointment.getTime());
			if (appointment.getDoctor() != null)
				docComboBox.setValue(appointment.getDoctor().getName());
			catComboBox.setValue(appointment.getCategory());

			if (catPatientBox != null) {
				catPatientBox.setValue(appointment.getPatient().getName());
			}
			// Add update and cancel buttons
			//
			HBox hbBtn = new HBox(10);
			Button updateBtn = new Button("Update Appointment");
			hbBtn.getChildren().add(updateBtn);
			updateBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					Doctor doctor = Helper.getDoctorAtIndex(docComboBox.getSelectionModel().getSelectedIndex());
					Patient patient = null;
					if (UserSession.getInstance().getCurrentUser().getUsertype() == UserType.PATIENT) {
						patient = (Patient)UserSession.getInstance().getCurrentUser();
					} else {
						patient = Helper.getPatientAtIndex(catPatientBox.getSelectionModel().getSelectedIndex());
					}

					Appointment newApp = new Appointment(datePicker.getValue(), timeTextField.getText(), doctor, patient, catComboBox.getSelectionModel().getSelectedItem());

					parentController.handleUpdateClick(newApp);

				}
			});

			Button cancelBtn = new Button("Cancel Appointment");
			hbBtn.getChildren().add(cancelBtn);
			cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					parentController.handleAppointmentCancellation();

				}
			});
			baseVbox.getChildren().add(hbBtn);

		} else {
			Button scheduleAppBtn = new Button("Submit");
			scheduleAppBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					Doctor doctor = Helper.getDoctorAtIndex(docComboBox.getSelectionModel().getSelectedIndex());
					Patient patient = null;
					if (UserSession.getInstance().getCurrentUser().getUsertype() == UserType.PATIENT) {
						patient = (Patient)UserSession.getInstance().getCurrentUser();
					} else {
						patient = Helper.getPatientAtIndex(catPatientBox.getSelectionModel().getSelectedIndex());
					}

					Appointment newApp = new Appointment(datePicker.getValue(), timeTextField.getText(), doctor, patient, catComboBox.getSelectionModel().getSelectedItem());
					parentController.handleSubmitClick(newApp);
				}
			});

			baseVbox.getChildren().add(scheduleAppBtn);
		}

		return baseVbox;
	}



	public void createUpdateAppoinmentView(Appointment appointment, AppointmentViewController parentController) {
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
				parentController.handleUpdateGoBack();

			}
		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().add(hbox);

		vbox.getChildren().add(addScheduleAppoinment(appointment, parentController));
		createScene(vbox);
	}

	public void refreshList(ObservableList<String>list) {
		listView.getItems().clear();
		listView.getItems().addAll(list);
	}
}
