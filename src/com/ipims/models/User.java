package com.ipims.models;

public class User {

	public enum UserType {
	    DOCTOR,
	    PATIENT,
	    HSPSTAFF,
	    NURSE,
	    LABSTAFF,
	    UNKNOWN;
	    
	    public static UserType fromInteger(int x) {
	        switch(x) {
	        case 0:
	            return DOCTOR;
	        case 1:
	            return PATIENT;
	        case 2:
	            return HSPSTAFF;
	        case 3:
	            return NURSE;
	        case 4:
	            return LABSTAFF;
	        }
	        return UNKNOWN;
	    }
	}
	
	protected UserType type;
	private String userName;
	private String name;
	private String dateOfBirth;
	private String ssn;
	private String address;
	private String email;
	private String phoneNumber;
	private String passwordHash;
	private int userId;
	
	// added
	//public ArrayList<User> validUsers = new ArrayList<User>();
	
	public User()
	{ 
		type = UserType.UNKNOWN;
	}

	public static User createUser(UserType userType) {
		User user = null;
		switch (userType) {
		case DOCTOR:
			user = new Doctor();
			break;

		case PATIENT:
			user = new Patient();
			break;
			
		case HSPSTAFF:
			user = new HSPStaff();
			break;
			
		case NURSE:
			user = new Nurse();
			break;
			
		case LABSTAFF:
			user = new LabStaff();
			break;
			
		case UNKNOWN:
			break;
		default:
			break;
		}
		
		return user;
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
