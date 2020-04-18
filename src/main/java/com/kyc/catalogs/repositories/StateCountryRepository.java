package com.kyc.catalogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyc.catalogs.entity.StatesCountryDTO;

public interface StateCountryRepository extends JpaRepository<StatesCountryDTO,Integer>{

}
