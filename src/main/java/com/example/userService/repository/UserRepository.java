package com.example.userService.repository;

import com.example.userService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByLogin(String login);
    User findUserByLoginAndPassword(String login, String password);
}
