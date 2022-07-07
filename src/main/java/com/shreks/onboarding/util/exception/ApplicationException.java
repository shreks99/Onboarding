package com.shreks.onboarding.util.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
public class ApplicationException extends RuntimeException
{
    private HttpStatus statusCode;

    private String errorCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    private String debugMessage;

    private ApplicationException() {
        timestamp = LocalDateTime.now();
    }



    public  ApplicationException(HttpStatus statusCode, ApplicationErrorCodes errorCode, String message) {
        this();
        this.statusCode = statusCode;
        this.errorCode = errorCode.getReasonPhrase();
        this.message = message;
    }

    public  ApplicationException(ApplicationErrorCodes errorCode, String message) {
        this();
        this.errorCode = errorCode.getReasonPhrase();
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }
}
