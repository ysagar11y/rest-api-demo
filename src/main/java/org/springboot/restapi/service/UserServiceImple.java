package org.springboot.restapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springboot.restapi.dto.ApiResponseDto;
import org.springboot.restapi.dto.ApiStatusDto;
import org.springboot.restapi.dto.UserRegisterDto;
import org.springboot.restapi.entity.User;
import org.springboot.restapi.exception.UserAlreadyExist;
import org.springboot.restapi.exception.UserNotExist;
import org.springboot.restapi.exception.UserServiceIssue;
import org.springboot.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserServiceImple implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> registerUser(UserRegisterDto newUserDetails) throws UserAlreadyExist, UserServiceIssue {
        try {
            if (userRepository.findByEmail(newUserDetails.getEmail()) != null){
                throw new UserAlreadyExist("Registration failed: User already exists with email " + newUserDetails.getEmail());
            }
            if (userRepository.findByUsername(newUserDetails.getUsername()) != null){
                throw new UserAlreadyExist("Registration failed: User already exists with username " + newUserDetails.getUsername());
            }

            User newUser = new User();
            newUser.setEmail(newUserDetails.getEmail());
            newUser.setPhone(newUserDetails.getPhone());
            newUser.setUsername(newUserDetails.getUsername());
            newUser.setRegDateAndTime(LocalDateTime.now());

            // save() is an in built method given by JpaRepository
            userRepository.save(newUser);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiStatusDto.SUCCESS.name(), newUser));

        }catch (UserAlreadyExist e) {
            throw e;
        }catch (Exception e) {
            log.error("Failed to create new user account: " + e.getMessage());
            throw new UserServiceIssue();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceIssue {

        try{
            List<User> users = userRepository.findAllByOrderByRegDateAndTimeDesc();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto<>(ApiStatusDto.SUCCESS.name(), users));
        }
        catch (UserServiceIssue e) {
            throw e;
        }
        catch (Exception e){
            log.error("Failed to get all users: " + e.getMessage());
            throw new UserServiceIssue();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateUser(UserRegisterDto newUserDetails, Long id) throws UserNotExist, UserServiceIssue {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotExist("User not found with id " + id));
            user.setEmail(newUserDetails.getEmail());
            user.setPhone(newUserDetails.getPhone());
            user.setUsername(newUserDetails.getUsername());
            user.setRegDateAndTime(LocalDateTime.now());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiStatusDto.SUCCESS.name(),user));
        }
        catch (UserNotExist e) {
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to update user: " + e.getMessage());
            throw new UserServiceIssue();

        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(Long id) throws UserServiceIssue, UserNotExist {
        try{
            if(!userRepository.existsById(id)){ throw new UserNotExist("User not found with id " + id);}
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto<>("Deleted the user with Id:" + id,ApiStatusDto.SUCCESS.name()));
        }
        catch (UserNotExist e) {
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to delete user: " + e.getMessage());
            throw new UserServiceIssue();
        }
    }
}
