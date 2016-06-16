package com.silverlake.dms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.viewBean.*;

public class ReservationController {
	@Autowired
	private LoginDelegate loginDelegate;

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, ReservationBean loginBean)
	{
		ModelAndView model = new ModelAndView("login");
		//LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);
		return model;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean)
	{
			ModelAndView model= null;
			try
			{
					boolean isValidUser = loginDelegate.isValidUser(loginBean.getUsername(), loginBean.getPassword());
					if(isValidUser)
					{
							System.out.println("User Login Successful");
							request.setAttribute("loggedInUser", loginBean.getUsername());
							//model = new ModelAndView("dashboard");
							response.sendRedirect("/DMS1/dashboard");
					}
					else
					{
							model = new ModelAndView("login");
							request.setAttribute("message", "Invalid credentials!!");
					}

			}
			catch(Exception e)
			{
					e.printStackTrace();
			}

			return model;
	}
}
