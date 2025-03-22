package org.springboot.restapi.exception;


import org.springboot.restapi.dto.ApiResponseDto;
import org.springboot.restapi.dto.ApiStatusDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(value = UserAlreadyExist.class)
    public ResponseEntity<ApiResponseDto<?>> UserAlreadyExistHandler(UserAlreadyExist userAlreadyExist) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDto<>(ApiStatusDto.ERROR.name(),userAlreadyExist.getMessage()));
    }

    @ExceptionHandler(value = UserNotExist.class)
    public ResponseEntity<ApiResponseDto<?>> UserNotExistException(UserNotExist userNotExist) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDto<>(ApiStatusDto.ERROR.name(),userNotExist.getMessage()));
    }

    @ExceptionHandler(value = UserServiceIssue.class)
    public ResponseEntity<ApiResponseDto<?>> UserServiceIssueHandler(UserServiceIssue userServiceIssue) {
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(ApiStatusDto.ERROR.name(),userServiceIssue.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<List<String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest()
                .body(new ApiResponseDto<>(ApiStatusDto.ERROR.name(), errors)); // Return List, not toString()
    }


}

