package com.silverlake.dms.service;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.viewBean.DeviceJournal;
import com.silverlake.dms.viewBean.DeviceListBean;


public interface DeviceListService {

	public DeviceListBean findById(String serialNo);
	
	public List<DeviceListBean> findAll();

	public void saveOrUpdate(DeviceListBean device);
	
	public void delete(String serialNo);

	public List<DeviceListBean> fetchAllDeviceList() throws SQLException;

}
