package com.shreks.onboarding.rest;

import com.shreks.onboarding.data.model.ErrorResponse;
import com.shreks.onboarding.util.exception.ApplicationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    /*
     * This is a custom exception thrown to handle application specific exceptions     *
     * */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException exception)
    {
        ErrorResponse errorResponseDTO = new ErrorResponse();
        errorResponseDTO.setErrorCode(exception.getErrorCode());
        errorResponseDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponseDTO, exception.getStatusCode());
    }
}
