package com.silverlake.dms.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.delegate.DeviceDelegate;
import com.silverlake.dms.delegate.ReservationDelegate;
import com.silverlake.dms.grid.DeviceJournalGrid;
import com.silverlake.dms.viewBean.DeviceBean;
import com.silverlake.dms.viewBean.DeviceJournal;
import com.silverlake.dms.viewBean.DeviceListBean;
import com.silverlake.dms.viewBean.ReservationBean;

@Controller
public class DashboardController {
	
	@Autowired
	private DeviceDelegate deviceDelegate;
	
	@Autowired
	private ReservationDelegate reservationDelegate;

	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public String displayDashboard(Model model)
	{
		List<ReservationBean> reserveList = new ArrayList<ReservationBean>();
		List<ReservationBean> reserveDayList = new ArrayList<ReservationBean>();
		List<DeviceListBean> dList = new ArrayList<DeviceListBean>();
		String deviceStatusStr = null;
		
		try {
			//djList = deviceDelegate.selectAllData();
			deviceStatusStr = reservationDelegate.getAvailabilityStartTime("12345");
			reserveList = reservationDelegate.selectAll();
			
			dList.add(new DeviceListBean("Projector 1", "12345", "old projector"));
			dList.add(new DeviceListBean("Projector 2", "23456", "new projector"));
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//model.addObject("deviceJournal", djList);
		model.addAttribute("deviceJournal", reserveList);
		model.addAttribute("deviceList", dList);
		model.addAttribute("deviceStatus", deviceStatusStr);
		return "dashboard";
	}
	
	@RequestMapping(value="/dashboard/{serialNo}/refresh",method=RequestMethod.GET)
	public String refreshDashboard(@PathVariable("serialNo") String serialNo, Model model) {
		List<ReservationBean> reserveList = new ArrayList<ReservationBean>();
		//List<ReservationBean> djList = new ArrayList<ReservationBean>();
		List<DeviceListBean> dList = new ArrayList<DeviceListBean>();
		String deviceStatusStr = null;
		
		try {
			//reserveList = reservationDelegate.getCurrentDayRecords(serialNo);
			deviceStatusStr = reservationDelegate.getAvailabilityStartTime(serialNo);
			reserveList = reservationDelegate.selectAllByDeviceSerialNo(serialNo);
			
			dList.add(new DeviceListBean("Projector 1", "12345", "old projector"));
			dList.add(new DeviceListBean("Projector 2", "23456", "new projector"));
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//model.addObject("deviceJournal", djList);
		model.addAttribute("deviceJournal", reserveList);
		model.addAttribute("deviceList", dList);
		model.addAttribute("deviceStatus", deviceStatusStr);
		model.addAttribute("selectedDevice", serialNo);

		return "dashboard";
	}
}
