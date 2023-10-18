package com.xperks.dto.transaction;

import com.xperks.dto.enums.Points;
import com.xperks.dto.enums.Status;
import com.xperks.dto.enums.TransactionReason;
import com.xperks.dto.enums.TransactionType;
import com.xperks.dto.user.UserMainInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private TransactionReason reason;
    private Date transactionDate;
    private TransactionType transactionType;
}
