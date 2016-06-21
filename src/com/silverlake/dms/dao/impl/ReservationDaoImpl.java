package com.silverlake.dms.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;

import com.silverlake.dms.dao.ReservationDao;
import com.silverlake.dms.viewBean.ReservationBean;


public class ReservationDaoImpl implements ReservationDao {
	private static final Calendar Calendar = null;
	DataSource dataSource;
	public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
                 
        return cal.getTime();
    }
	
	private Date getNextDate(String repeat, Date startDate)
	{	
	 	Date date = startDate;
	     
	 	System.out.println(startDate.getDay()+"day");
		if (repeat.equals("Daily"))
		{
			
			if (startDate.getDay() == 5)
			{	 date = addDays(startDate, 3);
			}else if (startDate.getDay() == 6)
			{
				 date= addDays(startDate, 2);
			}else
			{
				date= addDays(startDate, 1);
			}
		}
		else if (repeat.equals("Weekly"))
		{	
			date = addDays(startDate,7);
		}
		else
		{
			 date = addDays(date, 1);
		}
		System.out.println(date+"date");
		return date;
	}
	
	public DataSource getDataSource()
	{
			return this.dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
			this.dataSource = dataSource;
	}
	
	@Override
	public boolean isValidRange(ReservationBean reservation) throws SQLException {
		boolean ret = true;
		//check if time range is valid
		System.out.println(reservation.getTTimeFrom()+"isvalid timefrom");
		System.out.println(reservation.getTTimeTo()+"isvalid timeto");
		if (reservation.getReserveDate().isEmpty() ||
			reservation.getTimeFrom().isEmpty() ||
			reservation.getTimeTo().isEmpty()||
			reservation.getDeviceSerialNo().isEmpty())
		{
			ret = false;
		}
		
		if (reservation.getDReserveDate().getDay() > 5)
		{
			System.out.println("Invalid Reservation Date");
			ret = false;
		}
		if (reservation.getTTimeFrom().after(reservation.getTTimeTo()))
		{
			System.out.println("Invalid Time");
			ret = false;
		}
		
		//check if repeating date is valid
		if (!reservation.getRepeating().isEmpty())
		{
			if (reservation.getDReserveDate().compareTo(reservation.getDRepeatTo()) >=0)
			{
				System.out.println("Invalid repeat to");
				ret = false;
			}
		}
		
		System.out.println(reservation.getDReserveDate()+ "dateformat");
		System.out.println(reservation.getTTimeFrom()+"time");
		return ret;
	}

	@Override
	public boolean isOverlap(ReservationBean reservation)
			throws SQLException {
		String query;
		PreparedStatement pstmt;
		
		if (reservation.getRepeating().equals("Weekly") || reservation.getRepeating().isEmpty())
		{
			query = "Select count(1) from DEVICE_JOURNAL where device_serial_no = ? and reserve_date = ? and (time_from between ? and ? or time_to between ? and ?)";
			Date startDate = reservation.getDReserveDate();
			Date endDate = reservation.getDReserveDate();
			
			if (!reservation.getRepeating().isEmpty())
			{
				reservation.getDRepeatTo();
			}
			
			
			System.out.println(startDate+"date after");

			while (!startDate.after(endDate))
			{	pstmt = dataSource.getConnection().prepareStatement(query);
				
				pstmt.setString(1, reservation.getDeviceSerialNo());
				pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
				pstmt.setTime(3, reservation.getTTimeFrom());
				pstmt.setTime(4, reservation.getTTimeTo());
				pstmt.setTime(5, reservation.getTTimeFrom());
				pstmt.setTime(6, reservation.getTTimeTo());
				pstmt.execute();
				
				startDate = getNextDate(reservation.getRepeating() , startDate);
				System.out.println(startDate+"date after");
			}
		}
		else
		{
			query = "Select count(1) from DEVICE_JOURNAL where device_serial_no = ? and reserve_date between ? and ? and (time_from between ? and ? or time_to between ? and ?)";
			pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, reservation.getDeviceSerialNo());
			pstmt.setDate(2, new java.sql.Date(reservation.getDReserveDate() .getTime()));
			
			pstmt.setDate(3, new java.sql.Date(reservation.getDRepeatTo() .getTime()));
			if (reservation.getRepeating().equals("Daily"))
			{
				pstmt.setTime(4, reservation.getTTimeFrom());
				pstmt.setTime(5, reservation.getTTimeTo());
				pstmt.setTime(6, reservation.getTTimeFrom());
				pstmt.setTime(7, reservation.getTTimeTo());
			}
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next())
			{	System.out.println("duplicate");
					return !(resultSet.getInt(1) > 0);
			}

		}
			
		return true;
	}

	@Override
	public void create(ReservationBean reservation) throws SQLException {
		// TODO Auto-generated method stub
		String query = "insert into DEVICE_JOURNAL(seq_no, device_serial_no, username, reserve_date, time_from, time_to, location, add_info) values(NULL,?,?,?,?,?,?,?)";
		Date startDate = reservation.getDReserveDate();
		Date endDate = reservation.getDReserveDate();
		
		if (!reservation.getRepeating().isEmpty())
		{
			endDate = reservation.getDRepeatTo();
		}
		
		System.out.println(startDate+"start date");

		while (!startDate.after(endDate))
		{	PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			
			pstmt.setString(1, reservation.getDeviceSerialNo());
			pstmt.setString(2, "admin");
			pstmt.setDate(3, new java.sql.Date(startDate.getTime()));
			pstmt.setTime(4, reservation.getTTimeFrom());
			System.out.println(reservation.getTTimeTo()+"timeto");
			pstmt.setTime(5, reservation.getTTimeTo());
			pstmt.setString(6, reservation.getLocation() );
			pstmt.setString(7, reservation.getAddInfo());
			
			pstmt.execute();
			startDate = getNextDate(reservation.getRepeating() , startDate);
			System.out.println(startDate+"date after");
		}
	}

	@Override
	public List<ReservationBean> selectAll() {
		
		List<ReservationBean> reserveList = new ArrayList<ReservationBean>();
		
		PreparedStatement pstmt;
		try {
			pstmt = dataSource.getConnection().prepareStatement("SELECT a.seq_no, a.device_serial_no, b.device_name, a.username, a.reserve_date, a.time_from, a.time_to, a.location, a.add_info FROM device_journal a, device_list b WHERE a.device_serial_no = b.serial_no");
		
			try {
				ResultSet rbSet = pstmt.executeQuery();
				
				while (rbSet.next()) {
					ReservationBean rb = new ReservationBean();
					rb.setSeqNo(rbSet.getInt(1));
					rb.setDeviceSerialNo(rbSet.getString(2));
					rb.setDeviceName(rbSet.getString(3));
					rb.setUserName(rbSet.getString(4));
					rb.setReserveDate(rbSet.getDate(5));
					rb.setTimeFrom(rbSet.getTime(6));
					rb.setTimeTo(rbSet.getTime(7));
					rb.setLocation(rbSet.getString(8));
					rb.setAddInfo(rbSet.getString(9));
					reserveList.add(rb);
				}
	
			} finally {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reserveList;
	}

	@Override
	public ReservationBean getReservation(int seqNo) 
	{	
		PreparedStatement pstmt;
		ReservationBean rb = new ReservationBean();
		try
		{	pstmt = dataSource.getConnection().prepareStatement("SELECT a.seq_no, a.device_serial_no, b.device_name, a.username, a.reserve_date, a.time_from, a.time_to, a.location, a.add_info FROM device_journal a, device_list b WHERE a.device_serial_no = b.serial_no");
			
			try {
				ResultSet rbSet = pstmt.executeQuery();
				rb.setSeqNo(rbSet.getInt(1));
				rb.setDeviceSerialNo(rbSet.getString(2));
				rb.setDeviceName(rbSet.getString(3));
				rb.setUserName(rbSet.getString(4));
				rb.setReserveDate(rbSet.getDate(5));
				rb.setTimeFrom(rbSet.getTime(6));
				rb.setTimeTo(rbSet.getTime(7));
				rb.setLocation(rbSet.getString(8));
				rb.setAddInfo(rbSet.getString(9));
			}
			finally 
		 	{
		 		pstmt.close();
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rb;
	}
	
}
