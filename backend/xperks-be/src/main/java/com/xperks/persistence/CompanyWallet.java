package com.xperks.persistence;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "company_wallet")
@AttributeOverride(name = "id", column = @Column(name = "company_wallet_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyWallet extends BaseEntity {

    private int amount;
}
