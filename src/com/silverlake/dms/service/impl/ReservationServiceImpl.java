package com.silverlake.dms.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.sql.Date;
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
	public void updateReservation(ReservationBean reservation) throws SQLException {
		reservationDao.updateReservation(reservation);
	}
	
	@Override
	public boolean isDeviceAvailable(String deviceSerialNo) throws SQLException {
		boolean result = true;
		List<ReservationBean> rb = new ArrayList<ReservationBean>();
		
		rb = reservationDao.getCurrentDayRecords(deviceSerialNo);
		
		if (rb.size() > 0) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(rb.get(0).getTTimeFrom());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(rb.get(0).getTTimeTo());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(cal.getTime());
				
			if (cal.after(cal1.getTime()) && cal.before(cal2.getTime())) {
				result = false;
			}
			// if there are no records, then it means the device is available
		} else { 
			result = true;
		}
		return result;
	}

	@Override
	public ReservationBean getNextStartTime(String deviceSerialNo) throws SQLException {
		List<ReservationBean> rbList = new ArrayList<ReservationBean>();
		ReservationBean rb = new ReservationBean();
		
		rbList = reservationDao.getCurrentDayRecords(deviceSerialNo);
		Collections.sort(rbList, new ReservationBean.OrderByTimeFrom());
		
		// get first record
		if (rbList.size() > 0) {
			rb = rbList.get(0);
		}
		
		return rb;
	}

	@Override
	public String getAvailabilityStartTime(String deviceSerialNo) throws SQLException {
		String retstr = null;
		ReservationBean rb = new ReservationBean();
		
		if (isDeviceAvailable(deviceSerialNo)) {
			rb = getNextStartTime(deviceSerialNo);
			if (rb == null || rb.getSeqNo() == 0) {
				retstr = "Devices are available from this point onwards";
			} else {
				retstr = "Device " + rb.getDeviceName() + " is available until " + rb.getTimeFrom().toString();
			}
		} else {
			retstr = "Device is NOT available";
		}
		
		return retstr;
	}
	
	@Override
	public List<ReservationBean> getCurrentDayRecords(String deviceSerialNo) throws SQLException {		
		return reservationDao.getCurrentDayRecords(deviceSerialNo);
	}
}
