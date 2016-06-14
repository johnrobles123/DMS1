package com.silverlake.dms.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.delegate.DeviceDelegate;
import com.silverlake.dms.viewBean.DeviceBean;

@Controller
public class DashboardController {
	
	@Autowired
	private DeviceDelegate deviceDelegate;

	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public ModelAndView displayDashboard(HttpServletRequest request, HttpServletResponse response, DeviceBean deviceBean)
	{
		ModelAndView model = new ModelAndView("dashboard");
		
		try {
			deviceDelegate.addDevice();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addObject("deviceBean", deviceBean);
		return model;
	}
}
