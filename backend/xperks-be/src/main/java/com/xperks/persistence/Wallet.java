package com.xperks.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table
@AttributeOverride(name = "id", column = @Column(name = "wallet_id"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends BaseEntity {

    private BigDecimal balance;
    @Column(name = "erd_address")
    private String erdAddress;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
