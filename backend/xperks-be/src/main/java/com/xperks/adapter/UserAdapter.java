package com.xperks.adapter;

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
                .superior(user.getSuperior() != null && user.getId() != user.getSuperior().getId() ? toUserModel(user.getSuperior()) : null)
                .build();
    }

    public User toUser(UserModel userModel) {
        return User
                .builder()
                .id(userModel.getId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .dateOfBirth(userModel.getDateOfBirth())
                .emailAddress(userModel.getEmailAddress())
                .employmentDate(userModel.getEmploymentDate())
                .superior(userModel.getSuperior() != null ? toUser(userModel.getSuperior()) : null)
                .build();
    }
}
