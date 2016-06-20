package com.silverlake.dms.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.dao.ReservationDao;
import com.silverlake.dms.delegate.ReservationDelegate;
import com.silverlake.dms.viewBean.*;

@Controller
public class ReservationController {
	
	@Autowired
	private ReservationDelegate reservationDelegate;
	
	@RequestMapping(value="/reserve",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, ReservationBean reservation)
	{
		ModelAndView model = new ModelAndView("reserve");
		//LoginBean loginBean = new LoginBean();
		model.addObject("reservation", reservation);
		return model;
	}
	
	@RequestMapping(value="/reserve",method=RequestMethod.POST)
	public String createReservation(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("reservation")  ReservationBean reservation) throws SQLException
	{
		String ret = null;
		ModelAndView model= null;
		
		try
		{
			boolean isValidRange = reservationDelegate.isValidRange( reservation);
			if(isValidRange)
			{
				System.out.println("User Login Successful");
				request.setAttribute("loggedInUser", reservation.getDeviceName());
				//model = new ModelAndView("dashboard");
				 ret = "reserve";
			}
			else
			{
				model = new ModelAndView("reservation");
				request.setAttribute("message", "Invalid credentials!!");
			}
			
			if (!reservationDelegate.isOverlap(reservation))
			{
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		reservationDelegate.create(reservation);
		
		return ret;
	}
}
