package com.silverlake.dms.delegate;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.service.DeviceListService;
import com.silverlake.dms.viewBean.DeviceListBean;


public class DeviceListDelegate {

	private DeviceListService deviceListService;

	public DeviceListService getDeviceListService()
	{
			return this.deviceListService;
	}

	public void setDeviceListService(DeviceListService deviceListService)
	{
			this.deviceListService = deviceListService;
	}
	
	public List<DeviceListBean> selectAllData() {
		List<DeviceListBean> returnDeviceListBean = null;
		try {
			returnDeviceListBean = deviceListService.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnDeviceListBean;
	}
	
}
