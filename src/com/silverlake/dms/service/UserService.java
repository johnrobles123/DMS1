/**
 *
 */
package com.silverlake.dms.service;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.viewBean.DeviceListBean;
import com.silverlake.dms.viewBean.User;

/**
 * @author CENTAUR
 *
 */
public interface UserService
{
		public boolean isValidUser(String username, String password) throws SQLException;
		
		public void save(User addUser) throws SQLException;
		
		public void update(User addUser) throws SQLException;
		
		public void delete(String id) throws SQLException;
		
		public List<User> fetchAllUserList() throws SQLException;

		public boolean logout() throws SQLException;
}
