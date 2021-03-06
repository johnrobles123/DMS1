package com.silverlake.dms.viewBean;

import java.util.Comparator;
import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ReservationBean implements Comparable<ReservationBean> {
	private int seqNo;
	private String deviceName;
	private String deviceSerialNo;
	private String userName;
	private String reserveDate;
	
	private String timeFrom;
	private String timeTo;
	private String repeating;
	private String repeatTo;
	
	private String location;
	private String addInfo;
	private String status;
	private boolean returned;
	
	public ReservationBean() {
		this.setReturned(false);
	}

	public static class OrderByTimeFrom implements Comparator<ReservationBean> {

		@Override
		public int compare(ReservationBean o1, ReservationBean o2) {
			DateFormat df = new SimpleDateFormat("hh:mm a");
			java.sql.Time timeFrom1 = null;
			java.sql.Time timeFrom2 = null;
			
			try {
				timeFrom1 = new java.sql.Time(df.parse(o1.timeFrom).getTime());
			    timeFrom2 = new java.sql.Time(df.parse(o2.timeFrom).getTime());
			
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (timeFrom1 != null && timeFrom2 != null) {
				if (timeFrom1.after(timeFrom2)) {
					return 1;
				}
				else if (timeFrom1.before(timeFrom2)) {
					return -1;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}
		
	}
	
	private Time getTimeFormat(String time)
	{
		SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format
		
	    @SuppressWarnings("deprecation")
		Date d1 = new Date(12, 30, 2000);
	    Time ppstime = new Time(d1.getTime());
		try {

		    d1 =(java.util.Date)format.parse(time);

		    ppstime = new java.sql.Time(d1.getTime());

		} catch(Exception e) {
			System.out.println("Error in time"+time);
		}
		return ppstime;
	}
	
	private Date getDateFormat(String date){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		@SuppressWarnings("deprecation")
		Date retDate = new Date(12, 30, 2000);
		try {
			retDate = (Date) df.parse(date);
			
		}catch (ParseException e) {
	    }
		
		return retDate;
	}
 
	public String getTimeFrom() {
		return timeFrom;
	}
	
	public Time getTTimeFrom()
	{	return getTimeFormat(timeFrom);
	}
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	public void setTimeFrom(Time timeFrom) {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format
		
		this.timeFrom = format.format(timeFrom);
	}
	
	
	public String getTimeTo() {
		return timeTo;
	}
	public Time getTTimeTo()
	{	
		return getTimeFormat(timeTo);
	}
	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	public void setTimeTo(Time timeFrom) {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format
		
		this.timeTo = format.format(timeFrom);
	}
	
	public String getRepeating() {
		return repeating;
	}
	public void setRepeating(String repeating) {
		this.repeating = repeating;
	}
	public Date getDRepeatTo(){
		return getDateFormat(repeatTo);
	}
	public String getRepeatTo() {
		return repeatTo;
	}
	public void setRepeatTo(String repeatTo) {
		this.repeatTo = repeatTo;
	}
	public String getDeviceSerialNo() {
		return deviceSerialNo;
	}
	public void setDeviceSerialNo(String deviceName) {
		this.deviceSerialNo = deviceName;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public Date getDReserveDate(){
		return getDateFormat(reserveDate);
	}
	
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	
	public void setReserveDate(Date reserveDate) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
		this.reserveDate = DATE_FORMAT.format(reserveDate);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	
	@Override
	public int compareTo(ReservationBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
