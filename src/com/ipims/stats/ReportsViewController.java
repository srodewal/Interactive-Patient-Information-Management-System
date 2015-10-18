package com.ipims.stats;

import com.ipims.MenuViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;
import com.ipims.views.ReportsView;

import javafx.scene.Scene;
import javafx.scene.text.Text;

public class ReportsViewController {
	private ReportsView view;
	//public Text reportTitle;


	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
	public ReportsViewController(Text reportTitle) {
		view = new ReportsView();
		view.createReportsView(this, reportTitle);
	}
	
	public Scene getScene() {
		return view.getCurrentScene();
	}
	
	public void goBackStats() {
		 StatsViewController stats = new StatsViewController();
         view.getStage().setScene(stats.getScene());
         
	}
}
