package com.silverlake.dms.dao;

import java.util.List;

import com.silverlake.dms.viewBean.DeviceJournal;

public interface DeviceJournalDao {	
	
	public List<DeviceJournal> selectAll();
	
	public void insert (DeviceJournal deviceJournal);
}
