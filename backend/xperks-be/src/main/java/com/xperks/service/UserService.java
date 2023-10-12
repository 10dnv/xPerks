package com.xperks.service;

import com.xperks.adapter.UserAdapter;
import com.xperks.dto.UserModel;
import com.xperks.persistence.User;
import com.xperks.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceIF {

    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Transactional
    public List<User> getUserList() {
        return userRepository.findAll();
    }
    @Transactional
    public UserModel getUser(int id) {
        return userAdapter.toUserModel(userRepository.getUserById(id));
    }

    @Transactional
    public UserModel findUserByEmailAddress(String emailAddress) {
        User user = userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userAdapter.toUserModel(user);
    }
}
