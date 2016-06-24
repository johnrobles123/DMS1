package com.silverlake.dms.delegate;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.service.UserService;
import com.silverlake.dms.viewBean.DeviceListBean;
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
	
	public List<User> selectAllData() {
		List<User> returnUserList = null;
		try {
			returnUserList = userService.fetchAllUserList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnUserList;
	}
}