package com.silverlake.dms.delegate;

import java.sql.SQLException;
import com.silverlake.dms.service.AddUserService;

public class AddUserDelegate {

	private AddUserService addUserService;

	public AddUserService getAddUserService() {
		return addUserService;
	}

	public void setAddUserService(AddUserService addUserService) {
		this.addUserService = addUserService;
	}

	public boolean addUser(String fName, String lName,String email, String pword, String admin)
			throws SQLException {
		return addUser(fName, lName, email, pword, admin);
	}
}