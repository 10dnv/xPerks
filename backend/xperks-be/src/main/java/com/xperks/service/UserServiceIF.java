package com.xperks.service;

import com.xperks.dto.UserMainInfo;
import com.xperks.dto.UserModel;
import com.xperks.persistence.User;

import java.util.List;

public interface UserServiceIF {
    UserModel getUser(int id);

    User getUserById(int id);
    
    UserModel findUserByEmailAddress(String emailAddress);

    boolean isSuperior();

    void changeErdAddress(String erdAddress);

    List<UserMainInfo> getUserList(String searchValue);
}
