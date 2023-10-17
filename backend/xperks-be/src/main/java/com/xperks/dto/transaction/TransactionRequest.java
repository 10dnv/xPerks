package com.xperks.dto.transaction;

import com.xperks.dto.enums.Points;
import com.xperks.dto.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
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
