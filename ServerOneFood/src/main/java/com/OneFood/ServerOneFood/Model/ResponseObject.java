package com.OneFood.ServerOneFood.Model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseObject<T> {
    private boolean status;
    private String message;
    private T data;


    public ResponseObject(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }



    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
