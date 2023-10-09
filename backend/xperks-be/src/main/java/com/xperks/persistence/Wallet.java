package com.xperks.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table
@AttributeOverride(name = "id", column = @Column(name = "wallet_id"))
@Getter
@Setter
public class Wallet extends BaseEntity {

    private BigDecimal balance;
    @Column(name = "erd_address")
    private String erdAddress;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
