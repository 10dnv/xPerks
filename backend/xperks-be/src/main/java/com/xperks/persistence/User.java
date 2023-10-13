package com.xperks.persistence;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xperks.dto.Points;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Entity
@Table(name = "user_details")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
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
    @JsonIgnoreProperties("superior")
    private User superior;
    @Enumerated(EnumType.STRING)
    private Points balance;
    @Column(name = "erd_address")
    private String erdAddress;

}
