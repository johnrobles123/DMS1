package com.silverlake.dms.service.impl;

import java.util.List;
import java.sql.SQLException;
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

	@Override
	public List<ReservationBean> selectAll() {
		// TODO Auto-generated method stub
		return reservationDao.selectAll();
	}

	@Override
	public ReservationBean getReservation(int seqNo) {
		return reservationDao.getReservation(seqNo);
	}

	@Override
	public void updateReservation(ReservationBean reservation)
			throws SQLException {
		reservationDao.updateReservation(reservation);
	}
}
