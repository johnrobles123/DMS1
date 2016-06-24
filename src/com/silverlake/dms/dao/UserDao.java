package com.silverlake.dms.dao;

import java.sql.SQLException;

import com.silverlake.dms.viewBean.User;


public interface UserDao
{
		public boolean isValidUser(String username, String password) throws SQLException;
		
		public void save(User addUser) throws SQLException;
		
		public void update(User addUser) throws SQLException;
		
		public void delete(String id) throws SQLException;
}
