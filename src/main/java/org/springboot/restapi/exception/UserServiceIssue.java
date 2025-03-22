package org.springboot.restapi.exception;


import lombok.AllArgsConstructor;
import lombok.Data;


public class UserServiceIssue extends RuntimeException {
    public UserServiceIssue() {
        super("Service issue occurred");
    }
}
