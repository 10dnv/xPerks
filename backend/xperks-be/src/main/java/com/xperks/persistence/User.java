package com.xperks.persistence;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xperks.dto.Points;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(name = "first_name", nullable = false)
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 50)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    @Column(name = "employment_date", nullable = false)
    private Date employmentDate;

    @Column(name = "email_address", nullable = false)
    @Size(max = 100)
    private String emailAddress;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @JoinColumn(name = "superior_id", nullable = false)
    @OneToOne
    @JsonIgnoreProperties("superior")
    private User superior;

    @Enumerated(EnumType.STRING)
    private Points balance;

    @Column(name = "erd_address")
    @Size(max = 250)
    private String erdAddress;

}
