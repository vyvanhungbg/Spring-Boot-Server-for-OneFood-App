package com.OneFood.ServerOneFood.exception;

import com.OneFood.ServerOneFood.model.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandle {
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseObject errorAccessDenied(AccessDeniedException exception) {
          return new ResponseObject(false,exception.getMessage(),null);
    }


    @ExceptionHandler(ErrorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject errorNotFoundException(ErrorNotFoundException exception) {
        return new ResponseObject(false,exception.getMessage(),null);
    }

    @ExceptionHandler(ErrorExecutionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject ErrorExecutionFailedException(ErrorExecutionFailedException exception) {
        return new ResponseObject(false,exception.getMessage(),null);
    }
}
