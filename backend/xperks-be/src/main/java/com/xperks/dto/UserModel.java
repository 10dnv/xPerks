package com.xperks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
public class UserModel extends CustomUserDetails {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Date employmentDate;
    @JsonIgnoreProperties("superior")
    private UserModel superior;
    @JsonIgnoreProperties("user")
    private WalletModel wallet;

    @Builder
    public UserModel(String emailAddress, String password, int id, String firstName, String lastName, Date dateOfBirth, Date employmentDate, UserModel superior, WalletModel wallet) {
        super(emailAddress, password);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.employmentDate = employmentDate;
        this.superior = superior;
        this.wallet = wallet;
    }

}
