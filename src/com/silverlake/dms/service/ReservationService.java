package com.silverlake.dms.service;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.viewBean.ReservationBean;


public interface  ReservationService {
	public boolean isValidRange(ReservationBean reservation) throws SQLException;
	
	public boolean isOverlap(ReservationBean reservation) throws SQLException;
	
	public void create(ReservationBean reservation) throws SQLException;
	
	public List<ReservationBean> selectAll();
	
	public ReservationBean getReservation(int seqNo);
	
	public void updateReservation(ReservationBean reservation) throws SQLException;
	
	public boolean isDeviceAvailable(String deviceSerialNo) throws SQLException;
	
	public ReservationBean getNextStartTime(String deviceSerialNo) throws SQLException;
	
	public String getAvailabilityStartTime(String deviceSerialNo) throws SQLException;
	
	public List<ReservationBean> getCurrentDayRecords(String deviceSerialNo) throws SQLException;
	
	public List<ReservationBean> selectAllByDeviceSerialNo(String deviceSerialNo);
}
