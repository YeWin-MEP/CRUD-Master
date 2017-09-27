package com.techfun.domain.login.dto;

import java.io.Serializable;

public class SessionLoginEmployeeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer employeeId;
	
	private String employeeName;
	
	private String email;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
