package com.techfun.domain.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techfun.database.mapper.EmployeesMapper;

@Repository
public class EmployeeDeleteDaoImpl implements EmployeeDeleteDao {

	@Autowired
	private EmployeesMapper employeesMapper;

	@Override
	public int employeeDelete(Integer emmployeeId) {
		return employeesMapper.deleteByPrimaryKey(emmployeeId);
	}
}
