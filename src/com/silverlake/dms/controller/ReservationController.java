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

import com.silverlake.dms.delegate.ReservationDelegate;
import com.silverlake.dms.viewBean.*;

@Controller
@RequestMapping("/")
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
	
	public String createUpdateReservation(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("reservation")  ReservationBean reservation) throws SQLException
	{
		String ret = null;
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
		
		System.out.println(reservation.getSeqNo()+"seq no");
		if (reservation.getSeqNo() == 0) 
			reservationDelegate.create(reservation);
		else
			System.out.println(reservation.getReserveDate()+"reserve");
		System.out.println(reservation.getDeviceSerialNo()+"device");
			reservationDelegate.updateReservation(reservation);
		
		return ret;
	}
	
	@RequestMapping(value="/reserve",method=RequestMethod.GET)
	public String display(HttpServletRequest request, HttpServletResponse response, Model model, ReservationBean reservation)
	{	ReservationBean reserve = new ReservationBean();
		List<DeviceListBean> dList = new ArrayList<DeviceListBean>();
		List<String> tfList = new ArrayList<String>();
		List<String> ttList = new ArrayList<String>();
		
		tfList.add("08:00 AM");tfList.add("08:30 AM");tfList.add("09:00 AM");tfList.add("09:30 AM");
		ttList.add("08:29 AM");ttList.add("08:59 AM");ttList.add("09:29 AM");ttList.add("09:59 AM");
		
		try {
			//djList = deviceDelegate.selectAllData();
			DeviceListBean dlb = new DeviceListBean();
			dlb.setSerialNo("12345");
			dlb.setDeviceName("Projector 1");
			dlb.setAdditionalInfo("old projector");
			dList.add(dlb);
			
			DeviceListBean dlb2 = new DeviceListBean();
			dlb2.setSerialNo("23456");
			dlb2.setDeviceName("Projector 2");
			dlb2.setAdditionalInfo("new projector");
			dList.add(dlb2);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//model.addObject("reservation", reservation);
		model.addAttribute("deviceList", dList);
		model.addAttribute("reservation", reserve);
		model.addAttribute("fromList", tfList);
		model.addAttribute("toList", ttList);
		return "reserve";
	}
	
	@RequestMapping(value = "/reserve/{seqNo}/update", method = RequestMethod.GET)
	public String showUpdateReservationForm(@PathVariable("seqNo") int seqNo, Model model) {

		//logger.debug("showUpdateUserForm() : {}", id);
		List<DeviceListBean> dList = new ArrayList<DeviceListBean>();
		List<String> tfList = new ArrayList<String>();
		List<String> ttList = new ArrayList<String>();
		
		tfList.add("08:00 AM");tfList.add("08:30 AM");tfList.add("09:00 AM");tfList.add("09:30 AM");
		ttList.add("08:29 AM");ttList.add("08:59 AM");ttList.add("09:29 AM");ttList.add("09:59 AM");
		
		ReservationBean reserve = reservationDelegate.getReservation(seqNo);
		
		DeviceListBean dlb = new DeviceListBean();
		dlb.setSerialNo("12345");
		dlb.setDeviceName("Projector 1");
		dlb.setAdditionalInfo("old projector");
		dList.add(dlb);
		
		DeviceListBean dlb2 = new DeviceListBean();
		dlb2.setSerialNo("23456");
		dlb2.setDeviceName("Projector 2");
		dlb2.setAdditionalInfo("new projector");
		dList.add(dlb2);
		
		model.addAttribute("deviceList", dList);
		model.addAttribute("reservation", reserve);
		model.addAttribute("fromList", tfList);
		model.addAttribute("toList", ttList);
		
		return "reserve";

	}
}
