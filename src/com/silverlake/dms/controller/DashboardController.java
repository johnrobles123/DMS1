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

import com.silverlake.dms.delegate.DeviceDelegate;
import com.silverlake.dms.delegate.ReservationDelegate;
import com.silverlake.dms.viewBean.DeviceListBean;
import com.silverlake.dms.viewBean.ReservationBean;

@Controller
public class DashboardController {
	
	@Autowired
	private DeviceDelegate deviceDelegate;
	
	@Autowired
	private ReservationDelegate reservationDelegate;
	
	private static final String RETURNED = "R";

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
			if (serialNo.equalsIgnoreCase("*")) {
				reserveList = reservationDelegate.selectAll();
			} else {
				reserveList = reservationDelegate.selectAllByDeviceSerialNo(serialNo);
			}
			
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
		
		//model.addObject("deviceJournal", djList);
		model.addAttribute("deviceJournal", reserveList);
		model.addAttribute("deviceList", dList);
		model.addAttribute("deviceStatus", deviceStatusStr);
		model.addAttribute("selectedDevice", serialNo);

		return "dashboard";
	}
	
	@RequestMapping(value="/dashboard/{seqNo}/return",method=RequestMethod.POST)
	public String returnedDevice(@PathVariable("seqNo") int seqNo, Model model, ReservationBean reserveBean) throws SQLException {
		reservationDelegate.updateReservation(seqNo, RETURNED);
		
		//return "dashboard";
		return "redirect:/dashboard";
	}
}
