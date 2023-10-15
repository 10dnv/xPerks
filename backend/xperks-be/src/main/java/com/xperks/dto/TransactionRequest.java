package com.xperks.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    @NotNull
    private Integer receiverId;

    @NotNull
    private Points amount;

    private String description;

    @NotNull
    private TransactionType type;
}
