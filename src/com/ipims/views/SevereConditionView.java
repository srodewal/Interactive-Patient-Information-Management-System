package com.ipims.views;


import com.ipims.healthconditions.SevereConditionViewController;
import com.ipims.models.User;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SevereConditionView extends BaseView {

	private ListView<String> listView;
	
	public void createSevereConditionView(String titleString, SevereConditionViewController parentController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		Text title = new Text(titleString);
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


		listView = new ListView<String>();
		listView.setItems(parentController.alertObsList());
		vbox.getChildren().add(listView);
		
		Button markReadBtn = new Button("Mark as read");
		markReadBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				parentController.handleMarkRead(listView.getSelectionModel().getSelectedIndex());
			}
		});
		
		createScene(vbox);

	}
}
