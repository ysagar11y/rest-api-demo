package org.springboot.restapi.exception;

public class UserNotExist extends RuntimeException {
    public UserNotExist(String message) {
        super(message);
    }
}
