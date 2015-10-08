package com.ipims.models;

public class User {

	public enum UserType {
	    DOCTOR,
	    PATIENT,
	    HSPSTAFF,
	    NURSE,
	    UNKNOWN
	}
	
	protected UserType type;
	
	public User()
	{ 
		type = UserType.UNKNOWN;
	}

	public UserType getUsertype() {
		return type;
	}
	
}
