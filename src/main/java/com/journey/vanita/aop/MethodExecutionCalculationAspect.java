package com.journey.vanita.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MethodExecutionCalculationAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * Example Usage  
	 * 
	 * @Service
	 *		public class Business1 {
	 *	
	 *		@TrackTime
	 *		public String calculateSomething(){
	 * 
	 * */
	
	// Example to create annotation for around
	@Around("@annotation(com.journey.vanita.aop.TrackTime)")
	public Object aroundUsingAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		Object ret = joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
		return ret;
	}
	
	@Around("within(com.journey.vanita..*)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		Object ret = joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Around without annotation Time Taken by {} is {}", joinPoint, timeTaken);
		return ret;
	}
}
