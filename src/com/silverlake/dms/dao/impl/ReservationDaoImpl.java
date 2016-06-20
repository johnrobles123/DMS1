package com.silverlake.dms.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

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
		if (reservation.getDReservationDate().getDay() > 5)
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
			if (reservation.getDReservationDate().compareTo(reservation.getDRepeatTo()) >=0)
			{
				System.out.println("Invalid repeat to");
				ret = false;
			}
		}
		
		System.out.println(reservation.getDReservationDate()+ "dateformat");
		System.out.println(reservation.getTTimeFrom()+"time");
		return ret;
	}

	@Override
	public boolean isOverlap(ReservationBean reservation)
			throws SQLException {
		String query = "Select count(1) from reservations where device_name = ? and reserve_date between ? and ? and (time_from between ? and ? or time_to between ? and ?)";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, reservation.getDeviceName());
		pstmt.setDate(2, new java.sql.Date(reservation.getDReservationDate() .getTime()));
		
		if (reservation.getRepeating().isEmpty())
		{	pstmt.setDate(3, new java.sql.Date(reservation.getDReservationDate() .getTime()));}
		else		
		{	pstmt.setDate(3, new java.sql.Date(reservation.getDRepeatTo() .getTime()));
			if (reservation.getRepeating().equals("Daily"))
			{
				pstmt.setTime(4, reservation.getTTimeFrom());
				pstmt.setTime(5, reservation.getTTimeTo());
				pstmt.setTime(6, reservation.getTTimeFrom());
				pstmt.setTime(7, reservation.getTTimeTo());
			}
		}
		
		
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next())
		{	System.out.println("duplicate");
				return !(resultSet.getInt(1) > 0);
		}
		else
				return true;
		
	}

	@Override
	public void create(ReservationBean reservation) throws SQLException {
		// TODO Auto-generated method stub
		String query = "insert into reservations(seq_no, device_name, username, reserve_date, time_from, time_to, location, add_info) values(?,?,?,?,?,?,?,?)";
		Date startDate = reservation.getDReservationDate();
		Date endDate = reservation.getDRepeatTo();
		int seq	= 2;
		
		System.out.println(startDate+"date after");

		while (!startDate.after(endDate))
		{	PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, seq);
			
			pstmt.setString(2, reservation.getDeviceName());
			pstmt.setString(3, "admin");
			pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
			pstmt.setTime(5, reservation.getTTimeFrom());
			System.out.println(reservation.getTTimeTo()+"timeto");
			pstmt.setTime(6, reservation.getTTimeTo());
			pstmt.setString(7, reservation.getLocation() );
			pstmt.setString(8, reservation.getAddInfo());
			
			pstmt.execute();
			startDate = getNextDate(reservation.getRepeating() , startDate);
			seq++;
			System.out.println(startDate+"date after");
		}
//			
//		
//		Statement stmt = dataSource.getConnection().prepareStatement(query);
//		  this.con.setAutoCommit(false);
//	        stmt = this.con.createStatement();
//
//	        stmt.addBatch(
//	            "INSERT INTO COFFEES " +
//	            "VALUES('Amaretto', 49, 9.99, 0, 0)");
//
//	        stmt.addBatch(
//	            "INSERT INTO COFFEES " +
//	            "VALUES('Hazelnut', 49, 9.99, 0, 0)");
//
//	        stmt.addBatch(
//	            "INSERT INTO COFFEES " +
//	            "VALUES('Amaretto_decaf', 49, " +
//	            "10.99, 0, 0)");
//
//	        stmt.addBatch(
//	            "INSERT INTO COFFEES " +
//	            "VALUES('Hazelnut_decaf', 49, " +
//	            "10.99, 0, 0)");
//
//	        int [] updateCounts = stmt.executeBatch();
	}

}
