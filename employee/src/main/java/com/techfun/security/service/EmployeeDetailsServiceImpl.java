package com.techfun.security.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techfun.database.entity.Employees;
import com.techfun.domain.login.dto.SessionLoginEmployeeDto;
import com.techfun.log.ApplyAspect;
import com.techfun.security.dao.EmployeeReferDao;
import com.techfun.security.dto.AuthLoginEmployeeDto;
import com.techfun.util.Constant;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeReferDao employeeReferDao;

	@Autowired
	private HttpSession session;

	@Override
	@ApplyAspect
	public UserDetails loadUserByUsername(String adminEmail)
			throws UsernameNotFoundException {

		List<Employees> administratorList = getEmployeeInformationByEmail(adminEmail);

		AuthLoginEmployeeDto employee = getAuthLoginEmployeeDtoWithAuthData(administratorList);

		saveAdminInformationInSession(administratorList);

		return new LoginEmployee(employee);
	}

	private void saveAdminInformationInSession(
			List<Employees> employeeList) {
		SessionLoginEmployeeDto sessionLoginEmployeeDto = new SessionLoginEmployeeDto();
		sessionLoginEmployeeDto.setEmployeeId(employeeList.get(0).getEmployeeId());
		sessionLoginEmployeeDto.setEmployeeName(employeeList.get(0)
				.getEmployeeName());
		sessionLoginEmployeeDto.setEmail(employeeList.get(0)
				.getEmail());
		session.setAttribute(Constant.SESSION_KEY, sessionLoginEmployeeDto);
	}

	private AuthLoginEmployeeDto getAuthLoginEmployeeDtoWithAuthData(
			List<Employees> employeeList) {

		AuthLoginEmployeeDto employee = new AuthLoginEmployeeDto();

		employee.setEmployeeId(String.valueOf(employeeList.get(0).getEmployeeId()));
		employee.setPassword(employeeList.get(0).getPassword());

		return employee;
	}

	private List<Employees> getEmployeeInformationByEmail(String email)
			throws UsernameNotFoundException {

		List<Employees> employeeList = employeeReferDao
				.getEmployeeInformationByEmail(email);

		if (employeeList.isEmpty()) {
			throw new UsernameNotFoundException(
					"Employee information was not found");
		}

		return employeeList;
	}
}
