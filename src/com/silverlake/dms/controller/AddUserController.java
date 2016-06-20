package com.silverlake.dms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.silverlake.dms.delegate.AddUserDelegate;
import com.silverlake.dms.viewBean.AddUserBean;

@Controller("modify")
public class AddUserController {

	@Autowired
	private AddUserDelegate addUserDelegate;

	// @RequestMapping(value = "/login", method = RequestMethod.GET)
	// public ModelAndView displayLogin(HttpServletRequest request,
	// HttpServletResponse response, AddUserDelegate addUserBean) {
	// ModelAndView model = new ModelAndView("login");
	// // LoginBean loginBean = new addUserBean();
	// model.addObject("addUserBean", addUserBean);
	// return model;
	// }

	@RequestMapping(value = "/userdd", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("addUserBean") AddUserBean addUserBean) {
		ModelAndView model = null;
		try {
			boolean addUser = addUserDelegate.addUser(
					addUserBean.getFirstName(), addUserBean.getLasttName(),
					addUserBean.getIsAdmin());
			if (addUser) {
				System.out.println("User successfully added.");

				request.setAttribute("addedUser", addUserBean.getFirstName());
//				response.sendRedirect("/DMS1/useradd");
			} else {
				model = new ModelAndView("adduser");
				request.setAttribute("message", "Error!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
}
