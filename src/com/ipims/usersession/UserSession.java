package com.ipims.usersession;
import com.ipims.models.User;

public class UserSession {


	private static UserSession instance = null;
	private User loggedInUser;
	
	private UserSession() {
		// Exists only to defeat instantiation.
	}
	
	public static UserSession getInstance() {
		if(instance == null) {
			instance = new UserSession();
		}
		return instance;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}
	
	public void login(String userName, String password) {
		loggedInUser = new User();
		
	}
	
	public void logout() {
		loggedInUser = null;
	}
	
	public void register(User user) {
		loggedInUser = user;
	}
}

