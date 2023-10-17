package com.xperks.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table
@AttributeOverride(name = "id", column = @Column(name = "notification_id"))
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseEntity {

    @JoinColumn(name = "transaction_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Transaction transaction;

    private boolean unread;
}
