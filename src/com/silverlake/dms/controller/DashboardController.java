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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.delegate.DeviceDelegate;
import com.silverlake.dms.grid.DeviceJournalGrid;
import com.silverlake.dms.viewBean.DeviceBean;
import com.silverlake.dms.viewBean.DeviceJournal;
import com.silverlake.dms.viewBean.DeviceListBean;

@Controller
public class DashboardController {
	
	@Autowired
	private DeviceDelegate deviceDelegate;

	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public String displayDashboard(Model model)
	{
		List<DeviceJournal> djList = new ArrayList<DeviceJournal>();
		List<DeviceListBean> dList = new ArrayList<DeviceListBean>();
		
		try {
			djList = deviceDelegate.selectAllData();
			dList.add(new DeviceListBean("Projector 1", "1", "old projector"));
			dList.add(new DeviceListBean("Projector 2", "2", "new projector"));

		    /*
			int totalNumberOfPages = 1;
		    int currentPageNumber = 1;
		    int totalNumberOfRecords = djList.size();
		    
		    DeviceJournalGrid<DeviceJournal> gd = new DeviceJournalGrid<DeviceJournal>(totalNumberOfPages, currentPageNumber, totalNumberOfRecords, djList);
		    System.out.println("Grid Data: " + gd.getJsonString());
		    response.getWriter().write(gd.getJsonString());
		    */
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//model.addObject("deviceJournal", djList);
		model.addAttribute("deviceJournal", djList);
		model.addAttribute("deviceList", dList);
		return "dashboard";
	}
}
