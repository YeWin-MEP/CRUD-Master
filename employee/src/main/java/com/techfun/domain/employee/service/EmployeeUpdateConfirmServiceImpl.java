package com.techfun.domain.employee.service;

import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.techfun.database.entity.Employees;
import com.techfun.domain.employee.dao.EmployeeUpdateConfirmDao;
import com.techfun.domain.employee.dto.EmployeeDto;
import com.techfun.log.ApplyAspect;

@Service
public class EmployeeUpdateConfirmServiceImpl implements EmployeeUpdateConfirmService {

	@Autowired
	EmployeeUpdateConfirmDao employeeUpdateConfirmDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	@ApplyAspect
	public boolean updateEmployee(EmployeeDto employeeDto)
			throws Exception {

		employeeUpdateConfirmDao
				.updateEmployee(changeEntityModel(employeeDto));

		return true;
	}

	private Employees changeEntityModel(EmployeeDto employeeDto)
			throws Exception {
		Employees employee = new Employees();

		employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setEmail(employeeDto.getEmail());
		employee.setPhone(employeeDto.getPhone());
		employee.setAddress(employeeDto.getAddress());
		employee.setRemark(employeeDto.getRemark());	

		return employee;
	}
}
