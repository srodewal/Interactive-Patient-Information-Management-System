package com.ipims.models;


public class Patient extends User {

	public UserType getUsertype() {
		return UserType.PATIENT;
	}
}
