package com.techfun.domain.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.techfun.domain.employee.dto.EmployeeDto;
import com.techfun.domain.employee.service.EmployeeDeleteService;
import com.techfun.domain.employee.service.EmployeeInsertService;
import com.techfun.domain.employee.service.EmployeeUpdateConfirmService;
import com.techfun.domain.employee.service.EmployeeUpdateService;
import com.techfun.message.MessageHelper;
import com.techfun.message.ResultMessages;

@Controller
@RequestMapping("/employee/*")
public class EmployeeEditController {

	private static final String INPUT_PATH = "/employee/employeeInput";

	private static final String INPUT_COMPLETE_PATH = "/employee/employeeInputComplete";

	private static final String UPDATE_PATH = "/employee/employeeUpdate";

	private static final String UPDATE_COMPLETE_PATH = "/employee/employeeUpdateComplete";

	private static final String EMPLOYEE_DTO = "employeeDto";

	@Autowired
	private EmployeeInsertService employeeInsertService;

	@Autowired
	private EmployeeDeleteService employeeDeleteService;

	@Autowired
	private EmployeeUpdateService employeeUpdateService;

	@Autowired
	private EmployeeUpdateConfirmService employeeUpdateConfirmService;

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

		if (checkInputValueIsValid(bindingResult, employeeDto, mav)) {
			return mav;
		}

		employeeInsertService.insertEmployees(employeeDto);

		messageHelper.setCompleteMessage(mav, "MSP0002");

		return mav;
	}

	@GetMapping(value = "/delete/{employeeId}")
	public String employeeDelete(@PathVariable("employeeId") Integer employeeId)
			throws Exception {

		employeeDeleteService.employeeDelete(employeeId);

		return "redirect:/employee/";
	}

	@GetMapping(value = "/update/{employeeId}")
	public ModelAndView employeeUpdate(
			@ModelAttribute("employeeId") Integer employeeId) throws Exception {

		ModelAndView mav = new ModelAndView(UPDATE_PATH);

		EmployeeDto employeeDto = employeeUpdateService
				.getEmployeeById(employeeId);

		mav.addObject(EMPLOYEE_DTO, employeeDto);

		return mav;
	}

	@PostMapping(value = "/updateConfirm")
	public ModelAndView employeeUpdateConfirm(
			@Validated @ModelAttribute("employeeDto") EmployeeDto employeeDto,
			BindingResult bindingResult) throws Exception {

		ModelAndView mav = new ModelAndView(UPDATE_COMPLETE_PATH);
		mav.addObject(employeeDto);

		if (checkBeanValidator(bindingResult, UPDATE_COMPLETE_PATH, mav)) {
			return mav;
		}

		employeeUpdateConfirmService.updateEmployee(employeeDto);

		messageHelper.setCompleteMessage(mav, "MSP0002");

		return mav;
	}

	private boolean checkInputValueIsValid(BindingResult bindingResult,
			EmployeeDto employeeDto, ModelAndView mav) throws Exception {
		boolean flag = false;
		if (checkBeanValidator(bindingResult, INPUT_PATH, mav)) {
			return true;
		}

		if (checkCustomValidator(employeeDto, INPUT_PATH, mav)) {
			flag = true;
		}

		return flag;
	}

	private boolean checkBeanValidator(BindingResult bindingResult,
			String path, ModelAndView mav) {

		if (bindingResult.hasErrors()) {
			mav.setViewName(path);
			return true;
		}

		return false;
	}

	private boolean checkCustomValidator(EmployeeDto employeeDto,
			String path, ModelAndView mav) throws Exception {

		ResultMessages resultMessages = employeeInsertService
				.validate(employeeDto);

		if (!resultMessages.getErrorList().isEmpty()) {
			mav.addObject("resultMessages", resultMessages);
			mav.setViewName(path);
			return true;
		}

		return false;
	}
}
