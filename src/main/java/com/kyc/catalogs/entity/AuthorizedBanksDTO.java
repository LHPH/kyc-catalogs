package com.kyc.catalogs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="AUTHORIZED_BANKS")
public class AuthorizedBanksDTO {
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="CVE_BANK")
	private String cveBank;
	
	@Column(name="NAME")
	private String name;
	

}
