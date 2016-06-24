package com.silverlake.dms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.silverlake.dms.delegate.DeviceListDelegate;
import com.silverlake.dms.delegate.UserDelegate;
import com.silverlake.dms.viewBean.DeviceListBean;
import com.silverlake.dms.viewBean.User;


@Controller
public class AdminController {

	@Autowired
	private DeviceListDelegate deviceListDelegate;
	
	@Autowired
	private UserDelegate userDelegate;
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String displayAdmin(Model model)
	{
		List<DeviceListBean> deviceList = new ArrayList<DeviceListBean>();
		List<User> userList = new ArrayList<User>();
		
		try {
			deviceList = deviceListDelegate.selectAllData();
			userList = userDelegate.selectAllData();
					    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("deviceList", deviceList);
		model.addAttribute("userList", userList);
		
		return "admin";
	}
	
}
