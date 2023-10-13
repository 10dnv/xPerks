package com.xperks.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    private int receiverId;
    private Points amount;
}
