package com.ipims.views;

import java.time.LocalDate;

import com.ipims.appointment.AppointmentViewController;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.stats.ReportsViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

public class ReportsView extends BaseView {
	public void createReportsView(ReportsViewController parentController, Text reportTitle) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		
		//Text title = new Text("Reports");
		reportTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		hbox.getChildren().add(reportTitle);

		Button mainMenuBtn = new Button("View Another Report");
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				parentController.goBackStats();


			}
		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().add(hbox);
		
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
				"Report goes here!",
				"Some information.."
			);
		
		final Text actionTarget = new Text();
		
		list.setItems(items); // moved

		Text subTitle = new Text("Current Report");
		subTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);
		vbox.getChildren().add(list);
		
		vbox.getChildren().add(addReport(items, actionTarget));
		
		// to display success or error message
		vbox.getChildren().add(actionTarget);
		
		currentScene = new Scene(vbox, 500, 700);
	}

	public VBox addReport(ObservableList<String> items, Text actionTarget) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		Text title = new Text("Would you like to print this report?");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		title.setFill(Color.WHITE);

		baseVbox.getChildren().add(title);

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		//hbox.getChildren().addAll();
		//baseVbox.getChildren().add(hbox);

		HBox hbox2 = new HBox();
		hbox2.setSpacing(10);

		//baseVbox.getChildren().add(hbox2);

		Button printBtn = new Button("Print");
		printBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// to-do
				actionTarget.setFill(Color.GREEN);
				actionTarget.setText("Print button pressed!");
			}
		});

		baseVbox.getChildren().add(printBtn);

		return baseVbox;
	}
}