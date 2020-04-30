package com.kyc.catalogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kyc.catalogs.entity.PaymenthMethodsDTO;

public interface PaymenthMethodsRepository extends JpaRepository<PaymenthMethodsDTO, Integer> {

   @Query("from PaymenthMethodsDTO method where method.id=:id")
   public PaymenthMethodsDTO getPaymenthMethodById(@Param("id") Integer id);

}
