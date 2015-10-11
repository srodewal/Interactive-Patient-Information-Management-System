package com.ipims.models;

import com.ipims.models.User.UserType;

public class Nurse extends User {

	public Nurse()
	{
		type = UserType.NURSE;
	}
}
