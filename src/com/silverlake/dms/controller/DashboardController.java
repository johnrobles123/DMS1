package com.silverlake.dms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.viewBean.DeviceBean;

@Controller
public class DashboardController {

	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public ModelAndView displayDashboard(HttpServletRequest request, HttpServletResponse response, DeviceBean deviceBean)
	{
		ModelAndView model = new ModelAndView("dashboard");
		//LoginBean loginBean = new LoginBean();
		model.addObject("deviceBean", deviceBean);
		return model;
	}
}
