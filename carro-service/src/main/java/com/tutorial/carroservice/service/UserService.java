package com.tutorial.carroservice.service;

import com.tutorial.carroservice.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse((null));
    }

    public User save(User user) {
        User userNew = userRepository.save(user);
        return userNew;
    }
}
