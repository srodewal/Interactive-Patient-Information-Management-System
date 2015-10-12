package com.ipims.views;

import java.time.LocalDate;

import com.ipims.models.Patient;
import com.ipims.models.User;
import com.ipims.usersession.RegistrationViewController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Registrationview extends BaseView {

	public void createRegistrationMenu (RegistrationViewController parentController) {

		GridPane registerPane = new GridPane();
		registerPane.setAlignment(Pos.CENTER);
		registerPane.setHgap(10);
		registerPane.setVgap(10);
		registerPane.setPadding(new Insets(25, 25, 25, 25));

		Text registerText = new Text("Register");
		registerText.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
		registerPane.add(registerText, 0, 0, 2, 1);

		Label name = new Label("Name:");
		registerPane.add(name, 0, 1);
		TextField nameTextField = new TextField();
		registerPane.add(nameTextField, 1, 1);

		Label userName = new Label("User Name:");
		registerPane.add(userName, 0, 2);
		TextField userNameTextField = new TextField();
		userNameTextField.setPromptText("Enter a unique username");
		registerPane.add(userNameTextField, 1, 2);
		
		Label password = new Label("Password:");
		registerPane.add(password, 0, 3);
		TextField passwordTextField = new TextField();
		passwordTextField.setPromptText("Enter a unique password");
		registerPane.add(passwordTextField, 1, 3);

		Label dateOfBirth = new Label("Date of Birth:");
		registerPane.add(dateOfBirth, 0, 4);

		DatePicker datePicker = new DatePicker();
		datePicker.setPromptText("mm/dd/yyyy");
		registerPane.add(datePicker, 1, 4);

		Label currentAddress = new Label("Current Address:");
		registerPane.add(currentAddress, 0, 5);
		TextField currentAddressTextField = new TextField();
		registerPane.add(currentAddressTextField, 1, 5);

		Label ssnNumber = new Label("Social Security Number:");
		registerPane.add(ssnNumber, 0, 6);
		TextField ssnNumberTextField = new TextField();
		registerPane.add(ssnNumberTextField, 1, 6);

		Label phoneNumber = new Label("Phone Number:");
		registerPane.add(phoneNumber, 0, 7);
		TextField phoneNumberTextField = new TextField();
		registerPane.add(phoneNumberTextField, 1, 7);

		Label email = new Label("Email:");
		registerPane.add(email, 0, 8);
		TextField emailTextField = new TextField();
		registerPane.add(emailTextField, 1, 8);

		Label healthInsuranceProvider = new Label("Health Insurance Provider:");
		registerPane.add(healthInsuranceProvider, 0, 9);
		TextField healthInsuranceTextField = new TextField();
		registerPane.add(healthInsuranceTextField, 1, 9);

		//Label pw = new Label("Password:");
		//registerPane.add(pw, 0, 2);

		//PasswordField pwBox = new PasswordField();
		//registerPane.add(pwBox, 1, 2);

		Button btn = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		registerPane.add(hbBtn, 1, 10);

		Button btn2 = new Button("Back");
		HBox hbBtn2 = new HBox(10);
		hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn2.getChildren().add(btn2);
		registerPane.add(hbBtn2, 1, 11);


		final Text actiontarget = new Text();
		registerPane.add(actiontarget, 1, 12);

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				actiontarget.setFill(Color.RED);
				actiontarget.setText("Information submitted");
//TODO: Validate input
				if(true) {
					User patient = new Patient(userNameTextField.getText(), passwordTextField.getText());
					System.out.println(userNameTextField.getText()); // for testing
					System.out.println(passwordTextField.getText()); // for testing
					validUsers.put(userNameTextField.getText(), passwordTextField.getText());
					System.out.println(validUsers.values());
					// Uncomment below lines when database is setup and validation is done.
					
//					patient.setName(nameTextField.getText());
//					patient.setAddress(currentAddressTextField.getText());
//					patient.setSsn(ssnNumberTextField.getText());
//					LocalDate date = datePicker.getValue();
//					patient.setDateOfBirth(date.toString());
//					//                insuranceProviderToDatabase = healthInsuranceTextField.getText();
//					patient.setEmail(emailTextField.getText());
//					patient.setPhoneNumber(phoneNumberTextField.getText());
					parentController.handleRegister(patient);

					// go back to login
					parentController.handleBackButton();
				}
				

			}
		});


		btn2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				parentController.handleBackButton();

			}
		});

		currentScene = new Scene(registerPane, 600, 550);
	}
	
}
