package com.ipims.healthconditions;

	import com.ipims.MenuViewController;
	import com.ipims.usersession.UserSession;
	import com.ipims.views.Appointmentsview;
import com.ipims.views.PrescribeMedView;
import com.ipims.views.StatsView;

	import javafx.scene.Scene;
	
	
	
	public class PrescribeMedViewController {

	
	private PrescribeMedView view;

		//--------------- View Controller methods ---------------
		//--------------------------------------------------------
		
	public  PrescribeMedViewController() {
		view = new PrescribeMedView();
		view.createPrescribeMedView(UserSession.getInstance().getCurrentUser(), this);
	}
	
	public Scene getScene() {
		return view.getCurrentScene();
	}
	
		public void goBack() {
			 MenuViewController menu = new MenuViewController();
	         view.getStage().setScene(menu.getScene());
	         
		}
		
		
		
	}
