package com.cdac.osvs.Exception;

public class EmailException extends RuntimeException{

    public EmailException(){}
    public EmailException(String message){
        super(message);
    }
}
