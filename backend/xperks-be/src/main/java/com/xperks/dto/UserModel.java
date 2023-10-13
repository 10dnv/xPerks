package com.xperks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.Date;


@Getter
@Setter
@SuperBuilder
public class UserModel extends CustomUserDetails {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Date employmentDate;
    @JsonIgnoreProperties("superior")
    private UserMainInfo superior;
    private Points balance;
    private String erdAddress;
}
