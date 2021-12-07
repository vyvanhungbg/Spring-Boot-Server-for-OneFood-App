package com.OneFood.ServerOneFood.exception;

public class ErrorAccessDeniedException extends Exception{
    private static final long serialVersionUID = 1L;

    public ErrorAccessDeniedException(String message) {
        super(message);
    }
}
