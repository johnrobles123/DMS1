package com.silverlake.dms.dao;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.viewBean.DeviceListBean;
import com.silverlake.dms.viewBean.User;

public interface UserDao
{
		public boolean isValidUser(String username, String password) throws SQLException;
		
		public void save(User addUser) throws SQLException;
		
		public void update(User addUser) throws SQLException;
		
		public void delete(String id) throws SQLException;

		public List<User> selectAll();
}
