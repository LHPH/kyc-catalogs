package com.kyc.catalogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyc.catalogs.entity.AuthorizedBanksDTO;

public interface AuthorizedBanksRepository extends JpaRepository<AuthorizedBanksDTO,Integer>{

}
