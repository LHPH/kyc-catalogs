package com.kyc.catalogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kyc.catalogs.entity.AuthorizedBanksDTO;

public interface AuthorizedBanksRepository extends JpaRepository<AuthorizedBanksDTO, Integer> {

   @Query("from AuthorizedBanksDTO bank where bank.id=:id")
   public AuthorizedBanksDTO getAuthorizedBankById(@Param("id") Integer id);

}
