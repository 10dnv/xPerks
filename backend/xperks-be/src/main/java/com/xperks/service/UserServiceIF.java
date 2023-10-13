package com.xperks.service;

import com.xperks.dto.UserModel;

public interface UserServiceIF {
    UserModel getUser(int id);
    UserModel findUserByEmailAddress(String emailAddress);
    boolean isSuperior(int userId);

}
