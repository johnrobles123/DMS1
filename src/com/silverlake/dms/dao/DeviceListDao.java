package com.silverlake.dms.dao;

import java.util.List;

import com.silverlake.dms.viewBean.DeviceListBean;

public interface DeviceListDao {	
	
	public List<DeviceListBean> selectAll();
	
}
