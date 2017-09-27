package com.techfun.domain.login.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class LoginEmployeeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Email
	@NotBlank
	private String email;	
	
	@NotBlank
	private String password;

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
