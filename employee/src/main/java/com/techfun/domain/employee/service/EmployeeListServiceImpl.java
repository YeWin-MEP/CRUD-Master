package com.techfun.domain.employee.service;

import java.util.List;

import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.techfun.database.entity.Employees;
import com.techfun.domain.employee.dao.EmployeeListDao;
import com.techfun.domain.employee.dto.EmployeeListDto;
import com.techfun.log.ApplyAspect;

@Service
public class EmployeeListServiceImpl implements EmployeeListService {

	@Autowired
	private EmployeeListDao employeeListDao;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	@ApplyAspect
	public List<EmployeeListDto> getEmployeeList() throws Exception {

		List<Employees> employee = employeeListDao.getAdministratorList();

		List<EmployeeListDto> employeeListDto = (List<EmployeeListDto>) (List<?>) employee;

		return employeeListDto;
	}
}
