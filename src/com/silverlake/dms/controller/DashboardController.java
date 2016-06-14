package com.silverlake.dms.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.repositories.DeviceJournalLocal;
import com.silverlake.dms.repositories.entities.DeviceJournal;

@Controller
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DeviceJournalLocal deviceJournal;
	
	public DashboardController() {
		 
    }

	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public ModelAndView displayDashboard(HttpServletRequest request, HttpServletResponse response, DeviceJournal deviceJournal)
	{
		ModelAndView model = new ModelAndView("dashboard");
		//LoginBean loginBean = new LoginBean();
		
		//List<DeviceJournal> deviceJournalList = new ArrayList<DeviceJournal>();
		
		model.addObject("deviceJournal", deviceJournal);
		return model;
	}
}
