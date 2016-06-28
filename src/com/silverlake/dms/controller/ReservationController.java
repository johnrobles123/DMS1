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
	private final List<String> tfList = new ArrayList<String>();
	private final List<String> ttList = new ArrayList<String>();
	
	public ReservationController()
	{
		tfList.add("08:00 AM");tfList.add("08:30 AM");tfList.add("09:00 AM");tfList.add("09:30 AM");
		tfList.add("10:00 AM");tfList.add("10:30 AM");tfList.add("11:00 AM");tfList.add("11:30 AM");
		tfList.add("12:00 PM");tfList.add("12:30 PM");tfList.add("01:00 PM");tfList.add("01:30 PM");
		tfList.add("02:00 PM");tfList.add("02:30 PM");tfList.add("03:00 PM");tfList.add("03:30 PM");
		tfList.add("04:00 PM");tfList.add("04:30 PM");tfList.add("05:00 PM");tfList.add("05:30 PM");
		tfList.add("06:00 PM");tfList.add("06:30 PM");tfList.add("07:00 PM");tfList.add("07:30 PM");
		tfList.add("08:00 PM");tfList.add("08:30 PM");
		ttList.add("08:29 AM");ttList.add("08:59 AM");ttList.add("09:29 AM");ttList.add("09:59 AM");
		ttList.add("10:29 AM");ttList.add("10:59 AM");ttList.add("11:29 AM");ttList.add("11:59 AM");
		ttList.add("12:29 PM");ttList.add("12:59 PM");ttList.add("01:29 PM");ttList.add("01:59 PM");
		ttList.add("02:29 PM");ttList.add("02:59 PM");ttList.add("03:29 PM");ttList.add("03:59 PM");
		ttList.add("04:29 PM");ttList.add("04:59 PM");ttList.add("05:29 PM");ttList.add("05:59 PM");
		ttList.add("06:29 PM");ttList.add("06:59 PM");ttList.add("07:29 PM");ttList.add("07:59 PM");
		ttList.add("08:29 PM");ttList.add("08:59 PM");
	}
	
	private List<DeviceListBean> getDeviceList()
	{
		List<DeviceListBean> dList = new ArrayList<DeviceListBean>();
		
		
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
		
		return dList;
	}
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
		List<DeviceListBean> dList = getDeviceList();
		
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
		List<DeviceListBean> dList = getDeviceList();
		
		ReservationBean reserve = reservationDelegate.getReservation(seqNo);
		
		model.addAttribute("deviceList", dList);
		model.addAttribute("reservation", reserve);
		model.addAttribute("fromList", tfList);
		model.addAttribute("toList", ttList);
		
		return "reserve";

	}
	
	@RequestMapping(value = "/viewreserve/{seqNo}", method = RequestMethod.GET)
	public String showViewReservationForm(@PathVariable("seqNo") int seqNo, Model model) {

		//logger.debug("showUpdateUserForm() : {}", id);
		List<DeviceListBean> dList = getDeviceList();
		ReservationBean reserve = reservationDelegate.getReservation(seqNo);
		
		
		model.addAttribute("deviceList", dList);
		model.addAttribute("reservation", reserve);
		model.addAttribute("fromList", tfList);
		model.addAttribute("toList", ttList);
		
		return "viewreserve";

	}
}
