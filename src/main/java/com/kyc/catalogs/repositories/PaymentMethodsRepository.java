package com.kyc.catalogs.repositories;

import com.kyc.catalogs.entity.PaymentMethodsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentMethodsRepository extends JpaRepository<PaymentMethodsDTO, Integer> {

   @Query("from PaymentMethodsDTO method where method.id=:id")
   public PaymentMethodsDTO getPaymenthMethodById(@Param("id") Integer id);

}
