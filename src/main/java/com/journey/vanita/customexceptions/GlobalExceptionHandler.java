package com.journey.vanita.customexceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.journey.vanita.web.dtos.GlobalErrorResponse;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	private GlobalErrorResponse error;
	
	GlobalExceptionHandler(GlobalErrorResponse e)
	{
		this.error =  e;
	}
	
	// this is very specific to hotels, hence can e kept in hotelscontroller itself
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Bookings exceeds available")
	@ExceptionHandler(BookingsFullException.class)
	public String handleIOException(BookingsFullException ex){
		logger.debug("handling booing full exception");
		return ex.getMessage();
		
	}

	//general exception when element is not found by id in db
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public @ResponseBody GlobalErrorResponse return400(NoSuchElementException ex) {
		error.setErrorCode(HttpStatus.NOT_FOUND);
		error.setErrorMessage(ex.getMessage());
        return error;
    }
}
