package com.techfun.domain.employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techfun.database.entity.Employees;
import com.techfun.database.entity.EmployeesExample;
import com.techfun.database.mapper.EmployeesMapper;

@Repository
public class EmployeeListDaoImpl implements EmployeeListDao {

	@Autowired
	private EmployeesMapper employeeMapper;

	@Override
	public List<Employees> getAdministratorList() {

		EmployeesExample employeesExample = new EmployeesExample();

		employeesExample.or().andEmployeeIdIsNotNull();

		return employeeMapper.selectByExample(employeesExample);
	}
}
