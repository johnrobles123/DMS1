package com.silverlake.dms.service;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.viewBean.DeviceJournal;

public interface DeviceJournalService {
	
	public List<DeviceJournal> fetchAllDeviceJournal() throws SQLException;
	
	public void addDeviceJournal();
}
