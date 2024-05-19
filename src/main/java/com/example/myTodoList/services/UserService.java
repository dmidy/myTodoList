package com.example.myTodoList.services;

import com.example.myTodoList.model.User;
import com.example.myTodoList.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User findByUsername(String username) {
        Optional<User> optionalUser = repository.findById(username);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));
    }
}
