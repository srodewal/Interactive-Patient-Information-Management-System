package com.ipims.models;

import com.ipims.models.User.UserType;

public class LabStaff extends User {
	public LabStaff()
	{
		type = UserType.LABSTAFF;
	}
}
