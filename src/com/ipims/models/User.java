package com.ipims.models;

public class User {

	public enum UserType {
	    DOCTOR,
	    PATIENT,
	    HSPSTAFF,
	    NURSE,
	    UNKNOWN
	}

	public UserType getUsertype() {
		return UserType.UNKNOWN;
	}
	
}
