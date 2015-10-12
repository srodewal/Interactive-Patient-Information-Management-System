package com.ipims.models;


public class Patient extends User {

	public Patient(String username, String password)
	{
		type = UserType.PATIENT;
		//validUsers.add(this); // add to valid users
	}
}
