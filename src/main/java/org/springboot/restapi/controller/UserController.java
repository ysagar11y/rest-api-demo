package org.springboot.restapi.controller;

import jakarta.validation.Valid;
import org.springboot.restapi.dto.ApiResponseDto;
import org.springboot.restapi.dto.ApiStatusDto;
import org.springboot.restapi.dto.UserRegisterDto;
import org.springboot.restapi.exception.UserAlreadyExist;
import org.springboot.restapi.exception.UserNotExist;
import org.springboot.restapi.exception.UserServiceIssue;
import org.springboot.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@Valid @RequestBody UserRegisterDto user) throws UserAlreadyExist, UserServiceIssue {
        userService.registerUser(user);
        return ResponseEntity.ok().body(new ApiResponseDto<>(ApiStatusDto.SUCCESS.name(),user+"got registered successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceIssue {
        userService.getAllUsers();
        return ResponseEntity.ok().body(new ApiResponseDto<>(ApiStatusDto.SUCCESS.name(), userService.getAllUsers()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<?>> updateUser(@Valid @RequestBody UserRegisterDto userDetailsRequestDto, @PathVariable Long id) throws UserServiceIssue, UserNotExist {
        userService.updateUser(userDetailsRequestDto, id);
        return ResponseEntity.ok().body(new ApiResponseDto<>(ApiStatusDto.SUCCESS.name(),"User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable Long id) throws UserNotExist, UserServiceIssue {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(new ApiResponseDto<>(ApiStatusDto.SUCCESS.name(),"User deleted successfully"));
    }

}
