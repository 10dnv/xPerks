package com.xperks.repository.companyWallet;

import com.xperks.persistence.CompanyWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyWalletRepository extends JpaRepository<CompanyWallet, Long>, CustomCompanyWalletRepository {

}
