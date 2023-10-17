package com.xperks.adapter;

import com.xperks.dto.user.UserMainInfo;
import com.xperks.dto.user.UserModel;
import com.xperks.persistence.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    public UserModel toUserModel(User user) {
        if (user == null) {
            return null;
        }
        return UserModel
                .builder()
                .emailAddress(user.getEmailAddress())
                .password(user.getPassword())
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .employmentDate(user.getEmploymentDate())
                .superior(toUserMainInfo(user.getSuperior()))
                .balance(user.getBalance())
                .erdAddress(user.getErdAddress())
                .build();
    }

    static UserMainInfo toUserMainInfo(User user) {
        return new UserMainInfo(user.getId(), user.getFirstName(), user.getLastName());
    }

    public List<UserMainInfo> toUserMainInfoList(List<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }
        List<UserMainInfo> userList = new ArrayList<>();
        users.forEach(user -> userList.add(toUserMainInfo(user)));
        return userList;
    }
}
