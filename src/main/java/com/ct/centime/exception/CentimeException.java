package com.ct.centime.exception;


public class CentimeException extends RuntimeException {
    public CentimeException(String message){
        super(message);
    }

    public CentimeException(Exception e){
        super(e);
    }
}
