package com.ipims;

import com.ipims.usersession.LoginViewController;
import com.ipims.usersession.UserSession;

import javafx.application.Application;
import javafx.stage.Stage;

public class IPIMSApplication extends Application {
	
	  public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) {
	    	
	    	if (UserSession.getInstance().getCurrentUser() == null) {
	    		// If user is not logged in show login view
	    		
	    		LoginViewController vc = new LoginViewController();
	    		primaryStage.setScene(vc.getScene());
	    		primaryStage.show();
	    		
	    	} else {
	    		// User is logged in, so show the menu
	    		
	    	}
	    	
	    	
	    	 
	    }
}
