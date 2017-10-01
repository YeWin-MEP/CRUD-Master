package com.techfun.domain.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techfun.database.entity.Employees;
import com.techfun.database.mapper.EmployeesMapper;

@Repository
public class EmployeeUpdateConfirmDaoImpl implements EmployeeUpdateConfirmDao {

	@Autowired
	private EmployeesMapper employeesMapper;

	@Override
	public int updateEmployee(Employees employee) {
		return employeesMapper.updateByPrimaryKeySelective(employee);
	}
}
