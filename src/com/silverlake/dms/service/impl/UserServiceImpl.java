package com.silverlake.dms.service.impl;

import java.sql.SQLException;

import com.silverlake.dms.dao.UserDao;
import com.silverlake.dms.service.UserService;
import com.silverlake.dms.viewBean.User;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return this.userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean isValidUser(String username, String password)
			throws SQLException {
		return userDao.isValidUser(username, password);
	}

	@Override
	public void save(User addUser) throws SQLException {
		userDao.save(addUser);
	}

	@Override
	public void update(User addUser) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) throws SQLException {
		userDao.delete(id);
	}

}
