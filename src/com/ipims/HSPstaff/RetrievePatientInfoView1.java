package com.ipims.HSPstaff;
//edited by Kevin George ( I kind of stole the LoginView and worked off of that to create something similar to the GUI examples provided in the SDD).

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


public class RetrievePatientInfoView1 extends BaseView {

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

		// Create Submit Button

		Button SubmitBtn = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(SubmitBtn);
		grid.add(hbBtn, 1, 4);



		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		//If login button is pressed

		SubmitBtn.setOnAction(new EventHandler<ActionEvent>() {

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
