package com.xperks.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class WalletModel {
    private BigDecimal balance;
    private String erdAddress;
    private UserModel user;
}
