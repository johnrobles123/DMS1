/**
 *
 */
package com.silverlake.dms.service;

import java.sql.SQLException;

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
}
