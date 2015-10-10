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
	private String userName;
	private String name;
	private String dateOfBirth;
	private String ssn;
	private String address;
	private String email;
	private String phoneNumber;
	
	public User()
	{ 
		type = UserType.UNKNOWN;
	}

	public UserType getUsertype() {
		return type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
