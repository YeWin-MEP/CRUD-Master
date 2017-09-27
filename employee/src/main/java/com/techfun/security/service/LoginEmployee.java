package com.techfun.security.service;

import org.springframework.security.core.authority.AuthorityUtils;

import com.techfun.security.dto.AuthLoginEmployeeDto;

public class LoginEmployee extends
		org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	private final AuthLoginEmployeeDto authLoginUserDto;

	public LoginEmployee(AuthLoginEmployeeDto employee) {

		super(employee.getEmployeeId(), employee.getPassword(), AuthorityUtils
				.createAuthorityList("ROLE_ADMIN"));
		this.authLoginUserDto = employee;
	}

	public AuthLoginEmployeeDto getAuthLoginUserDto() {
		return authLoginUserDto;
	}
}
