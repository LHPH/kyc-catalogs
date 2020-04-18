package com.kyc.catalogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyc.catalogs.entity.ServiceStatusDTO;

public interface ServiceStatusRepository extends JpaRepository<ServiceStatusDTO,Integer>{

}
