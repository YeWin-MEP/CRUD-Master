package com.techfun.domain.employee.service;

import com.techfun.domain.employee.dto.EmployeeDto;
import com.techfun.message.ResultMessages;

public interface EmployeeInsertService {
	
	ResultMessages validate(EmployeeDto employeesDto) throws Exception;;

	boolean insertEmployees(EmployeeDto employeesDto) throws Exception;
}
