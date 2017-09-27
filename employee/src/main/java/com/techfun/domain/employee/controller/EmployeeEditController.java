package com.techfun.domain.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.techfun.domain.employee.dto.EmployeeDto;
import com.techfun.domain.employee.service.EmployeeInsertService;
import com.techfun.message.MessageHelper;
import com.techfun.message.ResultMessages;

@Controller
@RequestMapping("/employee/*")
public class EmployeeEditController {

	private static final String INPUT_PATH = "/employee/employeeInput";
	
	private static final String INPUT_COMPLETE_PATH = "/employee/employeeInputComplete";
	
	private static final String EMPLOYEE_DTO = "employeeDto";
	
	@Autowired
	private EmployeeInsertService employeeInsertService;
	
	@Autowired
	private MessageHelper messageHelper;
	
	@GetMapping(value = "/insert")
	public ModelAndView init() {

		ModelAndView mav = new ModelAndView(INPUT_PATH);
		mav.addObject(EMPLOYEE_DTO, new EmployeeDto());
		return mav;
	}
	
	@PostMapping(value = "/insertConfirm")
	public @ResponseBody ModelAndView employeeInsert(
			@Validated @ModelAttribute(EMPLOYEE_DTO) EmployeeDto employeeDto,
			BindingResult bindingResult) throws Exception {

		ModelAndView mav = new ModelAndView(INPUT_COMPLETE_PATH);
		mav.addObject(employeeDto);

		if (bindingResult.hasErrors()) {
			mav.setViewName(INPUT_PATH);
			return mav;
		}
		
		ResultMessages resultMessages = employeeInsertService
				.validate(employeeDto);

		if (!resultMessages.getErrorList().isEmpty()) {
			mav.addObject("resultMessages", resultMessages);
			mav.setViewName(INPUT_PATH);
			return mav;
		}

		employeeInsertService.insertEmployees(employeeDto);

		messageHelper.setCompleteMessage(mav, "MSP0002");

		return mav;
	}
}
