package com.xperks.repository.user;

import com.xperks.persistence.User;

import java.util.List;

public interface CustomUserRepository {

    int countSuperiorById(int userId);

    List<User> getListWithoutLoggedUser(int id);
}
