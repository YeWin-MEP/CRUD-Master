package com.techfun.domain.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import com.techfun.domain.employee.dto.EmployeeListDto;
import com.techfun.domain.employee.service.EmployeeListService;

@Controller
@RequestScope
@RequestMapping("/employee/*")
public class EmployeeListController {

	private static final String LIST_PATH = "/employee/employeeList";

	@Autowired
	private EmployeeListService employeeListService;

	@GetMapping(value = "/")
	public @ResponseBody ModelAndView employeeList() throws Exception {

		ModelAndView mav = new ModelAndView(LIST_PATH);

		List<EmployeeListDto> employeeList = employeeListService
				.getEmployeeList();

		mav.addObject("employeeList", employeeList);

		return mav;
	}
}