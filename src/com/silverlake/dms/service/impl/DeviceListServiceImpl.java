package com.silverlake.dms.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.silverlake.dms.dao.DeviceListDao;
import com.silverlake.dms.service.DeviceListService;
import com.silverlake.dms.viewBean.DeviceJournal;
import com.silverlake.dms.viewBean.DeviceListBean;

public class DeviceListServiceImpl implements DeviceListService {
	
	private DeviceListDao deviceListDao;
	
	public DeviceListDao getDeviceListDao()
	{
			return this.deviceListDao;
	}

	public void setDeviceListDao(DeviceListDao deviceListDao)
	{
			this.deviceListDao = deviceListDao;
	}
	
	@Override
	public List<DeviceListBean> fetchAllDeviceList() throws SQLException {
		return deviceListDao.selectAll();
	}

	@Override
	public DeviceListBean findById(String serialNo) {
		return null;
	}

	@Override
	public List<DeviceListBean> findAll() {
		return null;
	}

	@Override
	public void saveOrUpdate(DeviceListBean device) {
		
	}

	@Override
	public void delete(String serialNo) {		
	}

}
