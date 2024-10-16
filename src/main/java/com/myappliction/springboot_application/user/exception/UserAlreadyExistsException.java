package com.myappliction.springboot_application.user.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {}

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
