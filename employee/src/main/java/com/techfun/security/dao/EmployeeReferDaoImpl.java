package com.techfun.security.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techfun.database.entity.Employees;
import com.techfun.database.entity.EmployeesExample;
import com.techfun.database.mapper.EmployeesMapper;

@Repository
public class EmployeeReferDaoImpl implements EmployeeReferDao {

	@Autowired
	private EmployeesMapper employeesMapper;

	@Override
	public List<Employees> getEmployeeInformationByEmail(String email) {

		EmployeesExample employeesExample = new EmployeesExample();
		employeesExample.or().andEmailEqualTo(email);

		return employeesMapper.selectByExample(employeesExample);
	}

}
