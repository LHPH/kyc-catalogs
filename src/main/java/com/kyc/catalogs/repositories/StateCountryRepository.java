package com.kyc.catalogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kyc.catalogs.entity.StatesCountryDTO;

public interface StateCountryRepository extends JpaRepository<StatesCountryDTO, Integer> {

   @Query("from StatesCountryDTO states where states.id=:id")
   public StatesCountryDTO getStatesCountryById(@Param("id") Integer id);

}
