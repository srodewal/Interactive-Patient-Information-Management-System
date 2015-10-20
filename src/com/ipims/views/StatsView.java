package com.ipims.views;

import com.ipims.stats.StatsViewController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StatsView extends BaseView {
	public void createStatsView(StatsViewController parentController) {

		GridPane statsPane = new GridPane();
		statsPane.setAlignment(Pos.CENTER);
		statsPane.setHgap(10);
		statsPane.setVgap(10);
		statsPane.setPadding(new Insets(25, 25, 25, 25));

		Text statsText = new Text("Generate Statistical Reports");
		statsText.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
		statsPane.add(statsText, 0, 0, 2, 1);

		Button patientPopulations = new Button("Analysis of patient populations");
		HBox ppBtn = new HBox(10);
		ppBtn.getChildren().add(patientPopulations);
		statsPane.add(ppBtn, 1, 1);

		Button healthOutcomes = new Button("Analysis of health outcomes");
		HBox hoBtn = new HBox(10);
		hoBtn.getChildren().add(healthOutcomes);
		statsPane.add(hoBtn, 1, 2);
		
		Button trackingAdmission = new Button("Tracking of the admission rates");
		HBox taBtn = new HBox(10);
		taBtn.getChildren().add(trackingAdmission);
		statsPane.add(taBtn, 1, 3);
		
		Button typePatients = new Button("Analysis of type of patients");
		HBox tpBtn = new HBox(10);
		tpBtn.getChildren().add(typePatients);
		statsPane.add(tpBtn, 1, 4);
		
		Button backBtn = new Button("Back to Menu");
		HBox bBtn = new HBox(10);
		bBtn.getChildren().add(backBtn);
		statsPane.add(bBtn, 1, 5);

		final Text actiontarget = new Text();
		statsPane.add(actiontarget, 1, 6);

		patientPopulations.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
					actiontarget.setFill(Color.RED);
					actiontarget.setText("patient populations button pressed");
					parentController.handlePP();
				}
		});
		
		healthOutcomes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
					actiontarget.setFill(Color.RED);
					actiontarget.setText("Health outcomes button pressed");
					parentController.handleHA();
				}
		});
		
		trackingAdmission.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
					actiontarget.setFill(Color.RED);
					actiontarget.setText("Tracking admission rates button pressed");
					parentController.handleTA();
				}
		});
		
		typePatients.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
					actiontarget.setFill(Color.RED);
					actiontarget.setText("Analysis of type of patients button pressed");
					parentController.handleTP();
				}
		});
		
		backBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
					parentController.goBack();
				}
		});
		createScene(statsPane);
		
	}
}
