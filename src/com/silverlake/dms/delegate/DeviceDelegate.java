package com.silverlake.dms.delegate;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.service.DeviceJournalService;
import com.silverlake.dms.viewBean.DeviceJournal;


public class DeviceDelegate {

	private DeviceJournalService deviceJournalService;

	public DeviceJournalService getDeviceJournalService()
	{
			return this.deviceJournalService;
	}

	public void setDeviceJournalService(DeviceJournalService deviceJournalService)
	{
			this.deviceJournalService = deviceJournalService;
	}
	
	public List<DeviceJournal> selectAllData() {
		List<DeviceJournal> returnDeviceJournal = null;
		try {
			returnDeviceJournal = deviceJournalService.fetchAllDeviceJournal();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnDeviceJournal;
	}

	public void addDevice() throws SQLException {		
		deviceJournalService.addDeviceJournal();
	}
}
