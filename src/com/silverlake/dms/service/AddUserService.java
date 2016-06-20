package com.silverlake.dms.service;

import java.sql.SQLException;

public interface AddUserService {

	public boolean addUser(String fName, String lName,String emai, String pword, String admin)
			throws SQLException;
}
