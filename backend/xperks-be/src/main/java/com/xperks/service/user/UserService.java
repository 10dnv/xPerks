package com.xperks.service.user;

import com.xperks.adapter.UserAdapter;
import com.xperks.dto.user.UserMainInfo;
import com.xperks.dto.user.UserModel;
import com.xperks.persistence.User;
import com.xperks.repository.user.UserRepository;
import com.xperks.security.AuthUtil;
import com.xperks.service.EntityManagerSupport;
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
    public boolean isSuperior() {
        /* check if current user is someone's superior */
        int count = userRepository.countSuperiorById(AuthUtil.getAuthenticatedUserId());
        return count != 0;
    }

    @Override
    @Transactional
    public void changeErdAddress(String erdAddress) {
        if (StringUtils.isBlank(erdAddress)) {
            throw new IllegalArgumentException("erd address is missing");
        }
        /* change erd address for current user */
        User user = getUserById(AuthUtil.getAuthenticatedUserId());
        user.setErdAddress(erdAddress);
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public List<UserMainInfo> getUserList() {
        /* get all users that can be recognized (witho */
        List<User> users = userRepository.getListWithoutLoggedUser(AuthUtil.getAuthenticatedUserId());
        return userAdapter.toUserMainInfoList(users);
    }


}
