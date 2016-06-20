package com.silverlake.dms.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.silverlake.dms.dao.DeviceJournalDao;
import com.silverlake.dms.viewBean.DeviceJournal;

public class DeviceJournalDaoImpl implements DeviceJournalDao {

	private static final String SELECT_QUERY = "select journal_id, date_time, device_name, reserve_date from Device_Journal";

	private DataSource dataSource;

	public DataSource getDataSource()
	{
			return this.dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
			this.dataSource = dataSource;
	}
	
	public void insert (DeviceJournal deviceJournal) {
		String insert = "INSERT INTO DEVICE_JOURNAL"
				+ "(JOURNAL_ID, DATE_TIME, DEVICE_NAME, RESERVE_DATE) VALUES"
				+ "(?,?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try {		
			try {
				pstmt = dataSource.getConnection().prepareStatement(insert);
				
				java.sql.Date d = null;
				
				pstmt.setInt(1, deviceJournal.getId());
				pstmt.setDate(2, deviceJournal.getDateTime());
				pstmt.setString(3, deviceJournal.getDeviceName());
				pstmt.setDate(4, deviceJournal.getReserveDate());
				// execute insert SQL stetement
				pstmt.executeUpdate();
			} finally {
				pstmt.close();
			}
		} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	public List<DeviceJournal> selectAll() {
		List<DeviceJournal> djList = new ArrayList<DeviceJournal>();
		
		PreparedStatement pstmt;
		try {
			pstmt = dataSource.getConnection().prepareStatement(SELECT_QUERY);
		
			try {
				ResultSet djSet = pstmt.executeQuery();
				
				
				while (djSet.next()) {
					DeviceJournal dj = new DeviceJournal();
					dj.setId(djSet.getInt(1));
					dj.setDateTime(djSet.getDate(2));
					dj.setDeviceName(djSet.getString(3));
					dj.setReserveDate(djSet.getDate(4));
					djList.add(dj);
				}
	
			} finally {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return djList;
	}
}
