package com.silverlake.dms.dao;

import java.sql.SQLException;

public interface AddUserDao {
	public boolean addUser(String firstName, String lastName, String isAdmin)
			throws SQLException;
}
