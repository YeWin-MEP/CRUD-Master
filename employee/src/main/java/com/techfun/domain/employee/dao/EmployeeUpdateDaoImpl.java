package com.techfun.domain.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techfun.database.entity.Employees;
import com.techfun.database.mapper.EmployeesMapper;

@Repository
public class EmployeeUpdateDaoImpl implements EmployeeUpdateDao {

	@Autowired
	private EmployeesMapper employeesMapper;

	@Override
	public Employees getEmployeeById(Integer employeeId) {
		return employeesMapper.selectByPrimaryKey(employeeId);
	}
}