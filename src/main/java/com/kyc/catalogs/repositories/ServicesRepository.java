package com.kyc.catalogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kyc.catalogs.entity.ServicesDTO;

public interface ServicesRepository extends JpaRepository<ServicesDTO,Integer>{

	@Query("from ServicesDTO service where service.id=:id")
	public ServicesDTO getServiceById(@Param("id") Integer id);
	
}
