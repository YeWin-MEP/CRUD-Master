package com.techfun.security.dao;

import java.util.List;

import com.techfun.database.entity.Employees;

public interface EmployeeReferDao {

	List<Employees> getEmployeeInformationByEmail(String email);
}
