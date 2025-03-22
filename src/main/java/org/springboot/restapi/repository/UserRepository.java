package org.springboot.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springboot.restapi.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findAllByOrderByRegDateAndTimeDesc();
}
