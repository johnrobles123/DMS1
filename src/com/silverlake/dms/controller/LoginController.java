package com.silverlake.dms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.delegate.LoginDelegate;
import com.silverlake.dms.viewBean.LoginBean;


@Controller
public class LoginController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LoginDelegate loginDelegate;

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
	{
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		ModelAndView model = new ModelAndView("login");
		//LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);
		return model;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean)
	{
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
	
		ModelAndView model= null;
		try
		{
				boolean isValidUser = loginDelegate.isValidUser(loginBean.getUsername(), loginBean.getPassword());
				if(isValidUser)
				{
						System.out.println("User Login Successful");
						request.setAttribute("loggedInUser", loginBean.getUsername());
						model = new ModelAndView("dashboard");
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

    public static void removeCookies(HttpServletRequest request) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null && cookies.length > 0) {
	        for (int i = 0; i < cookies.length; i++) {
	            cookies[i].setMaxAge(0);
	        }
	    }
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public void logout (HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean) throws SQLException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		loginDelegate.logout();
		
		HttpSession hs = request.getSession();
	    Enumeration<String> e = hs.getAttributeNames();
	    while (e.hasMoreElements()) {
	        String attr = e.nextElement();
	        hs.setAttribute(attr, null);
	    }
	    removeCookies(request);
	    hs.invalidate();		
		
		response.sendRedirect(request.getContextPath() + "/login");
		
		return;
	}
}
