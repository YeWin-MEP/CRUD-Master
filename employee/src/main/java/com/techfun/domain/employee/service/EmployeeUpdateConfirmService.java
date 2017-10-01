package com.techfun.domain.employee.service;

import com.techfun.domain.employee.dto.EmployeeDto;

public interface EmployeeUpdateConfirmService {

	boolean updateEmployee(EmployeeDto employeeDto)throws Exception;
}
