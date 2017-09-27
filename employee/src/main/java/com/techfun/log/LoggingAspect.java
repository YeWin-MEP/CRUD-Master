package com.techfun.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.techfun.domain.employee.service.EmployeeInsertServiceImpl;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeInsertServiceImpl.class);

	@Before("@annotation(ApplyAspect)")
	public void loggingServiceBefore(JoinPoint jointPoint) {
		Signature signature = jointPoint.getSignature();
		String declareTypeName = signature.getDeclaringTypeName();
		String name = signature.getName();
		String methodName = declareTypeName + "." + name + "() ";
		logger.debug(methodName + "method has been started.");

	}

	@AfterReturning("@annotation(ApplyAspect)")
	public void loggingServiceAfterReturn(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		String declareTypeName = signature.getDeclaringTypeName();
		String name = signature.getName();
		String methodName = declareTypeName + "." + name + "() ";
		logger.debug(methodName + "method has been successfully finished.");
	}

	@AfterThrowing(pointcut = "@annotation(ApplyAspect)", throwing = "e")
	public void loggingServiceAfterThrow(JoinPoint joinPoint, RuntimeException e) {
		Signature signature = joinPoint.getSignature();
		String declareTypeName = signature.getDeclaringTypeName();
		String name = signature.getName();
		String methodName = declareTypeName + "." + name + "() ";
		logger.error(methodName + "method has been failed.");
		logger.error("Exception is ", e);
	}
}
