package com.techfun.domain.login.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.techfun.domain.login.dto.LoginEmployeeDto;
import com.techfun.domain.login.service.LoginService;
import com.techfun.message.ResultMessages;

@Controller
public class LoginController {
	
	private static final String TOP_REDIRECT_PATH = "redirect:/employee/";
	
	private static final String LOGIN_PATH = "/login/login";
	
	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
    public ModelAndView login() {
		
		ModelAndView mav = new ModelAndView(LOGIN_PATH);
		LoginEmployeeDto loginEmployeeDto = new LoginEmployeeDto();

		mav.addObject("loginEmployeeDto", loginEmployeeDto);

		return mav;
    }
	
	@RequestMapping(value = "/login-error")
	public ModelAndView loginError(@Validated @ModelAttribute LoginEmployeeDto loginAdminDto, BindingResult result,
			Locale locale) {

		ModelAndView mav = new ModelAndView(LOGIN_PATH);

		ResultMessages resultMessages = loginService.validate(loginAdminDto, result);
		mav.addObject("resultMessages", resultMessages);
		return mav;
	}
	
	@RequestMapping(value = "/login-success")
	public String loginSsccess(){

		return TOP_REDIRECT_PATH;
	}
	
	@GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
