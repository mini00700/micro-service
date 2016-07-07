package com.tcl.user.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BasedErrorHandleController {
	private static Logger LOG =
			LoggerFactory.getLogger(BasedErrorHandleController.class);

	@ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
    public ApiError handleUnexpectedRequestError(MissingServletRequestParameterException ex,
    		                                 HttpServletRequest request) {
    	ApiError error = new ApiError(request,HttpStatus.BAD_REQUEST.value(),ex.getMessage(),null);
        return error;
    }

	@ExceptionHandler(HttpStatusCodeException.class)
	@ResponseBody
    public ApiError handleUnexpectedServerError(HttpStatusCodeException ex,
    		                                 HttpServletRequest request,HttpServletResponse response) {
		//TODO monitor the exceptions we catch. delete it after dev.
		LOG.error("exception: http status code is "+ex.getStatusCode().value()+",message is "+ex.getMessage());
		ApiError error = new ApiError(request,ex.getStatusCode().value(),ex.getMessage(),null);
    	response.setStatus(ex.getStatusCode().value());
        return error;
    }


	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
    public ApiError handleUnexpectedServerError(UserNotFoundException ex,
    		                                 HttpServletRequest request,HttpServletResponse response) {
		//TODO monitor the exceptions we catch. delete it after dev.
		LOG.error("exception: http status code is 400,message is "+ex.getMessage());
		ApiError error = new ApiError(request,400,ex.getMessage(),null);
    	response.setStatus(400);
        return error;
    }

	@ExceptionHandler(DuplicateUserException.class)
	@ResponseBody
    public ApiError handleUnexpectedServerError(DuplicateUserException ex,
    		                                 HttpServletRequest request,HttpServletResponse response) {
		//TODO monitor the exceptions we catch. delete it after dev.
		LOG.error("exception: http status code is 400,message is "+ex.getMessage());
		ApiError error = new ApiError(request,400,ex.getMessage(),null);
    	response.setStatus(400);
        return error;
    }

	/**
	 * deal of custom exceptions
	 */
	@ExceptionHandler(CustomeHttpStatusCodeException.class)
	@ResponseBody
    public ApiError handleHttpStatusCodeException(CustomeHttpStatusCodeException ex,
    		                                 HttpServletRequest request,HttpServletResponse response){ //throws IOException
		LOG.error("exception: http status code is "+ex.getStatusCode()+",error code is "+ex.getErrorCode()+",message is "+ex.getErrorDesc());
		ApiError error = new ApiError(request,ex.getErrorCode(),ex.getErrorDesc(),null);
    	response.setStatus(ex.getStatusCode());
		return error;
    }

}
