package com.silverlake.dms.service;

import java.sql.SQLException;

public interface AddUserService {

	public boolean addUser(String firstName, String lastName, String isAdmin)
			throws SQLException;
}
