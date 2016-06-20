package com.silverlake.dms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.silverlake.dms.dao.AddUserDao;

public class AddUserDaoImpl implements AddUserDao {

	DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addUser(String firstName, String lastName, String isAdmin)
			throws SQLException {
		String insert = "insert into users_jc (first_name,last_nameis_admin)"
				+ "values (?, ?, ?, )";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				insert);
		// pstmt.setInt(1, id);
		pstmt.setString(2, firstName);
		pstmt.setString(3, lastName);
		// pstmt.setDate(4, dateCrated);
		pstmt.setString(5, isAdmin);
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		boolean resultSet = pstmt.execute();
		if (resultSet)
			return true;
		else
			return false;
	}

}