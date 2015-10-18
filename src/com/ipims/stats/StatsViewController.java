package com.ipims.stats;

import com.ipims.MenuViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;
import com.ipims.views.StatsView;

import javafx.scene.Scene;
import javafx.scene.text.Text;

public class StatsViewController {
	private StatsView view;
	public Text reportTitle; // to differentiate


	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
	public StatsViewController() {
		view = new StatsView();
		view.createStatsView(this);
	}
	
	public Scene getScene() {
		return view.getCurrentScene();
	}
	
	public void goBack() {
		 MenuViewController menu = new MenuViewController();
         view.getStage().setScene(menu.getScene());
         
	}
	
	// to-do
	public void handlePP() {
		reportTitle = new Text("Patient Populations");
		ReportsViewController pp = new ReportsViewController(reportTitle);
        view.getStage().setScene(pp.getScene());
	}
	
	// to-do
	public void handleHA() {
		reportTitle = new Text("Health Outcomes");
		ReportsViewController ha = new ReportsViewController(reportTitle);
        view.getStage().setScene(ha.getScene());
	}
	
	// to-do
	public void handleTA() {
		reportTitle = new Text("Admission Rates");
		ReportsViewController ta = new ReportsViewController(reportTitle);
        view.getStage().setScene(ta.getScene());
	}
	
	// to-do
	public void handleTP() {
		reportTitle = new Text("Patient Type");
		ReportsViewController tp = new ReportsViewController(reportTitle);
        view.getStage().setScene(tp.getScene());
	}

	
	
	
}
