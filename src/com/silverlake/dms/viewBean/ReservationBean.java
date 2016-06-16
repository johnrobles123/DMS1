package com.silverlake.dms.viewBean;

import java.sql.Date;
import java.sql.Time;


public class ReservationBean {
	String deviceName;
	String userName;
	Date reserveDate;
	
	Time timeFrom;
	Time timeTo;
	String repeating;
	Date repeatTo;
	
	String location;
	String addInfo;
}
