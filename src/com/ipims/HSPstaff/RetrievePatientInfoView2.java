package com.ipims.HSPstaff;
//edited by Kevin George Not really done at all but it exists!!!!

import com.ipims.views.*;
import com.ipims.usersession.LoginViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class RetrievePatientInfoView2 extends BaseView {

	public void createLoginView(LoginViewController parentController) {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Retrieve Patient Information");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Patient Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);
                
                Label addressField = new Label("Address:");
		grid.add(addressField, 0, 1);

		TextField addressTextField = new TextField();
		grid.add(addressTextField, 1, 1);
                
                Label Email = new Label("Email:");
		grid.add(Email, 0, 1);

		TextField emailField = new TextField();
		grid.add(emailField, 1, 1);
                
                Label phoneNumber = new Label("Phone Number:");
		grid.add(phoneNumber, 0, 1);

		TextField phoneNumberField = new TextField();
		grid.add(phoneNumberField, 1, 1);
                
                Label SSN = new Label("Social Security Number:");
		grid.add(SSN, 0, 1);

		TextField SSNField = new TextField();
		grid.add(SSNField, 1, 1);
                
                Label HIP = new Label("Health Insurance Provider:");
		grid.add(HIP, 0, 1);

		TextField HIPField = new TextField();
		grid.add(HIPField, 1, 1);

		// Create Done Button

		Button DoneBtn = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(DoneBtn);
		grid.add(hbBtn, 1, 4);



		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		//If login button is pressed

		DoneBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				System.out.println(userTextField.getText()); // for testing
//				System.out.println(pwBox.getText()); // for testing
//				parentController.handleLoginButtonClick(userTextField.getText(), pwBox.getText());

			}
		});

		//If New User button is pressed
//		btn2.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent e) {
//
//				parentController.handleNewUserButtonClick();
//
//			}
//		});


		currentScene = new Scene(grid, 300, 275);


	}
}
