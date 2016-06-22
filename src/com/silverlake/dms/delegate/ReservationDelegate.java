package com.silverlake.dms.delegate;

import java.sql.SQLException;
import java.util.List;

import com.silverlake.dms.service.ReservationService;
import com.silverlake.dms.viewBean.ReservationBean;

public class ReservationDelegate {
	private ReservationService reservationService;
	
	public ReservationService getreservationService()
	{
		return this.reservationService;
	}
	
	public void setreservationService(ReservationService reservation)
	{
		this.reservationService = reservation;
	}
	
	public  boolean isValidRange(ReservationBean reservation) throws SQLException
	{
		return reservationService.isValidRange(reservation);
	}
	public  boolean isOverlap(ReservationBean reservation) throws SQLException
	{
		return reservationService.isOverlap(reservation);
	}
	public void create(ReservationBean reservation) throws SQLException
	{
		reservationService.create(reservation);
	}
	public List<ReservationBean> selectAll()
	{
		return reservationService.selectAll();
	}
	public ReservationBean getReservation(int seqNo)
	{
		return reservationService.getReservation(seqNo);
	}
}
