package com.techfun.domain.employee.service;

import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.techfun.database.entity.Employees;
import com.techfun.domain.employee.dao.EmployeeUpdateDao;
import com.techfun.domain.employee.dto.EmployeeDto;
import com.techfun.log.ApplyAspect;

@Service
public class EmployeeUpdateServiceImpl implements EmployeeUpdateService {

	@Autowired
	EmployeeUpdateDao employeeUpdateDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	@ApplyAspect
	public EmployeeDto getEmployeeById(Integer employeeId)
			throws Exception {

		Employees employee = employeeUpdateDao
				.getEmployeeById(employeeId);

		return changeDtoModel(employee);
	}

	private EmployeeDto changeDtoModel(Employees employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		
		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setEmployeeName(employee.getEmployeeName());
		employeeDto.setPhone(employee.getPhone());
		employeeDto.setAddress(employee.getAddress());
		employeeDto.setRemark(employee.getRemark());

		return employeeDto;
	}
}
