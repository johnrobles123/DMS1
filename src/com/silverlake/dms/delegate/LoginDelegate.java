package com.silverlake.dms.delegate;

import java.sql.SQLException;

import com.silverlake.dms.service.UserService;

public class LoginDelegate
{
		private UserService userService;

		public UserService getUserService()
		{
				return this.userService;
		}

		public void setUserService(UserService userService)
		{
				this.userService = userService;
		}

		public boolean isValidUser(String username, String password) throws SQLException
    {
		    return userService.isValidUser(username, password);
    }
}
