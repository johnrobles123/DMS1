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
import com.silverlake.dms.dao.DeviceListDao;
import com.silverlake.dms.viewBean.DeviceJournal;
import com.silverlake.dms.viewBean.DeviceListBean;

public class DeviceListDaoImpl implements DeviceListDao {

	private static final String SELECT_QUERY = "select serial_no, device_name, additional_info from Device_List";

	private DataSource dataSource;

	public DataSource getDataSource()
	{
			return this.dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
			this.dataSource = dataSource;
	}
	
	public List<DeviceListBean> selectAll() {
		List<DeviceListBean> dlList = new ArrayList<DeviceListBean>();
		
		PreparedStatement pstmt;
		try {
			pstmt = dataSource.getConnection().prepareStatement(SELECT_QUERY);
		
			try {
				ResultSet dlSet = pstmt.executeQuery();
				
				while (dlSet.next()) {
					DeviceListBean dl = new DeviceListBean();
					dl.setSerialNo(dlSet.getString(1));
					dl.setDeviceName(dlSet.getString(2));
					dl.setAdditionalInfo(dlSet.getString(3));
					dlList.add(dl);
				}
	
			} finally {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dlList;
	}
}
