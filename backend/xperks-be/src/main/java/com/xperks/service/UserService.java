package com.xperks.service;

import com.xperks.persistence.User;
import com.xperks.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> getUserList() {
        return userRepository.findAll();
    }
    @Transactional
    public User getUser(int id) {
        return userRepository.getUserById(id);
    }

}
