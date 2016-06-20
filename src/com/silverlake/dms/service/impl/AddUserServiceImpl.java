package com.silverlake.dms.service.impl;

import java.sql.SQLException;

import com.silverlake.dms.dao.AddUserDao;
import com.silverlake.dms.service.AddUserService;

public class AddUserServiceImpl implements AddUserService {

	private AddUserDao addUserDao;

	public AddUserDao getAddUserDao() {
		return addUserDao;
	}

	public void setAddUserDao(AddUserDao addUserDao) {
		this.addUserDao = addUserDao;
	}

	@Override
	public boolean addUser(String firstName, String lastName, String isAdmin)
			throws SQLException {
		return addUserDao.addUser(firstName, lastName, isAdmin);
	}

}