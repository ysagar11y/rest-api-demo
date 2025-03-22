package org.springboot.restapi.service;

import org.springboot.restapi.dto.ApiResponseDto;
import org.springboot.restapi.dto.UserRegisterDto;
import org.springboot.restapi.exception.UserAlreadyExist;
import org.springboot.restapi.exception.UserNotExist;
import org.springboot.restapi.exception.UserServiceIssue;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<ApiResponseDto<?>> registerUser(UserRegisterDto newUserDetails)
            throws UserAlreadyExist, UserServiceIssue;

    ResponseEntity<ApiResponseDto<?>> getAllUsers()
            throws UserServiceIssue;

    ResponseEntity<ApiResponseDto<?>> updateUser(UserRegisterDto newUserDetails, Long id)
            throws UserNotExist, UserServiceIssue;

    ResponseEntity<ApiResponseDto<?>> deleteUser(Long id)
            throws UserServiceIssue, UserNotExist;
}
