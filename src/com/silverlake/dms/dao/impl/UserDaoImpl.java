package com.silverlake.dms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.silverlake.dms.dao.UserDao;
import com.silverlake.dms.viewBean.User;
import com.silverlake.dms.viewBean.User;

/**
 * @author CENTAUR
 */
public class UserDaoImpl implements UserDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean isValidUser(String username, String password)
			throws SQLException {
		String query = "Select count(1) from login where username = ? and password = ?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				query);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next())
			return (resultSet.getInt(1) > 0);
		else
			return false;
	}

	@Override
	public void save(User addUser) throws SQLException {
		String insert = "insert into users (fname,lname,email,uname,password,admin)"
				+ "values (?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				insert);

		pstmt.setString(1, addUser.getfname());
		pstmt.setString(2, addUser.getlname());
		pstmt.setString(3, addUser.getEmail());
		pstmt.setString(4, addUser.getuname());
		pstmt.setString(5, addUser.getpassword());
		pstmt.setString(6, addUser.getAdmin());

		pstmt.execute();

	}

	@Override
	public void update(User addUser) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	private static final String SELECT_QUERY = "select id, username, password from login";

	public List<User> selectAll() {
		List<User> user = new ArrayList<User>();
		
		PreparedStatement pstmt;
		try {
			pstmt = dataSource.getConnection().prepareStatement(SELECT_QUERY);
		
			try {
				ResultSet ulSet = pstmt.executeQuery();
				
				while (ulSet.next()) {
					User ul = new User();
					ul.setId(ulSet.getInt(1));
					ul.setuname(ulSet.getString(2));
					ul.setpassword(ulSet.getString(3));
					user.add(ul);
				}
	
			} finally {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public boolean logout() throws SQLException {		
		if (!dataSource.getConnection().isClosed()) {
			dataSource.getConnection().close();
		}
		
		return true;
	}
}