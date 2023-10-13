package com.xperks.persistence;

import com.xperks.dto.Points;
import com.xperks.dto.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "transaction")
@AttributeOverride(name = "id", column = @Column(name = "transaction_id"))
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

    @JoinColumn(name = "sender_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User sender;
    @JoinColumn(name = "receiver_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User receiver;
    @JoinColumn(name = "approver_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User approver;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Points amount;
}
