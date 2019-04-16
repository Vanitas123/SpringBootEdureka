package com.journey.vanita.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("execution(* com.journey.vanita.customexceptions.GlobalExceptionHandler.handleIOException(..))")
	private void logDebugMessage() {}
	
	@Before("execution(* com.journey.vanita.services.HotelServices.bookHotelRoom(..))")
	public void myAdviceForLogError() {
		System.out.println("Aspect here..");
		LOGGER.error("bookHotelRoom error ");
	}
	
	
}
