package com.silverlake.dms.service.impl;

import java.util.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.silverlake.dms.dao.ReservationDao;
import com.silverlake.dms.service.ReservationService;
import com.silverlake.dms.viewBean.ReservationBean;


public class ReservationServiceImpl implements ReservationService {
	private ReservationDao reservationDao;
	
	public ReservationDao getReservationDao() {
		return reservationDao;
	}

	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Override
	public boolean isValidRange(ReservationBean reservation)throws SQLException {
		return reservationDao.isValidRange(reservation);
	}

	@Override
	public boolean isOverlap(ReservationBean reservation)
			throws SQLException {
		return reservationDao.isOverlap(reservation);
	}

	@Override
	public void create(ReservationBean reservation) throws SQLException {
		// TODO Auto-generated method stub
		reservationDao.create(reservation);
	}
}
