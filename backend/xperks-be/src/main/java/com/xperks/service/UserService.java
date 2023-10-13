package com.xperks.service;

import com.xperks.adapter.UserAdapter;
import com.xperks.dto.UserModel;
import com.xperks.persistence.User;
import com.xperks.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceIF {

    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Override
    @Transactional
    public UserModel getUser(int id) {
        return userAdapter.toUserModel(userRepository.getUserById(id));
    }

    @Override
    @Transactional
    public UserModel findUserByEmailAddress(String emailAddress) {
        User user = userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userAdapter.toUserModel(user);
    }

    @Override
    @Transactional
    public boolean isSuperior(int userId) {
        int count = userRepository.countSuperiorById(userId);
        return count != 0;
    }
}
