package com.xperks.adapter;

import com.xperks.dto.UserMainInfo;
import com.xperks.dto.UserModel;
import com.xperks.persistence.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    public UserModel toUserModel(User user) {
        return UserModel
                .builder()
                .emailAddress(user.getEmailAddress())
                .password(user.getPassword())
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .employmentDate(user.getEmploymentDate())
                .superior(getUserMainInfo(user.getSuperior()))
                .balance(user.getBalance())
                .erdAddress(user.getErdAddress())
                .build();
    }

    static UserMainInfo getUserMainInfo(User user) {
        return new UserMainInfo(user.getId(), user.getFirstName(), user.getLastName());
    }
}
