package com.silverlake.dms.dao.impl;

import java.sql.SQLException;
import java.sql.PreparedStatement;
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
	public boolean addUser(String fName, String lName, String email,
			String pword, String admin) throws SQLException {
		String insert = "insert into users (fname,lname,email,password,admin)"
				+ "values (?, ?, ?, ?,?)";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				insert);
		// pstmt.setInt(1, id);
		pstmt.setString(2, fName);
		pstmt.setString(3, lName);
		pstmt.setString(4, email);
		pstmt.setString(5, pword);
		// pstmt.setDate(6, dateCrated);
		pstmt.setString(7, admin);
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