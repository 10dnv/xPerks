package com.xperks.service;

import com.xperks.dto.UserModel;
import com.xperks.persistence.User;

public interface UserServiceIF {
    UserModel getUser(int id);
    User getUserById(int id);
    UserModel findUserByEmailAddress(String emailAddress);
    boolean isSuperior(int userId);
}
