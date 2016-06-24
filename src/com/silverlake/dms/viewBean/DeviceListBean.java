package com.silverlake.dms.viewBean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device_list")
public class DeviceListBean {
	
	private String deviceName;
	
	@Id
	private String serialNo;
	
	private String additionalInfo;
	
	public DeviceListBean (String deviceName, String serialNo, String additionalInfo) {
		this.deviceName = deviceName; 
		this.serialNo = serialNo;
		this.additionalInfo = additionalInfo;
	}

	public DeviceListBean() {
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}