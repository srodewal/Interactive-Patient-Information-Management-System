package com.ipims.login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Interactive Patient Information Management System");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Login");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Sign in button pressed");
                
                // these if statements should send userTextField.getText() to database to see if user in database
                if(userTextField.getText().equals("Doctor")) {
                	actiontarget.setFill(Color.RED);
                    actiontarget.setText("Doctor Logged In");
                }
                if(userTextField.getText().equals("Patient")) {
                	actiontarget.setFill(Color.RED);
                    actiontarget.setText("Patient Logged In");
                }
                if(userTextField.getText().equals("Nurse")) {
                	actiontarget.setFill(Color.RED);
                    actiontarget.setText("Nurse Logged In");
                }
                if(userTextField.getText().equals("Lab Staff")) {
                	actiontarget.setFill(Color.RED);
                    actiontarget.setText("Lab Staff Logged In");
                }
            }
        });

        Scene scene = new Scene(grid, 600, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}