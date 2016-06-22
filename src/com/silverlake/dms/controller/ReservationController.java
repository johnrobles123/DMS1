package com.silverlake.dms.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
//	@RequestMapping(value="/reserve",method=RequestMethod.GET)
//	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, ReservationBean reservation)
//	{
//		ModelAndView model = new ModelAndView("reserve");
//		//LoginBean loginBean = new LoginBean();
//		model.addObject("reservation", reservation);
//		return model;
//	}
//	
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
				request.setAttribute("loggedInUser", reservation.getDeviceSerialNo());
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
	
	@RequestMapping(value="/reserve",method=RequestMethod.GET)
	public String display(HttpServletRequest request, HttpServletResponse response, Model model, ReservationBean reservation)
	{
		List<DeviceListBean> dList = new ArrayList<DeviceListBean>();
		try {
			//djList = deviceDelegate.selectAllData();
			
			dList.add(new DeviceListBean("Projector 1", "12345", "old projector"));
			dList.add(new DeviceListBean("Projector 2", "23456", "new projector"));

		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		//model.addObject("reservation", reservation);
		model.addAttribute("deviceList", dList);
		return "reserve";
	}
	
	@RequestMapping(value = "/reserve/{seqNo}/update", method = RequestMethod.GET)
	public String showUpdateReservationForm(@PathVariable("seqNo") int seqNo, Model model) {

		//logger.debug("showUpdateUserForm() : {}", id);

		ReservationBean reserve = reservationDelegate.getReservation(seqNo);
		model.addAttribute("reservation", reserve);
		
		return "reserve";

	}
}
