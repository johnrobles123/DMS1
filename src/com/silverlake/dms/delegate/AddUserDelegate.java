package com.silverlake.dms.delegate;

import java.sql.SQLException;
import com.silverlake.dms.service.AddUserService;

public class AddUserDelegate {

	private AddUserService adduserService;

	public AddUserService getAdduserService() {
		return adduserService;
	}

	public void setAdduserService(AddUserService adduserService) {
		this.adduserService = adduserService;
	}

	public boolean addUser(String fName, String lName,String email, String pword, String admin)
			throws SQLException {
		return addUser(fName, lName, email, pword, admin);
	}
}