package com.xperks.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionModel {

    private int id;
    private UserMainInfo sender;
    private UserMainInfo receiver;
    private UserMainInfo approver;
    private Status status;
    private Points amount;
    private String description;
    private TransactionType type;
}
