package com.silverlake.dms.service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import com.silverlake.dms.viewBean.ReservationBean;


public interface  ReservationService {
	public boolean isValidRange(ReservationBean reservation) throws SQLException;
	public boolean isOverlap(ReservationBean reservation) throws SQLException;
	public void create(ReservationBean reservation) throws SQLException;
	public List<ReservationBean> selectAll();
	public ReservationBean getReservation(int seqNo);
}
