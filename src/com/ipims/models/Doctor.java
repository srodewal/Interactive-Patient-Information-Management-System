package com.ipims.models;

public class Doctor extends User {
	private String category;
	
	public Doctor() {
		type = UserType.DOCTOR;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
