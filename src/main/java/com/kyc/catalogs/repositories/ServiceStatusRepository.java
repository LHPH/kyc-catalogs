package com.kyc.catalogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kyc.catalogs.entity.ServiceStatusDTO;

public interface ServiceStatusRepository extends JpaRepository<ServiceStatusDTO, Integer> {

   @Query("from ServiceStatusDTO status where status.id=:id")
   public ServiceStatusDTO getServiceStatusById(@Param("id") Integer id);

}
