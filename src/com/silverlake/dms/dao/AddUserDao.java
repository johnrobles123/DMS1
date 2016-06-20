package com.silverlake.dms.dao;

import java.sql.SQLException;

public interface AddUserDao {
	public boolean addUser(String fname, String lname, String email,
			String pword, String admin) throws SQLException;

}
