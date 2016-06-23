package com.silverlake.dms.delegate;

import java.sql.SQLException;

import com.silverlake.dms.service.UserService;
import com.silverlake.dms.viewBean.User;

public class UserDelegate {

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void save(User user) throws SQLException {
		userService.save(user);
	}
}