package com.techfun.domain.employee.service;

import java.util.List;

import com.techfun.domain.employee.dto.EmployeeListDto;

public interface EmployeeListService {

	List<EmployeeListDto> getEmployeeList() throws Exception;
}
