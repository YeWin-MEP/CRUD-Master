package com.techfun.domain.employee.service;

import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.techfun.domain.employee.dao.EmployeeDeleteDao;
import com.techfun.log.ApplyAspect;

@Service
public class EmployeeDeleteServiceImpl implements EmployeeDeleteService {

	@Autowired
	EmployeeDeleteDao employeeDeleteDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	@ApplyAspect
	public boolean employeeDelete(Integer employeeId) throws Exception {
		
		employeeDeleteDao.employeeDelete(employeeId);

		return true;
	}
}
