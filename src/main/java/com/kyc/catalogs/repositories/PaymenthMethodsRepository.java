package com.kyc.catalogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyc.catalogs.entity.PaymenthMethodsDTO;

public interface PaymenthMethodsRepository extends JpaRepository<PaymenthMethodsDTO,Integer>{

}
