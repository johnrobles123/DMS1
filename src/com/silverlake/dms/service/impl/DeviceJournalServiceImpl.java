package com.silverlake.dms.service.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.silverlake.dms.dao.DeviceJournalDao;
import com.silverlake.dms.service.DeviceJournalService;
import com.silverlake.dms.viewBean.DeviceJournal;

public class DeviceJournalServiceImpl implements DeviceJournalService {
	
	private DeviceJournalDao deviceJournalDao;
	
	public DeviceJournalDao getDeviceJournalDao()
	{
			return this.deviceJournalDao;
	}

	public void setDeviceJournalDao(DeviceJournalDao deviceJournalDao)
	{
			this.deviceJournalDao = deviceJournalDao;
	}
	
	@Override
	public List<DeviceJournal> fetchAllDeviceJournal() throws SQLException {
		return deviceJournalDao.selectAll();
	}

	@Override
	public void addDeviceJournal() {
		java.util.Calendar cal = Calendar.getInstance();
		java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
		
		DeviceJournal dj = new DeviceJournal();
		dj.setId(1);
		dj.setDateTime(sqlDate);
		dj.setDeviceName("Projector 1");
		dj.setReserveDate(sqlDate);
		
		deviceJournalDao.insert(dj);
	}
}
