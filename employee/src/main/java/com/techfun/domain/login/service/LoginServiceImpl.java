package com.techfun.domain.login.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.techfun.domain.login.dto.LoginEmployeeDto;
import com.techfun.message.DisplayMessage;
import com.techfun.message.ResultMessages;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public ResultMessages validate(LoginEmployeeDto loginAdminDto,
			BindingResult bindingResult) {

		ResultMessages resultMessages = new ResultMessages();
		Locale locale = LocaleContextHolder.getLocale();

		if (!bindingResult.hasErrors()) {
			resultMessages.addError(new DisplayMessage(messageSource
					.getMessage("MEP0001", new Object[] { "Employee information" }, locale)));
		}

		return resultMessages;
	}

}
