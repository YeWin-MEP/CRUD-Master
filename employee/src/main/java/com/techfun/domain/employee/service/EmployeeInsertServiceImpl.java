package com.techfun.domain.employee.service;

import java.util.Locale;
import java.util.Objects;

import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.techfun.database.entity.Employees;
import com.techfun.domain.employee.dao.EmployeeInsertDao;
import com.techfun.domain.employee.dto.EmployeeDto;
import com.techfun.log.ApplyAspect;
import com.techfun.message.DisplayMessage;
import com.techfun.message.ResultMessages;
import com.techfun.util.StringUtil;

@Service
public class EmployeeInsertServiceImpl implements EmployeeInsertService {

	@Autowired
	EmployeeInsertDao employeeInsertDao;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	@ApplyAspect
	public ResultMessages validate(EmployeeDto employeesDto) {
		ResultMessages resultMessages = new ResultMessages();
		Locale locale = LocaleContextHolder.getLocale();

		if(checkPasswordIsEmpty(resultMessages, employeesDto.getPassword(),
				locale)){
			return resultMessages;
		}

		checkPasswordComplexity(resultMessages, employeesDto.getPassword(),
				locale);

		checkPasswordAndConfirmPasswordIsNotEqual(resultMessages,
				employeesDto.getPassword(),
				employeesDto.getConfirmPassword(), locale);
		
		return resultMessages;
	}
	
	private boolean checkPasswordIsEmpty(ResultMessages resultMessages,
			String password, Locale locale) {

		if (StringUtils.isEmpty(password)) {
			resultMessages.addError(new DisplayMessage(
					messageSource.getMessage("NotBlank",
							new Object[] { "Password" }, locale)));

			return true;
		}
		return false;
	}

	private boolean checkPasswordComplexity(ResultMessages resultMessages,
			String password, Locale locale) {

		if (!StringUtil.validatePassword(password)) {
			resultMessages.addError(new DisplayMessage(
					messageSource.getMessage("MSP0003",
							new Object[] { "Password" }, locale)));

			return true;
		}
		return false;
	}

	private boolean checkPasswordAndConfirmPasswordIsNotEqual(
			ResultMessages resultMessages, String password,
			String confirmPassword, Locale locale) {

		if (!Objects.equals(password, confirmPassword)) {
			resultMessages.addError(new DisplayMessage(messageSource
					.getMessage("MSP0004", new Object[] { "Password",
							"Confirm Password" }, locale)));
		}
		return false;
	}

	@Override
	@ApplyAspect
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public boolean insertEmployees(EmployeeDto employeeDto) throws Exception {

		employeeInsertDao.insertEmployees(setDtoModelToEntityModel(employeeDto));
		
		return true;
	}
	
	public Employees setDtoModelToEntityModel(EmployeeDto employeeDto) {
		Employees employee = new Employees();
		
		employee.setPassword(employeeDto.getPassword());
		employee.setEmail(employeeDto.getEmail());
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setPhone(employeeDto.getPhone());
		employee.setAddress(employeeDto.getAddress());
		employee.setRemark(employeeDto.getRemark());
		
		return employee;
	}

}
