package com.xperks.service;

import com.xperks.adapter.UserAdapter;
import com.xperks.dto.UserMainInfo;
import com.xperks.dto.UserModel;
import com.xperks.persistence.User;
import com.xperks.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends EntityManagerSupport implements UserServiceIF {

    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Override
    @Transactional
    public UserModel getUser(int id) {
        return userAdapter.toUserModel(userRepository.getUserById(id));
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("No user found with id " + id);
        }
        return user;
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

    @Override
    @Transactional
    public void changeErdAddress(int id, String erdAddress) {
        if (StringUtils.isBlank(erdAddress)) {
            throw new IllegalArgumentException("erd address is missing");
        }
        User user = getUserById(id);
        user.setErdAddress(erdAddress);
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public List<UserMainInfo> getUserList(String searchValue) {
        if (StringUtils.isBlank(searchValue)) {
            return userAdapter.toUserMainInfoList(userRepository.findAll());
        }
        return userAdapter.toUserMainInfoList(userRepository.getUserByName(searchValue));
    }


}
