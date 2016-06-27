package com.silverlake.dms.viewBean;


//@Entity
//@Table(appliesTo="device_list")

public class DeviceListBean {
	//@Id
	//private int id;

	//@Column(name="device_name")
	private String deviceName;
	//@Column(name="serial_no")
	private String serialNo;
	//@Column(name="additional_info")
	private String additionalInfo;

	public DeviceListBean () {
	/*	this.deviceName = deviceName; 
		this.serialNo = serialNo;
		this.additionalInfo = additionalInfo;*/
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

	public boolean isNew() {
		return (this.serialNo == null);	
	}
}