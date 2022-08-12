package com.OneFood.ServerOneFood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ErrorExecutionFailedException extends Exception{
    private static final long serialVersionUID = 1L;
    public ErrorExecutionFailedException(String message) {
        super(message);
    }
}
