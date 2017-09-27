package com.techfun.security.dto;

import java.io.Serializable;

public class AuthLoginEmployeeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String employeeId;
	
	private String email;
	
	private String password;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
