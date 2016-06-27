package com.silverlake.dms.dao;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.viewBean.ReservationBean;

public interface ReservationDao {
	public boolean isValidRange(ReservationBean reservation) throws SQLException;
	public boolean isOverlap(ReservationBean reservation) throws SQLException;
	public void create(ReservationBean reservation) throws SQLException;
	public List<ReservationBean> selectAll();
	public List<ReservationBean> selectAllByDeviceSerialNo(String deviceSerialNo);
	public ReservationBean getReservation(int seqNo);
	public void updateReservation(ReservationBean reservation) throws SQLException;
	public List<ReservationBean> getCurrentDayRecords(String deviceSerialNo) throws SQLException;
}
