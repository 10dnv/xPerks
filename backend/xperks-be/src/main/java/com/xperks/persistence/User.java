package com.xperks.persistence;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_details")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Getter
@Setter
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "employment_date")
    private Date employmentDate;
    @Column(name = "email_address")
    private String emailAddress;
    @JoinColumn(name = "superior_id")
    @JsonIgnoreProperties("superior")
    @OneToOne
    private User superior;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private Wallet wallet;
}
