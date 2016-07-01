package com.silverlake.dms.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.silverlake.dms.service.DeviceListService;
import com.silverlake.dms.viewBean.DeviceListBean;


@Controller
public class DeviceListController {


	@Autowired
	private DeviceListService deviceListService;
	
	
	//list page
	@RequestMapping(value = "/devicelist", method = RequestMethod.GET)
	public String showAllDevices(Model model) throws SQLException {
		model.addAttribute("deviceList", deviceListService.findAll());
		return "devicelist/list";

	}
	

	// save or update device	
	@RequestMapping(value="/devicelist",method=RequestMethod.POST)
	public String saveOrUpdateDevice(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("deviceForm")  DeviceListBean deviceList) throws SQLException
	{
		String ret = null;
		Model model= null;  

		deviceListService.saveOrUpdate(deviceList);
		
	    return "redirect:/devicelist/" + deviceList.getSerialNo();
		
	}
	
	

	// show add device form
	@RequestMapping(value = "/devicelist/add", method = RequestMethod.GET)
	public String showAddDeviceForm(Model model) {

		DeviceListBean device = new DeviceListBean();

		
		model.addAttribute("deviceForm", device);

		return "devicelist/deviceform";

	}

	
	// show update form
	@RequestMapping(value = "/devicelist/{serialNo}/update", method = RequestMethod.GET)
	public String showUpdateDeviceForm(@PathVariable("serialNo") String serialNo, Model model) throws SQLException {


		DeviceListBean device = deviceListService.findById(serialNo);
		model.addAttribute("deviceForm", device);
			
		return "devicelist/deviceform";

	}


	// delete device
	@RequestMapping(value = "/devicelist/{serialNo}/delete", method = RequestMethod.POST)
	public String deleteDevice(HttpServletRequest request, HttpServletResponse response, @PathVariable("serialNo") String serialNo) throws SQLException {

		deviceListService.delete(serialNo);
		//return "redirect:/devicelist/list";
		return "redirect:/admin";

	}	
	

	// show device
	@RequestMapping(value = "/devicelist/{serialNo}", method = RequestMethod.GET)
	public String showDevice(@PathVariable("serialNo") String serialNo, Model model) throws SQLException {

		DeviceListBean device = deviceListService.findById(serialNo);
		if (device == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Device not found");
		}
		model.addAttribute("device", device);

		return "devicelist/show";

	}	
	
}
