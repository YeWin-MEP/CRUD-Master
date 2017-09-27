package com.techfun.domain.login.service;

import org.springframework.validation.BindingResult;

import com.techfun.domain.login.dto.LoginEmployeeDto;
import com.techfun.message.ResultMessages;

public interface LoginService {

	public ResultMessages validate(LoginEmployeeDto loginAdminDto,
			BindingResult bindingResult);
}
