package com.xperks.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "user_details")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Temporal(TemporalType.DATE)
    @Column(name = "employment_date")
    private Date employmentDate;
    @Column(name = "email_address")
    private String emailAddress;
    private String password;
    @JoinColumn(name = "superior_id")
    @OneToOne
    private User superior;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Wallet wallet;

    @Builder
    public User(int id, String firstName, String lastName, Date dateOfBirth, Date employmentDate, String emailAddress, String password, User superior, Wallet wallet) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.employmentDate = employmentDate;
        this.emailAddress = emailAddress;
        this.password = password;
        this.superior = superior;
        this.wallet = wallet;
    }
}
