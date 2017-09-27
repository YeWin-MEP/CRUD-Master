package com.techfun.domain.employee.dao;

import java.util.List;

import com.techfun.database.entity.Employees;

public interface EmployeeListDao {

	public List<Employees> getAdministratorList();
}
