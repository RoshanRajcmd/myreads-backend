package com.myappliction.springboot_application.user.exception;

public class NoSuchUserExistsException extends RuntimeException{

    public NoSuchUserExistsException() {}

    public NoSuchUserExistsException(String msg) {
        super(msg);
    }
}
