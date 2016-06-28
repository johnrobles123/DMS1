package com.silverlake.dms.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.sql.DataSource;

import com.silverlake.dms.dao.ReservationDao;
import com.silverlake.dms.viewBean.ReservationBean;


public class ReservationDaoImpl implements ReservationDao {
	private static final Calendar Calendar = null;
	DataSource dataSource;
	
	@SuppressWarnings("static-access")
	public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
                 
        return cal.getTime();
    }
	
	@SuppressWarnings("deprecation")
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
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isValidRange(ReservationBean reservation) throws SQLException {
		boolean ret = true;
		//check if time range is valid
		System.out.println(reservation.getTTimeFrom()+"isvalid timefrom");
		System.out.println(reservation.getTTimeTo()+"isvalid timeto");
		if (//reservation.getReserveDate().isEmpty ||
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
				pstmt.setInt(7, reservation.getSeqNo());
				pstmt.execute();
				
				startDate = getNextDate(reservation.getRepeating() , startDate);
				System.out.println(startDate+"date after");
			}
		}
		else
		{
			query = "Select count(1) from DEVICE_JOURNAL where device_serial_no = ? and reserve_date between ? and ? and (time_from between ? and ? or time_to between ? and ? and seq_no <> ?)";
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
				pstmt.setInt(8, reservation.getSeqNo());
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

	public void update(ReservationBean reservation) throws SQLException {
		// TODO Auto-generated method stub
		String query = "Update reservation_journal";
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
			pstmt = dataSource.getConnection().prepareStatement("SELECT a.seq_no, a.device_serial_no, b.device_name, a.username, a.reserve_date, a.time_from, a.time_to, a.location, a.add_info FROM device_journal a, device_list b WHERE a.device_serial_no = b.serial_no AND a.status IS NULL AND TIMESTAMP(a.reserve_date, a.time_to) >= CURRENT_TIMESTAMP() ORDER BY TIMESTAMP(a.reserve_date, a.time_to)");
			
			/*
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    java.sql.Time sqlTime = new java.sql.Time(sqlDate.getTime()); 
			
			pstmt.setTime(1, sqlTime);		
			*/

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
	
	public List<ReservationBean> selectAllByDeviceSerialNo(String deviceSerialNo) {
		
		List<ReservationBean> reserveList = new ArrayList<ReservationBean>();
		StringBuilder stmt = new StringBuilder(); 
		stmt.append("SELECT a.seq_no, a.device_serial_no, b.device_name, a.username, a.reserve_date, a.time_from, a.time_to, a.location, a.add_info FROM device_journal a, device_list b WHERE a.device_serial_no = b.serial_no ");
		
		if (!deviceSerialNo.equalsIgnoreCase("*")) {
			stmt.append("AND a.device_serial_no = ? ");
		}
		
		stmt.append("AND TIMESTAMP(a.reserve_date, a.time_to) >= CURRENT_TIMESTAMP()");
		
		PreparedStatement pstmt;
		try {
			pstmt = dataSource.getConnection().prepareStatement(stmt.toString());
			
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    java.sql.Time sqlTime = new java.sql.Time(sqlDate.getTime()); 
			
			pstmt.setString(1, deviceSerialNo);
			//pstmt.setTime(2, sqlTime);

			Calendar cal = Calendar.getInstance();
			cal.setTime(cal.getTime());
			
			try {
				ResultSet rbSet = pstmt.executeQuery();
				
				Date curDate = new Date();
				
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
		ReservationBean rb = new ReservationBean();
		PreparedStatement pstmt;

		try
		{	
			pstmt = dataSource.getConnection().prepareStatement("SELECT a.seq_no, a.device_serial_no, b.device_name, a.username, a.reserve_date, a.time_from, a.time_to, a.location, a.add_info FROM device_journal a, device_list b WHERE a.device_serial_no = b.serial_no AND a.seq_no = ?");
			pstmt.setInt(1, seqNo);
			
			try {
				ResultSet rbSet = pstmt.executeQuery();
				
				while (rbSet.next()) {
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
				
			} catch (SQLException e) {
				e.printStackTrace();
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
	
	@Override
	public void updateReservation(ReservationBean reservation) throws SQLException
	{	String query = "update DEVICE_JOURNAL set reserve_date = ? , time_from = ?, time_to = ?, location = ?, add_info = ? where seq_no = ?";
	
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		
		pstmt.setDate(1, new java.sql.Date(reservation.getDReserveDate().getTime()));
		pstmt.setTime(2, reservation.getTTimeFrom());
		pstmt.setTime(3, reservation.getTTimeTo());
		pstmt.setString(4, reservation.getLocation() );
		pstmt.setString(5, reservation.getAddInfo());
		pstmt.setInt(6, reservation.getSeqNo());
		
		pstmt.execute();

	}

	@Override
	public List<ReservationBean> getCurrentDayRecords(String deviceSerialNo) throws SQLException {
		List<ReservationBean> reserveList = new ArrayList<ReservationBean>();
		String dbProductName = dataSource.getConnection().getMetaData().getDatabaseProductName();
		
		PreparedStatement pstmt = null;
		
		try {
			if (dbProductName.toLowerCase().contains("mysql")) {
				pstmt = dataSource.getConnection().prepareStatement("SELECT a.seq_no, a.device_serial_no, b.device_name, a.username, a.reserve_date, a.time_from, a.time_to, a.location, a.add_info, a.status FROM device_journal a, device_list b WHERE a.device_serial_no = b.serial_no AND a.device_serial_no = ? AND a.reserve_date = CURDATE() AND TIMESTAMP(a.reserve_date, a.time_to) >= CURRENT_TIMESTAMP() ORDER BY TIMESTAMP(a.reserve_date, a.time_to)");
			} else if (dbProductName.toLowerCase().contains("oracle")) {
				pstmt = dataSource.getConnection().prepareStatement("SELECT a.seq_no, a.device_serial_no, b.device_name, a.username, a.reserve_date, a.time_from, a.time_to, a.location, a.add_info, a.status FROM device_journal a, device_list b WHERE a.device_serial_no = b.serial_no AND a.device_serial_no = ? AND a.reserve_date = trunc(sysdate) ORDER BY time_from");
			}		 
			
			pstmt.setString(1, deviceSerialNo);
			
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
					rb.setStatus(rbSet.getString(10));
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
	public void updateReservation(int seqNo, String status) throws SQLException {

		String query = "update DEVICE_JOURNAL set status = ? where seq_no = ?";
		
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		
		pstmt.setString(1, status);
		pstmt.setInt(2, seqNo);
		
		pstmt.execute();
	}
	
}
