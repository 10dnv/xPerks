package com.xperks.service.user;

import com.xperks.dto.user.UserMainInfo;
import com.xperks.dto.user.UserModel;
import com.xperks.persistence.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserServiceIF {
    UserModel getUser(int id);

    User getUserById(int id);
    
    UserModel findUserByEmailAddress(String emailAddress);

    boolean isSuperior();

    void changeErdAddress(String erdAddress);

    List<UserMainInfo> getUserList();

    void redeemEgld(int pts, BigDecimal amount) throws Exception;
}
