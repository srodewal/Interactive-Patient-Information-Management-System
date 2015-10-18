package com.ipims.stats;

import com.ipims.MenuViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;
import com.ipims.views.StatsView;

import javafx.scene.Scene;

public class StatsViewController {
	private StatsView view;


	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
	public  StatsViewController() {
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
		
	}
	
	// to-do
	public void handleHA() {
		
	}
	
	// to-do
	public void handleTA() {
		
	}
	
	// to-do
	public void handleTP() {
		
	}

	
	
	
}
