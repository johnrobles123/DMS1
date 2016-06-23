package com.silverlake.dms.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.delegate.UserDelegate;
import com.silverlake.dms.viewBean.User;

@Controller
public class UserController {

	@Autowired
	private UserDelegate userDelegate;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request,
			HttpServletResponse response, User userBean) {
		ModelAndView model = new ModelAndView("user");
		// LoginBean loginBean = new addUserBean();
		model.addObject("userBean", userBean);
		return model;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("addUser")  User user) throws SQLException {
		String ret = null;
		try {
			request.setAttribute("addedUser", user.getId());
			ret = "user";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userDelegate.save(user);
		
		return ret;
		
	}
}
